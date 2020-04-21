<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="js/jquery.datetimepicker.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	min-height: 300px;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	min-height: 100%;
	margin: 0;
	padding: 0;
}

body {
	font-size: 17px;
}

</style>


<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-blue bold uppercase"><i
				class="fa fa-clock-o"></i><fmt:formatDate value="${currentDate}"
																	pattern="EEE dd-MMM-yyyy HH:mm" /></span>
			<!-- <p id="asd">asddd</p> -->
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="row">
	<form action="save-check" class="form-horizontal" method="POST">
		
						<div class="col-lg-12">

       						
       						<div class="input-icon form-group form-md-line-input inpur-group"
									>
										<i class="fa fa-calendar"></i>
										<input type="text" id="mydate" name="work_hours_date_work"
											value="${fulldate}" onchange="datechenge()"
											style="text-align: center;"
											class="form-control info date-picker input-group"
											size="9" type="text" data-date-format="dd-mm-yyyy"
											onkeypress='return false'>
								</div>

							
							<div class="form-group form-line-input">
								<div class="col-md-2"></div>
								<div class="col-md-8">
								<input type="text" name="work_hours_time_work" id="ourtime"
													class="form-control timepicker timepicker-24 line-input-lg numeric-input"
													value="${time}" data-time-format="HH:mm"
													style="border: 0px; text-align: center; font-size: 80px; height:150px" onchange="timechenge()"
													onkeypress='return false'> 
								</div>
								<div class="col-md-2"></div>
							</div>
							
								<div class="form-group form-lg-line-input"
									>
									<div class="col-md-12">
										<div class="md-radio-inline" style="text-align:center">
											<div class="md-radio">
												<input type="radio" id="checkbox1_1" name="work_hours_type"
													value="1" class="md-radiobtn"
													onclick="show_text(this.value);"> <label
													for="checkbox1_1" style="color: #32c5d2;"> <span></span>
													<span class="check"></span> <span class="box"></span>
													Check-IN
												</label>
											</div>
											<div class="md-radio">
												<input type="radio" id="checkbox1_2" name="work_hours_type"
													value="2" class="md-radiobtn"
													onclick="show_text(this.value);"> <label
													for="checkbox1_2" style="color: red;"> <span></span>
													<span class="check" style="background: #ff2524;"></span> <span
													class="box"></span> Check-OUT
												</label>
											</div>
										</div>
										<div align="center">
											

											<div class="input-group-btn blue">
			                            			<button 
			                            				title="Show location"
			                            				class="btn default" type="button" onclick="toggleMap()">
			                                            <i class="fa fa-map"></i>
			                                        </button>
			                                </div>
											<font color="gray"><c:forEach var="lastcheckin"
													items="${lastcheckin}" varStatus="status">
													
													<div>
														<sub>( Last Check-In : </sub> <sub id="demo"><fmt:formatDate value="${lastcheckin.work_hours_time_work}"
																	pattern=" dd-MMM HH:mm" /></sub> <sub>)</sub>
													</div>

												</c:forEach></font>											
										</div>
									</div>
								</div>
								

								<div class="form-group form-lg-line-input"
									>
									<div class="col-md-12">
										<textarea class="form-control " name="description"
											maxlength="500" rows="3" placeholder="กรุณาระบุเหตุผล"
											id="detail"></textarea>
										<div class="form-control-focus"></div>
									</div>
								</div>

								<input type="hidden" id="x" class="latitude" name="latitude">
								<input type="hidden" id="y" class="longitude" name="longitude">

<script>
	function toggleMap() {

        if( $('#map').css('display') == 'none' ) {
            $("#map").show();
        } else {
            $('#map').hide();
        }
	}
