<!DOCTYPE html>
<html>
   <head>
   <title>Login</title>
   </head>
   <body>

   <div align="center" style="margin-top: 100px; margin-bottom: 150px">
        <form id="loginForm" action="Controller" method="post">
              <input type="hidden" name="command" value="sign_in" />
              <table id="sign_in-table">
              <tr>
                    <td><form:label path="sign_in">Login:</form:label></td>
                    <td><input type="text" name="login" placeholder="Mishka123"  maxlength="10" required="required" title="${login}"/></td>
              </tr>
              <tr>
                    <td><form:label path="password">Password:</form:label></td>
                    <td><input type="text" name="password" placeholder="qwerty"  maxlength="10" required="required" title="${password}"/></td>
              </tr>
              <tr>
                    <td>
							<form action="Controller" method="GET"  align="center">
                          <input type="hidden" name="command" value="welcome" />
                          <button class="btn btn-success" type="submit">Submit</button>
        </form>      
        </td>     
            </tr>
              </table>
        </form>   
        
        </div>           
          </body>
</html>