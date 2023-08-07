<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Update</title>
</head>
<body>
    <h1>Create an Employee Record</h1>
    <p>${messages.success}</p><br>
    <form method="post" action="${pageContext.request.contextPath}/employeecreate">
        <label for="EmployeeId">Employee Id:</label>
        <input type="text" name="EmployeeId" required><br>
        <label for="Firstname">First Name:</label>
        <input type="text" name="Firstname" required><br>
        <label for="LastName">Last Name:</label>
        <input type="text" name="LastName" required><br>
        <label for="SSN">SSN:</label>
        <input type="text" name="SSN"><br>
        <label for="DOB">Date of Birth:</label>
        <input type="date" name="DOB" required><br>
        <label for="Email">Email:</label>
        <input type="email" name="Email"><br>
        <label for="Phone">Phone:</label>
        <input type="tel" name="Phone"><br>
        <label for="Street1">Street 1:</label>
        <input type="text" name="Street1"><br>
        <label for="Street2">Street 2:</label>
        <input type="text" name="Street2"><br>
        <label for="City">City:</label>
        <input type="text" name="City"><br>
        <label for="State">State:</label>
        <input type="text" name="State"><br>
        <label for="Zip">Zip:</label>
        <input type="text" name="Zip"><br>
        <label for="Status">Status:</label>
        <select name="Status">
            <option value="true">Active</option>
            <option value="false">Inactive</option>
        </select><br>
        <label for="Role">Role:</label>
        <input type="text" name="Role" required><br>
        <label for="wage">Wage:</label>
        <input type="number" name="wage" required><br>
        <button type="submit">Create Employee</button>
    </form>
    <br>
     <!-- Back button -->
    <form method="get" action="${pageContext.request.contextPath}/employeeread">
        <button type="submit">Back to Employee List</button>
    </form>
</body>
</html>
