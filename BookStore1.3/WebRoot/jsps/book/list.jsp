<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
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
         font-size: 10pt;
     }
    .icon{
         margin: 10px;
         border: ;/* solid 2px gray */
         width: 160px;
         height: 200px;/* 180 */
         text-align: center;
         float: left;
     }
     .fenye{
         margin: 10px;
         border: ;/* solid 2px gray */
         width: 900px;
         height: 10px;/* 180 */
         text-align: center;
         float: right;
     }
</style>
  </head>
  <body>

 
<c:forEach items="${bookListAll }" var="bookall" varStatus="bookallstat">
<c:if test="${bookallstat.first }"><h5>全部分类>></h5></c:if>   <!-- 判断是否为第一个,分类显示在第一个div的上面 -->
   <div class="icon">
       <a href="<c:url value="/BookServlet?method=load&bid=${bookall.bid }"/>"><img src="<c:url value='/${bookall.image }'/>" border="0"></a>
       <br/>
       <a href="<c:url value='/BookServlet?method=load&bid=${bookall.bid }'/>">${bookall.bname }</a>
   </div>
</c:forEach>

<c:forEach items="${bookListAllByName }" var="bookall" varStatus="bookallstat">
<c:if test="${bookallstat.first }"><h5>全部分类>></h5></c:if>   <!-- 判断是否为第一个,分类显示在第一个div的上面 -->
   <div class="icon">
       <a href="<c:url value="/BookServlet?method=load&bid=${bookall.bid }"/>"><img src="<c:url value='/${bookall.image }'/>" border="0"></a>
       <br/>
       <a href="<c:url value='/BookServlet?method=load&bid=${bookall.bid }'/>">${bookall.bname }</a>
   </div>
</c:forEach>
 
<c:forEach items="${bookList }" var="book" varStatus="bookstat">
   <c:if test="${bookstat.first}"><h5>${book.categary.cname }>></h5></c:if>  <!-- 判断是否为第一个,分类显示在第一个div的上面 -->
   <div class="icon">
       <a href="<c:url value="/BookServlet?method=load&bid=${book.bid }"/>"><img src="<c:url value='/${book.image }'/>" border="0"></a>
       <br/>
       <a href="<c:url value='/BookServlet?method=load&bid=${book.bid }'/>">${book.bname }</a>
   </div>
</c:forEach>

<c:if test="${fn:length(bookListAll) > 0 }">
<div class="fenye">
<center>
第${pb.pc }页/共${pb.tp}页
   <a href="<c:url value='/BookServlet?method=findAllPageBean&pc=1'/>">首页</a>
<c:if test="${pb.pc > 1 }">
   <a href="<c:url value='/BookServlet?method=findAllPageBean&pc=${pb.pc-1 }'/>">上一页</a>
</c:if>

<c:choose>
    <c:when test="${pb.tp <= 4 }">
       <c:set var="begin" value="1"/>
       <c:set var="end" value="${pb.tp }"/>
    </c:when>
    <c:otherwise>
       <c:set var="begin" value="${pb.pc-2 }"/>
       <c:set var="end" value="${pb.pc+1 }"/>
       <c:if test="${begin < 1 }">
          <c:set var="begin" value="1"/>
          <c:set var="end" value="4"/>
       </c:if>
       <c:if test="${end > pb.tp }">
          <c:set var="begin" value="${pb.tp-3 }"/>
          <c:set var="end" value="${pb.tp }"/>
       </c:if>
    </c:otherwise>
</c:choose>

<c:forEach var="i" begin="${begin}" end="${end }">
    <c:choose>
        <c:when test="${i eq pb.pc }">
          [${i }]
        </c:when>
        <c:otherwise>
          <a href="<c:url value='/BookServlet?method=findAllPageBean&pc=${i }'/>">${i }</a>
        </c:otherwise>
    </c:choose>
</c:forEach>

<c:if test="${pb.pc < pb.tp }">
   <a href="<c:url value='/BookServlet?method=findAllPageBean&pc=${pb.pc+1 }'/>">下一页</a>
</c:if>
   <a href="<c:url value='/BookServlet?method=findAllPageBean&pc=${pb.tp }'/>">尾页</a>
</center>
</div>
</c:if>

  </body>
 
</html>

