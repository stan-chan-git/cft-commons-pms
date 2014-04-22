package cft.commons.pms.web.sweibo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.dto.sina.SinaComDto;
import cft.commons.pms.dto.sina.SinaDTO;
import cft.commons.pms.web.sweibo.sinaUtil.SinaUtil;

@Controller
public class SinaWeiBoController {

	private static final String APP_KEY = "4281626272";
	private static final String CLIENT_SECET = "69eade123bb72a88abadcdde95e8e6ae";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/sina/sinaweibo.do";

	/* 授权controller */
	@RequestMapping(value = "sinaweibo.do")
	public String getOauth2Access_token(String code, Model model, HttpServletRequest request)
			throws IOException {

		System.out.println("===========code===========");
		System.out.println(code);
		System.out.println("===========code===========");

		// 通过回调地址获取code，再去访问新浪微博获取access_token
		String url = "https://api.weibo.com/oauth2/access_token";
		Map<String, String> nvpMap = new HashMap<String, String>();
		nvpMap.put("client_id", APP_KEY);
		nvpMap.put("client_secret", CLIENT_SECET);
		nvpMap.put("redirect_uri", REDIRECT_URL);
		nvpMap.put("code", code);
		String result = HttpClientUtils.httpPost(nvpMap, url, 9000, 9000);
		System.out.println("===========result===========");
		System.out.println(result);
		System.out.println("===========result===========");

		String access_token = "";// 从result中获取access_token的值

		if (result != null && !result.equals("")) {// 取消授权

			if (result.contains("access_token")) {
				access_token = result.split(",")[0];
				access_token = access_token.substring(17, access_token.length() - 1);
			}
			System.out.println("===========access_token===========");
			System.out.println(access_token);
			System.out.println("===========access_token===========");

			String uid = "";// 从result中获取uid的值
			if (result.contains("uid")) {
				uid = result.split(",")[3];
				uid = uid.substring(7, uid.length() - 2);
			}
			System.out.println("===========uid===========");
			System.out.println(uid);
			System.out.println("===========uid===========");

			// 返回access_token和uid到jsp页面
			request.getSession().setAttribute("sina_token", access_token);
			request.getSession().setAttribute("uid", uid);

			return "easyui/layout";
		}

		request.getSession().setAttribute("sinareturn", "你取消了新浪授权");
		return "sina/sinareturn"; // 失败页面
	}

	/* 发文字微博controller */
	@RequestMapping(value = "sinaStatusesUpdate.do")
	public @ResponseBody
	String sina_Statuses_update(String status, HttpServletRequest request) throws IOException {
		System.out.println("==========123");
		System.out.println(request.getSession().getAttribute("sina_token"));
		String access_token = (String) request.getSession().getAttribute("sina_token");
		System.out.println(access_token);

		// 有授权或者获取到token
		if (access_token != null && !access_token.equals("")) {

			String updateUrl = "https://api.weibo.com/2/statuses/update.json";
			Map<String, String> upMap = new HashMap<String, String>();
			upMap.put("access_token", access_token);
			upMap.put("status", status);
			String upsString = HttpClientUtils.httpPost(upMap, updateUrl, 5000, 5000);

			// 将返回值转成JSON，获取需要的信息
			JSONObject jsonObject = new JSONObject(upsString);
			Long id = (Long) jsonObject.get("id");

			if (id != null) {
				request.getSession().setAttribute("sinareturn", "发布一条微博信息成功");
				return "success";// 成功页面
			}
		}

		request.getSession().setAttribute("sinareturn", "发布一条微博信息失败");
		return "failure"; // 失败页面
	}

	/* 发图片微博controller */
	@RequestMapping(value = "sinaStatusesUpload.do")
	public @ResponseBody
	String sina_Statuses_upload(@RequestParam("pic") MultipartFile file, String status,
			Model model, HttpServletRequest request) throws IOException {
		String access_token = (String) request.getSession().getAttribute("sina_token");
		String picString = request.getSession().getServletContext().getRealPath("/")
				+ "\\static\\images\\test.jpg";
		System.out.println(picString);
		if (access_token != null && !access_token.equals("")) {

			String uploadUrl = "https://upload.api.weibo.com/2/statuses/upload.json";
			Map<String, String> params = new HashMap<String, String>();
			params.put("access_token", access_token);
			params.put("status", status);
			Map<String, byte[]> itemsMap = new HashMap<String, byte[]>();
			byte[] content = SinaUtil.readFileImage(picString);
			// byte[] content = file.getBytes();
			itemsMap.put("pic", content);
			String returnString = SinaUtil.postMethodRequestWithFile(uploadUrl, params,
					SinaUtil.header, itemsMap);

			JSONObject jsonObject = new JSONObject(returnString);
			Long id = (Long) jsonObject.get("id");

			if (id != null) {
				request.getSession().setAttribute("sinareturn", "发含有图片的微博成功");
				return "success"; // 返回页面
			}
		}
		request.getSession().setAttribute("sinareturn", "发含有图片的微博失败");
		return "failure"; // 失败页面
	}

	/* 转发一条微博信息controller */
	@RequestMapping(value = "sinaStatusesRepost.do")
	public String sina_Statuses_repost(String id, String status, HttpServletRequest request)
			throws IOException {

		String access_token = (String) request.getSession().getAttribute("sina_token");

		// 有授权或者获取到token
		if (access_token != null && !access_token.equals("")) {

			String repostUrl = "https://api.weibo.com/2/statuses/repost.json";
			Map<String, String> reMap = new HashMap<String, String>();
			reMap.put("access_token", access_token);
			reMap.put("id", id);
			reMap.put("status", status);
			String reString = HttpClientUtils.httpPost(reMap, repostUrl, 3000, 3000);

			System.out.println(reString);
			// 将返回值转成JSON，获取需要的信息
			JSONObject jsonObject = new JSONObject(reString);
			Long returnId = (Long) jsonObject.get("id");
			System.out.println(returnId);
			if (returnId != null) {
				request.getSession().setAttribute("sinareturn", "转发一条微博信息成功");
				return "sina/sinareturn";// 成功页面
			}
		}

		request.getSession().setAttribute("sinareturn", "转发一条微博信息失败");
		return "sina/sinareturn"; // 失败页面
	}

