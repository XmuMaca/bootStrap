package com.server.servlet;

import com.server.bean.Account;
import com.server.db.UserDB;
import com.server.strings.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	private Account account;
	private String adminIcon;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		UserDB db = new UserDB();
		db.createConnection();
		
		String id = request.getParameter("userid_input");
		String password = request.getParameter("password_input");
		
		String query_sql = String.format("select * from %s where userId='%s' and userPassword='%s' and userIdentity='admin'", IStringConstans.USER_TABLE_NAME, id, password);
		
		if (db.query(query_sql))
		{
			try
			{
				ResultSet rs = db.executeQuery(query_sql);
				account = new Account();
				
				while(rs.next())
				{
					request.getSession().setAttribute("userId", rs.getString("userId"));
					
					account.setId(rs.getString("userId"));
					account.setName(rs.getString("userName"));
					adminIcon = rs.getString("adminIcon");
					account.setGender(rs.getString("userGender"));
					account.setLocation(rs.getString("userLocation"));
					account.setEmail(rs.getString("userEmail"));
					account.setPhone(rs.getString("userPhone"));
					account.setIdentity(rs.getString("userIdentity"));
					account.setIsBaned(rs.getInt("userIsBaned"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("account", account);
			request.getSession().setAttribute("adminIcon", adminIcon);
			
			response.sendRedirect("activities.jsp");
			//response.sendRedirect("settings.jsp");
		}
		else 
		{
			response.sendRedirect("failed.jsp");
		}
		
		db.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


		
}
