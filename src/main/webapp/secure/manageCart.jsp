<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordering Kiosk</title>

</head>
<body>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="cartManagement">
	<h2 class="manageHeader" hx-trigger="load" hx-get="/ITP258CapstoneKiosk/DisplayCartServlet" hx-target=".cartcontainer">Manage Your Order</h2>
	<form class="orderItems">
		<c:forEach items="${sessionScope.cart}" var="item">
			<div class="manageCart">
				<div class="fullItem">
					<div class="subPCont"><input type="checkbox" class=".hidden" id="${item.name}"><label class="subLabel" for ="${item.name}"><span class="displayName" manageFont>${item.name}</span><span class="displayCost manageFont">$${item.cost}</span></label></div>
					<c:if test= "${item.subItems != null}">
						<c:forEach items="${item.subItems}" var="values">
							<div class="cartSub subFont">${values.name}</div><div class="subCost subFont">$${values.cost}</div>
							
						</c:forEach>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</form>
	<div class="total">
	</div>
</div>

</body>
</html>