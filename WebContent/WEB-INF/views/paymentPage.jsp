<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"
	import="com.java.components.Card, com.java.components.Address"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.payment/1.2.3/jquery.payment.min.js"></script>

<!-- If you're using Stripe for payments -->
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>


<title>Payment</title>

<link rel="stylesheet" href="/style/payment.css" />
<link rel="stylesheet" href="/style/styles.css" />
</head>
<body>



	<%
		String conPath = request.getContextPath();
		Card card = request.getAttribute("card") == null ? null : (Card) request.getAttribute("card");
		Address address = null;
		if (card != null && card.getCardName() != null) {
			address = card.getBillingAddress() == null ? new Address() : card.getBillingAddress();
		}
	%>

	<jsp:include page="./navbar.jsp" />


	<div class="container">
		<div class="row">

			<div class="col-lg-3">


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
						<div class="row">
							<div class="col-md-12">
								<h4>
									<tag:message code="paymentDetails"></tag:message>
								</h4>
								<hr>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">

								<%
									if (card == null || card.getCardName() == null) {
								%>

								<div class="col-md-12">
									<a class="btn icon-btn btn-primary pull-left"
										href="<%=request.getContextPath()%>/addNewCard">Add Card</a>
								</div>

								<%
									} else {
								%>

								<div class="col-md-12">
									<a class="btn icon-btn btn-primary pull-left" href="<%=request.getContextPath()%>/changeCard">Change
										Card</a>
								</div>
								<br> <br>
								<hr>
								<div class="col-md-9">
									<address>
										<strong>Billing address.</strong><br>
										<%=address.getAddressLine1()%>
										<br>
										<%
											if (address.getAddressLine2() != null) {
										%>

										<%=address.getAddressLine2()%><br>

										<%
											}
										%>
										<%=address.getCity()%>,
										<%=address.getState()%>
										<br>
										<%=address.getCountry()%>
										<%=address.getZipcode()%>

									</address>
								</div>

								<hr>

								<div class="col-xs-12 col-sm-4 col-md-4 col-lg-9">
									<div class="thumbnail">
										<div class="col-md-12">
											<h4><%=card.getCardName()%></h4>
										</div>
										<div class="col-md-12">
											<p class="text-uppercase"><%=card.getCardType().toString()%></p>
											<p>
												xxxx-xxxx-xxxx-<%=card.getCardNumber() % 10000%></p>
											<p class="text-muted">
												Exp:
												<%=card.getExpiryDate()%></p>
										</div>
										<a href="<%=request.getContextPath()%>/pay"
											class="btn btn-lg btn-primary btn-block text-uppercase">pay</a>

									</div>
								</div>

								<%
									}
								%>

							</div>
						</div>
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