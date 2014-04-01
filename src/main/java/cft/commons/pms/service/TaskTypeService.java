package cft.commons.pms.service;

import java.util.List;

import cft.commons.pms.model.TaskType;

public interface TaskTypeService {
	
	
	List<TaskType> getMainTaskTypes();
	List<TaskType> getSuperTaskTypes(String taskId);
}
