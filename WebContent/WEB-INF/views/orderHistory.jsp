<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.Set, com.java.components.Order, com.java.components.Cart, com.java.components.CartEntry, com.java.components.Product"
	isELIgnored="false"%>
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
<meta charset="UTF-8">
<title>Cart</title>

<link href="<c:url value="/style/styles.css" />" rel="stylesheet" />
</head>
<body>



	<jsp:include page="./navbar.jsp" />


	<%
		List<Order> orders = (List<Order>) request.getAttribute("orders");
	%>

	<h3 style="color: green; font-size: 25px">
		<%=request.getAttribute("paymentSuccess") == null ? "" : request.getAttribute("paymentSuccess")%>
	</h3>

	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading">
				<tag:message code="orders"></tag:message>
			</h1>
			<%
				if (orders == null || orders.isEmpty()) {
			%>
			<div class="searchMsg">
				<tag:message code="noOrderHistory"></tag:message>
				.
			</div>
			<%
				}
			%>
		</div>
	</section>



	<%
		if (orders != null) {

			Product p = null;
	%>

	<div class="container mb-4">
		<div class="row">
			<div class="col-12">
				<div class="table-responsive">


					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col"><tag:message code="product"></tag:message></th>
								<th scope="col"><tag:message code="details"></tag:message></th>
								<th scope="col" class="text-center"><tag:message
										code="quantity"></tag:message></th>
								<th scope="col" class="text-right"><tag:message
										code="price"></tag:message></th>
								<th scope="col" class="text-right"><tag:message code="date"></tag:message></th>
							</tr>
						</thead>


						<tbody>
							<%
								for (Order order : orders) {
										p = order.getProduct();
										if (p != null) {
							%>

							<tr>
								<td><img src="https://dummyimage.com/50x50/55595c/fff" />
								</td>
								<td><a
									href="<%=request.getContextPath()%>/displayProduct/<%=p.getProductId()%>"><%=p.getProductName()%></a></td>
								<td><%=order.getStringDetails()%></td>
								<td class="text-center"><%=order.getQuantity()%></td>
								<td class="text-right">$<%=p.getUnitPrice() * order.getQuantity()%></td>
								<td class="text-right"><%=order.getOrderDate().toString()%></td>
								
							</tr>

							<%
								}
									}
							%>

						</tbody>
					</table>




				</div>
			</div>
			
		</div>
	</div>




	<%
		}
	%>


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