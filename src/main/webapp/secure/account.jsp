<jsp:include page="adminheader.jsp" />
<section>
	<div class="accounts">
		<div class="create">
			<h2>Create a new Account</h2>
			<form action="AccountServlet" method="post" class="accountForm">
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
					<input type="password" class="password" name="password " maxlength="100" required pattern="^[\\p{ASCII}&&[\\S]]{8,}$" title=" Password must be a a minimum of 8 characters.">
				</div>
				<div class="padform">
					<label for ="confirm">Re-enter the password:</label><br>
				</div>
				<div class="padform">
					<input type="password" class="" name="confirm" maxlength="100"><br>
				</div>
				<div class="padform">
					<input class="accountButton" type="submit" value="Create">
				</div>
			</form>
		</div>
		<div class="delete">
			<h2>Delete an account</h2>
			<form action="AccountServlet" method="post" class="accountForm">
				<div class="padform">
					<label for="delUser">Select a User: </label>
				</div>
				<div class="padform">
					<select name="delUser" class="">
					</select><br>
				</div>
				<div class="padform">
					<label for="password">Enter password to confirm: </label>
				</div>
				<div class="padform">
					<input type="password" name="password" class="">
				</div>
				<div class="padform">
					<input class="accountButton" type="submit" value="Delete">
				</div>
			</form>
		</div>
		<div class="update">
			<h2>Update Account Password</h2>
			<form action="AccountServlet" method="post" class="accountForm">
				<div class="padform">
					<label for="updateUser">Select a user: </label>
				</div>
				<div class="padform">	
					<select name="updateUser">
					</select><br>
				</div>
				<div class="padform">
				<label for="updatePass">Enter new Password:</label><br>
				</div>
				<div class="padform">
					<input type="password" name="updatePass" class="password">
				</div>
				<div class="padform">
					<label for="confirmUpdate">Re-enter password: </label>
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