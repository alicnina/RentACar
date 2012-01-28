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

	<form:form action="searchDB.htm" commandName="users">
		<table>
			<tr>
				<td>User Username :</td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td>User Password :</td>
				<td><form:password path="password" /></td>
			</tr>

			<tr>
				<th colspan='2'><input name="submit" type="submit"
					value="Submit" /></th>
			</tr>
			<tr>
				<th colspan='2'><input name="reset" type="reset" /></th>
			</tr>
		</table>
	</form:form>
	
	<!--  IF USER NOT REGISTERED -->
	<h4>If You do not have account and you want to register, please click 
	<a href="getRegistered.htm"> HERE </a></h4>
	
	<p>&nbsp;</p>
	<c:if test="${!empty error}">
		<p style="color: #cc0000"></p>
	</c:if>
</body>
</html>

