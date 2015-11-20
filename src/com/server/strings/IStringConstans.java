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
	
//	String LOCAL_USER = "hikalif";
//	String LOCAL_PASSWORD = "140030";
	
	String LOCAL_USER = "root";
	String LOCAL_PASSWORD = "software";
	
	String SAE_USER = "15owwk10j0";
	String SAE_PASSWORD = "y43wy1wi4mhj1lhlwhjmixh13xkwjxk05m10w4y3";
	
	String TIAN_YI_CLOUD_USER = "root";
	String TIAN_YI_CLOUD_PASSWORD = "jCB2Q6ZaXpJrE";
	
	/*the url of the image*/
	String RELATIVE_IMAGE_PATH = "../images_repo/";
	
	//Tian Yi cloud server
	String REMOTE_IMAGE_PATH = "http://106.0.4.149:8082/bootStrap/images_repo/";
	//cz's local path
	//String REMOTE_IMAGE_PATH = "http://106.0.4.149:8082/bootStrap/images_repo/";
	
	
	//String REMOTE_IMAGE_PATH = "/images_repo/";
	

	
	//this the remote image path in Tianyi cloud.
	//String REMOTE_IMAGE_PATH = "http://106.0.4.149:8081/bootStrap/images_repo/";
	
	/*the pattern of the image*/
	String DOT_PNG = ".png";
	String PNG = "png";
	
	/*the level of the compression*/
	long COMPRESSION_LEVEL_1 = 500 * 1024; 
	float COMPRESSION_RATE_1 = 0.3f;
	long COMPRESSION_LEVEL_2 = 200 * 1024; 
	float COMPRESSION_RATE_2 = 0.5f;
	long COMPRESSION_LEVEL_3 = 50 * 1024; 
	float COMPRESSION_RATE_3 = 0.8f;
	
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
