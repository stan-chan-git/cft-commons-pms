package cft.commons.pms.service;

import java.util.List;

import cft.commons.pms.model.Role;
import cft.commons.pms.plugins.Page;

/**
 * @author daniel
 *
 */
public interface RoleService {
	
	String generatePK();

	Role getRoleByRoleId(String roleId);

	List<Role> getRoleList(Role role);
	List<Role> getRoleList(Page<Role> page);
	void createRole(Role role);

	Integer updateRole(Role role);

	Integer disableRole(String roleId);

}
