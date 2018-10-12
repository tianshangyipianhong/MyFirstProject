<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'writemessage.jsp' starting page</title>
    <style>
    #div1{
  	width:600px;
  	height:400px;
  	text-align: center;
  	margin: 0 auto;
  	padding:10px;
  	background-image:url("/messageboard/img/successRegister.jpg");
  }
  #div2{
  	width:300px;
  	height:300px;
  	margin: 0 auto;
  	margin-top:50px;
  	border:1px solid red;
  }
  #s1{
  font-size:8px;
  color:gray;
  }
    </style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  <form action="${pageContext.request.contextPath }/WriteMessage" method="post">
  <div id="div1">
  <div id="div2">
  <table>
  <tr>
  <td>昵称:</td>
  <td>${name }</td>
  </tr>
  <tr>
  <td>内容:<span id="s1">(你还能输入20个字符)</span></td>
  <td><textarea name="content" style="width:200px;height:100px" maxlength="20"></textarea></td>
  </tr>
  <tr>
  <td><input type="submit" value="发布"></td>
  </tr>
  </table>
  </div>
  </div>
  </form>
	
  </body>
</html>
