<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<div class="welcome" align="center">
		<h2>Welcome!</h2>
		<form action="Controller" method="GET" style="margin: 15px">
			<input type="hidden" name="command" value="sign_out" /> 	
			<input type="submit" class="btn btn-warning" value="SignOut" />
		</form>
	</div>
</html>