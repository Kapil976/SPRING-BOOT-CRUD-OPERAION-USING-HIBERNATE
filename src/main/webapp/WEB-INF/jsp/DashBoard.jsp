<%-- 
    Document   : DashBoard
    Created on : Nov 26, 2020, 5:43:26 PM
    Author     : Kapil Gupta
--%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/dashboard.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/resources/js/showData.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DashBoard</title>
</head>
<body>
	<%--  <p style="color:green; font-size: 20px;" align="center"><c:out value="${save}"></c:out></p> --%>
	<%-- <%  response.setHeader("Cache-Control", "no-cache, nostore, must-revalidate"); %> --%>
	<div id="main_container">
		<form action="/register">
			<div id="header">
				Employee Dashboard
				<button
					style="float: right; background-color: #e7e7e7; color: black; font-size: 12px; padding: 1px 40px;" onclick="onAdd();" >Add</button>
			</div>
		</form>

		<hr>
		<form action="/filter" method="post">
			<div id="main">

				<p style="color: blue; font-size: 20px;">Filter</p>
				<p style="color: red; font-size: 20px;" align="center">
					<c:out value="${filter}"></c:out>
					<c:out value="${delete}"></c:out>
					<c:out value="${noDataFound}"></c:out>
				</p>
				<table id="table_dashboard" style="width: 100%;">
					<tr>
						<td>Designation</td>
						<td>Emp Name <span id="lblError" style="color: red"></span></td>
						<td>Mobile No <span id="lblErrorr" style="color: red"></span></td>
						<td>Registration Date <span id="Date" style="color: red"></span></td>
					</tr>
					<tr>
						<td><select name="DESIG_CODE">
								<option disabled selected value>-- select an code --</option>
								<option value="TT">Technical Trainee</option>
								<option value="TM">Technical Member</option>
								<option value="PL">Project Leader</option>
								<option value="PM">Project Manager</option>
								<option value="MD">Managing Director</option>
						</select></td>
						<td><input type="text" name="EMP_NAME"
							id="EMP_NAME" pattern="[a-zA-Z(\s)]{1,30}" title="Please Enter only Alphabets"></td>
						<td><input type="text" name="MOBILE"
						id="MOBILE"	pattern="[7-9]{1}[0-9]{9}" title="Please Provide Valid Number"
							maxlength="10"></td>
						<td><input id="REG_DATE" type="text" name="REG_DATE" pattern="^\d{2}\/\d{2}\/\d{4}$" title="Please valid pattern[mm/dd/yyyy]" maxlength="10"></td>
							
					</tr>
				</table>
				<br>
				<center>
				<button id="btn_Register"
						style="background-color: #e7e7e7; color: #3385ff; font-size: 12px; padding: 4px 40px;"
						type="submit" formaction="/showAll">ShowAllEmployee</button>
					<button id="btn_Register"
						style="background-color: #e7e7e7; color: green; font-size: 12px; padding: 4px 40px;"
						type="submit">Show</button>

					<input type="reset"
						style="background-color: #e7e7e7; color: red; font-size: 12px; padding: 4px 40px;"
						name="clear" value="clear">
				</center>
			</div>
		</form>
		<hr>
		<div id="maindashboard">
			<div id="user-table">
				<table id="main_table" style="width: 100%;">
					<tr>
						<th colspan="2">Action</th>
						<th>EMP_NAME</th>
						<th>DESIGN_CODE</th>
						<th>BIRTH</th>
						<th>GENDER</th>
						<th>EMAIL</th>
						<th>MOBILE</th>
						<th>ADDRESS</th>
						<th>LOGIN_USER</th>
					</tr>
					<c:forEach var="user" items="${users}">
						<tr>
							<td class="td"><a  style="background-color: #e7e7e7; color:black; font-size: 12px; padding: 2px 20px;" onclick="onEdit();"
							onmouseover="this.style.color = 'green'" onmouseout="this.style.color = 'black'"	 href="/edit-user?id=${user.EMP_CODE }">Edit</a></td>
							<td class="td"><a style="background-color: #e7e7e7; color:black; font-size: 12px; padding: 2px 20px;"
							onclick="onDelete();" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = 'black'"	href="/edit-user?id=${user.EMP_CODE}">Delete</a></td>
							<td class="td">${user.EMP_NAME}</td>
							<td class="td">${user.DESIG_CODE}</td>
							<td class="td">
							     <fmt:formatDate value="${user.BIRTH_DATE}" type="date" pattern="dd-MM-yyyy"/>
							</td>
							<td class="td">${user.GENDER}</td>
							<td class="td">${user.EMAIL}</td>
							<td class="td">${user.MOBILE}</td>
							<td class="td">${user.ADDRESS}</td>
							<td class="td">${user.LOGIN_USER}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

</body>

</html>
