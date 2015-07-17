function activitySetStatus(){
	var atyTable = document.getElementById("atyTable");
	var atyList = atyTable.getElementsByTagName("span");
	
	for(i = 0; i < atyList.length; i++){
		if(atyList[i].innerHTML == "normal"){
			atyList[i].className = "label label-success";
		}
		else if(atyList[i].innerHTML == "banned"){
			atyList[i].className = "label label-important";
		}
	}
}