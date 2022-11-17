package com.vishal.Model;
import java.util.*;
import java.sql.*;

public class Adapter 
{
    protected Connection conn = null;

    public void setConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Assign_Work";
            String user = "root";
            String pass = "vishal@1234";
            Connection conn = DriverManager.getConnection(url, user , pass);
            this.conn = conn;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

    public Connection getConnection()
    {
        if(this.conn == null)
        {
            this.setConnection();
        }
        return this.conn;
    }

    public ResultSet getAll(String tableName)
    {
    	System.out.println(tableName);
        ResultSet rs = null;
        try
        {
            Connection conn = this.getConnection();
            String query = "SELECT * FROM " + tableName; 
            PreparedStatement stmt = conn.prepareStatement(query);
            // stmt.setString(1, tableName);
            rs = stmt.executeQuery();
        }
        catch(SQLException e)
        {
            System.out.println("Inside Adapter getAll() method");
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getOne(String tableName , String primaryKey , int id)
    {
        ResultSet rs = null;
        try
        {
            Connection conn = this.getConnection();
            
            String query = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        }
        catch(SQLException e)
        {
        	System.out.println("Inside Adapter getOne() method");
            e.printStackTrace();
        }
        return rs;
    }

    public int upsert(String query)
    {
        int rs = 0;
        try
        {
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeUpdate();
        }
        catch(SQLException e)
        {
        	System.out.println("Inside Adapter upsert() method");
            e.printStackTrace();
        }
        return rs;
    }

    public int delete(String tableName , String primaryKey , int id)
	{
		int deleteCount = 0;
		try
		{
			Connection conn = this.getConnection();
			String query = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?"; 
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            deleteCount = stmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Inside Model_Employee delete() method");
			e.printStackTrace();
		}
		return deleteCount;
	}



    

}
