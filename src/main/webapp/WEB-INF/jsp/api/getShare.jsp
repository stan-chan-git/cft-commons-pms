<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(function(){
	var wenzi = "#分享#" + $("#wenzi").text();
	var picArray = [];
	var url = "";
	
/*********************
 ********分享文字********
 *********************/	
   //腾讯分享文字
   $("#tencent_share_text").click(function(){
	   url = "http://10.211.55.3:8080/pms/getShare.do";
	   tencentShare(wenzi,picArray,url);
   });
   
   //新浪分享文字
   $("#sina_share_text").click(function(){
	   url = "http://10.211.55.3:8080/pms/getShare.do";
	   sinaShare(wenzi,picArray,url);
   });
   
   //Facebook分享文字
   $("#sina_share_text").click(function(){
	   url = "http://10.211.55.3:8080/pms/getShare.do";
	   facebookShare(wenzi,picArray,url);
   });
   
/*********************
 ********分享图片********
 *********************/
   //腾讯分享单张图片
   $("#tencent_share_onepic").click(function(){
	   url = "http://10.211.55.3:8080/pms/getShare.do";  
	   picArray.push($("#pic").attr("src"));
	   tencentShare("#分享图片#",picArray,url);
   });
   
   //新浪分享单张图片
   $("#sina_share_onepic").click(function(){
	   url = "http://10.211.55.3:8080/pms/getShare.do";  
	   picArray.push($("#pic").attr("src"));
	   sinaShare("#分享图片#",picArray,url);
   });
   
   //Facebook分享单张图片
   $("#sina_share_onepic").click(function(){
	  
   });
   
////////////////////////////////////////////////////////////////////   
   //腾讯分享多张图片
   $("#tencent_share_pics").click(function(){
	   url = "http://10.211.55.3:8080/pms/getShare.do";  
	   picArray.push($("#pic1").attr("src"));
	   picArray.push($("#pic2").attr("src"));
	   tencentShare("#分享图片#",picArray,url);
   });
   
   //新浪分享多张图片
   $("#sina_share_pics").click(function(){
	   url = "http://10.211.55.3:8080/pms/getShare.do";  
	   picArray.push($("#pic1").attr("src"));
	   picArray.push($("#pic2").attr("src"));
	   sinaShare("#分享图片#",picArray,url);
   });
   
   //Facebook分享多张图片
   $("#sina_share_pics").click(function(){
	  
   });
})
</script>
</head>
<body>
<!-- 分享文字 -->
<div style="width:800px">
    <label class="label label-info">文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;字:</label>
    <span id="wenzi" style="padding-left:10px">品味生活，完善人性。存在就是机会，思考才能提高。人需要不断打碎自己，更应该重新组装自己。</span>
    <br><br>
    <label>分享到:</label>
    <a href="#" id="tencent_share_text"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/tencent.jpg"></a>
    <a href="#" id="sina_share_text"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/sina.jpg"></a>
    <a href="#" id="facebook_share_text"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/facebook.jpg"></a>
</div>
<div style="border-bottom:1px solid #000;width:800px;margin-top:10px"></div>
<!-- 分享图片 -->
<!-- 单张图片 -->
<div style="width:800px;margin-top:50px">
    <label class="label label-info" style="margin-bottom:800px">单张图片:</label>
    <img id="pic" style="width:315px;height:210px" src="http://www.ttcnn.com/jpg/img/jpg10/201202183.jpg" />
    <br><br>
    <label>分享到:</label>
    <a href="#" id="tencent_share_onepic"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/tencent.jpg"></a>
    <a href="#" id="sina_share_onepic"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/sina.jpg"></a>
    <a href="#" id="facebook_share_onepic"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/facebook.jpg"></a>
</div>
<div style="border-bottom:1px solid #000;width:800px;margin-top:10px"></div>
<!-- 多张图片 -->
<div style="width:800px;margin-top:50px">
    <label class="label label-info">多张图片:</label>
    <img id="pic1" style="width:315px;height:210px" src="http://www.ttcnn.com/jpg/img/jpg10/201202183.jpg" />
    &nbsp;&nbsp;
    <img id="pic2" style="width:315px;height:210px" src="http://photocdn.sohu.com/20110814/Img316287479.jpg" />
    <br><br>
    <label>分享到:</label>
    <a href="#" id="tencent_share_pics"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/tencent.jpg"></a>
    <a href="#" id="sina_share_pics"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/sina.jpg"></a>
    <a href="#" id="facebook_share_pics"><img style="width:18px;height:19px" src="${pageContext.request.contextPath}/static/images/facebook.jpg"></a>
</div>
<div style="border-bottom:1px solid #000;width:800px;margin-top:10px"></div>
</body>
</html>