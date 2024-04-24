<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Kiosk</title>

</head>
<body>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="cart">
	<h2 class="cartHeader">Your Order</h2>
	<div class="orderItems">
		<c:forEach items="${sessionScope.cart}" var="item">
			<div class="shoppingCart">
				<div class="cartItem">${item.name}</div><div class="cartCost">$${item.cost}</div>
				<c:if test= "${item.subItems != null}">
					<c:forEach items="${item.subItems}" var="values">
						<div class="cartSub subFont">${values.name}</div><div class="subCost subFont">$${values.cost}</div>
					</c:forEach>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div class="total">
		<div>Order total:</div> <div class="totalright">${requestScope.cartTotal}</div> 
	</div>
</div>

</body>
</html>