<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
	<%@include file="../css/myStyle.css" %>
</style>

<title>ADD USER ROLES (accessible only to admins)</title>
</head>
<body>

	<h3>User Role :</h3>
	<form:form action="edit.htm" commandName="users">
		<input name="username" id="username" type="hidden" value="${username_extra}" />

		<h4><select id="role" name="role">
			<option value="ROLE_USER">Guest</option>
			<option value="ROLE_RESTRICTED">Restricted</option>
			<option value="ROLE_ADMIN">Administrator</option>
			<option value="ROLE_EMPLOYEE">Employee</option>
		</select> </h4>
		<h4><input type="submit" value="Apply"></h4>
	</form:form>



</body>
</html>