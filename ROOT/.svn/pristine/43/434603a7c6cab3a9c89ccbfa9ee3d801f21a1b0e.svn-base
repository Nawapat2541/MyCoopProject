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
     max-height: 100%;
     max-width: 60%;
     left:20%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	 min-height: 100%; 
        margin: 0;
        padding: 0;
}
</style>
<%-- ${beforeType} --%>
<%-- ${time} --%>
<%-- ${fulldate} --%>
<%-- ${datecheckin} --%>
<%-- ${timecheckin} --%>
<%-- ${beforeType} --%>

<c:forEach var="workhours" items="${work}">

	<div class="portlet light bordered">
		<div class="portlet-title">
			<div class="caption">
				<span class="caption-subject font-red sbold uppercase"><i
					class="fa fa-clock-o"></i> Check List Form</span>
				</div>
			<div class="actions">
				<a class="btn btn-circle btn-icon-only btn-default fullscreen"
					href="javascript:;" data-original-title="" title=""> </a>
			</div>
		</div>
			<div class="portlet-body" style="margin-bottom: 25%;">
				<form action="edit-check" class="form-horizontal" method="POST">

					<input type="hidden" id="lat" value="${workhours.latitude}">
					<input type="hidden" id="lng" value="${workhours.longitude}">
					<input type="hidden" value="${workhours.work_hours_id}" name="workhoursId">
					<div class="form-group form-lg-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Check
							:</label>
						<div class="col-md-9">
							<div class="md-radio-inline">
								<div class="md-radio">
									<input type="radio" id="checkbox1_1" name="work_hours_type"
											onclick="show_text(this.value);" class="md-radiobtn"
											<c:if test="${workhours.work_hours_type.toString() eq '1' }">checked </c:if>
											value="1"> <label for="checkbox1_1"
											style="color: #32c5d2;"> <span class="inc"></span> <span
											class="check" style="background: #32C5D2;"></span> <span
											class="box" style="border-color: #2F353B;"></span> Check-IN
									</label>
								</div>
								<div class="md-radio">
									<input type="radio" id="checkbox1_2" name="work_hours_type"
											onclick="show_text(this.value);" class="md-radiobtn"
											<c:if test="${workhours.work_hours_type.toString() eq '2' }">checked </c:if>
											value="2"> <label for="checkbox1_2"
											style="color: red;"> <span class="inc"></span> <span
											class="check" style="background: #ff2524;"></span> <span
											class="box" style="border-color: #2F353B;"></span> Check-OUT
									</label>
								</div>

							</div>
						</div>
					</div>

					<div class="form-group form-lg-line-input">
						<label class="control-label col-md-3">Date :</label>
						<div class="col-md-3">
							<input name="work_hours_date_work" type="text"
								onchange="datechenge()" 
								class="form-control  form-control-inline date-picker test" style="text-align: center;"
								value="<fmt:formatDate value="${workhours.work_hours_time_work}"
									type="date" pattern="dd-MM-yyyy" />"
								type="text" data-date-format="dd-mm-yyyy"
								onkeypress='return false'>
						</div>

					</div>

					<div class="form-group form-lg-line-input">
						<label class="control-label col-md-3">Time :</label>
						<div class="col-md-3">
							<div class="input-group">
								
								<input type="text" name="work_hours_time_work" id="ourtime"
									class="form-control  timepicker timepicker-24 test"
									value="<fmt:formatDate value="${workhours.work_hours_time_work}" 
									type="time" pattern="HH:mm" />"
									data-time-format="HH:mm" style="text-align: center;"
									onchange="timechenge()" onkeypress='return false'> 
									<span class="input-group-btn" >
									<button class="btn default" type="button"
										onclick="timechenge()">
										<i class="fa fa-clock-o"></i>
									</button>
								</span>
							</div>
						</div>
					</div>


					<div class="form-group form-lg-line-input">
						<label class="col-md-3 control-label" for="form_control_1"
							id="labeldetail">Detail :</label>
						<div class="col-md-3">
								<textarea class="form-control " name="description"
									rows="3" placeholder="กรุณาระบุเหตุผล"
									id="detail"></textarea>
								<div class="form-control-focus"></div>

						</div>
					</div>
					<!--  startgooglemap-->
					<div id="map">

						<script>
							// Note: This example requires that you consent to location sharing when
							// prompted by your browser. If you see the error "The Geolocation service
							// failed.", it means you probably did not give permission for the browser to
							// locate you.

	 var map, infoWindow,marker;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -34.397, lng: 150.644},
          zoom: 16
        });
        var lats = $('#lat').val();
        var lngs = $('#lng').val(); 
        var a ;
    	var b ;
    	var latEl =  document.querySelector( '.latitude' );
		var longEl = document.querySelector( '.longitude' );
		
        infoWindow = new google.maps.InfoWindow;
        marker = new google.maps.Marker;
        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat:  a = parseFloat(lats),
              lng:  b = parseFloat(lngs) 
            };  
         	x = pos.lat;
         	y = pos.lng;
          
          	marker.setPosition(pos),
			marker.setMap(map),
			marker.setDraggable(true);
            
            infoWindow.setContent('Current Position');
            infoWindow.open(map,marker);
            map.setCenter(pos);
            adddata();
            
            google.maps.event.addListener( marker, "dragend", function ( event ){
            	var lati, lngti, address;
            	console.log( 'i am dragged' );
            	lati = marker.getPosition().lat();
            	lngti = marker.getPosition().lng();
            	var geocoder = new google.maps.Geocoder();
            	geocoder.geocode( { latLng: marker.getPosition() }, function ( result, status ){
            		if ( 'OK' === status ) {  // This line can also be written like if ( status == google.maps.GeocoderStatus.OK ) {
        				address = result[0].formatted_address;
        				resultArray =  result[0].address_components;

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
        				console.log( 'Geocode was not successful for the following reason: ' + status );
        			}
            		if ( infoWindow ) {
        				infoWindow.close();
        			}
				
        			/**
        			 * Creates the info Window at the top of the marker
        			 */
        			infoWindow = new google.maps.InfoWindow({
        				content: address
        			});

        			infoWindow.open( map, marker );
            	});
            });
          
          }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
          }); 
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, map.getCenter());
        }
        
      }
      function adddata(){
          $(document).ready(function() {
            	
            	document.getElementById("latitude").value = x;
            	//console.log(x); 
            	
            	});
          
          $(document).ready(function() {
            	
            	document.getElementById("longitude").value = y;
            	//console.log(y); 
            	
            	});
          }
      
      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
        infoWindow.open(map);
      }
    </script>
    <script async defer
   	src="https://maps.googleapis.com/maps/api/js?key=${GOOGLE_API_KEY}&callback=initMap">
    </script>
	
					</div>
					
					
					<!--  stopgooglemap-->
					
					 <input type="hidden" class="latitude" name="latitude" id="latitude"> 
					 <input type="hidden" class="longitude" name="longitude" id="longitude">
					
					<div class="form-group form-md-line-input text-center"
						style="margin-top: 15%;">

						<button class="btn btn-primary blue" style="font-size: 200%"
							type="submit" id="" name="savebtn">
							<i class="fa fa-save"></i> Accept
						</button>

						<button type="reset" class="btn btn-primary red"
							style="font-size: 200%" onclick="back()">
							<i class="fa fa-close"></i> Cancel
						</button>
					</div>
				</form>
			</div>
		</div>
