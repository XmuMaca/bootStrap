<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.server.bean.Account" %>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.*" %>

<%
	Account account = (Account)session.getAttribute("account");
	String adminIcon = (String)session.getAttribute("adminIcon");
%>

<%
	ArrayList<ArrayList<String>> atyList = (ArrayList<ArrayList<String>>)request.getAttribute("atyList");
ArrayList<ArrayList<String>> userList = (ArrayList<ArrayList<String>>)request.getAttribute("userList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />    
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->                
    <title>APlus backstage management system</title>
    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lte IE 7]>
        <link href="css/ie.css" rel="stylesheet" type="text/css" />
        <script type='text/javascript' src='js/plugins/other/lte-ie7.js'></script>
    <![endif]-->      
    <script type='text/javascript' src='js/plugins/jquery/jquery-1.9.1.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/jquery-migrate-1.1.1.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/globalize.js'></script>
    <script type='text/javascript' src='js/plugins/other/excanvas.js'></script>
    
    <script type='text/javascript' src='js/plugins/other/jquery.mousewheel.min.js'></script>
        
    <script type='text/javascript' src='js/plugins/bootstrap/bootstrap.min.js'></script>            
    
    <script type='text/javascript' src='js/plugins/cookies/jquery.cookies.2.2.0.min.js'></script>    
    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.js'></script>    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='js/plugins/jflot/jquery.flot.resize.js'></script>
        
    <script type='text/javascript' src='js/plugins/epiechart/jquery.easy-pie-chart.js'></script>    
    <script type='text/javascript' src='js/plugins/sparklines/jquery.sparkline.min.js'></script>        
    
    <script type='text/javascript' src='js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>    
    
    <script type='text/javascript' src="js/plugins/uniform/jquery.uniform.min.js"></script>
    
    <script type='text/javascript' src='js/plugins/shbrush/XRegExp.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shCore.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushXml.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushJScript.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushCss.js'></script>    
    
    <script type='text/javascript' src='js/plugins.js'></script>
    <script type='text/javascript' src='js/charts.js'></script>
    <script type='text/javascript' src='js/actions.js'></script>
    
