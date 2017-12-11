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
<script type="text/javascript">
   function changeColor(){
	   /* var aEle = document.getElementByClassName("a");
	   aEle.style.color="red"; */
	  // document.getElementById("a").style.color="red";
	   document.getElementById("a1_"+${categary.cname }).style.color="red";
   }
</script>
	
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
	.div ul{
	   list-style-type:none;
	    
	}
	.div ul li a{
	    font-size:15px;
	}
	
	.div ul li a:hover{
	   text-decoration:underline;
	   font-size:16px;
	   color:red;
	}
	
	.div ul li a:{
	   text-decoration:underline;
	   font-size:16px;
	   color:red;
	}
</style>
</head>
<body>  
  <div class="div">
<%--   <ul>
  <li>
    <a id="a" href="<c:url value='/BookServlet?method=findAll'/>" onclick="javascript:changeColor()">全部分类</a>
  </li>
  </ul> --%>
  <ul>
  <li>
    <a id="a" href="<c:url value='/BookServlet?method=findAllPageBean'/>" onclick="javascript:changeColor()">全部分类</a>
  </li>
  </ul>
  </div>
  
<c:forEach items="${categaryList }" var="categary">
  <div class="div">
  <ul>
  <li>
      <a id="a1_${categary.cname }" href="<c:url value='/BookServlet?method=findByCategary&cid=${categary.cid }&cname=${categary.cname }'/>" onclick="javascript:changeColor()">${categary.cname }</a>
  </li>
  </ul>
  </div>
</c:forEach>
</body>
</html>
