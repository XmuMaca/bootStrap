package com.jpush.server;

import java.util.Set;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;



public class MessagePush {

    private static final String appKey = "829d2844bc726022b6b78e86";
    private static final String masterSecret = "1c04b3a9c837fa2c20975b3c";
    private JPushClient jpushClient ;
    private String title;
	private String message;
	
	public MessagePush(String message) {
         this.message = message;    
         jpushClient = new JPushClient(masterSecret, appKey, 3);
     }
     public MessagePush(String message,String title) {
    	 this(message);        
         this.title=title;    
     }
     
     public static void main(String[] args)
     {
    	 MessagePush test = new MessagePush("bbbbbb", "test");
    	 
    	 System.out.println(test.sendPushAll());
     }
  
     
     /**
      * 向所有人发送消息
      * @return 消息id
      */
     public long sendPushAll(){
        PushPayload payload=buildPushObject_all_all_alert();
        long msgId=0;
        try {
            PushResult result=jpushClient.sendPush(payload);
            msgId=result.msg_id;
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
                System.out.println("Connection error. Should retry later. " + e);
        } catch (APIRequestException e) {
            System.out.println("HTTP Status: " + e.getStatus());
            msgId=e.getMsgId();
        }
        return msgId;
    }
    /**
     * 向指定别名的客户端发送消息
     * @param alias 所有别名信息集合，这里表示发送所有学生编号
     * @return 消息id
     */
    public long sendPushAlias(Set<String> alias){
    PushPayload payloadAlias=buildPushObject_android_alias_alertWithTitle(alias);
    long msgId=0;
        try {
            PushResult result=jpushClient.sendPush(payloadAlias);
            msgId=result.msg_id;
            
        } catch (APIConnectionException e) {
            System.out.println("Connection error. Should retry later. " + e);
        } catch (APIRequestException e) {
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            System.out.println("Msg ID: " + e.getMsgId());
            msgId=e.getMsgId();
        }
        return msgId;
    }
    /**
     * 向指定组发送消息
     * @param tag 组名称
     * @return 消息id     
	*/
    public  long sendPushTag(String tag) {
        PushPayload payloadtag = buildPushObject_android_tag_alertWithTitle(tag);
        long msgId=0;
        try {
            PushResult result = jpushClient.sendPush(payloadtag);
            msgId=result.msg_id;
            System.out.println("Got result - " + result);
        } catch (APIConnectionException e) {
            System.out.println("Connection error. Should retry later. " + e);
            
        } catch (APIRequestException e) {
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            System.out.println("Msg ID: " + e.getMsgId());
            msgId=e.getMsgId();
        }
        return msgId;
    }


    /**
     * 下列封装了三种获得消息推送对象（PushPayload）的方法
     *  buildPushObject_android_alias_alertWithTitle、
     *  buildPushObject_android_tag_alertWithTitle、
     *  buildPushObject_all_all_alert
     */
    public  PushPayload buildPushObject_android_alias_alertWithTitle(Set<String> alias) {
        return PushPayload.newBuilder().setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))        .setNotification(Notification.android(message,title,null)).build();
    }

    public  PushPayload buildPushObject_android_tag_alertWithTitle(String tag){
    	return PushPayload.newBuilder().setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(message, title, null)).build();
    }
    
    public  PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(message);
    }
}


