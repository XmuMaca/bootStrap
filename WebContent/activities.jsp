<%@page import="java.sql.Connection"%>
<%@ page import="com.server.bean.Account" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.server.dao.ActivitiesDAO" %>
<%@ page import="com.server.bean.Activity" %>
<%@ page import="java.util.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
	Account account = (Account)session.getAttribute("account");
	String adminIcon = (String)session.getAttribute("adminIcon");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<base href="<%=basePath%>" />
	        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />    
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->                
    <title>Dynamic Tables - Aries Premium Admin Template</title>
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
    
    <script type='text/javascript' src="js/plugins/uniform/jquery.uniform.min.js"></script>
    
    <script type='text/javascript' src='js/plugins/datatables/jquery.dataTables.min.js'></script>
    
    <script type='text/javascript' src='js/plugins/shbrush/XRegExp.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shCore.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushXml.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushJScript.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushCss.js'></script>    
    
    <script type='text/javascript' src='js/plugins.js'></script>
    <script type='text/javascript' src='js/charts.js'></script>
    <script type='text/javascript' src='js/actions.js'></script>
    <script type='text/javascript' src='js/activities.js'></script>
    
</head>
<body>
    <div id="loader"><img src="img/loader.gif"/></div>
    <div class="wrapper">
        
        <div class="sidebar">
            
            <div class="top">
                <a href="activities.jsp#" class="logo"></a>
                <div class="search">
                    <div class="input-prepend">
                        <span class="add-on orange"><span class="icon-search icon-white"></span></span>
                        <input type="text"/>                                                      
                    </div>            
                </div>
            </div>
            
            <ul class="navigation" id="tags">            
                <li class="active">
                	<a href="#" class="blblue">Activities</a>
                	<div class="open"></div>
                    <ul>
                        <li class="active"><a href="activities.jsp">all activities</a></li>
                        <li><a href="banedActivities.jsp">baned activities</a></li>
                    </ul>
                </li>
                <li>
                	<a href="users.jsp" class="blyellow">Users</a>
                </li>
                <li>
                    <a href="communities.jsp" class="blgreen">Communities</a>
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
                        <span class="ico-layout-7"></span>
                    </div>
                    <h1>Dynamic Tables <small>METRO STYLE ADMIN PANEL</small></h1>
                </div>

                <div class="row-fluid">
                    <div class="span12">
                                    
                        <div class="block">
                            <div class="head orange">
                                <div class="icon"><span class="ico-layout-9"></span></div>
                                <h2>Table sorting pagination</h2>
                                <ul class="buttons">
                                    <li><a href="#" onClick="source('table_sort_pagination'); return false;"><div class="icon"><span class="ico-info"></span></div></a></li>
                                </ul>                                                        
                            </div>                
                                <div class="data-fluid">
                                    <table class="table fpTable lcnp" cellpadding="0" cellspacing="0" width="100%" id="atyTable">
                                        <thead>
                                            <tr>
                                                <th><input type="checkbox" class="checkall"/></th>
                                                <th width="20%">Name</th>
                                                <th>Product</th>
                                                <th width="20%">Status</th>
                                                <th width="20%">Date</th>
                                                <th width="80" class="TAC">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<%
                                        	ActivitiesDAO atyDAO = new ActivitiesDAO();
                                        	ArrayList<Activity> atyList = atyDAO.readActivities();
                                        	for(Activity aty : atyList){
                                        		String status = null;
                                        		String statusClass = null;
                                        		if(aty.getIsBanned() == 0)
                                        		{
                                        			status = "normal";
                                        			statusClass = "label label-success";
                                        		}
                                        		else
                                        		{
                                        			status = "banned";
                                        			statusClass = "label label-important";
                                        		}
                                        	%>
                                        	
                                            <tr>
                                                <td><input type="checkbox" name="order[]" value="528"/></td>
                                                <td><a href="AtyDetailsServlet?atyId=<%=aty.getId() %>"><%=aty.getId() %></a></td>
                                                <td><%=aty.getType() %></td>
                                                <td><span class="<%=statusClass%>"><%=status %></span></td>
                                                <td><%=aty.getStartTime() %></td>
                                                <td>
                                                    <a href="AtyChangeStatusServlet?atyId=<%=aty.getId() %>&status=0" class="button green">
                                                        <div class="icon"><span class="ico-pencil"></span></div>
                                                    </a>
                                                    <a href="AtyChangeStatusServlet?atyId=<%=aty.getId() %>&status=1" class="button red">
                                                        <div class="icon"><span class="ico-remove"></span></div>
                                                    </a>                                              
                                                </td>
                                            </tr>
                                            						<%}
                                            %>                                
                                        </tbody>
                                    </table>                    
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
