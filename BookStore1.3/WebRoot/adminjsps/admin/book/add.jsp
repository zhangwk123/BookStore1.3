<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- <style type="text/css">
    body{
        background:rgb(254,238,189);
    }
</style> -->
</head>
<body>
   <h1>添加图书</h1>
   <p style="font-weight: 900; color: red;">${msg }</p>
   <!-- 由于要上传文件,所以baseservlet用不上了,需要重新建立一个AdminAddBookServlet -->
   <form id="form" action="<c:url value='/admin/AdminAddBookServlet'/>" method="post" enctype="multipart/form-data">
     添加书名：<input type="text" name="bname"/><br/>
     添加图片：<input type="file" name="image"/><br/>   
     添加单价：<input type="text" name="price"/><br/>
     添加作者：<input type="text" name="author"/><br/>
     添加分类：<select style="width:155px;height:20px;" name="cid">
                       <c:forEach items="${categaryList}" var="c">
                         <option value="${c.cid }" >${c.cname }</option>
                       </c:forEach>
                     </select><br/>
     <input type="submit" value="添加图书"/>
   </form>
</body>
</html>
