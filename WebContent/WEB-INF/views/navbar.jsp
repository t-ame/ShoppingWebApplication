<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="com.java.components.Cart"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<!-- 
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous"> -->

<!-- 
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"> -->


<link href="<c:url value="/style/styles.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<title>...</title>

<style type="text/css">
.badge {
	padding-left: 9px;
	padding-right: 9px;
	-webkit-border-radius: 9px;
	-moz-border-radius: 9px;
	border-radius: 9px;
}

.label-warning[href], .badge-warning[href] {
	background-color: #c67605;
}

#lblCartCount {
	font-size: 12px;
	background: #ff0000;
	color: #fff;
	padding: 0 5px;
	vertical-align: top;
	margin-left: -10px;
}
</style>

</head>


<body>

<% int cartNo = request.getAttribute("cart") == null ? 0 : ((Cart)request.getAttribute("cart")).getCartEntries().size(); %>

	<div id="container">
		<div id="header"></div>
		<div id="body"></div>
		<div id="footer"></div>
	</div>


	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<button type="button" id="sidebarCollapse" class="btn btn-info">
			<i class="fa fa-align-justify"></i>
			<!--  <span>toggle sidebar</span> -->
		</button>

		<!--<a class="navbar-brand" href="#">Navbar</a> -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto" style="display: flex">
				<li class="nav-item active"><a class="nav-link" href="./home"
					style="font-size: x-large"><span
						style="font-family: 'Comic Sans MS'; color: yellow">My</span><span
						style="font-family: 'Comic Sans MS'; color: #007bff">Cart</span> <span
						class="sr-only">(current)</span> </a></li>

				<li class="nav-item"><pre>										</pre></li>

				<%
					if (session.getAttribute("user") == null) {
				%>
				<li class="nav-item"><pre>	</pre></li>
				<%
					}
				%>

				<li class="nav-item"><a class="nav-link" href="#">Features</a>
				</li>

				<li class="nav-item"><a class="nav-link" href="#">Features</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Pricing</a></li>

				<li class="nav-item"><a class="nav-link" href="#"><i
						class="fa" style="font-size: 24px">&#xf07a;</i><span
						class='badge badge-warning' id='lblCartCount'> <%=cartNo%> </span></a></li>



				<%
					if (session.getAttribute("user") != null) {
				%>

				<li class="nav-item"><a class="nav-link"
					href="./displayAccount">My Account</a></li>
				<li class="nav-item"><a class="nav-link" href="./logout">Logout</a></li>
				<%
					} else {
				%>
				<li class="nav-item"><a class="nav-link" href="./login">Login/Register</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</nav>

</body>
</html>