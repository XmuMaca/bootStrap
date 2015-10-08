package com.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateId 
{
	public CreateId()
	{
		
	}
	
	public static String createAtyId(String userId)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return userId + sdf.format(new Date());
	}
	
	public static String createCommentId(String userId)
	{
		return createAtyId(userId);		
	}
	
	public static String createCreditId(String userId)
	{
		return createAtyId(userId);
	}
	
	public static String createGroupId(String userId)
	{
		Random rd = new Random();
		return createAtyId(userId) + "x" + rd.nextInt(100);
	}
	
}