</c:forEach>

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
		document.location = "myleave_add";
	}

	function pageload() {
		var day = new Date();
		var mytime = day.getHours();
// 		if (mytime < 12) {
// 			checkbox1_1.checked = true;
// 			$("#detail").hide();
// 			$("#labeldetail").hide();

// 		} else {
// 			checkbox1_2.checked = true;
// 			$("#detail").hide();
// 			$("#labeldetail").hide();

// 		}
	}
	window.onload = pageload;
// 	$("#")
// 			.click(
// 					function() {
// 						var timecheckin = "${timecheckin}".trim();//เวลาเช็คอินล่าสุด
// 						var timecheckinlength = "${timecheckin}".trim().length;//ความยาวเวลาเช็คอินล่าสุด
// 						var datecheckin = "${datecheckin}".trim();//วันที่เช็คอินล่าสุด
// 						var fulldate = "${fulldate}".trim();//วันที่ปัจจุบัน
// 						var timeNow = "${time}".trim();//เวลาปัจจุบัน
// 						var timeNowlength = "${time}".trim().length;//ความยาวเวลาปัจจุบัน
// 						var mytime = $("#ourtime").val();//เวลาที่เลือก
// 						var mytimelength = $("#ourtime").val().length;//ความยาวเวลาที่เลือก
// 						var Userdate = $("#mydate").val();//วันที่เลือก
// 						var timelength = $("#ourtime").val().length;//เช็ค length เวลา
// 						var mydetail = $("#detail").val().length;
// 						;//เช็ต ค่าใน detail 
// 						var checkourtime = $("#ourtime").val();//เช็ต ค่าใน detail 
// 						var radio1 = $('input[id=checkbox1_1]:checked').val();
// 						var radio2 = $('input[id=checkbox1_2]:checked').val();

