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
<title>Edit User Page</title>
</head>

<body>
	<h1>Edit Person</h1>

	<form:form action="saveEdit.htm" commandName="users">
		<table>
			<tr>
				<th>User Name :</th>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<th>User Surname :</th>
				<td><form:input path="surname" /></td>
			</tr>
			<tr>
				<th>User Username :</th>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<th>User Password :</th>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<th>User Address :</th>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<th>User Email :</th>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<th>User Phone :</th>
				<td><form:input path="phone" /></td>
			</tr>
			<tr>
				<th>User ID Number :</th>
				<td><form:input path="idNumber" /></td>
			</tr>
			<tr>
				<th>User ID Expire Date:</th>
				<td><form:input path="idExpireDate" /></td>
			</tr>
			<tr>
				<th>User Driving Licence Number :</th>
				<td><form:input path="drivingLicenceNumber" /></td>
			</tr>
			<tr>
				<th>User Driving Licence Expire Date :</th>
				<td><form:input path="drivingLicenceExpireDate" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:checkbox path="mailingList"
						label="Would you like to join our mailinglist?" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Register"></td>
			</tr>
		</table>
	</form:form>

</body>
</html>