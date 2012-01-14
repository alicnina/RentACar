<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>

	<form:form action="add.htm" commandName="rental">
		<form:hidden path="id" />
		<input type="hidden" id="username" name="username" value="${username}" />
		<input type="hidden" id="vehicleId" name="vehicleId" value="${vehicleId}" />
		<table>
			<tr>
				<td>Rent Start Date :</td>
				<td><form:input path="startDate" /></td>
			</tr>
			<tr>
				<td>Number of Days :</td>
				<td><form:input path="numberDays" /></td>
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

	<p>&nbsp;</p>
	<c:if test="${!empty error}">
		<p style="color: #cc0000"></p>
	</c:if>
</body>
</html>

