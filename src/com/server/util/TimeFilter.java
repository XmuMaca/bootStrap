package com.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFilter {

	public static String Parse(String tempTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newTime = new Date();
		
		try {
			Date oldTime = sdf.parse(tempTime);
			
			long subTime = newTime.getTime() - oldTime.getTime();
			
			long days = subTime / (1000 * 60 * 60 * 24);
	        if (days < 1) {
	            long hours = subTime / (1000 * 60 * 60);
	            if (hours < 1) {
	                long minutes = subTime / (1000 * 60);
	                if (minutes < 1)
	                    return "Moments ago";
	                return (int)(minutes) == 1 ? String.format("%s minute", minutes) : String.format("%s minutes", minutes);
	            }
	            return (int)(hours) == 1 ? String.format("%s hour", hours) : String.format("%s hours", hours);
	        }
	        if (days >= 7) {
	            return (int)(days / 7) == 1 ? String.format("%s week", (int) (days / 7)) : String.format("%s weeks", (int) (days / 7));
	        } else
	            return (int)(days) == 1 ? String.format("%s day", days) : String.format("%s days", days);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tempTime;
	}
}
