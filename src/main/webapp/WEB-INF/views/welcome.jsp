<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/WelcomeStyle.css"/>">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Home</title>
</head>
<body background="<c:url value='/resources/images/flower.jpg'/>"  style="background-size:cover;" >

	<div>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<div style="text-align: right; padding-right:50px; padding-top:50px; padding-bottom:30px;" >
			<a href="javascript:document.getElementById('logout').submit()" style="background-color: #ffffff"><b>LOGOUT</b></a>
		</div>
	</c:if>
	</div>
	
	<div id="header" class="container">
		<h1 style="font-family:'Comic Sans MS', cursive, sans-serif; font-size:450%;">Hello ${pageContext.request.userPrincipal.name}</h1>
		<span style="float: right; margin-right: 10%"><b>Your
				authorities: </b>${authorities}</span> <span
			style="float: left; margin-left: 10%"><b>Last Action Date:
		</b><span id="demo"></span></span>
		
		<script>
			var date = new Date("${user.getLastOperationDate()}");
			var str = date.toGMTString();
			console.log(str);
			document.getElementById("demo").innerHTML = str;
		</script>
	</div>
	<br /><br /><br />
	<c:if test="${fn:contains(authorities,'Administrator')}">
		<form action="<c:url value='/welcome/addProduct'/>" method="get">
			<button class="btn btn-success" type="submit">Add Product</button>
		</form>
	</c:if>
	<br />
	<c:if test="${not empty errorProductExists}">
		<div class='alert alert-warning'>
			<strong>Warning! </strong>${errorProductExists}</div>
	</c:if>
	<c:if test="${not empty successAddingProduct}">
		<div class='alert alert-success'>
			<strong>Success! </strong>${successAddingProduct}</div>
	</c:if>
	<c:if test="${not empty errorAddingProduct}">
		<div class='alert alert-danger'>${errorAddingProduct}</div>
	</c:if>

	<br />
	<div class="table-responsive">
	<table class="table" style="border-collapse: collapse; width: 52%; !important;">
		<tr>
			<th style="padding: 15px;">#ID</th>
			<th style="padding: 15px;">Name</th>
			<th style="padding: 15px;">Price</th>
			<th style="padding: 15px;">Action</th>
		</tr>
		<%
			int counter = 0;
		%>
		<c:forEach var="element" items="${products}" varStatus="index">
			
			<script type="text/javascript">
				var productList = new Array();
			</script>
			<script type="text/javascript">
			productList[<c:out value="${index.index}"/>] = { 
					'productId' : '<c:out value="${element.getIdproduct()}"/>',
					'productName' : '<c:out value="${element.getName()}"/>',
					'productPrice' : '<c:out value="${element.getPrice()}"/>'
			};
			
			
			function getElements(rowId) {
				var id = productList[rowId-1].productId;
				var name = productList[rowId-1].productName;
				var price = productList[rowId-1].productPrice;
				

				document.getElementById("delete-form").action="welcome/delete?id="+id;
			}
			</script>			
			<tr>
				<%
					counter = counter + 1;
				%>
				<td align="center" style="padding: 15px;"><%=counter%></td>
				<td align="left" style="padding: 15px;">${element.getName()}</td>
				<td align="right" style="padding: 15px;">${element.getPrice()}</td>

				<td align="left" style="padding: 15px;">
					<button type="button" class="btn btn-danger" data-toggle="modal"
							data-target="#myModal" onclick="getElements(<%=counter%>)" style="background-color: #B0045A; !important">Delete</button> 
				<span> <spring:url
							value="/welcome/update/${element.getIdproduct()}" var="updateUrl" />
					<button class="btn btn-primary"
							onclick="location.href='${updateUrl}'">Update</button>
				</span>
				</td>

			</tr>
		</c:forEach>
	</table>
	</div>
	<br />
	<br />
	<%
		String message = "You have " + counter + " rows in your table :)";
	%>
	<div>
		<p style="color: #A300E3; background-color: #ffffff; width: 30%; display: inline-block;">
			<%out.write(message); %>
		</p>
	</div>
	<br />
	<br />
	<br />
	

	<div class="container"  id="footer" style=" margin-bottom:100px">
		<img src="<c:url value="/resources/images/hellosum3.jpg"/>" height="258" width="252" />
		<img src="<c:url value="/resources/images/summer.jpg"/>" height="258" width="252" />
		<img class="middle-img" src="<c:url value="/resources/images/hellosum2.jpg"/>" height="258" width="252" /> 
		<img src="<c:url value="/resources/images/sumgirl.jpg"/>" height="258" width="252" />
		<img src="<c:url value="/resources/images/hellosum4.jpg"/>" height="258" width="252" />
	</div>

	<c:url value="/logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form id="logout" action="${logoutUrl}" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>



	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-sm">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 style="color:white;" class="modal-title" align="center" >Warning</h4>
				</div>
				<div class="modal-body">
					<p align="center">Are you sure you want to delete this content?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<form id="delete-form" style="display: inline-block;" method="post">
						<input type="submit" value="Delete" class="btn btn-default">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</div>

		</div>
	</div> 

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="<c:url value="/resources/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/javascript/welcomePage.js"/>"></script>
	
</body>
</html>