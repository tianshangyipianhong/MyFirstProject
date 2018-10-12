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
  	height:400px;
  	background-image:url("/messageboard/img/background.jpg");
  	background-repeat: no-repeat;
  	background-size:100%;
  	margin: 0 auto;
  	padding:10px;
  	text-align:center; 
  	
  }
  form{
  	margin-left:250px;
  }
  input{
  margin:5px;
  font-size: 20px;
  }
  </style>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
  
    <div id="div1">
    <center><h1 style="color:red">用户注册</h1></center>
    
    
    <form action="${pageContext.request.contextPath }/register" method="post">
    	<table>
    	<tr>
    	<td>用户名:</td>
    	<td><input onblur="check(this)"  type="text" name="username" id="username" ><span style="color:red;font-size:12px;display:none">用户名已存在</span></td>
    	</tr>
    	<tr>
    	<td>密码:</td>
    	<td><input type="password" name="password" id="password"></td>
    	</tr>
    	<tr>
    	<td>确认密码:</td>
    	<td><input type="password" name="password1" id="password1"></td>
    	</tr>
    	<tr>
    	<td>性别:</td>
    	<td><input type="radio" name="sexy" value="男">男
    	<input type="radio" name="sexy" value="女">女
    	</td>
    	</tr>
    	<tr>
    	<td>身份证:</td>
    	<td><input type="text" name="idcard"></td>
    	</tr>
    	<tr>
    	<td colspan="3"><input type="button" onclick="checkData()" value="注册"/></td>
    	</tr>
    	</table>
    </form>
    </div>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.min.js"></script>
  <script type="text/javascript">
  //确认用户名和密码名长度以及提交表单
 function checkData(){
   var  name=$("#username").val();
   var  password=$("#password").val();
   var  password1=$("#password1").val();
	if(name.length<3){
	alert("用户名长度不能小于3!");
	return;
	}
	if(password.length<6){
	alert("密码长度不能小于6!");
	return;
	}
	if(password!=password1){
	alert("两次密码不一致!")
	return;
	}
	//可以提交表单了
	var form = $("form");
	form.submit();
  }
  //ajax传值判断用户名是否存在
   function check(cell){
  	var url="${pageContext.request.contextPath}/checkname";
  	var param={username:cell.value};
  	$.get(url,param,function(data){
  		if(data==1){
  		$("span").show();
  		}else{
  		$("span").hide();
  		}
  	})
  } 
  
  
  </script>
</html>
