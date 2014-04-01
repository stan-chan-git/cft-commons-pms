package cft.commons.pms.service.impl;

import java.util.List;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cft.commons.pms.dao.RoleDAO;
import cft.commons.pms.model.Role;
import cft.commons.pms.plugins.Page;
import cft.commons.pms.service.RoleService;

/**
 * @author daniel
 *
 */
@Slf4j
@Component("roleService")
public class RoleServiceImpl implements RoleService {
 
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public Role getRoleByRoleId(String roleId) {
		return roleDAO.getRoleByRoleId(roleId);
	}

	@Override
	public List<Role> getRoleList(Role role) {
		return roleDAO.getRoleList(role);
	}

	@Synchronized
	@Override
	public String generatePK() {

		final String prefix = "R";
		String newPK = null;

		String lastId = roleDAO.getLastPK();
		if (StringUtils.isNotBlank(lastId)) {
			newPK = prefix + String.valueOf((Integer.parseInt(StringUtils.substring(lastId, 1)) + 1));
		} else {
			newPK = prefix + "1001";
		}

		return newPK;
	}

	@Override
	public void createRole(Role role) {
		role.setRoleId(generatePK());
		roleDAO.insertRole(role);
	}

	@Override
	public Integer updateRole(Role role) {
		return roleDAO.updateRole(role);
	}

	@Override
	public Integer disableRole(String roleId) {
		return roleDAO.disableRole(roleId);
	}

	@Override
	public List<Role> getRoleList(Page<Role> page) {
		// TODO Auto-generated method stub
		return roleDAO.getRolePage(page);
	}

}
