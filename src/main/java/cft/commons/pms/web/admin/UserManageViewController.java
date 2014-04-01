package cft.commons.pms.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.pms.model.Role;
import cft.commons.pms.service.RoleService;


@Controller
public class UserManageViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserManageViewController.class);
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping(value="/userManage")
	public String gotoUserManageView(){
		logger.info("gotoUserManageView");
		return "/admin/userManage";
	}
	
	@RequestMapping(value="/createUser")
	public String gotoCreateUserView(Model model,HttpServletRequest request){
		logger.info("createUser");
		Role role = new Role();
		role.setStatus("active");
		List<Role> roleList = roleService.getRoleList(role);
		model.addAttribute("roleList", roleList);
		return "/admin/createUser";
	}
}
