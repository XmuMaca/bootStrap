package com.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
}
