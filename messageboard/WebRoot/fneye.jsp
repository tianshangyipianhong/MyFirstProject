<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userlist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <style>
           th{
               background-color: gray;
               color: #fff;
           }

        .c0{
            background-color: #5da0ed;
        }

        .c1{
            background-color: #cbdbed;
        }

        table{
            width: 500px;
            line-height: 30px;
        }
    </style>
  </head>
  
  <body>
  
		<table cellpadding="0" cellspacing="0" align="center">
		    <caption>人员列表</caption>
		    <tr>
		        <th>用户编号</th>
		        <th>用户姓名</th>
		        <th>用户评论</th>
		        <th>用户性别</th>
		        <th>操作</th>
		    </tr>
		    
		    <c:forEach items="${list}" var="u" varStatus="status">
			    <tr  class="c${status.index%2}">
			        <td>${u.umd	}</td>
			        <td>${u.uname}</td>
			        <td>${u.ucontent}</td>
			        <td>${u.usexy}</td>
			        <td>删除:</td>
			    </tr>
		    </c:forEach>
		 <tr>
		<td>页数:	${pageNum}</td> 
		 </tr>
		 <%		//获取servlet中存储的数据
		 		int pageNum=(Integer)request.getAttribute("pageNum");
		 		int total=(Integer)request.getAttribute("total");
		 		int pageCount=(Integer)request.getAttribute("pageCount");
		 		int totals=total/pageCount;
		 		int totalPages=total%pageCount;
		 		if(totalPages!=0){
		 			totals = totals+1;
		 		}
		 		//计算上一页的页码
		 		int front=pageNum-1;
		 		if(front<=0){
		 			front=1;
		 		}
		 		//计算下一页的页码
		 		int next=pageNum+1;
		 		if(next>totals){
		 			next=totals;
		 		}
		  %> 
		    <tr>
		        <td  colspan="5" align="center">
		            <a href="<%=path%>/CountPage?pageNum=1">首页</a>&nbsp;&nbsp;
		            <a href="<%=path%>/CountPage?pageNum=<%=front%>">上一页</a>&nbsp;&nbsp;
		            <a href="<%=path%>/CountPage?pageNum=<%=next%>">下一页</a>&nbsp;&nbsp;
		            <a href="<%=path%>/CountPage?pageNum=<%=totals%>">尾页</a>
		        </td>
		    </tr>
		</table>
  </body>
</html>
