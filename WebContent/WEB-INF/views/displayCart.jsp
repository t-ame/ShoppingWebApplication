<%@ page language="java" contentType="text/html; charset=UTF-8" session="true"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"
	import="java.util.List, com.java.components.Cart, com.java.components.CartEntry, com.java.components.Product"
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

<title>Cart</title>

<link href="<c:url value="/style/styles.css" />" rel="stylesheet" />
</head>
<body>



	<jsp:include page="./headbar.jsp" />


	<%
		Cart cart = (Cart) session.getAttribute("cart");
	
		System.out.println("cart: "+ cart);
	
		/* Cart cart = (Cart) request.getAttribute("cart"); */
	%>

	<section class="jumbotron text-center" style="opacity: 0.5">
		<div class="container">
			<h1 class="jumbotron-heading">MyCart</h1>
			<%
				boolean cartActive = cart != null && cart.getCartEntries() != null && cart.getCartEntries().size() > 0;
				if (!cartActive) {
			%>
			<div class="searchMsg">
				<tag:message code="cartEmpty"></tag:message>
			</div>
			<%
				}
			%>
		</div>
	</section>



	<%
		if (cartActive) {

			List<CartEntry> entries = cart.getCartEntries();

			float total = 0;
			float shipping = entries.size() <= 0 ? 0 : 6.99f;
			Product p = null;
			int count = 0;
	%>

	<div class="container mb-4">
		<div class="row">
			<div class="col-12">
				<div class="table-responsive">

					<div class="errorMsg"><%=request.getAttribute("errorMsg") == null ? "" : request.getAttribute("errorMsg")%></div>
					<%
						request.setAttribute("errorMsg", "");
					%>


					<table class="table table-striped mycartlogo">
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col"><tag:message code="product"></tag:message></th>
								<th scope="col"><tag:message code="available"></tag:message></th>
								<th scope="col" class="text-center"><tag:message
										code="quantity"></tag:message></th>
								<th scope="col" class="text-right"><tag:message
										code="price"></tag:message></th>
								<th></th>
							</tr>
						</thead>


						<tbody>
							<%
								for (CartEntry entry : entries) {
										p = entry.getProduct();
										if (p != null) {
											total += p.getUnitPrice() * entry.getQuantity();
							%>

							<tr>
								<td><img src="https://dummyimage.com/50x50/55595c/fff" />
								</td>
								<td><a
									href="<%=request.getContextPath()%>/displayProduct/<%=p.getProductId()%>"><%=p.getProductName()%></a></td>
								<td><%=p.getStockQuantity() < entry.getQuantity() ? "Out of stock" : "In stock"%></td>
								<td><input class="form-control" type="number" id=""
									onchange="quantityChange(this.value, <%=count%>)"
									value="<%=entry.getQuantity()%>" /></td>
								<td class="text-right">$<%=p.getUnitPrice() * entry.getQuantity()%></td>
								<td class="text-right"><a
									href="<%=request.getContextPath()%>/removeFromCart?index=<%=count++%>"
									class="btn btn-sm btn-danger"> <i class="fa fa-trash"></i>
								</a></td>
							</tr>

							<%
								}
									}
							%>

							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><tag:message code="subTotal"></tag:message></td>
								<td class="text-right">$<%=Math.round(total * 100) / 100%></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><tag:message code="shipping"></tag:message></td>
								<td class="text-right">$<%=shipping%></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><strong><tag:message code="total"></tag:message></strong></td>
								<td class="text-right"><strong>$<%=total + shipping%></strong></td>
							</tr>

						</tbody>
					</table>




				</div>
			</div>
			<div class="col mb-2">
				<div class="row">
					<div class="col-sm-12  col-md-6">
						<a href="<%=request.getContextPath()%>/home"
							class="btn btn-block btn-light"><tag:message
								code="continueShopping"></tag:message></a>
					</div>
					<div class="col-sm-12 col-md-6 text-right">
						<a href="<%=request.getContextPath()%>/proceedToCheckout"
							class="btn btn-lg btn-block btn-success text-uppercase"> <tag:message
								code="checkout"></tag:message></a>
					</div>
				</div>
			</div>
		</div>
	</div>




	<%
		}
	%>


	<jsp:include page="./footer.jsp" />


	<script type="text/javascript">

function quantityChange(value, id) {
		var form = $('<form action="${pageContext.request.contextPath}/updateCartItems">' + 
		    '<input type="hidden" name="index" value="' + id + '">' +
		    '<input type="hidden" name="quantity" value="' + value + '">' +
		    '</form>');
			$(document.body).append(form);
			form.submit();
	}
	
</script>

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