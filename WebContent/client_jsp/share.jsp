<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" /> 
<title>校园岛</title>
</head>
<body>
	<% 
		JSONObject jsobj = (JSONObject)request.getAttribute("atyInfo");
		List<String> distributedAty = (ArrayList<String>)request.getAttribute("distributedAty");
		List<String> photos = (ArrayList<String>)request.getAttribute("photos");
	%>
	<div><h1>校园岛</h1></div>
	<hr/>
	<div><h2><%= jsobj.getString("atyName") %></h2></div>
	<div><span>开始时间: <%=jsobj.getString("atyStartTime") %></span></div>
	<div><span>结束时间: <%=jsobj.getString("atyEndTime") %></span></div>
	<div><span>地点:<%=jsobj.getString("atyPlace") %></span></div>
	
	<div><%=jsobj.getString("atyContent") %></div>
	<div><%=jsobj.getString("atyType") %></div>
	<hr/>
	<div><h3>发布者</h3></div>
	<div><span>昵称</span><span><%=jsobj.getString("userName") %></span></div>
	<hr/>
	<div><h3>Ta还发布了</h3></div>
	<div><%= distributedAty.get(0)%><%= distributedAty.get(1)%></div>
	<a ></a>
</body>
</html>