package cft.commons.pms.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cft.commons.pms.model.Role;
import cft.commons.pms.model.User;
import cft.commons.pms.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String toMain() {
		return "/uams/main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "uams/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "uams/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "uams/login";
	}

	@RequestMapping(value = "/registered")
	public String registered() {

		return "uams/registered";
	}

	@RequestMapping(value = "/registeredLogin")
	public String registeredLogin(User user) {
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role("R1002"));
		user.setRoleList(roles);
		userService.createUser(user);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPlainPassword().toCharArray());
		subject.login(token);
		return "redirect:/";
	}
}
