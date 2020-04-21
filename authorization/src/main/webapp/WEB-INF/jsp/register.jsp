<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<%@include file="header.jsp"%>
<%@ include file="include.jsp"%>
<style type="text/css">
<%@include file="/resources/css/style.css"%>
</style>

<fmt:setLocale value="${sessionScope.localization}" />
<fmt:setBundle basename="localization.local" var="local" />

<fmt:bundle basename="localization">
	<fmt:message key="local.registrMessage" var="registrMessage" />
	<fmt:message key="local.name" var="name" />
	<fmt:message key="local.surname" var="surname" />
	<fmt:message key="local.money" var="money" />
	<fmt:message key="local.email" var="email" />
	<fmt:message key="local.login" var="login" />
	<fmt:message key="local.password" var="password" />
	<fmt:message key="local.send" var="send" />
</fmt:bundle>

</head>

<body>
	<div class="wrapper">
		<div class="content" align="center">
			<form id="regForm" action="Controller" method="post">
				<input type="hidden" name="command" value="register" /> 
				<input type="hidden" name="userRole" value="client" />
				<!-- <p align="center" style="color: DarkBlue; font-weight: bold">${registrMessage}</p> -->
				<fieldset >
 					<legend style="color: DarkBlue; font-weight: bold">
 					${registrMessage}
 					</legend>
				    <label for="name">${name}</label> <br>
				    <input type="text" name="name" id="name" maxlength="15" required="required" placeholder="John" title="${name}" /><br>
					<label for="surname">${surname}</label> <br>
				    <input type="text" name="surname" id="surname" maxlength="15" required="required" placeholder="Smith" title="${surname}" /><br>
					<label for="email">${email}</label> <br>
				    <input type="email" name="email" id="email" maxlength="15" required="required" placeholder="j.smith@gmail.com" title="${email}" /><br>
				    <label for="login">${login}</label> <br>
				    <input type="text" name="login" id="login" maxlength="15" required="required" placeholder="Johnny" title="${login}" /><br>
				    <label for="password">${password}</label> <br>
				    <input type="password" name="password" id="password" maxlength="15" required="required" placeholder="Qwerty123" title="${password}" /><br>
				    <input type="submit" class="btn btn-success"
										value="${send}" />				
    			</fieldset>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>