
String.prototype.format = function(){  
  if(arguments.length==0) return this;  
  for(var s=this, i=0; i<arguments.length; i++)  
    s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);  
  return s;  
}; 
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