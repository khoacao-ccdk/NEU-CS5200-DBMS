<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
This create a clock edit
	<form action="clockeditcreate" method="post">
		<h1>Edit a time clock</h1>
		Time clock ID: <input type="number" name="timeclockid"> 
		Clock in: <input type="time" step="1" name="in">
		Clock out: <input type="time" step="1" name="out">
		Break Start: <input type="time" step="1" name="breakstart">
		Break End: <input type="time" step="1" name="breakend">
		<input type="submit">
	</form>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>