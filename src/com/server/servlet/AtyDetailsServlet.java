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
 * Servlet implementation class AtyDetailsServlet
 */
@WebServlet("/AtyDetailsServlet")
public class AtyDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDB db;
	private String sql;
	private PreparedStatement pstat;
	
	private Activity aty;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtyDetailsServlet() {
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
		
		db = new UserDB();
		db.createConnection();
		
		sql = String.format("select * from %s where atyId = ?", IStringConstans.ACTIVITY_TABLE_NAME);
		
		aty = new Activity();
		
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			pstat.setString(1, atyId);
			
			ResultSet rs = pstat.executeQuery();
			while(rs.next())
			{
				aty.setId(rs.getString("atyId"));
				aty.setName(rs.getString("atyName"));
				aty.setType(rs.getString("atyType"));
				aty.setStartTime(rs.getString("atyStartTime"));
				aty.setEndTime(rs.getString("atyEndTime"));
				aty.setPlace(rs.getString("atyPlace"));
				aty.setMembers(rs.getInt("atyMembers"));
				aty.setContent(rs.getString("atyContent"));
				aty.setAlbum(rs.getString("atyAlbum"));
				aty.setIsBanned(rs.getInt("atyIsBanned"));
				aty.setLikes(rs.getInt("atyLikes"));
				aty.setShares(rs.getInt("atyShares"));
			}
			
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("aty", aty);
		
		ArrayList<String> photoURL = new ArrayList<String>();
		String sql = String.format("select photoId from photos wehere albumId='%s'", atyId);
		try
		{
			pstat = db.getConnection().prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while(rs.next())
			{
				photoURL.add(rs.getString("photoId"));
			}
			pstat.executeUpdate();
			pstat.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("photoURL", photoURL);
		
		request.getRequestDispatcher("atyDetails.jsp").forward(request, response);
	}

}
