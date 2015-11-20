package com.client.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.server.db.UserDB;
import com.server.util.imageHandler.IconAndUrl;

public class PicPostServlet extends HttpServlet 
{

	private UserDB db = new UserDB();
	
	private String IMAGE_PATH;
	
	private DataOutputStream out;
	private DataInputStream in;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		IMAGE_PATH = getServletContext().getRealPath("/") + "images_repo/";
		System.out.println(IMAGE_PATH);
		
		in = new DataInputStream(req.getInputStream());
		
		addUserIcon(resp);
		
//		String action = in.readLine();
//		switch (action) {
//		case "userIcon":
//			addUserIcon(resp);
//			break;
//
//		default:
//			break;
//		}
	}
	
	private byte[] readPic()
	{
		try {
			int size = in.readInt();
			System.out.println(size);
			byte[] data = new byte[size];
			int len = 0;
			while(len < size)
			{
				len += in.read(data, len, size - len);
			}
						
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private void addUserIcon(HttpServletResponse resp)
	{
		//String userId = 
		byte[] iconData = readPic();

		IconAndUrl icon2url = new IconAndUrl();
		String path = icon2url.getUrl(IMAGE_PATH, iconData);
		
		System.out.println("image path:" + path);
		
	}
}
