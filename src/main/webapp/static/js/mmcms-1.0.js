
function startLoad(){

	isBlock = true;
}

function stopLoad(){
	setTimeout(function(){	
		isBlock =false;
	   },1000);
}


function setmain(title,href){  

	   // $(".combo-panel").parent(".panel").remove(); //解决combobox在页面缓存的问题
	

		var centerURL = href;
		var centerTitle = title;
		$('body').layout('panel', 'center').panel({
			title : "Your Location: " + centerTitle,
			href : centerURL
		});
		$('body').layout('panel', 'center').panel('refresh');
	
	return false;
}  


function showLoadingMask(panel){
	var msg = "Loading...";
	$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: panel.width(), height: panel.height() }).appendTo(panel);
	$("<div class=\"datagrid-mask-msg\" style=\"font: 12px/18px Lucida Sans, sans-serif\"></div>").html(msg).appendTo(panel).css({ display: "block", left: (panel.width() - $("div.datagrid-mask-msg", panel).outerWidth()) / 2, top: (panel.height() - $("div.datagrid-mask-msg", panel).outerHeight()) / 2 });
}

function hideLoadingMask(panel){
	panel.find("div.datagrid-mask-msg").remove();
    panel.find("div.datagrid-mask").remove();
}




// show bottom right message
function showInfoMessage(title, message){
	$.messager.show({  
		title: title,  
        msg: '<div class="messager-icon messager-info"></div><div>' + message + "</div>",    
        showType:'slide',
        timeout: 5000
    }); 
}

function showWarningMessage(title, message){
	$.messager.show({  
		title: title,  
        msg: '<div class="messager-icon messager-warning"></div><div>' + message + "</div>",    
        showType:'slide',
        timeout: 5000
    }); 
}

function showErrorMessage(title, message){
	$.messager.show({  
		title: title,  
        msg: '<div class="messager-icon messager-error"></div><div>' + message + "</div>",    
        showType:'slide',
        timeout: 5000
    }); 
}

$.fn.initPage = function(options) {
	var defaults = {
		title : '',
		iconCls : '',
		toolbar : '',
		postUrl : '',
		loadMsg : '数据装载中......',
		sortName : '',
		sortOrder : ''
	};
	var _this = $(this);
	var options = $.extend(defaults,options);
	_this.datagrid({
		title : options.title,
		iconCls : options.iconCls,
		pageSize : 5,//默认选择的分页是每页5行数据
		pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar : options.toolbar,//在添加 增添、删除、修改操作的按钮要用到这个
		url : options.postUrl,//url调用Action方法
		loadMsg : options.loadMsg,
		singleSelect : true,//为true时只能选择单行
		fitColumns : true,//允许表格自动缩放，以适应父容器
		sortName : options.firstName,//当数据表格初始化时以哪一列来排序
		sortOrder : options.sortOrder,//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		pagination : true,//分页
		rownumbers : true
	//行数
	});
	var p = _this.datagrid('getPager');
	$(p).pagination({
		beforePageText : '第',//页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
	});
}