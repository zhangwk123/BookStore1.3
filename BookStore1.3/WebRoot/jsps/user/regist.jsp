<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
<h1>注册</h1>
<p style="color:red;font-weight:900">${msg }</p>
<form action="<c:url value='/UserServlet'/>" method="post">
<input type="hidden" name="method" value="regist">
    <table>
      <tr>
        <td align="center">账号：</td>
        <td><input type="text" name="username"/></td>
        <td style="color:red;font-weight:900">${errors.username }</td>
      </tr>
      <tr>
        <td align="center">密码：</td>
        <td><input type="password" name="password" /></td>
        <td style="color:red;font-weight:900">${errors.password }</td>
      </tr>
      <tr>
        <td></td>
        <td>
          <input type="submit" value="立即注册"/>&nbsp;&nbsp;&nbsp;
          <a href="<c:url value='/jsps/user/login.jsp'/>">已有账号,点此去登录</a>
        </td>
      </tr>
    </table>
</form>    
  </body>
</html>
