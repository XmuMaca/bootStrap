package com.server.util.imageHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.print.attribute.standard.RequestingUserName;

import org.apache.taglibs.standard.extra.spath.RelativePath;

import com.server.strings.IAttritubes;
import com.server.strings.IStringConstans;
import com.server.util.fileHandler.FileHandler;

import net.coobird.thumbnailator.Thumbnails;

public class IconAndUrl 
{
	
	public IconAndUrl()
	{
		
	}

	/*get the url by the icon data consisting of string type
	 * 
	 * */
	public static String getUrl(String root_path, String icon)
	{
		try 
		{
			byte[] ic;	
			ic = icon.getBytes("ISO-8859-1");				//make sure the encoding pattern 
			return getUrl(root_path, ic);	
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			return null;
		}
	}

	/*get the url by the icon data consisting of bytes array type
	 * the url is produced by store path and image name
	 * 
	 * root_path ： 项目所在服务器的绝对路径 + 一个文件夹（因为tomcat不能直接在webapp根目录放文件，所以建个文件夹存图片）
	 * relative_path : 根据当前时间确定一个相对文件夹路径
	 * store_path: root_path + relative_path 构成存储的绝对路径
	 * store_thumnail: 缩略图的绝对存储路径
	 * 最后返回的是除了ip:port的一个相对路径存储都数据库中
	 * */
	public static String getUrl(String root_path, byte[] icon)
	{
		String imageName = createIconName();
		String relative_path = makeRelativeDir();
		String dir_path = root_path + relative_path;
		
		FileHandler.createDir(dir_path);
		
		String store_path = dir_path + IStringConstans.INIT_PIC_FLAG +imageName;
		String store_thumnailPath = dir_path + imageName;
		
		FileHandler.writeFile(store_path, icon);
		
		icon2thumnail(store_path, store_thumnailPath, icon.length);
		
		return IStringConstans.REMOTE_IMAGE_PATH + relative_path + imageName;
	}
	
	/*
	 * the url is produced by store path ,image name and number, which aims at 
	 * distinguishing different pictures distributed at the same time by one person. 
	 * */
	public static String getUrl(String root_path, byte[] icon, int var)
	{
		String imageName = createIconName();
		String relative_path = makeRelativeDir();
		String dir_path = root_path + relative_path;
		
		FileHandler.createDir(dir_path);
		
		String store_path = dir_path + IStringConstans.INIT_PIC_FLAG + var + imageName;
		String store_thumnailPath = dir_path + var + imageName;
		
		FileHandler.writeFile(store_path, icon);	
		
		icon2thumnail(store_path, store_thumnailPath, icon.length);
		
		return IStringConstans.REMOTE_IMAGE_PATH + relative_path + var + imageName ;
	}
	
	public static String getUrl(String root_path, String icon, int var)
	{
		try 
		{
			byte[] ic;	
			ic = icon.getBytes("ISO-8859-1");				//make sure the encoding pattern 
			return getUrl(root_path, ic, var);	
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			return null;
		}
	}	

	private static void icon2thumnail(String fromPath, String toPath, int picSize)
	{ 
		
		if(picSize > IStringConstans.COMPRESSION_LEVEL_1)
		{
			ImageHandler.compress(fromPath, toPath, IStringConstans.COMPRESSION_RATE_1);
		}
		else if(picSize > IStringConstans.COMPRESSION_LEVEL_2) {
			ImageHandler.compress(fromPath, toPath, IStringConstans.COMPRESSION_RATE_2);
		}
		else if(picSize > IStringConstans.COMPRESSION_LEVEL_3)
		{
			ImageHandler.compress(fromPath, toPath, IStringConstans.COMPRESSION_RATE_3);
		}
	}
	
	private static String createIconName()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date()) + IStringConstans.DOT_PNG;
	}
	
	private static String makeRelativeDir()
	{
		Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH))+1;
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
        return String.format("%d/%d/%d/", year, month, day_of_month);
	}
}
