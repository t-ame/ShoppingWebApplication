<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<html>
<head>
<meta charset="UTF-8">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<title>Registration Page</title>

<link href="<c:url value="/style/styles.css" />" rel="stylesheet"
	type="text/css">

<!-- type="text/css"  -->

<style type="text/css">
/* body {
	background-color: #525252;
} */
.centered-form {
	margin-top: 60px;
}

.centered-form .panel {
	background: rgba(255, 255, 255, 0.8);
	box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
}
</style>

</head>
<body>

	<jsp:include page="./navbar.jsp" />
	<!-- 
	<h2>Registration</h2> -->


	<!-- <form action=""> -->
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h5 class="card-title text-center"><tag:message code="registration"></tag:message></h5>
						<form class="form-signin" action="<%= request.getContextPath() %>/registerUser" method="post">


							<div class="errorMsg"><%=request.getAttribute("errorMsg") == null ? "" : request.getAttribute("errorMsg")%></div>
							<%
								request.setAttribute("errorMsg", "");
							%>

							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">

										<label for="first_name"><tag:message code="firstName"></tag:message></label> <input type="text"
											name="firstName" id="first_name"
											class="form-control input-sm" placeholder="First Name">
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">

										<label for="last_name"><tag:message code="lastName"></tag:message></label> <input type="text"
											id="last_name" class="form-control input-sm"
											placeholder="Last Name" name="lastName">
									</div>
								</div>
							</div>

							<div class="form-label-group">
								<label for="inputEmail"><tag:message code="email"></tag:message></label> <input
									type="email" id="inputEmail" class="form-control"
									placeholder="Email address" required autofocus name="userEmail">
							</div>

							<br>

							<div class="form-label-group">
								<label for="inputPassword"><tag:message code="password"></tag:message></label> <input
									type="password" id="inputPassword" class="form-control"
									placeholder="Password" required name="userPassword">
							</div>
<!-- 
							<br>

							<div class="form-label-group">
								<label for="inputConfirmPassword">Confirm password</label> <input
									type="password" id="inputConfirmPassword" class="form-control"
									placeholder="Password confirmation" required
									name="password_confirmation">
							</div> -->

							<br>


							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit"><tag:message code="register"></tag:message></button>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="./footer.jsp" />
	
	
	<!-- Bootstrap core JavaScript -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	

</body>
</html>