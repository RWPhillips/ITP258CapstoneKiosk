<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Ordering Kiosk</title>
    <script src="https://unpkg.com/htmx.org@1.6.0/dist/htmx.js"></script>
</head>
<body>
    <div id="category-title">Select a Category</div>

    <div class="category">
        <h2>Categories</h2>
        <button hx-get="/path/to/category1/items" hx-target="#category1">Category 1</button>
        <button hx-get="/path/to/category2/items" hx-target="#category2">Category 2</button>
    </div>

    <div id="items-container">
        
    </div>
</body>
</html>
