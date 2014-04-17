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
				
			</ul>
		</li>
		
		<li id="one" class="files"><a href="#one">Instagram API</a>
			
		<ul class="sub-menu">
				<li><a href="instagram/instagramApi.jsp"><em>01</em>发布照片<span>87</span></a></li>
				<li><a href="comment.do"><em>01</em>获取各个不同的社交平台上最新的回复<span>87</span></a></li>
				<li><a href="goComment.do"><em>01</em>创建评论<span>87</span></a></li>
				<li><a href="followFirend.do"><em>01</em>获取关注人的信息动态<span>87</span></a></li>
				<li><a href=""><em>01</em>分享<span>87</span></a></li>
				<li><a href="likeMedia.do"><em>01</em>分享一个like，并且获取这个like的在各个平台上的信息<span>87</span></a></li>
			</ul>	
			
		</li>
		
		<li id="one" class="files"><a href="#one">FaceBook API</a>
			
		</li>
	</ul>
</div>
