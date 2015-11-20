package com.server.util.fileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
	/*
	 * write the icon data to the file
	 * */
	public static void writeFile(String path, byte[] icon) 
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
	
	public static void createDir(String path)
	{
		File file = new File(path);
		if(!file.exists() && !file.isDirectory())
		{
			file.mkdirs();
		}
	}
}
