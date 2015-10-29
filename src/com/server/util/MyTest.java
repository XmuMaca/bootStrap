package com.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date newTime = new Date();
		
		String tempTime = "2015-10-25 12:22";
		
		try {
			Date oldTime = sdf.parse(tempTime);
			
			long timeInterval = newTime.getDate() - oldTime.getDate();
			System.out.println(oldTime.getHours());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
	}

}
