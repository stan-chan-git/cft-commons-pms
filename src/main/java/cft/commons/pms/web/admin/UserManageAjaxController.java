package cft.commons.pms.web.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.pms.model.Role;
import cft.commons.pms.model.User;
import cft.commons.pms.plugins.Page;
import cft.commons.pms.plugins.PaginationObject;
import cft.commons.pms.service.UserService;

@Controller
public class UserManageAjaxController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userPageList")
	public @ResponseBody
	PaginationObject userPageList(User user, @RequestParam("rows") Integer pageSize,
			@RequestParam("page") Integer pageNo) {
		PaginationObject pno = new PaginationObject();
		Page<User> page = new Page<User>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.getParams().put("user", user);
		List<User> userList = userService.getUserList(page);
		pno.setRows(userList);
		pno.setTotal(String.valueOf(page.getTotalRecord()));
		return pno;
	}

	@RequestMapping(value = "saveUser")
	public @ResponseBody
	String saveUser(User user,@RequestParam(value="roleId",required=true)String[] roleIds) {
		List<Role> roles = new ArrayList<Role>();
		for (String roleId : roleIds) {
			roles.add(new Role(roleId));
		}
		user.setRoleList(roles);
		userService.createUser(user);
		return "ok";
	}
}
