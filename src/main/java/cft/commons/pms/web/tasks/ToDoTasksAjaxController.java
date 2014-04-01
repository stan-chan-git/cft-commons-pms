package cft.commons.pms.web.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cft.commons.pms.model.Record;
import cft.commons.pms.model.TaskType;
import cft.commons.pms.service.RecordService;
import cft.commons.pms.service.TaskTypeService;
import cft.commons.pms.web.BaseController;

@Controller
public class ToDoTasksAjaxController extends BaseController {

	@Autowired
	private RecordService recordService;

	@Autowired
	private TaskTypeService taskTypeService;

	@RequestMapping("saveRecord")
	public @ResponseBody
	String gotoCreateRecordView(Record record){
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		record.setUserId(getCurrentUserId());
		record.setOpDate(simpleFormat.format(new Date()));
		try {
			recordService.saveRecord(record);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "ok";
	}
	@RequestMapping("getTaskTypes")
	public @ResponseBody List<TaskType> getTaskTypes(String id){
		List<TaskType> taskTypes =taskTypeService.getSuperTaskTypes(id);
		
		return taskTypes;
	}
	
}
