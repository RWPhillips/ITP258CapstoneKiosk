<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Food Ordering Kiosk</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/splash.css">
<script src="https://unpkg.com/htmx.org@1.9.3"></script>
</head>
<body hx-on:click="window.location.href='${pageContext.request.contextPath}/secure/order.jsp'">
    <div class="background-image">
        <img class="bgimg" src="${pageContext.request.contextPath}/images/foodsplash.png">
    </div>
    <footer class="footer">
        <div class="footer-content">
            <p class="copyright">Copyright &copy;2024 Team-One Associates, Inc. All rights reserved.</p>
            <button class="continue-button">Tap anywhere to continue</button>
        </div>
    </footer>
</body>
</html>