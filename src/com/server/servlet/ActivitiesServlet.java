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

import com.server.bean.Activity;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;

/**
 * Servlet implementation class ActivitiesServlet
 */
@WebServlet("/ActivitiesServlet")
public class ActivitiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
	
	private ArrayList<Activity> atyList;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivitiesServlet() {
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
		
		db = new UserDB();
		db.createConnection();
		
		sql = String.format("select * from %s", IStringConstans.ACTIVITY_TABLE_NAME);
		
		atyList = new ArrayList<Activity>();
		
		try
		{
			ResultSet rs=db.executeQuery(sql);
			while(rs.next())
			{
				Activity tempAty = new Activity();
				
				tempAty.setId(rs.getString("atyId"));
				tempAty.setType(rs.getString("atyType"));
				tempAty.setStartTime(rs.getString("atyStartTime"));
				tempAty.setIsBanned(rs.getInt("atyIsBanned"));
				
				atyList.add(tempAty);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("atyList", atyList);
		
		response.sendRedirect("activities.jsp");
	}

}
