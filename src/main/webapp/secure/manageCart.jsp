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
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="cartManagement">
	<h2 class="manageHeader" hx-trigger="load" hx-get="/ITP258CapstoneKiosk/DisplayCartServlet" hx-target=".cartcontainer">Manage Your Order</h2>
	<form class="orderItems orderText" hx-get="/ITP258CapstoneKiosk/RemoveCartServlet" hx-target=".cartManagement">
		<div class="sticky">
			<c:forEach items="${sessionScope.cart}" var="item">
				<div class="manageCart">
					<div class="fullItem">
						<div class="subPCont">
							<input type="checkbox" name="parentItem" class="hiddenBox" id="${item.id}" value="${item.id}">
							<label class="subLabel" for ="${item.id}"><span class="displayName manageFont">${item.name}</span><span class="displayCost manageFont"><fmt:formatNumber value = "${item.cost}" type = "currency"/></span></label>
						</div>
						<c:if test= "${item.subItems != null}">
							<c:forEach items="${item.subItems}" var="values">
								<div class="subItemC"> 
									<input type="checkbox" name="subItems" class="hiddenBox" id="${values.id}" value="${values.id}">
									<input type="hidden" name="parentID" value="${item.id}">
									<label class="subItemLabel" for="${values.id}"><span class="displayName manageSubFont">${values.name}</span><span class="displayCost manageSubFont"><fmt:formatNumber value = "${values.cost}" type = "currency"/></span></label>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	<div class="manageButtons">
		<button class="removeItems">Remove Items From Cart</button>
		<button class="complete" hx-get="/ITP258CapstoneKiosk/CompleteOrderServlet">Complete Order</button>
	</div>
	</form>
</div>


</body>
</html>