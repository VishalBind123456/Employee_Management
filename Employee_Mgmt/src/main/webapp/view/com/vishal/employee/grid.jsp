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
	<div class="container my-3">
		<div>
			<button type="button" id="add">Add New</button>
		</div>
		<% ArrayList<Model_Employee> emp_list = (ArrayList<Model_Employee>)request.getAttribute("emp_list"); %>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Name</th>
					<th scope="col">Gender</th>
					<th scope="col">BirthDate</th>
					<th scope="col">Address</th>
					<th scope="col">Salary</th>
				</tr>
			</thead>
			<tbody>
			<% for(int idx = 0 ; idx < emp_list.size() ; idx++){ %>
				<% Model_Employee me = emp_list.get(idx); %>
				<tr>
					<th scope="row"><%= (idx+1) %></th>
					<td><%= me.get("Name") %></td>
					<td><%= me.get("Gender") %></td>
					<td><%= me.get("BirthDate") %></td>
					<td><%= me.get("Address") %></td>
					<td><%= me.get("Salary") %></td>
					<td><button type="button" class="edit" name="employeeId" value=<%= me.get("employeeId") %> > Edit </button></td>
					<td><button type="button" class="delete" name="employeeId" value=<%= me.get("employeeId") %> > Delete</button></td>
				</tr>
			<% } %>
		</tbody>	
		</table>
	</div>
</body>
<script>
	jQuery("#add").on({
		click : function(){
			var empId = jQuery(this).val();
			url = "http://localhost:8080/Employee_Mgmt/controller_employee?action=upsert&employeeId=-1";
			window.location.href = url;
		}
	});
	jQuery(".edit").on({
		click : function(){
			var empId = jQuery(this).val();
			url = "http://localhost:8080/Employee_Mgmt/controller_employee?employeeId="+empId+"&action=upsert";
			window.location.href = url;
		}
	});
	jQuery(".delete").on({
		click : function(){
			var empId = jQuery(this).val();
			var resp = window.confirm("Are you sure you want to delete employee ? ");
			if(resp == true)
			{
				url = "http://localhost:8080/Employee_Mgmt/controller_employee?employeeId="+empId+"&action=delete";
				window.location.href = url;				
			}
			
		}
	});

</script>
</html>
