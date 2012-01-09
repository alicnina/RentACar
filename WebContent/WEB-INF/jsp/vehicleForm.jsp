<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.even {
	background-color: silver;
}
</style>
<title>Vehicle Stock Page</title>
</head>
<body>

	<form:form action="add.htm" commandName="vehicle">
		<table>
			<tr>
				<td>Vehicle Manufacturer :</td>
				<td><form:input path="manufacturer" /></td>
			</tr>
			<tr>
				<td>Vehicle Model :</td>
				<td><form:input path="model" /></td>
			</tr>

			<tr>
				<td>Vehicle Production Date :</td>
				<td><form:input path="productionDate" /></td>
			</tr>

			<tr>
				<td>Vehicle Registration Number :</td>
				<td><form:input path="registrationNumber" /></td>
			</tr>

			<tr>
				<td>Vehicle Registration Expire Date :</td>
				<td><form:input path="registrationExpireDate" /></td>
			</tr>
			
			<tr>
				<td>Vehicle Rent Price Per Day :</td>
				<td><form:input path="rentPricePerDay" /></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Register"></td>
			</tr>

			<tr>
				<td colspan='2'><input name="reset" type="reset" /></td>
			</tr>
		</table>
	</form:form>

	<c:if test="${fn:length(vehicleList) > 0}">
		<table>
			<tr class="even">
				<th>Manufacturer</th>
				<th>Model</th>
				<th>Production Date</th>
				<th>Registration Number</th>
				<th>Registration Expire Date</th>
				<th>Rent Price Per Day</th>
				<th>Status</th>
				<th>Edit</th>
				<th>Delete</th>
				<th>Rent</th>
			</tr>
			<c:forEach items="${vehicleList}" var="vehicle" varStatus="status">
				<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
					<td>${vehicle.manufacturer}</td>
					<td>${vehicle.model}</td>
					<td>${vehicle.productionDate}</td>
					<td>${vehicle.registrationNumber}</td>
					<td>${vehicle.registrationExpireDate}</td>
					<td>${vehicle.rentPricePerDay}</td>
					<td>${vehicle.status}</td>
					<td><form:form action="getEdit.htm" commandName="vehicle">
							<form:hidden path="id" value="${vehicle.id}" />
							<input type="submit" value="Edit" />
						</form:form></td>
					<td><form:form action="getDelete.htm" commandName="vehicle">
							<form:hidden path="id" value="${vehicle.id}" />
							<input type="submit" value="Delete" />
						</form:form></td>
					<td><form:form action="../rental/getRent.htm" commandName="vehicle">
							<form:hidden path="id" value="${vehicle.id}" />
							<input type="submit" value="Rent" />
						</form:form></td>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>