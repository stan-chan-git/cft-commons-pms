package cft.commons.pms.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import cft.commons.pms.model.EasyuiApplication;
import cft.commons.pms.plugins.Page;

public interface EasyuiApplicationService {
	public List<EasyuiApplication> getAllEasyuiApplication(Page<EasyuiApplication> page)throws DataAccessException;
}
