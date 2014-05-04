<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
<title>User Login</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/dist/css/bootstrap-theme.min.css">
<script type="text/javascript" src="${ctx}/static/dist/js/bootstrap.min.js"></script>
<style type="text/css">
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
</head>
<body>
	<div class="container">
      <form class="form-signin" role="form"  action="${ctx}" method="post">
        <h2 class="form-signin-heading">PMS sign in</h2>
        <input type="text" name="username" class="form-control" placeholder="Account" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me" name="rememberMe"> Remember me
          <label style="margin-left: 10px;"><a href="${ctx}/uams/registered">注册</a></label>
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
    </div>
	<!-- /container -->
</body>
</html>
