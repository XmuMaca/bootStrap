package com.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.server.strings.IStringConstans;

public class DB 
{	
	protected Connection connection = null;
	
	protected Statement statement = null;
	
	protected ResultSet resultSet = null;
	
	public void createConnection()
	{
		try 
		{
			Class.forName(IStringConstans.MYSQL_DRIVER);	
			
			String url = IStringConstans.LocalURL;
			String user = IStringConstans.USER;
			String password = IStringConstans.PASSWORD;
			
			connection = DriverManager.getConnection(url, user, password);
			//System.out.print("connection succeed.");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public int excuteUpdate(String sql)
	{
		try 
		{
			statement = connection.createStatement();
			int row = statement.executeUpdate(sql);
			return row;			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet executeQuery(String sql)
	{
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			return resultSet;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public JSONArray queryGetJsonArray(String sql)
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		
		ResultSet rs = executeQuery(sql);
		ResultSetMetaData rsmd;
		try {
			
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while (rs.next()) 
			{
				for (int i = 1; i <= cols; i++) 
				{
					jsonObj.put(rsmd.getColumnName(i), rs.getString(i));
				}
				jsonArray.add(jsonObj);
				jsonObj = new JSONObject();				
			}
			
			return jsonArray;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
//	public JSONArray queryGetJsonArray2(String sql)
//	{
//		JSONArray jsonArray = new JSONArray();
//		JSONObject jsonObj = new JSONObject();
//		
//		ResultSet rs = executeQuery(sql);
//		ResultSetMetaData rsmd;
//		
//		Map<String, String> map = new HashMap<String, String>();
//		List<Map<String, String>> maplist = new ArrayList<Map<String,String>>() ;
//		
//		try {
//			
//			rsmd = rs.getMetaData();
//			int cols = rsmd.getColumnCount();
//			while (rs.next()) 
//			{
//				for (int i = 1; i <= cols; i++) 
//				{
//					//jsonObj.put(rsmd.getColumnName(i), rs.getString(i));
//					map.put(rsmd.getColumnName(i), rs.getString(i));
//				}
//				//jsonArray.add(jsonObj);
//				maplist.add(map);
//				map = new HashMap<String, String>();
//			}
//			
//			
//			
////			return jsonArray;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
////			return null;
//		}
//		
//		for (int i = 0; i < maplist.size(); i++) 
//		{
//			
//		}
//	}
//	
	public JSONObject queryGetJsonObj(String sql)
	{
		JSONArray jsarray = queryGetJsonArray(sql);
		return jsarray.getJSONObject(0);
	}
	
	public void close()
	{
		try 
		{
			if (connection != null) 
			{
				connection.close();
			}	
			
			if (statement != null) 
			{
				statement.close();
			}
			
			if (resultSet != null) 
			{
				resultSet.close();
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}


}
