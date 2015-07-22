package com.client.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
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
		IMAGE_PATH = getServletContext().getRealPath("/") + "images_repo\\";
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
		case "showJoinedAty":
			showJoinedAty(resp, jsobj);
			break;
		case "showDistributingAty":
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
			break;
		case "showCredit":
			showCredit(resp, jsobj);
			break;
		case "message":
			sendMessage(resp, jsobj);
			break;
		case "setPublicTrue":
			setPublicTrue(resp, jsobj);
			break;
		case "setPublicFalse":
			setPublicFalse(resp, jsobj);
			break;
		case "showMembers":
			showMembers(resp, jsobj);
		default:
			break;
		}
		
		db.close();
	}
	
//	private JSONObject readPic(HttpServletResponse req)
//	{
//		
//	}
	
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
		
		writeJson(resp, outJson.toString());
		
	}
	
	private void editProfile(HttpServletResponse resp, JSONObject json)
	{
		
	}
	
	private void login(HttpServletResponse resp, JSONObject jsobj)
	{
		String id = jsobj.getString("userId");
		String password = jsobj.getString("userPassword");
		
		//System.out.println("id:" + id + " password:" + password);
		
		String query_sql = String.format("select * from %s where userId='%s' and userPassword='%s'", IStringConstans.USER_TABLE_NAME, id, password);
		
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
		
		writeJson(resp, outJSon.toString());
		
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
		
		writeJson(resp, outJSon.toString());
		
		
		//credit
		String userId = jsobj.getString("userId");
		String creditId = CreateId.createCreditId(userId);
		String creditContent = "注册成功";
		int creditNumbers = 50;
		
		String insert_credit = String.format("insert into %s values(%s, %s, %s, %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
		
		db.excuteUpdate(insert_credit);
	}

	private void release(HttpServletResponse resp, JSONObject jsobj)
	{
		JSONObject outJson = new JSONObject();
		Activity activity = new Activity();
		
		String userId = jsobj.getString("userId");
		//-------------------release time still lack!
		String releaseTime = jsobj.getString("releaseTime");
		
		activity.setId(CreateId.createAtyId(userId));
		activity.setName(jsobj.getString("atyName"));
		activity.setType(jsobj.getString("atyType"));
		activity.setStartTime(jsobj.getString("atyStartTime"));
		activity.setEndTime(jsobj.getString("atyEndTime"));
		activity.setPlace(jsobj.getString("atyPlace"));
		activity.setMembers(Integer.parseInt(jsobj.getString("atyMembers")));
		activity.setContent(jsobj.getString("atyContent"));
		activity.setShares(Integer.parseInt(jsobj.getString("atyShare")));
		
		String insert_sql1 = String.format("insert into %s values('%s', '%s', '%s')", IStringConstans.DISTRIBUTE_TABLE_NAME, userId, activity.getId(), releaseTime);
		String insert_sql2 = String.format("insert into %s(atyId, atyName, atyType, atyStartTime, atyEndTime, atyPlace, atyMembers, atyContent, atyShares,atyReleaseTime) values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", IStringConstans.ACTIVITY_TABLE_NAME, activity.getId(), activity.getName(),activity.getType(), activity.getStartTime(), activity.getEndTime(), activity.getPlace(), activity.getMembers(), activity.getContent(), activity.getShares(), releaseTime);
		db.excuteUpdate(insert_sql1);
		db.excuteUpdate(insert_sql2);
		
		outJson.put("atyId", activity.getId());
		writeJson(resp, outJson.toString());
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String creditContent = "发布活动：" + jsobj.getString("atyName");
		int creditNumbers = 10;
		
		String insert_credit = String.format("insert into %s values(%s, %s, %s, %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
		
		db.excuteUpdate(insert_credit);
		
	}
	
	private void like(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		
		String update_aty = String.format("update %s " +
										"set atyLikes=atyLikes+1 " +
										"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		
		
		String insert_like = String.format("insert into %s values('%s', '%s')", IStringConstans.LIKE_TABLE_NAME, userId, atyId);
		db.excuteUpdate(update_aty);
		db.excuteUpdate(insert_like);
	}
	
	private void notLike(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		
		String update_aty = String.format("update %s " +
										"set atyLikes=atyLikes-1 " +
										"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		
		
		String delete_like = String.format("delete from %s where userId='%s' and atyId='%s'", IStringConstans.LIKE_TABLE_NAME, userId, atyId);
		db.excuteUpdate(delete_like);
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
		String creditContent = "参加活动：" + atyName;
		int creditNumbers = 5;
		
		String insert_credit = String.format("insert into %s values(%s, %s, %s, %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
				
		db.excuteUpdate(insert_credit);		
	}
	
	private void notJoin(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		String atyId = jsobj.getString("atyId");
		
		String update_aty = String.format("update %s " +
										"set atyMembers=atyMembers-1 " +
										"where atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, atyId);
		
		
		
		String insert_like = String.format("insert into %s values('%s', '%s')", IStringConstans.JOIN_TABLE_NAME, userId, atyId);
		db.excuteUpdate(update_aty);
		db.excuteUpdate(insert_like);
		
		//credit
		String creditId = CreateId.createCreditId(userId);
		String atyName = jsobj.getString("atyName");
		String creditContent = "退出活动：" + atyName;
		int creditNumbers = -5;
				
		String insert_credit = String.format("insert into %s values(%s, %s, %s, %d)", IStringConstans.ADDCREDIT_TABLE_NAME, userId, creditId, creditContent, creditNumbers);
						
		db.excuteUpdate(insert_credit);
	}
	
	private void showActivities(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty = "select user.userId, userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments " +
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
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments " +
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
		
		String longitude = jsobj.getString("longitude");
		
		String latitude = jsobj.getString("latitude");
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments " +
											"from %s, %s, %s " +
											"where activity.atyId = distribute.atyId and user.userId = distribute.userId and activity.atyIsBanned=0 " , IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER_TABLE_NAME, IStringConstans.DISTRIBUTE_TABLE_NAME );
				
		
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
		
		String queryAllAty = String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments " +
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
		
		String insert_comment = String.format("insert into %s values(%s, %s, %s)", IStringConstans.COMMENT_TABLE_NAME, cmtId, cmtContent, cmtTime);
		String insert_evaluation = String.format("insert into %s values(%s, %s, %s)", IStringConstans.EVALUATION_TABLE_NAME, userId, atyId, cmtId);
		
		db.excuteUpdate(insert_comment);
		db.excuteUpdate(insert_evaluation);
	}

	private void showJoinedAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty =String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments " + 
										"from %s, %s, %s" +
										"where activity.atyId = joining.atyId and user.userId = joining.userId and activity.atyIsBanned=0 and userId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER, IStringConstans.JOIN_TABLE_NAME, userId);		
											
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);		
		writeJson(resp, outJson.toString());		
	}
	
	private void showDistributeAty(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllAty =String.format("select userName,userIcon, activity.atyId, atyName, atyType, atyStartTime,atyEndTime, atyPlace, atyMembers,atyContent, atyLikes, atyShares, atyComments " + 
										"from %s, %s, %s" +
										"where activity.atyId = distribute.atyId and user.userId = distribute.userId and activity.atyIsBanned=0 and userId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.USER, IStringConstans.DISTRIBUTE_TABLE_NAME, userId);
		JSONArray outJson = db.queryGetJsonArray(queryAllAty);
		writeJson(resp, outJson.toString());	
	}
	
	private void showCredit(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllCredit = String.format("select creditContent, creditNumber from %s where userId='%s'", IStringConstans.ADDCREDIT_TABLE_NAME, userId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllCredit);
		writeJson(resp, outJson.toString());
	}
	
	private void sendMessage(HttpServletResponse resp, JSONObject jsobj)
	{
		String userId = jsobj.getString("userId");
		
		String queryAllMessage = String.format("select * from %s, %s where receive.msgId=message.msgId and userId='%s'", IStringConstans.MESSAGE_TABLE_NAME, IStringConstans.RECEIVE_TABLE_NAME, userId);
		
		JSONArray outJson = db.queryGetJsonArray(queryAllMessage);
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
}
