<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Items</title>
</head>
<body>
    <h1>Check Items</h1>
    <table border="1">
        <tr>
            <th>Check Item ID</th>
            <th>Check ID</th>
            <th>Date</th>
            <th>Item ID</th>
            <th>Time Created</th>
            <th>Order Mode ID</th>
            <th>Quantity</th>
            <th>Refund Quantity</th>
            <th>Employee ID</th>
            <th>Parent Modifier ID</th>
        </tr>
        <c:forEach items="${checkitems}" var="checkitem">
            <tr>
                <td>${checkitem.checkItemId}</td>
                <td>${checkitem.checkId}</td>
                <td>${checkitem.date}</td>
                <td>${checkitem.itemId}</td>
                <td>${checkitem.timeCreated}</td>
                <td>${checkitem.orderModeId}</td>
                <td>${checkitem.quantity}</td>
                <td>${checkitem.refundQuantity}</td>
                <td>${checkitem.employeeId}</td>
                <td>${checkitem.parentModifierId}</td>
            </tr>
        </c:forEach>
    </table>
    
    <!-- Back button using JavaScript history.back() function -->
    <button onclick="history.back()">Back</button>
</body>
</html>
