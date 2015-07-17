<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />    
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->                
    <title>Login - Aries Premium Admin Template</title>
    <link rel="icon" type="image/ico" href="favicon.ico"/>
    
    <link href="css/stylesheets.css" rel="stylesheet" type="text/css" />
    <!--[if lt IE 10]>
        <link href="css/ie.css" rel="stylesheet" type="text/css" />
    <![endif]-->           
    <!--[if lte IE 7]>
        <script type='text/javascript' src='js/plugins/other/lte-ie7.js'></script>
    <![endif]-->    
    <script type='text/javascript' src='js/plugins/jquery/jquery-1.9.1.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/jquery-ui-1.10.1.custom.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/jquery-migrate-1.1.1.min.js'></script>
    <script type='text/javascript' src='js/plugins/jquery/globalize.js'></script>
    <script type='text/javascript' src='js/plugins/other/excanvas.js'></script>
    
    <script type='text/javascript' src='js/plugins/other/jquery.mousewheel.min.js'></script>
        
    <script type='text/javascript' src='js/plugins/bootstrap/bootstrap.min.js'></script>
    
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
       
    <div class="login">

        <div class="page-header">
            <div class="icon">
                <span class="ico-arrow-right"></span>
            </div>
            <h1>Login <small>METRO STYLE ADMIN PANEL</small></h1>
        </div>        
        
        <div class="row-fluid">
        <form id="login_form" name="login_form" method="get" action="LoginServlet">
            <div class="row-form">
                <div class="span12">
                    <input type="text" name="userid_input" placeholder="userid"/>
                </div>
            </div>
            <div class="row-form">
                <div class="span12">
                    <input type="password" name="password_input" placeholder="password"/>
                </div>            
            </div>
            <div class="row-form">
                <div class="span12">
                    <input type="checkbox"/> Keep me signed in
                </div>
            </div>
            <div class="row-form">
                <div class="span12">
                    <button class="btn" onclick="document.getElementById('login_form').submit();">Sign in <span class="icon-arrow-next icon-white"></span></button>
                </div>                
            </div>
        </form>
        </div>
    </div>
    
</body>
</html>
