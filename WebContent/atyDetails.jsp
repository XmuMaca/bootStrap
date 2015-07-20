<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.server.bean.Activity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />    
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->                
    <title>Images - Aries Premium Admin Template</title>
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
    
    <script type='text/javascript' src='js/plugins/shbrush/XRegExp.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shCore.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushXml.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushJScript.js'></script>
    <script type='text/javascript' src='js/plugins/shbrush/shBrushCss.js'></script>    
    
    <script type='text/javascript' src='js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='js/plugins.js'></script>
    <script type='text/javascript' src='js/charts.js'></script>
    <script type='text/javascript' src='js/actions.js'></script>
    
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
                <li class="active">
                	<a href="#" class="blblue">Activities</a>
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
                    <a href="index-2.html" class="button">
                        <div class="icon">
                            <span class="ico-monitor"></span>
                        </div>                    
                        <div class="name">Dashboard</div>
                    </a>                
                </li>
                <li>
                    <a href="#" class="button yellow">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-cog-2"></span>
                        </div>                    
                        <div class="name">UI Elements</div>
                    </a>          
                    <ul class="sub">
                        <li><a href="ui.html">UI Elements</a></li>
                        <li><a href="widgets.html">Widgets</a></li>
                        <li><a href="buttons.html">Buttons</a></li>
                        <li><a href="icons.html">Icons</a></li>
                        <li><a href="grid_system.html">Grid System</a></li>
                    </ul>
                </li>                
                <li>
                    <a href="#" class="button green">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-pen-2"></span>
                        </div>                    
                        <div class="name">Forms Stuff</div>
                    </a>                
                    <ul class="sub">
                        <li><a href="forms.html">Elements</a></li>
                        <li><a href="validation.html">Validation</a></li>
                        <li><a href="grid.html">Grid</a></li>
                        <li><a href="editor.html">Editors</a></li>
                        <li><a href="wizard.html">Wizard</a></li>
                    </ul>                    
                </li>                        
                <li>
                    <a href="statistic.html" class="button red">
                        <div class="icon">
                            <span class="ico-chart-4"></span>
                        </div>                    
                        <div class="name">Statistic</div>
                    </a>                
                </li>                
                <li>
                    <a href="#" class="button dblue">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-layout-7"></span>
                        </div>                    
                        <div class="name">Tables</div>
                    </a> 
                    <ul class="sub">
                        <li><a href="tables.html">Simple</a></li>
                        <li><a href="tables_dynamic.html">Dynamic</a></li>
                    </ul>                                        
                </li>
                <li>
                    <a href="#" class="button purple">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-box"></span>
                        </div>                    
                        <div class="name">Samples</div>
                    </a>   
                    <ul class="sub">
                        <li><a href="faq.html">FAQ</a></li>
                        <li><a href="invoice.html">Invoice</a></li>
                        <li><a href="login.html">Login</a></li>
                    </ul>                                        
                </li>
                <li>
                    <a href="#" class="button orange">
                        <div class="arrow"></div>
                        <div class="icon">
                            <span class="ico-cloud"></span>
                        </div>                    
                        <div class="name">Other</div>
                    </a>                
                    <ul class="sub">
                        <li><a href="files.html">File handling</a></li>
                        <li><a href="images.html">Images</a></li>
                        <li><a href="typography.html">Typography</a></li>
                        <li><a href="404.html">Error 404</a></li>
                    </ul>                                      
                </li>                
                <li>
                    <div class="user">
                        <img src="img/examples/users/dmitry_m.jpg" align="left"/>
                        <a href="#" class="name">
                            <span>Dmitry Ivaniuk</span>
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
                        <span class="ico-pictures"></span>
                    </div>
                    <h1>Images <small>METRO STYLE ADMIN PANEL</small></h1>
                </div>
                
                <div class="row-fluid">
                    <div class="span12">
                        <div class="block">
                            <div class="head">
                                <div class="icon"><span class="ico-picture"></span></div>
                                <h2>Simple Gallery</h2>                    
                            </div>                           
                            <div class="data-fluid sGallery">
                                <div class="item"><a href="img/examples/photo/example_1.jpg" class="fb" rel="group"><img src="img/examples/photo/example_1.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_2.jpg" class="fb" rel="group"><img src="img/examples/photo/example_2.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_3.jpg" class="fb" rel="group"><img src="img/examples/photo/example_3.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_4.jpg" class="fb" rel="group"><img src="img/examples/photo/example_4.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_5.jpg" class="fb" rel="group"><img src="img/examples/photo/example_5.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_6.jpg" class="fb" rel="group"><img src="img/examples/photo/example_6.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_7.jpg" class="fb" rel="group"><img src="img/examples/photo/example_7.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_8.jpg" class="fb" rel="group"><img src="img/examples/photo/example_8.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_9.jpg" class="fb" rel="group"><img src="img/examples/photo/example_9.jpg" width="220"/></a></div>
                                <div class="item"><a href="img/examples/photo/example_10.jpg" class="fb" rel="group"><img src="img/examples/photo/example_10.jpg" width="220"/></a></div>
                            </div>
                        </div>     
                    </div>
                    
                </div>
                
                <div class="row-fluid typography">

                    <div class="block">
						 <div class="head">
                                <div class="icon"><span class="ico-picture"></span></div>
                                <h2>Simple Gallery</h2>                    
                            </div>
							
                        <div class="data">
							
							<%
							Activity aty = (Activity)request.getAttribute("aty");
							%>
								
                            <h2><%=aty.getName() %></h2>
                            <div class="row-fluid">
                                <div class="span12">
                                    <p><strong>Content</strong>, consectetur adipiscing elit. Etiam non luctus mauris. <a href="#">Nunc et turpis ac nibh vehicula venenatis</a> vitae sed eros. Donec non dignissim purus. Nam et urna et arcu ultricies suscipit. Nam elit erat, blandit sed congue sit amet, consequat sit amet nulla. <em>Proin lorem ipsum, vehicula a pellentesque ac</em>, tincidunt a mauris. In vel purus urna, a sodales nisl. Praesent nec tellus nec ante ullamcorper facilisis a ut velit. Praesent dictum rutrum leo ut condimentum. Nullam nec varius enim. Maecenas hendrerit risus id tellus venenatis a hendrerit urna eleifend. Aenean sed tincidunt nibh. Cras sodales porta sodales. Fusce vulputate interdum risus, porttitor blandit risus tincidunt eget.</p>
                                    <p>Maecenas tempus vehicula neque, a dictum lectus ullamcorper nec. <span class="text-error">Ut pellentesque nibh a lorem pharetra vitae lobortis felis semper. Sed vehicula scelerisque semper.</span> Donec arcu enim, ornare adipiscing adipiscing imperdiet, imperdiet nec ante. Proin sit amet odio turpis. Etiam non porttitor orci. Duis massa est, tincidunt eget faucibus eu, ultricies id turpis. Morbi imperdiet euismod nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed accumsan, dui et fermentum blandit, lectus est placerat est, placerat accumsan mi tortor non purus. Morbi lacus metus, fringilla et tempor sit amet, tempor volutpat leo. Nulla at interdum mi.</p>                            
                                </div>
                            </div>

							<h4>Type</h4>
                            <p>dota</p>

                            <h4>Time</h4>
                            <p><strong>Begin:</strong>2015/7/16<strong>End:</strong>2015/7/16</p>

							<h4>Place</h4>
                            <p>haiyun2</p>
							
							
                            <h4>Members</h4>
                            <blockquote>
                            <p>23333</p>
                            </blockquote>

                            <div class="clearfix"></div>

                        </div>

                    </div>            

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
