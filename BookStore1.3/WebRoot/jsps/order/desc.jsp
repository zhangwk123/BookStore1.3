<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
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
<style type="text/css">
    * {
          font-size: 11pt;
    }
    div{
          margin:20px;
          border:solid 2px gray;
          width:75px;
          height:75px;
          text-align:center;
    }
    li {
          margin:10px;
    }
    #pay{
          background:url(<c:url value='/images/all.png'/>) no-repat;
          display:inline-block;
          background-position:0 -412px;
          margin-left:30px;
          height:36px;
          width:146px;
    }
    #pay:hover{
          background:url(<c:url value='/images/all.png'/>) no-repat;
          display:inline-block;
          background-position:0 -448px;
          margin-left:30px;
          height:36px;
          width:146px;
    }
</style>
<body>
<h1>当前订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
    <tr bgcolor="gray" bordercolor="gray">
       <td colspan="6">
                            订单编号:${order.oid } 成交时间: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime }"/> 金额:<font color="red">${order.total}元</font>
       </td>
    </tr>
<c:forEach items="${order.orderItemList }" var="orderItem">
    <tr bordercolor="gray" align="center">
       <td width="15%">
           <div><img src="<c:url value='${orderItem.book.image }'/>" height="75"/></div>
       </td>
       <td>书名:${orderItem.book.bname }</td>
       <td>单价:${orderItem.book.price }</td>
       <td>作者:${orderItem.book.author }</td>
       <td>数量:${orderItem.count }</td>
       <td>小计:${orderItem.subtotal }元</td>
    </tr>
</c:forEach>
</table>
<br/>
<form method="post" action="javascript:alert('别点了,再点就去银行页面了!');" id="form" target="">
     收货地址:<input type="text" name="address" size="50" value="北京市海淀区">
     选择银行:<br/>
     <input type="radio" name="pd_FrpId" value="ICBC_NET_B2C" checked="checked"/>工商银行
     <img src="../../bank_img/icbc.bmp" align="middle"/>
     <input type="radio" name="pd_FrpId" value="ICBC_NET_B2C" />中国银行
     <img src="../../bank_img/icbc.bmp" align="middle"/>
     <input type="radio" name="pd_FrpId" value="ICBC_NET_B2C" />农业银行
     <img src="../../bank_img/icbc.bmp" align="middle"/>
     <input type="radio" name="pd_FrpId" value="ICBC_NET_B2C" />建设银行
     <img src="../../bank_img/icbc.bmp" align="middle"/>
     <input type="radio" name="pd_FrpId" value="ICBC_NET_B2C" />交通银行
     <img src="../../bank_img/icbc.bmp" align="middle"/>
</form>
<a id="pay" href="javascript:document.getElementById('form').submit();"></a>

<!-- !!!!!!!!!!!!target没写!!!!!!!!!!!!!!!! -->


</body>
</html>

