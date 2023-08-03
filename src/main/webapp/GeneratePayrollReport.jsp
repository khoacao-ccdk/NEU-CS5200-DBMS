<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Generate payroll report for this time period.
	<form action="generatepayrollreport" method="get">
		<h1>Search for a time period</h1>
		Enter start date: <input type="date" name="start"> 
		Enter end date: <input type="date" name="end"> 
		<input type="submit">
	</form>
</body>
</html>