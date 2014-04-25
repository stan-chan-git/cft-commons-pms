package cft.commons.pms.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.pms.web.api.util.ApiUtils;

@Controller
public class ViewController {

	@RequestMapping(value="sendMsgView.do")
	public String sendMsgView(Model model) throws Exception{
		model.addAttribute("clientip", ApiUtils.getClientIP());
		return "api/sendMessage";
	}
	
	
	@RequestMapping(value="sendPicMsgView.do")
	public String sendPicMsgView(){
		return "api/sendPicMessage";
	}
	
	
	@RequestMapping(value="getFriendsDynamicView.do")
	public String getFriendsDynamicView(){
		return "api/getFriendsDynamic";
	}
}
