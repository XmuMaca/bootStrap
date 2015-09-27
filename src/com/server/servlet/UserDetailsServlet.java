package com.server.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Account;
import com.server.bean.Activity;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;
import com.server.util.*;

/**
 * Servlet implementation class UserDetailsServlet
 */
@WebServlet("/UserDetailsServlet")
public class UserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
	
	private Account user;
	
	private DesUtils des;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailsServlet() {
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
		
		String userId = null;
		
		try {
			des = new DesUtils();
			userId = des.decrypt(request.getParameter("userId"));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		db = new UserDB();
		db.createConnection();
		
		sql = String.format("select * from %s where userId = ?", IStringConstans.USER_TABLE_NAME);
		
		user = new Account();
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, userId);
			
			ResultSet rs = pstat.executeQuery();
			while(rs.next())
			{
				user.setId(rs.getString("userId"));
				user.setName(rs.getString("userName"));
				user.setPassword(rs.getString("userPassword"));
				user.setIcon(rs.getString("userIcon"));
				user.setGender(rs.getString("userGender"));
				user.setLocation(rs.getString("userLocation"));
				user.setEmail(rs.getString("userEmail"));
				user.setPhone(rs.getString("userPhone"));
			}
			
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("userDetails.jsp").forward(request, response);
	}

}
