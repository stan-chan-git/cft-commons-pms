package cft.commons.pms.web.sweibo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SinaViewController {

	@RequestMapping(value ="sina_statuses_update")
	public String updateView() {
		return "sina/sina_statuses_update";	
	}
	
	@RequestMapping(value ="sina_statuses_upload")
	public String uploadView() {
		return "sina/sina_statuses_upload";	
	}
	
	@RequestMapping(value ="sina_statuses_repost")
	public String repostView() {
		return "sina/sina_statuses_repost";	
	}
	
	@RequestMapping(value ="sina_statuses_repost2")
	public String repost2View(String sina_id,Model model) {
		model.addAttribute("sina_id", sina_id);
		return "sina/sina_statuses_repost2";	
	}
	
	@RequestMapping(value ="sina_comments_tome")
	public String toMeView() {
		return "sina/sina_comments_tome";	
	}
	
}