</head>
<body>    
    <div id="loader"><img src="img/loader.gif"/></div>
    <div class="wrapper">
        
        <div class="sidebar">
            
            <div class="top">
                <a href="activities.jsp#"><h1 style="font-size:50px;margin:4px 4px 20px 4px;">APLUS<small>v1.0</small></h1></a>
                <div class="search">
                    <div class="input-prepend">
                        <span class="add-on orange"><span class="icon-search icon-white"></span></span>
                        <input type="text"/>                                                      
                    </div>            
                </div>
            </div>
            
            <ul class="navigation" id="tags">            
                <li>
                	<a href="activities.jsp" class="blblue">Activities</a>
                </li>
                <li>
                	<a href="users.jsp" class="blyellow">Users</a>
                </li>
                <li class="active">
                    <a href="#" class="blgreen">Communities</a>
                </li>
                <li>
                	<a href="boardcast.jsp" class="blred">Boardcast</a>
                </li>                
                <li>
                    <a href="settings.jsp" class="bldblue">Settings</a>
                </li>
            </ul>
            
            <div class="widget">
                <div class="datepicker"></div>
            </div>
            
        </div>
        
        <div class="body">
            
            <ul class="navigation">
                <li>
                    <a href="activities.jsp" class="button">
                        <div class="icon">
                            <span class="ico-layout-7"></span>
                        </div>                    
                        <div class="name">all activities</div>
                    </a>                
                </li>
                <li>
                    <a href="banedActivities.jsp" class="button yellow">
                        <div class="icon">
                            <span class="ico-layout-7"></span>
                        </div>                    
                        <div class="name">baned activities</div>
                    </a>          
                </li>                
                <li>
                    <a href="users.jsp" class="button green">
                        <div class="icon">
                            <span class="ico-layout-7"></span>
                        </div>                    
                        <div class="name">all users</div>
                    </a>                
                </li>                        
                <li>
                    <a href="banedUsers.jsp" class="button red">
                        <div class="icon">
                            <span class="ico-layout-7"></span>
                        </div>                    
                        <div class="name">baned users</div>
                    </a>                
                </li>                
                <li>
                    <a href="communities.jsp" class="button dblue">
                        <div class="icon">
                            <span class="ico-box"></span>
                        </div>                    
                        <div class="name">communities</div>
                    </a> 
                </li>
                <li>
                    <a href="boardcast.jsp" class="button purple">
                        <div class="icon">
                            <span class="ico-pen-2"></span>
                        </div>                    
                        <div class="name">boardcast</div>
                    </a>                
                </li>
                <li>
                    <a href="settings.jsp" class="button orange">
                        <div class="icon">
                            <span class="ico-cog-2"></span>
                        </div>                    
                        <div class="name">settings</div>                       
                    </a>                
                </li>                
                <li>
                    <div class="user">
                        <img src="<%=adminIcon %>" style="width:30px;border-radius:30px;" align="left"/>
                        <a href="#" class="name">
                            <span><%=account.getName() %></span>
                            <span class="sm">Administrator</span>
                        </a>
                    </div>
                    <div class="buttons">
                        <div class="sbutton green navButton">
                            <a href="#"><span class="ico-align-justify"></span></a>
                        </div>
                        <div class="sbutton blue">
                            <a href="#"><span class="ico-cogs"></span></a>
                            <div class="popup">
                                <div class="arrow"></div>
                                <div class="row-fluid">
                                    <div class="row-form">
                                        <div class="span12"><strong>SETTINGS</strong></div>
                                    </div>                                    
                                    <div class="row-form">
                                        <div class="span4">Navigation:</div>
                                        <div class="span8"><input type="radio" class="cNav" name="cNavButton" value="default"/> Default <input type="radio" class="cNav" name="cNavButton" value="bordered"/> Bordered</div>
                                    </div>                                    
                                    <div class="row-form">
                                        <div class="span4">Content:</div>
                                        <div class="span8"><input type="radio" class="cCont" name="cContent" value=""/> Responsive <input type="radio" class="cCont" name="cContent" value="fixed"/> Fixed</div>
                                    </div>                                    
                                </div>
                            </div>
                            
                        </div>
                        <button class="btn btn-warning" type="button" onClick="document.location.href = 'LogoutServlet';">Logout</button>                        
                    </div>
                </li>
            </ul>
            
            
            <div class="content">
                
                <div class="page-header">
                    <div class="icon">
                        <span class="ico-cube"></span>
                    </div>
                    <h1>Communities <small>METRO STYLE ADMIN PANEL</small></h1>
                </div>
                
                <div class="row-fluid">
                	<div class="span6">
                		<div class="block">
                            <div class="head">                                
                                <h2>Activities</h2>
                                <ul class="buttons">
                                    <li><a href="#" onClick="source('sortable'); return false;"><div class="icon"><span class="ico-info"></span></div></a></li>
                                </ul>                                
                            </div>                        
                            <div class="data-fluid">
                                <ul class="sList" id="sort_1">
                                	<%
                                	for(ArrayList<String> aty : atyList)
                                	{%>

                                    <li><%=aty.get(0) %>(<%=aty.get(1) %>)</li>
                                    
                                   <% }%>
                                </ul>
                            </div>
                        </div>
                	</div>
                	
                	<div class="span6">
                		<div class="block">
                            <div class="head">                                
                                <h2>Members</h2>
                                <ul class="buttons">
                                    <li><a href="#" onClick="source('sortable'); return false;"><div class="icon"><span class="ico-info"></span></div></a></li>
                                </ul>                                
                            </div>                        
                            <div class="data-fluid">
                                <ul class="sList" id="sort_1">
                                   <%
                                	for(ArrayList<String> user : userList)
                                	{%>

                                    <li><%=user.get(0) %>(<%=user.get(1) %>)</li>
                                    
                                   <% }%>
                                </ul>
                            </div>
                        </div>
                	</div>
                </div>
                
                <div class="row-fluid">
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                	<br/>
                </div>
                
                <div class="row-fluid">
                	<div class="span12">
                    	<div class="block">
                    		
                    		<div class="data-fluid">
                    		
                                <div class="row-form" style="float:right;">
                                    <button class="btn" type="button" onClick="history.back();">Return</button>
                                </div>
                            </div>
                    	</div>
                    </div>
                </div>                
                
            </div>
            
        </div>
        
    </div>
    
    <div class="dialog" id="source" style="display: none;" title="Source"></div>    
    
</body>
</html>
