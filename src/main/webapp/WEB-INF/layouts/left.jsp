<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="bs-sidebar hidden-print affix col-md-3" role="complementary" style="border-left:1px;border-left-color:#E7E7E7">
	<ul class="accordion">
		<li id="one" class="files"><a href="#one">Tencent API</a>
		    <ul class="sub-menu">
		        <li><a href="${ctx}/view/addWeiBoView.do">发送一条微博消息</a></li>
		        <li><a href="${ctx}/tencent/getReview.do">获取单条微博的评论列表</a>
		        <li><a href="${ctx}/tencent/forwardWeiBo.do">转发一条微博</a>
		        <li><a href="">获取关注的人的最新微博</a>
		    </ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Sina API</a>
			<ul class="sub-menu">
				<li><a href="${ctx}/view/sina_statuses_update"><em>01.1</em>发布一条微博信息</a></li>						
				<li><a href="${ctx}/view/sina_statuses_upload"><em>01.2</em>上传图片并发布一条微博</a></li>
				<li><a href="${ctx}/sina/sinaCommentsToMe.do"><em>02</em>获取评论列表</a></li>
				<li><a href="${ctx}/view/sina_statuses_repost"><em>04.1</em>转发一条微博信息</a></li>
				<li><a href="${ctx}/sina/sinaStatuseFriends.do"><em>04.2</em>转发某条微博信息</a></li>			
				<li><a href="${ctx}/sina/sinaStatuseFriends.do"><em>05</em>获取关注人的信息动态</a></li>
				<li><a href="${ctx}/sina/sinaStatusesUpload.do"><em>06</em>点击转发图片</a></li>		
			</ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Instagram API</a>
			
		</li>
		
		<li id="one" class="files"><a href="#one">FaceBook API</a>
			
		</li>
	</ul>
</div>
