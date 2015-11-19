<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="css/share/share.css">
	<script type="text/javascript" src="js/share/share.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" /> 
<title>校园岛</title>
</head>
<body>
	<% 
		JSONObject jsobj = (JSONObject)request.getAttribute("atyInfo");

		String tags = jsobj.getString("atyType");
		request.setAttribute("tags", tags);
	%>
	<div><h1>校园岛</h1></div>
	<hr/>
	<div><h2><%= jsobj.getString("atyName") %></h2></div>
	<div><span>开始时间: <%=jsobj.getString("atyStartTime") %></span><span>结束时间: <%=jsobj.getString("atyEndTime") %></span></div>
	<div><span>地点:<%=jsobj.getString("atyPlace") %></span></div>
	
	<div><%=jsobj.getString("atyContent") %></div>
	<c:forTokens items="${tags}" delims="&" var="tag">
		<span><c:out value="${tag}"/></span>
	</c:forTokens>
	
	<c:forEach items="${photos}" var="photo">
		<c:out value="${photo}"/>
		<img src="${photo}"/>
	</c:forEach>
	<hr/>
	<div><h3>发布者</h3></div>
	<div><span>昵称</span><span><%=jsobj.getString("userName") %></span></div>
	<hr/>
	<div><h3>Ta还发布了</h3></div>
	<c:forEach items="${distributedAty}" var="aty">
		<span><c:out value="${aty}"/></span>
	</c:forEach>
	<div>
	</div>
	<div>
		<a href="http://www.baidu.com" id="openApp" onclick="openApp()">进入App</a>
	</div>
</body>
</html>