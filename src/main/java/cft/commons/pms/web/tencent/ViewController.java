package cft.commons.pms.web.tencent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    
	@RequestMapping("addWeiBoView.do")
	public String addWeiBo(){
		return "tencent/sendWeiBo";
	}
}
