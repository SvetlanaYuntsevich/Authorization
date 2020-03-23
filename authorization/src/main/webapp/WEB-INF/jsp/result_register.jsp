<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<title>Success register page</title>
</head>
<body>
	<div align="center" style="margin-top: 100px; margin-bottom: 150px">
		<form action="Controller" method="GET" 
			style="margin: 15px">
			<input type="hidden" name="command" value="sign_out" /> <input
				type="submit" class="btn btn-warning" value="SignOut" />
		</form>
		<div align="center" style="margin-top: 50px; margin-bottom: 400px">
			<table border="1" style="border: 3px ridge DarkBlue">
				<tr>
					<td align="center"
						style="color: DarkBlue; font-weight: bold; font-size: 16px; font-style: italic">
						<p>${param.name}!</p>
						<form action="Controller" method="GET" 
							style="margin: 15px">
							<input type="hidden" name="command" value="to_login" /> <input
								type="submit" class="btn btn-success" value="Continue" />
						</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>