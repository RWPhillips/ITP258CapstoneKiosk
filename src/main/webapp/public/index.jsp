<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" type="text/css" href="../styles/index.css">
    <link rel="stylesheet" type="text/css" href="../styles/footer.css">
</head>
<body>
    <section class="fullscreen">
        <div class="header">
            <img src="../images/orderup.png" class="headerImg">
        </div>
        <div class="banner"></div>
        <div class="login-form-container">
            <div class="login-form">
                <h2>Login</h2>
                <form>
                    <div class="grid-container">
                        <div>
                            <label for="username">Username:</label><br> <input
                                type="text" id="username" name="username"><br>
                        </div>
                        <div>
                            <label for="password">Password:</label><br> <input
                                type="password" id="password" name="password"><br>
                        </div>
                        <div>
                            <input type="submit" value="Login" class="login-button">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <jsp:include page="../secure/footer.jsp" />
</body>
</html>
