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
				<th>Rent Start Date :</th>
				<td><input type="text" id="startDate" name="startDate" value="yyyy-mm-dd" /></td>
			</tr>
			<tr>
				<th>Number of Days :</th>
				<td><input type="text" id="numberDays" name="numberDays" value="0" /></td>
			</tr>
			<tr>
				<th>Credit Card No :</th>
				<td><input type="text" id="creditCardNumber" name="creditCardNumber" value="" /></td>
			</tr>
			<tr>
				<th>Cvv2 :</th>
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

