<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="bs-sidebar hidden-print affix col-md-3" role="complementary" style="border-left:1px;border-left-color:#E7E7E7">
	<ul class="accordion">
		<li id="one" class="files"><a href="#one">Demo<span>87</span></a>
			<ul class="sub-menu">
				<li><a href="${ctx}/easyui/application"><em>01</em>分页demo<span>87</span></a></li>
			</ul>
		</li>
		<shiro:hasPermission name="system:admin">
		<li id="one" class="files"><a href="#one">Admin</a>
			<ul class="sub-menu">
				<li><a href="${ctx}/view/userManage"><em>01</em>用户管理<span>87</span></a></li>
				<li><a href="${ctx}/view/roleManage"><em>02</em>权限管理<span>87</span></a></li>
			</ul>
		</li>
		</shiro:hasPermission>
		<li id="one" class="files"><a href="#one">User</a>
			<ul class="sub-menu">
				<li><a href="${ctx}/view/fillLog"><em>01</em>填写日志<span>87</span></a></li>
			</ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Authorization</a>
			<ul class="sub-menu">
				<li><a href="https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=801495189&response_type=code&redirect_uri=http://localhost:8088/pms/tencent/tweibo.do"><em>01</em>腾讯微博授权<span>87</span></a></li>
				<li><a href=""><em>01</em>新浪微博授权<span>87</span></a></li>
				<li><a href=""><em>01</em>Instagram授权<span>87</span></a></li>
				<li><a href=""><em>01</em>FaceBook授权<span>87</span></a></li>
			</ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Tencent API</a>
			
		</li>
		
		<li id="one" class="files"><a href="#one">Sina API</a>
			<ul class="sub-menu">
				
			</ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Instagram API</a>
			
		</li>
		
		<li id="one" class="files"><a href="#one">FaceBook API</a>
			
		</li>
	</ul>
</div>