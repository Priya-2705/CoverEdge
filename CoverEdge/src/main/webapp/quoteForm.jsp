<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quote Form</title>
</head>
<body>
	<form action="quoteController" method="post">
	    <select name="policyType" required>
	        <option value="HEALTH">Health</option>
	        <option value="AUTO">Auto</option>
	        <option value="HOME">Home</option>
	    </select>
	    <input type="number" name="coverage" placeholder="Coverage Amount" required>
	    <select name="term" required>
	        <option value="1">1 Year</option>
	        <option value="5">5 Years</option>
	        <option value="10">10 Years</option>
	    </select>
	    <button type="submit">Calculate Premium</button>
	</form>
</body>
</html>