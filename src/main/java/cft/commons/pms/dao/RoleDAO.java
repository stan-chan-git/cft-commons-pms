package cft.commons.pms.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import cft.commons.pms.model.Role;
import cft.commons.pms.plugins.Page;

/**
 * @author daniel
 *
 */
public interface RoleDAO {
	
	String getLastPK() throws DataAccessException;

	Role getRoleByRoleId(String roleId) throws DataAccessException;
	
	List<Role> getRoleList(Role role) throws DataAccessException;
	List<Role> getRolePage(Page<Role> page) throws DataAccessException;
	void insertRole(Role role) throws DataAccessException;
	
	Integer updateRole(Role role) throws DataAccessException;
	
	Integer disableRole(String roleId) throws DataAccessException;

}
