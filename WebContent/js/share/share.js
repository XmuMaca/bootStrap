function openApp(){
		alert("hello");
		var the_href="http://www.baidu.com";//获得下载链接
		window.location="apps custom url schemes";//打开某手机上的某个app应用
		setTimeout(function(){
			window.location=the_href;//如果超时就跳转到app下载页
		},500);
	}