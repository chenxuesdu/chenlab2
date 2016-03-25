<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form:form action="create" method="post" commandName="profileForm">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Profile</h2></td>
				</tr>
				<tr>
					<td>id:</td>
					<td><form:input path="id" /></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><form:input path="firstname" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:password path="lastname" /></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:input path="address" /></td>
				</tr>
				<tr>
					<td>organization:</td>
					<td><form:select path="organization"/></td>
				</tr>
				<tr>
					<td>aboutMyself:</td>
					<td><form:select path="aboutMyself"/></td>
				</tr>
				<tr>
					<td colspan="2" align="left"><input type="submit"
						value="Update" /></td>
						<td colspan="2" align="right"><input type="submit"
						value="Delete" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>