	/* 获取评论列表controller */
	@RequestMapping(value = "sinaCommentsToMe.do")
	public String sina_Comments_tome(HttpServletRequest request) throws IOException, ParseException {

		String access_token = (String) request.getSession().getAttribute("sina_token");
		Long since_id = (Long) request.getSession().getAttribute("since_id");

		// 有授权或者获取到token
		if (access_token != null && !access_token.equals("")) {

			String friendUrl = "https://api.weibo.com/2/comments/to_me.json?" + "access_token="
					+ access_token + "&since_id=" + since_id;

			String tomeString = HttpClientUtils.httpGet(friendUrl, 3000, 3000);
			System.out.println(tomeString);

			// 将返回值转成JSON，获取需要的信息
			JSONObject jsonObject = new JSONObject(tomeString);
			JSONArray comments = jsonObject.getJSONArray("comments");

			System.out.println(comments);

			if (comments != null && comments.length() > 0) {// 如有新的评论则显示

				List<SinaComDto> sinaComDTOs = new ArrayList<SinaComDto>();
				Long[] ids = new Long[comments.length()];

				for (int i = 0; i < comments.length(); i++) {// 提取对应信息
					JSONObject tempComments = (JSONObject) comments.get(i);
					String textRepost = tempComments.getString("text");// 评论的内容
					String created_at = tempComments.getString("created_at");// 评论创建时间
					Long id = tempComments.getLong("id");// 评论的ID
					JSONObject tempUser = tempComments.getJSONObject("user");// 评论作者的用户信息
					String screen_name = (String) tempUser.get("screen_name");// 评论作者的用户昵称
					JSONObject tempStatus = tempComments.getJSONObject("status");// 评论的微博信息字段
					String textStatus = (String) tempStatus.get("text");// 微博信息内容
					ids[i] = id;

					// 将需要的参数写入DTO，然后前台显示
					SinaComDto sinaComDTO = new SinaComDto();
					sinaComDTO.setId(id);
					sinaComDTO.setCreated_at(SinaUtil.SinaDateFormat(created_at));
					sinaComDTO.setScreen_name(screen_name);
					sinaComDTO.setText(textRepost);
					sinaComDTO.setTextStatus(textStatus);

					sinaComDTOs.add(sinaComDTO);
				}

				request.getSession().setAttribute("since_id", ids[0]);// 取到上次最后评论的id
				request.setAttribute("sinaComDTOs", sinaComDTOs);
				return "sina/sina_comments_tome";// 成功页面
			}
			request.getSession().setAttribute("sinareturn", "没有最新评论");
			return "sina/sinareturn"; // 返回页面
		}

		request.getSession().setAttribute("sinareturn", "获取评论列信息失败");
		return "sina/sinareturn"; // 失败页面
	}

	/* 获取关注人的微博controller */
	@RequestMapping(value = "sinaStatuseFriends.do")
	public String sina_Comments_friends(HttpServletRequest request) throws IOException,
			ParseException {

		String access_token = (String) request.getSession().getAttribute("sina_token");
		String uid = (String) request.getSession().getAttribute("uid");
		// 有授权或者获取到token
		if (access_token != null && !access_token.equals("")) {

			String friendUrl = "https://api.weibo.com/2/statuses/friends_timeline.json?"
					+ "access_token=" + access_token;

			String friendString = HttpClientUtils.httpGet(friendUrl, 3000, 3000);
			System.out.println(friendString);

			// 将返回值转成JSON，获取需要的信息
			JSONObject jsonObject = new JSONObject(friendString);
			JSONArray statuses = jsonObject.getJSONArray("statuses");
			System.out.println(statuses);

			if (statuses != null && statuses.length() > 0) {// 如有新的好友动态则显示

				List<SinaDTO> sinaDTOs = new ArrayList<SinaDTO>();
				for (int i = 0; i < statuses.length(); i++) {// 提取对应信息
					JSONObject tempStatuses = (JSONObject) statuses.get(i);
					String text = tempStatuses.getString("text");
					String created_at = tempStatuses.getString("created_at");
					Long id = tempStatuses.getLong("id");
					JSONObject tempUser = tempStatuses.getJSONObject("user");
					String screen_name = (String) tempUser.get("screen_name");
					String userIdStr = tempUser.getString("idstr");

					// 非自己的微博写入DTO
					if (!userIdStr.equals(uid)) {
						SinaDTO sinaDTO = new SinaDTO();
						sinaDTO.setId(id);
						sinaDTO.setCreated_at(SinaUtil.SinaDateFormat(created_at));
						sinaDTO.setText(text);
						sinaDTO.setUserId(userIdStr);
						sinaDTO.setScreen_name(screen_name);

						sinaDTOs.add(sinaDTO);
					}
				}

				request.getSession().setAttribute("sinareturn", "获取评论列表信息成功");
				request.getSession().setAttribute("sinaDTOs", sinaDTOs);
				return "sina/sina_statuses_friends";// 成功页面
			}
			request.getSession().setAttribute("sinareturn", "没有最新好友动态");
			return "sina/sinareturn"; // 返回页面
		}

		request.getSession().setAttribute("sinareturn", "获取关注的人动态信息失败");
		return "sina/sinareturn"; // 失败页面
	}

}
