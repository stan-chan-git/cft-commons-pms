package cft.commons.pms.web;

import org.apache.shiro.SecurityUtils;

import cft.commons.pms.shiro.realm.ShiroJdbcRealm.ShiroUser;


public class BaseController {
	
	
	protected ShiroUser getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	public String getCurrentUserId() {
		return getCurrentUser().userId;
	}

	public String getCurrentName() {
		return getCurrentUser().name;
	}

	public String getCurrentLoginName() {
		return getCurrentUser().loginName;
	}

	public boolean isApproval() {
		return ("T".equals(getCurrentUser().isApproval))?true:false;
	}
	
}
