<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <h1>登录</h1>
<font color="red">${msg }</font>
              <form action="<c:url value='/UserServlet'/>" method="post" target="_top">
              <input type="hidden" name="method" value="login"/>
                  <table>
                    <tr>
                      <td align="center">账号：</td>
                      <td><input type="text" name="username"/></td>
                      <td>${errors.username }</td>
                    </tr>
                    <tr>
                      <td align="center">密码：</td>
                      <td><input type="password" name="password"/></td>
                      <td>${errors.password }</td>
                    </tr>
                   
                    <tr>
                      <td>&nbsp;</td>
                      <td align="left">
                        <input type="submit" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/jsps/user/regist.jsp'/>"><input type="button" value="注册"/></a>
                      </td>
                    </tr>																				
                 </table>
              </form>
  </body>
</html>
	