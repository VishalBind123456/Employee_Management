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

    public int update(String tableName , String primaryKey , String id , HashMap<String,String> map)
    {
        int rs = 0;
        try
        {
            Connection conn = this.getConnection();

            String query = " UPDATE " + tableName + " SET ";
            int counter = 0;
            for(Map.Entry<String,String> entry : map.entrySet())
            {
                String str = entry.getKey() + " = ? ";
                str = (counter != 0) ? " , " + str : str;
                query += str;
                counter++;
            }
            query += "WHERE " + primaryKey + " = " + id; 
            PreparedStatement stmt = conn.prepareStatement(query);
            counter = 1;
            for(Map.Entry<String,String> entry : map.entrySet())
            {
                stmt.setString(counter , entry.getValue());
                counter++;
            }
            System.out.println(query);
            rs = stmt.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Inside Adapter update() method");
            e.printStackTrace();
        }
        return rs;
    }

    public int insert(String tableName , Map<String,String> map)
    {
        int rs = 0;
        try
        {
            Connection conn = this.getConnection();

            String query = " INSERT INTO " + tableName + " ( ";
            int counter = 0;
            String data = "";
            for(Map.Entry<String,String> entry : map.entrySet())
            {
                String str = entry.getKey();
                String str2 = " ? ";
                str = (counter != 0) ? " , " + str : str;
                str2 = (counter != 0) ? " , " + str2 : str2;
                data += str2;
                query += str;
                counter++;
            }
            query += " ) VALUES ( " + data + " ) ";
            System.out.println(query);
            PreparedStatement stmt = conn.prepareStatement(query);
            counter = 1;
            for(Map.Entry<String,String> entry : map.entrySet())
            {
            	stmt.setString(counter , entry.getValue());
                counter++;
            }            
            rs = stmt.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println("Inside Adapter insert() method");
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
