<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
  body{
  background-image: url("/messageboard/img/successRegister.jpg");
  background-size: 100%;
  }
  center{
  font-family: inherit;
  font-size: 100px;
  color:red;
  margin-top:100px; 
  }
  p{
   font-size: 50px;
  }
  </style>
    <base href="<%=basePath%>">
    
    <title>My JSP 'successRegister.jsp' starting page</title>
    
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
    <center>注册成功!</center>
    <center><p><a href="${pageContext.request.contextPath }/index.jsp">返回登录界面</a></p></center>
  </body>
</html>
