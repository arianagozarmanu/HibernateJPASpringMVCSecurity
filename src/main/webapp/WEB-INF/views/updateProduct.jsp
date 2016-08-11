<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/CRUDPageStyle.css"/>">
<title>Update product</title>
<style>
</style>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	type="text/css" rel="stylesheet">

</head>
<body background="<c:url value='/resources/images/back.jpg'/>">
	<div align="center" class="box">
		<form action="doUpdate" method="post">
			<input name="idproduct" value="${product.getIdproduct()}"
				type="hidden" /> <input name="userId"
				value="${product.getUser().getIduders()}" type="hidden" />
			<table>
				<tr>
					<td colspan="2" align="center"><h2>Update Product
							${product.getIdproduct()}:</h2></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input name="name" value="${product.getName()}" 
						style="margin-top: 10px; margin-bottom: 10px" /></td>
				</tr>
				<tr>
					<td>Price:</td>
					<td><input name="price" value="${product.getPrice()}" type="number" min="0" step="0.01" required/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update"
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="<c:url value="/resources/bootstrap/bootstrap.min.js"/>"></script>
</body>

</html>