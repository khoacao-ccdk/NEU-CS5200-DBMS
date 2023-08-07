<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu Items</title>
</head>
<body>
    <h1>Menu Items</h1>
    <table border="1">
        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <th>Item Price</th>
            <th>Category ID</th>
        </tr>
        <c:forEach items="${menuitems}" var="menuitem">
            <tr>
                <td>${menuitem.itemId}</td>
                <td>${menuitem.itemName}</td>
                <td>${menuitem.itemPrice}</td>
                <td>${menuitem.categoryId}</td>
                <td><a href="menuitemupdate?MenuItemId=<c:out value="${menuitem.getItemId()}"/>">Edit Item</a></td>
            </tr>
        </c:forEach>
    </table>
    
    <!-- Back button using JavaScript history.back() function -->
    <button onclick="history.back()">Back</button>
</body>
</html>
