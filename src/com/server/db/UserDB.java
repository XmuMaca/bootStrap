package com.server.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;

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
				account.setEasemobId(rs.getString("easemobId"));
				account.setName(rs.getString("userName"));
				account.setIcon(rs.getString("userIcon"));
				account.setGender(rs.getString("userGender"));
				account.setLocation(rs.getString("userLocation"));
				account.setEmail(rs.getString("userEmail"));
				account.setPhone(rs.getString("userPhone"));
				account.setIdentity(rs.getString("userIdentity"));
				account.setIsBaned(rs.getInt("userIsBaned"));
				account.setIsPublic(rs.getString("userAlbumIsPublic"));
				account.setCredit(rs.getString("userCredit"));
				result.add(account);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * get the activity id which is liked or joined by someone
	 * */
	public List<String> getAtyId(String sql)
	{
		List<String> result = new ArrayList<>();
		ResultSet rs = executeQuery(sql);
		
		try {
			while(rs.next())
			{
				result.add(rs.getString("atyId"));
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getAlbumUrl(String sql)
	{
		JSONArray jsarray = new JSONArray();
		ResultSet rs = executeQuery(sql);
		
		try {
			while(rs.next())
			{
				jsarray.add(rs.getString("photoId"));
			}
			return jsarray;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/*获得满足sql的某一元组的colName列的string值*/
	public String getOneColValue(String sql, String colName)
	{
		ResultSet rs = executeQuery(sql);
		String id = null;
		try {
			while(rs.next())
			{
				id = rs.getString(colName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return id;
	}
}
