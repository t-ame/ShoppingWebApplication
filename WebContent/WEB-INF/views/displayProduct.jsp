<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.Set, java.util.Iterator, com.java.components.Product, com.java.components.ProductDetailGroup, com.java.components.ProductDetail"
	isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- <meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Bootstrap-ecommerce by Vosidiy"> -->


<link href="<c:url value="/style/homepage.css"/>" rel="stylesheet" />

<title>Product details</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">



</head>
<body>


	<jsp:include page="./navbar.jsp" />



	<section class="jumbotron text-center">
		<div class="container">

			<%
				Product product = (Product) request.getAttribute("product");
			%>

			<%
				if (product == null) {
			%>

			<h1 class="jumbotron-heading">
				<tag:message code="productDoesntExist"></tag:message>
			</h1>

			<div>
				<pre>










				</pre>
			</div>

			<%
				} else {
			%>

			<h1 class="jumbotron-heading"><%=product.getProductName()%></h1>

			<%
				}
			%>

		</div>
	</section>

	<div class="container">

		<%
			if (product != null) {
		%>

		<div class="row">
			<!-- Image -->
			<div class="col-12 col-lg-6">
				<div class="card bg-light mb-3">
					<div class="card-body">
						<a href="" data-toggle="modal" data-target="#productModal"> <img
							class="img-fluid"
							src="<%=request.getContextPath()%>/<%=product.getImageUrl()%>"
							style="width: 400px; height: 500px" />
						</a>
					</div>
				</div>
			</div>

			<!-- Add to cart -->
			<div class="col-12 col-lg-6 add_to_cart_block">
				<div class="card bg-light mb-3">
					<div class="card-body">
						<p class="price">
							$<%=product.getUnitPrice()%></p>
						<p class="price">
							<tag:message code="only"></tag:message>
							<%=product.getStockQuantity()%>
							<tag:message code="inStock"></tag:message>
						</p>
						<form
							action="<%=request.getContextPath()%>/addToCart/<%=product.getProductId()%>">


							<%
								Set<ProductDetailGroup> dg = product.getProductDetails();
									int dgsize = dg.size();

									Iterator<ProductDetailGroup> pdit = dg.iterator();

									while (pdit.hasNext()) {
										ProductDetailGroup git = pdit.next();
										Set<ProductDetail> pd = git.getDetailValues();
							%>

							<div class="form-group">
								<label for="colors"><%=git.getGroupName()%></label> <select
									class="custom-select" id="colors"
									name="<%=git.getGroupName()%>" required>
									<option selected disabled value=""><tag:message code="select"></tag:message></option>


									<%
										Iterator<ProductDetail> pit = pd.iterator();
												ProductDetail detail = null;
												while (pit.hasNext()) {
													detail = pit.next();
									%>

									<option value="<%=detail.getDetailValue()%>"><%=detail.getDetailValue()%></option>
									<%
										}
									%>
								</select>
							</div>
							<%
								}
							%>

							<div class="form-group">
								<label><tag:message code="quantity"></tag:message> :</label>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<button type="button"
											class="quantity-left-minus btn btn-danger btn-number"
											data-type="minus" data-field="">
											<i class="fa fa-minus"></i>
										</button>
									</div>
									<input type="text" class="form-control" id="quantity"
										name="quantity" min="1" max="100" value="1">
									<div class="input-group-append">
										<button type="button"
											class="quantity-right-plus btn btn-success btn-number"
											data-type="plus" data-field="">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
							</div>
							
							<button type="submit"
								class="btn btn-success btn-lg btn-block text-uppercase">
								<i class="fa fa-shopping-cart"></i>
								<tag:message code="addToCart"></tag:message>
							</button>
						</form>

						<div class="reviews_product p-3 mb-2 ">
							3
							<tag:message code="reviews"></tag:message>
							<%
								for (int i = 0; i < product.getProductRating(); ++i) {
							%>
							<i class="fa fa-star"></i>
							<%
								}
							%>
							(<%=product.getProductRating()%>/5)
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- Description -->
			<div class="col-12">
				<div class="card border-light mb-3">
					<div class="card-header bg-primary text-white text-uppercase">
						<i class="fa fa-align-justify"></i>
						<tag:message code="description"></tag:message>
					</div>
					<div class="card-body">
						<p class="card-text">
							<%=product.getProductDescription()%>
						</p>
					</div>
				</div>
			</div>

			<!-- Reviews -->
			<div class="col-12" id="reviews">
				<div class="card border-light mb-3">
					<div class="card-header bg-primary text-white text-uppercase">
						<i class="fa fa-comment"></i>
						<tag:message code="reviews"></tag:message>
					</div>
					<div class="card-body">
						<div class="review">
							<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							<meta itemprop="datePublished" content="01-01-2016">
							January 01, 2018 <span class="fa fa-star"></span> <span
								class="fa fa-star"></span> <span class="fa fa-star"></span> <span
								class="fa fa-star"></span> <span class="fa fa-star"></span> by
							Paul Smith
							<p class="blockquote">
							<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
								adipiscing elit. Integer posuere erat a ante.</p>
							</p>
							<hr>
						</div>
						<div class="review">
							<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							<meta itemprop="datePublished" content="01-01-2016">
							January 01, 2018 <span class="fa fa-star" aria-hidden="true"></span>
							<span class="fa fa-star" aria-hidden="true"></span> <span
								class="fa fa-star" aria-hidden="true"></span> <span
								class="fa fa-star" aria-hidden="true"></span> <span
								class="fa fa-star" aria-hidden="true"></span> by Paul Smith
							<p class="blockquote">
							<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
								adipiscing elit. Integer posuere erat a ante.</p>
							</p>
							<hr>
						</div>
					</div>
				</div>
			</div>
		</div>


		<%
			}
		%>

	</div>


	<!-- Modal image -->
	<div class="modal fade" id="productModal" tabindex="-1" role="dialog"
		aria-labelledby="productModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="productModalLabel">
						<tag:message code="productTitle"></tag:message>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<img class="img-fluid"
						src="https://dummyimage.com/1200x1200/55595c/fff" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<tag:message code="close"></tag:message>
					</button>
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


	<script>
		$(document).ready(function() {
			var quantity = 1;

			$('.quantity-right-plus').click(function(e) {
				e.preventDefault();
				var quantity = parseInt($('#quantity').val());
				$('#quantity').val(quantity + 1);
			});

			$('.quantity-left-minus').click(function(e) {
				e.preventDefault();
				var quantity = parseInt($('#quantity').val());
				if (quantity > 1) {
					$('#quantity').val(quantity - 1);
				}
			});

		});
	</script>

</body>
</html>