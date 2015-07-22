package com.server.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Message;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;

/**
 * Servlet implementation class BoardcastServlet
 */
@WebServlet("/BoardcastServlet")
public class BoardcastServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Message message;
	private UserDB db;
	
	private String msg_sql;
	private String user_sql;
	private String receive_sql;
	
	private PreparedStatement pstat;
	
	private ArrayList<String> users;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardcastServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String[] userids = request.getParameterValues("ms_example");
		/*for(int i = 0; i < userids.length; i++)
		{
			System.out.printf("%s\r\n", userids[i]);
		}*/
		
		
		message = new Message();
		message.setMsgId(request.getParameter("msgId_input"));
		message.setMsgType(request.getParameter("msgType_input"));
		message.setMsgTime(request.getParameter("msgTime_input"));
		message.setMsgContent(request.getParameter("msgContent_input"));
		
		db = new UserDB();
		db.createConnection();
		
		msg_sql = String.format("insert into %s values(?,?,?,?,?)", IStringConstans.MESSAGE_TABLE_NAME);
		user_sql = String.format("select * from %s where userId = ?", IStringConstans.USER_TABLE_NAME);
		receive_sql = String.format("insert into %s values(?,?)", IStringConstans.RECEIVE_TABLE_NAME);
		
		users = new ArrayList<String>();
		
		try
		{
			pstat = db.getConnection().prepareStatement(msg_sql);
			pstat.setString(1, message.getMsgId());
			pstat.setString(2, message.getMsgType());
			pstat.setString(3, message.getMsgTime());
			pstat.setString(4, message.getMsgContent());
			pstat.setInt(5, 0);
			pstat.executeUpdate();
			pstat.close();
			
			for(int i = 0; i < userids.length; i++)
			{
				pstat = db.getConnection().prepareStatement(user_sql);
				pstat.setString(1, userids[i]);
				ResultSet rs = pstat.executeQuery();
				while(rs.next())
				{
					String id = rs.getString("userId");
					users.add(id);
				}
				rs.close();
				pstat.close();
			}
			
			for(String ele : users)
			{
				pstat = db.getConnection().prepareStatement(receive_sql);
				pstat.setString(1, ele);
				pstat.setString(2, message.getMsgId());
				pstat.executeUpdate();
				pstat.close();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		db.close();
		
		response.sendRedirect("boardcast.jsp");
	}

}
