<jsp:include page="adminheader.jsp" />
<section class="content">
	<div class="container">
		<div class="create">
			<h2>Create an Item</h2>
			<form action="${pageContext.request.contextPath}/CreateSubItemServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="createItem">Select an subitem category:</label>
				</div>
				<div class="padform">
					<select name="createItem" class="" hx-trigger="load" hx-get="${pageContext.request.contextPath}/GetSubcategoryServlet" >
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
			<form action="CreateItemServlet" method="post" class="createForm"> 
				<div class="padform">
					<label for="selectCat">Select a Subitem Category</label>
				</div>
				<div class="padform" hx-trigger="load" hx-target=".selectCat" hx-get="${pageContext.request.contextPath}/GetSubCategoryServlet">
					<select name="createItem" class="selectCat" hx-trigger="change" hx-get="${pageContext.request.contextPath}/GetSubItemServlet" hx-target=".delItem" >
					</select><br>
				</div>
				<div class="padform">
					<label for="delItem">Select a Subitem to delete</label>
				</div>
				<div class="padform">
					<select name="delItem" class="delItem" hx-trigger="load" hx-get="${pageContext.request.contextPath}/GetSubItemServlet" >
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
