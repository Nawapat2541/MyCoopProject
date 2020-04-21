<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
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
	<form action="search-list" method="post" name="form">
		<perm:permission object="checklist.viewall">
				<div class="portlet-body">
					<div class="form-group form-lg-line-input">
						<label class="col-md-1 control-label">Name :</label>
						<div class="col-md-4">
								<select id="xxx" class="form-control select2me" name="user.roletId" style='width:50%;'>
									<option value="All" id="user.id">All</option>
									<optgroup label="Enable">
										<c:forEach var="user" items="${cubeUser}">
											<!--  edit nulll to null -->
											<c:if test="${user.enable == 1 }">
												<c:if test="${logonUser == null }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${logonUser != null }">
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
												<c:if test="${logonUser == null }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${logonUser != null }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
								</select>
							</div>
								<label  class="col-md-1 control-label">Date :</label>
								<div class="col-md-4">
									<div class="input-group input-large date-picker input-daterange"
										data-date-format="dd-mm-yyyy">
										<c:choose>
											<c:when test="${startmonths == null && startdate == null}">
												<input type="text" class="form-control cannot" name="Date-Start"
													id="F-date" data-date-format="dd-mm-yyyy" value="${datenow}">
											</c:when>
											<c:when test="${startdate == null && startdate == null}">
												<input type="text" class="form-control cannot" name="Date-Start"
													id="F-date" data-date-format="dd-mm-yyyy"
													value="${startmonths}">
											</c:when>
											<c:when test="${startdate != ''}">
												<input type="text" class="form-control cannot" name="Date-Start"
													id="F-date" data-date-format="dd-mm-yyyy" value="${startdate}">
											</c:when>
										</c:choose>
										 <span
											class="input-group-addon"> to </span>
										<c:choose>
											<c:when test="${enddate == null}">
												<input type="text" class="form-control cannot" name="Date-End"
													id="E-date" data-date-format="dd-mm-yyyy" value="${datenow}">
											</c:when>
											<c:when test="${enddate != ''}">
												<input type="text" class="form-control cannot" name="Date-End"
													id="E-date" data-date-format="dd-mm-yyyy" value="${enddate}">
											</c:when>
										</c:choose>
									</div>
								</div>
							<label></label>
							<div class="col-md-2 text-center">
								<button type="submit" class="btn sbold blue" id="searchbutton"
									onclick="search()">
									<i class="fa fa-search"></i>&nbsp;Search
								</button>
								<a id="hrefPdf" class="btn btn-circle btn-sm sbold blue"
						title="" style="color: white;"><i class="fa fa-print"></i></a>
							</div>
					</div>
					
				</div>