// 						console.log(timecheckin);
// 						//////////////////////// คำนวนเวลา ///////////////////
// 						var test = null;
// 						var hourtimeNow = null; //ชั่วโมงปัจจุบัน
// 						var mintimeNow = null; //นาทีปัจจุบัน
// 						var hourmytime = null; //ชั่วโมงที่เลือก
// 						var minmytime = null; //นาทีที่เลือก
// 						var hourfull = null; //ชั่วโมงที่เลือก-ชั่วโมงปัจจุบัน
// 						var minfull = null; //นาทีที่เลือก-นาทีปัจจุบัน
// 						var fulltimecheck = null; //ชั่วโมง + นาที
// 						var fullchecktimenow = null;
// 						if (mytimelength == '4') { //เวลาที่เลือกก่อนเที่ยง = 4 
// 							hourmytime = mytime.substring(0, 1);
// 							minmytime = mytime.substring(2, 4);
// 						} else if (mytimelength == '5') {//เวลาที่เลือกหลังเที่ยง = 5
// 							hourmytime = mytime.substring(0, 2);
// 							minmytime = mytime.substring(3, 5);
// 						}
// 						if (timeNowlength == '4') { // เวลาปัจจุบันก่อนเที่ยง =4
// 							hourtimeNow = timeNow.substring(0, 1);
// 							mintimeNow = timeNow.substring(2, 4);
// 						} else if (timeNowlength == '5') { //เวลาปัจจุบันหลังเที่ยง  =5
// 							hourtimeNow = timeNow.substring(0, 2);
// 							mintimeNow = timeNow.substring(3, 5);
// 						}
// 						hourfull = ((hourmytime - hourtimeNow) * 60); //ลบชั่วโมง * 60  
// 						minfull = (minmytime - mintimeNow); // ลบนาที
// 						fulltimecheck = (hourfull + minfull); // ชั่วโมง+นาที  (คำนวณเวลาที่เลือกกับเวลาปัจจุบัน)
// 						fullchecktimenow = ((hourmytime * 60) + minmytime); //คำนวณเวลาที่เลือก

// 						////////////// คำนวณเวลาปัจจุบันกับเวลาcheck-in ล่าสุด////////////
// 						var hourtimelast = null;
// 						var mintimelast = null;
// 						var calcheckin = null; // คำนวณเวลาแcheck-in ล่าสุด
// 						var calcheckout = null; // คำนวณเวลา check-out 
// 						if (timecheckinlength == '4') { // เวลา Check-in ล่าสุด =4
// 							hourtimelast = timecheckin.substring(0, 1);
// 							mintimelast = timecheckin.substring(2, 4);
// 						} else if (timecheckinlength == '5') { //เวลา check-inล่าสุด  = 5
// 							hourtimelast = timecheckin.substring(0, 2);
// 							mintimelast = timecheckin.substring(3, 5);
// 						}
// 						calcheckin = ((hourtimelast * 60) + mintimelast); // คำนวณเวลา check-in ล่าสุด
// 						calcheckout = ((hourtimeNow * 60) + mintimeNow); // คำนวณเวลาปัจจุบัน

// 						//////////////////////// คำนวน วันที่ ///////////////////
// 						var checkfulldate = null; // คำนวณวันที่เลือกกับวันที่ปัจจุบัน
// 						var checkindatelast = null; // คำนวณวันที่ปัจจุบันกับวันที่Check-in
// 						var d = new Date();
// 						var thisDateNow = d.getDate()
// 								+ ((d.getMonth() + 1) * 30)
// 								+ (d.getFullYear() * 360);
// 						var res = Userdate.split("-");
// 						var selectedDate = parseInt(res[0])
// 								+ parseInt(res[1] * 30)
// 								+ parseInt(res[2] * 360);
// 						var res1 = datecheckin.split("-");
// 						var Datecheckinlast = parseInt(res1[0])
// 								+ parseInt(res1[1] * 30)
// 								+ parseInt(res1[2] * 360);
// 						checkfulldate = (thisDateNow - selectedDate); //ไว้เช็คเวลาcheck-outห้ามเกิน 24 ชั่วโมง
// 						checkindatelast = (Datecheckinlast - thisDateNow); //คำนวณวันที่ปัจจุบันกับวันที่Check-in
// 						/////////////////////////////////////////// เช็คเวลา Check-in //////////////////////////////////
// 						if (radio1 > 0) {
// 							if (fulltimecheck > 0 || Userdate > fulldate) { // เวลาที่เลือก > เวลาปัจจุบัน หรือ วันที่เลือก > วันปัจจุบัน 
// 								swal("Here's a message!",
// 										"Can't Check-In In Future.") // (กรณี check-in ล่วงหน้า)
// 								document.getElementById("").type = "button";

