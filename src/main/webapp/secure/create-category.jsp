<jsp:include page="adminheader.jsp" />
<section class="content">
	<div class="container">
		<div class="create">
			<h2>Create a New Item Category</h2>
			<form action="${pageContext.request.contextPath}/CreateCategoryServlet" method="post" class="createForm" enctype="multipart/form-data"> 
				<div class="padform">
					<label for="category">Enter a category name: </label>
				</div>
				<div class="padform">
					<input type="text" class="" name="category" maxlength="20">
				</div>
				<div class="padform">
					<label for="itemImage">Upload an Image for the Category:</label>
				</div>
				<div class="padform">
					<input type="file" class="" name="img" maxlength="100" accept="image/*"  >
				</div>
				<div class="padform">
					<input type="submit" value="Create" class="createButton">
				</div>
			</form>
		</div>
		<div class="delete">
				<h2>Delete an Item Category</h2>
			<form action="${pageContext.request.contextPath}/DeleteCategoryServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="delCategory">Select a Category to delete:</label>
				</div>
				<div class="padform">
					<select name="delCategory" class="" hx-trigger="load" hx-get="${pageContext.request.contextPath}/GetCategoryServlet">
					</select>
				</div>
				<p class="reminder">Category must be empty to be deleted.
				<br>Delete items in the <a href="" class="bodyLink" id="bodyLink">Manage Items Interface</a>.</p>
				<div class="padform">
					<input type="submit" value="Delete" class="createButton">
				</div>
			</form>
		</div>
	</div>
</section>	
<jsp:include page="footer.jsp" />
