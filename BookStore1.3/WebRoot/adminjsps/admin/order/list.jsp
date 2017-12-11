<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
        <!-- font-family:宋体; -->
        font-size:12px;
        border-color:rgb(78,78,78);
        /* width:100%; */
        text-align:center;
   }
   #th{
       background:rgb(78,78,78);
       color:white;
   }
</style>
 </head>
  
  <body>

    <table border="1" cellspacing="0">
        <tr id="th" bordercolor="rgb(78,78,78)">
           <th>订单号</th>
           <th>订单状态</th>
           <th>订单条目编号</th>
           <th>订单条目数量</th>
           <th>用户编号(点击显示用户名)</th>
           <th>操作</th>
        </tr>
<c:forEach items="${orderList }" var="o">   
        <tr bordercolor="rgb(78,78,78)">
               <td>${o.oid}</td>
               <td>${o.state}</td>
               <td>${o.orderItem.iid}</td>
               <td>${o.orderItem.count }</td>
               <td><a href="<c:url value="/admin/AdminUserServlet?method=findNameByUid&uid=${o.owner.uid}"/>">${o.owner.uid}</a></td>
               
           <td>
              <a onclick="return confirm('你真的要删除该订单及其订单下的条目吗?');" href="<c:url value='/admin/AdminOrderServlet?method=deleteAll&oid=${o.oid}'/>">删除订单</a>
           </td>
        </tr>
</c:forEach> 
    </table>
    <h3 style="text-align:center;">用户名:${user.username }</h3>
  </body>
</html>
