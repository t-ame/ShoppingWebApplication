<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-autocomplete/1.0.7/jquery.auto-complete.min.js"></script> -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">



<link rel="stylesheet"  href="<c:url value="/style/styles.css" />"/>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<title>...</title>

<style type="text/css">
.badge {
	padding-left: 9px;
	padding-right: 9px;
	-webkit-border-radius: 9px;
	-moz-border-radius: 9px;
	border-radius: 9px;
}

.label-warning[href], .badge-warning[href] {
	background-color: #c67605;
}

#lblCartCount {
	font-size: 12px;
	background: #ff0000;
	color: #fff;
	padding: 0 5px;
	vertical-align: top;
	margin-left: -10px;
}

.autocomplete-suggestions { border: 1px solid #999; background: #FFF; overflow: auto; }
.autocomplete-suggestion { padding: 5px 5px; white-space: nowrap; overflow: hidden; font-size:22px}
.autocomplete-selected { background: #F0F0F0; }
.autocomplete-suggestions strong { font-weight: bold; color: #3399FF; }

</style>
</head>
<body>




	<header class="section-header">



		<section class="header-main">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-13-24 col-sm-12 order-3 order-lg-2">
						<form action="<%=request.getContextPath()%>/searchProduct?page=1">
							<input type="hidden" name="page" value="1">
							<div class="input-group w-100">
								<select style="width: 30px;" class="custom-select"
									id="SearchOption" name="className">
									<option value="All" selected><tag:message code="all"></tag:message></option>
									<option value="Electronics"><tag:message
											code="electronics"></tag:message></option>
									<option value="Clothing"><tag:message code="clothing"></tag:message></option>
									<option value="Outdoors"><tag:message code="outdoors"></tag:message></option>
									<option value="Home"><tag:message code="home"></tag:message></option>
									<option value="Books"><tag:message code="books"></tag:message></option>
								</select> <input type="text" class="form-control" style="width: 60%;"
									placeholder="<tag:message code="search"></tag:message>"
									name="searchKeys" id="searchKeys" />

								<div class="input-group-append">
									<button class="btn btn-primary" type="submit">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
						<!-- search-wrap .end// -->
					</div>
					<!-- col.// -->
				</div>
				<!-- row.// -->
			</div>
			<!-- container.// -->
		</section>
		<!-- header-main .// -->

	</header>
	
	<br>
	
	
	
	
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

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>

	

</body>
</html>