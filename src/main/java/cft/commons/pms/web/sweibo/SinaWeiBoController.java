package cft.commons.pms.web.sweibo;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.core.util.HttpClientUtils;

@Controller
public class SinaWeiBoController {
	
	private static final String APP_KEY = "4281626272";
	private static final String CLIENT_SECET = "69eade123bb72a88abadcdde95e8e6ae";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/sina/sinaweibo.do";
	
	@RequestMapping(value = "sinaweibo.do")
	public String GetOauth2Access_token(String code,Model model) throws IOException {
		
		System.out.println("===========code===========");
		System.out.println(code);
		System.out.println("===========code===========");
		
		//通过回调地址获取code，再去访问新浪微博获取access_token
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

		String access_token = "";//从result中获取access_token的值
		if (result.contains("access_token")) {
			access_token = result.split(",")[0];
			access_token = access_token.substring(17, access_token.length() - 1);
		}	
		System.out.println("===========access_token===========");
		System.out.println(access_token);
		System.out.println("===========access_token===========");
		
		String uid = "";//从result中获取uid的值
		if (result.contains("uid")) {
			uid = result.split(",")[3];
			uid = uid.substring(7, uid.length() - 2);
		}
		System.out.println("===========uid===========");
		System.out.println(uid);
		System.out.println("===========uid===========");
		
		//返回access_token和uid到jsp页面
		model.addAttribute("access_token", access_token);
		model.addAttribute("uid", uid);
		return "sina/sinafunction";	
	}
		
}
