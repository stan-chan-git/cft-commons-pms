package cft.commons.pms.service.api;

/**
 * @author luffy
 *
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.util.SinaConstants;
import cft.commons.pms.web.sweibo.sinaUtil.SinaUtil;

public class SinaService {

	/**
	 * 
	 * @explanation sina微博发布新文字微博
	 * @param sian_token
	 *            ：access_token
	 * @param status
	 *            ：要发布的文字信息
	 * @return "success"和"failure"字符串
	 * @throws IOException
	 */
	public String SinaStatusesUpdate(String sina_token, String status) throws IOException {
		
		/*是否有授权*/
		if (sina_token != null && !sina_token.equals("")) {

			String Url = SinaConstants.StatusesUpdateUrl;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("access_token", sina_token);
			params.put("status", status);
			String upString = HttpClientUtils.httpPost(params, Url, 3000, 3000);

			/*将返回值转成JSON，获取需要的信息*/
			JSONObject jsonObject = new JSONObject(upString);
			Long id = (Long) jsonObject.get("id");

			if (id != null) {
				return "success";/*成功*/
			}
		}
		return "failure";/*失败*/
	}
	
/**
 * 
 * @explanation sina微博发布含有图片的微博
 * @param sina_token：access_token
 * @param status ：要发布的文字信息
 * @param file ：要发布的图片文件，使用multipart/form-data格式
 * @return "success"和"failure"字符串
 * @throws IOException
 */
	public static String SinaStatusesUpload(String sina_token, String status, MultipartFile file) throws IOException {
		
		/*是否有授权*/
		if (sina_token != null && !sina_token.equals("")) {

			String Url = SinaConstants.StatusesUploadUrl;
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("access_token", sina_token);
			params.put("status", status);
			
			Map<String, byte[]> itemsMap = new HashMap<String, byte[]>();
			byte[] pic = file.getBytes();
			itemsMap.put("pic", pic);
			
			String returnString = SinaUtil.postMethodRequestWithFile(Url, params, SinaUtil.header, itemsMap);
			
			/*将返回值转成JSON，获取需要的信息*/
			JSONObject jsonObject = new JSONObject(returnString);
			Long id = (Long)jsonObject.get("id");
			
			if (id!=null) {
				return "success";/*成功*/
			}
		}
		return "failure";/*失败*/
	}
}
