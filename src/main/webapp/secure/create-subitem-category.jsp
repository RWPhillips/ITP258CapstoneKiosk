<jsp:include page="adminheader.jsp" />
<section class="content">
	<div class="container">
		<div class="create">
			<h2>Create a Subitem Category</h2>
			<form action="${pageContext.request.contextPath}/CreateSubcategoryServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="addSubcategory">Enter a subitem category name: </label>
				</div>
				<div class="padform">
					<input type="text" class="" name="addSubcategory" maxlength="100">
				</div>
				<div class="padform">
					<input type="submit" value="Create" class="createButton">
				</div>
			</form>
		</div>
		<div class="delete">
				<h2>Delete a Subitem Category</h2>
			<form action="${pageContext.request.contextPath}/DeleteSubCategoryServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="delSubcategory">Select a Subcategory to delete:</label>
				</div>
				<div class="padform">
					<select name="delSubcategory" class="" hx-trigger="load" hx-get="${pageContext.request.contextPath}/GetSubcategoryServlet">
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
