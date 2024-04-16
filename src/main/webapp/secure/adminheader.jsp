<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderUp Administration Panel</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/headerstyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/account.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css">
<script src="https://unpkg.com/htmx.org@1.9.3"></script>



</head>
<body>
	<div class="imgPad"> 
		<img src="${pageContext.request.contextPath}/images/orderup.png" class="headerImg">
	</div>
	<header> 
		<nav class="nav">
			<ul class="header-ul">
				<li><a class="header-li" href="${pageContext.request.contextPath}/secure/account.jsp">Account Management</a></li>
				<li><a class="header-li" href="${pageContext.request.contextPath}/secure/create-category.jsp">Manage Categories</a></li>
				<li><a class="header-li" href="${pageContext.request.contextPath}/secure/create-item.jsp">Manage Items</a></li>
				<li><a class="header-li" href="${pageContext.request.contextPath}/secure/create-subitem-category.jsp">Manage Subitem Categories</a></li>
				<li><a class="header-li" href="${pageContext.request.contextPath}/secure/create-subitem.jsp">Manage Subitems</a></li>
			</ul>
		</nav>
	</header>
