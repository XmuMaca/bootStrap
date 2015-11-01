package com.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.server.strings.IAttritubes;
import com.server.strings.IStringConstans;

import net.coobird.thumbnailator.Thumbnails;

public class IconAndUrl 
{
	public IconAndUrl()
	{
		
	}
	
	/*get the url by the icon data consisting of string type
	 * 
	 * */
	public static String getUrl(String store_path, String icon)
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

	/*get the url by the icon data consisting of bytes array type
	 * the url is produced by store path and image name
	 * */
	public static String getUrl(String store_path, byte[] icon)
	{
		
		String imageName = createIconName();

		String path = store_path + IStringConstans.INIT_PIC_FLAG +imageName;
		String thumnailPath = store_path + imageName;
		writeIcon(path, icon);		
		icon2thumnail(path, thumnailPath);
		
		return IStringConstans.REMOTE_IMAGE_PATH + imageName;
	}
	
	/*
	 * the url is produced by store path ,image name and number, which aims at 
	 * distinguishing different pictures distributed at the same time by one person. 
	 * */
	public static String getUrl(String store_path, byte[] icon, int var)
	{
		String imageName = createIconName();
		String path = store_path + IStringConstans.INIT_PIC_FLAG + var + imageName;
		String thumnailPath = store_path + var + imageName;
		writeIcon(path, icon);		
		icon2thumnail(path, thumnailPath);
		
		return IStringConstans.REMOTE_IMAGE_PATH + var + imageName ;
	}
	
	public static String getUrl(String store_path, String icon, int var)
	{
		try 
		{
			byte[] ic;	
			ic = icon.getBytes("ISO-8859-1");				//make sure the encoding pattern 
			return getUrl(store_path, ic, var);	
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			return null;
		}
	}	
	
	/*
	 * write the icon data to the file
	 * */
	private static void writeIcon(String path, byte[] icon) 
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

	private static void icon2thumnail(String fromPath, String toPath)
	{
		try {
			Thumbnails.of(fromPath)
					  .scale(IAttritubes.THUMNAIL_RATE)
					  .outputFormat(IStringConstans.PNG)
					  .toFile(toPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				  
	}
	
	private static String createIconName()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date()) + IStringConstans.DOT_PNG;
	}
}
