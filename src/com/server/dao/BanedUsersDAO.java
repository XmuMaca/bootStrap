package com.server.dao;

import java.sql.*;
import java.util.ArrayList;

import com.server.bean.Account;
import com.server.db.UserDB;
import com.server.strings.IStringConstans;

public class BanedUsersDAO {
	
	public ArrayList<Account> readUsers()
	{
		ArrayList<Account> userList = new ArrayList<Account>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserDB db = new UserDB();
		
		try
		{
			db.createConnection();
			conn = db.getConnection();
			String sql = String.format("select * from %s where userIdentity='normal' and userIsBaned=1", IStringConstans.USER_TABLE_NAME);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Account tempUser = new Account();
				
				tempUser.setId(rs.getString("userId"));
				tempUser.setName(rs.getString("userName"));
				tempUser.setEmail(rs.getString("userEmail"));
				tempUser.setIsBaned(rs.getInt("userIsBaned"));
				
				userList.add(tempUser);
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
		
		return userList;
		
	}
}
