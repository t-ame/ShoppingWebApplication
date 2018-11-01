<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"
	import="java.util.Set, java.util.HashSet, com.java.components.Address"
	isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<html>
<head>
<meta charset="UTF-8">


<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- 
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script> -->
<title>Cards</title>

<link href="<c:url value="/style/styles.css" />" rel="stylesheet" />
</head>
<body>



	<jsp:include page="./navbar.jsp" />



	<%
		Set<Address> addresses = request.getAttribute("addresses") == null ? new HashSet<>()
				: (Set<Address>) request.getAttribute("addresses");
		int size = addresses.size();
	%>


	<div class="container" >
		<div class="row">


			<div class="col-lg-3">

				<%
					String conPath = request.getContextPath();
				%>

				<h1 class="my-4 mycartlogo">MyCart</h1>
				<div class="list-group">
					<a href="<%=request.getContextPath()%>/displayHistory"
						class="list-group-item"><tag:message code="viewOrders"></tag:message></a>
					<a href="<%=request.getContextPath()%>/profile"
						class="list-group-item"><tag:message code="viewProfile"></tag:message></a>
					<a href="<%=request.getContextPath()%>/showAllCards"
						class="list-group-item"><tag:message code="manageCards"></tag:message></a>
					<a href="<%=request.getContextPath()%>/showAllAddresses"
						class="list-group-item"><tag:message code="manageAddress"></tag:message></a>
				</div>

			</div>


			<div class="col-md-9">
				<div class="card">
					<div class="card-body">

						<div class=col-md-12>
							<h3>
								My Addresses <a class="btn icon-btn btn-primary pull-right"
									href="<%=request.getContextPath()%>/addAddress"><span
									class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span>
									Add New Address</a>
							</h3>
						</div>

						<%
							int index = 1;
							for (Address address : addresses) {
						%>

						<div class="row-md-4">
							<address>
								<strong>Address <%=index++%></strong><br>
								<%=address.getAddressLine1()%>
								<br>
								<%
									if (address.getAddressLine2() != null || address.getAddressLine2().isEmpty()) {
								%>

								<%=address.getAddressLine2()%><br>

								<%
									}
								%>
								<%=address.getCity()%>,
								<%=address.getState()%> 
								<%=address.getZipcode()%>
								<br>
								<%=address.getCountry()%>

							</address>
							<a
								href="<%=request.getContextPath()%>/editAddress?addressId=<%=address.getAddressId()%>">Edit</a><br>
						</div>
						<hr>

						<%
							}
						%>

					</div>
				</div>
			</div>


		</div>

		<!-- End row -->
	</div>
	<!-- End container -->





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