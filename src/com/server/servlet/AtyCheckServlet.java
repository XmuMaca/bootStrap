package com.server.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.db.UserDB;
import com.server.strings.IStringConstans;

/**
 * Servlet implementation class AtyCheckServlet
 */
@WebServlet("/AtyCheckServlet")
public class AtyCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtyCheckServlet() {
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
		
		String atyId = request.getParameter("atyId");
		String status = request.getParameter("status");
		
		db = new UserDB();
		db.createConnection();
		
		System.out.println("check start");
		
		sql = String.format("update %s set atyIsBanned=? where atyId = ?", IStringConstans.ACTIVITY_TABLE_NAME);
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, status);
			pstat.setString(2, atyId);
			pstat.executeUpdate();
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		db.close();
		
		response.sendRedirect("activities.jsp");
	}

}
