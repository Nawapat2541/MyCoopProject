<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
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
<fmt:formatDate pattern="yyyy" value="${now}" var="year_now" />

<div class="portlet light bordered">

	<div class="portlet-title">
		<div class="caption">
			<i class="icon-layers font-blue-madison"></i> <span
				class="caption-subject font-blue-madison sbold uppercase">Leave
				application list</span>
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



		<form action="search_leave" method="post">
			<div class="portlet-body">
				<div class="row">
					<div class="form-group form-lg-line-input">
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
								: </label>
							<!-- type select -->
							<div class="col-xs-12 col-sm-5 col-md-4 col-lg-2"
								style="padding-left: 0px !important">
								<select class="form-control select2me" name="appr">
									<option value="3" id="All1"
										<c:if test="${ appr == 3 }"><c:out value="selected=selected"/></c:if>>All</option>
									<option value="0"
										<c:if test="${ appr == 0 }"><c:out value="selected=selected"/></c:if>>
										Waiting for approve</option>
									<option value="1"
										<c:if test="${ appr == 1 }"><c:out value="selected=selected"/></c:if>>Approved</option>

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
									data-date-format="dd-mm-yyyy">
									<c:if test="${startdate == null}">
										<input type="text" class="form-control cannot"
											name="startdate" id="startdate" data-date-format="dd-MM-yyyy"
											placeholder="Start date" size="13"
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
			</div>
			<br>
		</form>


		<%-- Start TABLE --%>

		<div class="portlet-body" style="text-align: center;">
			<table
				class="table table-bordered table-striped table-condensed table-hover">
				<thead class="flip-content">
					<tr>
						<th>
							<center>#</center>
						</th>
						<th>Leave Information</th>
						<!-- <th><center>Status</center></th> -->
						<!--  <th height="41"><center>Leave application form</center></th>  -->
						<th height="41"><center>Status Button</center></th>
						<th height="41"><center>Detail</center></th>
						<th height="41" width="50"><center>Print</center></th>
						<th height="41"><center>Delete</center></th>
					</tr>
				</thead>


				<form action="myleave_list" method="POST">
					<c:forEach var="leave" items="${leaveList}" varStatus="status">
						<tr>
							<td><a class="text-right"
								href="LeaveEdit?id=${leave.leave_id}">${leave.leave_id} </a></td>
							<td><span class="btn blue-hoki btn-outline"
								style="width: 180px"> <i class="fa fa-user"></i> <a
									class="text-right" href="LeaveEdit?id=${leave.leave_id}">${leave.user_id}</a>
							</span> <span class="btn purple btn-outline" style="width: 180px">
									<i class="fa fa-sign-out"></i> ${leave.leave_type_name}
							</span> <span class="btn blue btn-outline"> <i
									class="fa fa-calendar"></i> <fmt:formatDate
										value="${leave.start_date}" pattern="dd-MMM-yyyy"></fmt:formatDate>
									~ <fmt:formatDate value="${leave.end_date}"
										pattern="dd-MMM-yyyy"></fmt:formatDate>
							</span> <span class="btn default"> <i class="fa fa-bookmark"></i>
									<c:set var="amoutLeaveDay" value="${leave.no_day}" /> <fmt:formatNumber
										type="number" pattern="#" value="${amoutLeaveDay}" /> d <fmt:formatNumber
										type="number" pattern="#.##"
										value="${(amoutLeaveDay % 1) * 8}" /> h
							</span> <%--
										<br>
										<span class="btn btn-sm btn-outline">
										<i class="fa fa-sign-in"></i>
											<fmt:formatDate value="${leave.time_create}"
													pattern="dd-MM-yyyy HH:mm"></fmt:formatDate>
													
										</span>
											 --%></td>
							<!--  <td><c:if test="${leave.leave_status_id.toString() == '0'}">
									<div class="wait-${leave.leave_id}">
										<div class="item-status btn-sm btn-warning">Waiting for
											Approved</div>
									</div>


									<div hidden class="app-${leave.leave_id}">
										<div class="item-status btn-sm btn-info">Approved</div>
									</div>

									<div hidden class="app0-${leave.leave_id}">
										<div class="item-status btn-sm btn-danger">Reject</div>
									</div>

									<div hidden class="app1-${leave.leave_id}">
										<div class="item-status btn-sm btn-warning">Waiting for
											Approved</div>
									</div>


								</c:if> <c:if test="${leave.leave_status_id.toString() == '1'}">
									<div class="wait-${leave.leave_id}">
										<div class="item-status btn-sm btn-info">Approved</div>
									</div>

									<div hidden class="app1-${leave.leave_id}">
										<div class="item-status btn-sm btn-warning">Waiting for
											Approved</div>
									</div>

									<div hidden class="app0-${leave.leave_id}">
										<div class="item-status btn-sm btn-danger">Reject</div>
									</div>

									<div hidden class="app-${leave.leave_id}">
										<div class="item-status btn-sm btn-info">Approved</div>
									</div>

								</c:if> <c:if test="${leave.leave_status_id.toString() == '2'}">
									<div class="wait-${leave.leave_id}">
										<div class="item-status btn-sm btn-danger">Reject</div>
									</div>

									<div hidden class="app1-${leave.leave_id}">
										<div class="item-status btn-sm btn-warning">Waiting for
											Approved</div>
									</div>

									<div hidden class="app-${leave.leave_id}">
										<div class="item-status btn-sm btn-info">Approved</div>
									</div>

									<div hidden class="app0-${leave.leave_id}">
										<div class="item-status btn-sm btn-danger">Reject</div>
									</div>

								</c:if></td> 
							<!--   <td><perm:permission object="leave.approve">
									<div class="btn-group">
										<button
											class="btn btn-circle green btn-outline btn-sm dropdown-toggle"
											type="button" data-toggle="dropdown" data-hover="dropdown">
											Actions <i class="fa fa-angle-down"></i>
										</button>
										<ul class="dropdown-menu pull-right" role="menu">
											<li><a class="text-right "
												onclick="ajaxLoad(${leave.leave_id})">Approve </a></li>
											<li><a class="text-right"
												href="LeaveEdit?id=${leave.leave_id}">Detail </a></li>
											<li><a class="text-right"
												href="leaveReport?leaveId=${leave.leave_id}">Print </a></li>
											<li class="divider"></li>
											<li><a class="text-right"
												onclick="delete_leave_id?leave_id=${leave.leave_id}"><font
													color="red">Delete </font></a></li>
										</ul>
									</div>
								</perm:permission></td> -->

							<td><perm:permission object="leave.approve">
									<div class="btn-group">
										<c:if test="${leave.leave_status_id.toString() == '0'}">

											<div class="wait-${leave.leave_id}">
												<button
													class="btn btn-circle yellow-crusta btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Waiting for Approving<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right "
														onclick="ajaxLoad(${leave.leave_id})"> Approve </a></li>
													<li><a class="text-right" title="reject_leave_id"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
												</ul>
											</div>

											<div hidden class="app-${leave.leave_id}">
												<button
													class="btn btn-circle blue btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Approved<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right"
														title="reject_leave_id"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
													<li class="divider"></li>
												</ul>
											</div>

											<div hidden class="app0-${leave.leave_id}">
												<button
													class="btn btn-circle red-mint btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Reject<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right"
														onclick="ajaxLoad(${leave.leave_id});"> Approve </a></li>
												</ul>
											</div>

											<div hidden class="app1-${leave.leave_id}">
												<button
													class="btn btn-circle yellow-crusta btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Waiting for Approving<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right "
														onclick="ajaxLoad(${leave.leave_id});"> Approve </a></li>
													<li><a class="text-right"
														title="reject_leave_id"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
												</ul>
											</div>

										</c:if>



										<c:if test="${leave.leave_status_id.toString() == '1'}">

											<div class="wait-${leave.leave_id}">
												<button
													class="btn btn-circle blue btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Approved<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right"
														title="reject_leave_id"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
													<li class="divider"></li>
												</ul>
											</div>

											<div hidden class="app-${leave.leave_id}">
												<button
													class="btn btn-circle blue btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Approved<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right"
														title="reject_leave_id"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
													<li class="divider"></li>
												</ul>
											</div>

											<div hidden class="app0-${leave.leave_id}">
												<button
													class="btn btn-circle red-mint btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Reject<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right "
														onclick="ajaxLoad(${leave.leave_id});"> Approve </a></li>
												</ul>
											</div>

											<div hidden class="app1-${leave.leave_id}">
												<button
													class="btn btn-circle yellow-crusta btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Waiting for Approving<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right "
														onclick="ajaxLoad(${leave.leave_id});"> Approve </a></li>
													<li><a class="text-right"
														title="reject_leave_id"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
												</ul>
											</div>

										</c:if>



										<c:if test="${leave.leave_status_id.toString() == '2'}">

											<div class="wait-${leave.leave_id}">
												<button
													class="btn btn-circle red-mint btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Reject<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right "
														onclick="ajaxLoad(${leave.leave_id});"> Approve </a></li>
												</ul>
											</div>

											<div hidden class="app-${leave.leave_id}">
												<button
													class="btn btn-circle blue btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Approved<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right"
														title="reject_leave_id"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
													<li class="divider"></li>
												</ul>
											</div>

											<div hidden class="app0-${leave.leave_id}">
												<button
													class="btn btn-circle red-mint btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Reject<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right "
														onclick="ajaxLoad(${leave.leave_id});"> Approve </a></li>
												</ul>
											</div>

											<div hidden class="app1-${leave.leave_id}">
												<button
													class="btn btn-circle yellow-crusta btn-outline btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown" data-hover="dropdown">
													Waiting for Approving<i class="fa fa-angle-down"></i>
												</button>
												<ul class="dropdown-menu pull-right" role="menu">
													<li><a class="text-right"
														onclick="ajaxLoad(${leave.leave_id});"> Approve </a></li>
													<li><a class="text-right"
														title="reject_leave_id" data-toggle="modal"
														onclick="ajaxLoad2(${leave.leave_id});"> Reject </a></li>
												</ul>
											</div>

										</c:if>
									</div>
								</perm:permission></td>

							<td><perm:permission object="leave.approve">
									<div class="btn-group">
										<a class="btn  btn-sm sbold blue"
											href="LeaveEdit?id=${leave.leave_id}"
											title="information of leave" style="color: white;"> <i
											class="fa fa-clipboard"></i>
										</a>
									</div>
								</perm:permission></td>

							<td><perm:permission object="leave.approve">
									<div class="btn-group">
										<a class="btn btn-sm sbold blue"
											href="leaveReport?leaveId=${leave.leave_id}"
											title="leave information printing" style="color: black;">
											<i class="fa fa-print"></i>
										</a>

									</div>
								</perm:permission></td>

							<td><perm:permission object="leave.approve">
									<div class="btn-group">
										<a class="btn btn-sm sbold red" data-toggle="modal"
											href="#delete_leave" title="leave deleting"
											style="color: white;"> <i class="fa fa-trash-o"></i>
										</a>
									</div>

								</perm:permission></td>
						</tr>
						
						
						<div class="modal fade draggable-modal ui-draggable"
							id="delete_leave" tabindex="-1" role="basic" aria-hidden="true"
							style="display: none;">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header ui-draggable-handle">
										<button type="button" class="close" data-dismiss="modal">
										</button>
										<h4 class="modal-title">Are your sure?!</h4>
									</div>
									You will be deleting this id.
									<div class="modal-body"></div>
									<div class="modal-footer">
										<button type="button" class="btn dark btn-outline"
											data-dismiss="modal">Cancel</button>
										<a class="btn btn-sm sbold red"
											href="delete_leave_id?leave_id=${leave.leave_id}"
											title="leave deleting" style="color: white;"> Confirm </a>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog  -->
						</div>
						
					</c:forEach>
				</form>
			</table>




			<!-- <form action="leaveMonthReport" method="post">
					Month : <input type="text" name="month">
					Year : <input type="text" name="year">
					<button type="submit"></button>
							
