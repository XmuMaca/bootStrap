package com.client.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.catalina.deploy.LoginConfig;

import com.server.bean.Account;
import com.server.bean.Activity;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;
import com.server.util.CreateId;
import com.server.util.IconAndUrl;

import net.sf.json.JSONObject;

public class ClientPostServlet extends HttpServlet
{
	private String IMAGE_PATH;
	
	private UserDB db = new UserDB();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		IMAGE_PATH = getServletContext().getRealPath("/") + "user_images/";
		System.out.println(IMAGE_PATH);
		
		db.createConnection();
		
		JSONObject jsobj = readJson(req);		
		String action = jsobj.getString("action");
		
		
		switch (action) {
		case "login":
			login(resp, jsobj);
			break;
		case "signup":
			signup(resp, jsobj);
			break;
		case "release":
			release(resp, jsobj);
			break;
		case "like":
			like(resp, jsobj);
			break;
		case "showActivities":
			showActivities(resp, jsobj);
			break;
		case "showComments":
			showComments(resp, jsobj);
			break;
		case "comment":
			comment(resp, jsobj);
			break;
		default:
			break;
		}
		
		db.close();
	}
	
	private JSONObject readJson(HttpServletRequest req)
	{
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(req.getInputStream());
			
			Object obj = ois.readObject();
			
			JSONObject jsobj = JSONObject.fromObject((String)obj);
			
			ois.close();
			
			return jsobj;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void writeJson(HttpServletResponse resp, JSONObject jsobj)
	{
		resp.setContentType("text/html;charset=utf-8");
		
		ObjectOutputStream oos;
		
		try {
			oos = new ObjectOutputStream(resp.getOutputStream());
			
			oos.writeObject(jsobj.toString());
						
			oos.flush();
			oos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		
	private void login(HttpServletResponse resp, JSONObject jsobj)
	{
		String id = jsobj.getString("userId");
		String password = jsobj.getString("userPassword");
		
		//System.out.println("id:" + id + " password:" + password);
		
		String query_sql = String.format("select * from %s where userId=%s and userPassword=%s", IStringConstans.USER_TABLE_NAME, id, password);
		
		JSONObject outJSon = new JSONObject();
		
		if (db.query(query_sql)) 
		{
			List<Account> list = db.queryResult(query_sql);
			Account accout = list.get(0);
			
			if (accout.getIsBaned() == 0) 
			{
				outJSon.put("userId", accout.getId());
				//outJSon.put("userPassword", );
				outJSon.put("userName", accout.getName());
				outJSon.put("userIcon", accout.getIcon());
				outJSon.put("userGender", accout.getGender());
				outJSon.put("userEmail", accout.getEmail());
				outJSon.put("userPhone", accout.getPhone());
				outJSon.put("userIsBaned", accout.getIsBaned());
				outJSon.put("result", "true");
			}
			else 
			{
				outJSon.put("result", "isBanned");
			}
		}
		else 
		{
			outJSon.put("result", "false");
		}
		
		writeJson(resp, outJSon);
		
	}
	
	private void signup(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJSon = new JSONObject();
		Account account = new Account();
		
		account.setId(jsobj.getString("userId"));
		String query_sql = String.format("select * from %s where userId=%s", IStringConstans.USER_TABLE_NAME, account.getId());
		
		if (!db.query(query_sql)) 
		{
			account.setEmail(jsobj.getString("userEmail"));
			account.setPhone(jsobj.getString("userPhone"));
			account.setPassword(jsobj.getString("userPassword"));
			account.setGender(jsobj.getString("userGender"));
			account.setName(jsobj.getString("userName"));
			
			String iconData = jsobj.getString("userIcon");		
			IconAndUrl icon2url = new IconAndUrl();
			account.setIcon(icon2url.getUrl(IMAGE_PATH, iconData));
			
			String signup_sql = String.format("insert into %s(userId,userName,userPassword,userIcon,userGender,userEmail,userPhone) values('%s','%s','%s','%s','%s','%s','%s')", IStringConstans.USER_TABLE_NAME, account.getId(), account.getName(), account.getPassword(),account.getIcon(), account.getGender(), account.getEmail(), account.getPhone());
			db.save(signup_sql);
			
			outJSon.put("result", "true");
			
		}
		else 
		{
			outJSon.put("result", "false");
		}
		
		writeJson(resp, outJSon);
	}

	private void release(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		Activity activity = new Activity();
		
		activity.setId(CreateId.createAtyId(jsobj.getString("userId")));
	}
	
	private void like(HttpServletResponse resp, JSONObject jsobj)
	{
		
	}
	
	private void showActivities(HttpServletResponse resp, JSONObject jsobj)
	{
		
	}
	
	private void showComments(HttpServletResponse resp, JSONObject jsobj)
	{
		
	}
	
	private void comment(HttpServletResponse resp, JSONObject jsobj)
	{
		
	}
}
