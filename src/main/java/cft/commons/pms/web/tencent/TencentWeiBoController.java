package cft.commons.pms.web.tencent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.core.util.HttpClientUtils;

@Controller
public class TencentWeiBoController {

	private static final String APP_KEY = "801495189";
	private static final String CLIENT_SECET = "ff66b84d1e37af0b630639de332ef996";
	private static final String REDIRECT_URL = "http://localhost:8088/pms/tencent/tweibo.do";
	//调用API需要的部分

	/* 回调地址  */
	@RequestMapping(value = "tweibo.do")
	public String tweibo(String code, String openid, String openkey,HttpServletRequest request) throws IOException {
		//获取code，通过code发出请求获取access_token
		String url = "https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=" + APP_KEY
				+ "&client_secret=" + CLIENT_SECET + "&redirect_uri=" + REDIRECT_URL
				+ "&grant_type=authorization_code&code=" + code;
		
		//发出请求，成功则返回带access_token的url字符串
		String result = HttpClientUtils.httpGet(url, 9000, 9000);
		String[] params = result.split("&");
		String tencent_token = "";
		
		//遍历切割后的字符串,获取access_token
		for (String param : params) {
			if (param.contains("access_token")) {
				tencent_token = param.split("=")[1];
				break;
			}
		}
		
		//判断是否授权成功
		if(!tencent_token.equals("")){
			//调用API需要的部分公有参数
			request.getSession().setAttribute("tencent_token", tencent_token);
		    request.getSession().setAttribute("openid", openid);
			request.getSession().setAttribute("openkey", openkey);
			//在页面使用，提示授权成功
			request.setAttribute("tencent_info", "success");
		}else{
			request.setAttribute("tencent_info", "failure");
		}
		
		return "easyui/layout";
	}
    
    /* 发送一条微博消息   */	
	@RequestMapping(value="sendWeiBo.do")
	public String sendWeiBo(String content,HttpServletRequest request) throws IOException{
		String clientip = "";
		//获取客户端ip
		try {
			InetAddress address = InetAddress.getLocalHost();
			clientip = address.getHostAddress(); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		String url = "https://open.t.qq.com/api/t/add";
		
		Map<String,String> nvpMap = new HashMap<String,String>();
		//私有参数
		nvpMap.put("format", "json");
		nvpMap.put("content", content);
		nvpMap.put("clientip", clientip);
		//公有参数
		nvpMap.put("access_token",(String)request.getSession().getAttribute("tencent_token"));
		nvpMap.put("oauth_consumer_key",APP_KEY);
		nvpMap.put("openid",(String)request.getSession().getAttribute("openid"));
		nvpMap.put("openkey",(String)request.getSession().getAttribute("openkey"));
		nvpMap.put("oauth_version", "2.a");
		nvpMap.put("scope", "all");
		//发出请求,返回字符串结果
		String result = HttpClientUtils.httpPost(nvpMap, url, 5000, 5000);
System.out.println(result);
		/** 将字符串转成JSON,获取需要的信息    **/
		JSONObject jsonObject = new JSONObject(result);
		//String errorcode = jsonObject.getString("errorcode");
		//String msg = jsonObject.getString("msg");
		
		return "tencent/result";
	}
}