</form>-->

			<br> <br>
			<div class="caption">
				<i class="icon-layers font-blue-madison"></i> <span
					class="caption-subject font-blue-madison sbold uppercase">Leave
					Month Report</span>
			</div>
			<br> <br>
			<form action="leaveMonthReport" method="post">
				<div class="row">
					<div class="col-lg-4">
						<select class="form-control select2me" name="month">
							<option value='01' id='01'
								<c:if test="${month == 1 }"><c:out value="selected=selected"/> </c:if>>January</option>
							<option value='02' id='02'
								<c:if test="${month == 2 }"><c:out value="selected=selected"/> </c:if>>February</option>
							<option value='03' id='03'
								<c:if test="${month == 3 }"><c:out value="selected=selected"/> </c:if>>March</option>
							<option value='04' id='04'
								<c:if test="${month == 4 }"><c:out value="selected=selected"/> </c:if>>April</option>
							<option value='05' id='05'
								<c:if test="${month == 5 }"><c:out value="selected=selected"/> </c:if>>May</option>
							<option value='06' id='06'
								<c:if test="${month == 6 }"><c:out value="selected=selected"/> </c:if>>June</option>
							<option value='07' id='07'
								<c:if test="${month == 7 }"><c:out value="selected=selected"/> </c:if>>July</option>
							<option value='08' id='08'
								<c:if test="${month == 8 }"><c:out value="selected=selected"/> </c:if>>August</option>
							<option value='09' id='09'
								<c:if test="${month == 9 }"><c:out value="selected=selected"/> </c:if>>September</option>
							<option value='10' id='10'
								<c:if test="${month == 10 }"><c:out value="selected=selected"/> </c:if>>October</option>
							<option value='11' id='11'
								<c:if test="${month == 11 }"><c:out value="selected=selected"/> </c:if>>November</option>
							<option value='12' id='12'
								<c:if test="${month == 12 }"><c:out value="selected=selected"/> </c:if>>December</option>
						</select>
					</div>
					<div class="col-lg-4">
						<select class="form-control select2me" name="year">
							<c:forEach begin="2009" end="${year_now}" var="select">
								<option value="${select}"
									<c:if test="${year == select }"><c:out value="selected=selected"/> </c:if>>${select}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-3">
						<button class="btn paddinglg sbold blue" type="submit">Print
							PDF</button>
					</div>
				</div>
				<%--  <a class="button"href="leaveMonthReport?month=${month}&year=2019">Print</a>  --%>
			</form>
		</div>

		<%-- End TABLE --%>

	</div>

</div>
</div>
</div>
<!-- end of main portlet body -->
</div>

</div>

<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
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

	function ajaxLoad(boo) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

			}
		};
		xhttp.open("GET", "Leave_inList?leave_id=" + boo, true);
		xhttp.send();

		$(".app-" + boo).show();
		$(".wait-" + boo).hide();
		$(".app0-" + boo).hide();
		$(".app1-" + boo).hide();
		
	}
	
	function ajaxLoad0(boo) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

			}
		};
		xhttp.open("GET", "Leave_inListLeaveStatusToWaiting?leave_id=" + boo, true);
		xhttp.send();

		$(".app1-" + boo).show();
		$(".wait-" + boo).hide();
		$(".app0-" + boo).hide();
		$(".app-" + boo).hide();
		
	}
	
	function ajaxLoad2(boo) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

			}
		};
		xhttp.open("GET", "Leave_inListLeaveStatusToReject?leave_id=" + boo, true);
		xhttp.send();

		$(".app0-" + boo).show();
		$(".wait-" + boo).hide();
		$(".app1-" + boo).hide();
		$(".app-" + boo).hide();
	}
	
	
</script>