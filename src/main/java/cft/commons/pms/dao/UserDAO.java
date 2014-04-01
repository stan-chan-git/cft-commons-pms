package cft.commons.pms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import cft.commons.pms.model.User;
import cft.commons.pms.plugins.Page;

public interface UserDAO {

	String getLastPK() throws DataAccessException;

	Integer isExistUser(String loginName) throws DataAccessException;

	User getUserByUserId(String userId) throws DataAccessException;

	User getUserByLoginName(String loginName) throws DataAccessException;

	List<User> getUserList(Page<User> page) throws DataAccessException;

	void insertUser(User user) throws DataAccessException;

	Integer updateUser(User user) throws DataAccessException;

	Integer changeUserPassword(User user) throws DataAccessException;

	Integer deleteUser(String userId) throws DataAccessException;

	void deleteUserRole(String userId) throws DataAccessException;

	void insertUserRole(Map<String, String> paramMap) throws DataAccessException;

}
