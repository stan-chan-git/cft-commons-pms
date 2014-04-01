package cft.commons.pms.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import cft.commons.pms.model.StaffGroup;

public interface StaffGroupDAO {
	
	
	
	/**
	 * 获取组员
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<StaffGroup> getStaffGroups(String id)throws DataAccessException;
}
