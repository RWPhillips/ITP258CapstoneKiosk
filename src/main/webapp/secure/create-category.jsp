<jsp:include page="adminheader.jsp" />
<section>
	<div class="categories">
		<h2>Add a New Item Category</h2>
		<form action="DataServlet" method="post"> 
			<label for="addcategory"></label>
			<input type="text" class="" name="addcategory" maxlength="100">
			<input type="submit" value="Create">
		</form>
		<h2>Delete an Item Category</h2>
			<p>Only categories with no items can be deleted</p>
			<form action="DataServlet" method="post"> 
			<label for="delcategory"></label>
			<select name="delcategory" class="">
			</select>
			<input type="submit" value="Delete">
		</form>
	</div>
</section>	
<jsp:include page="footer.jsp" />
