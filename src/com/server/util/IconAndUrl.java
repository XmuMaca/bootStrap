package com.server.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.net.aso.a;

import com.server.strings.IStringConstans;

public class IconAndUrl 
{
	public IconAndUrl()
	{
		
	}
	
	public String getUrl(String store_path, String icon)
	{
		try 
		{
			byte[] ic;	
			ic = icon.getBytes("ISO-8859-1");				//make sure the encoding pattern 
			return getUrl(store_path, ic);	
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public String getUrl(String store_path, byte[] icon)
	{
		String imageName = createIconName();
		String path = store_path + imageName;
		writeIcon(path, icon);		
		
		return IStringConstans.REMOTE_IMAGE_PATH + imageName;
	}
	
	private void writeIcon(String path, byte[] icon) 
	{
		try {
			File file = new File(path);
			FileOutputStream fileos = new FileOutputStream(file);
			fileos.write(icon);
			fileos.flush();
			fileos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String createIconName()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date()) + IStringConstans.PNG;
	}
}
