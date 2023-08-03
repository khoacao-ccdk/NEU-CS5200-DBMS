<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Update an employee

	<form action="../employeeupdate" method="put">
		Employee ID: <input type="number" name="EmployeeId"> <br>
		First Name: <input type="text" name="Firstname"> <br>
		Last Name: <input type="text" name="Lastname"> <br>
		SSN: <input type="text" name="SSN"> <br>
		Date of Birth: <input type="text" name="DOB"> <br> 
		Email: <input type="text" name="Email"> <br>
		Phone: <input type="text" name="Phone"> <br>
		Street1: <input type="text" name="Street1"> <br>
		Street2: <input type="text" name="Street2"> <br>
		City: <input type="text" name="City"> <br>
		State: <input type="text" name="State"> <br>
		Zip Code: <input type="text" name="Zip"> <br>
		Status: <input type="checkbox" name="Status"> <br>
		Role: <input type="text" name="Role"> <br>
		Wage per hour: <input type="number" name="wage"> <br> 
		<input type="submit">
	</form>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>