</perm:permission>
</form>



	 <div class="portlet-body flip-scroll" style="text-align: center;">
                                    <div class="portlet-title">
                                        <!--  <div class="caption">
                                            <i class="fa fa-history"></i> </div>-->
                                        <div class="tools">
                                            <a href="javascript:;" class="collapse"> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-striped table-condensed flip-content table-hover">
                                                <thead class="flip-content">
                                                    <tr>
                                                       	<th><center>User</center></th>
														<th><center>Type</center></th>
														<th><center>Work Time</center></th>
														<th class="hidden-xs"><center>Timestamp / IP</center></th>
														<th><center>Working</center></th>
														<th><center>Status</center></th>
														<perm:permission object="checklist.edit">
															<th><center>Edit</center></th>
														</perm:permission>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${logonUser != 'All'}">
													<c:forEach var="workhours" items="${work}" varStatus="status">
														<tr>
															<td>
																<div  class="btn blue-hoki default btn-outline" style="width:180px">
																<i class="fa fa-user"></i>
																${workhours.user_create}
																</div>
															</td>
															<td>
																<c:if
																	test="${workhours.work_hours_type.toString() == '1'}">
																	<div  class="btn green default btn-outline" >
																	<i class="fa fa-sign-in">เข้างาน</i>
																	</div>
																</c:if> <c:if test="${workhours.work_hours_type.toString() == '2'}">
																	<div  class="btn red default btn-outline" >
																	<i class="fa fa-sign-out">ออกงาน</i>
																	</div>
																</c:if>
															</td>
															<td align="left">
															<div  class="btn blue-hoki default btn-outline" >
															<i class="fa fa-calendar"></i>
															<fmt:formatDate
																	value="${workhours.work_hours_time_work}"
																	pattern=" dd-MMM-yyyy" />
															</div>		
																	<div  class="btn blue" >
																	<i class="fa fa-clock-o"></i>
																		<fmt:formatDate
																			value="${workhours.work_hours_time_work}"
																				pattern="HH:mm" />
																	</div>
																	
																	<c:if test="${workhours.description.length() > 0 }">
																	<div  class="btn-xs green warning btn-outline" title='${workhours.description}'>
																	<i class="fa fa-commenting"></i>
																	<c:choose>
																		<c:when test="${workhours.description.length() > 30 }">
																			${workhours.description.substring(0,30)} ...
																		</c:when>
																		<c:otherwise>
																			${workhours.description}
																		</c:otherwise>
																		</c:choose>
																	</div>
																	</c:if>
															</td>
		
															<td class="hidden-xs">
															<div  class="btn-xs grey" >
															<fmt:formatDate value="${workhours.time_create}"
																	pattern=" dd-MMM HH:mm" /> <br>
																	${workhours.ip_address}
															</div>	
																	</td>
															
															
															<td><c:if
																	test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour == stime.toString().substring(0, 1)}">
																	<c:choose>
																		<c:when
																			test="${workhours.work_hours_time_work.toString().substring(14, 16) <= stime.toString().substring(2, 4)}">
																			<div  class="btn-sm btn-success" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>
																		</c:when>
																		<c:otherwise>
																			<div  class="btn-sm btn-danger" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>
																		</c:otherwise>
																	</c:choose>
																</c:if> <c:if
																	test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour < stime.toString().substring(0, 1)}">
																			<div  class="btn-sm btn-success" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>
																</c:if> <c:if
																	test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour > stime.toString().substring(0, 1)}">
																			<div  class="btn-sm btn-danger" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>
																</c:if> <c:if test="${workhours.work_hours_type.toString() == '2'}">
																	<c:choose>
																		<c:when
																			test="${(workhours.hour == '0' || workhours.hour == '00') && (workhours.min == '0' || workhours.min == '00') }">
																			<div  class="btn-sm btn-primary" >
																				<i class="fa fa-clock-o"></i>
																				N\A
																			</div>
																		</c:when>
																		<c:when
																			test="${workhours.hour < '09' && workhours.min > '00'}">
																			<c:if test="${workhours.hour == '00'}">
																			<div class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>																					
																				${workhours.hour}:${workhours.min}
																					hrs.
																			</div>
																			</c:if>
																			<c:if test="${workhours.hour != '00'}">
																				<c:if test="${workhours.hour >= '04'}">
																			<div class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>																						
																					${workhours.hour-1}:${workhours.min}
																						hrs.
																				</div>
																				</c:if>
																				<c:if test="${workhours.hour < '04'}">
																			<div class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>																						${workhours.hour}:${workhours.min}
																						hrs.</div>
																				</c:if>
																			</c:if>
																		</c:when>
																		<c:when
																			test="${workhours.work_hours_type.toString() == '2' && workhours.hour >= '09' && workhours.min <= '59' }">
																			<div class="btn-sm btn-info" >
																				<i class="fa fa-clock-o"></i>	
																			${workhours.hour-1}:${workhours.min}
																				hrs.</div>
																		</c:when>
																	</c:choose>
																</c:if></td>
															
															<td>
														
															<c:choose>
																	<c:when
																	test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour <  stime.toString().substring(0, 1)}">
																			<div  class="btn-sm btn-success" >
																				<i class="fa fa-check"></i>
																				On Time
																			</div>
								
																	</c:when>
																	<c:when
																		test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour == stime.toString().substring(0, 1)}">
																		<!-- edit -->
																		<c:choose>
																			<c:when
																				test="${workhours.work_hours_time_work.toString().substring(14, 16) <= stime.toString().substring(2, 4)}">
																				<div  class="btn-sm btn-success" >
																					<i class="fa fa-check"></i>
																					On Time
																				</div>
																			</c:when>
																			<c:otherwise>
																			<div  class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>
																				Late
																			</div>
																			</c:otherwise>
																		</c:choose>
								
																	</c:when>
																	
																	<c:when
																		test="${workhours.work_hours_type.toString() == '1' && workhours.myhour > stime.toString().substring(0, 1) }">
																			<div  class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>
																				Late
																			</div>
								
																	</c:when>
																	<c:when test="${workhours.work_hours_type.toString() == '2'}">
																		<c:choose>
																			<c:when
																				test="${(workhours.hour == '0' || workhours.hour == '00') && (workhours.min == '0' || workhours.min == '00') }">
																			<div  class="btn-sm btn-primary" >
																				<i class="fa fa-user-secret"></i>
																				Wait For Approve
																			</div>
																			</c:when>
																			<c:when
																				test="${workhours.hour >= '09' && workhours.min >= '00'}">
																			<div  class="btn-sm btn-info" >
																				<i class="fa fa-check"></i>
																				Finished Work
																			</div>
																			</c:when>
																			<c:when
																				test="${workhours.hour < 09 && workhours.min <= '59'}">
																			<div  class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>
																				Unfinished Work
																			</div>
																			</c:when>
																		</c:choose>
																	</c:when>
																</c:choose></td>
																
															<perm:permission object="checklist.edit">
																<td><a class="btn btn-circle btn-sm sbold blue"
																	href="check_edit?Id=${workhours.work_hours_id}"
																	title="Edit" style="color: white;"><i
																		class="fa fa-pencil"></i></a></td>
															</perm:permission>
														</tr>
													</c:forEach>
												</c:if>
												<c:if test="${logonUser == 'All'}">
														<c:forEach var="workhours" items="${workAll}" varStatus="status">
															
															<c:forEach var="work2" items="${workallTime}">
																<c:if test="${workhours.user_create.toLowerCase().equals(work2.id.toLowerCase())}">
														 <tr>
															<td>
																<div  class="btn blue-hoki default btn-outline" style="width:180px">
																<i class="fa fa-user"></i>
																${workhours.user_create}
																</div>
															</td>
															<td><c:if
																	test="${workhours.work_hours_type.toString() == '1'}">
																	<div  class="btn green default btn-outline" >
																	<i class="fa fa-sign-in">เข้างาน</i>
																	</div>
																</c:if> <c:if test="${workhours.work_hours_type.toString() == '2'}">
																	<div  class="btn red default btn-outline" >
																	<i class="fa fa-sign-out">ออกงาน</i>
																	</div>
																</c:if></td>
															<td align='left'>
															<div  class="btn blue-hoki default btn-outline" >
															<i class="fa fa-calendar"></i>
															<fmt:formatDate
																	value="${workhours.work_hours_time_work}"
																	pattern=" dd-MMM-yyyy" />
															</div>		
																	<div  class="btn blue" >
																	<i class="fa fa-clock-o"></i>
																		<fmt:formatDate
																			value="${workhours.work_hours_time_work}"
																				pattern="HH:mm" />
																	</div>
																	
																	<c:if test="${workhours.description.length() > 0}">
																	<div  class="btn-sm grey warning btn-outline" title='${workhours.description}'>
																	<i class="fa fa-commenting"></i>
																	<c:choose>
																		<c:when test="${workhours.description.length() > 30 }">
																			${workhours.description.substring(0,30)} ...
																		</c:when>
																		<c:otherwise>
																			${workhours.description}
																		</c:otherwise>
																		</c:choose>
																	</div>
																	</c:if>
																	</td>

															<td class="hidden-xs">
															<div  class="btn-xs grey btn-outline" >
															<fmt:formatDate value="${workhours.time_create}"
																	pattern=" dd-MMM HH:mm" /> <br>
																	${workhours.ip_address}
															</div>	
																	</td>
															<td><c:if
																	test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour == work2.work_time_start.toString().substring(0, 1)}">
																	<c:choose>
																		<c:when
																			test="${workhours.work_hours_time_work.toString().substring(14, 16) <= work2.work_time_start.toString().substring(2, 4)}">
																			<div  class="btn-sm btn-success" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>
																		</c:when>
																		<c:otherwise>
																			<div  class="btn-sm btn-warning" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>																		</c:otherwise>
																	</c:choose>
																</c:if> <c:if
																	test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour < work2.work_time_start.toString().substring(0, 1)}">
																			<div  class="btn-sm btn-success" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>																</c:if> <c:if
																	test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour > work2.work_time_start.toString().substring(0, 1)}">
																			<div  class="btn-sm btn-warning" >
																				<i class="fa fa-clock-o"></i>
																				Check - In
																			</div>																	</c:if> <c:if test="${workhours.work_hours_type.toString() == '2'}">
																	<c:choose>
																		<c:when
																			test="${(workhours.hour == '0' || workhours.hour == '00') && (workhours.min == '0' || workhours.min == '00') }">
																			<div  class="btn-sm btn-primary" >
																				<i class="fa fa-user-secret"></i>
																				N\A
																			</div>
																		</c:when>
																		<c:when
																			test="${workhours.hour < '09' && workhours.min > '00'}">
																			<c:if test="${workhours.hour == '00'}">
																				<div class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>	
																				${workhours.hour}:${workhours.min}
																					hrs.</div>
																			</c:if>
																			<c:if test="${workhours.hour != '00'}">
																				<c:if test="${workhours.hour >= '04'}">
																				<div class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>																						${workhours.hour-1}:${workhours.min}
																						hrs.
																				</div>
																				</c:if>
																				<c:if test="${workhours.hour < '04'}">
																				<div class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>																				
																						${workhours.hour}:${workhours.min}
																						hrs.
																				</div>
																				</c:if>
																			</c:if>
																		</c:when>
																		<c:when
																			test="${workhours.work_hours_type.toString() == '2' && workhours.hour >= '09' && workhours.min <= '59' }">
																			<div  class="btn-sm btn-info" >
																				<i class="fa fa-clock-o"></i>																			
																			${workhours.hour-1}:${workhours.min}
																				hrs.
																			</div>
																		</c:when>
																	</c:choose>
																</c:if></td>
																
															<td>
															
															<c:choose>
															

																	<c:when
																		test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour < work2.work_time_start.toString().substring(0, 1)}">
																			<div  class="btn-sm btn-success" >
																				<i class="fa fa-check"></i>
																				On Time
																			</div>
								
																	</c:when>
																	<c:when
																		test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour == work2.work_time_start.toString().substring(0, 1)}">
																		<!-- edit -->
																		<c:choose>
																			<c:when
																				test="${workhours.work_hours_time_work.toString().substring(14, 16) <= work2.work_time_start.toString().substring(2, 4)}">
																			<div  class="btn-sm btn-success" >
																				<i class="fa fa-check"></i>
																				On Time
																			</div>
																			</c:when>
																			<c:otherwise>
																			<div  class="btn-sm btn-warning" >
																				<i class="fa fa-warning"></i>
																				Late
																			</div>
																			</c:otherwise>
																		</c:choose>
																	</c:when>

																
																	<c:when
																		test="${workhours.work_hours_type.toString() == '1' && workhours.myhour > work2.work_time_start.toString().substring(0, 1) }">
																			<div  class="btn-sm btn-warning" >
																				<i class="fa fa-warning"></i>
																				Late
																			</div>
								
																	</c:when>
															        
																	<c:when test="${workhours.work_hours_type.toString() == '2'}">
																		<c:choose>
																			<c:when
																				test="${(workhours.hour == '0' || workhours.hour == '00') && (workhours.min == '0' || workhours.min == '00') }">
																				<div  class="btn-sm btn-primary" >
																				<i class="fa fa-exclamation"></i>
																				Waiting for Appprove
																				</div>
																			</c:when>
																			<c:when
																				test="${workhours.hour >= '09' && workhours.min >= '00'}">
																				<div  class="btn-sm btn-info" >
																				<i class="fa fa-check"></i>
																					Finish Work
																				</div>
																			</c:when>
																			<c:when
																				test="${workhours.hour < 09 && workhours.min <= '59'}">
																				<div  class="btn-sm btn-danger" >
																				<i class="fa fa-fire"></i>
																					Unfinished Work
																				</div>
																			</c:when>
																		</c:choose>
																	</c:when>
																</c:choose></td>
																
															<perm:permission object="checklist.edit">
																<td><a class="btn btn-circle btn-sm sbold blue"
																	href="check_edit?Id=${workhours.work_hours_id}"
																	title="Edit" style="color: white;"><i
																		class="fa fa-pencil"></i></a></td>
															</perm:permission>
														</tr>
													  </c:if>
														</c:forEach>
													</c:forEach>
												</c:if>
                                               	 </tbody>
                                            </table>
                                        </div>
                                    </div>
	 </div>
	<div id="map"></div>

 </div>
