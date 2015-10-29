package com.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFilter {

	/**
	 * 评论时间格式转换
	 *  */
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
	
	/** 
	 * 私信时间格式转换
	 * */
	public static String ChatTimeParse(String tempTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date newTime = new Date();
		
		try {
			Date oldTime = sdf.parse(tempTime);
			
			long timeInterval = newTime.getDate() - oldTime.getDate();
			
			if(timeInterval == 0)
			{
				String timeFormat = "HH:mm";
				int hour = oldTime.getHours();
				
				if(hour >= 0 && hour < 6)
				{
					timeFormat = "凌晨 hh:mm";
				}
				else if(hour >= 6 && hour < 12)
				{
					timeFormat = "上午 hh:mm";
				}
				else if(hour >= 12 && hour < 18)
				{
					timeFormat = "下午 hh:mm";
				}
				else if(hour >= 18 && hour < 24)
				{
					timeFormat = "晚上 hh:mm";
				}
				
				return new SimpleDateFormat(timeFormat).format(oldTime);
			}
			else if(timeInterval == 1)
			{
				return "昨天";
			}
			else if(timeInterval == 2)
			{
				return "前天";
			}
			else
			{
				return new SimpleDateFormat("yyyy-MM-dd").format(oldTime);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tempTime;
	}
	
	/** 
	 * 活动开始时间格式转换
	 * */
	public static String AtyTimeParse(String tempTime)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date newTime = new Date();
		
		String format = "MM月dd日\nHH:mm";
		
		Date oldTime = new Date();
		
		try {
			oldTime = sdf.parse(tempTime);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new SimpleDateFormat(format).format(oldTime);
	}
}
