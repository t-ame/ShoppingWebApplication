<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>

<!doctype html>
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


<!-- <link rel="stylesheet" href="style/styles.css"> -->

<!-- <link href="/WEB-INF/views/style/styles.css"  rel="stylesheet" /> -->

<title>Home Page</title>
</head>

<body>



	<jsp:include page="./WEB-INF/views/headbar.jsp" />

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-lg-3">

				<%
					String conPath = request.getContextPath();
				%>

				<h1 class="my-4 mycartlogo">MyCart</h1>
				<div class="list-group">
					<a href="<%=conPath%>/categoryProducts/Electronics?page=1"
						class="list-group-item"><tag:message code="electronics"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Clothing?page=1"
						class="list-group-item"><tag:message code="clothing"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Outdoors?page=1"
						class="list-group-item"><tag:message code="outdoors"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Home?page=1"
						class="list-group-item"><tag:message code="home"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Books?page=1"
						class="list-group-item"><tag:message code="books"></tag:message></a>
				</div>

			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img class="d-block img-fluid"
								src="images/man-sport-climbing-rock-face.jpg" width=900
								height=350 alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid"
								src="images/woman-on-computer-istock.jpg" width=900 height=350
								alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid"
								src="images/women-spend-more-money-when-shopping-together-1050x700.jpg"
								width=900 height=350 alt="Third slide">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only"><tag:message code="previous"></tag:message></span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only"><tag:message code="next"></tag:message></span>
					</a>
				</div>

				<div class="row mycartlogo">

					<div>
						<a href="<%=conPath%>/categoryProducts/Electronics?page=1"> <img
							alt="Electronics" src="images/Computers.jpg" width=900 height=200>
						</a>

						<h4>
							<tag:message code="electronics"></tag:message>
						</h4>

					</div>

					<div>
						<a href="<%=conPath%>/categoryProducts/Clothing?page=1"> <img
							alt="Clothing" src="images/best-online-mens-clothing-stores.jpg"
							width=900 height=200>
						</a>

						<h4>
							<tag:message code="clothing"></tag:message>
						</h4>

					</div>

					<div>
						<a href="<%=conPath%>/categoryProducts/Outdoors?page=1"> <img
							alt="Outdoors" src="images/OutdoorGear.png" width=900 height=200>
						</a>

						<h4>
							<tag:message code="outdoors"></tag:message>
						</h4>

					</div>

					<div>
						<a href="<%=conPath%>/categoryProducts/Home?page=1"> <img alt="Home"
							src="images/kitchen-appliances.png" width=900 height=200>
						</a>

						<h4>
							<tag:message code="home"></tag:message>
						</h4>

					</div>

					<div>
						<a href="<%=conPath%>/categoryProducts/Books?page=1"> <img
							alt="Books" src="images/1*YLlZ96J3p8GFkIh1USVMzg.jpeg" width=900
							height=200></a>

						<h4>
							<tag:message code="books"></tag:message>
						</h4>

					</div>


				</div>
				<!-- /.row -->

			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->


	<jsp:include page="./WEB-INF/views/footer.jsp" />


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