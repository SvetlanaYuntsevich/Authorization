
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
   <title>Login</title>
        <%@include file="header.jsp" %>
        <%@ include file="include.jsp" %>
        <style type="text/css"><%@include file="/resources/css/style.css"%></style>

        <fmt:setLocale value="${sessionScope.localization}"/>
        <fmt:setBundle basename="localization.local" var="local"/>

        <fmt:bundle basename="localization">
              <fmt:message key="local.login" var="login"/>
              <fmt:message key="local.password" var="password"/>
              <fmt:message key="local.send" var="send"/>
        </fmt:bundle>

   </head>

   <body>
   <div class="wrapper">
   <div class="content" align="center">
        <form id="loginForm" action="Controller" method="post">
              <input type="hidden" name="command" value="sign_in" />
              <fieldset >
 					<legend style="color:DarkBlue; font-weight:bold">
 					</legend>
				    <label for="login">${login}</label> <br>
				    <input type="text" name="login" id="login" maxlength="15" required="required" placeholder="Johnny" title="${login}" /><br>
				    <label for="password">${password}</label> <br>
				    <input type="password" name="password" id="password" maxlength="15" required="required" placeholder="Qwerty123" title="${password}" /><br>
				    <br>
				    <input type="submit" class="btn btn-success"
										value="${send}" />				
    		  </fieldset>
        </form>
   </div>
   </div>

   <%@ include file="footer.jsp" %>
   </body>
</html>