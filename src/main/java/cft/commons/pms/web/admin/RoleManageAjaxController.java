package cft.commons.pms.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.pms.model.Role;
import cft.commons.pms.plugins.Page;
import cft.commons.pms.plugins.PaginationObject;
import cft.commons.pms.service.RoleService;

@Controller
public class RoleManageAjaxController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/rolePageList")
	public @ResponseBody
	PaginationObject rolePageList(@RequestParam("rows") Integer pageSize,
			@RequestParam("page") Integer pageNo) {
		PaginationObject pno = new PaginationObject();
		Page<Role> page = new Page<Role>();
		Role role = new Role();
		role.setStatus("active");

		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.getParams().put("role", role);
		List<Role> roles = roleService.getRoleList(page);

		pno.setRows(roles);
		pno.setTotal(String.valueOf(page.getTotalRecord()));

		return pno;
	}
}
