<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
<%@include file="../css/myStyle.css"%>
</style>

<title>Registration Page</title>
</head>
<body>

	<form:form action="add.htm" commandName="users">
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
				<th colspan="2"><input type="submit" value="Register"></th>
			</tr>
			<tr>
				<th colspan='2'><input name="reset" type="reset" /></th>
			</tr>
		</table>
	</form:form>

	<!--  IF NOT LOGGED IN -->
	<!--  KASNIJE ZAVRSITI -->
	<h4>If You already have an account and You want to SIGN IN, please click
	<a href="getLogged.htm"> HERE </a></h4>

	<!-- ZA ADMINA I EMPLOYEERE -->
	<!--  KASNIJE ZAVRSITI -->
	<h4>To see list of cars and add new cars, please please click
	<a href="../vehicle/list.htm"> HERE </a></h4>

	<c:if test="${fn:length(usersList) > 0}">
		<table>
			<tr class="even">
				<th>Name</th>
				<th>Surname</th>
				<th>Username</th>
				
				<!-- <th>Role</th> -->
				<th>Address</th>
				<th>Email</th>
				<th>Phone</th>
				<th>ID Number</th>
				<th>ID Expire Date</th>
				<th>Driving Licence Number</th>
				<th>Driving Licence Expire Date</th>
				<th>Mailing List</th>
				<th>Edit</th>
				<th>Delete</th>
				<th>Add Role</th>
			</tr>
			<c:forEach items="${usersList}" var="user" varStatus="status">
				<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
					<td>${user.name}</td>
					<td>${user.surname}</td>
					<td>${user.username}</td>
					
					<!-- <td>${user.authorities}</td> -->
					<td>${user.address}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.idNumber}</td>
					<td>${user.idExpireDate}</td>
					<td>${user.drivingLicenceNumber}</td>
					<td>${user.drivingLicenceExpireDate}</td>
					<td>${user.mailingList}</td>
					<td><form:form action="getEdit.htm" commandName="users">
							<form:hidden path="username" value="${user.username}" />
							<input type="submit" value="Edit" />
						</form:form></td>
					<td><form:form action="getDelete.htm" commandName="users">
							<form:hidden path="username" value="${user.username}" />
							<input type="submit" value="Delete" />
						</form:form></td>
					<td><form:form action="../authorities/getRole.htm"
							commandName="users">
							<form:hidden path="username" value="${user.username}" />
							<input type="submit" value="EditRole" />
							<br />
							<small><c:forEach items="${user.authorities}" var="auth">${auth.authority}</c:forEach></small>
						</form:form></td>
			</tr></c:forEach>
		</table>
	</c:if>
</body>
</html>