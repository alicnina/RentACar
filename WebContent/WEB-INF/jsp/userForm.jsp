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
<title>Registration Page</title>
</head>
<body>

	<form:form action="add.htm" commandName="user">
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
				<td><form:input path="IDNumber" /></td>
			</tr>
			<tr>
				<td>User Driving Licence Number :</td>
				<td><form:input path="drivingLicenceNumber" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:checkbox path="mailingList"
						label="Would you like to join our mailinglist?" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Register"></td>
			</tr>	
			<tr>
				<td colspan='2'><input name="reset" type="reset" /></td>
			</tr>
		</table>
	</form:form>

	<!--  IF NOT LOGGED IN -->
	<!--  KASNIJE ZAVRSITI -->
	If You already have an account and You want to SIGN IN, please click <a href="getLogged.htm"> HERE </a>
	<br/>
	
	<!-- ZA ADMINA I EMPLOYEERE -->
	<!--  KASNIJE ZAVRSITI -->
	To see list of cars and add new cars, please please click <a href="../vehicle/list.htm"> HERE </a>
	<br/><br/>
	
	<c:if test="${fn:length(userList) > 0}">
		<table>
			<tr class="even">
				<th>Name</th>
				<th>Surname</th>
				<th>Username</th>
				<th>Password</th>
				<th>Role</th>
				<th>Address</th>
				<th>Email</th>
				<th>Phone</th>
				<th>ID Number</th>
				<th>Driving Licence Number</th>
				<th>Mailing List</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${userList}" var="user" varStatus="status">
				<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
					<td>${user.name}</td>
					<td>${user.surname}</td>
					<td>${user.username}</td>
					<td>${user.password}</td>
					<td>${user.role}</td>
					<td>${user.address}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.IDNumber}</td>
					<td>${user.drivingLicenceNumber}</td>
					<td>${user.mailingList}</td>
					<td><form:form action="getEdit.htm" commandName="user">
							<form:hidden path="id" value="${user.id}" />
							<input type="submit" value="Edit" />
						</form:form></td>
					<td><form:form action="getDelete.htm" commandName="user">
							<form:hidden path="id" value="${user.id}" />
							<input type="submit" value="Delete" />
						</form:form></td>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>