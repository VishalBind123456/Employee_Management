package com.vishal.Controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.vishal.Model.Model_Employee;

public class Controller_Employee extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException 
	{
		try
		{
			String action = String.valueOf(req.getParameter("action"));
			System.out.println(action + "<----");
			
			if(action == null)
			{
				res.getWriter().println("<h1>Invalid Request...<h1>"); 
			}
			switch(action)
			{
				case "grid":
					this.grid(req , res);
					break;
				case "upsert":
					this.upsert(req , res);
					break;
				case "save":
					this.save(req , res);
					break;
				case "delete":
					this.delete(req , res);
					break;
				default:
					 res.getWriter().println("<h1>Invalid Request...<h1>"); 
			}
		}
		catch(Exception e)
		{
			System.out.println("Inside Controller_Employee service() method");
			e.printStackTrace();
		}
	}

	public void grid(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException 
	{
		Model_Employee me = new Model_Employee();
		ArrayList<Model_Employee> emp_list = me.getAllEmployee();
		req.setAttribute("emp_list" , emp_list);
		RequestDispatcher rd = req.getRequestDispatcher("/view/com/vishal/employee/grid.jsp");
		rd.forward(req , res);

	}
	
	public void upsert(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException
	{
		int empId = Integer.parseInt(req.getParameter("employeeId"));
		Model_Employee me = new Model_Employee();
		if(empId > 0)
		{
			me.load(empId);
		}
		req.setAttribute("emp" , me);
		RequestDispatcher rd = req.getRequestDispatcher("/view/com/vishal/employee/upsert.jsp");
		rd.forward(req , res);
	}

	public void save(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException
	{
		Model_Employee me = new Model_Employee();
		for(Map.Entry<String , String[]> entry : req.getParameterMap().entrySet())
		{
			String key = String.valueOf(entry.getKey());
			String value = String.valueOf(entry.getValue()[0]);
			me.set(key , value);
		}
		me.save();
		this.grid(req, res);
		
	}

	public void delete(HttpServletRequest req , HttpServletResponse res) throws ServletException , IOException
	{
		res.getWriter().println("<h1>Delete Action<h1>");
		int empId = Integer.parseInt(req.getParameter("employeeId"));
		Model_Employee me = new Model_Employee();
		int count = me.delete(empId);
		System.out.println(count + " <--- deleted");
		this.grid(req, res);
	}







	/* public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException
	{
	}

	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException
	{
	} */


}
