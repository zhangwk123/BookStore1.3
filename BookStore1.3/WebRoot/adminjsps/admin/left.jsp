<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
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
		background: #F7F7F7;
		margin-left: -13px;
		margin-top:20px;
		color: black/* #ffffff */;
		
	}
	a {
		text-decoration:none;
		color: gray/* #ffffff */;
		font-weight: 900;
		
	} 
	a:hover {
		text-decoration: underline;
		color: gray/* #ffffff */;
		font-weight: 900;
	}
	.div11 ul{
	   list-style-type:none;
	    
	}
	.div11 ul li a{
	   
	    font-size:12px;
	    margin-left:-30px;
	}
	
	.div11 ul li a:hover{
	   text-decoration:underline;
	   font-size:12px;
	   color:red;
	}
</style>
</head>
<body>  
  <div class="div1"><h5>分类管理</h5></div>
  <div class="div11">
  <ul>
  <li>
    <a href="<c:url value='/admin/AdminCategaryServlet?method=findAll'/>">查看全部分类</a>
  </li>
  <li>
    <a href="<c:url value='/adminjsps/admin/category/add.jsp'/>">添加分类</a>
  </li>
  </ul>
  </div>
  
  <div class="div1"><h5>图书管理</h5></div>
  <div class="div11">
  <ul>
  <li>
    <a href="<c:url value='/admin/AdminBookServlet?method=findAll'/>">查看全部图书</a>
  </li>
  <li>
    <a href="<c:url value='/admin/AdminBookServlet?method=addPre'/>">添加图书</a>
  </li>
  </ul>
  </div>
  
  <div class="div1"><h5>订单管理</h5></div>
  <div class="div11">
  <ul>
  <li>
    <a href="<c:url value='/admin/AdminOrderServlet?method=allOrders'/>">查看全部订单</a>
  </li>
  </ul>
  </div>
  
  <div class="div1"><h5>库存管理</h5></div>
  <div class="div11">
  <ul>
  <li>
    <a href="<c:url value='/admin/AdminBookServlet?method=lookKucun'/>">库存设置</a>
  </li>
  </ul>
  </div>
  
</body>
</html>
