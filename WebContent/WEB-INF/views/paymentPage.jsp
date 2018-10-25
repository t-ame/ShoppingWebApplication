<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> -->
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

<link href="<c:url value="/style/payment.css" />" rel="stylesheet" />
</head>
<body>



	<jsp:include page="./navbar.jsp" />


	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">

						<div class="panel-heading display-table">
							<div class="row display-tr">
								<h3 class="panel-title display-td"><tag:message code="paymentDetails"></tag:message></h3>
								<div class="display-td">
									<img class="img-responsive pull-right"
										src="http://i76.imgup.net/accepted_c22e0.png">
								</div>
							</div>
						</div>

						<form class="form-signin" action="./pay" method="post">

							<div class="row">


								<div class="form-label-group">
									<label for="cardNumber"><tag:message code="cardNumber"></tag:message></label>
									<input type="email" id="cardNumber" class="form-control"
										placeholder="Valid card number" required autofocus
										name="cardNumber">
								</div>

								<br>

								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">

										<label for="first_name"><tag:message code="expiration"></tag:message></label>
										<input name="cardExpiry" id="cardExpiry"
											class="form-control input-sm" placeholder="First Name"
											type="number" placeholder="MM / YY" required>
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">

										<label for="cvv">CVV</label> <input type="number" id="cvv"
											class="form-control input-sm" placeholder="CVV" name="cvv">
									</div>
								</div>
							</div>

							<br>


							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit">
								<tag:message code="pay"></tag:message>
							</button>

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