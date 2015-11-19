/**
 * 
 */
// To avoid the "protocol not supported" alert, fail must open another app.
var appstore = "http://www.baidu.com";
	function applink(fail){		
		return function(){
			alert("hello");
		var clickedAt = +new Date;
		// During tests on 3g/3gs this timeout fires immediately if less than 500ms.
		setTimeout(function(){
		// To avoid failing on return to MobileSafari, ensure freshness!
			if (+new Date - clickedAt < 2000)
			{
				window.location = fail;
			}
			}, 500);    
	};
}
document.getElementById("enterApp").onclick = applink(appstore);
