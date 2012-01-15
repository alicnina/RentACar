<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login Page</title>
<style type="text/css">
<%@include file="../css/myStyle.css" %>
</style>
</head>
<body>
	
	<form id="rental" action="add.htm" method="post">
		<input type="hidden" id="rentalId" name="rentalId" value="${rentalId}" />
		<input type="hidden" id="username" name="username" value="${username}" />
		<input type="hidden" id="vehicleId" name="vehicleId" value="${vehicleId}" />
		<table>
			<tr>
				<td>Rent Start Date :</td>
				<td><input type="text" id="startDate" name="startDate" value="yyyy-mm-dd" /></td>
			</tr>
			<tr>
				<td>Number of Days :</td>
				<td><input type="text" id="numberDays" name="numberDays" value="0" /></td>
			</tr>
			<tr>
				<td>Credit Card No :</td>
				<td><input type="text" id="creditCardNumber" name="creditCardNumber" value="" /></td>
			</tr>
			<tr>
				<td>Cvv2 :</td>
				<td><input type="text" id="cvv2" name="cvv2" value="" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Submit" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" /></td>
			</tr>
		</table>
	</form>

	<p>&nbsp;</p>
	<c:if test="${!empty error}">
		<p style="color: #cc0000"></p>
	</c:if>
</body>
</html>

