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
	String ADDCREDIT_TABLE_NAME = "addcredit";

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
	
//	String LOCAL_USER = "root";
//	String LOCAL_PASSWORD = "ethink";
	
	String LOCAL_USER = "hikalif";
	String LOCAL_PASSWORD = "140030";
	
//	String LOCAL_USER = "root";
//	String LOCAL_PASSWORD = "software";
	
	String SAE_USER = "15owwk10j0";
	String SAE_PASSWORD = "y43wy1wi4mhj1lhlwhjmixh13xkwjxk05m10w4y3";
	
	/*the url of the image*/
	//String REMOTE_IMAGE_PATH = "http://172.16.1.246:8081/bootStrap/images_repo/";
	//String REMOTE_IMAGE_PATH = "http://192.241.229.214:8080/bootStrap/images_repo/";
	//String REMOTE_IMAGE_PATH = "http://124.172.185.128:8090/bootStrap/images_repo/";
	
	String REMOTE_IMAGE_PATH = "http://192.168.199.217:8080/bootStrap/images_repo/";
	
	//String REMOTE_IMAGE_PATH = "http://192.168.1.103:8080/bootStrap/images_repo/";
	
	/*the pattern of the image*/
	String DOT_PNG = ".png";
	String PNG = "png";
	
	/*Json response*/
	String JSON_RESULT = "result";
	String JSON_OK = "success";
	String JSON_TRUE = "true";
	String JSON_FALSE = "false";
	
	/*system easemob id*/
	String SYSTEM_EASEMOB_ID = "systemzero";
	
	/*debug mode*/
	boolean DEBUG = true;
	boolean UN_DEBUG = false;
	
	/*initial picture flag*/
	String INIT_PIC_FLAG = "i";
}
