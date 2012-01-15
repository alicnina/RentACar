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

	<h1>Edit Person</h1>

	<form:form action="saveEdit.htm" commandName="users">
		<form:hidden path="id" />
		<table>
			<tr>
				<td>User Name :</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>User Surname :</td>
				<td><form:input path="surname" /></td>
			</tr>
			<tr>
				<td>User Username :</td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td>User Password :</td>
				<td><form:password path="password" /></td>
			</tr>
			<!-- <tr>
				<td>User Role :</td>
				<td><form:select path="authorities">
						<form:option value="0" label="Select" />
						<form:option value="ROLE_USER" label="Guest" />
						<form:option value="ROLE_ADMIN" label="Administrator" />
						<form:option value="ROLE_EMPLOYEE" label="Employee" />
					</form:select></td>
			</tr> -->
			<tr>
				<td>User Address :</td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td>User Email :</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>User Phone :</td>
				<td><form:input path="phone" /></td>
			</tr>
			<tr>
				<td>User ID Number :</td>
				<td><form:input path="idNumber" /></td>
			</tr>
			<tr>
				<td>User ID Expire Date:</td>
				<td><form:input path="idExpireDate" /></td>
			</tr>
			<tr>
				<td>User Driving Licence Number :</td>
				<td><form:input path="drivingLicenceNumber" /></td>
			</tr>
			<tr>
				<td>User Driving Licence Expire Date :</td>
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