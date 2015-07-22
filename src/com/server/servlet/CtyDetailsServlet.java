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

import com.server.db.UserDB;

/**
 * Servlet implementation class CtyDetailsServlet
 */
@WebServlet("/CtyDetailsServlet")
public class CtyDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtyDetailsServlet() {
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
		
		String ctyId = request.getParameter("ctyId");
		
		db = new UserDB();
		db.createConnection();
		
		sql = String.format("select atyId, atyName from activity where atyType='%s'", ctyId);
		
		ArrayList<String> aty = new ArrayList<String>();
		ArrayList<ArrayList<String>> atyList = new ArrayList<ArrayList<String>>();
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			
			ResultSet rs = pstat.executeQuery();
			while(rs.next())
			{
				aty.add(rs.getString("atyId"));
				aty.add(rs.getString("atyName"));
				
				atyList.add(aty);
				
				aty = new ArrayList<String>();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(atyList);
		
		request.setAttribute("atyList", atyList);
		
		sql = String.format("select user.userId, userName from attention, user where user.userId=attention.userId and ctyId='%s'", ctyId);
		
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<ArrayList<String>> userList = new ArrayList<ArrayList<String>>();
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			
			ResultSet rs = pstat.executeQuery();
			while(rs.next())
			{
				user.add(rs.getString("user.userId"));
				user.add(rs.getString("userName"));
				
				userList.add(user);
				
				user = new ArrayList<String>();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("userList", userList);
		
		request.getRequestDispatcher("ctyDetails.jsp").forward(request, response);
		
	}

}
