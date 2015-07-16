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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return userId + sdf.format(new Date());
	}
	
	
}
