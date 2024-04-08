<jsp:include page="adminheader.jsp" />
	<div class="accounts">
		<h2>Create a new Account</h2>
		<form action="AccountServlet" method="post">
			<label for="userName">User name</label>
			<input type="text" class="" name="userName" maxlength="100"><br>
			<label for="accType" class="">Account type</label>
			<select name="accType" class="">
				<option value="kiosk">Kiosk</option>
				<option value="TicketDisplay">Ticket Display</option>
				<option value="Administrator">Administrator</option>
			</select><br>
			<label for="password">Enter a password</label>
			<input type="password" class="" name="password" maxlength="100"><p>Password must be at least 8 characters long and...</p><br>
			<label for ="confirm">Re-enter the password</label>
			<input type="password" class="" name="confirm" maxlength="100">
			<input type="submit" value="Create">
		</form>
		<h2>Delete an account</h2>
		<form action="AccountServlet" method="post">
			<label for="delUser">Select a User</label>
			<select name="delUser" class="">
			</select><br>
			<label for="password">Enter password to confirm</label>
			<input type="password" name="password" class="">
		</form>
		<h2>Update Account Password</h2>
			<form action="AccountServlet" method="post">
				<label for="updateUser">Select a user</label>
				<select name="updateUser">
				</select><br>
				<label for="updatePass">Enter new Password:</label>
				<input type="password" name="updatePass" class=""><br>
				<label for="confirmUpdate">Re-enter password</label>
				<input type="password" name="confirmUpdate"><br>
			</form>
	</div>
</body>
</html>