package com.server.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.db.UserDB;
import com.server.strings.IStringConstans;

/**
 * Servlet implementation class UserChangeStatusServlet
 */
@WebServlet("/UserChangeStatusServlet")
public class UserChangeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserChangeStatusServlet() {
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
		
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		
		db = new UserDB();
		db.createConnection();
		
		sql = String.format("update %s set userIsBaned=? where userId = ?", IStringConstans.USER_TABLE_NAME);
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, status);
			pstat.setString(2, userId);
			pstat.executeUpdate();
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		sql = String.format("insert into %s values(?,?,?,?,?)", IStringConstans.MESSAGE_TABLE_NAME);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		String content = null;
		if(status.equals("1"))
		{
			content = "您已被禁用！";
		}
		else
		{
			content = "您已被恢复使用！";
		}
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, userId + time);
			pstat.setString(2, "系统通知");
			pstat.setString(3, time);
			pstat.setString(4, content);
			pstat.setInt(5, 0);
			pstat.executeUpdate();
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		sql = String.format("insert into %s values(?,?)", IStringConstans.RECEIVE_TABLE_NAME);
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, userId);
			pstat.setString(2, userId+time);
			pstat.executeUpdate();
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		db.close();
		
		response.sendRedirect("users.jsp");
	}

}
