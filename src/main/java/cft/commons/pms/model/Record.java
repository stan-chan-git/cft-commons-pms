package cft.commons.pms.model;

import lombok.Data;

/**
 * 日志			
 * @author stan
 *
 */
@Data
public class Record {
	private String id;                  //id
	private String toDay;				//日志时间
	private String hours;				//工作的时间
	private String mainTask;			//任务大类
	private String taskType;			//任务类型
	private String taskContent;			//任务内容
	private String userId;				//创建人
	private String opDate;				//创建日志
	private String isExpired;			//是否可用
	private String isApproval;			//是否审批
	private String type;				//日志类型 1为日报，2为草稿
	
	public Record(){}
	public Record(String toDay){
		this.toDay = toDay;
	}
}
