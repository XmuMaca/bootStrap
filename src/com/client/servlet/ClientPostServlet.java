package com.client.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easemob.server.example.httpclient.apidemo.EasemobChatGroups;
import com.easemob.server.example.httpclient.apidemo.EasemobIMUsers;
import com.easemob.server.example.httpclient.apidemo.EasemobMessages;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.server.bean.Account;
import com.server.bean.Activity;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;
import com.server.util.CreateId;
import com.server.util.GzipHelper;
import com.server.util.IconAndUrl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ClientPostServlet extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		JSONObject jsobj;
		
		if(!IStringConstans.DEBUG)
		{
			jsobj = testJson();
		}
		else
		{
			jsobj = readJson(req);
		}
		System.out.println("jsobj: " + jsobj);
		
		String action = jsobj.getString("action");
				
		switch (action) {
		case "userChat":
			userChat(resp, jsobj);
			break;
		case "sendMessage":
			sendMsg(resp, jsobj);
			break;
		case "showChatlist":
			showChatlist(resp, jsobj);
			break;
		case "deleteOneChat":
			deleteOneChat(resp, jsobj);
			break;
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
		case "releaseByPerson":
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
		case "showLatestAty":
			showLatestAty(resp, jsobj);
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
		case "showGroupAty":
			showGroupAty(resp, jsobj);
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
		case "showJoinedCommunities":
			showAllCommunities(resp, jsobj);
			break;
		case "showOwnedCommunities":
			showOwnedCommunities(resp, jsobj);
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
			break;
		case "showUserAlbum":
			showUserAlbum(resp, jsobj);
			break;
		case "easemobMsg":
			easemobMsg(resp, jsobj);
			break;
		case "notifyFromAty":
			sendInfo(resp, jsobj);
			break;
		case "showAllNotifications":
			showAllNotifications(resp, jsobj);
			break;
		case "editAty":
			editAty(resp, jsobj);
			break;
		case "deleteAty":
			deleteAty(resp, jsobj);
			break;
		case "showUserComments":
			showUserComments(resp, jsobj);
			break;
		case "createCommunity":
			createCommunity(resp, jsobj);
			break;
		case "releaseByCty":
			releaseByCty(resp, jsobj);
			
			break;
		case "editCommunity":
			editCommunity(resp, jsobj);
			break;
		case "showHotCommunities":
			showHotCty(resp, jsobj);
			break;
		default:
			break;
		}
		
		db.close();
	}
	
	private JSONObject testJson()
	{		
		JSONObject jsonObject = new JSONObject();
		/*
		 * test the createCommunity	*/
		jsonObject.put("action", "createCommunity");
		jsonObject.put("userId", "cc@qq.com");
		jsonObject.put("ctyName", "a community name");
		jsonObject.put("ctyType", "a community type");
		jsonObject.put("ctyIntro", "cc@qq.com");
		jsonObject.put("ctyIcon", "");
	
		
		/*
		 * test the releasebycty
		*/
		/*
		JSONArray album = new JSONArray();
		jsonObject.put("action", "releaseByCty");
		jsonObject.put("userId", "cc@qq.com");
		jsonObject.put("ctyId", "a community name20151016203152");
		jsonObject.put("releaseTime", "Its release time");
		jsonObject.put("atyName", "activity name");
		jsonObject.put("atyType", "activity type");
		jsonObject.put("atyStartTime", "activity start time");
		jsonObject.put("atyEndTime", "activity end time");
		jsonObject.put("atyPlace", "activity place");
		jsonObject.put("easemobId", "t1007105840");
		jsonObject.put("longitude", "10");
		jsonObject.put("latitude", "10");
		jsonObject.put("atyMembers", "0");
		jsonObject.put("atyContent", "activity content");
		jsonObject.put("atyShares", "0");
		jsonObject.put("atyComments", "0");
		jsonObject.put("atyAlbum", album.toString());
		jsonObject.put("atyIsPublic", "toVisitors");
		*/
		
		/*
		 * test the editCommunity
		 * */
//		jsonObject.put("action", "editCommunity");
//		jsonObject.put("ctyId", "a community name20151016203043");
//		jsonObject.put("ctyIntro", "the changed community introduction");
		
		return jsonObject;
	}
	
	private JSONObject readJson(HttpServletRequest req)
	{
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(req.getInputStream());
			
			Object obj = ois.readObject();
			
//			String decompressReq = GzipHelper.decompress((String)obj);
//			JSONObject jsobj = JSONObject.fromObject(decompressReq);
	
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
			//String compress_resp = GzipHelper.compress(json);
			
			oos = new ObjectOutputStream(resp.getOutputStream());
			
			//oos.writeObject(compress_resp);
			oos.writeObject(json);
						
			oos.flush();
			oos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void easemobMsg(HttpServletResponse resp, JSONObject json)
	{
//		EasemobMessages.mySendMsg();
		System.out.println("IM signup start");
		EasemobIMUsers xx = new EasemobIMUsers();
		xx.mysignup();
		System.out.println("IM signup succesful");
	}
	
	private void sendMsg(HttpServletResponse resp, JSONObject json)
	{
		String easemobId = json.getString("easemobId");
		String toEasemobId = json.getString("toEasemobId");
		String fromUserId = json.getString("userId");
		String msgContent = json.getString("msgContent");
		String fromUserName = "";
		String fromUserIcon = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sendTime = sdf.format(new Date()).toString();
		
		//第一次更新
		String userIcon_sql = String.format("select userName,userIcon from user where userId = '%s'", fromUserId);
		ResultSet rs = db.executeQuery(userIcon_sql);
		
		try {
			if(rs.next())
			{
				fromUserName = rs.getString("userName");
				fromUserIcon = rs.getString("userIcon");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String chatlist_sql = String.format("select newMsgCount from chatlist where fromEasemobId = '%s' and toEasemobId = '%s'", easemobId, toEasemobId);
		rs = db.executeQuery(chatlist_sql);
		
		int newMsgCount = 0;
		
		try {
			if(rs.next())
			{
				String update_sql = String.format("update chatlist set msgContent = '%s', newMsgCount = newMsgCount+1, sendTime = '%s' " +
						"where fromEasemobId = '%s' and toEasemobId = '%s'", msgContent, sendTime, easemobId, toEasemobId);
				db.excuteUpdate(update_sql);
				
				newMsgCount = rs.getInt("newMsgCount");
			}
			else
			{
				String insert_sql = String.format("insert into chatlist values('%s', '%s', '%s', '%s', '%s', '%s', %d, '%s')", easemobId, toEasemobId, fromUserId, fromUserIcon, fromUserName, msgContent, 1, sendTime);
				db.excuteUpdate(insert_sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//第二次更新
		String userIcon_sql2 = String.format("select userName,userIcon from user where easemobId = '%s'", toEasemobId);
		rs = db.executeQuery(userIcon_sql2);
		
		try {
			if(rs.next())
			{
				fromUserName = rs.getString("userName");
				fromUserIcon = rs.getString("userIcon");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String chatlist_sql2 = String.format("select newMsgCount from chatlist where fromEasemobId = '%s' and toEasemobId = '%s'", toEasemobId, easemobId);
		rs = db.executeQuery(chatlist_sql2);
		
		try {
			if(rs.next())
			{
				String update_sql = String.format("update chatlist set msgContent = '%s', sendTime = '%s' " +
						"where fromEasemobId = '%s' and toEasemobId = '%s'", msgContent, sendTime, toEasemobId, easemobId);
				db.excuteUpdate(update_sql);
			}
			else
			{
				String insert_sql = String.format("insert into chatlist values('%s', '%s', '%s', '%s', '%s', '%s', %d, '%s')", toEasemobId, easemobId, fromUserId, fromUserIcon, fromUserName, msgContent, 1, sendTime);
				db.excuteUpdate(insert_sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject outJson = new JSONObject();
		
		outJson.put("newMsgCount", newMsgCount+1);
		
		writeJson(resp, outJson.toString());
	}
	
	private void showChatlist(HttpServletResponse resp, JSONObject json)
	{
		String toEasemobId = json.getString("easemobId");
		
		String queryChatlist = String.format("select * from chatlist where toEasemobId = '%s'", toEasemobId);
		
		JSONArray outJson = db.chatListQueryGetJsonArrayWithTime(queryChatlist, "sendTime");
		writeJson(resp, outJson.toString());
	}
	
	private void deleteOneChat(HttpServletResponse resp, JSONObject json)
	{
		String toEasemobId = json.getString("toEasemobId");
		String fromEasemobId = json.getString("fromEasemobId");
		
		String delete_sql = String.format("delete from chatlist where fromEasemobId='%s' and toEasemobId='%s'", fromEasemobId, toEasemobId);
		db.excuteUpdate(delete_sql);
	}
	
	private void userChat(HttpServletResponse resp, JSONObject json)
	{
		String toEasemobId = json.getString("toEasemobId");
		String fromEasemobId = json.getString("fromEasemobId");
				
		System.out.println("easemobId : " + toEasemobId);
		
		String query_sql = String.format("select * from %s where easemobId='%s'", IStringConstans.USER_TABLE_NAME, toEasemobId);
		
		JSONObject outJson = new JSONObject();
		
		List<Account> list = db.queryResult(query_sql);
		Account account = list.get(0);
		
		outJson.put("userId", account.getId());
		outJson.put("userName", account.getName());
		outJson.put("userIcon", account.getIcon());
		
		writeJson(resp, outJson.toString());
		
		//清空未读消息
		String update_sql = String.format("update chatlist set newMsgCount = 0 where fromEasemobId = '%s' and toEasemobId = '%s'", toEasemobId, fromEasemobId);
		db.excuteUpdate(update_sql);
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
		outJson.put("easemobId", account.getEasemobId());
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
				outJSon.put("easemobId", accout.getEasemobId());
				System.out.println(accout.getEasemobId());
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
				outJSon.put("easemobId", accout.getEasemobId());
				System.out.println(accout.getEasemobId());
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
				outJSon.put("easemobId", accout.getEasemobId());
				System.out.println("huanxin:" + accout.getEasemobId());
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
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("MMddHHmm");
		String time=format.format(date);
		
		Random ra = new Random();
		String easemobId ="t" + time + Integer.toString(ra.nextInt(100));
		
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
//			IconAndUrl icon2url = new IconAndUrl();
//			String iconurl = IconAndUrl.getUrl(IMAGE_PATH, iconData) 
			account.setIcon(IconAndUrl.getUrl(IMAGE_PATH, iconData));
//			IconAndUrl.writeIcon(iconurl, iconData);
			
			String signup_sql = String.format("insert into %s(userId,easemobId,userName,userPassword,userIcon,userGender,userEmail,userPhone, userAlbumIsPublic) values('%s','%s','%s','%s','%s','%s','%s','%s', '%s')", IStringConstans.USER_TABLE_NAME, account.getId(),easemobId, account.getName(), account.getPassword(),account.getIcon(), account.getGender(), account.getEmail(), account.getPhone(), isPublic);
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
			
			outJSon.put("result", easemobId);
			
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
		
		String userId = jsobj.getString("userId");
		account.setId(userId);
		String query_sql = String.format("select * from %s where userId='%s'", IStringConstans.USER_TABLE_NAME, account.getId());
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("MMddHHmm");
		String time=format.format(date);
		
		Random ra = new Random();
		String easemobId ="t" + time + Integer.toString(ra.nextInt(100));
		
		
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
			
			String signup_sql = String.format("insert into %s(userId,easemobId,userName,userPassword,userIcon,userGender,userEmail,userPhone, userAlbumIsPublic) values('%s','%s','%s','%s','%s','%s','%s','%s', '%s')", IStringConstans.USER_TABLE_NAME, account.getId(),easemobId, account.getName(), account.getPassword(),account.getIcon(), account.getGender(), account.getEmail(), account.getPhone(), isPublic);
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
			
			
			outJSon.put("result", easemobId);
			
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
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("MMddHHmm");
		String time=format.format(date);
		
		Random ra = new Random();
		String easemobId ="t" + time + Integer.toString(ra.nextInt(100));
		
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
			
			String signup_sql = String.format("insert into %s(userId,easemobId,userName,userPassword,userIcon,userGender,userEmail,userPhone, userAlbumIsPublic) values('%s','%s','%s','%s','%s','%s','%s','%s', '%s')", IStringConstans.USER_TABLE_NAME, account.getId(),easemobId, account.getName(), account.getPassword(),account.getIcon(), account.getGender(), account.getEmail(), account.getPhone(), isPublic);
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
			
			outJSon.put("result", easemobId);
			
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String releaseTime = sdf.format(new Date());
		
		String atyId = CreateId.createAtyId(userId);
		activity.setId(atyId);
		System.out.println(activity.getId());
		activity.setName(jsobj.getString("atyName"));
		System.out.println(activity.getName());
		activity.setType(jsobj.getString("atyType"));
		System.out.println(activity.getType());
		activity.setStartTime(jsobj.getString("atyStartTime"));
		System.out.println(activity.getStartTime());
		activity.setEndTime(jsobj.getString("atyEndTime"));
		System.out.println(activity.getEndTime());
		activity.setPlace(jsobj.getString("atyPlace"));
		System.out.println(activity.getPlace());
		activity.setLongitude(0);
		System.out.println(activity.getLongitude());
		activity.setLatitude(0);
		System.out.println(activity.getLatitude());
		activity.setMembers(Integer.parseInt(jsobj.getString("atyMembers")));
		System.out.println(activity.getMembers());
		activity.setContent(jsobj.getString("atyContent"));
		System.out.println(activity.getContent());
		activity.setShares(Integer.parseInt(jsobj.getString("atyShares")));
		System.out.println(activity.getShares());
		activity.setComments(Integer.parseInt(jsobj.getString("atyComments")));
		System.out.println(activity.getComments());
		
		String atyAlbumStr = jsobj.getString("atyAlbum");
		String atyIsPublic = jsobj.getString("atyIsPublic");
		String groupId = "";
		
		/*insert all pictures*/
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(atyAlbumStr);
		
		String albumId_aty = activity.getId();
		String albumId_user = userId;
		for (int i = 0; i < jsonArray.size(); i++) 
		{
//			IconAndUrl icon2url = new IconAndUrl();
			String picurl = IconAndUrl.getUrl(IMAGE_PATH, jsonArray.getString(i), i);
			String insert_pics_sql = String.format("insert into %s values('%s', '%s')", IStringConstans.PHOTOS_TABLE_NAME, picurl, albumId_aty);
			String insert_pics_sql2 = String.format("insert into %s values('%s', '%s')", IStringConstans.PHOTOS_TABLE_NAME, picurl, albumId_user);
			db.excuteUpdate(insert_pics_sql);
			db.excuteUpdate(insert_pics_sql2);
		}
		
		String ctyId = "";
		String insert_sql1 = String.format("insert into %s values('%s', '%s', '%s', '%s')", IStringConstans.DISTRIBUTE_TABLE_NAME, userId, activity.getId(), ctyId, releaseTime);
		String insert_sql2 = String.format("insert into %s(atyId, atyName, atyType, atyStartTime, atyEndTime, atyPlace, atyLongitude, atyLatitude, atyMembers, atyContent, atyShares, atyIsPublic, groupId) values('%s', '%s', '%s', '%s', '%s', '%s', %f, %f, '%s', '%s', '%s', '%s', '%s')", IStringConstans.ACTIVITY_TABLE_NAME, activity.getId(), activity.getName(),activity.getType(), activity.getStartTime(), activity.getEndTime(), activity.getPlace(), activity.getLongitude(), activity.getLatitude(), activity.getMembers(), activity.getContent(), activity.getShares(), atyIsPublic, groupId);
		String joint_sql = String.format("insert into %s values('%s', '%s')", IStringConstans.JOIN_TABLE_NAME, userId, activity.getId());
		
		db.excuteUpdate(insert_sql1);
		db.excuteUpdate(insert_sql2);
		db.excuteUpdate(joint_sql);
		
		//group
		groupId = createTempGroup(jsobj.getString("easemobId"), atyId);
		
		String update_group = String.format("update %s set groupId = %s where atyId = '%s'", IStringConstans.ACTIVITY_TABLE_NAME, groupId, atyId);
		db.excuteUpdate(update_group);
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String creditContent = "发布活动：" + jsobj.getString("atyName");
		int creditNumbers = 20;
		
		String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
		db.excuteUpdate(insert_credit);
		
		String update_credit = String.format("update %s set userCredit = userCredit + %d where userId = '%s'", IStringConstans.USER_TABLE_NAME, creditNumbers, userId);
		db.excuteUpdate(update_credit);
		
		outJson.put("atyId", activity.getId());
		outJson.put("groupId", groupId);
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
		String userName = jsobj.getString("userName");
		String atyId = jsobj.getString("atyId");
		String easemobId = jsobj.getString("easemobId");
		
		String update_aty = String.format("update %s " +
										"set atyMembers=atyMembers+1 " +
										"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		
		
		String insert_like = String.format("insert into %s values('%s', '%s')", IStringConstans.JOIN_TABLE_NAME, userId, atyId);
		db.excuteUpdate(update_aty);	
		
		db.excuteUpdate(insert_like);
		
		//group
		String query_group = String.format("select groupId from activity where atyId = '%s'", atyId);
		ResultSet rs = db.executeQuery(query_group);
		
		String groupId = null;
		try {
			while(rs.next())
			{
				groupId = rs.getString("groupId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		addMember(easemobId, groupId);
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String atyName = jsobj.getString("atyName");
		String creditContent = "参加活动：" + atyName;
		int creditNumbers = 5;
		
		String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);				
		db.excuteUpdate(insert_credit);
		
		String update_credit = String.format("update %s set userCredit = userCredit + %d where userId = '%s'", IStringConstans.USER_TABLE_NAME, creditNumbers, userId);
		db.excuteUpdate(update_credit);

		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, outJson.toString());
		
		String toEasemobId = "";
		//sendMessage
		String query_sql = String.format("select easemobId from user, distribute where distribute.userId = user.userId and atyId = '%s'", atyId);
		rs = db.executeQuery(query_sql);
		try {
			while(rs.next())
			{
				toEasemobId = rs.getString("easemobId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		EasemobMessages.mySendMsgJoin(easemobId, toEasemobId, userName + "参加了您的活动：" + atyName, atyName);
	}
	
	private void notJoin(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String userName = jsobj.getString("userName");
		String atyId = jsobj.getString("atyId");
		String easemobId = jsobj.getString("easemobId");
		
		String update_aty = String.format("update %s " +
				"set atyMembers=atyMembers-1 " +
				"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		String delete_aty = String.format("delete from %s where atyId='%s' and userId='%s'", IStringConstans.JOIN_TABLE_NAME, atyId, userId);
		
		db.excuteUpdate(update_aty);
		db.excuteUpdate(delete_aty);
		
		//group
		String query_group = String.format("select groupId from activity where atyId = '%s'", atyId);
		ResultSet rs = db.executeQuery(query_group);
				
		String groupId = null;
		try {
			while(rs.next())
			{
				groupId = rs.getString("groupId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		deleteMember(easemobId, groupId);		
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String atyName = jsobj.getString("atyName");
		String creditContent = "退出活动：" + atyName;
		int creditNumbers = -5;
				
		String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
		db.excuteUpdate(insert_credit);

		String update_credit = String.format("update %s set userCredit = userCredit + %d where userId = '%s'", IStringConstans.USER_TABLE_NAME, creditNumbers, userId);
		db.excuteUpdate(update_credit);
		
		JSONObject outJson = new JSONObject();
		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, outJson.toString());
		
		String toEasemobId = "";
		//sendMessage
		String query_sql = String.format("select easemobId from user, distribute where distribute.userId = user.userId and atyId = '%s'", atyId);
		rs = db.executeQuery(query_sql);
		try {
			while(rs.next())
			{
				toEasemobId = rs.getString("easemobId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		EasemobMessages.mySendMsgJoin(easemobId, toEasemobId, userName + "退出了您的活动：" + atyName, atyName);
	}
	
	private void showActivities(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowTime = sdf.format(new Date());
		
		System.out.println("nowTime: " + nowTime);
		
		String queryAllAty = String.format("select user.userId, userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic, releaseTime, ctyId " +
				"from activity, user, distribute " +
				"where activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1) and atyEndTime > '%s' " +
				"order by distribute.releaseTime", nowTime);
			
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
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
	
	private void showLatestAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowTime = sdf.format(new Date());
		
		String queryAllAty = String.format("select user.userId, userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic, releaseTime, ctyId " +
				"from activity, user, distribute " +
				"where activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1) and atyEndTime > '%s' " +
				"order by activity.atyStartTime desc", nowTime);
		
		/*String queryAllAty = "select user.userId, userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic " +
				"from activity, user, distribute, distributebycty " +
				"where (activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1)) " +
				"or (activity.atyId = distributebycty.atyId and cty.ctyId = distributebycty.ctyId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1))";
*/		
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
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
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments ,atyIsPublic, releaseTime, ctyId " +
											"from %s, %s, %s " +
											"where activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1)" +
											"order by atyMembers*2+atyLikes desc", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME );
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
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
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic, releaseTime, ctyId " +
											"from %s, %s, %s " +
											"where activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1)"
											+ "and atyLongitude>%f-1 and atyLongitude<%f+1 "
											+ "and atyLatitude>%f-1 and atyLatitude<%f+1 ", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME, userLongitude, userLongitude, userLatitude, userLatitude);
				
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
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
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments,atyIsPublic, releaseTime, ctyId " +
											"from %s, %s, %s " +
											"where activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1)" +
											"order by atyLikes desc", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME );
		
		String queryIsLike =String.format("select atyId " +
										 "from %s " +
										 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 
		
		String queryIsJoined =String.format("select atyId " +
											"from %s " +
											"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);				
		
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
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

		
		JSONArray outJson = db.commentsQueryGetJsonArrayWithTime(query_sql, "cmtTime");		
		
		System.out.println(outJson.toString());
		
		writeJson(resp, outJson.toString());
		
	}
	
	private void comment(HttpServletResponse resp, JSONObject jsobj)
	{
		String atyId = jsobj.getString("atyId");
		String userName = jsobj.getString("userName");
		String userId = jsobj.getString("userId");
		String easemobId = jsobj.getString("easemobId");
		String cmtId = CreateId.createCommentId(userId);
		String cmtContent = jsobj.getString("cmtContent");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cmtTime = sdf.format(new Date());
		
		
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
		
		String query_easemobId = String.format("select easemobId from distribute,user where atyId = '%s' and user.userId = distribute.userId", atyId);
		ResultSet rs = db.executeQuery(query_easemobId);
		
		String ToEasemobId = null;
		try {
			while(rs.next())
			{
				ToEasemobId = rs.getString("easemobId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("groupId : " + ToEasemobId);
		
		EasemobMessages.mySendMsgComment(easemobId, ToEasemobId, cmtContent, userName);
		
		JSONObject out = db.queryGetJsonObj(query_comment);
		out.put("cmtContent", cmtContent);
		out.put("cmtTime", "moments ago");
		
		writeJson(resp, out.toString());
	}

	private void showJoinedAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty =String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic, releaseTime, ctyId " + 
										"from %s, %s, %s, %s " +
										"where distribute.atyId = activity.atyId and activity.atyId = joining.atyId and user.userId = joining.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1) and user.userId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.JOIN_TABLE_NAME, "distribute", userId);		
		
		String queryIsLike =String.format("select atyId " +
				 "from %s " +
				 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 

		String queryIsJoined =String.format("select atyId " +
					"from %s " +
					"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);	
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
		
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
		
		String queryAllAty =String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic, releaseTime, ctyId " + 
										"from %s, %s, %s " +
										"where activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1) and user.userId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME, userId);
		
		String queryIsLike =String.format("select atyId " +
				 "from %s " +
				 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 

		String queryDistributed =String.format("select atyId " +
					"from %s " +
					"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);	
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
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
	
	private void showGroupAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String ctyId = jsobj.getString("ctyId");
		
		String queryAllAty =String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments, atyIsPublic, releaseTime, ctyId " + 
										"from %s, %s, %s " +
										"where activity.atyId = distribute.atyId and user.userId = distribute.userId and (activity.atyIsBanned=0 or activity.atyIsBanned=-1) and distribute.ctyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME, ctyId);
		
		String queryIsLike =String.format("select atyId " +
				 "from %s " +
				 "where userId='%s'", IStringConstans.LIKE_TABLE_NAME, userId); 

		String queryDistributed =String.format("select atyId " +
					"from %s " +
					"where userId='%s'", IStringConstans.JOIN_TABLE_NAME, userId);	
		
		JSONArray outJson = db.AtyQueryGetJsonArrayWithTime(queryAllAty, "atyStartTime");
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
	
	/*展示userId所参与的所有社区*/
	private void showAllCommunities(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllCty = String.format("select communnity.ctyId, ctyName, ctyIcon,ctyMembers, user.userName, user.userIcon from attention, communnity, user where user.userId='%s' and user.userId = ctyCreatorId and attention.ctyId=communnity.ctyId", userId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllCty);
		writeJson(resp, outJson.toString());
	}
	
	/*展示userId所创建的所有社区*/
	private void showOwnedCommunities(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllCty = String.format("select ctyId, ctyName, ctyIcon, ctyMembers, userName, userIcon from communnity, user where ctyCreatorId='%s' and userId = ctyCreatorId", userId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllCty);
		writeJson(resp, outJson.toString());
	}
	
	/*展示一个社区的详细信息*/
	private void showCommunity(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String ctyId = jsobj.getString("ctyId");
		
		/*获得社区的详细信息
		 * */
		String queryCtyDetails = String.format("select * from communnity where ctyId='%s'", ctyId);
		
		JSONObject outJson = new JSONObject(); 
		if(db.query(queryCtyDetails))
		{
			ResultSet rs = db.executeQuery(queryCtyDetails);
			String ctyIcon = null;
			String ctyName = null;
			String ctyType = null;
			String ctyIntro = null;
			int ctyMembers = 0;
			try
			{
				while(rs.next())
				{
					ctyIcon = rs.getString("ctyIcon");
					ctyMembers = rs.getInt("ctyMembers");
					ctyName = rs.getString("ctyName");
					ctyType = rs.getString("ctyType");
					ctyIntro = rs.getString("ctyIntro");
					
				}
				
				outJson.put("ctyId", ctyId);
				outJson.put("ctyIcon", ctyIcon);
				outJson.put("ctyMembers", ctyMembers);
				outJson.put("ctyName", ctyName);
				outJson.put("ctyType", ctyType);
				outJson.put("ctyIntro", ctyIntro);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		/*获得创建者的Name*/
		String queryCreatorId = String.format("select userId from user, communnity where userId = '%s' and userId = ctyCreatorId", userId);
		String creatorId = db.getOneColValue(queryCreatorId, "userId");
		String queryCreatorName = String.format("select userName from user, communnity where userId = '%s' and userId = ctyCreatorId", userId);
		String userName = db.getOneColValue(queryCreatorName, "userName");
		outJson.put("creatorId", creatorId);
		outJson.put("creatorName", userName);
		
		/*判断点开此小组的人是否订阅了此小组
		 * true - 已订阅
		 * false - 未订阅*/
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
	
	/*加入社区*/
	private void joinCty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String ctyId = jsobj.getString("ctyId");
		
		/*community's credit plus 2 if the member number of community plus one*/
		String update_cty = String.format("update %s " +
				"set ctyMembers=ctyMembers+1" +
				"where ctyId='%s'", IStringConstans.COMMUNITY_TABLE_NAME, ctyId);

		String insert_attention = String.format("insert into %s values('%s', '%s')", IStringConstans.ATTENTION_TABLE_NAME, userId, ctyId);
		
		db.excuteUpdate(update_cty);	
		db.excuteUpdate(insert_attention);
		
		
		JSONObject out = new JSONObject();
		out.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
		writeJson(resp, out.toString());
	}
	
	/*退出社区*/
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
		String queryCredit = String.format("select userId, userName, userIcon from %s where userId!='000' and userId!='001' order by userCredit desc", IStringConstans.USER_TABLE_NAME);
		
		JSONArray outJson = db.queryGetJsonArray(queryCredit);
		writeJson(resp, outJson.toString());
	}
	
	private void test(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		
		String c = jsobj.getString("char");
		String pic = jsobj.getString("pic");
//		IconAndUrl icon2url = new IconAndUrl();
		String path = IconAndUrl.getUrl(IMAGE_PATH, pic);
		
//		System.out.println("image path:" + path);
//		System.out.println("����:" + c);
		
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
		
		System.out.println(jsarray.toString());
		writeJson(resp, jsarray.toString());
	}
	
	//new functions
	private String createTempGroup(String easemobId, String atyId)
	{
		ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
		dataObjectNode.put("groupname", atyId);
		dataObjectNode.put("desc", atyId);
		dataObjectNode.put("approval", true);
		dataObjectNode.put("public", true);
		dataObjectNode.put("maxusers", 100);
		dataObjectNode.put("owner", easemobId);
		ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
		arrayNode.add(easemobId);
		dataObjectNode.put("members", arrayNode);
		ObjectNode creatChatGroupNode = EasemobChatGroups.creatChatGroups(dataObjectNode);
		
//		String update_group = String.format("update %s set groupId = %s where atyId = '%s'", IStringConstans.ACTIVITY_TABLE_NAME, creatChatGroupNode.get("data").get("groupid").toString(), atyId);
//		db.excuteUpdate(update_group);
		
		System.out.println(creatChatGroupNode.toString());
		System.out.println(creatChatGroupNode.get("data").get("groupid").toString());
		
		return creatChatGroupNode.get("data").get("groupid").toString();
	}
	
	private static void addMember(String easemobId, String groupId)
	{
		String addToChatgroupid = groupId;
		String toAddUsername = easemobId;
		ObjectNode addUserToGroupNode = EasemobChatGroups.addUserToGroup(addToChatgroupid, toAddUsername);
		System.out.println(addUserToGroupNode.toString());
	}
	
	private static void deleteMember(String easemobId, String groupId)
	{
		String delFromChatgroupid = groupId;
		String toRemoveUsername = easemobId;
		ObjectNode deleteUserFromGroupNode = EasemobChatGroups.deleteUserFromGroup(delFromChatgroupid, toRemoveUsername);
		System.out.println(deleteUserFromGroupNode.asText());
	}
	
	private static ObjectNode getAllMembers(String groupId)
	{
		String chatgroupid = groupId;
		ObjectNode getAllMemberssByGroupIdNode = EasemobChatGroups.getAllMemberssByGroupId(chatgroupid);
		System.out.println(getAllMemberssByGroupIdNode.toString());
		return getAllMemberssByGroupIdNode;
	}
	
	private void sendInfo(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		String msgContent = jsobj.getString("msgContent");
		String easemobId = jsobj.getString("easemobId");
		String userName = jsobj.getString("userName");
		String userIcon = jsobj.getString("userIcon");
		String atyName = jsobj.getString("atyName");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String releaseTime = sdf.format(new Date());
		System.out.println("easemobId : " + easemobId);
		
		
		String query_group = String.format("select groupId from activity where atyId = '%s'", atyId);
		ResultSet rs = db.executeQuery(query_group);
		
		String groupId = null;
		try {
			while(rs.next())
			{
				groupId = rs.getString("groupId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("groupId : " + groupId);
		
		ObjectNode allMembers = getAllMembers(groupId);
		
		int members = 0;
		String query_sql = String.format("select atyMembers from activity where atyId = '%s'", atyId);
	    rs = db.executeQuery(query_sql);
	    try {
			while(rs.next())
			{
				members = Integer.parseInt(rs.getString("atyMembers"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EasemobMessages.mySendMsg(easemobId, userName, userIcon, atyId, atyName, msgContent, releaseTime, allMembers, members);
		//EasemobMessages.mySendInfo(easemobId, atyId, msgContent, groupId);
	}
	
	private void showAllNotifications(HttpServletResponse resp, JSONObject jsobj)
	{
		String userToId = jsobj.getString("easemobId");
		
		String queryAllnotifications = String.format("select * from notification where userToId = '%s'", userToId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllnotifications);
		
		writeJson(resp, outJson.toString()); 
	}
	
	/*活动发布者对活动内容编辑，并通知所有活动成员*/
	private void editAty(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		
		String atyId = jsobj.getString("atyId");
		String atyName = jsobj.getString("atyName");
		String atyType = jsobj.getString("atyType");
		String atyStartTime = jsobj.getString("atyStartTime");
		String atyEndTime = jsobj.getString("atyEndTime");
		String atyPlace = jsobj.getString("atyPlace");
		String atyContent = jsobj.getString("atyContent");
		
		String update = String.format("update activity "
									+ "set atyName='%s', atyType='%s', atyStartTime='%s', atyEndTime='%s', atyPlace='%s', atyContent='%s' "
									+ "where atyId='%s'", atyName, atyType, atyStartTime, atyEndTime, atyPlace, atyContent, atyId);
		db.excuteUpdate(update);
		
		String query = String.format("select atyStartTime, atyEndTime, atyPlace, groupId, atyMembers "
									+ "from activity "
									+ "where atyId='%s'", atyId);				
		
		ObjectNode allMembers = null;
		int members = 0;
		
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next())
			{
				String startTime = rs.getString("atyStartTime");
				String endTime = rs.getString("atyEndTime");
				String place= rs.getString("atyPlace");
				String groupId = rs.getString("groupId");
				members = Integer.parseInt(rs.getString("atyMembers"));
				
				allMembers = getAllMembers(groupId);
				
				if (startTime == atyStartTime) {
					outJson.put("atyStartTime", "null");
				}
				else
				{
					outJson.put("atyStartTime", atyStartTime);
				}
				
				if (endTime == atyEndTime) {
					outJson.put("atyEndTime", "null");
				}
				else
				{
					outJson.put("atyEndTime", atyEndTime);
				}
				
				if (place == atyPlace) {
					outJson.put("atyPlace", "null");
				}
				else
				{
					outJson.put("atyPlace", atyPlace);
				}
				
				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		outJson.put("atyName", atyName);
		
		EasemobMessages.myEditAtyMsg(IStringConstans.SYSTEM_EASEMOB_ID, outJson.toString(), members, allMembers);
//		writeJson(resp, outJson.toString());
	}
	
	/*删除指定的活动*/
	private void deleteAty(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		
		String atyId = jsobj.getString("atyId");
		
		String deleAtAty = "delete from activity where atyId=" + atyId;
		String deleAtJoining = "delete from joining where atyId=" + atyId;
		String deleAtDistribute = "delete from distribute where atyId=" + atyId;
		
		db.excuteUpdate(deleAtAty);
		db.excuteUpdate(deleAtJoining);
		db.excuteUpdate(deleAtDistribute);
		
		
		String query = String.format("select atyName, groupId, atyMembers "
				+ "from activity "
				+ "where atyId='%s'", atyId);				

		ObjectNode allMembers = null;
		int members = 0;
		
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next())
			{
				String atyName = rs.getString("atyName");
				String groupId = rs.getString("groupId");
				members = Integer.parseInt(rs.getString("atyMembers"));
			
				allMembers = getAllMembers(groupId);	
								
				outJson.put("atyName", atyName);
			}			
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		EasemobMessages.myEditAtyMsg(IStringConstans.SYSTEM_EASEMOB_ID, outJson.toString(), members, allMembers);		
//		JSONObject outJson = new JSONObject();
//		outJson.put(IStringConstans.JSON_RESULT, IStringConstans.JSON_OK);
//		writeJson(resp, outJson.toString());
	}

	/*创建社区*/
	private void createCommunity(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String ctyName = jsobj.getString("ctyName");
		String ctyType = jsobj.getString("ctyType");
		String ctyIntro = jsobj.getString("ctyIntro");
		String ctyId = CreateId.createCtyId(ctyName);
		
		String iconData = jsobj.getString("ctyIcon");		
//		IconAndUrl icon2url = new IconAndUrl();
		String iconURL = IconAndUrl.getUrl(IMAGE_PATH, iconData);
		
//		System.out.printf("userId:", userId);
		
		String easeidColName = "easemobId";
		String getUserEaseIdSql = String.format("select easemobId "
											  + "from user "
											  + "where userId='%s'", userId);
		
		String userEaseid = db.getOneColValue(getUserEaseIdSql, easeidColName);
		
		System.out.println("userId:" + userId + "   ctyOwner:" + userEaseid);
		
		String ctyEaseId = createTempGroup(userEaseid, ctyId);
		
		System.out.printf("userEaseid:%s;ctyEaseiD:%S", userEaseid, ctyEaseId);
		
		String insertSql = String.format("insert into communnity(ctyId, ctyIcon, ctyName, ctyType, ctyGroupId, ctyCreatorId, ctyIntro) values('%s', '%s', '%s', '%s', '%s', '%s', '%s')", ctyId, iconURL, ctyName, ctyType, ctyEaseId, userId, ctyIntro);		
		String update_cty = String.format("update %s " +
				"set ctyMembers=ctyMembers+1 " +
				"where ctyId='%s'", IStringConstans.COMMUNITY_TABLE_NAME, ctyId);

		String insert_attention = String.format("insert into %s values('%s', '%s')", IStringConstans.ATTENTION_TABLE_NAME, userId, ctyId);
		
		db.excuteUpdate(insertSql);
		db.excuteUpdate(update_cty);	
		db.excuteUpdate(insert_attention);
	}	

	/*由社区发布活动*/
	private void releaseByCty(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		Activity activity = new Activity();
		
		String userId = jsobj.getString("userId");
		String ctyId = jsobj.getString("atyCtyId");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String releaseTime = sdf.format(new Date());
		
		String atyId = CreateId.createAtyId(userId);
		activity.setId(atyId);
		activity.setName(jsobj.getString("atyName"));
		activity.setType(jsobj.getString("atyType"));
		activity.setStartTime(jsobj.getString("atyStartTime"));
		activity.setEndTime(jsobj.getString("atyEndTime"));
		activity.setPlace(jsobj.getString("atyPlace"));
		activity.setLongitude(0);
		activity.setLatitude(0);
		activity.setMembers(Integer.parseInt(jsobj.getString("atyMembers")));
		activity.setContent(jsobj.getString("atyContent"));
		activity.setShares(Integer.parseInt(jsobj.getString("atyShares")));
		activity.setComments(Integer.parseInt(jsobj.getString("atyComments")));
		
		String atyAlbumStr = jsobj.getString("atyAlbum");
		String atyIsPublic = jsobj.getString("atyIsPublic");
		String groupId = "";
		
		/*insert all pictures*/
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(atyAlbumStr);
		
		String albumId_aty = activity.getId();
		String albumId_user = userId;
		for (int i = 0; i < jsonArray.size(); i++) 
		{
			String picurl = IconAndUrl.getUrl(IMAGE_PATH, jsonArray.getString(i), i);
			String insert_pics_sql = String.format("insert into %s values('%s', '%s')", IStringConstans.PHOTOS_TABLE_NAME, picurl, albumId_aty);
			String insert_pics_sql2 = String.format("insert into %s values('%s', '%s')", IStringConstans.PHOTOS_TABLE_NAME, picurl, albumId_user);
			db.excuteUpdate(insert_pics_sql);
			db.excuteUpdate(insert_pics_sql2);
		}
		
		String insert_sql1 = String.format("insert into distribute values('%s', '%s', '%s', '%s')", userId, activity.getId(), ctyId, releaseTime);
		String insert_sql2 = String.format("insert into %s(atyId, atyName, atyType, atyStartTime, atyEndTime, atyPlace, atyLongitude, atyLatitude, atyMembers, atyContent, atyShares, atyIsPublic, groupId) values('%s', '%s', '%s', '%s', '%s', '%s', %f, %f, '%s', '%s', '%s', '%s', '%s')", IStringConstans.ACTIVITY_TABLE_NAME, activity.getId(), activity.getName(),activity.getType(), activity.getStartTime(), activity.getEndTime(), activity.getPlace(), activity.getLongitude(), activity.getLatitude(), activity.getMembers(), activity.getContent(), activity.getShares(), atyIsPublic, groupId);
		String joint_sql = String.format("insert into %s values('%s', '%s')", IStringConstans.JOIN_TABLE_NAME, userId, activity.getId());
		String update_cty_sql = String.format("update communnity "
											 + "set ctyNumOfAty=ctyNumOfAty+1"
											 + "where ctyId='%s'", ctyId);
		
		db.excuteUpdate(insert_sql1);
		db.excuteUpdate(insert_sql2);
		db.excuteUpdate(joint_sql);
		db.excuteUpdate(update_cty_sql);
		
		//create a easemob id for the activity
		groupId = createTempGroup(jsobj.getString("easemobId"), atyId);
		
		String update_group = String.format("update %s set groupId = %s where atyId = '%s'", IStringConstans.ACTIVITY_TABLE_NAME, groupId, atyId);
		db.excuteUpdate(update_group);
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String creditContent = "发布活动：" + jsobj.getString("atyName");
		int creditNumbers = 20;
		
		String insert_credit = String.format("insert into %s values('%s', '%s', '%s', %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
		db.excuteUpdate(insert_credit);
		
		String update_credit = String.format("update %s set userCredit = userCredit + %d where userId = '%s'", IStringConstans.USER_TABLE_NAME, creditNumbers, userId);
		db.excuteUpdate(update_credit);
		
		outJson.put("atyId", activity.getId());
		outJson.put("groupId", groupId);
		writeJson(resp, outJson.toString());
		
	}
		
	private void showUserComments(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryUserComment = String.format("select user.userId, user.userName, user.userIcon, activity.atyName, comment.cmtContent, comment.cmtTime" +
				" from user, activity, distribute, comment, evaluation " +
				"where evaluation.userId = user.userId and evaluation.atyId = distribute.atyId and distribute.atyId = activity.atyId and evaluation.cmtId = comment.cmtId and distribute.userId = '%s'", userId);
		
		JSONArray outJson = db.queryGetJsonArray(queryUserComment);
		writeJson(resp, outJson.toString());
	}
	
	/*edit the introduction of the community*/
	private void editCommunity(HttpServletResponse resp, JSONObject jsobj)
	{
		String ctyIntro = jsobj.getString("ctyIntro");
		String ctyId = jsobj.getString("ctyId");
		
		String update_cty = String.format("update communnity set ctyIntro='%s' where ctyId='%s'", ctyIntro, ctyId);
		
		db.excuteUpdate(update_cty);		
	}

	/*显示热门小组*/
	private void showHotCty(HttpServletResponse resp, JSONObject jsobj)
	{
		String query_hot_cty = String.format("select ctyId, ctyName, ctyIcon, ctyMembers, ctyNumOfAty, ctyCredit "
										   + "from communnity "
										   + "order by ctyCredit DESC "
										   + "limit 10");
		JSONArray outJson = db.queryGetJsonArray(query_hot_cty);
		writeJson(resp, outJson.toString());
	}

}
