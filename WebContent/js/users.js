function userSetStatus(){
	var userTable = document.getElementById("userTable");
	var userList = userTable.getElementsByTagName("span");
	
	for(i = 0; i < userList.length; i++){
		if(userList[i].innerHTML == "normal"){
			userList[i].className = "label label-success";
		}
		else if(userList[i].innerHTML == "banned"){
			userList[i].className = "label label-important";
		}
	}
}