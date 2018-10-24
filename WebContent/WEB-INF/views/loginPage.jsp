<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
<title>Login Page</title>

<link href="<c:url value="/style/styles.css" />" rel="stylesheet"
	type="text/css">

<!-- type="text/css"  -->

</head>



<body>

	<jsp:include page="./navbar.jsp" />


	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h5 class="card-title text-center"><tag:message code="signIn"></tag:message></h5>
						<form class="form-signin" action="./loginUser" method="post">


							<div class="errorMsg"><%=request.getAttribute("errorMsg") == null ? "" : request.getAttribute("errorMsg")%></div>
							<%
								request.setAttribute("errorMsg", "");
							%>

							<div class="form-label-group">
								<label for="inputEmail"><tag:message code="email"></tag:message>

								</label> <input type="email" id="inputEmail" class="form-control"
									placeholder="Email address" required autofocus name="userEmail">
							</div>

							<br>

							<div class="form-label-group">
								<label for="inputPassword"><tag:message code="password"></tag:message></label> <input
									type="password" id="inputPassword" class="form-control"
									placeholder="Password" required name="userPassword">
							</div>

							<br>

							<div class="custom-control custom-checkbox mb-3">
								<input type="checkbox" class="custom-control-input"
									id="customCheck1"> <label class="custom-control-label"
									for="customCheck1"><tag:message code="rememberMe"></tag:message></label>
							</div>
							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit"><tag:message code="signIn"></tag:message></button>

							<br>
							<div style="text-align: center">
								<a href="./register" style="color: blue; font-size: 15px"><tag:message code="registerNewUser"></tag:message></a>
							</div>
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