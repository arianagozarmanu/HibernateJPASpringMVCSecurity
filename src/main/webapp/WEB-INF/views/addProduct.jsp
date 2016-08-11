<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/CRUDPageStyle.css"/>">
<title>Add Product</title>

<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	type="text/css" rel="stylesheet">
</head>
<body background="<c:url value='/resources/images/back.jpg'/>">

	<!-- can change this -->
	<div align="center" class="box">
		<form action="register" method="post">
			<table border="">
				<tr>
					<td colspan="2" align="center"><h2>Add New Product</h2></td>
				</tr>
				<tr>
					<td>Product ID:</td>
					<td><input name="idproduct" value="0"
						style="margin-bottom: 10px; margin-top: 10px" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input name="name" value="numele produsului"
						style="margin-bottom: 10px; margin-top: 10px" /></td>
				</tr>
				<tr>
					<td>Price:</td>
					<td><input name="price" value="0.0"
						style="margin-bottom: 10px; margin-top: 10px" /></td>
				</tr>
				<tr>
					<td>User ID:</td>
					<td><select name="iduser"
						style="margin-bottom: 10px; margin-top: 10px">

							<c:forEach var="element" items="${users}">
								<option value="${element.getIduders()}">${element.getUsername()}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Add Product"
						style="background-color: #A83116; border-radius: 5px; color: white; padding: 10px 10px; margin-top: 20px" /></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName }"
				value="${_csrf.token }" />
		</form>
	</div>
	<c:if test="${not empty invalidData}">
		<div class='alert alert-warning' align="center">
			<strong>Warning! </strong>${invalidData}</div>
	</c:if>
	
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/bootstrap.min.js"/>"></script>
</body>

</html>