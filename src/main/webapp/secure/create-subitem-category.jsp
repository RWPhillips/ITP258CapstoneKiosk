<jsp:include page="adminheader.jsp" />
<section class="content">
	<div class="container">
		<div class="create">
			<h2>Create a Subitem Category</h2>
			<form action="DataServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="addCategory">Enter a subitem category name: </label>
				</div>
				<div class="padform">
					<input type="text" class="" name="addCategory" maxlength="100">
				</div>
				<div class="padform">
					<input type="submit" value="Create" class="createButton">
				</div>
			</form>
		</div>
		<div class="delete">
				<h2>Delete a Subitem Category</h2>
			<form action="DataServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="delCategory">Select a Category to delete:</label>
				</div>
				<div class="padform">
					<select name="delCategory" class="">
					</select>
				</div>
				<p class="reminder">Subitem category must be empty to be deleted
				<br>Delete items in the <a href="" class="bodyLink" id="bodyLink">Manage Subitems</a>.</p>
				<div class="padform">
					<input type="submit" value="Delete" class="createButton">
				</div>
			</form>
		</div>
	</div>
</section>	
<jsp:include page="footer.jsp" />
