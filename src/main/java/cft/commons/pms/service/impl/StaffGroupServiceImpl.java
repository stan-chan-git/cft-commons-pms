package cft.commons.pms.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cft.commons.pms.dao.StaffGroupDAO;
import cft.commons.pms.model.StaffGroup;
import cft.commons.pms.service.StaffGroupService;


@Slf4j
@Component("staffGroupService")
public class StaffGroupServiceImpl implements StaffGroupService{

	@Autowired
	private StaffGroupDAO staffGroupDAO;
	
	@Override
	public List<StaffGroup> getStaffGroupList(String leardarId) {
		// TODO Auto-generated method stub
		return staffGroupDAO.getStaffGroups(leardarId);
	}

}
