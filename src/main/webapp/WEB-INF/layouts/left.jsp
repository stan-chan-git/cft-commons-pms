<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="bs-sidebar hidden-print affix col-md-3" role="complementary" style="border-left:1px;border-left-color:#E7E7E7">
	<ul class="accordion">
		<li id="one" class="files"><a href="#one">Tencent API</a>
		    <ul class="sub-menu">
		        <li><a href="${ctx}/view/addWeiBoView.do">发送一条微博消息</a></li>
		    </ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Sina API</a>
			<ul class="sub-menu">
				<li><a href="${ctx}/sina/sina_statuses_update"><em>01</em>发布一条微博信息</a></li>						
				<li><a href="${ctx}/sina/sina_statuses_upload"><em>02</em>上传图片并发布一条微博</a></li>
				<li><a href="#statuses/repost"><em>04</em>转发一条微博信息</a></li>				
			</ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Instagram API</a>
			
		</li>
		
		<li id="one" class="files"><a href="#one">FaceBook API</a>
			
		</li>
	</ul>
</div>