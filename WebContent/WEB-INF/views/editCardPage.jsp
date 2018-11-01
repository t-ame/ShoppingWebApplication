<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="errorPage.jsp"
	import="com.java.components.Card, com.java.components.Address"
	isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.payment/1.2.3/jquery.payment.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<!-- If you're using Stripe for payments -->
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>


<title>Edit card</title>

<link
	href="<c:url value="<%=request.getContextPath()%>/style/payment.css" />"
	rel="stylesheet" />
<link
	href="<c:url value="<%=request.getContextPath()%>/style/styles.css" />"
	rel="stylesheet" />
</head>
<body>



	<jsp:include page="./navbar.jsp" />


	<div class="container">
		<div class="row">

			<div class="col-lg-3">

				<%
					String conPath = request.getContextPath();
					Card card = (request.getAttribute("card") == null) ? new Card() : (Card) request.getAttribute("card");
					Address address = (card.getBillingAddress() == null) ? new Address() : card.getBillingAddress();
					System.out.println("card is " + card);
					System.out.println("address is " + address);
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
								<form action="<%=request.getContextPath()%>/updateCard"
									method="post">
									<input type="hidden" name="cardId"
										value="<%=card.getCardId()%>">

									<div class="form-group row">
										<label for="cardType" class="col-4 col-form-label"><tag:message
												code="cardType"></tag:message></label>
										<div class="controls">
											<select id="cardType" name="cardType" class=" col-8" required>
												<option value=""
													<%=card.getCardType() != null && card.getCardType() == null ? "selected" : ""%>
													disabled>Select</option>
												<option value="DISCOVER"
													<%=card.getCardType() != null && card.getCardType().toString().equalsIgnoreCase("DISCOVER")
					? "selected"
					: ""%>>DISCOVER</option>
												<option value="VISA"
													<%=card.getCardType() != null && card.getCardType().toString().equalsIgnoreCase("VISA") ? "selected"
					: ""%>>VISA</option>
												<option value="AMERICAN_EXPRESS"
													<%=card.getCardType() != null && card.getCardType().toString().equalsIgnoreCase("AMERICAN_EXPRESS")
					? "selected"
					: ""%>>AMERICAN
													EXPRESS</option>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label for="cardName" class="col-4 col-form-label"><tag:message
												code="cardName"></tag:message></label>
										<div class="col-8">
											<input id=cardName name="cardName" placeholder="Card holder"
												class="form-control here" type="text" required
												value="<%=card.getCardName() == null ? "" : card.getCardName()%>">
										</div>
									</div>
									<div class="form-group row">
										<label for="cardNumber" class="col-4 col-form-label"><tag:message
												code="cardNumber"></tag:message></label>
										<div class="col-8">
											<input id="cardNumber" name="cardNumber"
												placeholder="Card number" class="form-control here" required
												type="text" pattern="^\d{16}$" title="16 digit card number."
												value="<%=card.getCardNumber() == 0 ? "" : card.getCardNumber()%>">
										</div>
									</div>
									<div class="form-group row">
										<label for="expiration" class="col-4 col-form-label"><tag:message
												code="expiration"></tag:message></label>
										<div class="col-8">
											<input id="expiration" name="expiryDate" placeholder="MM/YY"
												pattern="^\d{2}[/]\d{2}$"
												title="Expiry date must be in format MM/YY"
												class="form-control here" type="text" required
												value="<%=card.getExpiryDate() == null ? "" : card.getExpiryDate()%>">
										</div>
									</div>
									<div class="form-group row">
										<label for="cvv" class="col-4 col-form-label">CVV</label>
										<div class="col-8">
											<input id="cvv" name="cvv" placeholder="cvv"
												class="form-control here" type="text" required
												pattern="^\d{3}$" title="3 digit CVV number."
												value="<%=card.getCvv() == 0 ? "" : card.getCvv()%>">
										</div>
									</div>


									<%
									boolean editCard = (request.getAttribute("editCard") == null) ? false
												: ((boolean) request.getAttribute("editCard"));

										if (!editCard) {
									%>

									<fieldset>
										<!-- Address form -->

										<div class="col-md-12">
											<h4>
												<tag:message code="billingAddress"></tag:message>
											</h4>
											<hr>
										</div>

										<input type="hidden" name="addressId"
											value="<%=address.getAddressId()%>">

										<!-- FILL IN ADDRESS FIELDS -->

										<!-- full-name input-->
										<div class="form-group row">
											<label for="addressLine1" class="col-4 col-form-label"><tag:message
													code="addressLine"></tag:message> 1</label>
											<div class="col-8">
												<input id="addressLine1" name="addressLine1"
													class="form-control here" type="text" required
													value="<%=address.getAddressLine1() == null ? "" : address.getAddressLine1()%>">
												<p class="help-block">
													<tag:message code="streetAddress"></tag:message>
													, c/o
												</p>
											</div>
										</div>
										<div class="form-group row">
											<label for="addressLine2" class="col-4 col-form-label"><tag:message
													code="addressLine"></tag:message> 2</label>
											<div class="col-8">
												<input id="addressLine2" name="addressLine2"
													class="form-control here" type="text"
													placeholder="(<tag:message code="optional"></tag:message>)"
													value="<%=address.getAddressLine2() == null ? "" : address.getAddressLine2()%>">
												<p class="help-block">
													<tag:message code="apartment"></tag:message>
													, etc.
												</p>
											</div>
										</div>
										<div class="form-group row">
											<label for="city" class="col-4 col-form-label"><tag:message
													code="cityTown"></tag:message></label>
											<div class="col-8">
												<input id="city" name="city" class="form-control here"
													type="text" required
													value="<%=address.getCity() == null ? "" : address.getCity()%>">
											</div>
										</div>
										<div class="form-group row">
											<label for="state" class="col-4 col-form-label"><tag:message
													code="stateRegion"></tag:message></label>
											<div class="col-8">
												<input id="state" name="state" class="form-control here"
													type="text" required
													value="<%=address.getState() == null ? "" : address.getState()%>">
											</div>
										</div>
										<div class="form-group row">
											<label for="zipcode" class="col-4 col-form-label"> <tag:message
													code="zipcode"></tag:message>
											</label>
											<div class="col-8">
												<input id="zipcode" name="zipcode" class="form-control here"
													type="text" required pattern="^\d{5}$"
													title="5 digit zip code."
													value="<%=address.getZipcode() == 0 ? "" : address.getZipcode()%>">
											</div>
										</div>

										<!-- country select -->
										<div class="form-group row">
											<label for="country" class="col-4 col-form-label"><tag:message
													code="country"></tag:message></label>
											<div class="controls">
												<select id="country" name="country" class=" col-8" required>
													<option value="" selected disabled>Select</option>
													<option value="AF">Afghanistan</option>
													<option value="AL">Albania</option>
													<option value="DZ">Algeria</option>
													<option value="AS">American Samoa</option>
													<option value="AD">Andorra</option>
													<option value="AO">Angola</option>
													<option value="AI">Anguilla</option>
													<option value="AQ">Antarctica</option>
													<option value="AG">Antigua and Barbuda</option>
													<option value="AR">Argentina</option>
													<option value="AM">Armenia</option>
													<option value="AW">Aruba</option>
													<option value="AU">Australia</option>
													<option value="AT">Austria</option>
													<option value="AZ">Azerbaijan</option>
													<option value="BS">Bahamas</option>
													<option value="BH">Bahrain</option>
													<option value="BD">Bangladesh</option>
													<option value="BB">Barbados</option>
													<option value="BY">Belarus</option>
													<option value="BE">Belgium</option>
													<option value="BZ">Belize</option>
													<option value="BJ">Benin</option>
													<option value="BM">Bermuda</option>
													<option value="BT">Bhutan</option>
													<option value="BO">Bolivia</option>
													<option value="BA">Bosnia and Herzegowina</option>
													<option value="BW">Botswana</option>
													<option value="BV">Bouvet Island</option>
													<option value="BR">Brazil</option>
													<option value="IO">British Indian Ocean Territory</option>
													<option value="BN">Brunei Darussalam</option>
													<option value="BG">Bulgaria</option>
													<option value="BF">Burkina Faso</option>
													<option value="BI">Burundi</option>
													<option value="KH">Cambodia</option>
													<option value="CM">Cameroon</option>
													<option value="CA">Canada</option>
													<option value="CV">Cape Verde</option>
													<option value="KY">Cayman Islands</option>
													<option value="CF">Central African Republic</option>
													<option value="TD">Chad</option>
													<option value="CL">Chile</option>
													<option value="CN">China</option>
													<option value="CX">Christmas Island</option>
													<option value="CC">Cocos (Keeling) Islands</option>
													<option value="CO">Colombia</option>
													<option value="KM">Comoros</option>
													<option value="CG">Congo</option>
													<option value="CD">Congo, the Democratic Republic
														of the</option>
													<option value="CK">Cook Islands</option>
													<option value="CR">Costa Rica</option>
													<option value="CI">Cote d'Ivoire</option>
													<option value="HR">Croatia (Hrvatska)</option>
													<option value="CU">Cuba</option>
													<option value="CY">Cyprus</option>
													<option value="CZ">Czech Republic</option>
													<option value="DK">Denmark</option>
													<option value="DJ">Djibouti</option>
													<option value="DM">Dominica</option>
													<option value="DO">Dominican Republic</option>
													<option value="TP">East Timor</option>
													<option value="EC">Ecuador</option>
													<option value="EG">Egypt</option>
													<option value="SV">El Salvador</option>
													<option value="GQ">Equatorial Guinea</option>
													<option value="ER">Eritrea</option>
													<option value="EE">Estonia</option>
													<option value="ET">Ethiopia</option>
													<option value="FK">Falkland Islands (Malvinas)</option>
													<option value="FO">Faroe Islands</option>
													<option value="FJ">Fiji</option>
													<option value="FI">Finland</option>
													<option value="FR">France</option>
													<option value="FX">France, Metropolitan</option>
													<option value="GF">French Guiana</option>
													<option value="PF">French Polynesia</option>
													<option value="TF">French Southern Territories</option>
													<option value="GA">Gabon</option>
													<option value="GM">Gambia</option>
													<option value="GE">Georgia</option>
													<option value="DE">Germany</option>
													<option value="GH">Ghana</option>
													<option value="GI">Gibraltar</option>
													<option value="GR">Greece</option>
													<option value="GL">Greenland</option>
													<option value="GD">Grenada</option>
													<option value="GP">Guadeloupe</option>
													<option value="GU">Guam</option>
													<option value="GT">Guatemala</option>
													<option value="GN">Guinea</option>
													<option value="GW">Guinea-Bissau</option>
													<option value="GY">Guyana</option>
													<option value="HT">Haiti</option>
													<option value="HM">Heard and Mc Donald Islands</option>
													<option value="VA">Holy See (Vatican City State)</option>
													<option value="HN">Honduras</option>
													<option value="HK">Hong Kong</option>
													<option value="HU">Hungary</option>
													<option value="IS">Iceland</option>
													<option value="IN">India</option>
													<option value="ID">Indonesia</option>
													<option value="IR">Iran (Islamic Republic of)</option>
													<option value="IQ">Iraq</option>
													<option value="IE">Ireland</option>
													<option value="IL">Israel</option>
													<option value="IT">Italy</option>
													<option value="JM">Jamaica</option>
													<option value="JP">Japan</option>
													<option value="JO">Jordan</option>
													<option value="KZ">Kazakhstan</option>
													<option value="KE">Kenya</option>
													<option value="KI">Kiribati</option>
													<option value="KP">Korea, Democratic People's
														Republic of</option>
													<option value="KR">Korea, Republic of</option>
													<option value="KW">Kuwait</option>
													<option value="KG">Kyrgyzstan</option>
													<option value="LA">Lao People's Democratic
														Republic</option>
													<option value="LV">Latvia</option>
													<option value="LB">Lebanon</option>
													<option value="LS">Lesotho</option>
													<option value="LR">Liberia</option>
													<option value="LY">Libyan Arab Jamahiriya</option>
													<option value="LI">Liechtenstein</option>
													<option value="LT">Lithuania</option>
													<option value="LU">Luxembourg</option>
													<option value="MO">Macau</option>
													<option value="MK">Macedonia, The Former Yugoslav
														Republic of</option>
													<option value="MG">Madagascar</option>
													<option value="MW">Malawi</option>
													<option value="MY">Malaysia</option>
													<option value="MV">Maldives</option>
													<option value="ML">Mali</option>
													<option value="MT">Malta</option>
													<option value="MH">Marshall Islands</option>
													<option value="MQ">Martinique</option>
													<option value="MR">Mauritania</option>
													<option value="MU">Mauritius</option>
													<option value="YT">Mayotte</option>
													<option value="MX">Mexico</option>
													<option value="FM">Micronesia, Federated States of</option>
													<option value="MD">Moldova, Republic of</option>
													<option value="MC">Monaco</option>
													<option value="MN">Mongolia</option>
													<option value="MS">Montserrat</option>
													<option value="MA">Morocco</option>
													<option value="MZ">Mozambique</option>
													<option value="MM">Myanmar</option>
													<option value="NA">Namibia</option>
													<option value="NR">Nauru</option>
													<option value="NP">Nepal</option>
													<option value="NL">Netherlands</option>
													<option value="AN">Netherlands Antilles</option>
													<option value="NC">New Caledonia</option>
													<option value="NZ">New Zealand</option>
													<option value="NI">Nicaragua</option>
													<option value="NE">Niger</option>
													<option value="NG">Nigeria</option>
													<option value="NU">Niue</option>
													<option value="NF">Norfolk Island</option>
													<option value="MP">Northern Mariana Islands</option>
													<option value="NO">Norway</option>
													<option value="OM">Oman</option>
													<option value="PK">Pakistan</option>
													<option value="PW">Palau</option>
													<option value="PA">Panama</option>
													<option value="PG">Papua New Guinea</option>
													<option value="PY">Paraguay</option>
													<option value="PE">Peru</option>
													<option value="PH">Philippines</option>
													<option value="PN">Pitcairn</option>
													<option value="PL">Poland</option>
													<option value="PT">Portugal</option>
													<option value="PR">Puerto Rico</option>
													<option value="QA">Qatar</option>
													<option value="RE">Reunion</option>
													<option value="RO">Romania</option>
													<option value="RU">Russian Federation</option>
													<option value="RW">Rwanda</option>
													<option value="KN">Saint Kitts and Nevis</option>
													<option value="LC">Saint LUCIA</option>
													<option value="VC">Saint Vincent and the
														Grenadines</option>
													<option value="WS">Samoa</option>
													<option value="SM">San Marino</option>
													<option value="ST">Sao Tome and Principe</option>
													<option value="SA">Saudi Arabia</option>
													<option value="SN">Senegal</option>
													<option value="SC">Seychelles</option>
													<option value="SL">Sierra Leone</option>
													<option value="SG">Singapore</option>
													<option value="SK">Slovakia (Slovak Republic)</option>
													<option value="SI">Slovenia</option>
													<option value="SB">Solomon Islands</option>
													<option value="SO">Somalia</option>
													<option value="ZA">South Africa</option>
													<option value="GS">South Georgia and the South
														Sandwich Islands</option>
													<option value="ES">Spain</option>
													<option value="LK">Sri Lanka</option>
													<option value="SH">St. Helena</option>
													<option value="PM">St. Pierre and Miquelon</option>
													<option value="SD">Sudan</option>
													<option value="SR">Suriname</option>
													<option value="SJ">Svalbard and Jan Mayen Islands</option>
													<option value="SZ">Swaziland</option>
													<option value="SE">Sweden</option>
													<option value="CH">Switzerland</option>
													<option value="SY">Syrian Arab Republic</option>
													<option value="TW">Taiwan, Province of China</option>
													<option value="TJ">Tajikistan</option>
													<option value="TZ">Tanzania, United Republic of</option>
													<option value="TH">Thailand</option>
													<option value="TG">Togo</option>
													<option value="TK">Tokelau</option>
													<option value="TO">Tonga</option>
													<option value="TT">Trinidad and Tobago</option>
													<option value="TN">Tunisia</option>
													<option value="TR">Turkey</option>
													<option value="TM">Turkmenistan</option>
													<option value="TC">Turks and Caicos Islands</option>
													<option value="TV">Tuvalu</option>
													<option value="UG">Uganda</option>
													<option value="UA">Ukraine</option>
													<option value="AE">United Arab Emirates</option>
													<option value="GB">United Kingdom</option>
													<option value="US">United States</option>
													<option value="UM">United States Minor Outlying
														Islands</option>
													<option value="UY">Uruguay</option>
													<option value="UZ">Uzbekistan</option>
													<option value="VU">Vanuatu</option>
													<option value="VE">Venezuela</option>
													<option value="VN">Viet Nam</option>
													<option value="VG">Virgin Islands (British)</option>
													<option value="VI">Virgin Islands (U.S.)</option>
													<option value="WF">Wallis and Futuna Islands</option>
													<option value="EH">Western Sahara</option>
													<option value="YE">Yemen</option>
													<option value="YU">Yugoslavia</option>
													<option value="ZM">Zambia</option>
													<option value="ZW">Zimbabwe</option>
												</select>
											</div>
										</div>
									</fieldset>

									<%
										}
									%>

									<button class="btn btn-lg btn-primary btn-block text-uppercase"
										type="submit">
										<tag:message code="save"></tag:message>
									</button>
								</form>
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