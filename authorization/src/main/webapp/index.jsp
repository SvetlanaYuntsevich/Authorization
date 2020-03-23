<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE html>
<html>
   <head>
       <title>Home</title>
   </head>
<body>
    <div align="center" style="margin-top: 100px; margin-bottom: 150px">
		<h1>Let the travel begin!</h1>
	
		<form action="Controller" method="GET">
                          <input type="hidden" name="command" value="to_login" />
                          <button class="btn btn-success" type="submit">SignIn</button>
        </form>    
		<br/>
		<form action="Controller" method="GET" >
						  <input type="hidden" name="command" value="to_register" />
						  <button class="btn btn-success" type="submit">Register</button>
        </form> 
	</div>
</body>
</html>
