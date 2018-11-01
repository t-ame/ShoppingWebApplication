<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<!DOCTYPE html>
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


<link rel="stylesheet" href="style/styles.css">


<title>...</title>
</head>


<body>

	<br>
	
	<pre>
	
	
	
	
	
	
	
	
	</pre>



	<div style="position: relative; bottom: 0; left: 0; width: 100%">

		<!-- Footer -->
		<footer class="page-footer font-small blue pt-4"
			style="background-color: #0c5460; color: white">

			<!-- Footer Links -->
			<div class="container-fluid text-center text-md-left">

				<!-- Grid row -->
				<div class="row">

					<!-- Grid column -->
					<div class="col-md-6 mt-md-0 mt-3">

						<!-- Content -->
						<h5 class="text-uppercase"><a href="<%= request.getContextPath() %>/home">MyCart</a></h5>
						<p><tag:message code="myCartInfo"></tag:message></p>

					</div>


					<!-- Grid column -->

					<hr class="clearfix w-100 d-md-none pb-3">


					<!-- Grid column -->
					<div class="col-md-3 mb-md-0 mb-3">

						<!-- Links -->
						<h5 class="text-uppercase"><tag:message code="getToKnowUs"></tag:message></h5>

						<ul class="list-unstyled">
							<li><a href="#!" style="color: white"><tag:message code="about"></tag:message> MyCart</a></li>
							<li><a href="#!" style="color: white"><tag:message code="services"></tag:message></a></li>
							<li><a href="#!" style="color: white"><tag:message code="careers"></tag:message></a></li>
						</ul>

					</div>

					<!-- Grid column -->
					<div class="col-md-3 mb-md-0 mb-3">

						<!-- Links -->
						<h5 class="text-uppercase"><tag:message code="letUsHelp"></tag:message></h5>

						<ul class="list-unstyled">
							<li><a href="<%= request.getContextPath() %>/profile" style="color: white"><tag:message code="myAccount"></tag:message></a></li>
							<li><a href="<%= request.getContextPath() %>/displayHistory" style="color: white"><tag:message code="myOrders"></tag:message></a></li>
							<li><a href="#!" style="color: white"><tag:message code="help"></tag:message></a></li>
						</ul>

					</div>

					<!-- Grid column -->

				</div>
				<!-- Grid row -->

			</div>
			<!-- Footer Links -->

			<!-- Copyright -->
			<div class="footer-copyright text-center py-3">
				© 2018 <tag:message code="copyright"></tag:message>: <a href="#" style="color: white"> MyCart.com</a>
			</div>
			<!-- Copyright -->

		</footer>
		<!-- Footer -->

	</div>
	
	
	
	
	

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>



<script>
  $(document).ready(function() {
	  
	  console.log('Hello');

	/* $('#searchKeys').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/productNames',
		paramName: "searchKeys",
		delimiter: ",",
	   transformResult: function(response) {
		    	
		return {      	
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {
		            	
		      return { value: item.productName, data: item.productId };
		   })
		            
		 };
		        
            }
		    
	 }); */
				
  });
  </script>



</body>
</html>