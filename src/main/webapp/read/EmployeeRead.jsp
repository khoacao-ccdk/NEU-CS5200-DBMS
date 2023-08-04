<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Employee Record</title>
</head>
<body>
	<h1>All Employee Record</h1>
	<br/>
	<table border="1">
		<tr>
			<th>Employee ID</th>
			<th>First Name</th>
			<th>LastName</th>
			<th>SSN</th>
			<th>DOB</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Street1</th>
			<th>Street2</th>
			<th>City</th>
			<th>State</th>
			<th>Zip</th>
			<th>Role</th>
			<th>Wage</th>
			<th/>
		</tr>
		<c:forEach items="${employees}" var="employees">
			<tr>
				<td><c:out value="${employees.getEmployeeId()}" /></td>
				<td><c:out value="${employees.getFirstName()}" /></td>
				<td><c:out value="${employees.getLastName()}" /></td>
				<td><c:out value="${employees.getSSN()}" /></td>
				<td>
					<%--  <fmt:parseDate value="${timeClock.getDate()}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
					<fmt:formatDate value="${parsedDate}" type="date" /> --%>
					<c:out value="${employees.getDOB()}" /> 
				</td>
				<td><c:out value="${employees.getEmail()}" /></td>
				<td><c:out value="${employees.getPhone()}" /></td>
				<td><c:out value="${employees.getStreet1()}" /></td>
				<td><c:out value="${employees.getStreet2()}" /></td>
				<td><c:out value="${employees.getCity()}" /></td>
				<td><c:out value="${employees.getState()}" /></td>
				<td><c:out value="${employees.getZip()}" /></td>
				<td><c:out value="${employees.getRole()}" /></td>
				<td><c:out value="${employees.getWage()}" /></td>
				<td><button><a href="update/EmployeeUpdate.jsp">Update</a></button></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>