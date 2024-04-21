<jsp:include page="adminheader.jsp" />
<section class="content">
	<div class="container">
		<div class="create">
			<h2>Create an Item</h2>
			<form action="${pageContext.request.contextPath}/CreateItemServlet" method="post" class="createForm" enctype="multipart/form-data"> 
				<div class="padform">
					<label for="createItem">Select an item category:</label>
				</div>
				<div class="padform">
					<select name="createItem" class="" hx-trigger="load" hx-get="${pageContext.request.contextPath}/GetCategoryServlet" >
					</select><br>
				</div>
				<div class="padform">
					<label for="itemName">Enter an item name:</label>
				</div>
				<div class="padform">
					<input type="text" class="" name="itemName" maxlength="100">
				</div>
				<div class="padform">
					<label for="itemCost">Enter the item's cost:</label>
				</div>
				<div class="padform">
					<input type="number" step="0.01" class="" name="itemCost" maxlength="100">
				</div>
				<div class="padform">
					<label for="itemImage">Upload an Image for the Item:</label>
				</div>
				<div class="padform">
					<input type="file" class="" name="img" maxlength="100" accept="image/*" >
				</div>
				<div class="padform">
					<label for="itemDescription">Enter an item description:</label>
				</div>
				<div class="padform">
					<input type="text" class="" name="itemDescription" maxlength="100">
				</div>
				<div class="padform">
					<input type="submit" value="Create" class="createButton">
				</div>
			</form>
		</div>
		<div class="delete">
				<h2>Delete an Item</h2>
			<form action="${pageContext.request.contextPath}/DeleteItemServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="selectCat">Select an item Category</label>
				</div>
				<div class="padform" hx-trigger="load" hx-target=".selectCat" hx-get="${pageContext.request.contextPath}/GetCategoryServlet" >
					<select name="selectCat" class="selectCat" hx-trigger="change" hx-get="${pageContext.request.contextPath}/GetItemServlet" hx-target=".delItem">
					</select><br>
				</div>
				<div class="padform">
					<label for="delItem">Select an item to delete</label>
				</div>
				<div class="padform">
					<select name="delItem" class="delItem" hx-trigger="load" hx-include=".selectCat" hx-get="${pageContext.request.contextPath}/GetItemServlet" >
					</select>
				</div>
				
				<div class="padform">
					<input type="submit" value="Delete" class="createButton">
				</div>
			</form>
		</div>
	</div>
</section>	
<jsp:include page="footer.jsp" />
