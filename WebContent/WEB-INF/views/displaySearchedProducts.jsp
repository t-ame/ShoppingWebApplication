<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, com.java.components.Product"
	isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<link href="<c:url value="/style/styles.css" />" rel="stylesheet" />

<title>Search</title>

<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous" /> -->

<!-- Bootstrap core CSS -->
<!-- <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->

<!-- Custom styles for this template -->
<!-- <link href="css/shop-homepage.css" rel="stylesheet"> -->
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">


</head>

<body>



	<jsp:include page="./navbar.jsp" />


	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-lg-3">

				<%
					String conPath = request.getContextPath();
				%>

				<h1 class="my-4">MyCart</h1>
				<div class="list-group">
					<a href="<%=conPath %>/categoryProducts/Electronics" class="list-group-item"><tag:message
							code="electronics"></tag:message></a> <a
						href="<%=conPath %>/categoryProducts/Clothing" class="list-group-item"><tag:message
							code="clothing"></tag:message></a> <a
						href="<%=conPath %>/categoryProducts/Outdoors" class="list-group-item"><tag:message
							code="outdoors"></tag:message></a> <a href="<%=conPath %>/categoryProducts/Home"
						class="list-group-item"><tag:message code="home"></tag:message></a>
					<a href="<%=conPath %>/categoryProducts/Books" class="list-group-item"><tag:message
							code="books"></tag:message></a>
				</div>

			</div>

			<div class="col-lg-9">


				<%
					List<Product> products = (List<Product>) request.getAttribute("products");

					int psize = products == null ? 0 : products.size();
					/* int complete = 0;
					int rem = 0;
					if (psize != 0) {
						complete = psize / 3;
						rem = psize % 3;
					} */
				%>



				<div class="row">

					<div class="searchMsg"><%=request.getAttribute("searchMsg") == null ? "" : request.getAttribute("searchMsg")%></div>
					<%
						request.setAttribute("searchMsg", "");
				
						Product prod = null;
						for (int i = 0; i < psize; ++i) {
							prod = products.get(i);
					%>

					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="<%= request.getContextPath() %>/displayProduct/<%=prod.getProductId()%>"><img
								class="card-img-top" src="<%=prod.getImageUrl()%>" alt=""
								width="700" height="400"></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="<%= request.getContextPath() %>/displayProduct/<%=prod.getProductId()%>"><%=prod.getProductName()%></a>
								</h4>
								<h5>
									$<%=prod.getUnitPrice()%></h5>
								<p class="card-text"><%=prod.getProductDescription()%></p>
							</div>
							<div class="card-footer">
								<small class="text-muted"> 
									<%
									 	for (int p = 0; p < prod.getProductRating(); ++p) {
									 %> &#9733; <%
									 	}
									 %>
								</small>
							</div>
						</div>
					</div>

					<%
						}
					%>

				</div>
				<!-- /.row -->

			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->




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
