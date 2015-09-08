package com.client.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessorMatcher;

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
		
		//IMAGE_PATH = getServletContext().getRealPath("/") + "images_repo/";
		IMAGE_PATH = getServletContext().getRealPath("/") + "images_repo/";
		System.out.println(IMAGE_PATH);
		
		db.createConnection();
		
		JSONObject jsobj = readJson(req);		
		String action = jsobj.getString("action");
		
		
		switch (action) {
		case "loginemail":
			loginemail(resp, jsobj);
			break;
		case "loginsina":
			loginsina(resp, jsobj);
			break;
		case "loginqq":
			loginqq(resp, jsobj);
			break;
		case "signupemail":
			signupemail(resp, jsobj);
			break;
		case "signupsina":
			signupsina(resp, jsobj);
			break;
		case "signupqq":
			signupqq(resp, jsobj);
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
		case "showJoinedAty":
			showJoinedAty(resp, jsobj);
			break;
		case "showDistributedAty":
			showDistributeAty(resp, jsobj);
			break;
		case "comment":
			comment(resp, jsobj);
			break;
		case "notLike":
			notLike(resp, jsobj);
			break;
		case "test":
			test(resp, jsobj);
			break;
		case "showProfile":
			showProfile(resp, jsobj);
			break;
		case "showHotAty":
			showHotAty(resp, jsobj);
			break;
		case "showNearbyAty":
			showNearbyAty(resp, jsobj);
			break;
		case "showHightAty":
			showHightAty(resp, jsobj);
			break;
		case "join":
			join(resp, jsobj);
			break;
		case "notJoin":
			notJoin(resp, jsobj);
			break;
		case "editProfile":
			editProfile(resp, jsobj);
			break;
		case "atyMembers":
			atyMembers(resp, jsobj);
			break;
		case "showCredit":
			showCredit(resp, jsobj);
			break;
		case "message":
			sendMessage(resp, jsobj);
			break;
		case "showMessage":
			showMessage(resp, jsobj);
			break;
		case "setPublicTrue":
			setPublicTrue(resp, jsobj);
			break;
		case "setPublicFalse":
			setPublicFalse(resp, jsobj);
			break;
		case "showMembers":
			showMembers(resp, jsobj);
			break;
		case "showAllCommunities":
			showAllCommunities(resp, jsobj);
			break;
		case "showCommunity":
			showCommunity(resp, jsobj);
			break;
		case "showAtyInCommunity":
			showAtyInCommunity(resp, jsobj);
			break;
		case "showMembersInCommunity":
			showMembersInCommunity(resp, jsobj);
			break;
		case "joinCty":
			joinCty(resp, jsobj);
			break;
		case "notJoinCty":
			notJoinCty(resp, jsobj);
			break;
		case "creditRank":
			creditRank(resp, jsobj);
			break;
		case "editAlbumRight":
			editAlbumRight(resp, jsobj);
			
		case "showUserAlbum":
			showUserAlbum(resp, jsobj);
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
	
	private void writeJson(HttpServletResponse resp, String json)
	{
		resp.setContentType("text/html;charset=utf-8");
		
		ObjectOutputStream oos;
		
		try {
			oos = new ObjectOutputStream(resp.getOutputStream());
			
			oos.writeObject(json);
						
			oos.flush();
			oos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		
	private void showProfile(HttpServletResponse resp, JSONObject json)
	{
		String userId = json.getString("userId");
		
		String query_sql = String.format("select * " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.USER_TABLE_NAME, userId);
		
		JSONObject outJson = new JSONObject();
		
		List<Account> list = db.queryResult(query_sql);
		Account account = list.get(0);
		outJson.put("userName", account.getName());
		outJson.put("userEmail", account.getEmail());
		outJson.put("userGender", account.getGender());
		outJson.put("userLocation", account.getLocation());
		outJson.put("userPhone", account.getPhone());
		outJson.put("userAlbumIsPublic", account.getIsPublic());
		outJson.put("userIcon", account.getIcon());		
		outJson.put("userCredit", account.getCredit());
		
		writeJson(resp, outJson.toString());
		
	}
	
	private void editProfile(HttpServletResponse resp, JSONObject json)
	{
		String userId = json.getString("userId");
		String userName = json.getString("userName");
		String userEmail = json.getString("userEmail");
		String userPhone = json.getString("userPhone");
		String userGender = json.getString("userGender");	
		String userLocation = json.getString("userLocation");
		
		String update_sql = String.format("update %s " +
										  "set userName='%s' ,userEmail='%s', userPhone='%s' ,userGender='%s', userLocation='%s' " +
										  "where userId='%s'", IStringConstans.USER_TABLE_NAME, userName, userEmail, userPhone, userGender, userLocation, userId);
		
		db.excuteUpdate(update_sql);
		
		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, outJson.toString());
		
	}
	
	private void loginemail(HttpServletResponse resp, JSONObject jsobj)
	{
		String id = jsobj.getString("userEmail");
		String password = jsobj.getString("userPassword");
		
		System.out.println("id:" + id + " password:" + password);
		
		String query_sql = String.format("select * from %s where userId='%s' and userPassword='%s'", IStringConstans.USER_TABLE_NAME, id, password);
		
		JSONObject outJSon = new JSONObject();
		
		if (db.query(query_sql)) 
		{
			List<Account> list = db.queryResult(query_sql);
			Account accout = list.get(0);
			
			if (accout.getIsBaned() == 0) 
			{
				outJSon.put("userId", accout.getId());
				outJSon.put("userPassword", accout.getPassword());
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
		
		writeJson(resp, outJSon.toString());
		
	}
	
	private void loginsina(HttpServletResponse resp, JSONObject jsobj)
	{
		String id = jsobj.getString("userId");
		
		//System.out.println("id:" + id + " password:" + password);
		
		String query_sql = String.format("select * from %s where userId='%s'", IStringConstans.USER_TABLE_NAME, id);
		
		JSONObject outJSon = new JSONObject();
		
		if (db.query(query_sql)) 
		{
			List<Account> list = db.queryResult(query_sql);
			Account accout = list.get(0);
			
			if (accout.getIsBaned() == 0) 
			{
				outJSon.put("userId", accout.getId());
				outJSon.put("userPassword", accout.getPassword());
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
		
		writeJson(resp, outJSon.toString());
		
	}
	
	private void loginqq(HttpServletResponse resp, JSONObject jsobj)
	{
		String id = jsobj.getString("userId");
		
		//System.out.println("id:" + id + " password:" + password);
		
		String query_sql = String.format("select * from %s where userId='%s'", IStringConstans.USER_TABLE_NAME, id);
		
		JSONObject outJSon = new JSONObject();
		
		if (db.query(query_sql)) 
		{
			List<Account> list = db.queryResult(query_sql);
			Account accout = list.get(0);
			
			if (accout.getIsBaned() == 0) 
			{
				outJSon.put("userId", accout.getId());
				outJSon.put("userPassword", accout.getPassword());
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
		
		writeJson(resp, outJSon.toString());
		
	}
	
	private void signupemail(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJSon = new JSONObject();
		Account account = new Account();
		
		account.setId(jsobj.getString("userEmail"));
		String query_sql = String.format("select * from %s where userId='%s'", IStringConstans.USER_TABLE_NAME, account.getId());
		
		if (!db.query(query_sql)) 
		{
			account.setEmail(jsobj.getString("userEmail"));
			//account.setPhone(jsobj.getString("userPhone"));
			account.setPassword(jsobj.getString("userPassword"));
			account.setGender(jsobj.getString("userGender"));
			account.setName(jsobj.getString("userName"));
			//String isPublic = jsobj.getString("userAlbumIsPublic");
			String isPublic = "true";
			
			String iconData = jsobj.getString("userIcon");		
			IconAndUrl icon2url = new IconAndUrl();
			account.setIcon(icon2url.getUrl(IMAGE_PATH, iconData));
			
			
			String signup_sql = String.format("insert into %s(userId,userName,userPassword,userIcon,userGender,userEmail, userAlbumIsPublic) values('%s','%s','%s','%s','%s', '%s', '%s')", IStringConstans.USER_TABLE_NAME, account.getId(), account.getName(), account.getPassword(),account.getIcon(), account.getGender(), account.getEmail(), isPublic);
			db.save(signup_sql);
			
//			//credit
//			String userId = jsobj.getString("userId");
//			String creditId = CreateId.createCreditId(userId);
//			String creditContent = "success";
//			int creditNumbers = 50;
//			
////			String insert_credit = String.format("insert into %s (userId, creditId,creditContent, creditNumbers) values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
//			String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
//			db.excuteUpdate(insert_credit);
////			
////			//userCode
//			String userCode = jsobj.getString("userCode");
//			
//			String updateCredit = String.format("update user set userCredit=userCredit+10 where userId='%s' ", userCode);
//			db.excuteUpdate(updateCredit);
			
			outJSon.put("result", "true");
			
		}
		else 
		{
			outJSon.put("result", "false");
		}
		
//		//credit
//		String userId = jsobj.getString("userId");
//		String creditId = CreateId.createCreditId(userId);
//		String creditContent = "success";
//		int creditNumbers = 50;
//		
////		String insert_credit = String.format("insert into %s (userId, creditId,creditContent, creditNumbers) values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
//		String insert_credit = String.format("insert into %s (userId, creditId) values('%s', '%s')", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId);
//		db.excuteUpdate(insert_credit);
//		
//		//userCode
//		String userCode = jsobj.getString("userCode");
//		
//		String updateCredit = String.format("update user set userCredit=userCredit+10 where userId='%s' ", userCode);
//		db.excuteUpdate(updateCredit);
		
		
		writeJson(resp, outJSon.toString());
	}
	
	private void signupsina(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJSon = new JSONObject();
		Account account = new Account();
		
		account.setId(jsobj.getString("userId"));
		String query_sql = String.format("select * from %s where userId='%s'", IStringConstans.USER_TABLE_NAME, account.getId());
		
		if (!db.query(query_sql)) 
		{
//			account.setEmail(jsobj.getString("userEmail"));
//			account.setPhone(jsobj.getString("userPhone"));
//			account.setPassword(jsobj.getString("userPassword"));
			account.setGender(jsobj.getString("userGender"));
			account.setName(jsobj.getString("userName"));
//			String isPublic = jsobj.getString("userAlbumIsPublic");
			String isPublic = "true";
			
//			String iconData = jsobj.getString("userIcon");		
//			IconAndUrl icon2url = new IconAndUrl();
//			account.setIcon(icon2url.getUrl(IMAGE_PATH, iconData));
			account.setIcon(jsobj.getString("userIcon"));
			
			String signup_sql = String.format("insert into %s(userId,userName,userPassword,userIcon,userGender,userEmail,userPhone, userAlbumIsPublic) values('%s','%s','%s','%s','%s','%s','%s', '%s')", IStringConstans.USER_TABLE_NAME, account.getId(), account.getName(), account.getPassword(),account.getIcon(), account.getGender(), account.getEmail(), account.getPhone(), isPublic);
			db.save(signup_sql);
			
//			//credit
//			String userId = jsobj.getString("userId");
//			String creditId = CreateId.createCreditId(userId);
//			String creditContent = "success";
//			int creditNumbers = 50;
//			
//			String insert_credit = String.format("insert into %s (userId, creditId,creditContent, creditNumbers) values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
////			String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
//			db.excuteUpdate(insert_credit);
//			
//			//userCode
//			String userCode = jsobj.getString("userCode");
//			
//			String updateCredit = String.format("update user set userCredit=userCredit+10 where userId='%s' ", userCode);
//			db.excuteUpdate(updateCredit);
			
			outJSon.put("result", "true");
			
		}
		else 
		{
			outJSon.put("result", "false");
		}
		
		writeJson(resp, outJSon.toString());
	}

	private void signupqq(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJSon = new JSONObject();
		Account account = new Account();
		
		account.setId(jsobj.getString("userId"));
		String query_sql = String.format("select * from %s where userId='%s'", IStringConstans.USER_TABLE_NAME, account.getId());
		
		if (!db.query(query_sql)) 
		{
//			account.setEmail(jsobj.getString("userEmail"));
//			account.setPhone(jsobj.getString("userPhone"));
//			account.setPassword(jsobj.getString("userPassword"));
			account.setGender(jsobj.getString("userGender"));
			account.setName(jsobj.getString("userName"));
//			String isPublic = jsobj.getString("userAlbumIsPublic");
			String isPublic = "true";
			
//			String iconData = jsobj.getString("userIcon");		
//			IconAndUrl icon2url = new IconAndUrl();
//			account.setIcon(icon2url.getUrl(IMAGE_PATH, iconData));
			account.setIcon(jsobj.getString("userIcon"));
			
			String signup_sql = String.format("insert into %s(userId,userName,userPassword,userIcon,userGender,userEmail,userPhone, userAlbumIsPublic) values('%s','%s','%s','%s','%s','%s','%s', '%s')", IStringConstans.USER_TABLE_NAME, account.getId(), account.getName(), account.getPassword(),account.getIcon(), account.getGender(), account.getEmail(), account.getPhone(), isPublic);
			db.save(signup_sql);
			
//			//credit
//			String userId = jsobj.getString("userId");
//			String creditId = CreateId.createCreditId(userId);
//			String creditContent = "success";
//			int creditNumbers = 50;
//			
//			String insert_credit = String.format("insert into %s (userId, creditId,creditContent, creditNumbers) values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
//			String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
//			db.excuteUpdate(insert_credit);
//			
//			//userCode
//			String userCode = jsobj.getString("userCode");
//			
//			String updateCredit = String.format("update user set userCredit=userCredit+10 where userId='%s' ", userCode);
//			db.excuteUpdate(updateCredit);
			
			outJSon.put("result", "true");
			
		}
		else 
		{
			outJSon.put("result", "false");
		}
		
		writeJson(resp, outJSon.toString());
	}
	
	private void release(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		Activity activity = new Activity();
		
		String userId = jsobj.getString("userId");
		String releaseTime = jsobj.getString("releaseTime");
		
		activity.setId(CreateId.createAtyId(userId));
		activity.setName(jsobj.getString("atyName"));
		activity.setType(jsobj.getString("atyType"));
		activity.setStartTime(jsobj.getString("atyStartTime"));
		activity.setEndTime(jsobj.getString("atyEndTime"));
		activity.setPlace(jsobj.getString("atyPlace"));
		activity.setLongitude(Double.parseDouble(jsobj.getString("longitude")));
		activity.setLatitude(Double.parseDouble(jsobj.getString("latitude")));
		activity.setMembers(Integer.parseInt(jsobj.getString("atyMembers")));
		activity.setContent(jsobj.getString("atyContent"));
		activity.setShares(Integer.parseInt(jsobj.getString("atyShares")));
		activity.setComments(Integer.parseInt(jsobj.getString("atyComments")));
		
		String atyAlbumStr = jsobj.getString("atyAlbum");
		String atyIsPublic = jsobj.getString("atyIsPublic");
		
		/*insert all pictures*/
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(atyAlbumStr);
		
		String albumId_aty = activity.getId();
		String albumId_user = userId;
		for (int i = 0; i < jsonArray.size(); i++) 
		{
			IconAndUrl icon2url = new IconAndUrl();
			String picurl = icon2url.getUrl(IMAGE_PATH, jsonArray.getString(i), i);
			String insert_pics_sql = String.format("insert into %s values('%s', '%s')", IStringConstans.PHOTOS_TABLE_NAME, picurl, albumId_aty);
			String insert_pics_sql2 = String.format("insert into %s values('%s', '%s')", IStringConstans.PHOTOS_TABLE_NAME, picurl, albumId_user);
			db.excuteUpdate(insert_pics_sql);
			db.excuteUpdate(insert_pics_sql2);
		}
		
		String insert_sql1 = String.format("insert into %s values('%s', '%s', '%s')", IStringConstans.DISTRIBUTE_TABLE_NAME, userId, activity.getId(), releaseTime);
		String insert_sql2 = String.format("insert into %s(atyId, atyName, atyType, atyStartTime, atyEndTime, atyPlace, atyLongitude, atyLatitude, atyMembers, atyContent, atyShares, atyIsPublic) values('%s', '%s', '%s', '%s', '%s', '%s', %f, %f, '%s', '%s', '%s', '%s')", IStringConstans.ACTIVITY_TABLE_NAME, activity.getId(), activity.getName(),activity.getType(), activity.getStartTime(), activity.getEndTime(), activity.getPlace(), activity.getLongitude(), activity.getLatitude(), activity.getMembers(), activity.getContent(), activity.getShares(), atyIsPublic);
		String joint_sql = String.format("insert into %s values('%s', '%s')", IStringConstans.JOIN_TABLE_NAME, userId, activity.getId());
		
		db.excuteUpdate(insert_sql1);
		db.excuteUpdate(insert_sql2);
		db.excuteUpdate(joint_sql);
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String creditContent = "发布活动：" + jsobj.getString("atyName");
		int creditNumbers = 10;
		
		String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
		
		db.excuteUpdate(insert_credit);
		
		outJson.put("atyId", activity.getId());
		writeJson(resp, outJson.toString());		
	}
	
	private void like(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		
		String update_aty = String.format("update %s " +
										"set atyLikes=atyLikes+1 " +
										"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		
		
		String insert_like = String.format("insert into %s values('%s','%s')", IStringConstans.LIKE_TABLE_NAME, userId, atyId);
		int row1 = db.excuteUpdate(update_aty);
		int row2 = db.excuteUpdate(insert_like);
		
		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		outJson.put("row1", row1);
		outJson.put("row2", row2);
		writeJson(resp, outJson.toString());
		
		
	}
	
	private void notLike(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		
		String update_aty = String.format("update %s " +
										"set atyLikes=atyLikes-1 " +
										"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		
		
		String delete_like = String.format("delete from %s where userId='%s' and atyId='%s'", IStringConstans.LIKE_TABLE_NAME, userId, atyId);
		db.excuteUpdate(update_aty);
		db.excuteUpdate(delete_like);
		
		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, outJson.toString());
	}
	
	private void join(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		
		String update_aty = String.format("update %s " +
										"set atyMembers=atyMembers+1 " +
										"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		
		
		String insert_like = String.format("insert into %s values('%s', '%s')", IStringConstans.JOIN_TABLE_NAME, userId, atyId);
		db.excuteUpdate(update_aty);	
		
		db.excuteUpdate(insert_like);
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String atyName = jsobj.getString("atyName");
		String creditContent = "joined activity" + atyName;
		int creditNumbers = 5;
		
		String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
				
		db.excuteUpdate(insert_credit);		

		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, outJson.toString());
	}
	
	private void notJoin(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		
		String update_aty = String.format("update %s " +
				"set atyMembers=atyMembers-1 " +
				"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		String delete_aty = String.format("delete from %s where atyId='%s' and userId='%s'", IStringConstans.JOIN_TABLE_NAME, atyId, userId);
		
		db.excuteUpdate(update_aty);
		db.excuteUpdate(delete_aty);
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String atyName = jsobj.getString("atyName");
		String creditContent = "退出活动：" + atyName;
		int creditNumbers = -5;
				
		String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
						
		db.excuteUpdate(insert_credit);

		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, outJson.toString());
	}
	
	private void showActivities(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty = "select user.userId, userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic " +
							"from activity, user, distribute " +
							"where activity.atyId = distribute.atyId and user.userId = distribute.userId and activity.atyIsBanned=0";
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);
		List<String> likeAty = db.getAtyId(queryIsLike);
		List<String> joinAty = db.getAtyId(queryIsJoined);
		
		System.out.println(likeAty.size());
		System.out.println(joinAty.size());
		
		for (int i = 0; i < outJson.size(); i++) 
		{
			JSONObject temp = outJson.getJSONObject(i);
			
			String query_album = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME,  temp.getString("atyId"));
			
			JSONArray jsarray = db.getAlbumUrl(query_album);
			
			temp.put("atyAlbum", jsarray);
			
			if (likeAty.size() > 0) 
			{
				if (likeAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsLiked", "true");
				}
				else 
				{
					temp.put("atyIsLiked", "false");
				}	
			}
			else 
			{
				temp.put("atyIsLiked", "false");
			}
			
			if (joinAty.size() > 0) 
			{
				if (joinAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsJoined", "true");
				}
				else 
				{
					temp.put("atyIsJoined", "false");
				}	
			}
			else 
			{
				temp.put("atyIsJoined", "false");
			}
			
			outJson.set(i, temp);
		}
		
		
		writeJson(resp, outJson.toString());
		
	}
	
	private void showHotAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments ,atyIsPublic " +
											"from %s, %s, %s " +
											"where activity.atyId = distribute.atyId and user.userId = distribute.userId and activity.atyIsBanned=0 " +
											"order by atyMembers", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME );
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);
		List<String> likeAty = db.getAtyId(queryIsLike);
		List<String> joinAty = db.getAtyId(queryIsJoined);
		
		System.out.println(likeAty.size());
		System.out.println(joinAty.size());
		
		for (int i = 0; i < outJson.size(); i++) 
		{
			JSONObject temp = outJson.getJSONObject(i);
			
			String query_album = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME,  temp.getString("atyId"));
			
			JSONArray jsarray = db.getAlbumUrl(query_album);
			
			temp.put("atyAlbum", jsarray);
			
			if (likeAty.size() > 0) 
			{
				if (likeAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsLiked", "true");
				}
				else 
				{
					temp.put("atyIsLiked", "false");
				}	
			}
			else 
			{
				temp.put("atyIsLiked", "false");
			}
			
			if (joinAty.size() > 0) 
			{
				if (joinAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsJoined", "true");
				}
				else 
				{
					temp.put("atyIsJoined", "false");
				}	
			}
			else 
			{
				temp.put("atyIsJoined", "false");
			}
			
			outJson.set(i, temp);
		}
		
		
		writeJson(resp, outJson.toString());		
	}
	
	private void showNearbyAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		Double userLongitude = Double.parseDouble(jsobj.getString("longitude"));
		
		Double userLatitude = Double.parseDouble(jsobj.getString("latitude"));
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic " +
											"from %s, %s, %s " +
											"where activity.atyId = distribute.atyId and user.userId = distribute.userId and activity.atyIsBanned=0 "
											+ "and atyLongitude>%f-1 and atyLongitude<%f+1 "
											+ "and atyLatitude>%f-1 and atyLatitude<%f+1 ", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME, userLongitude, userLongitude, userLatitude, userLatitude);
				
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);
		List<String> likeAty = db.getAtyId(queryIsLike);
		List<String> joinAty = db.getAtyId(queryIsJoined);
		
		System.out.println(likeAty.size());
		System.out.println(joinAty.size());
		
		for (int i = 0; i < outJson.size(); i++) 
		{
			JSONObject temp = outJson.getJSONObject(i);
			
			String query_album = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME,  temp.getString("atyId"));
			
			JSONArray jsarray = db.getAlbumUrl(query_album);
			
			temp.put("atyAlbum", jsarray);
			
			if (likeAty.size() > 0) 
			{
				if (likeAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsLiked", "true");
				}
				else 
				{
					temp.put("atyIsLiked", "false");
				}	
			}
			else 
			{
				temp.put("atyIsLiked", "false");
			}
			
			if (joinAty.size() > 0) 
			{
				if (joinAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsJoined", "true");
				}
				else 
				{
					temp.put("atyIsJoined", "false");
				}	
			}
			else 
			{
				temp.put("atyIsJoined", "false");
			}
			
			outJson.set(i, temp);
		}
		
		
		writeJson(resp, outJson.toString());				
	}
	
	private void showHightAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments,atyIsPublic " +
											"from %s, %s, %s " +
											"where activity.atyId = distribute.atyId and user.userId = distribute.userId and activity.atyIsBanned=0 " +
											"order by atyLikes", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME );
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);
		List<String> likeAty = db.getAtyId(queryIsLike);
		List<String> joinAty = db.getAtyId(queryIsJoined);
		
		System.out.println(likeAty.size());
		System.out.println(joinAty.size());
		
		for (int i = 0; i < outJson.size(); i++) 
		{
			JSONObject temp = outJson.getJSONObject(i);
			
			String query_album = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME,  temp.getString("atyId"));
			
			JSONArray jsarray = db.getAlbumUrl(query_album);
			
			temp.put("atyAlbum", jsarray);
			
			if (likeAty.size() > 0) 
			{
				if (likeAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsLiked", "true");
				}
				else 
				{
					temp.put("atyIsLiked", "false");
				}	
			}
			else 
			{
				temp.put("atyIsLiked", "false");
			}
			
			if (joinAty.size() > 0) 
			{
				if (joinAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsJoined", "true");
				}
				else 
				{
					temp.put("atyIsJoined", "false");
				}	
			}
			else 
			{
				temp.put("atyIsJoined", "false");
			}
			
			outJson.set(i, temp);
		}
		
		
		writeJson(resp, outJson.toString());				
	}
	
	private void showComments(HttpServletResponse resp, JSONObject jsobj)
	{
		String atyId = jsobj.getString("atyId");
		String query_sql = String.format("select userName,userIcon,cmtContent,cmtTime " +
				"from %s, %s, %s, %s " +
				"where activity.atyId = evaluation.atyId and user.userId = evaluation.userId and comment.cmtId = evaluation.cmtId and activity.atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.COMMENT_TABLE_NAME, IStringConstans.EVALUATION_TABLE_NAME, IStringConstans.USER_TABLE_NAME, atyId); 

		
		JSONArray outJson = db.queryGetJsonArray(query_sql);		
		
		System.out.println(outJson.toString());
		
		writeJson(resp, outJson.toString());
		
	}
	
	private void comment(HttpServletResponse resp, JSONObject jsobj)
	{
		String atyId = jsobj.getString("atyId");
		String userId = jsobj.getString("userId");
		String cmtId = CreateId.createCommentId(userId);
		String cmtContent = jsobj.getString("cmtContent");
		String cmtTime = jsobj.getString("cmtTime");
		
		String insert_comment = String.format("insert into %s values('%s', '%s', '%s')", IStringConstans.COMMENT_TABLE_NAME, cmtId, cmtContent, cmtTime);
		String insert_evaluation = String.format("insert into %s values('%s', '%s', '%s')", IStringConstans.EVALUATION_TABLE_NAME, userId, atyId, cmtId);
		String update_comments = String.format("update %s " +
											   "set atyComments=atyComments+1 " +
											   "where atyId='%s'",IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		String query_comment = String.format("select userId,userIcon,userName " +
											 "from %s " +
											 "where userId='%s'", IStringConstans.USER_TABLE_NAME, userId);
		
		db.excuteUpdate(insert_comment);
		db.excuteUpdate(insert_evaluation);
		db.excuteUpdate(update_comments);
		
		
		JSONObject out = db.queryGetJsonObj(query_comment);
		out.put("cmtContent", cmtContent);
		out.put("cmtTime", cmtTime);
		
		writeJson(resp, out.toString());
	}

	private void showJoinedAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty =String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic " + 
										"from %s, %s, %s " +
										"where activity.atyId = joining.atyId and user.userId = joining.userId and activity.atyIsBanned=0 and user.userId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.JOIN_TABLE_NAME, userId);		
		
		String queryIsLike =String.format("select atyId " +
				 "from %s " +
				 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 

		String queryIsJoined =String.format("select atyId " +
					"from %s " +
					"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);	
		
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);	
		
		List<String> likeAty = db.getAtyId(queryIsLike);
		List<String> joinAty = db.getAtyId(queryIsJoined);
		
		for (int i = 0; i < outJson.size(); i++) 
		{
			JSONObject temp = outJson.getJSONObject(i);
			
			String query_album = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME,  temp.getString("atyId"));
			
			JSONArray jsarray = db.getAlbumUrl(query_album);
			
			temp.put("atyAlbum", jsarray);
			
			if (likeAty.size() > 0) 
			{
				if (likeAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsLiked", "true");
				}
				else 
				{
					temp.put("atyIsLiked", "false");
				}	
			}
			else 
			{
				temp.put("atyIsLiked", "false");
			}
			
			if (joinAty.size() > 0) 
			{
				if (joinAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsJoined", "true");
				}
				else 
				{
					temp.put("atyIsJoined", "false");
				}	
			}
			else 
			{
				temp.put("atyIsJoined", "false");
			}
			
			outJson.set(i, temp);
		}
		
		System.out.println(outJson.toString());
		writeJson(resp, outJson.toString());		
	}
	
	private void showDistributeAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty =String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic " + 
										"from %s, %s, %s " +
										"where activity.atyId = distribute.atyId and user.userId = distribute.userId and activity.atyIsBanned=0 and user.userId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME, userId);
		
		String queryIsLike =String.format("select atyId " +
				 "from %s " +
				 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 

		String queryDistributed =String.format("select atyId " +
					"from %s " +
					"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);	
		
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);
		List<String> likeAty = db.getAtyId(queryIsLike);
		List<String> joinAty = db.getAtyId(queryDistributed);
		
		for (int i = 0; i < outJson.size(); i++) 
		{
			JSONObject temp = outJson.getJSONObject(i);
			
			String query_album = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME,  temp.getString("atyId"));
			
			JSONArray jsarray = db.getAlbumUrl(query_album);
			
			temp.put("atyAlbum", jsarray);
			
			if (likeAty.size() > 0) 
			{
				if (likeAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsLiked", "true");
				}
				else 
				{
					temp.put("atyIsLiked", "false");
				}	
			}
			else 
			{
				temp.put("atyIsLiked", "false");
			}
			
			if (joinAty.size() > 0) 
			{
				if (joinAty.contains(temp.get("atyId"))) 
				{
					temp.put("atyIsJoined", "true");
				}
				else 
				{
					temp.put("atyIsJoined", "false");
				}	
			}
			else 
			{
				temp.put("atyIsJoined", "false");
			}
			
			outJson.set(i, temp);
		}
		
		writeJson(resp, outJson.toString());	
	}
	
	private void atyMembers(HttpServletResponse resp, JSONObject jsobj)
	{
		String atyId = jsobj.getString("atyId");
		
		String select_sql = String.format("select userName, userIcon " +
										  "from %s, %s " +
										  "where atyId='%s' and joining.userId=user.userId", IStringConstans.JOIN_TABLE_NAME, IStringConstans.USER_TABLE_NAME, atyId);
		
		JSONArray jsArray = db.queryGetJsonArray(select_sql);
		
		writeJson(resp, jsArray.toString());
	}

	private void showCredit(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllCredit = String.format("select creditContent, creditNumbers from %s where userId='%s'", IStringConstans.ADDCREDIT_TABLE_NAME, userId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllCredit);
		System.out.println(outJson.toString());
		writeJson(resp, outJson.toString());
	}
	
	private void sendMessage(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");

		String queryAllMsg = String.format("select * from %s, %s where receive.msgId=message.msgId and userId='%s' and msgIsSend=0", IStringConstans.MESSAGE_TABLE_NAME, IStringConstans.RECEIVE_TABLE_NAME, userId);
				
		JSONArray outJson = db.queryGetJsonArray(queryAllMsg);
		writeJson(resp, outJson.toString());
		
		String query_update = String.format("update %s set msgIsSend=1 where msgId in (select msgId from %s where userId='%s')", IStringConstans.MESSAGE_TABLE_NAME, IStringConstans.RECEIVE_TABLE_NAME, userId);
		db.excuteUpdate(query_update);
	}
	
	private void showMessage(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllMsg = String.format("select * from %s, %s where receive.msgId=message.msgId and userId='%s' and msgIsSend=1", IStringConstans.MESSAGE_TABLE_NAME, IStringConstans.RECEIVE_TABLE_NAME, userId);
	
		JSONArray outJson = db.queryGetJsonArray(queryAllMsg);
		writeJson(resp, outJson.toString());
	}
	
	private void setPublicTrue(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String query_update = String.format("update %s set userAlbumIsPublic=1 where userId='%s'", IStringConstans.USER_TABLE_NAME, userId);
		
		db.excuteUpdate(query_update);
	}
	
	private void setPublicFalse(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String query_update = String.format("update %s set userAlbumIsPublic=0 where userId='%s'", IStringConstans.USER_TABLE_NAME, userId);
		
		db.excuteUpdate(query_update);
	}
	
	private void showMembers(HttpServletResponse resp, JSONObject jsobj)
	{
		String atyId = jsobj.getString("atyId");
		
		String queryAllMembers = String.format("select user.userId, userName, userIcon from %s, %s where user.userId = joining.userId and joining.atyId='%s'", IStringConstans.JOIN_TABLE_NAME, IStringConstans.USER_TABLE_NAME, atyId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllMembers);
		writeJson(resp, outJson.toString());
	}
	
	private void showAllCommunities(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllCty = String.format("select attention.ctyId, ctyIcon,ctyMembers from attention, communnity where userId='%s' and attention.ctyId=communnity.ctyId", userId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllCty);
		writeJson(resp, outJson.toString());
	}
	
	private void showCommunity(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String ctyId = jsobj.getString("ctyId");
		
		String queryCtyDetails = String.format("select * from communnity where ctyId='%s'", ctyId);
		
		JSONObject outJson = new JSONObject(); 
		if(db.query(queryCtyDetails))
		{
			ResultSet rs = db.executeQuery(queryCtyDetails);
			String ctyIcon = null;
			int ctyMembers = 0;
			try
			{
				while(rs.next())
				{
					ctyIcon = rs.getString("ctyIcon");
					ctyMembers = rs.getInt("ctyMembers");
				}
				
				outJson.put("ctyId", ctyId);
				outJson.put("ctyIcon", ctyIcon);
				outJson.put("ctyMembers", ctyMembers);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		String queryAttentionCty = String.format("select * from attention where userId='%s'", userId);
		
		outJson.put("ctyIsAttention", "false");
		try
		{
			ResultSet rs = db.executeQuery(queryAttentionCty);
			while(rs.next())
			{
				if(rs.getString("ctyId").equals(ctyId))
				{
					outJson.put("ctyIsAttention", "true");
					break;
				}
				else
				{
					outJson.put("ctyIsAttention", "false");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		writeJson(resp, outJson.toString());
	}
	
	private void showAtyInCommunity(HttpServletResponse resp, JSONObject jsobj)
	{
		String ctyId = jsobj.getString("ctyId");
		
		String queryAllAty = String.format("select user.userIcon, activity.atyId, activity.atyName,  user.userId, userName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic  "
				+ "from activity, distribute, user "
				+ "where atyType='%s' and distribute.atyId=activity.atyId and distribute.userId=user.userId", ctyId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);
		writeJson(resp, outJson.toString());
	}
	
	private void showMembersInCommunity(HttpServletResponse resp, JSONObject jsobj)
	{
		String ctyId = jsobj.getString("ctyId");
		
		String queryAllMembers = String.format("select user.userId, userName, userIcon from %s, %s where user.userId=attention.userId and ctyId='%s'", IStringConstans.USER_TABLE_NAME, IStringConstans.ATTENTION_TABLE_NAME, ctyId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllMembers);
		writeJson(resp, outJson.toString());
	}
	
	private void joinCty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String ctyId = jsobj.getString("ctyId");
		
		String update_cty = String.format("update %s " +
				"set ctyMembers=ctyMembers+1 " +
				"where ctyId='%s'", IStringConstans.COMMUNITY_TABLE_NAME, ctyId);

		String insert_attention = String.format("insert into %s values('%s', '%s')", IStringConstans.ATTENTION_TABLE_NAME, userId, ctyId);
		
		db.excuteUpdate(update_cty);	

		db.excuteUpdate(insert_attention);
		
		JSONObject out = new JSONObject();
		out.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, out.toString());
	}
	
	private void notJoinCty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String ctyId = jsobj.getString("ctyId");
		
		String update_cty = String.format("update %s " +
				"set ctyMembers=ctyMembers-1 " +
				"where ctyId='%s'", IStringConstans.COMMUNITY_TABLE_NAME, ctyId);
		
		String delete_attention = String.format("delete from %s where ctyId='%s' and userId='%s'", IStringConstans.ATTENTION_TABLE_NAME, ctyId, userId);
		
		db.excuteUpdate(update_cty);
		db.excuteUpdate(delete_attention);
		
		JSONObject out = new JSONObject();
		out.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, out.toString());
	}
	
	private void creditRank(HttpServletResponse resp, JSONObject jsobj)
	{
		String queryCredit = String.format("select userId, userName, userIcon from %s order by userCredit", IStringConstans.USER_TABLE_NAME);
		
		JSONArray outJson = db.queryGetJsonArray(queryCredit);
		writeJson(resp, outJson.toString());
	}
	
	private void test(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		
		String c = jsobj.getString("char");
		String pic = jsobj.getString("pic");
		IconAndUrl icon2url = new IconAndUrl();
		String path = icon2url.getUrl(IMAGE_PATH, pic);
		
//		System.out.println("image path:" + path);
//		System.out.println("汉字:" + c);
		
		outJson.put("result", "success");
		writeJson(resp, outJson.toString());
	}
	
	private void editAlbumRight(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String albumIsPublic = jsobj.getString("userAlbumIsPublic");
		
		String sql = String.format("update %s set userAlbumIsPublic='%s' where userId='%s'", IStringConstans.USER_TABLE_NAME, albumIsPublic, userId);
		db.excuteUpdate(sql);
		
		
		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, outJson.toString());
		
	}
	
	private void showUserAlbum(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String query_sql = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME, userId);
		
		ResultSet rs = db.executeQuery(query_sql);
		
		JSONArray jsarray = new JSONArray();
		try {
			while (rs.next()) 
			{
				jsarray.add(rs.getString("photoId"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writeJson(resp, jsarray.toString());
	}
}
