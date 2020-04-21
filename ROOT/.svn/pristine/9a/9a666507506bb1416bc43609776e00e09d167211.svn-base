<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	min-height: 400px;
	max-height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	min-height: 100%;
	margin: 0;
	padding: 0;
}
</style>
<%-- ${checkinhourtime} --%>
<%-- ${checktimecheckin} --%>
<%-- ${mintime} ${hourstime} --%>
<%-- ${hoursmin} --%>
<%-- ${datenow} --%>
<%-- ${startmonths} --%>
<%-- ${datenow} --%>
<%-- ${department} --%>
<%-- ${departments} --%>
<%-- ${workall} --%>
<%-- ${logonUser} --%>
<%-- ${work} --%>
<%-- ${departmentList} --%>
<%-- ${position} --%>
<%-- ${logonUsers} --%>
<%-- ${startdate} --%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i
				class="fa fa-clock-o"></i> Check List</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<form action="check_map" method="post" name="form">
		<perm:permission object="checklist.viewall">
			<div class="portlet-body">
				<div class="form-group form-lg-line-input">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group form-lg-line-input">
						  <label class="col-md-1 control-label">Name :</label>
							<div class="col-md-4">
								<select class="form-control select2me" name="user.roletId">
									<option value="All" id="user.roletId">All</option>
									<optgroup label="Enable">
										<c:forEach var="user" items="${cubeUser}">

											<c:if test="${user.enable == 1 }">
												<c:if test="${logonUser == nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${logonUser != nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
									<optgroup label="Disable">
										<c:forEach var="user" items="${cubeUser}">

											<c:if test="${user.enable == 0 }">
												<c:if test="${logonUser == nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${logonUser != nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
								</select>
							</div>
							<label class="col-md-1 control-label">Date Range :</label>
							<div class="col-md-4">
                       			<div class="input-group input-large date-picker input-daterange" data-date="10/11/2012" data-date-format="dd-mm-yyyy">
                           			<input type="text" class="form-control cannot" name="Date-Start" id="F-date" value="${startdate}">
                            		<span class="input-group-addon"> to </span>
                                	<input type="text" class="form-control cannot" name="Date-End" id="E-date" value="${enddate}">
                                </div>
                        	</div>
                        	<label></label>
                        	<div class="col-md-2 text-center">
							<button type="submit" class="btn sbold blue" id="searchbutton"
								onclick="search()">
								<i class="fa fa-search"></i>&nbsp;Search
							</button>
							</div>
						</div>
					</div>
				</div>
				</div>
				
		</perm:permission>
					
<!-- 					<div class="col-sm-10"> -->
<!-- 						<div class="input-group input-large date-picker input-daterange" -->
<!-- 							data-date-format="dd-mm-yyyy"> -->

<!-- 							<input type="text" class="input-control cannot" name="Date-Start" -->
<%-- 								id="F-date" data-date-format="dd-mm-yyyy" value="${startdate}"> --%>

<!-- 							<span class="help-block">Pick Date Start</span> <span -->
<!-- 								class="input-group-addon"> to </span>  -->
<!-- 								<input type="text" -->
<!-- 								class="input-control cannot" name="Date-End" id="E-date" -->
<%-- 								data-date-format="dd-mm-yyyy" value="${enddate}"> <span --%>
<!-- 								class="help-block">Pick Date End</span> -->
<!-- 						</div> -->
<!-- 					</div> -->

</div>

</form>
<div class="portlet-body flip-scroll" style="text-align: center;">
	<!-- map -->
	<div id="map"></div>
</div>

</div>
<link href="../assets/global/plugins/ladda/ladda-themeless.min.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/ladda/spin.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/ladda/ladda.min.js"
	type="text/javascript"></script>

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

<link href="../assets/global/plugins/ladda/ladda-themeless.min.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/pages/scripts/ui-buttons.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.select2me').select2();
	});
</script>
<script>
	function search() {
		var DateStart = $("#F-date").val();
		var DateEnd = $("#E-date").val();
		if ((DateStart == "") || (DateEnd == "")) {
			document.getElementById("searchbutton").type = "button";
			swal("You miss Date", "Please Try It Agian :)", "error");
		} else {
			document.getElementById("searchbutton").type = "submit";
		}
	}
	function random_item(items)
	{
	  
	return items[Math.floor(Math.random()*items.length)];
	     
	}

</script>
<script>
          function initMap() {
              var map;
              var bounds = new google.maps.LatLngBounds();
              map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 13.727027, lng: 100.5260313},
                zoom: 15
              });
              
              var markers = [
              <c:forEach var="check" items="${CheckIn}">
              ["${check.name}",${check.lat},${check.lng},"${check.user}"],
              </c:forEach>
              []
              ];
              markers.pop();

              
              var infoWindowContent = [
            	  <c:forEach var="check" items="${CheckIn}">
            	  [],
            	  </c:forEach>
            	  []
                  ];
              infoWindowContent.pop();

              var markerDetail = [
            	  <c:forEach var="check" items="${CheckIn}">
            	  ["${check.name}","${check.date}","${check.time}","${check.check_type}","${check.role}"],
            	  </c:forEach>
            	  []
                  ];
              markerDetail.pop();

              // Add multiple markers to map
              var infoWindow = new google.maps.InfoWindow(), marker, i;

      		var pins = [
          		'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png',
          		'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
          		'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
          		'http://maps.google.com/mapfiles/ms/icons/ltblue-dot.png',
          		'http://maps.google.com/mapfiles/ms/icons/orange-dot.png',
          		'http://maps.google.com/mapfiles/ms/icons/pink-dot.png',
          		'http://maps.google.com/mapfiles/ms/icons/purple-dot.png',
          		'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
          		];

              for( i = 0; i < markers.length; i++ ) {
                  name = markerDetail[i][0];
                  date = markerDetail[i][1];
                  time = markerDetail[i][2];
                  check_type = markerDetail[i][3];
                  if(check_type=='1'){
                	  check_type = 'เข้างาน';
                      } else {
                    	  check_type = 'ออกงาน';
                          }
                  role = markerDetail[i][4];
                  marker_mouse_over = name+" "+role+"\n"+date+"\n"+check_type+" "+time.substr(0,5);
                  
                  pin = random_item(pins);
                  
                  var position = new google.maps.LatLng(markers[i][1], markers[i][2]);
                  bounds.extend(position);
                  marker = new google.maps.Marker({
                      position: position,
                      map: map,
                      title: marker_mouse_over,
                      icon: pin
                  });

                  infoWindowContent[i][0] = "<div>"
        				+ "<p>"+name+" "+role+"</p>"
        				+ "<p>"+date+"</p>"
        				+ "<p>"+check_type+" "+time.substr(0,5)+"</p>"
        				+ "</div>";
                  
                  // Add info window to marker    
                  infoWindowContent[i]
                  google.maps.event.addListener(marker, 'click', (function(marker, i) {
                      return function() {
                          infoWindow.setContent(infoWindowContent[i][0]);
                          infoWindow.open(map, marker);
                      }
                  })(marker, i));

                  // Center the map to fit all markers on the screen
                  map.fitBounds(bounds);
              }

              // Set zoom level
              var boundsListener = google.maps.event.addListener((map), 'bounds_changed', function(event) {
                  this.setZoom(13);
                  google.maps.event.removeListener(boundsListener);
              });
        }
    </script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=${GOOGLE_API_KEY}&callback=initMap"
	async defer></script>
<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
