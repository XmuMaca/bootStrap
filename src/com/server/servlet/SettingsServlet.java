package com.server.servlet;

import com.server.bean.Account;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Account user;
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsServlet() {
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
		
		user = new Account();
		user.setId(request.getParameter("userId_input"));
		user.setName(request.getParameter("userName_input"));
		String userNewPassword = request.getParameter("userNewPassword_input");
		String userComfirmPassword = request.getParameter("userComfirmPassword_input");
		if(userNewPassword.equals(userComfirmPassword))
		{
			user.setPassword(userComfirmPassword);
		}
		else
		{
			
		}
		user.setGender(request.getParameter("userGender_input"));
		user.setLocation(request.getParameter("userLocation_input"));
		System.out.println(request.getParameter("userLocation_input"));
		user.setEmail(request.getParameter("userEmail_input"));
		user.setPhone(request.getParameter("userPhone_input"));
		
		request.getSession().setAttribute("account", user);
		
		db = new UserDB();
		db.createConnection();
		
		/*sql = String.format("update %s set userName=%s, userPassword=%s, userGender=%s, userLocation=%s, userEmail=%s, userPhone=%s where userId=%s", 
							IStringConstans.USER_TABLE_NAME, userName, userComfirmPassword, userGender, userLocation, userEmail, userPhone, userId);
		*/
		sql = String.format("update %s set userName=?, userPassword=?, userGender=?, userLocation=?, userEmail=?, userPhone=? where userId=?", IStringConstans.USER_TABLE_NAME);
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, user.getName());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getGender());
			pstat.setString(4, user.getLocation());
			pstat.setString(5, user.getEmail());
			pstat.setString(6, user.getPhone());
			pstat.setString(7, user.getId());
			pstat.executeUpdate();
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		db.close();
		
		response.sendRedirect("settings.jsp");
		
		
	}

}
