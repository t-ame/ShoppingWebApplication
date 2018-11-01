<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment success</title>

<link href="<c:url value="/style/styles.css" />" rel="stylesheet" />
</head>
<body>

	<jsp:include page="./navbar.jsp" />

	<section class="jumbotron text-center" style="opacity: 0.5">

		<div class="container">

			<h3 class="jumbotron-heading" style="color: green; font-size: 25px">
				<%=request.getAttribute("successMsg") == null ? "Products purchase successful."
					: request.getAttribute("successMsg")%>
			</h3>
		</div>

	</section>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="error-template">
<%-- 
					<h3 class="jumbotron-heading" style="color: white; font-size: 25px">
						<%=request.getAttribute("successMsg") == null ? "" : request.getAttribute("successMsg")%>
					</h3> --%>
					<div class="error-actions">
						<a href="./home" class="btn btn-primary btn-sm"><span
							class="glyphicon glyphicon-home"></span> <tag:message
								code="takeMeHome"></tag:message> </a>
					</div>
				</div>
			</div>
		</div>
	</div>



	<br>

	<pre>
	
	
	
	
	
	
	
	
	
	</pre>

	<jsp:include page="./footer.jsp" />





</body>
</html>