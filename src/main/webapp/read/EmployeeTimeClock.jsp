<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees' Invalid Time Clock</title>
<button style="position: absolute; top: 10px; right: 10px;"><a href="<%=request.getContextPath()%>/">Back to Home</a></button>
<script>
        function validateForm() {
            var startDate = document.getElementById("start").value;
            var endDate = document.getElementById("end").value;
            var submitButton = document.getElementById("submitButton");
            
            if (startDate === "" || endDate === "") {
                submitButton.disabled = true;
            } else {
                submitButton.disabled = false;
            }
        }
    </script>
</head>
<body>
	<form action="timeclocksread" method="get">
        <h1>Search for a time period</h1>
        Enter start date: <input type="date" id="start" name="start" oninput="validateForm()"> 
        Enter end date: <input type="date" id="end" name="end" oninput="validateForm()"> 
        <input type="submit" id="submitButton" disabled>
    </form>

	<h1>Time clock records</h1>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
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
				<td><fmt:formatDate type="time" value="${timeClock.getClockInTime()}" timeZone="GMT-8" pattern="HH:mm:ss" /></td>
				<td><fmt:formatDate type="time" value="${timeClock.getClockOutTime()}" timeZone="GMT-8" pattern="HH:mm:ss" /></td>
				<td><c:out value="${timeClock.getUnpaidBreakMin()}" /></td>
				<td><a href="clockeditcreate?timeclockid=<c:out value="${timeClock.getTimeClockId()}"/>"/>Update</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>