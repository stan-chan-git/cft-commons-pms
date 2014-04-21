package cft.commons.pms.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping(value="sendMsgView.do")
	public String sendMsgView(){
		
		return "api/sendMessage";
	}
}
