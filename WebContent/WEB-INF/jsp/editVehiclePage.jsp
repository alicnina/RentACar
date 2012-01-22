<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<%@include file="../css/myStyle.css" %>
</style>
<title>Insert title here</title>
</head>
<body>

	<h1>Edit Vehicle</h1>

	<form:form action="saveEdit.htm" commandName="vehicle">
		<form:hidden path="id" />
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
<!-- 			<tr> -->
<!-- 				<td>Vehicle Status :</td> -->
<%-- 				<td><form:select path="authority"> --%>
<%-- 						<form:option value="0" label="Select" /> --%>
<%-- 						<form:option value="rented" label="rented" /> --%>
<%-- 						<form:option value="available" label="available" /> --%>
<%-- 						<form:option value="onRepair" label="on repair" /> --%>
<%-- 					</form:select></td> --%>
<!-- 			</tr> -->
			<tr>
				<th colspan="2"><input type="submit" value="Edit"></th>
			</tr>
		</table>
	</form:form>

</body>
</html>