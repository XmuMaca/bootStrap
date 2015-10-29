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

import com.server.strings.IStringConstans;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class IconAndUrl 
{
	public IconAndUrl()
	{
		
	}
	
	 public static byte[] unZip(byte[] data) {
	        byte[] b = null;
	        try {
	            ByteArrayInputStream bis = new ByteArrayInputStream(data);
	            ZipInputStream zip = new ZipInputStream(bis);
	            while (zip.getNextEntry() != null) {
	                byte[] buf = new byte[1024];
	                int num = -1;
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                while ((num = zip.read(buf, 0, buf.length)) != -1) {
	                    baos.write(buf, 0, num);
	                }
	                b = baos.toByteArray();
	                baos.flush();
	                baos.close();
	            }
	            zip.close();
	            bis.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return b;
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
		System.out.println("zip:" + icon.length);
		
		String imageName = createIconName();
		String path = store_path + imageName;
		//writeIcon(path, icon);
		byte[] temp = unZip(icon);
		
		System.out.println("unzip:" + icon.length);
		
		writeIcon(path, temp);
		
		return IStringConstans.REMOTE_IMAGE_PATH + imageName;
	}
	
	public String getUrl(String store_path, byte[] icon, int var)
	{
		String imageName = createIconName();
		String path = store_path + var + imageName;
		writeIcon(path, icon);		
		
		return IStringConstans.REMOTE_IMAGE_PATH + var + imageName ;
	}
	
	public String getUrl(String store_path, String icon, int var)
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
