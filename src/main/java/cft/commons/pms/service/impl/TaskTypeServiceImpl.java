package cft.commons.pms.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cft.commons.pms.dao.TaskTypeDAO;
import cft.commons.pms.model.TaskType;
import cft.commons.pms.service.TaskTypeService;


@Slf4j
@Component("taskTypeService")
public class TaskTypeServiceImpl implements TaskTypeService {

	@Autowired
	private TaskTypeDAO taskTypeDAO;
	
	@Override
	public List<TaskType> getMainTaskTypes() {
		return taskTypeDAO.getMainTaskTypes();
	}

	@Override
	public List<TaskType> getSuperTaskTypes(String taskId) {
		return taskTypeDAO.getSubTaskTypes(taskId);
	}


}
