package com.server.dao;

import java.sql.*;
import java.util.ArrayList;

import com.server.bean.Activity;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;

public class ActivitiesDAO {
	
	public ArrayList<Activity> readActivities()
	{
		ArrayList<Activity> atyList = new ArrayList<Activity>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserDB db = new UserDB();
		
		try
		{
			db.createConnection();
			conn = db.getConnection();
			String sql = String.format("select * from %s", IStringConstans.ACTIVITY_TABLE_NAME);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
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
		finally
		{
			try
			{
				if(rs != null)
				{
					rs.close();
				}
				if(pstmt != null)
				{
					pstmt.close();
				}
				if(conn != null)
				{
					conn.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return atyList;
		
	}
}
