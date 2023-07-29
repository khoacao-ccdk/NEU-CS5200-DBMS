<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
This is the payment delete page.

	<form action="paymentdelete" method="post">
		Payment ID: <input type="number" name="paymentId"> 
		<input type="submit">
	</form>
	<p>
		<span id="successMessage"><b>${messages.title}</b></span>
	</p>
</body>
</html>