// 							} else if (Userdate < fulldate) { //วันที่ที่เลือกน้อยกว่าวันที่ปัจจุบัน 
// 								swal("Here's a message!",
// 										"Can't Check-In In Last.") // (กรณี check-in ย้อนหลัง)
// 								document.getElementById("").type = "button";
// 							} else if (calcheckout != fullchecktimenow
// 									&& mydetail < 10) { ///////////////////////////// check-in Detail///////////
// 								swal("Here's a message!",
// 										"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") // (กรณี check-in ย้อนหลังใส่เหตุผล)
// 								document.getElementById("").type = "button";
// 							} else {
// 								document.getElementById("").type = "submit";
// 							}
// 						}
// 						///////////////////////////////////////// เช็คเวลา check-out ////////////////////////////////////
// 						if (radio2 > 0) {
// 							if (checkfulldate == 0) {// วันปัจจุบัน 
// 								if (fulltimecheck > 0) {
// 									swal("Here's a message!",
// 											"Can't Check-out In Future.") // (กรณี check-out ล่วงหน้า) เช็คจากเวลาที่เลือกกับเวลาปัจจุบัน
// 									document.getElementById("").type = "button";

// 								} else if ((calcheckout != fullchecktimenow && mydetail < 10)) {
// 									swal("Here's a message!",
// 											"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") //check-out ย้อนหลัง 
// 									document.getElementById("").type = "button";
// 								} else {
// 									document.getElementById("").type = "submit";
// 								}// เหลือ (กรณี Check-out ไม่มี Chec-in , Status = N/A )

// 							} else if (selectedDate > thisDateNow) { // วันที่เลือก มากกว่าวันที่ปัจจุบัน   
// 								swal("Here's a message!",
// 										"Can't Check-out In Future.") // (กรณี check-outล่วงหน้า) เช็คจากวันที่ ที่เลือก กับวันที่ ปัจจุบัน
// 								document.getElementById("").type = "button";

// 							} else if (checkfulldate == 1) { // (กรณีCheck-out เลือกเวลาย้อนหลังเกิน24ชั่วโมงจากเวลาปัจจุบัน)
// 								if (fulltimecheck < 0) { //เวลาที่เลือกเทียบเวลาปัจจุบัน   (<0 คือเลือกเวลาย้อนหลังเกิน 24 ชั่วโมง)
// 									swal("Here's a message!",
// 											"Can't Check-Out Greater than 24 Hr.")
// 									document.getElementById("").type = "button";
// 								} else if (fulltimecheck > 0 && mydetail < 10) { //เช็ค Detail (กรณีCheck-out เลือกเวลาย้อนหลังเกิน24ชั่วโมงจากเวลาปัจจุบัน)
// 									swal("Here's a message!",
// 											"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") // check-out ย้อนหลัง
// 									document.getElementById("").type = "button";
// 								} else {
// 									document.getElementById("").type = "submit";
// 								}

// 							} else if (checkfulldate > 1) { //(กรณี Check-out ย้อนหลังเกิน 1 วัน) 
// 								swal("Here's a message!",
// 										"Can't Check-out In Last.") //ย้อนหลังเกิน  1 วัน
// 								document.getElementById("").type = "button";

// 							} else if (mydetail < 10) {
// 								swal("Here's a message!",
// 										"กรุณาระบุเหตุผลการลงเวลาย้อนหลังมากกว่า 10 ตัวอักษร") //
// 								document.getElementById("").type = "button";
// 							} else {
// 								document.getElementById("").type = "submit";
// 							}

// 						}

// 					});
// 	$('.test').keydown(function(e) {
// 		// trap the return key being pressed
// 		if (e.keyCode === 13 || e.keyCode === 8) {
// 			return false;
// 		}
// 	});
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
