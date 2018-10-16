<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">


<link rel="stylesheet" href="style/styles.css">


<title>...</title>
</head>


<body>

<div id="container">
   <div id="header"></div>
   <div id="body"></div>
   <div id="footer"></div>
</div>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">

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
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="#">MyCart
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Features</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Pricing</a></li>

				<%
					if (session.getAttribute("user") != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="./logout">Logout</a></li>
					<%
						} else {
					%>
				<li class="nav-item"><a class="nav-link" href="./loginPage">Login</a></li>
					<%
						}
					%>
			</ul>
		</div>
	</nav>

</body>
</html>