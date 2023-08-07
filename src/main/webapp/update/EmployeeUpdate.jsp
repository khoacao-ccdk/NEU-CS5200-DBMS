<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Update</title>
</head>
<body>
    <h1>Update Employee</h1>
    <form method="post" action="${pageContext.request.contextPath}/employeeupdate">
        <input type="hidden" name="EmployeeId" value="${Employee.getEmployeeId()}">
        <label for="Firstname">First Name:</label>
        <input type="text" name="Firstname" value="${Employee.getFirstName()}" required><br>
        <label for="LastName">Last Name:</label>
        <input type="text" name="LastName" value="${Employee.getLastName()}" required><br>
        <label for="SSN">SSN:</label>
        <input type="text" name="SSN" value="${Employee.getSSN()}"><br>
        <label for="DOB">Date of Birth:</label>
        <input type="date" name="DOB" value="${Employee.getDOB()}" required><br>
        <label for="Email">Email:</label>
        <input type="email" name="Email" value="${Employee.getEmail()}"><br>
        <label for="Phone">Phone:</label>
        <input type="tel" name="Phone" value="${Employee.getPhone()}"><br>
        <label for="Street1">Street 1:</label>
        <input type="text" name="Street1" value="${Employee.getStreet1()}"><br>
        <label for="Street2">Street 2:</label>
        <input type="text" name="Street2" value="${Employee.getStreet2()}"><br>
        <label for="City">City:</label>
        <input type="text" name="City" value="${Employee.getCity()}"><br>
        <label for="State">State:</label>
        <input type="text" name="State" value="${Employee.getState()}"><br>
        <label for="Zip">Zip:</label>
        <input type="text" name="Zip" value="${Employee.getZip()}"><br>
        <label for="Status">Status:</label>
        <select name="Status">
            <option value="true" ${Employee.isStatus() ? 'selected' : ''}>Active</option>
            <option value="false" ${!Employee.isStatus() ? 'selected' : ''}>Inactive</option>
        </select><br>
        <label for="Role">Role:</label>
        <input type="text" name="Role" value="${Employee.getRole()}" required><br>
        <label for="wage">Wage:</label>
        <input type="number" name="wage" value="${Employee.getWage()}" required><br>
        <button type="submit">Update Employee</button>
    </form>
    <p>${messages.success}</p>
    
     <!-- Back button -->
    <form method="get" action="${pageContext.request.contextPath}/employeeread">
        <button type="submit">Back to Employee List</button>
    </form>
</body>
</html>
