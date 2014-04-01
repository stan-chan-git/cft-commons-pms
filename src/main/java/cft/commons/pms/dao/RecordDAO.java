package cft.commons.pms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import cft.commons.pms.model.Record;

public interface RecordDAO {
	
	String getLastPK() throws DataAccessException;
	/**
	 * 最近一个月的日报
	 * @return
	 * @throws DataAccessException
	 */
	public List<Record> findLastMonthRecord(Map<String, String> paramMap)throws DataAccessException;
	
	/**
	 * 插入一条日报
	 * @param record
	 * @throws DataAccessException
	 */
	public void createDailyRecord(Record record)throws DataAccessException;
}
