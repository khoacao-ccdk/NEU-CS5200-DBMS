<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit time clock</title>
</head>
<body>
	<h1>Edit time clock</h1>
	<form action="clockeditcreate" method="post">
		Time clock ID: <input type="number" name="timeclockid" value="${timeclock.getTimeClockId()}" readonly> <br>
		Employee: <input type="number" name="employeeid" value="${timeclock.getEmployeeId()}" readonly> <br>
		Current Clock in: <input type="time" name="currentclockin" value="${timeclock.getClockInTime()}" readonly> <br>
		Current Clock out: <input type="time" name="currentclockout" value="${timeclock.getClockOutTime()}" readonly> <br>
		Current Unpaid Break Minutes: <input type="number" name="currentbreakmin" value="${timeclock.getUnpaidBreakMin()}" readonly> <br>
		New Clock in: <input type="time" step="1" name="in"> <br>
		New Clock out: <input type="time" step="1" name="out"> <br>
		Break Start: <input type="time" step="1" name="breakstart"> <br>
		Break End: <input type="time" step="1" name="breakend"> <br>
		<input type="submit">
	</form>
	<!-- Back button -->
    <form method="get" action="${pageContext.request.contextPath}/timeclocksread">
        <button type="submit">Back to time clock list</button>
    </form>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>