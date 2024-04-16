<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/cart.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/order.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/category.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/orderfooter.css">

<title>Select a Category</title>
</head>
<body>
<div class="container">
	<h1>Choose an item</h1>
	<div class="wrapper">
		<jsp:include page="select-category.jsp" />
	

		<section>
		<div class="content">
			<h2 class="title"></h2>
		</div>
	
		</section>
		<jsp:include page="cart.jsp" />
	</div>
	<jsp:include page="orderfooter.jsp" />
</div>
</body>
</html>