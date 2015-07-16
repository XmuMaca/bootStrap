package com.server.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.server.bean.Account;

public class UserDB extends DB 
{
	public int save(String sql)
	{
		return excuteUpdate(sql);
	}
	
	/*
	 * query successfully return true,or return false
	 * @param sql statement
	 * @return true or false
	 * */
	public boolean query(String sql)
	{
		ResultSet rs = executeQuery(sql);
		try {
			if (rs.next()) 
			{
				return true;
			}
			else 
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	public List<Account> queryResult(String sql)
	{
		List<Account> result = new ArrayList<>();
		ResultSet rs = executeQuery(sql);
		
		
		try {
			while(rs.next())
			{
				Account account = new Account();

				account.setId(rs.getString("userId"));
				account.setName(rs.getString("userName"));
				account.setIcon(rs.getString("userIcon"));
				account.setGender(rs.getString("userGender"));
				account.setLocation(rs.getString("userLocation"));
				account.setEmail(rs.getString("userEmail"));
				account.setPhone(rs.getString("userPhone"));
				account.setIdentity(rs.getString("userIdentity"));
				account.setIsBaned(rs.getInt("userIsBaned"));
				result.add(account);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
