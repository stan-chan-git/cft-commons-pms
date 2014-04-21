package cft.commons.pms.service.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import cft.commons.core.util.HttpClientUtils;
import cft.commons.pms.web.tencent.Utils;

@Service
public class TencentService {
	private static final String APP_KEY = "801495189";
	//调用API需要的公共部分
	private static final String COMMON_URL = "https://open.t.qq.com/api";
	//公有参数
	private static final String COMMON_PARAM = "oauth_consumer_key=" + APP_KEY
			                                   + "&oauth_version=2.a&scope=all";
	
	public String sendWeiBo(String access_token,String content,String openid,String openkey) throws Exception{
		
		String tencent_info = "";
        String url = COMMON_URL + "/t/add";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		//私有参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", Utils.getClientIP());
		//公有参数
		nvpMap.put("access_token",access_token);
		nvpMap.put("oauth_consumer_key",APP_KEY);
		nvpMap.put("openid",openid);
		nvpMap.put("openkey",openkey);
		nvpMap.put("oauth_version", "2.a");
		nvpMap.put("scope", "all");
		//发出请求,返回字符串结果
		String result = HttpClientUtils.httpPost(nvpMap, url, 5000, 5000);

        System.out.println(result);

		/** 将字符串转成JSON,获取需要的信息    **/
		JSONObject jsonObject = new JSONObject(result);
		int errcode = (Integer)jsonObject.get("errcode");
		int ret = (Integer)jsonObject.get("ret");

		//当errcode=0&ret=0,请求成功,即已成功发表新微博
		if(errcode == 0 && ret == 0){
			tencent_info = "success";
		}
		
		return tencent_info;
	}
}
