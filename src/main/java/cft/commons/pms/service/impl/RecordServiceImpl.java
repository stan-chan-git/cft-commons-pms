package cft.commons.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cft.commons.pms.dao.RecordDAO;
import cft.commons.pms.model.Record;
import cft.commons.pms.service.RecordService;

@Slf4j
@Component("recordService")
public class RecordServiceImpl implements RecordService{
	private static final Logger log = LoggerFactory.getLogger(RecordServiceImpl.class);
	@Autowired
	private RecordDAO recordDAO;
	
	@Synchronized
	public String generatePK(String today) {
		today = today.replace("-", "");
		final String prefix = "T"+today;
		String newPK = null;
		//pk generation logic
		String lastId = recordDAO.getLastPK();
		
		if (StringUtils.isNotBlank(lastId) && lastId.contains(today)) {
			newPK = prefix + String.valueOf((Integer.parseInt(StringUtils.substring(lastId, 9)) + 1));
		} else {
			newPK = prefix + "1001";
		}
		return newPK;
	}
	
	@Override
	public List<Record> getLastMonthDailyRecord(String startDate, String endDate, String userId) {
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("userId", userId);
		return recordDAO.findLastMonthRecord(param);
	}

	@Override
	public void saveRecord(Record record) {
		String id = generatePK(record.getToDay());
		record.setId(id);
		recordDAO.createDailyRecord(record);
	}

}
