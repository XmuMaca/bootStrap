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
 * Servlet implementation class IconChangeServlet
 */
@WebServlet("/IconChangeServlet")
public class IconChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IconChangeServlet() {
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
		
		String iconDataURL = (String)request.getSession().getAttribute("adminIcon");
		if(request.getParameter("iconDataURL") != null)
		{
			iconDataURL = request.getParameter("iconDataURL");
		}
		//System.out.println(iconDataURL);
		
		request.getSession().setAttribute("adminIcon", iconDataURL);
		
		db = new UserDB();
		db.createConnection();
		
		sql = String.format("update %s set adminIcon=?", IStringConstans.USER_TABLE_NAME);
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, iconDataURL);
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
