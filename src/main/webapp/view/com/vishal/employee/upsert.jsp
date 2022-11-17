<%@ page language="java" contentType="text/html; charset=UTF-8" 
	import="com.vishal.Model.Model_Employee , java.util.*"
    pageEncoding="UTF-8" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<!-- JQueryuery only -->
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>

</head>
<body>
	<% Model_Employee me = (Model_Employee)request.getAttribute("emp"); %>
	<div class="container my-3">
		<% String empId = (me.get("employeeId").equals("")) ? "-1" : me.get("employeeId"); %>	
		<% String url = "http://localhost:8080/Employee_Management/controller_employee?action=save&employeeId=" + empId; %>
		<form action=<%= url %> method="post">
			<table class="table">
				<tr>
					<td><label for="Name">Employee Name</label></td>
					<td><input type="text" id="Name" placeholder="Enter your name" name="Name" value=<%= me.get("Name") %> ></td>
				</tr>
				<tr>
					<td><label for="Male">Male</label></td>
					<td><input type="radio" id="Male" name="Gender" value="1" <% if(me.get("Gender").equals("1")){ %> <%= "checked" %>  <% } %> ></td>
				</tr>
				<tr>
					<td><label for="Female">Female</label></td>
					<td><input type="radio" id="Female" name="Gender" value="2" <% if(me.get("Gender").equals("2")){ %> <%= "checked" %>  <% } %> ></td>
				</tr>
				<tr>
					<td><label for="Others">Others</label></td>
					<td><input type="radio" id="Others" name="Gender" value="3" <% if(me.get("Gender").equals("3")){ %> <%= "checked" %>  <% } %> ></td>
				</tr>
				<tr>
					<td><label for="BirthDate">BirthDate</label></td>
					<td><input type="date" id="BirthDate" name="BirthDate" value=<%= me.get("BirthDate") %> ></td>
				</tr>
				<tr>
					<td><label for="Salary">Salary</label></td>
					<td><input type="number" step="0.01" id="Salary" name="Salary" value=<%= me.get("Salary") %> ></td>
				</tr>
				<tr>
					<td><label for="Address">Address</label></td>
					<td><textarea id="Address" name="Address" placeholder="Enter Your Address"><%= me.get("Address") %></textarea></td>
				</tr>
				<tr>
					<td><button type="submit">Save</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script>
	
</script>
</html>
