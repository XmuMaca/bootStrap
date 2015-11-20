<%@page import="net.sf.json.JSONArray"%>
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

	<script language="javascript" type="text/javascript" src="js/share/share.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" /> 
<title>校园岛</title>
</head>
<body>
	<% 
		JSONObject jsobj = (JSONObject)request.getAttribute("atyInfo");	
		JSONArray comments = (JSONArray)request.getAttribute("comments");
		String tags = jsobj.getString("atyType");
		request.setAttribute("tags", tags);
	%>	
	<h1><img class="logo" src="images_repo/logo.JPG">校园岛</h1>
	<hr/>
	<div><h2><%= jsobj.getString("atyName") %></h2></div>
	<div class="detailed"><span><%=jsobj.getString("atyStartTime") %></span><span><%=jsobj.getString("atyEndTime") %></span></div>
	<div class="detailed"><span>地点:</span><span><%=jsobj.getString("atyPlace") %></span></div>	
	<div><%=jsobj.getString("atyContent") %></div>
	<div>
		<c:forEach items="${photos}" var="photourl">
				<img class="atyPic" src="${photourl}"/>
		</c:forEach>
	</div>
	
	<div class="detailed">
		<c:forTokens items="${tags}" delims="&" var="tag">
			<span class="atyTag"><c:out value="${tag}"/></span>
		</c:forTokens>
	</div>
	<hr/>
	<div><h4>发布者</h4></div>
	<div><img class="userIcon" src="<%=jsobj.getString("userIcon")%>"/><span><%=jsobj.getString("userName") %></span></div>
	<hr/>
	<div><h4>Ta还发布了</h4></div>	
	<c:choose>
		<c:when test="${distributedAty.size() != 0 }">	
			<div>
				<c:forEach items="${distributedAty}" var="activity">
					<span><c:out value="${activity}"/></span>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				<span><c:out value="这是Ta发布的第一个活动哟~"/></span>
			</div>
		</c:otherwise>
	</c:choose>	
	<%
		int cmtNum = comments.size();
		for(int i = 0; i < cmtNum; i++)
		{
	%> 
	<hr/>
	<div>
		<img src="<%= comments.getJSONObject(i).getString("userIcon")%>"/>
		<span class="fontbold"><%= comments.getJSONObject(i).getString("userName")%></span>
		<span class="detailed"><%= comments.getJSONObject(i).getString("cmtTime")%></span>
	</div>
	<div><%= comments.getJSONObject(i).getString("cmtContent")%></div>
	
	<%  }%>
	
	
		<!--充当占位符的div块，无实质内容 -->
	<div style="height:20px;"></div> <!--fixed悬浮出来的footer -->
	<div class='footer'>
		<img class = "little_logo" src="images_repo/logo.JPG"/>
		<span>校园岛</span>
		<a id="enterApp" href="http://www.baidu.com" class="sharelink">进入App</a>
	</div>
</body>
</html>