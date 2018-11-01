<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"
	import="java.util.Set, java.util.HashSet, com.java.components.Card"
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

<title>Cards</title>

<link href="<c:url value="/style/styles.css" />" rel="stylesheet" />
</head>
<body>



	<jsp:include page="./navbar.jsp" />

	<%
		Set<Card> cards = request.getAttribute("cards") == null ? new HashSet<>()
				: (Set<Card>) request.getAttribute("cards");
		int size = cards.size();
	%>


	<div class="container" id="tourpackages-carousel">
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
							<h1>
								My Cards <a class="btn icon-btn btn-primary pull-right"
									href="<%=request.getContextPath()%>/addNewCard"><span
									class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span>
									Add New Card</a>
							</h1>
						</div>

						<%
							for (Card card : cards) {
						%>

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

								<%
									boolean changeCard = (session.getAttribute("changeCard") == null) ? false
												: ((boolean) session.getAttribute("changeCard"));

										session.removeAttribute("changeCard");

										System.out.println("changeCard is " + changeCard);

										if (changeCard) {
								%>
								<a
									href="<%=request.getContextPath()%>/selectCard?cardid=<%=card.getCardId()%>"
									class="btn btn-primary btn-xs btn-update btn-add-card">Select</a>

								<%
									}
								%>

								<a
									href="<%=request.getContextPath()%>/editCard?cardid=<%=card.getCardId()%>"
									class="btn btn-primary btn-xs btn-update btn-add-card">Edit</a>

								<a
									href="<%=request.getContextPath()%>/deleteCard?cardid=<%=card.getCardId()%>"
									class="btn btn-primary btn-xs btn-update btn-add-card">Delete</a>

							</div>
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