package cft.commons.pms.web.tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cft.commons.pms.model.Record;
import cft.commons.pms.model.TaskType;
import cft.commons.pms.service.RecordService;
import cft.commons.pms.service.TaskTypeService;
import cft.commons.pms.web.BaseController;
import cft.commons.pms.web.tasks.dto.MyWeekLogDTO;

@Controller
public class ToDoTasksViewController extends BaseController {

	@Autowired
	private RecordService recordService;

	@Autowired
	private TaskTypeService taskTypeService;
	/**
	 * 填写日志界面
	 * 
	 * @return
	 */
	@RequestMapping("/fillLog")
	public String gotoToDoTasksView(Model model) {
		List<List<MyWeekLogDTO>> nowCalendar = initTodoCalendar();
		model.addAttribute("nowCalendar", nowCalendar);
		return "/tasks/fillLog";
	}

	@RequestMapping("/createRecord")
	public String gotoCreateRecordView(String date,Model model){
		List<TaskType> taskTypes = taskTypeService.getMainTaskTypes();
		model.addAttribute("taskTypes", taskTypes);
		model.addAttribute("date", date);
		return "/tasks/createRecord";
	}
	
	
	
	public List<List<MyWeekLogDTO>> initTodoCalendar() {
		List<List<MyWeekLogDTO>> nowCalendar = new ArrayList<List<MyWeekLogDTO>>();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		int start_day_of_week = 1; // 当月第一天是星期几
		int end_of_date = 31;// 当月最后一天
		Date startDate = null;
		Date endDate = null;
		// 获取开始星期数
		Calendar _dateTime = Calendar.getInstance();
		_dateTime.set(Calendar.DATE, 1);// 设为当前月的1号
		start_day_of_week = _dateTime.get(Calendar.DAY_OF_WEEK);// 获取当月第一天是星期几
		startDate = _dateTime.getTime();

		// 获取最后一天
		_dateTime.set(Calendar.MONTH, _dateTime.get(Calendar.MONTH) + 1);
		_dateTime.set(Calendar.DATE, 0);//
		end_of_date = _dateTime.get(Calendar.DATE);
		endDate = _dateTime.getTime();

		//获取最近一个月的日志
		List<Record> records = recordService.getLastMonthDailyRecord(
				simpleFormat.format(startDate), simpleFormat.format(endDate), getCurrentUserId());
		
		nowCalendar = setCurrentCalendar(start_day_of_week, end_of_date, records, simpleFormat);
		return nowCalendar;
	}
	
	private List<List<MyWeekLogDTO>> setCurrentCalendar(int weekDate,int ofDate,List<Record> records,SimpleDateFormat simpleFormat){
		List<List<MyWeekLogDTO>> result = new ArrayList<List<MyWeekLogDTO>>();
		int index = 1;
		int day = 1;
		Calendar calendar = Calendar.getInstance();
		int weekMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		int records_index = 0;
		for(int i=0;i<6;i++){
			List<MyWeekLogDTO> weeks = new ArrayList<MyWeekLogDTO>();
			for(int j=0;j<7;j++){
				calendar.set(Calendar.DATE, day);
				
				MyWeekLogDTO week = new MyWeekLogDTO();
				boolean hasValid = false;
				String _date = simpleFormat.format(calendar.getTime());
				
				
				
				//判断是否在当前月历中
				if(!(index<weekDate || day > ofDate)){
					hasValid = true;
					week.setNoWweekMonth((calendar.get(Calendar.WEEK_OF_MONTH) == weekMonth)?"T":"F");
				}
				if(hasValid){
					//搜有月历中的某天是否有写日志
					for (int k = records_index; k < records.size(); k++) {
						Record record = records.get(k);
						//匹配是否有填写的日子
						if (_date.contains(record.getToDay())) {
							week.getRecords().add(record);
							records_index++;
						}
						//同一个星期内
					}
					week.setDate(_date);
					day++;
				}
				index++;
				weeks.add(week);
			}
			System.out.println();
			result.add(weeks);
		}
		return result;
	}
	
}
