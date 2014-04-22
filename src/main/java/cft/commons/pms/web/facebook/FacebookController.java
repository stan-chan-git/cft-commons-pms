package cft.commons.pms.web.facebook;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cft.commons.core.util.HttpClientUtils;

@Controller
public class FacebookController {

	private static final String APP_KEY = "137410796429161";
	private static final String CLIENT_SECET = "1f943be78994b12b88680dfe331acec7";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/facebook/facebook.do";
    
/////////////////////////////////////////////////////////////
/////////////////////以下为*.do请求处理方法////////////////////////
/////////////////////////////////////////////////////////////
	/**
	 * facebook授权
	 */
	@RequestMapping(value = "facebook.do")
	public String facebook(String code, String openid, String openkey,HttpServletRequest request) throws Exception {
		//获取code，通过code发出请求获取access_token
		String url = "https://graph.facebook.com/oauth/access_token?client_id=" + APP_KEY
				     + "&client_secret=" + CLIENT_SECET + "&redirect_uri=" + REDIRECT_URL
				     + "&code=" + code;
			
		//发出请求，成功则返回带access_token的url字符串
		String result = HttpClientUtils.httpGet(url, 9000, 9000);
		String[] params = result.split("&");
		String facebook_token = "";
		//遍历切割后的字符串,获取access_token
		for (String param : params) {
			if (param.contains("access_token")) {
				facebook_token = param.split("=")[1];
				System.out.println("facebook_token:::::::::::::::" + facebook_token);
				break;
			}
		}
		//判断是否授权成功
		if(!facebook_token.equals("")){
			//调用API需要的部分公有参数
			request.getSession().setAttribute("facebook_token", facebook_token);
		}
		return "easyui/layout";
	}
	
	/**
	 * 用js发布消息
	 */
	@RequestMapping(value = "writeNewPost.do")
	public String writeNewPost(HttpServletRequest request){
		return "facebook/writeNewPost";
	}
	
}
