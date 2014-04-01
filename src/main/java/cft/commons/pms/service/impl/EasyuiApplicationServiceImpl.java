package cft.commons.pms.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import cft.commons.pms.dao.EasyuiApplicationDAO;
import cft.commons.pms.model.EasyuiApplication;
import cft.commons.pms.plugins.Page;
import cft.commons.pms.service.EasyuiApplicationService;

@Slf4j
@Component("easyuiApplicationService")
public class EasyuiApplicationServiceImpl implements EasyuiApplicationService {

	@Autowired
	private EasyuiApplicationDAO easyuiApplicationDAO;
	
	@Override
	public List<EasyuiApplication> getAllEasyuiApplication(Page<EasyuiApplication> page)
			throws DataAccessException {
		return easyuiApplicationDAO.getAllEasyuiApplication(page);
	}

}
