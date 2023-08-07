<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Menu Item</title>
</head>
<body>
    <h1>Update Menu Item</h1>
    <p>${messages.success}</p>
    <form action="menuitemupdate" method="post">
    	<p>
            <label for="MenuItemId">Item Id:</label>
           <input name="MenuItemId" value="${MenuItem.itemId}" readonly/>
        </p>
        
        <p>
            <label for="ItemName">Item Name:</label>
            <input type="text" id="ItemName" name="ItemName" value="${MenuItem.itemName}" required />
        </p>
        <p>
            <label for="ItemPrice">Item Price:</label>
            <input type="number" step="0.01" id="ItemPrice" name="ItemPrice" value="${MenuItem.itemPrice}" required />
        </p>
        <p>
            <label for="CategoryId">Category:</label>
            <select id="CategoryId" name="CategoryId">
                <option value="" disabled>Select a category</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.categoryId}" ${category.categoryId == MenuItem.categoryId ? 'selected' : ''}>
                        ${category.categoryName}
                    </option>
                </c:forEach>
            </select>
        </p>
        <p>
            <button type="submit">Update</button>
        </p>
    </form>
    
    <!-- Back button using JavaScript history.back() function -->
    <button><a href="menuitemread">Back</a></button>
</body>
</html>
