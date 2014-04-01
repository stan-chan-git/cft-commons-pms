package cft.commons.pms.service;

import java.util.List;

import cft.commons.pms.model.Record;

public interface RecordService {
	
	
	public void saveRecord(Record record);
	
	public List<Record> getLastMonthDailyRecord(String startDate,String endDate,String userId);
}
