<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/footer.css">
</head>
<body>
    <section class="fullscreen">
        <div class="header">
            <img src="${pageContext.request.contextPath}/images/orderup.png" class="headerImg">
        </div>
        <div class="banner"></div>
        <div class="login-form-container">
            <div class="login-form">
                <h2>Login</h2>
                <form action="${pageContext.request.contextPath}/LoginServlet" method="post" class="width">
                    <div class="grid-container">
                        <div>
                            <div class="padForm">
                            	<label for="username" class="padForm">Username:</label>
                           	</div>
                           	<div> 
                           		<input type="text" id="username" name="username" class="padForm">
                           	</div>
                        </div>
                        <div>
                            <div class="padForm">
                            	<label for="password" class="padForm">Password:</label>
                           	</div>
                           	<div>
                           		<input type="password" id="password" name="password" class="padForm">
                           	</div>
                        </div>
                        <div class="padForm"> 
                            <input type="submit" value="Login" class="login-button">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
<jsp:include page="../secure/footer.jsp" />