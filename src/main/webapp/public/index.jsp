<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" type="text/css" href="../styles/index.css">
</head>
<body>
    <section>
        <div class="imgPad">
            <img src="../images/orderup.png" class="headerImg">
        </div>
        <div class="banner"></div>
        <div class="login-form-container">
            <div class="login-form">
                <h2>Login</h2>
                <form>
                    <div class="grid-container">
                        <div>
                            <label for="username">Username:</label><br>
                            <input type="text" id="username" name="username"><br>
                        </div>
                        <div>
                            <label for="password">Password:</label><br>
                            <input type="password" id="password" name="password"><br>
                        </div>
                        <div>
                            <input type="submit" value="Login">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
<jsp:include page="footer.jsp" />
