<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.server.bean.Account" %>
<%@page import="java.sql.Connection"%>

<%
	Account account = (Account)session.getAttribute("account");
	String adminIcon = (String)session.getAttribute("adminIcon");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />    
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->                
    <title>Forms - Aries Premium Admin Template</title>
    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="css/stylesheets.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="css/myiconstyle.css" type="text/css" />
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
    
    <script type='text/javascript' src='js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>    
    
    <script type='text/javascript' src="js/plugins/uniform/jquery.uniform.min.js"></script>
    <script type='text/javascript' src="js/plugins/select/select2.min.js"></script>
    <script type='text/javascript' src='js/plugins/tagsinput/jquery.tagsinput.min.js'></script>
    <script type='text/javascript' src='js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    <script type='text/javascript' src='js/plugins/multiselect/jquery.multi-select.min.js'></script>
    
    <script type='text/javascript' src='js/plugins/validationEngine/languages/jquery.validationEngine-en.js'></script>
    <script type='text/javascript' src='js/plugins/validationEngine/jquery.validationEngine.js'></script>
    
    <script type='text/javascript' src='js/plugins/shbrush/XRegExp.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shCore.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushXml.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushJScript.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushCss.js'></script>    
    
    <script type='text/javascript' src='js/plugins.js'></script>
    <script type='text/javascript' src='js/charts.js'></script>
    <script type='text/javascript' src='js/actions.js'></script>
    <script type="text/javascript" src="js/cropbox.js"></script>
    
</head>
<body>    
    <div id="loader"><img src="img/loader.gif"/></div>
    <div class="wrapper">
        
        <div class="sidebar">
            
            <div class="top">
                <a href="index-2.html" class="logo"></a>
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
                <li>
                    <a href="communities.jsp" class="blgreen">Communities</a>
                </li>
                <li>
                	<a href="boardcast.jsp" class="blred">Boardcast</a>
                </li>                
                <li class="active">
                    <a href="#" class="bldblue">Settings</a>
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
                        <span class="ico-pen-2"></span>
                    </div>
                    <h1>Form <small>METRO STYLE ADMIN PANEL</small></h1>
                </div>
                

                <div class="row-fluid">
					<form id="validate" name="icon_form" method="post" action="IconChangeServlet">
                    <div class="span8">                

                        <div class="block">
                            <div class="head">                                
                                <h2>Default form elements</h2>
                                <ul class="buttons">             
                                    <li><a href="#" onClick="source('form_default'); return false;"><div class="icon"><span class="ico-info"></span></div></a></li>
                                </ul>                                  
                            </div>                                        
                            <div class="container">
                                
                                <div class="imageBox">
    								<div class="thumbBox"></div>
   	 								<div class="spinner" style="display: none">Loading...</div>
  								</div>
  								<div class="action"> 
    							<!-- <input type="file" id="file" style=" width: 200px">-->
    								<div class="new-contentarea tc"> 
    									<a href="javascript:void(0)" class="upload-img">
      										<label for="upload-file" style="font-size:20px;margin:18px auto auto auto;">上传图像</label>
      									</a>
      									<input type="file" class="" name="upload-file" id="upload-file" />
    								</div>
    								<input type="button" id="btnCrop"  class="Btnsty_peyton" value="裁切">
    								<input type="button" id="btnZoomIn" class="Btnsty_peyton" value="放大"  >
    								<input type="button" id="btnZoomOut" class="Btnsty_peyton" value="缩小" >
  								</div>
								<div class="cropped"></div>
                            </div>
                            <script type="text/javascript">
							$(window).load(function() {
								var options =
								{
									thumbBox: '.thumbBox',
									spinner: '.spinner',
									imgSrc: 'img/avatar.png'
								}
								var cropper = $('.imageBox').cropbox(options);
								$('#upload-file').on('change', function(){
								var reader = new FileReader();
								reader.onload = function(e) {
									options.imgSrc = e.target.result;
									cropper = $('.imageBox').cropbox(options);
								}
								reader.readAsDataURL(this.files[0]);
								this.files = [];
								})
								$('#btnCrop').on('click', function(){
									var img = cropper.getDataURL();
									$('.cropped').html('');
									
									$('.cropped').append('<input name="iconDataURL" type="text" value="'+img+'"></input>');
									
									$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
									$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
									$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
								})
								$('#btnZoomIn').on('click', function(){
									cropper.zoomIn();
								})
								$('#btnZoomOut').on('click', function(){
									cropper.zoomOut();
								})
							});
							</script>
                        </div>

                    </div>
                    <div class="span4">
                    	<div class="block">
                    		<div class="head">                                
                                <h2></h2>                                  
                            </div>
                    		<div class="data-fluid">
                    		
                    			<div class="row-form">
                    				<div class="span3">User Icon:</div>
                    				<img src="<%=adminIcon %>" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;" />
                    			</div>
                    			<div class="row-form">
                    				<div class="span3">&nbsp;</div>
                    				<button class="btn btn-large" type="button" onClick="history.back();">cancel</button>
                                    <button class="btn btn-large btn-success" type="submit">confirm</button>
                    			</div>
                            </div>
                    	</div>
                    </div>

				</form>
                </div>
                
                <div class="row-fluid">
                	<div class="span8">                
                        <div class="block">
                                                                    
                            <div class="data-fluid">
                                
                            </div>
                        </div>

                    </div>
                    
                    <div class="span4">
                    	<div class="block">
                    		
                    		<div class="data-fluid">
                    		
                                <div class="row-form" style="float:right;">
									<button class="btn btn-warning" type="button" onClick="doChange();">Change</button>	
                                	<button class="btn btn-success" type="button" onClick="document.getElementById('validate').submit();">Submit</button> 
                                    <button class="btn" type="button" onClick="doCancel();">Cancel</button>
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
