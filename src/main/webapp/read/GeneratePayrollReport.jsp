<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="../generatepayrollreport" method="get">
		<h1>Generate payroll report for a time period</h1>
		Enter start date: <input type="date" name="start"> Enter end
		date: <input type="date" name="end"> <input type="submit">
	</form>
	<h2>Hours worked by Employee</h2>
	<table border="1">
		<tr>
			<th>Last Name</th>
			<th>First Name</th>
			<th>Role</th>
			<c:forEach items="${listOfDates}" var="date">
				<th>${date}</th>
			</c:forEach>
		</tr>
		<c:forEach items="${listOfEmployees}" var="employee">
			<tr>
				<td><c:out value="${employee.getLastName()}" /></td>
				<td><c:out value="${employee.getFirstName()}" /></td>
				<td><c:out value="${employee.getRole()}" /></td>
				<c:forEach items="${listOfDates}" var="date">
					<th><fmt:formatNumber type="number" minFractionDigits="2"
							maxFractionDigits="2"
							value="${payrollMap[date][employee].getHours()}" /></th>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	
	<h2>Tips earned by Employee</h2>

	<table border="1">
		<tr>
			<th>Last Name</th>
			<th>First Name</th>
			<th>Role</th>
			<c:forEach items="${listOfDates}" var="date">
				<th>${date}</th>
			</c:forEach>
		</tr>
		<c:forEach items="${listOfEmployees}" var="employee">
			<tr>
				<td><c:out value="${employee.getLastName()}" /></td>
				<td><c:out value="${employee.getFirstName()}" /></td>
				<td><c:out value="${employee.getRole()}" /></td>
				<c:forEach items="${listOfDates}" var="date">
					<th><fmt:formatNumber type="number" minFractionDigits="2"
							maxFractionDigits="2"
							value="${payrollMap[date][employee].getTips()}" /></th>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>