<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registeration Page</title>
<link rel="stylesheet" href="/resources/css/register.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/resources/js/showData.js" type="text/javascript"></script>

</head>
<body>

	<div id="main_container">
		<div id="header">Registeration User</div>
		<hr>
		<form action="/save-user" method="post" enctype="multipart/form-data">
			<div id="main">
				<table id="table_register">
					<p style="color: red; text-align: center;">${name}</p>
					<tr>
						<td>Employee Name :</td>
						<td style="border-collapse: collapse;"><input type="text"
							placeholder="FirstName" value="${user.EMP_NAME}" name="EMP_NAME"
							id="EMP_NAME" pattern="[a-zA-Z ]{1,30}"
							title="Please Enter only Alphabets" required>
							<span id="lblError" style="color: red"></span>
							</td>

					</tr>
					<tr>
						<td id="load">DOB :</td>
						<td><input type="date" placeholder="Date of Birth"
							name="BIRTH_DATE" value="${user.BIRTH_DATE}" id="dob" required></td>
					</tr>
					<tr>
						<td>Gender :</td>
						<td><c:set var="gender" value="${user.GENDER}" /> Male <input
							type="radio" name="GENDER" value="M" required="required"
							<c:if test="${gender == 'M'}">checked</c:if>> Female <input
							type="radio" name="GENDER" value="F" required="required"
							<c:if test="${gender == 'F'}"> checked</c:if>></td>
					</tr>
					<tr>
						<td>Designation Code :</td>
						<td><c:set var="code" value="${user.DESIG_CODE}" /> <select
							name="DESIG_CODE" id="DESIG_CODE" value="${user.DESIG_CODE}"
							required>
								<option value="TT"
									<c:if test="${code == 'TT'}">selected="selected"</c:if>>Technical
									Trainee</option>
								<option value="TM"
									<c:if test="${code == 'TM'}">selected="selected"</c:if>>Technical
									Member</option>
								<option value="PL"
									<c:if test="${code == 'PL'}">selected="selected"</c:if>>Project
									Leader</option>
								<option value="PM"
									<c:if test="${code == 'PM'}">selected="selected"</c:if>>Project
									Manager</option>
								<option value="MD"
									<c:if test="${code == 'MD'}">selected="selected"</c:if>>Managing
									Director</option>
						</select></td>
					</tr>
					<tr>
						<td>Email id :</td>
						<td><input type="email" placeholder="Email id" name="EMAIL"
							value="${user.EMAIL}" id="emailid"
							pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
							required></td>
					</tr>
					<tr>
						<td>Mobile No :</td>
						<td><input type="text" placeholder="Mobile No" name="MOBILE"
							value="${user.MOBILE}" id="MOBILE" pattern="[7-9]{1}[0-9]{9}"
							title="Please Provide Valid Number" maxlength="10" required>
							<span id="lblErrorr" style="color: red"></span></td>
					</tr>
					<tr>
						<td>Address :</td>
						<td><input name="ADDRESS" id="address" type="text"
							placeholder="Address" value="${user.ADDRESS}" maxlength="30"
							pattern="[(A-Z)(a-z)(0-9)(.)(-)(/)(\s)(,)]{1,30}"
							title="Please Provide Alphabets,Backlash,dot,comma and numbers whitespace only"
							required></td>
					</tr>
					<tr>
						<td>Choose Image :</td>
						<c:set var="code" value="${edit}" />
						<td ><input type="file" name="EMP_PHOTO" id="image"  <c:if test="${code == 'edit'}">hidden</c:if>
							multiple="multiple"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="REG_DATE"
							value="<fmt:formatDate value="${user.REG_DATE}" type="date" pattern="yyyy-MM-dd"/>" /></td>
					</tr>
					<tr>
						<td><input type="hidden" name="EMP_CODE"
							value="${user.EMP_CODE}" /></td>
					</tr>
					<c:set var="code" value="${register}" />
					<tr  <c:if test="${code == 'register'}">hidden</c:if> >
					<c:if test = "${user.base64Image != null && user.base64Image !='' }" >
					<td><img  src="data:image/jpg;base64,${user.base64Image}" width="240" height="300" /></td>
					</c:if>
					<c:if test = "${user.base64Image == null || user.base64Image ==''}" >
					<td><img  src="image.jpg" width="240" height="300"/></td>
					</c:if>
					</tr> 
					
				</table>
				<center>
					<button id="btn_Register" type="submit">Register</button>
					<button id="btn_update" formaction="/update?id=${user.EMP_CODE}">Update</button>
					 <button  id="btn_delete"  formaction="/delete-user?id=${user.EMP_CODE}">Delete</button>
				</center>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		document.getElementById("dob").value = "<fmt:formatDate value="${user.BIRTH_DATE}" type="date" pattern="yyyy-MM-dd"/>";
	</script>

</body>
</html>