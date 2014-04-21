package cft.commons.pms.service.api;
/**
 * @author luffy
 *
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.util.SinaConstants;


public class SinaService {

	public String  SinaStatusesUpdate(String sina_token,String status) throws IOException {

		if (sina_token != null && !sina_token.equals("")) {

			String updateUrl = SinaConstants.StatusesUpdateUrl;
			Map<String, String> upMap = new HashMap<String, String>();
			upMap.put("access_token", sina_token);
			upMap.put("status", status);
			String upString = HttpClientUtils.httpPost(upMap, updateUrl, 3000, 3000);

			// 将返回值转成JSON，获取需要的信息
			JSONObject jsonObject = new JSONObject(upString);
			Long id = (Long)jsonObject.get("id");

			if (id!=null) {
				return "success";// 成功
			}	
		}
		return "failure";// 失败
	}
	
}
