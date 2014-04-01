package cft.commons.pms.web.tweibo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.core.util.HttpClientUtils;

@Controller
public class TencentWeiBoController {

	private static final String APP_KEY = "801489930";
	private static final String CLIENT_SECET = "fee24c6b127f6c3a09de5360a7a7c143";
	private static final String REDIRECT_URL = "http://localhost/easyui/tweibo.do";

	@RequestMapping(value = "tweibo.do")
	public @ResponseBody
	String tweibo(String code, String openid, String openkey) throws IOException {
		System.out.println("======================");
		System.out.println(code);
		System.out.println("======================");
		String url = "https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=" + APP_KEY
				+ "&client_secret=" + CLIENT_SECET + "&redirect_uri=" + REDIRECT_URL
				+ "&grant_type=authorization_code&code=" + code;
		String result = HttpClientUtils.httpGet(url, 9000, 9000);
		String[] params = result.split("&");
		String access_token = "";
		for (String param : params) {
			if (param.contains("access_token")) {
				access_token = param.split("=")[1];
				break;
			}
		}
		String commParam ="oauth_consumer_key=" +APP_KEY +
				"&access_token=" +access_token+
				"&openid=" +openid+
				"&openkey=" +openkey+
				"&oauth_version=2.a&scope=all";
		String fanslistUrl = "http://open.t.qq.com/api/friends/fanslist?"
				+ "format=json&reqnum=20&startindex=0&mode=0&install=0&sex=0&"
				+commParam;
		return HttpClientUtils.httpGet(fanslistUrl, 3000, 9000);
	}

	@RequestMapping(value = "access_token.do")
	public String access_token(HttpServletRequest request) {
		System.out.println("======================");
		System.out.println(request);
		System.out.println("======================");
		return null;
	}
}
