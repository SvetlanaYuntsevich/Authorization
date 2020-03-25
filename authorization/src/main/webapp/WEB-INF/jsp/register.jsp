<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE html>
<html>
   <head>
   		<title>Register</title>
   </head>
   <body>
   		<div align="center" style="margin-top: 100px; margin-bottom: 150px">
   		<p align="center" style="color: DarkBlue; font-weight: bold">Please register</p>
           
           <form id="regForm" action="Controller" method="post">
           <input type="hidden" name="command" value="register" />
           <input type="hidden" name="userRole" value="client" />
                   <table id="registration-table">
                       <tr>
                           <td>
                                <form:label path="name">Name:</form:label>
                           </td>
                           <td>
                                <input type="text" name="name"  maxlength="15" required="required" title="${name}"/>
                           </td>
                       </tr>
                       <tr>
                            <td>
                                 <form:label path="surname">Surname:</form:label>
                            </td>
                             <td>
                                 <input type="text" name="surname"  maxlength="15" required="required" title="${surname}"/>
                            </td>
                       </tr>
                      
                       <tr>
                             <td>
                                  <form:label path="email">E-mail:</form:label>
                             </td>
                             <td>
                                  <input type="text" name="email" required="required" title="${email}"/>
                             </td>
                       </tr>
                       <tr>
                           <td>
                               <form:label path="login">Login:</form:label>
                           </td>
                           <td>
                                <input type="text" name="login"  maxlength="10" required="required" title="${login}"/>
                           </td>
                       </tr>
                       <tr>
                           <td>
                               <form:label path="password">Password:</form:label>
                           </td>
                           <td>
                               <input type="text" name="password"	
						    maxlength="10" required="required" title="${password}"/>
                           </td>
                       </tr>
                       <tr>
                           <td>
                           <form action="Controller" method="GET" align="center" id="register">
                            <input type="hidden" name="command" value="result_register" />
                            <button class="btn btn-success" type="submit" id="submit">Submit</button>
                           </form>
                           </td>
                          
                       </tr>
                  </table>
           </form>
   </div>
   </body>
</html>