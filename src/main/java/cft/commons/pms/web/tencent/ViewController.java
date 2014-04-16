package cft.commons.pms.web.tencent;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    
	@RequestMapping("addWeiBoView.do")
	public String addWeiBo(){
		return "tencent/sendWeiBo";
	}
	
	
	@RequestMapping("addPicWeiBoView.do")
	public String addPicWeiBo(Model model) throws UnknownHostException{
		model.addAttribute("clientip", IPUtils.getClientIP());
		
		return "tencent/sendPicWeiBo";
	}
}