</script>                                                           
								<div id="map">
								<script>
									// Note: This example requires that you consent to location sharing when
									// prompted by your browser. If you see the error "The Geolocation service
									// failed.", it means you probably did not give permission for the browser to
									// locate you.

									var map, infoWindow, marker;
									function initMap() {
										map = new google.maps.Map(document
												.getElementById('map'), {
											center : {
												lat : -34.397,
												lng : 150.644
											},
											zoom : 16
										});
										var latEl = document
												.querySelector('.latitude');
										var longEl = document
												.querySelector('.longitude');
										infoWindow = new google.maps.InfoWindow;
										marker = new google.maps.Marker;
										// Try HTML5 geolocation.
										if (navigator.geolocation) {
											navigator.geolocation
													.getCurrentPosition(
															function(position) {
																var pos = {
																	lat : position.coords.latitude,
																	lng : position.coords.longitude
																};
																x = pos.lat;
																y = pos.lng;

																		marker
																				.setPosition(pos),
																		marker
																				.setMap(map),
																		marker
																				.setDraggable(true);

																infoWindow
																		.setContent('Current Position');
																infoWindow
																		.open(
																				map,
																				marker);
																map
																		.setCenter(pos);
																adddata();
																google.maps.event
																		.addListener(
																				marker,
																				"dragend",
																				function(
																						event) {
																					var lati, lngti, address;
																					console
																							.log('i am dragged');
																					lati = marker
																							.getPosition()
																							.lat();
																					lngti = marker
																							.getPosition()
																							.lng();
																					var geocoder = new google.maps.Geocoder();
																					geocoder
																							.geocode(
																									{
																										latLng : marker
																												.getPosition()
																									},
																									function(
																											result,
																											status) {
																										if ('OK' === status) { // This line can also be written like if ( status == google.maps.GeocoderStatus.OK ) {
																											address = result[0].formatted_address;
																											resultArray = result[0].address_components;

																											// Get the city and set the city input value to the one selected
																											/*for( var i = 0; i < resultArray.length; i++ ) {
																												if ( resultArray[ i ].types[0]  ) {
																													citi = resultArray[ i ].long_name;
																													console.log( citi );
																													city.value = citi;
																												}
																											}*/

																											latEl.value = lati;
																											longEl.value = lngti;

																										} else {
																											console
																													.log('Geocode was not successful for the following reason: '
																															+ status);
																										}
																										if (infoWindow) {
																											infoWindow
																													.close();
																										}

																										/**
																										 * Creates the info Window at the top of the marker
																										 */
																										infoWindow = new google.maps.InfoWindow(
																												{
																													content : address
																												});

																										infoWindow
																												.open(
																														map,
																														marker);
																									});
																				});
															},
															function() {
																handleLocationError(
																		true,
																		infoWindow,
																		map
																				.getCenter());
															});
										} else {
											// Browser doesn't support Geolocation
											handleLocationError(false,
													infoWindow, map.getCenter());
										}

									}
									function adddata() {
										$(document)
												.ready(
														function() {
															document
																	.getElementById("x").value = x;
															//console.log(x); 

														});

										$(document)
												.ready(
														function() {
															document
																	.getElementById("y").value = y;
															//console.log(y); 

														});
									}
									function handleLocationError(
											browserHasGeolocation, infoWindow,
											pos) {
										infoWindow.setPosition(pos);
										infoWindow
												.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
														: 'Error: Your browser doesn\'t support geolocation.');
										infoWindow.open(map);
									}
								</script>
								<script async defer
									src="https://maps.googleapis.com/maps/api/js?key=${GOOGLE_API_KEY}&callback=initMap">
									
								</script>

							
							</div>
							<!--  stopgooglemap-->

							<div class="form-group form-md-line-input text-center">

									<button class="btn btn-info" style="font-size: 24px"
										type="submit" id="checktime" name="savebtn">
										<i class="fa fa-toggle-right"></i> Confirm
									</button>

								</div>
								
							</form>	
						</div>
						<!--  startgooglemap-->

	</div>
</div>


<style>
.mt-widget-3 {
	border: 0px;
}
</style>


