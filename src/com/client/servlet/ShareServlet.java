package com.client.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.db.UserDB;
import com.server.strings.IStringConstans;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShareServlet extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDB db = new UserDB();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{		
		db.createConnection();
		
		//String atyId = (String) req.getAttribute("atyId");
		String atyId = "cc@qq.com20151020221856";
		
		//get the basic activity info
		String aty_select = String.format("SELECT atyName, atyType, atyStartTime, atyEndTime, atyPlace, atyContent, userName, user.userId, userIcon "
										+ "from activity, user, distribute "
										+ "where activity.atyId = '%s'and activity.atyId = distribute.atyId and user.userId = distribute.userId", atyId);
		
		JSONObject atyInfo = db.queryGetJsonObj(aty_select);
		
		//get the album info
		String query_album = String.format("select photoId from %s where albumId='%s'", IStringConstans.PHOTOS_TABLE_NAME,  atyId);		
		List<String> photos = db.getListBySql(query_album, "photoId");
		
		//get the distributed activities by the userid.
		String userId = atyInfo.getString("userId");
		String distribute_select = String.format("SELECT atyName "
												+"FROM activity "
												+"where atyId<>'%s' and atyId IN (SELECT atyId "
																				+"FROM distribute "
																			    +"where userId = '%s') "
																			    +"limit 2", atyId, userId);
		
		List<String> distributedAty = db.getListBySql(distribute_select, "atyName");
		
		//get the comment about the activity
		String query_comments = String.format("select user.userId, userName,userIcon,cmtContent,cmtTime " +
				"from %s, %s, %s, %s " +
				"where activity.atyId = evaluation.atyId and user.userId = evaluation.userId and comment.cmtId = evaluation.cmtId and activity.atyId='%s'", IStringConstans.ACTIVITY_TABLE_NAME, IStringConstans.COMMENT_TABLE_NAME, IStringConstans.EVALUATION_TABLE_NAME, IStringConstans.USER_TABLE_NAME, atyId); 

		
		JSONArray comments = db.commentsQueryGetJsonArrayWithTime(query_comments, "cmtTime");	
				
		db.close();
				
		req.setAttribute("atyInfo", atyInfo);
		req.setAttribute("distributedAty", distributedAty);
		req.setAttribute("photos", photos);
		req.setAttribute("comments", comments);
		req.getRequestDispatcher("/client_jsp/share.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
	}
	
}
