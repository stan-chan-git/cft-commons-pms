package cft.commons.pms.web.tencent;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.core.util.HttpClientUtils;

@Controller
public class TencentWeiBoController {

	private static final String APP_KEY = "801489930";
	private static final String CLIENT_SECET = "fee24c6b127f6c3a09de5360a7a7c143";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/tencent/tweibo.do";

	@RequestMapping(value = "tweibo.do")
	public String tweibo(String code, String openid, String openkey,Model model) throws IOException {
		//获取code，通过code发出请求获取access_token
		String url = "https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=" + APP_KEY
				+ "&client_secret=" + CLIENT_SECET + "&redirect_uri=" + REDIRECT_URL
				+ "&grant_type=authorization_code&code=" + code;
		
		//发出请求，成功则返回带access_token的url字符串
		String result = HttpClientUtils.httpGet(url, 9000, 9000);
		String[] params = result.split("&");
		String access_token = "";
		
		//遍历切割后的字符串,获取access_token
		for (String param : params) {
			if (param.contains("access_token")) {
				access_token = param.split("=")[1];
				break;
			}
		}
		
		
		//调用API需要的公有参数
		model.addAttribute("access_token",access_token);
		model.addAttribute("oauth_consumer_key",APP_KEY);
		model.addAttribute("access_token", access_token);
		model.addAttribute("openid", openid);
		model.addAttribute("openkey", openkey);
		model.addAttribute("oauth_version", "2.a");
		model.addAttribute("scope", "all");
		//在页面使用，提示授权成功
		model.addAttribute("success", "腾讯微博授权成功");
		
		return "common";
	}
    
	
}
