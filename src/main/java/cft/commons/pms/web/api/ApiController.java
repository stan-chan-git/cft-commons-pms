package cft.commons.pms.web.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.pms.service.api.TencentService;

@Controller
public class ApiController {
	@Autowired
	private TencentService tencentService;
	private String tencent_info = "";
	
	//发表一条新消息(即：发表一条微博)
	@RequestMapping(value="sendMessage.do")
	public String sendMessage(String content,HttpServletRequest request){
		String access_token = (String)request.getSession().getAttribute("tencent_token");
		String openid = (String)request.getSession().getAttribute("openid");
		String openkey = (String)request.getSession().getAttribute("openkey");
System.out.println(content);		
		try {
			tencent_info = tencentService.sendWeiBo(access_token, content,openid ,openkey );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(tencent_info.equals("")){
			request.setAttribute("fail_info", "腾讯微博发布失败!");
		}
		
		return "api/result";
	}
}
