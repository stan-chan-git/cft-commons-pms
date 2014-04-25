package cft.commons.pms.web.api.tencent;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.pms.dto.tencent.WeiBoDTO;
import cft.commons.pms.web.api.util.ApiUtils;

@Controller
public class TencentViewController {
    
	@RequestMapping("addWeiBoView.do")
	public String addWeiBoView(Model model) throws Exception{
		model.addAttribute("clientip", ApiUtils.getClientIP());
		return "tencent/sendWeiBo";
	}
	
	
	@RequestMapping("addPicWeiBoView.do")
	public String addPicWeiBo(Model model) throws UnknownHostException{
		model.addAttribute("clientip", ApiUtils.getClientIP());
		return "tencent/sendPicWeiBo";
	}
	
	@RequestMapping(value="getView.do")
	public String getView(String state,HttpServletRequest request) throws Exception{
		//判断点击的是哪个菜单，comments为获取评论，forward为转发
		if(state.equals("comments")){
		     request.setAttribute("state", "comments");	
		}else if(state.equals("forward")){
			request.setAttribute("state", "forward");
		}
		
		List<WeiBoDTO> wbList = ApiUtils.getWeiBoList((String)request.getSession().getAttribute("tencent_token"),(String)request.getSession().getAttribute("openid"),(String)request.getSession().getAttribute("openkey"));
		
		request.setAttribute("wbList", wbList);
		
		return "tencent/userWeiBoList";
	}
	
	
	@RequestMapping(value="toForwardWeiBo.do")
	public String toForward(String reid,String orgitext,Model model){
		
		model.addAttribute("reid", reid);
		model.addAttribute("orgitext", orgitext);
		
		return "tencent/forwardWeiBo";
	}
}
