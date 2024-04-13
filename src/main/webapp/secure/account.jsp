<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="adminheader.jsp" />
<section class="content">
	<div class="accounts">
		<div class="create">
			<h2>Create a New Account</h2>
			<form action="${pageContext.request.contextPath}/CreateAccountServlet" method="post" class="accountForm">
				<div class="padform">
					<label for="userName">User Name: </label>
				</div>
				<div class="padform">
					<input type="text" class="" name="userName" maxlength="100">
				</div>
				<div class="padform">
					<label for="accType" class="">Account Type:</label>
				</div>
				<div class="padform">
				<select name="accType" class="">
					<option value="kiosk">Kiosk</option>
					<option value="TicketDisplay">Ticket Display</option>
					<option value="Administrator">Administrator</option>
				</select></div>
				<div class="padform">
					<label for="createPass">Enter a Password:</label><br>
				</div>
				<div class="padform">
					<input type="password" class="" name="createPass" maxlength="100" required pattern="^[\\p{ASCII}&&[\\S]]{8,}$" title="Password must be a minimum of 8 characters.">
				</div>
				<div class="padform">
					<label for ="confirmCreate">Re-enter the Password:</label><br>
				</div>
				<div class="padform">
					<input type="password" class="" name="confirmCreate" maxlength="100"><br>
				</div>
				<div class="padform">
					<input class="accountButton" type="submit" value="Create">
				</div>
			</form>
		</div>
		<div class="delete">
			<h2>Delete an Account</h2>
			<form action="${pageContext.request.contextPath}/DeleteAccountServlet" method="post" class="accountForm">
				<div class="padform">
					<label for="delUser">Select a User: </label>
				</div>
				<div class="padform">
					<select name="delUser" class="" hx-trigger="load" hx-get="${pageContext.request.contextPath}/GetAccountServlet" >
						<!-- When hx-get does its thing, the options are automatically returned as the request from the Servlet -->
						<!-- I really recommend looking at how it's done in the GetAccountServlet, very cool stuff. -->
					</select><br>
				</div>
				<div class="padform">
					<label for="deletePass">Enter Password to Confirm: </label>
				</div>
				<div class="padform">
					<input type="password" name="deletePass" class="">
				</div>
				<div class="padform">
					<input class="accountButton" type="submit" value="Delete">
				</div>
			</form>
		</div>
		<div class="update">
			<h2>Update Account Password</h2>
			<form action="${pageContext.request.contextPath}/UpdatePasswordServlet" method="post" class="accountForm">
				<div class="padform">
					<label for="updateUser">Select a User: </label>
				</div>
				<div class="padform">	
					<select name="updateUser" class="" hx-trigger="load" hx-get="${pageContext.request.contextPath}/GetAccountServlet" >
					</select><br>
				</div>
				<div class="padform">
					<label for="updatePass">Current Password:</label><br>
				</div>
				<div class="padform">
					<input type="password" name="updatePass" class="password">
				</div>
				<div class="padform">
					<label for="confirmUpdate">New Password: </label>
				</div>
				<div class="padform">
					<input type="password" name="confirmUpdate"><br>
				</div>
				<div class="padform">
					<input class="accountButton" type="submit" value="Update">
				</div>
			</form>
		</div>
	</div>
</section>
<jsp:include page="footer.jsp" />