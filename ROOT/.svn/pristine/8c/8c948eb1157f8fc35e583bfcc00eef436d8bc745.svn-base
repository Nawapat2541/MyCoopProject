<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />

<style>
@media only screen and (max-width: 1200px) {
	.paddingmd {
		padding-top: 15px !important;
	}
}

@media only screen and (max-width: 960px) {
	.hiddensm {
		visibility: hidden;
	}
}

@media only screen and (max-width: 1300px) {
	.paddinglg {
		margin-left: 50px !important;
	}
}

@media only screen and (max-width: 769px) {
	.centerxs {
		display: block;
		margin-left: auto;
		margin-right: auto;
		text-align: center !important;
	}
}

.item-head {
	width: width:100% !important;
}
</style>
<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="portlet light bordered">

	<div class="portlet-title">
		<div class="caption">
			<i class="icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Leave
				Approve</span>
		</div>
		<div class="actions">
			<a class="btn  btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
		<div class="actions right">
			<button type="button" class="btn green-jungle" id="addLeave"
				onclick="add()">
				<i class="fa fa-plus"></i>&nbsp;Add leave
			</button>
		</div>
	</div>
	<div class="portlet-body">
		<div class="tools">
			<a href="javascript:;" class="collapse" data-original-title=""
				title=""> </a> <a href="#portlet-config" data-toggle="modal"
				class="config" data-original-title="" title=""> </a> <a
				href="javascript:;" class="reload" data-original-title="" title="">
			</a> <a href="javascript:;" class="remove" data-original-title=""
				title=""> </a>
		</div>
		<!--  search bar in 1 line -->
		<form action="search_leave_approved" method="post">
			<div class="portlet-body">
				<div class="form-group form-lg-line-input">
					<div class="row">
						<div class="col-lg-10">
							<label class="col-xs-12 col-sm-1 col-md-1 col-lg-1 control-label"
								for="form_control_1" style="padding-right: 0px !important;">Staff
								:</label>
							<!-- staff label -->

							<!-- start staff select -->
							<div class="col-xs-12 col-sm-5 col-md-6  col-lg-3"
								style="padding-left: 0px !important;">
								<select class="form-control select2me" name="name1">
									<option value="All" id="All">All</option>
									<optgroup label="Enable">
										<c:forEach var="user" items="${userseq}">

											<c:if test="${user.enable == 1 }">
												<c:if test="${userSelect == nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
									<optgroup label="Disable">
										<c:forEach var="user" items="${userseq}">

											<c:if test="${user.enable == 0 }">
												<c:if test="${userSelect == nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
								</select>
							</div>
							<!-- end staff select -->
							<!-- type label -->
							<label class="col-xs-2 col-sm-1 col-md-1 col-lg-1 control-label"
								for="form_control_1" style="padding-right: 0px !important">Type
								:</label>
							<!-- type select -->
							<div class="col-xs-12 col-sm-5 col-md-4 col-lg-2"
								style="padding-left: 0px !important">
								<select class="form-control select2me" name="type"
									style="padding-left: 0px !important; padding-right: 0px">
									<option value="All" id="All1">All</option>
									<c:forEach var="leavetypelistChoice"
										items="${leavetypelistChoice}">
										<option value="${leavetypelistChoice.leaveTypeId}"
											id="${leavetypelistChoice.leaveTypeId}"
											<c:if test="${type == leavetypelistChoice.leaveTypeId }"><c:out value="selected=selected"/></c:if>>${leavetypelistChoice.leaveTypeName}</option>
									</c:forEach>
								</select>
							</div>
							<!--end  type select -->
							<label
								class="centersx col-xs-12 col-sm-1 col-md-1 col-lg-1 control-label paddingmd"
								style="padding-right: 0px !important;">Date :</label>
							<div
								class="centerxs col-sx-12 col-sm-6  col-md-7 col-lg-4 paddingmd"
								style="padding-left: 0px !important;">
								<div class="input-group input-large date-picker input-daterange"
									data-date-format="dd-mm-yyyy"
									style="padding-left: 0px !important">
									<c:if test="${startdate == null}">
										<input type="text" class="form-control cannot"
											name="startdate" id="startdate" size="13"
											data-date-format="dd-MM-yyyy" placeholder="Start date"
											value="<fmt:formatDate type="date" value="${now}" pattern="01-01-yyyy"  />">
									</c:if>
									<c:if test="${startdate != null}">
										<input type="text" class="form-control cannot"
											name="startdate" id="startdate" data-date-format="dd-MM-yyyy"
											placeholder="Start date" size="13"
											value="<fmt:formatDate type="date" value="${startdate}" pattern="dd-MM-yyyy"  />">
									</c:if>
									<span class="input-group-addon"> To </span>
									<c:if test="${enddate == null}">
										<input type="text" class="form-control cannot" name="enddate"
											id="enddate" data-date-format="dd-MM-yyyy"
											placeholder="End date" size="13"
											value="<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy"  />">
									</c:if>
									<c:if test="${enddate != null}">
										<input type="text" class="form-control cannot" name="enddate"
											id="enddate" data-date-format="dd-MM-yyyy"
											placeholder="End date" size="13"
											value="<fmt:formatDate type="date" value="${enddate}" pattern="dd-MM-yyyy"  />">
									</c:if>
								</div>
							</div>
						</div>
						<div align="center"
							class="col-xs-12 col-sm-5 col-md-3 col-lg-2 paddingmd">
							<button id="" type="submit" class="btn paddinglg sbold blue">
								<i class="fa fa-search"></i> Search
							</button>
						</div>
					</div>

				</div>
		<!--  Dashboard  -->
				<div id="dashboard">
					<div class="row">
						<div class="col-md-12 col-lg-12">
						<!-- personal leave -->
							<div class="col-lg-4">
								<a class="dashboard-stat dashboard-stat-v2 blue-steel">
									<div class="visual">
										<i class="fa fa-photo"></i>
									</div>
									<div class="details">
										<form action="myleave_list" method="POST">
											<div class="number">
												<c:choose>
													<c:when test="${leave_1 != null}">
														<span id="leaveType1" data-counter="counterup" data-value="">${leave_1} </span>
														<span>/ ${quotaThisYear}</span>
														<div class="desc">${type_1}</div>
													</c:when>
													<c:when test="${leave_1 == null}">
														<span id="leaveType1" data-counter="counterup" data-value="">0 /
															${quotaThisYear}</span>
														<div class="desc">${type_1}</div>
													</c:when>
												</c:choose>
											</div>
										</form>
									</div>

								</a>
							</div>
							<!-- sick leave -->
							<div class="col-md-4">
								<a class="dashboard-stat dashboard-stat-v2 red">
									<div class="visual">
										<i class="fa fa-ambulance"></i>
									</div>
									<div class="details">

										<form action="myleave_list" method="POST">
											<div class="number">
												<c:choose>
													<c:when test="${leave_3 != null}">
														<span id="leaveType3" data-counter="counterup" data-value="">${leave_3}</span>
														<div class="desc">${type_3}</div>
													</c:when>
													<c:when test="${leave_3 == null}">
														<span id="leaveType3" data-counter="counterup" data-value="">0</span>
														<div class="desc">${type_3}</div>
													</c:when>
												</c:choose>
											</div>
										</form>

									</div>

								</a>
							</div>
							<!-- leave quota from last year -->
							<div class="col-lg-4">
								<a class="dashboard-stat dashboard-stat-v2 purple">
									<div class="visual">
										<i class="fa fa-credit-card"></i>
									</div>
									<div class="details">
										<form action="myleave_list" method="POST">
											<div class="number">
												<c:choose>
													<c:when test="${leave_1 != null}">
														<span id="leaveType6" data-counter="counterup" data-value="sad">${leave_6}
															/ ${quotaLastYear}</span>
														<div class="desc">${type_6}</div>
													</c:when>
													<c:when test="${leave_1 == null}">
														<span id="leaveType6" data-counter="counterup" data-value="">0 /
															${quotaLastYear}</span>
														<div class="desc">${type_6}</div>
													</c:when>
												</c:choose>
											</div>
										</form>
									</div>

								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<br>
		</form>






		<!-- start content portlet -->

		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption caption-md">
					<i class="icon-bar-chart theme-font hide"></i> <span
						class="caption-subject font-blue-madison bold uppercase">Leave
						Lists</span> <span class="caption-helper"></span>
				</div>
				<div class="inputs">
					<div class="portlet-input input-inline input-small ">
						<div class="input-icon right"></div>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<div data-always-visible="1" data-rail-visible1="0"
					data-handle-color="#D7DCE2">
					<div class="general-item-list">
						<c:forEach var="leave" items="${leave}" varStatus="status">
							<c:if test="${leave.leave_status_id.toString() == '0'}">
								<center>
									<div
										style="border: 1px solid lightblue; padding: 15px; margin: 10px; width: 90%;">
										<div class="row">
											<div align="center"
												class="col-xs-12 col-sm-3 col-md-3 col-lg-2">
												<div class="row centerxs">
													<div align="center"
														class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<c:choose>
															<c:when test="${leave.path == null}">
																<img style="padding: 2px;" width="60px" height="60px"
																	style="background-color: #555;">
															</c:when>
															<c:otherwise>
																<img width="60px" height="60px"
																	style="object-fit: cover; margin: 3px;"
																	src="${leave.path}">
															</c:otherwise>
														</c:choose>
														<div align="center"
															class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
															<a title="ID: ${leave.leave_id}"
																href="LeaveEdit?id=${leave.leave_id}"
																class="item-name primary-link"> <font
																color="#105AB0" size="4">${leave.user_id} </font></a>
														</div>
													</div>
												</div>
											</div>

											<div class="centerxs col-xs-12 col-sm-6 col-md-5 col-lg-6">
												<div style="text-align: left;"
													class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<font size="3" class="open-sans" color="#366FB1">
														<b>${leave.leave_type_name} &nbsp;&nbsp;&nbsp;&nbsp; <c:set
															var="amoutLeaveDay" value="${leave.no_day}" /> 
															<span hidden id="amountLeaveType-${leave.leave_id}"> ${leave.leave_type_id} </span>
															<span id="amountLeave-${leave.leave_id}" > <fmt:formatNumber
															type="number" pattern="#.##" value="${amoutLeaveDay}" /></span>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;วัน</b>
													</font>
												</div>
												
												<div style="text-align: left;"
													class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<font size="3" class="open-sans" color="#366FB1"><fmt:formatDate
															value="${leave.start_date}" pattern="dd-MMM-yyyy"></fmt:formatDate>
														&nbsp; ~&nbsp; &nbsp; <fmt:formatDate
															value="${leave.end_date}" pattern="dd-MMM-yyyy"></fmt:formatDate></font>
												</div>
												<div style="text-align: left;"
												class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<font size="3" class="open-sans" color="#366FB1">Submit date : <fmt:formatDate value="${leave.time_create}"
													pattern="dd-MM-yyyy HH:mm"></fmt:formatDate></font>
											</div>

												<div style="text-align: left;"
													class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p>
														<font size="3" class="open-sans" color="#366FB1">
															${leave.description}</font>
													</p>
												</div>
											</div>
											<div class="col-xs-12 col-sm-2 col-md-4 col-lg-4">
												<div class="row">
													<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
														<c:if test="${leave.leave_status_id.toString() == '0'}">

															<div class="wait-${leave.leave_id}">
																<span class="item-status"> <span
																	class="badge badge-empty badge-warning"></span> <a
																	class="centerxs hiddensm"> Waiting for approve </a>
																</span>
															</div>


															<div hidden class="app-${leave.leave_id}">
																<span class="item-status"> <span
																	class="badge badge-empty badge-success"></span> <a
																	class="centerxs hiddensm"> Approved </a>
																</span>
															</div>
														</c:if>

														<c:if test="${leave.leave_status_id.toString() == '1'}">
															<span class="item-status"> <span
																class="badge badge-empty badge-success"></span> <a
																class="centerxs hiddensm"> Approved </a>
															</span>

														</c:if>
														<c:if test="${leave.leave_status_id.toString() == '2'}">
															<span class="item-status"> <span
																class="badge badge-empty badge-danger"></span> <a
																class="centerxs hiddensm"> Reject </a>
															</span>
														</c:if>
													</div>
													<div class=" centerxs col-xs-5 col-sm-5 col-md-5 col-lg-5">
														<perm:permission object="leave.approve">
															<div class="btn-group">
																<button
																	class="btn btn-circle green btn-outline btn-sm dropdown-toggle"
																	type="button" data-toggle="dropdown"
																	data-hover="dropdown">
																	Actions <i class="fa fa-angle-down"></i>
																</button>
																<ul class="dropdown-menu pull-right" role="menu">
																	<li><a class="text-right "
																		onclick="ajaxLoad(${leave.leave_id})">Approve </a></li>
																	<li><a class="text-right"
																		href="LeaveEdit?id=${leave.leave_id}">Detail </a></li>
																	<li><a class="text-right"
																		href="leaveReport?leaveId=${leave.leave_id}">Print
																	</a></li>
																	<li class="divider"></li>
																	<li><a class="text-right"
																		href="delete_leave_id?leave_id=${leave.leave_id}"><font
																			color="red">Delete </font></a></li>
																</ul>
															</div>
														</perm:permission>
													</div>
												</div>
											</div>

											<!-- this below tag /div is main row -->
										</div>
										<!-- this below tag /div is css box -->
									</div>
								</center>
							</c:if>
						</c:forEach>

						<!-- end of portlet content -->
					</div>
				</div>
			</div>
		</div>
		<!-- end of main portlet body -->
	</div>

