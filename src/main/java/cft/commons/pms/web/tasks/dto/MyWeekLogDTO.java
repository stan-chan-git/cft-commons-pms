package cft.commons.pms.web.tasks.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import cft.commons.pms.model.Record;

@Data
public class MyWeekLogDTO {
	private List<Record> records = new ArrayList<Record>();//已有的日志信息
	private String date;
	private String noWweekMonth;//是否是同一周
	
}
