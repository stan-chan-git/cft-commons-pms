var actionLog = {
	titleMap: {
		logId:'Log ID',
		actionDate: 'Date',
		username: 'User',
		subjectType: 'Subject Type',
		subjectId: 'Subject ID',
		action: 'Action',
		description: 'Description',
		remark: 'Remark'
	},
	printLog : function(functName, data, logPanel, inputTitleMap) {
		
		var postlink = "/cms/ajax/" + functName;
		var logGridHtml = "<table class='logGrid' style='width: auto; height: auto'></table>";
		logPanel.empty();
		logPanel.append(logGridHtml);
		
		var w= $(logPanel).width();
		var newTitleMap = actionLog.titleMap;
		
		newTitleMap = inputTitleMap == null ? titleMap : inputTitleMap;
		
		
		var columnsArray = new Array();
		/* handle display log field in different column */
		if(logPanel.attr("id") == "logConsoleInUpdateNews"){
			columnsArray = [  
			 		        {field:'actionLogId',title:newTitleMap.logId,width : w * 0.1},  
					        {field:'actionDate',title:newTitleMap.actionDate,formatter : formatDate01,width : w * 0.15},  
					        {field:'action',title:newTitleMap.action,width : w * 0.15},
					        {field:'username',title:newTitleMap.username,width : w * 0.1},
					        {field:'actionDescription',title:newTitleMap.description,width : w * 0.45}
					        ];
		}else{
			columnsArray = [  
			 		        {field:'actionLogId',title:newTitleMap.logId,width : w * 0.1},  
					        {field:'actionDate',title:newTitleMap.actionDate,formatter : formatDate01,width : w * 0.15},  
					        {field:'subjectType',title:newTitleMap.subjectType,width : w * 0.1},  
					        {field:'subjectId',title:newTitleMap.subjectId,width : w * 0.1},  
					        {field:'action',title:newTitleMap.action,width : w * 0.15},
					        {field:'username',title:newTitleMap.username,width : w * 0.1},
					        {field:'actionDescription',title:newTitleMap.description,width : w * 0.25}
			 		        //{field:'remark',title:newTitleMap.remark,width : w * 0.1}
					        ];
			
		}
		/*-----------------------------------------------*/

		logPanel.find(".logGrid").datagrid({  
			singleSelect:true,collapsible:true,rownumbers:true,
			onBeforeLoad:startLoad,onLoadSuccess:stopLoad,
			url: postlink, 
			queryParams : data,
		    columns:[columnsArray]
		});  
		

	},
	reloadLog : function(logPanel){
		logPanel.find(".logGrid").datagrid("reload");  	
	}
	

}