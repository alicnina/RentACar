<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD USER ROLES (accessible only to admins)</title>
</head>
<body>

	User Role :
	<form:form action="edit.htm" commandName="users">
		<input name="username" id="username" type="hidden" value="${username_extra}" />

		<select id="role" name="role">
			<option value="ROLE_USER">Guest</option>
			<option value="ROLE_RESTRICTED">Restricted</option>
			<option value="ROLE_ADMIN">Administrator</option>
			<option value="ROLE_EMPLOYEE">Employee</option>
		</select>
		<input type="submit" value="Apply">
	</form:form>



</body>
</html>