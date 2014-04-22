package cft.commons.pms.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.pms.web.tencent.Utils;

@Controller
public class ViewController {

	@RequestMapping(value="sendMsgView.do")
	public String sendMsgView(Model model) throws Exception{
		model.addAttribute("clientip", Utils.getClientIP());
		return "api/sendMessage";
	}
	
	
	@RequestMapping(value="sendPicMsgView.do")
	public String sendPicMsgView(Model model){
		return "api/sendPicMessage";
	}
}
