<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<section>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login Form</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            background-color: #143D59;
            margin: 0;
            padding: 0;
        }
        .header {
            background-image: url('orderup.png');
            background-size: cover;
            height: 150px;
        }
        .banner {
            background-color: #143D59;
            height: 10px;
        }
        .login-form {
            margin: 0 auto;
            width: 300px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            margin-top: 50px;
        }
        .footer {
            background-color: #143D59;
            color: #ffffff;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

<div class="header"></div>

<div class="banner"></div>

<div class="login-form">
    <h2>Login</h2>
    <form>
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
</div>


</body>
</html>
</section>
<jsp:include page="footer.jsp" />