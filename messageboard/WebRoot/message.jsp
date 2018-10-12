<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<style type="text/css">
#div1 {
	width: 1200px;
	height: 1500px;
	background-image: url("/messageboard/img/ban.jpg");
	background-repeat:;
	background-size: 100%;
	margin: 0 auto;
	padding: 10px;
}

#p1 {
	font-size: 30px;
	color: red;
}

#div2 {
	width: 1000px;
	height: 800px;
	border: 1px solid red;
	margin: 0 auto;
}

#div3 {
	width: 450px;
	height: 30px;
	margin: 10px 10px;
}

.yes {
	background-color: red;
}

.no {
	background-color: yellow;
}

#a {
	cursor:pointer;
}

.td1 {
	position: absolute;
	left: 1000px;
}

.good1 {
	color: gray;
	font-weight: bold;
	cursor: pointer;
}

.good0{
	color: red;
	font-weight: bold;
	cursor: pointer;
}
.good2{
	color:blue;
	font-weight: bold;
	font-size:20px;
	opacity:0;
}
#ss{
	position:absolute;
	left:950px;
	cursor: pointer;
}
#ss1{
	position:absolute;
	left:800px;
	cursor: pointer;
}
</style>
<title>My JSP 'message.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function addGood(td,umd,ucontent,time,name,users) {
		//检验是否还能点击
		var className = td.firstElementChild.className;
		if(className=="good1"){
			alert("讨厌!还要点人家~");
			return;
		}
		//+1的显示和消失	
		var class1 = td.lastElementChild;
			class1.style.opacity="1";
			setTimeout(function() {
			class1.style.opacity="0";
			}, 1500)
		
		//点赞数+1	
		var oldgood = td.firstElementChild.innerHTML
		var newgood = parseInt(oldgood) + 1;
		td.firstElementChild.innerHTML = newgood;
		$.get("${pageContext.request.contextPath}/click", {
			newgood : newgood,
			umd : umd
		}, function(data) {
			td.firstElementChild.className = "good1";
		});
		
		$.get("${pageContext.request.contextPath}/CountClickServlet",{ucontent:ucontent,umd:umd,time:time,name:name},function(data){
		});
		
		$.get("${pageContext.request.contextPath}/ClickUsers?action=insert",{id:umd,users:users},function(data){});	
	}
</script>
</head>
<body>
	<div id="div1">
		<p id="p1">留言板</p>
		<div id="div2">
		<form action="${pageContext.request.contextPath }/Servlet" method="post" enctype="multipart/form-data">
    	头像:<img alt="" src="/img/${headImg }" style="width:60px">
    	<a id="ss" href="${pageContext.request.contextPath }/showclick?usename=${name }">我点赞过谁?</a>
    	<a id="ss1" href="${pageContext.request.contextPath }/showmymessage?name=${name }">我发布的消息</a>
    	<br>欢迎您:${name }
    	<br><input type="file" name="file">
    	<input type="submit" value="更换头像">
    	</form>
			
				<table cellpadding="3px">
					<c:forEach items="${messages }" var="messages">
					<tr>
						<td>用户名:</td>
						<td>评论:</td>
						<td>发布时间:</td>
						<td colspan="2" class="td1">点赞数:</td>
					</tr>
					<tr class="${messages.usexy eq '男' ?'yes':'no' }">
						<td><img style="width:30px" src="/img/${messages.uheadimg}">${messages.uname }</td>
						<td>${messages.ucontent }</td>
						<td>${messages.upubdate }</td>
						<td class="td1" title="点赞"
							onclick="addGood(this,'${messages.umd}','${messages.ucontent }','${messages.upubdate }','${messages.uname }','${name }')">
							<span class="good${messages.canaddgood }">${messages.uclick }</span> 
							<span class="good2">+1</span>
						</td>
						<td><a id="a" href="${pageContext.request.contextPath }/deleteMessage?uuid=${messages.umd}">删除</a></td>
					</tr>
					</c:forEach>
				<tr>
		       		 <td  colspan="4" align="center">
		            <a href="<%=path%>/CountPage?pageNum=1">首页</a>&nbsp;&nbsp;
		            <a href="<%=path%>/CountPage?pageNum">上一页</a>&nbsp;&nbsp;
		            <a href="<%=path%>/CountPage?pageNum">下一页</a>&nbsp;&nbsp;
		            <a href="<%=path%>/CountPage?pageNum">尾页</a>
		        	</td>
		    	</tr> 
				</table>
		</div>
		<p>
			<a href="${pageContext.request.contextPath }/writemessage.jsp">新增留言</a>
		</p>
		<p>
			<a href="${pageContext.request.contextPath }/index.jsp">更换用户</a>
		</p>
	</div>
</body>
</html>
