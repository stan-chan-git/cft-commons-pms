			$.extend($.fn.validatebox.defaults.rules, {
		    minLength : { // 判斷最小長度
		        validator : function(value, param) {
		            value = $.trim(value); //去空格
		            return value.length >= param[0];
		        },
		        message : '最少輸入 {0} 個字元。'
		    },
		   /* length:{validator:function(value,param){
		        var len=$.trim(value).length;
		            return len>=param[0]&&len<=param[1];
		        },
		            message:"輸入內容長度必須介於{0}和{1}之間"
		        },*/
		    intOrFloat : {// 驗證整數或小數
		        validator : function(value) {
		            return /^\d+(\.\d+)?$/i.test(value);
		        },
		        message : '請輸入數位，並確保格式正確'
		    },
		    integer : {// 驗證整數
		        validator : function(value) {
		            return /^[+]?[1-9]+\d*$/i.test(value);
		        },
		        message : '請輸入整數'
		    },
		    chinese : {// 驗證中文
		        validator : function(value) {
		            return /^[\u0391-\uFFE5]+$/i.test(value);
		        },
		        message : '請輸入中文'
		    },
		    english : {// 驗證英語
		        validator : function(value) {
		            return /^[A-Za-z]+$/i.test(value);
		        },
		        message : '請輸入英文'
		    },
		    unnormal : {// 驗證是否包含空格和非法字元
		        validator : function(value) {
		            return /.+/i.test(value);
		        },
		        message : '輸入值不能爲空和包含其他非法字元'
		    },
		    username : {// 驗證用戶名
		        validator : function(value) {
		            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
		        },
		        message : '用戶名不合法（字母開頭，允許6-16位元組，允許字母數位元下劃線）'
		    },
		    name : {// 驗證姓名，可以是中文或英文
		            validator : function(value) {
		                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
		            },
		            message : '請輸入姓名'
		    },
		    email:{
		        validator : function(value){
		        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
		    },
		    message : '請輸入有效的電子郵件賬號(例：abc@123.com)'   
		    },
		    equalTo: {
		        validator:function(value,param){
		            return $(param[0]).val() == value;
		        },
		        message:'兩次輸入不匹配'
		    },
		    /*multiple : {
				validator : function(value, vtypes) {
					var returnFlag = true;
					var opts = $.fn.validatebox.defaults;
					for (var i = 0; i < vtypes.length; i++) {
						var methodinfo = /([a-zA-Z_]+)(.*)/.exec(vtypes[i]);
						var rule = opts.rules[methodinfo[1]];
						if (value && rule) {
							var parame = eval(methodinfo[2]);
							if (!rule["validator"](value, parame)) {
								returnFlag = false;
								this.message = rule.message;
								break;
							}
						}
					}
					return returnFlag;
				}
			},
			isAvaiableUser : {
				validator : function(value,lengthParam) {
					var rules = $.fn.validatebox.defaults.rules;
					if (rules.length.validator(value, lengthParam)) {
						this.message = $.fn.validatebox.defaults.rules.remote.message;
						var postlink = "../ajax/isAvaiableUser";
						var param = new Array();
						param[0] = postlink;
						param[1] = "loginName";
						if (!rules.remote.validator(value, param)) {
							return false;
						} else {
							return true;
						}
					}else{	
						return true;
					}
				},
				message : $.fn.validatebox.defaults.rules.remote.message 
			},
			*/
			/*
			 * override length validator for multi validtype
			 * http://www.easyui.info/archives/602.html
			 * 
			 * */
			length : {
				validator : function(value, param) {
					this.message = $.fn.validatebox.defaults.rules.length.message;
					var len = $.trim(value).length;
					if (param) {
						for (var i = 0; i < param.length; i++) {
							this.message = this.message.replace(new RegExp(
											"\\{" + i + "\\}", "g"), param[i]);
						}
					}
					return len >= param[0] && len <= param[1];
				},
				message : $.fn.validatebox.defaults.rules.length.message 
			}
		}); 

