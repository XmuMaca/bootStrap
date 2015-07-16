function pageInit(){
	var inputs = document.getElementsByTagName("input");
	
	for(var i = 1; i < inputs.length; i++){
		inputs[i].readOnly = true;
	}
}

function doChange(){
	var inputs = document.getElementsByTagName("input");
	
	for(var i = 1; i < inputs.length; i++){
		inputs[i].readOnly = false;
	}
}

function doCancel(){
var inputs = document.getElementsByTagName("input");
	
	for(var i = 1; i < inputs.length; i++){
		inputs[i].readOnly = true;
	}
}