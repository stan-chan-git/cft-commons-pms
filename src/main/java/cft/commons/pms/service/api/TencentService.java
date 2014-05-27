package cft.commons.pms.service.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.web.api.util.ApiUtils;

public class TencentService {
	private static final String APP_KEY = "801495189";
	//private static final String CLIENT_SECET = "ff66b84d1e37af0b630639de332ef996";
	//private static final String REDIRECT_URL = "http://localhost:8080/pms/tencent/tweibo.do";
	//调用API需要的公共部分
	private static final String COMMON_URL = "https://open.t.qq.com/api";
	//公有参数
	//private static final String COMMON_PARAM = "oauth_consumer_key=" + APP_KEY
			                                   //+ "&oauth_version=2.a&scope=all";
	
	//发送文字微博
	public String sendWeiBo(String content,String tencent_token,String openid,String openkey) throws Exception{
		
		String url = COMMON_URL + "/t/add";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		//私有参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", ApiUtils.getClientIP());
		//公有参数
		nvpMap.put("access_token",tencent_token);
		nvpMap.put("oauth_consumer_key",APP_KEY);
		nvpMap.put("openid",openid);
		nvpMap.put("openkey",openkey);
		nvpMap.put("oauth_version", "2.a");
		nvpMap.put("scope", "all");
		//发出请求,返回字符串结果
		String result = HttpClientUtils.httpPost(nvpMap, url, 5000, 5000);

		/** 将字符串转成JSON,获取需要的信息    **/
		JSONObject jsonObject = new JSONObject(result);
		int errcode = (Integer)jsonObject.get("errcode");
		int ret = (Integer)jsonObject.get("ret");
		
		//根据官方文档,当errcode=0&&ret=0即请求成功
		if(errcode == 0 && ret == 0){
			JSONObject data = jsonObject.getJSONObject("data");
			Long id = data.getLong("id");
			Integer time = data.getInt("time");
			String info = id.toString() + "," + time.toString();
			
			return info;
		}
		
		return "failure";
	}
}