<!--  startgooglemap-->

<script>
 function random_item(items)
	{
	  
	return items[Math.floor(Math.random()*items.length)];
	     
	}
          function initMap() {
              var map;
              var bounds = new google.maps.LatLngBounds();
              map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 13.727027, lng: 100.5260313},
                zoom: 15
              });
              
              var markers = [
              <c:forEach var="check" items="${work}">
              ["${check.u_name}", "${check.latitude}", "${check.longitude}","${check.u_rold}"],
              </c:forEach>[]
              ];
              markers.pop();

              
              var infoWindowContent = [
            	  <c:forEach var="check" items="${work}">[],
            	  </c:forEach>[]
                  ];
              infoWindowContent.pop();
              var markerDetail = [
            	  <c:forEach var="check" items="${work}">
            	  ["${check.u_name}","${check.date}","${check.time}","${check.check_type}","${check.u_rold}"],
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
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=${GOOGLE_API_KEY}&callback=initMap">
    </script> 


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
		var value = "${flag_search}";
		if (value == "1") {
			var user = "${logonUser}";
			//  alert(user);
			document.getElementById(user).selected = "true";
		} else {

			$('.select2me').select2();
		}

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
</script>
<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
		var x = document.getElementById("xxx").value;
		var start = document.getElementById("F-date").value;
		var end = document.getElementById("E-date").value;
		var t = document.getElementById("hrefPdf");
		t.setAttribute("href","checkListReport?user.id="+x+"&start="+start+"&end="+end);
		
	});
</script>
<script>
$(function(){
$("#xxx,#F-date,#E-date").on('change',function(){
	var x = document.getElementById("xxx").value;
	var start = document.getElementById("F-date").value;
	var end = document.getElementById("E-date").value;
	var t = document.getElementById("hrefPdf");
	t.setAttribute("href","checkListReport?user.id="+x+"&start="+start+"&end="+end);
})
});
</script>
