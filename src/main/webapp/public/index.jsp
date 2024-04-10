<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
<link rel="stylesheet" type="text/css" href="account.css">
</head>
<body>
	<section>

		<div class="imgPad">
			<img src="../images/orderup.png" class="headerImg">
		</div>
		<div class="banner"></div>
		<div class="login-form">
			<h2>Login</h2>
			<form>
				<div class="grid-container">
					<label for="username">Username:</label><br> <input type="text"
						id="username" name="username"><br>
				</div>
				<div>
					<label for="password">Password:</label><br> <input
						type="password" id="password" name="password"><br>
				</div>
				<br>
				<div>
					<input type="submit" value="Login">
				</div>
			</form>
		</div>
	</section>
	<jsp:include page="../secure/footer.jsp" />
</body>
</html>
