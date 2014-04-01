package cft.commons.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cft.commons.core.constant.Constants;
import cft.commons.core.helper.encrypt.EncryptUtils;
import cft.commons.core.util.EncodeUtils;
import cft.commons.pms.dao.UserDAO;
import cft.commons.pms.model.Role;
import cft.commons.pms.model.User;
import cft.commons.pms.plugins.Page;
import cft.commons.pms.service.UserService;

@Slf4j
@Component("userService")
public class UserServiceImpl implements UserService {

	private static final int SALT_SIZE = 8;
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDAO userDAO;

	@Override
	@Synchronized
	public String generatePK() {

		final String prefix = "U";
		String newPK = null;
		//pk generation logic
		String lastId = userDAO.getLastPK();
		if (StringUtils.isNotBlank(lastId)) {
			newPK = prefix + String.valueOf((Integer.parseInt(StringUtils.substring(lastId, 1)) + 1));
		} else {
			newPK = prefix + "1001";
		}
		return newPK;
	}

	@Override
	public List<User> getUserList(Page<User> page) {
		return userDAO.getUserList(page);
	}

	@Override
	public void createUser(User user) {

		if (StringUtils.isNotBlank(user.getPlainPassword())) {

			byte[] salt = EncryptUtils.generateSalt(SALT_SIZE);

			user.setSalt(EncodeUtils.encodeHex(salt));
			user.setPassword(EncryptUtils.entryptPassword(user.getPlainPassword(), salt));

		}

		user.setUserId(generatePK());
		userDAO.insertUser(user);

		log.info(Constants.SVC_LOG + "UserServiceImpl:createUser:user = " + user);

		if (CollectionUtils.isNotEmpty(user.getRoleList())) {
			for (Role role : user.getRoleList()) {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("userId", user.getUserId());
				paramMap.put("roleId", role.getRoleId());

				userDAO.insertUserRole(paramMap);
			}
		}
	}

	@Override
	public User getUserByLoginName(String loginName) {
		User user = userDAO.getUserByLoginName(loginName);

		if (log.isDebugEnabled()) {
			log.debug(Constants.SVC_LOG + "getUserByLoginName:User: " + user);
		}

		return user;
	}

	@Override
	public User getUserByUserId(String userId) {
		return userDAO.getUserByUserId(userId);
	}

	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	@Override
	public Integer updateUser(User user) {

		log.info(Constants.SVC_LOG + "UserServiceImpl:updateUser:user = " + user);

		userDAO.deleteUserRole(user.getUserId());

		for (Role role : user.getRoleList()) {

			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("roleId", role.getRoleId());

			userDAO.insertUserRole(paramMap);
		}

		return userDAO.updateUser(user);
	}

	@Override
	public Integer deleteUser(String userId) {

		log.info(Constants.SVC_LOG + "UserServiceImpl:deleteUser:userId = " + userId);

		userDAO.deleteUserRole(userId);
		return userDAO.deleteUser(userId);
	}

	@Override
	public Integer changeUserPassword(User user) {

		if (StringUtils.isNotBlank(user.getPlainPassword())) {

			byte[] salt = EncryptUtils.generateSalt(SALT_SIZE);

			user.setSalt(EncodeUtils.encodeHex(salt));
			user.setPassword(EncryptUtils.entryptPassword(user.getPlainPassword(), salt));

		}
		return userDAO.changeUserPassword(user);
	}

	@Override
	public Boolean isAvaiableLoginName(String loginName) {
		boolean isExist = false;
		Integer i = userDAO.isExistUser(loginName);

		if (i.intValue() == 1) {
			isExist = true;
		}

		log.info(Constants.SVC_LOG + "UserService:isAvaiableLoginName = " + !isExist);
		return !isExist;
	}

}
