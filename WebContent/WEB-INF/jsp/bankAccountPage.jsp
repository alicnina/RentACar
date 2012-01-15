<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login Page</title>
<style type="text/css">
<%@include file="../css/myStyle.css" %>
</style>
</head>
<body>

	<form:form action="add.htm" commandName="rental">
		<table>
			<tr>
				<td>Credit Card Number :</td>
				<td><form:input path="creditCardNo" /></td>
			</tr>
			<tr>
				<td>CVV2 Number:</td>
				<td><form:input path="cvv2" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Submit" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>

