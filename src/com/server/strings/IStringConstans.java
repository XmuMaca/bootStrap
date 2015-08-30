package com.server.strings;

public interface IStringConstans 
{
	/*table name*/
	String USER_TABLE_NAME = "user";
	String ACTIVITY_TABLE_NAME = "activity";
	String MESSAGE_TABLE_NAME = "message";
	String RECEIVE_TABLE_NAME = "receive";
	String DISTRIBUTE_TABLE_NAME = "distribute";
	String EVALUATION_TABLE_NAME = "evaluation";
	String COMMENT_TABLE_NAME = "comment";
	String LIKE_TABLE_NAME = "islike";
	String JOIN_TABLE_NAME = "joining";
	String ADDCREDIT_TABLE_NAME = "scores";

	String PHOTOS_TABLE_NAME = "photos";

	String ATTENTION_TABLE_NAME = "attention";
	String COMMUNITY_TABLE_NAME = "communnity";

	
	/*mysql connection url*/
	String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	//String TestURL = "jdbc:mysql://172.16.1.246:3306/firstdatabase";
	String URL = "jdbc:mysql://172.16.1.246:3306/makefrienddb";
	String LocalURL = "jdbc:mysql://localhost:3306/makefrienddb";
	String DepartmentURL = "jdbc:mysql://111.143.188.237:3306/makefrienddb";
	String SAEURL = "jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_softwebserver";
	
	/*user & password*/
	String USER = "root";
	String PASSWORD = "software";
	
	String LOCAL_USER = "root";
	String LOCAL_PASSWORD = "140030";
	
	String SAE_USER = "15owwk10j0";
	String SAE_PASSWORD = "y43wy1wi4mhj1lhlwhjmixh13xkwjxk05m10w4y3";
	
	/*the url of the image*/
	//String REMOTE_IMAGE_PATH = "http://101.200.191.149:8080/bootstrapRepository/images_repo/";
	String REMOTE_IMAGE_PATH = "http://192.168.56.1:8081/bootStrap/WebContent/images_repo/";
	
	/*the pattern of the image*/
	String PNG = ".png";
	
	/*Json response*/
	String JSON_RESULT = "result";
	String JSON_OK = "success";
	String JSON_TRUE = "true";
	String JSON_FALSE = "false";
	
	
}
