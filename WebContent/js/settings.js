function pageInit(){
	var settingsForm = document.getElementById("validate");
	var inputs = settingsForm.getElementsByTagName("input");
	
	for(var i = 1; i < inputs.length; i++){
		inputs[i].readOnly = true;
	}
}

function doChange(){
	var settingsForm = document.getElementById("validate");
	var inputs = settingsForm.getElementsByTagName("input");
	
	for(var i = 1; i < inputs.length; i++){
		inputs[i].readOnly = false;
	}
}

function doCancel(){
	/*var settingsForm = document.getElementById("validate");
	var inputs = settingsForm.getElementsByTagName("input");
	
	for(var i = 1; i < inputs.length; i++){
		inputs[i].readOnly = true;
	}*/
	window.location.href = "settings.jsp";
}