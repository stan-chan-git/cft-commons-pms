<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
  
  <script type="text/javascript">  
function postToWb(){  
var _t = encodeURI(document.title);
var _url = encodeURI(window.location);  
var _appkey = encodeURI("801495189"); 
var _pic =encodeURI("${share.url}");
var _site = 'www.baidu.com';
var _u = 'http://v.t.qq.com/share/share.php?title='+_t+'&url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic;  
window.open( _u,'转播到腾讯微博', 'width=700, height=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );  
}  


</script>   
  

</head>
<body>
    
    <table  border="1" style="width:10cm;height:10cm;">
         <tr align="center">
             <th>标题</th>
            <th>发布人</th>
			<th>类型</th>
			<th >图像</th>
			<th>分享到</th>
		</tr>
		<c:forEach var="share" items="${share }">
			<tr>
                <td>${share.title }</td>
                <td>${share.author_name}</td>
				<td>${share.type}</td>
				<td style="width:3cm;height:3cm;"><img alt="" src="${share.url}" width="50px" height="50px"></td>
				<td><a href="**">新浪</a><a href="javascript:void(0)" onclick="postToWb();" class="tmblog">腾讯微博</a>  <a href="**">Facebook</a></td>
			</tr>
		</c:forEach>
	</table>
    
    
</body>
</html>