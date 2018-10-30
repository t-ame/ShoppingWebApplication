<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.java.components.User, com.java.components.UserDetails, com.java.components.Address, java.util.Set, java.util.HashSet"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<!DOCTYPE html>
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


<title>Profile</title>

<link rel="stylesheet" href="<c:url value="/style/payment.css" />" />
</head>
<body>



	<jsp:include page="./navbar.jsp" />


	<div class="container">
		<div class="row">

			<div class="col-lg-3">

				<%
					String conPath = request.getContextPath();
				%>

				<h1 class="my-4">MyCart</h1>
				<div class="list-group">
					<a href="<%=conPath%>/categoryProducts/Electronics?page=1"
						class="list-group-item"><tag:message code="electronics"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Clothing?page=1"
						class="list-group-item"><tag:message code="clothing"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Outdoors?page=1"
						class="list-group-item"><tag:message code="outdoors"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Home?page=1"
						class="list-group-item"><tag:message code="home"></tag:message></a>
					<a href="<%=conPath%>/categoryProducts/Books?page=1"
						class="list-group-item"><tag:message code="books"></tag:message></a>
				</div>

			</div>

			<div class="col-md-9">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-md-12">
								<h4>
									<tag:message code="yourProfile"></tag:message>
								</h4>
								<hr>
							</div>
						</div>

						<%
							User user = (User) session.getAttribute("user");
							UserDetails ud = null;
							Address ad = null;
							Set<Address> addset = new HashSet<>();
							if (user != null && user.getUserDetails() != null) {
								ud = user.getUserDetails();
								addset = ud.getAddresses();
								if (addset == null || addset.size() <= 0) {
									ad = new Address();
								} else {
									ad = addset.iterator().next();
								}
							} else {
								user = new User();
								ud = new UserDetails();
								ad = new Address();
							}
						%>

						<div class="row">
							<div class="col-md-12">
								<form action="./updateProfile" method="post">

									<div class="errorMsg"><%=request.getAttribute("formError") == null ? "" : request.getAttribute("formError")%></div>
									<%
										request.setAttribute("formError", "");
									%>

									<div class="form-group row">
										<label for="email" class="col-4 col-form-label"><tag:message
												code="email"></tag:message></label>
										<div class="col-8">
											<input id="email" name="userEmail"
												placeholder="<tag:message code="email"></tag:message>"
												class="form-control here" required="required" type="text"
												value="<%=user.getUserEmail()%>">
										</div>
									</div>
									<div class="form-group row">
										<label for="firstName" class="col-4 col-form-label"><tag:message
												code="firstName"></tag:message></label>
										<div class="col-8">
											<input id="name" name="firstName"
												placeholder="<tag:message code="firstName"></tag:message>"
												class="form-control here" type="text"
												value="<%=ud.getFirstName()%>">
										</div>
									</div>
									<div class="form-group row">
										<label for="lastname" class="col-4 col-form-label"><tag:message
												code="lastName"></tag:message></label>
										<div class="col-8">
											<input id="lastname" name="lastName"
												placeholder="<tag:message code="lastName"></tag:message>"
												class="form-control here" type="text"
												value="<%=ud.getLastName()%>">
										</div>
									</div>
									<div class="form-group row">
										<label for="newpass" class="col-4 col-form-label"><tag:message
												code="password"></tag:message></label>
										<div class="col-8">
											<input id="newpass" name="userPassword"
												placeholder="<tag:message code="password"></tag:message>"
												class="form-control here" type="password"
												value="<%=user.getUserPassword()%>">
										</div>
									</div>

									<div class="form-group row">
										<label for="email" class="col-4 col-form-label"><tag:message
												code="mobileNumber"></tag:message></label>
										<div class="col-8">
											<input id="email" name="mobileNumber"
												placeholder="<tag:message code="mobileNumber"></tag:message>"
												class="form-control here" required="required" type="tel"
												value="<%=ud.getMobileNumber()%>">
										</div>
									</div>


									<div class="form-group row">
										<label for="gender" class="col-4 col-form-label"><tag:message
												code="gender"></tag:message> </label>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input
												class="form-check-input" type="radio" name="genders"
												id="inlineRadio1"
												value="<%=ud.getGender() == UserDetails.Gender.FEMALE ? "checked" : ""%>">
												<tag:message code="female"></tag:message>
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <input
												class="form-check-input" type="radio" name="genders"
												id="inlineRadio2"
												value="<%=ud.getGender() == UserDetails.Gender.MALE ? "checked" : ""%>">
												<tag:message code="male"></tag:message>
											</label>
										</div>
									</div>

									<input type="hidden" name="userId" value="<%=ud.getUserId()%>">


									<fieldset>
										<!-- Address form -->

										<input type="hidden" name="addressId"
											value="<%=ad.getAddressId()%>">

										<div class="col-md-12">
											<h4>
												<tag:message code="address"></tag:message>
											</h4>
											<hr>
										</div>

										<!-- full-name input-->
										<div class="form-group row">
											<label for="firstName" class="col-4 col-form-label"><tag:message
													code="addressLine"></tag:message> 1</label>
											<div class="col-8">
												<input id="name" name="addressLine1"
													class="form-control here" type="text"
													value="<%=ad.getAddressLine1() == null ? "" : ad.getAddressLine1()%>"
													required>
												<p class="help-block">
													<tag:message code="streetAddress"></tag:message>
													, c/o
												</p>
											</div>
										</div>
										<div class="form-group row">
											<label for="firstName" class="col-4 col-form-label"><tag:message
													code="addressLine"></tag:message> 2</label>
											<div class="col-8">
												<input id="name" name="addressLine2"
													class="form-control here" type="text"
													placeholder="<tag:message code="optional"></tag:message>"
													value="<%=ad.getAddressLine2() == null ? "" : ad.getAddressLine2()%>">
												<p class="help-block">
													<tag:message code="apartment"></tag:message>
													, etc.
												</p>
											</div>
										</div>
										<div class="form-group row">
											<label for="firstName" class="col-4 col-form-label"><tag:message
													code="cityTown"></tag:message></label>
											<div class="col-8">
												<input id="name" name="city" class="form-control here"
													type="text"
													value="<%=ad.getCity() == null ? "" : ad.getCity()%>"
													required>
											</div>
										</div>
										<div class="form-group row">
											<label for="firstName" class="col-4 col-form-label"><tag:message
													code="stateRegion"></tag:message></label>
											<div class="col-8">
												<input id="name" name="state" class="form-control here"
													type="text"
													value="<%=ad.getState() == null ? "" : ad.getState()%>"
													required>
											</div>
										</div>
										<div class="form-group row">
											<label for="firstName" class="col-4 col-form-label"><tag:message
													code="zipcode"></tag:message></label>
											<div class="col-8">
												<input id="name" name="zipcode" class="form-control here"
													type="number" value="<%=ad.getZipcode()%>" required>
											</div>
										</div>

										<!-- country select -->
										<div class="form-group row">
											<label for="country" class="col-4 col-form-label"><tag:message
													code="country"></tag:message></label>
											<div class="controls">
												<select id="country" name="country" class=" col-8" required>
													<option value="" selected disabled><tag:message
															code="select"></tag:message></option>
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

									<div class="form-group row">
										<div class="offset-4 col-8">
											<button name="submit" type="submit" class="btn btn-primary">
												<tag:message code="updateProfile"></tag:message>
											</button>
										</div>
									</div>
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