</div>



<script>
	$(document).ready(function() {

		var value = "${flag_search}";
		if (value == "1") {
			var user = "${userId}";
			var type = "${type}";
			if (type == "All") {
				type = "All1";
			}
			document.getElementById(user).selected = "true";
			document.getElementById(type).selected = "true";
		} else {

			$('.select2me').select2();
		}
	});
</script>
<script>
	function add() {
		document.location = "LeaveAdd";
	}
</script>
<script>
	$('.cannot').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8) {
			return false;
		}
	});
</script>
<script>
	function approved() {
		document.location = "leave_check_approve";
	}

	$('.selectall').click(function() {
		if ($(this).is(':checked')) {
			$('div input').attr('checked', true);
		} else {
			$('div input').attr('checked', false);
		}
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#select_all').on('click', function() {
			if (this.checked) {
				$('.checkboxall').each(function() {
					this.checked = true;
				});
			} else {
				$('.checkboxall').each(function() {
					this.checked = false;
				});
			}
		});

		$('.checkbox').on('click', function() {
			if ($('.checkbox:checked').length == $('.checkbox').length) {
				$('#select_all').prop('checked', true);
			} else {
				$('#select_all').prop('checked', false);
			}
		});
	});
	
	
	

	function ajaxLoad(boo) {
		leaveId=boo;
	 $.ajax({
         url: '${pageContext.request.contextPath}/Leave_inList',
         type: 'POST',
         data : {
        	"leave_id" : leaveId
         }, 
	 }).done(function(data) {
		 $('.wait-'+boo).hide();
		 $('.app-'+boo).show();
		 swal("Success!","Leave ID "+boo+" was approved")
		 
		}).fail(function() {
			swal("Fail to approve ID : "+boo)
		});
	}
		
			    
		//not use	    $("#dashboard").load( " #dashboard", {name1: "value", type: "All"});
		
		// update dashboard function 
				var amount = parseFloat(document.getElementById("amountLeave-"+boo).innerHTML);
				var amountLeaveType = parseInt(document.getElementById("amountLeaveType-"+boo).innerHTML);
				var leaveType1 = parseFloat(document.getElementById("leaveType1").innerHTML);
				var leaveType3 = parseFloat(document.getElementById("leaveType3").innerHTML);
				var leaveType6 = parseFloat(document.getElementById("leaveType6").innerHTML);
				var total ;
				if (amountLeaveType == 1) { 
					total = amount + leaveType1;
					document.getElementById("leaveType1").innerHTML = total;
				} else if (amountLeaveType == 3) {
					total = amount + leaveType3;
					document.getElementById("leaveType3").innerHTML = total;
				} else {
					total = amount + leaveType6;
					document.getElementById("leaveType6").innerHTML = total;
				}				
	
</script>