<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find Payments</title>
</head>
<body>
    <h1>Payment Record</h1>
    <p>${messages.success}</p>
    
    <h2>Search Results:</h2>
    <table border="1">
        <tr>
            <th>Payment ID</th>
            <th>Check ID</th>
            <th>Date</th>
            <th>Time</th>
            <th>Payment Method</th>
            <th>CC Number</th>
            <th>Auth Number</th>
            <th>Payment Amount</th>
            <th>Tips</th>
            <th>Employee ID</th>
        </tr>
        <c:forEach items="${payments}" var="payment">
            <tr>
                <td><c:out value="${payment.getPaymentId()}"/></td>
                <td><c:out value="${payment.getCheckId()}"/></td>
                <td><c:out value="${payment.getDate()}"/></td>
                <td><c:out value="${payment.getTime()}"/></td>
                <td><c:out value="${payment.getPaymentMethod()}"/></td>
                <td><c:out value="${payment.getCcNumber()}"/></td>
                <td><c:out value="${payment.getAuthNumber()}"/></td>
                <td><c:out value="${payment.getPaymentAmount()}"/></td>
                <td><c:out value="${payment.getTips()}"/></td>
                <td><c:out value="${payment.getEmployeeId()}"/></td>
            </tr>
        </c:forEach>
    </table>
    
    <!-- Back button -->
    <button onclick="history.back()">Back</button>
    
</body>
</html>
