<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showclickmessage.jsp' starting page</title>
    <style type="text/css">
    #div1{
    margin:0 auto;
    border:1px solid red;
    width:800px;
    height:600px;
    text-align: center;
    }
    td{
    width:200px;
    height:60px;
    color:red;
    }
    </style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  <center><h1 style="color:orange">${name }:这些人点了赞</h1></center>
  <div id="div1">
  <table>
	<tr>
	<td>点赞的人有:</td>
	</tr>
	<c:forEach items="${users }" var="users">
	<tr>
	<td>${users.name}</td>
	</tr>
	</c:forEach>
	<tr>	
	<td><a href="${pageContext.request.contextPath }/ShowMessage">返回留言板</a></td>
	<td><a href="${pageContext.request.contextPath }/index.jsp">返回登录界面</a></td>
	</tr>
	
	</table>
  </div>
	
  </body>
</html>
