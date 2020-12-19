<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/resources/css/login.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<title>Login Page</title>
</head>
<body>
<form  method="POST" action="/login-user">
  <h1>Employer Log in</h1>
  <p style="color:red" align="center"><c:out value="${error }"></c:out></p>
  <div class="inset">
  <p>
    <label for="username">USERNAME</label>
    <input  style="color:white;"  type="text" name="USER_NAME" id="username" value="${testTraineeUserMast.USER_NAME }" />
  </p>
  <p>
    <label for="password">PASSWORD</label>
    <input style="color:white;" type="password" name="PASSWORD" id="password" value="${testTraineeUserMast.PASSWORD }" />
  </p>
  </div>
  <p class="p-container">
    <input type="submit"  name="go" id="go" value="Log in">
  </p>
</form>

</body>
</html>