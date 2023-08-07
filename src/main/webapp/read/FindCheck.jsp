<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find Checks</title>
</head>
<body>
    <h1>Find Checks by Date Range</h1>
    <button style="position: absolute; top: 10px; right: 10px;"><a href="<%=request.getContextPath()%>/">Back to Home</a></button>
    <form action="${pageContext.request.contextPath}/findcheck" method="post">
        <label for="start">Start Date:</label>
        <input type="date" id="start" name="start" value="${previousStart}" required><br>
        <label for="end">End Date:</label>
        <input type="date" id="end" name="end" value="${previousEnd}" required><br>
        <button type="submit">Search</button>
    </form>

    <h2>Search Results:</h2>
    <p>${messages.success}</p>
    <table border="1">
        <tr>
            <th>Check ID</th>
            <th>Date</th>
            <th>Rev Center ID</th>
            <th>Employee ID</th>
            <th>Table Description</th>
            <th>Guest Count</th>
            <th>Item Count</th>
            <th>Net Sales</th>
            <th>Comps</th>
            <th>Promo</th>
            <th>Tax</th>
            <th>Time Open</th>
            <th>Time Close</th>
        </tr>
        <c:forEach items="${checks}" var="check">
            <tr>
                <td><c:out value="${check.getCheckId()}" /></td>
                <td><c:out value="${check.getDate()}" /></td>
                <td><c:out value="${check.getRevCenterId()}"/></td>
                <td><c:out value="${check.getEmployeeId()}"/></td>
                <td><c:out value="${check.getTableDesc()}"/></td>
                <td><c:out value="${check.getGuestCount()}"/></td>
                <td><c:out value="${check.getItemCount()}"/></td>
                <td><c:out value="${check.getNetSales()}"/></td>
                <td><c:out value="${check.getComps()}"/></td>
                <td><c:out value="${check.getPromo()}"/></td>
                <td><c:out value="${check.getTax()}"/></td>
                <td><c:out value="${check.getTimeOpen()}"/></td>
                <td><c:out value="${check.getTimeClose()}"/></td>
                <td><a href="checkitemread?checkId=<c:out value="${check.getCheckId()}"/>&date=<c:out value="${check.getDate()}"/>">View Items</td>
                <td><a href="findpayments?checkId=<c:out value="${check.getCheckId()}"/>&date=<c:out value="${check.getDate()}"/>">View Payment</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
