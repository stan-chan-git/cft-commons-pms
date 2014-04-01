package cft.commons.pms.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import cft.commons.pms.model.TaskType;

public interface TaskTypeDAO {
	
	
	public List<TaskType> getMainTaskTypes()throws DataAccessException;
	public List<TaskType> getSubTaskTypes(String taskId)throws DataAccessException;
}
