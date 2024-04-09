<jsp:include page="adminheader.jsp" />
	<div class="accounts">
		<div class="create">
			<h2>Create a new Account</h2>
			<form action="AccountServlet" method="post">
				<div class="padform">
					<label for="userName">User name: </label>
				</div>
				<div class="padform">
					<input type="text" class="" name="userName" maxlength="100">
				</div>
				<div class="padform">
					<label for="accType" class="">Account type:</label>
				</div>
				<div class="padform">
				<select name="accType" class="">
					<option value="kiosk">Kiosk</option>
					<option value="TicketDisplay">Ticket Display</option>
					<option value="Administrator">Administrator</option>
				</select></div>
				<div class="padform">
				<label for="password">Enter a password:</label><br>
				</div>
				<div class="padform">
				<input type="password" class="password" name="password " maxlength="100"><span id="reminder">Password must be at least 8 characters long</span><br>
				</div>
				<div class="padform">
				<label for ="confirm">Re-enter the password</label><br>
				</div>
				<div class="padform">
				<input type="password" class="" name="confirm" maxlength="100"><br>
				</div>
				<input type="submit" value="Create">
			</form>
		</div>
		<div class="delete">
			<h2>Delete an account</h2>
			<form action="AccountServlet" method="post">
				<label for="delUser">Select a User</label>
				<select name="delUser" class="">
				</select><br>
				<label for="password">Enter password to confirm</label>
				<input type="password" name="password" class="">
			</form>
		</div>
		<div class="update">
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
	</div>
</body>
</html>