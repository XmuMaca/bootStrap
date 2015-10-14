package com.easemob.server.example.httpclient.apidemo;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.httpclient.utils.HTTPClientUtils;
import com.easemob.server.example.httpclient.vo.ClientSecretCredential;
import com.easemob.server.example.httpclient.vo.Credential;
import com.easemob.server.example.httpclient.vo.EndPoints;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.server.db.UserDB;

public class EasemobMessages {
	
	private static UserDB db = new UserDB();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobMessages.class);
    private static final String APPKEY = Constants.APPKEY;
    private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {
    	System.out.println("start");
    	
        //  检测用户是否在线
        String targetUserName = "wxmzero";
        ObjectNode usernode = getUserStatus(targetUserName);
        if (null != usernode) {
            LOGGER.info("检测用户是否在线: " + usernode.toString());
        }

        // 给用户发一条文本消息
        String from = "wxmzero";
        String targetTypeus = "users";
        ObjectNode ext = factory.objectNode();
        ArrayNode targetusers = factory.arrayNode();
        targetusers.add("zerowxm");
        //targetusers.add("kenshinnuser002");
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", "welcome!");
        txtmsg.put("type","txt");
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, from, ext);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }
    }
    
    public static void mySendMsgJoin(String from, String to, String msgContent, String atyName)
    {
    	System.out.println("start");
    	
        //  检测用户是否在线
        String targetUserName = to;
        ObjectNode usernode = getUserStatus(targetUserName);
        if (null != usernode) {
            LOGGER.info("检测用户是否在线: " + usernode.toString());
        }

        // 给用户发一条文本消息
        String targetTypeus = "users";
        ObjectNode ext = factory.objectNode();
        ext.put("identify", "join");
        ext.put("atyName", atyName);
        
        ArrayNode targetusers = factory.arrayNode();
        targetusers.add(to);
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", msgContent);
        txtmsg.put("type","txt");
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, from, ext);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }
    }
    
    public static void mySendMsgComment(String from, String to, String msgContent, String userName)
    {
    	System.out.println("start");
    	
        //  检测用户是否在线
        String targetUserName = to;
        ObjectNode usernode = getUserStatus(targetUserName);
        if (null != usernode) {
            LOGGER.info("检测用户是否在线: " + usernode.toString());
        }

        // 给用户发一条文本消息
        String targetTypeus = "users";
        ObjectNode ext = factory.objectNode();
        ext.put("identify", "comment");
        ext.put("userName", userName);
        
        ArrayNode targetusers = factory.arrayNode();
        targetusers.add(to);
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", msgContent);
        txtmsg.put("type","txt");
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, from, ext);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }
    }
    
    public static void mySendMsg(String easemobId,String userName, String userIcon, String atyId, String atyName, String msgContent, String releaseTime, ObjectNode allMembers, int members)
    {
    	System.out.println("start");
    	System.out.println("msg: " + msgContent);

        // 给用户发一条文本消息
        String from = easemobId;
        String targetTypeus = "users";
        ObjectNode ext = factory.objectNode();
        ext.put("identify", "notification");
        ext.put("userName", userName);
        
        db.createConnection();
        
        ArrayNode targetusers = factory.arrayNode();
        for(int i = 0; i < members; i++)
        {
        	String toUser = allMembers.path("data").get(i).path("member").asText();
        	targetusers.add(toUser);
        	
        	String query_sql = String.format("insert into notification values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", easemobId, toUser, atyId, userName, userIcon, atyName, msgContent, releaseTime);
        	db.excuteUpdate(query_sql);
        }
        
        db.close();
        
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", msgContent);
        txtmsg.put("type","txt");
        
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, from, ext);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }
    }
    
    public static void mySendInfo(String easemobId, String atyId, String msgContent, String groupId)
    {
    	String from = easemobId;
    	ObjectNode ext = factory.objectNode();
        
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", msgContent);
        txtmsg.put("type","txt");
        
        String targetTypegr = "chatgroups";
        
        //ArrayNode  chatgroupidsNode = (ArrayNode) EasemobChatGroups.getAllChatgroupids().path("data");
        ArrayNode targetgroup = factory.arrayNode();
        targetgroup.add(groupId);
        
        //targetgroup.add(allMembers);
        ObjectNode sendTxtMessagegroupnode = sendMessages(targetTypegr, targetgroup, txtmsg, from, ext);
        if (null != sendTxtMessagegroupnode) {
            LOGGER.info("给一个群组发文本消息: " + sendTxtMessagegroupnode.toString());
        }
    }
    
    /**
	 * 检测用户是否在线
	 * 
	 * @param username
     *
	 * @return
	 */
	public static ObjectNode getUserStatus(String username) {
		
		System.out.println("getUserStatus");
		
		ObjectNode objectNode = factory.objectNode();
		
		// check appKey format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(username)) {
			LOGGER.error("You must provided a targetUserName .");
			objectNode.put("message", "You must provided a targetUserName .");
			return objectNode;
		}

		try {
			URL userStatusUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ username + "/status");
			objectNode = HTTPClientUtils.sendHTTPRequest(userStatusUrl, credential, null, HTTPMethod.METHOD_GET);
			String userStatus = objectNode.get("data").path(username).asText();
			if ("online".equals(userStatus)) {
				LOGGER.error(String.format("The status of user[%s] is : [%s] .", username, userStatus));
			} else if ("offline".equals(userStatus)) {
				LOGGER.error(String.format("The status of user[%s] is : [%s] .", username, userStatus));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 发送消息
	 * 
	 * @param targetType
	 *            消息投递者类型：users 用户, chatgroups 群组
	 * @param target
	 *            接收者ID 必须是数组,数组元素为用户ID或者群组ID
	 * @param msg
	 *            消息内容
	 * @param from
	 *            发送者
	 * @param ext
	 *            扩展字段
	 * 
	 * @return 请求响应
	 */
	public static ObjectNode sendMessages(String targetType, ArrayNode target, ObjectNode msg, String from,
			ObjectNode ext) {
		
		System.out.println("sendMessages");
		
		ObjectNode objectNode = factory.objectNode();

		ObjectNode dataNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (!("users".equals(targetType) || "chatgroups".equals(targetType))) {
			LOGGER.error("TargetType must be users or chatgroups .");

			objectNode.put("message", "TargetType must be users or chatgroups .");

			return objectNode;
		}

		try {
			// 构造消息体
			dataNode.put("target_type", targetType);
			dataNode.put("target", target);
			dataNode.put("msg", msg);
			dataNode.put("from", from);
			dataNode.put("ext", ext);

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.MESSAGES_URL, credential, dataNode,
					HTTPMethod.METHOD_POST);

			objectNode = (ObjectNode) objectNode.get("data");
			for (int i = 0; i < target.size(); i++) {
				String resultStr = objectNode.path(target.path(i).asText()).asText();
				if ("success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] successfully .", target.path(i)
							.asText()));
				} else if (!"success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] failed .", target.path(i).asText()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}
}
