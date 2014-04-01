package cft.commons.pms.service;

import java.util.List;

import cft.commons.pms.model.User;
import cft.commons.pms.plugins.Page;

/**
 * @author daniel
 *
 */
public interface UserService {

	String generatePK();

	void createUser(User user);

	User getUserByUserId(String userid);

	User getUserByLoginName(String loginName);

	List<User> getUserList(Page<User> page);

	Integer updateUser(User user);

	Integer deleteUser(String userId);

	Integer changeUserPassword(User user);

	Boolean isAvaiableLoginName(String loginName);

}
