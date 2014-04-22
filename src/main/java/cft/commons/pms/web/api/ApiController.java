package cft.commons.pms.web.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.pms.service.api.SinaService;
import cft.commons.pms.service.api.TencentService;

@Controller
public class ApiController {
	
	private TencentService tencentService = new TencentService();
	private SinaService sinaService = new SinaService();
	private String tencent_info = "";
	private String sina_info = "";
	
	//发表一条新消息(即：发表一条微博)
	@RequestMapping(value="sendMessage.do")
	public String sendMessage(String content,HttpServletRequest request){
		//腾讯微博发布需要的参数
		String tencent_token = (String)request.getSession().getAttribute("tencent_token");
		String openid = (String)request.getSession().getAttribute("openid");
		String openkey = (String)request.getSession().getAttribute("openkey");
		
		//新浪微博发布需要的参数
		String sina_token = (String)request.getSession().getAttribute("sina_token");
		String status = content;
	
		try {
			//腾讯微博发布微博的API
			tencent_info = tencentService.sendWeiBo(tencent_token, content,openid ,openkey );
			//新浪微博发布微博的API
			sina_info = sinaService.SinaStatusesUpdate(sina_token, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*   判断发布到各个平台的新消息是否成功           */
		//腾讯微博
		if(tencent_info.equals("")){
			request.setAttribute("tencent_info", "腾讯微博发布失败!");
		}else{
			request.setAttribute("tencent_info", "腾讯微博发布成功!");
		}
		
		//新浪微博
		if(sina_info.equals("failure")){
			request.setAttribute("sina_info", "新浪微博发布失败！");
		}else{
			request.setAttribute("sina_info", "新浪微博发布成功！");
		}
		
	    
		return "api/result";
	}
}
