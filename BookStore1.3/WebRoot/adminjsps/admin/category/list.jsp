<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分类列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
/*    body{
        background:rgb(254,238,189);
   } */
   table{
        font-family:宋体;
        font-size:11pt;
        border-color:rgb(78,78,78);
        width:60%;
   }
   #th{
       background:rgb(78,78,78);
       color:white;
   }
</style>
 </head>
  
  <body>
    <h2 style="text-align:center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
        <tr id="th" bordercolor="rgb(78,78,78)">
           <th>分类名称</th>
           <th>操作</th>
        </tr>
<c:forEach items="${categaryList }" var="c">   
        <tr bordercolor="rgb(78,78,78)">
           <td>${c.cname }</td>
           <td>
              <a href="<c:url value='/admin/AdminCategaryServlet?method=load&cid=${c.cid }'/>">修改分类</a>&nbsp;&nbsp;&nbsp;&nbsp;|
              <a onclick="return confirm('你真的要删除该分类吗!');" href="<c:url value='/admin/AdminCategaryServlet?method=delete&cid=${c.cid}'/>">删除分类</a>&nbsp;&nbsp;&nbsp;&nbsp;|
              <a onclick="return confirm('你真的要删除该分类及其分类下的图书吗!');" href="<c:url value='/admin/AdminCategaryServlet?method=deleteAll&cid=${c.cid}'/>">删除分类及图书</a>
           </td>
        </tr>
</c:forEach> 
    </table>
  </body>
</html>
