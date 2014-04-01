package cft.commons.pms.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RoleManageViewController {
	
	
	@RequestMapping("/roleManage")
	public String gotoRoleManageView(){
		
		return "/admin/roleManage";
	}
	
	@RequestMapping("/createRole")
	public String gotoCreateRoleView(){
		
		return "/admin/createRole";
	}
}
