package cft.commons.pms.web.sweibo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cft.commons.pms.web.sweibo.sinaUtil.SinaUtil;

@Controller
public class SinaViewController {

	@RequestMapping(value ="sina_statuses_update")
	public String updateView() {
		return "sina/sina_statuses_update";	
	}
	
	@RequestMapping(value ="sina_statuses_upload")
	public String uploadView() {
		return "sina/sina_statuses_upload";	
	}
	
	@RequestMapping(value ="sina_statuses_repost")
	public String repostView() {
		return "sina/sina_statuses_repost";	
	}
	
	@RequestMapping(value ="sina_statuses_repost2")
	public String repost2View(String sina_id,Model model) {
		model.addAttribute("sina_id", sina_id);
		return "sina/sina_statuses_repost2";	
	}
	
	@RequestMapping(value ="sina_comments_tome")
	public String toMeView() {
		return "sina/sina_comments_tome";	
	}
	
		
//	@RequestMapping(value ="sinaTest.do")
//	public String testdoView(@RequestParam("pic") MultipartFile file,Model model,HttpServletRequest request) throws IOException {
//		String access_token = (String) request.getSession().getAttribute("sina_token");
//		System.out.println("======32=1======");
//		if (access_token!= null && !access_token.equals("") ) {
//			
//			String uploadUrl = "https://upload.api.weibo.com/2/statuses/upload.json";
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("access_token", access_token);
//			params .put("status", "能不能发送图片");
//			System.out.println("======32=1======");
//			Map<String, byte[]> itemsMap = new HashMap<String, byte[]>();
//			System.out.println("======32=1======");
//			byte[] content = file.getBytes();
//			itemsMap.put("pic",content);
//			SinaUtil.postMethodRequestWithFile(uploadUrl, params, header, itemsMap);
//			
//			request.getSession().setAttribute("sinareturn", "发含有图片的微博成功");
//			return "sina/sinareturn"; // 返回页面
//		}
//		request.getSession().setAttribute("sinareturn", "发含有图片的微博失败");
//		return "sina/sinareturn";	// 失败页面
//	}
//	
//	public static Map<String, String> header = new HashMap<String, String>();
//	
//	static{
//		header.put("Accept-Language", "zh-CN,zh;q=0.8");
//		header.put("User-Agent", "test sina api");
//		header.put("Accept-Charset", "utf-8;q=0.7,*;q=0.3");
//	}
	
}
