<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Time Clock</title>
</head>
<body>
	This is used to find if there is an error in the time clock.
	<form action="../timeclocksread" method="get">
		<h1>Search for a time period</h1>
		Enter start date: <input type="date" name="start"> 
		Enter end date: <input type="date" name="end"> 
		<input type="submit">
	</form>

	<h1>Incorrect time clock records</h1>
	<table border="1">
		<tr>
			<th>Time Clock ID</th>
			<th>Employee ID</th>
			<th>Date</th>
			<th>Clock In Time</th>
			<th>Clock Out Time</th>
			<th>Unpaid Break in Minute</th>
		</tr>
		<c:forEach items="${timeClocks}" var="timeClock">
			<tr>
				<td><c:out value="${timeClock.getTimeClockId()}" /></td>
				<td><c:out value="${timeClock.getEmployeeId()}" /></td>
				<td>
					<%--  <fmt:parseDate value="${timeClock.getDate()}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
					<fmt:formatDate value="${parsedDate}" type="date" /> --%>
					<c:out value="${timeClock.getDate().plusDays(1)}" /> 
				</td>
				<td><fmt:formatDate type="time" value="${timeClock.getClockInTime()}" timeZone="GMT-7" pattern="HH:mm:ss" /></td>
				<td><fmt:formatDate type="time" value="${timeClock.getClockOutTime()}" timeZone="GMT-7" pattern="HH:mm:ss" /></td>
				<td><c:out value="${timeClock.getUnpaidBreakMin()}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>