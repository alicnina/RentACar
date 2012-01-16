<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login Page</title>
<style type="text/css">
<%@ include file="../css/myStyle.css" %>
</style>
</head>
<body>

	<form id="rental" action="add.htm" method="post">
		<input type="hidden" id="rentalId" name="rentalId" value="${rentalId}" /> <input type="hidden" id="username" name="username" value="${username}" /> <input
			type="hidden" id="vehicleId" name="vehicleId" value="${vehicleId}" />
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
				<td colspan='2'><input name="submit" type="submit" value="Submit" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" /></td>
			</tr>
		</table>
	</form>

	<p>&nbsp;</p>
	<c:if test="${!empty error}">
		<p style="color: #cc0000">error</p>
	</c:if>

	<c:if test="${fn:length(rentalList) > 0}">
		<table>
			<tr class="even">
				<th>Start Date</th>
				<th>Number of Days</th>
				<th>Username</th>
				<th>Vehicle</th>
				<th>Status</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${rentalList}" var="rental" varStatus="status">
				<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
					<td>${rental.startDate}</td>
					<td>${rental.numberDays}</td>
					<td>${rental.users.username}</td>
					<td>${rental.vehicle.manufacturer} ${rental.vehicle.model}</td>
					<td>${rental.status}</td>
					<td><form:form action="getEdit.htm" commandName="rental">
							<form:hidden path="id" value="${rental.id}" />
							<input type="submit" value="Edit" />
						</form:form></td>
					<td><form:form action="getDelete.htm" commandName="rental">
							<form:hidden path="id" value="${rental.id}" />
							<input type="submit" value="Delete" />
						</form:form></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>

