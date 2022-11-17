package com.vishal.Model;

import java.util.*;
import java.sql.*;
import com.vishal.Model.Adapter;

public class Model_Employee
{
	protected HashMap<String , String> map = new HashMap<String , String>();
	protected Adapter adapter = null;
	
	public ArrayList<Model_Employee> getAllEmployee()
	{
		ArrayList<Model_Employee> emp_list = new ArrayList<Model_Employee>();
		try
		{
			ResultSet rs = this.getAdapter().getAll("Employee");
			ResultSetMetaData md = rs.getMetaData();
			Model_Employee me = null;
			while(rs.next())
			{
				me = new Model_Employee();
				for(int i = 1; i <= md.getColumnCount(); i++)
				{
					String key = String.valueOf(md.getColumnName(i));
					String val = String.valueOf(rs.getObject(i));
					// System.out.println(key + " " + val);
					me.set(key, val);
				}
				emp_list.add(me);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Inside Model_Employee getAllEmployee() method");
			e.printStackTrace();
		}
		return emp_list;
	}

	public void load(int id)
	{
		try
		{
			ResultSet rs = this.getAdapter().getOne("Employee" , "employeeId" , id);
			ResultSetMetaData md = rs.getMetaData();

			while(rs.next())
			{
				for(int i = 1; i <= md.getColumnCount(); i++)
				{
					String key = String.valueOf(md.getColumnName(i));
					String val = String.valueOf(rs.getObject(i));
					this.set(key, val);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Inside Model_Employee load() method");
			e.printStackTrace();
		}

	}

	public int delete(int id)
	{
		int deleteCount = 0;
		try
		{
			deleteCount = this.getAdapter().delete("Employee" , "employeeId" , id);
		}
		catch(Exception e)
		{
			System.out.println("Inside Model_Employee delete() method");
			e.printStackTrace();
		}
		return deleteCount;

	}
	
	public Model_Employee save()
	{
		try
		{
			String query = "";
			if(this.get("employeeId").equals("-1"))
			{
				query = "INSERT INTO Employee" + 
							"(Name , Salary , Gender , Address , BirthDate) " + 
						"VALUES(" + 
								"'" + this.get("Name") + "'" + "," + 
								"'" + this.get("Salary") + "'" + "," + 
								"'" + this.get("Gender") + "'" + "," + 
								"'" + this.get("Address") + "'" + "," +
								"'" + this.get("BirthDate") + "'" +
							")";
			}
			else
			{
				query = "UPDATE Employee SET Name = " + "'" + this.get("Name") + "'" + 
										",Salary = " + "'" + this.get("Salary") + "'" + 
										",Gender = " + "'" + this.get("Gender") + "'" + 
										",Address = " + "'" + this.get("Address") + "'" + 
										",BirthDate = " + "'" + this.get("BirthDate") + "'" + 
										"WHERE employeeId = " + "'" + this.get("employeeId") + "'";
			}
			System.out.println(query);
			this.getAdapter().upsert(query);
		}
		catch(Exception e)
		{
			System.out.println("Inside Model_Employee save() method");
			e.printStackTrace();
		}
		return this;
		
	}
	
	public Model_Employee set(String key , String value)
	{
		this.map.put(key, value);
		return this;
	}
	
	public String get(String key)
	{
		if(!this.map.containsKey(key))
		{
			return "";
		}
		return this.map.get(key);
	}

	//--------------------------------------------------------------------
	public Adapter getAdapter()	
	{
		if(this.adapter == null)
		{
			this.adapter = new Adapter();
		}
		return this.adapter;
	}
	

}
