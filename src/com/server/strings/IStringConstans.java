package com.server.strings;

public interface IStringConstans 
{
	/*table name*/
	String USER_TABLE_NAME = "user";
	String ACTIVITY_TABLE_NAME = "activity";
	String MESSAGE_TABLE_NAME = "message";
	String RECEIVE_TABLE_NAME = "receive";
	String RELEASE_TABLE_NAME = "release";
	
	/*mysql connection url*/
	String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	//String TestURL = "jdbc:mysql://172.16.1.246:3306/firstdatabase";
	String URL = "jdbc:mysql://172.16.1.246:3306/makefrienddb";
	String LocalURL = "jdbc:mysql://localhost:3306/makefrienddb";
	String DepartmentURL = "jdbc:mysql://111.143.188.237:3306/makefrienddb";
	String SAEURL = "jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_softwebserver";
	
	/*user & password*/
	String USER = "cz";
	String PASSWORD = "software";
	
	String LOCAL_USER = "root";
	String LOCAL_PASSWORD = "140030";
	
	String SAE_USER = "15owwk10j0";
	String SAE_PASSWORD = "y43wy1wi4mhj1lhlwhjmixh13xkwjxk05m10w4y3";
}