<script>



	function nextStep() {
		document.location = "check_list?userId=${onlineUser.id}";
	}
	function timechenge() {
		var mytime = $("#ourtime").val();//เวลาที่เลือก
		var timeNow = "${time}".trim();//เวลาปัจจุบัน
		var timeNowlength = "${time}".trim().length;//ความยาวเวลาปัจจุบัน
		var mytimelength = $("#ourtime").val().length;//ความยาวเวลาที่เลือก
		var hourtimeNow = null; //ชั่วโมงปัจจุบัน
		var mintimeNow = null; //นาทีปัจจุบัน
		var hourmytime = null; //ชั่วโมงที่เลือก
		var minmytime = null; //นาทีที่เลือก
		var fullmytime = null; //คำนวณชั่วโมงที่เลือก
		var fulltimenow = null; //คำนวณชั่วโมงปัจจุบัน
		if (mytimelength == '4') { //เวลาที่เลือกก่อนเที่ยง = 4 
			hourmytime = mytime.substring(0, 1);
			minmytime = mytime.substring(2, 4);
		} else if (mytimelength == '5') {//เวลาที่เลือกหลังเที่ยง = 5
			hourmytime = mytime.substring(0, 2);
			minmytime = mytime.substring(3, 5);
		}
		if (timeNowlength == '4') { // เวลาปัจจุบันก่อนเที่ยง =4
			hourtimeNow = timeNow.substring(0, 1);
			mintimeNow = timeNow.substring(2, 4);
		} else if (timeNowlength == '5') { //เวลาปัจจุบันหลังเที่ยง  =5
			hourtimeNow = timeNow.substring(0, 2);
			mintimeNow = timeNow.substring(3, 5);
		}
		fullmytime = ((hourmytime * 60) + minmytime);
		fulltimenow = ((hourtimeNow * 60) + mintimeNow);
		if (fullmytime != fulltimenow) { // ถ้าเวลาที่เลือกไม่เท่ากับเวลาปัจจุบันให้ Show detail
			$("#detail").show();
			$("#labeldetail").show();
		} else {
			$("#detail").hide();
			$("#labeldetail").hide();
		}
	}
	function datechenge() {

		$('#ourtime').timepicker({
            minuteStep: 5,
            showMeridian: false,
            align : 'right'
        });
	
		var fulldate = "${fulldate}".trim();//วันที่ปัจจุบัน
		var Userdate = $("#mydate").val();//วันที่เลือก
		if (fulldate != Userdate) {
			$("#detail").show();
			$("#labeldetail").show();
		} else {
			$("#detail").hide();
			$("#labeldetail").hide();
		}
	}

	function back() {
		document.location = "check_in";
	}

	function travel() {
		document.location = "travelexp_form";
	}

	function leave() {
		document.location = "LeaveAdd";
	}

	function pageload() {
		var day = new Date();
		var mytime = day.getHours();
		if (mytime < 12) {
			checkbox1_1.checked = true;
			$("#detail").hide();
			$("#labeldetail").hide();

		} else {
			checkbox1_2.checked = true;
			$("#detail").hide();
			$("#labeldetail").hide();

		}
	}
	window.onload = pageload;
	$("#checktime")
			.click(
					function() {
						var timecheckin = "${timecheckin}".trim();//เวลาเช็คอินล่าสุด
						var timecheckinlength = "${timecheckin}".trim().length;//ความยาวเวลาเช็คอินล่าสุด
						var datecheckin = "${datecheckin}".trim();//วันที่เช็คอินล่าสุด
						var fulldate = "${fulldate}".trim();//วันที่ปัจจุบัน
						var timeNow = "${time}".trim();//เวลาปัจจุบัน
						var timeNowlength = "${time}".trim().length;//ความยาวเวลาปัจจุบัน
						var mytime = $("#ourtime").val();//เวลาที่เลือก
						var mytimelength = $("#ourtime").val().length;//ความยาวเวลาที่เลือก
						var Userdate = $("#mydate").val();//วันที่เลือก
						var timelength = $("#ourtime").val().length;//เช็ค length เวลา
						var mydetail = $("#detail").val().length;
						;//เช็ต ค่าใน detail 
						var checkourtime = $("#ourtime").val();//เช็ต ค่าใน detail 
						var radio1 = $('input[id=checkbox1_1]:checked').val();
						var radio2 = $('input[id=checkbox1_2]:checked').val();


						console.log(timecheckin);
						//////////////////////// คำนวนเวลา ///////////////////
						var test = null;
						var hourtimeNow = null; //ชั่วโมงปัจจุบัน
						var mintimeNow = null; //นาทีปัจจุบัน
						var hourmytime = null; //ชั่วโมงที่เลือก
						var minmytime = null; //นาทีที่เลือก
						var hourfull = null; //ชั่วโมงที่เลือก-ชั่วโมงปัจจุบัน
						var minfull = null; //นาทีที่เลือก-นาทีปัจจุบัน
						var fulltimecheck = null; //ชั่วโมง + นาที
						var fullchecktimenow = null;
						if (mytimelength == '4') { //เวลาที่เลือกก่อนเที่ยง = 4 
							hourmytime = mytime.substring(0, 1);
							minmytime = mytime.substring(2, 4);
						} else if (mytimelength == '5') {//เวลาที่เลือกหลังเที่ยง = 5
							hourmytime = mytime.substring(0, 2);
							minmytime = mytime.substring(3, 5);
						}
						if (timeNowlength == '4') { // เวลาปัจจุบันก่อนเที่ยง =4
							hourtimeNow = timeNow.substring(0, 1);
							mintimeNow = timeNow.substring(2, 4);
						} else if (timeNowlength == '5') { //เวลาปัจจุบันหลังเที่ยง  =5
							hourtimeNow = timeNow.substring(0, 2);
							mintimeNow = timeNow.substring(3, 5);
						}
						hourfull = ((hourmytime - hourtimeNow) * 60); //ลบชั่วโมง * 60  
						minfull = (minmytime - mintimeNow); // ลบนาที
						fulltimecheck = (hourfull + minfull); // ชั่วโมง+นาที  (คำนวณเวลาที่เลือกกับเวลาปัจจุบัน)
						fullchecktimenow = ((hourmytime * 60) + minmytime); //คำนวณเวลาที่เลือก

						////////////// คำนวณเวลาปัจจุบันกับเวลาcheck-in ล่าสุด////////////
						var hourtimelast = null;
						var mintimelast = null;
						var calcheckin = null; // คำนวณเวลาแcheck-in ล่าสุด
						var calcheckout = null; // คำนวณเวลา check-out 
						if (timecheckinlength == '4') { // เวลา Check-in ล่าสุด =4
							hourtimelast = timecheckin.substring(0, 1);
							mintimelast = timecheckin.substring(2, 4);
						} else if (timecheckinlength == '5') { //เวลา check-inล่าสุด  = 5
							hourtimelast = timecheckin.substring(0, 2);
							mintimelast = timecheckin.substring(3, 5);
						}
						calcheckin = ((hourtimelast * 60) + mintimelast); // คำนวณเวลา check-in ล่าสุด
						calcheckout = ((hourtimeNow * 60) + mintimeNow); // คำนวณเวลาปัจจุบัน

						//////////////////////// คำนวน วันที่ ///////////////////
						var checkfulldate = null; // คำนวณวันที่เลือกกับวันที่ปัจจุบัน
						var checkindatelast = null; // คำนวณวันที่ปัจจุบันกับวันที่Check-in
						var d = new Date();
						var thisDateNow = d.getDate()
								+ ((d.getMonth() + 1) * 30)
								+ (d.getFullYear() * 360);
						var res = Userdate.split("-");
						var selectedDate = parseInt(res[0])
								+ parseInt(res[1] * 30)
								+ parseInt(res[2] * 360);
						var res1 = datecheckin.split("-");
						var Datecheckinlast = parseInt(res1[0])
								+ parseInt(res1[1] * 30)
								+ parseInt(res1[2] * 360);
						checkfulldate = (thisDateNow - selectedDate); //ไว้เช็คเวลาcheck-outห้ามเกิน 24 ชั่วโมง
						checkindatelast = (Datecheckinlast - thisDateNow); //คำนวณวันที่ปัจจุบันกับวันที่Check-in
						/////////////////////////////////////////// เช็คเวลา Check-in //////////////////////////////////
						if (radio1 > 0) {
							if (fulltimecheck > 0 || Userdate > fulldate) { // เวลาที่เลือก > เวลาปัจจุบัน หรือ วันที่เลือก > วันปัจจุบัน 
								swal("Here's a message!",
										"ไม่สามารถลงเวลาล่วงหน้าได้") // (กรณี check-in ล่วงหน้า)
								document.getElementById("checktime").type = "button";

							} else if (Userdate < fulldate) { //วันที่ที่เลือกน้อยกว่าวันที่ปัจจุบัน 
								swal("Here's a message!",
										"Can't Check-In In Last.") // (กรณี check-in ย้อนหลัง)
								document.getElementById("checktime").type = "button";
							} else if (calcheckout != fullchecktimenow
									&& mydetail < 10) { ///////////////////////////// check-in Detail///////////
								swal("Here's a message!",
										"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") // (กรณี check-in ย้อนหลังใส่เหตุผล)
								document.getElementById("checktime").type = "button";
							} else {
								document.getElementById("checktime").type = "submit";
							}
						}
						///////////////////////////////////////// เช็คเวลา check-out ////////////////////////////////////
						if (radio2 > 0) {
							if (checkfulldate == 0) {// วันปัจจุบัน 
								if (fulltimecheck > 0) {
									swal("Here's a message!",
											"Can't Check-out In Future.") // (กรณี check-out ล่วงหน้า) เช็คจากเวลาที่เลือกกับเวลาปัจจุบัน
									document.getElementById("checktime").type = "button";

								} else if ((calcheckout != fullchecktimenow && mydetail < 10)) {
									swal("Here's a message!",
											"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") //check-out ย้อนหลัง 
									document.getElementById("checktime").type = "button";
								} else {
									document.getElementById("checktime").type = "submit";
								}// เหลือ (กรณี Check-out ไม่มี Chec-in , Status = N/A )

							} else if (selectedDate > thisDateNow) { // วันที่เลือก มากกว่าวันที่ปัจจุบัน   
								swal("Here's a message!",
										"Can't Check-out In Future.") // (กรณี check-outล่วงหน้า) เช็คจากวันที่ ที่เลือก กับวันที่ ปัจจุบัน
								document.getElementById("checktime").type = "button";

							} else if (checkfulldate == 1) { // (กรณีCheck-out เลือกเวลาย้อนหลังเกิน24ชั่วโมงจากเวลาปัจจุบัน)
								if (fulltimecheck < 0) { //เวลาที่เลือกเทียบเวลาปัจจุบัน   (<0 คือเลือกเวลาย้อนหลังเกิน 24 ชั่วโมง)
									swal("Here's a message!",
											"Can't Check-Out Greater than 24 Hr.")
									document.getElementById("checktime").type = "button";
								} else if (fulltimecheck > 0 && mydetail < 10) { //เช็ค Detail (กรณีCheck-out เลือกเวลาย้อนหลังเกิน24ชั่วโมงจากเวลาปัจจุบัน)
									swal("Here's a message!",
											"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") // check-out ย้อนหลัง
									document.getElementById("checktime").type = "button";
								} else {
									document.getElementById("checktime").type = "submit";
								}

							} else if (checkfulldate > 1) { //(กรณี Check-out ย้อนหลังเกิน 1 วัน) 
								swal("Here's a message!",
										"Can't Check-out In Last.") //ย้อนหลังเกิน  1 วัน
								document.getElementById("checktime").type = "button";

							} else if (mydetail < 10) {
								swal("Here's a message!",
										"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") //
								document.getElementById("checktime").type = "button";
							} else {
								document.getElementById("checktime").type = "submit";
							}

						}

					});
	
	$('.numeric-input').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8) {
			return false;
		}
	});

	
	$("#map").hide();
	
	
</script>

<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
