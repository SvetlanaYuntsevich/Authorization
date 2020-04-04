
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
   <div class="content">
        <form id="loginForm" action="Controller" method="post">
              <input type="hidden" name="command" value="sign_in" />
              <table id="login-table">
              <tr>
                    <td><form:label path="login">${login}</form:label></td>
                    <td><input type="text" name="login"  maxlength="10" placeholder="Mishka123" required="required" title="${login}"/></td>
              </tr>
              <tr>
                    <td><form:label path="password">${password}</form:label></td>
                    <td><input type="password" name="password"  maxlength="10" placeholder="Qwerty123" required="required" title="${password}"/></td>
              </tr>
              <tr>
                    <td></td>
                    <td><input type="submit" class="btn btn-success" value="${send}"/></td>
              </tr>
              </table>
        </form>
   </div>
   </div>

   <%@ include file="footer.jsp" %>
   </body>
</html>