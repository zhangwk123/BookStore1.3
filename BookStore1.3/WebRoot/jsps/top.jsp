<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #836FFF/* #15B69A */;
		margin: 0px;
		color: black/* #ffffff */;
	}
	a {
		text-decoration:none;
		color: blue/* #ffffff */;
		font-weight: 900;
	} 
	a:hover {
		text-decoration: underline;
		color: black/* #ffffff */;
		font-weight: 900;
	}
	.divlogin{
	    padding-right:50px;
	    text-align:right;
	}
	.divgouwuche{
	    padding-left:10px;
	    text-align:left;
	    color:red;
	}
	.divgouwuche a{
	    text-decoration:none;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;color:black;">图书商城</h1>
<div class="div" style="font-size: 10pt; line-height: 10px;">

<%-- 根据用户是否登录，显示不同的链接 --%>
		
<c:choose>
	<c:when test="${empty sessionScope.session_user }">
	     <div class="divlogin">
		  <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp; 
		  <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>
		  </div>	
	</c:when>
	
	
	<c:otherwise>
	    <div class="divgouwuche">
		      您好：${sessionScope.session_user.username }
		  <a href="<c:url value='/jsps/cart/list.jsp'/>" target="body">&nbsp;&nbsp;&nbsp;&nbsp;我的购物车</a>
		  <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">&nbsp;&nbsp;|&nbsp;&nbsp;我的订单</a>
		  <a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">&nbsp;&nbsp;|&nbsp;&nbsp;退出</a>
		</div>
	</c:otherwise>
</c:choose>
<br/>
        <div class="divlogin">
		  <form action="<c:url value='/BookServlet?method=searchByName'/>" method="post" target="body">
		     <input type="text"  name="bname" value="输入图书名" />
		     <input type="submit" value="搜索">
		  </form>
		</div>



</div>
  </body>
</html>
