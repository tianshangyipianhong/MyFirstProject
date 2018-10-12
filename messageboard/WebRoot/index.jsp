<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
  #div1{
  	width:800px;
  	height:500px;
  	background-image:url("/messageboard/img/background.jpg");
  	background-repeat: no-repeat;
  	background-size:100%;
  	text-align: center;
  	margin: 0 auto;
  	padding:10px;
  }
  center{
  font-size:30px;
  color:orange;
  }
  #div2{
  margin: 0 auto;
  width:250px;
  height:300px;
  background-image:url("/messageboard/img/login.jpg");
  background-size:100%;
  padding:20px;
  }
  .input1{
  margin:5px;
  font-size: 20px;
  }
  #i{
  margin:40px auto;
  background-color: blue;
  }
  p{
  	font-size: 14px;
  	color:red;
	 }
  </style>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <div id="div1">
     <p><center>登陆你的账号密码,开始留言吧!</center></p>
     <form action="${pageContext.request.contextPath }/LoginServlet" method="post">
     <div id="div2">
     用户名:<input class="input1" style="width:150px;height:30px" type="text"  name="username" onfocus="javascript:if(this.value=='请输入用户名')this.value=''" value="请输入用户名"><br>
     密码:&nbsp;<input	class="input1"  style="width:150px;height:30px" type="password" name="password" value="请输入密码" onfocus="javascript:if(this.value=='请输入密码')this.value=''">
     <p><input type="checkbox">记住密码 &nbsp;忘记密码?<a href="${pageContext.request.contextPath }/register.jsp">立即注册</a></p>
     <input id="i" style="width:250px;height:30px;color:white" type="submit" value="登录">
     <p>使用社交账号注册:QQ&nbsp;微信&nbsp;人人</p>
     </div>
     </form>
    </div>
  </body>
</html>
