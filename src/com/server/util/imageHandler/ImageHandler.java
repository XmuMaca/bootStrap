package com.server.util.imageHandler;

import java.io.IOException;

import com.server.strings.IAttritubes;
import com.server.strings.IStringConstans;

import net.coobird.thumbnailator.Thumbnails;

public class ImageHandler 
{
	/*thi method will be used*/
	public static void compress(String fromPath, String toPath, float rate)
	{
		try {
			
			Thumbnails.of(fromPath)
					  .scale(rate)
					  .outputFormat(IStringConstans.PNG)
					  .toFile(toPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
