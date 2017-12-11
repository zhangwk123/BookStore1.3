<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>库存详细</title>
    
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
   function setMethod(method){
	   var ele = document.getElementById("method");
	   ele.value = method;
   }
</script>
<style type="text/css">
   body{
       font-size: 10pt;
   }
   div {
        margin:20px;
        border: /* solid 2px gray */;
        width:200px;
        height:200px;

   }
   li{
        margin:10px;
   }
</style>
  </head>
  
  <body>
  <div>
    <img src="<c:url value='/${book.image }'/>" border="0"/>
  </div>
  <form action="<c:url value='/admin/AdminBookServlet'/>" method="post">
  <input type="hidden" name="method" value="" id="method"/>
  <input type="hidden" name="bid" value="${book.bid }"/>
     书名：${book.bname }<br/>
     单价：${book.price }元<br/>
     作者：${book.author }<br/>
     数量：${book.kucun }<br/>
     设置库存数量：<input type="text" name="kucun" /><br/>
     <input type="submit" value="确认设置" onclick="setMethod('addKucun')"/>&nbsp;&nbsp;
  </form>
  </body>
</html>
