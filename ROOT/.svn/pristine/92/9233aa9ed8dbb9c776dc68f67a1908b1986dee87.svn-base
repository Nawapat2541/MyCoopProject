<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<%-- ${checkinhourtime} --%>
<%-- ${checktimecheckin} --%>
<%-- ${work} --%>
<%-- ${mintime} --%>
<%-- ${hoursmin} --%>
<%-- ${startmonths} --%>
<%-- ${datenow} --%>
<%-- ${department} --%>
<%-- ${departments} --%>
<%-- ${workall} --%>
<%-- ${logonUser} --%>
<%-- ${departmentList} --%>
<%-- ${position} --%>
<%-- ${checkradio} --%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i
				class="fa fa-clock-o"></i> Check List Approve</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div>
		<form action="search_approve" method="post" name="form">
			<div class="portlet-body">
				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label">Name :</label>
					<div class="col-md-4">
						<c:choose>
							<c:when test="${logonUser == null}">
								<select class="form-control select2me" name="user.roletId">
									<optgroup label="Enable">
										<c:forEach var="user" items="${cubeUser}">
											<c:if test="${user.enable == 1 }">
												<c:if test="${userSelect == null }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != null }">
													<option value="${userSelect}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
									<optgroup label="Disable">
										<c:forEach var="user" items="${cubeUser}">
											<c:if test="${user.enable == 0 }">
												<c:if test="${userSelect == nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${userSelect}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
								</select>
							</c:when>
							<c:when test="${logonUser != null}">
								<select class="form-control select2me" name="user.roletId">
									<optgroup label="Enable">
										<c:forEach var="user" items="${cubeUser}">
											<c:if test="${user.enable == 1 }">
												<c:if test="${userSelect == null }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != null }">
													<option value="${userSelect}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
									<optgroup label="Disable">
										<c:forEach var="user" items="${cubeUser}">
											<c:if test="${user.enable == 0 }">
												<c:if test="${userSelect == nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${userSelect}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
								</select>
							</c:when>
						</c:choose>
					</div>

					<label class="control-label col-md-2">Date Range :</label>
					<div class="col-md-4">
						<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy">
							<c:choose>
								<c:when test="${startmonths == null && startdate == null}">
									<input type="text" class="form-control cannot"
										name="Date-Start" id="F-date" data-date-format="dd-mm-yyyy"
										value="${datenow}">
								</c:when>
								<c:when test="${startdate == null && startdate == null}">
									<input type="text" class="form-control cannot"
										name="Date-Start" id="F-date" data-date-format="dd-mm-yyyy"
										value="${startmonths}">
								</c:when>
								<c:when test="${startdate != ''}">
									<input type="text" class="form-control cannot"
										name="Date-Start" id="F-date" data-date-format="dd-mm-yyyy"
										value="${startdate}">
								</c:when>
							</c:choose>
							<span class="input-group-addon"> to </span>
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
				</div>

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label">Select :</label>
					<div class="col-md-4">
						<div class="md-radio-inline">
							<div class="md-radio">
								<input type="radio" id="checkbox1_1" name="check" value="1"
									class="md-radiobtn test" onclick="check_select(this.value);">
								<label for="checkbox1_1" style="color: #32c5d2;"> <span></span>
									<span class="check"></span> <span class="box"></span> Check-IN
								</label>
							</div>
							<div class="md-radio">
								<input type="radio" id="checkbox1_2" name="check" value="2"
									class="md-radiobtn test" onclick="check_select(this.value);">
								<label for="checkbox1_2" style="color: red;"> <span></span>
									<span class="check" style="background: #ff2524;"></span> <span
									class="box"></span> Check-OUT
								</label>
							</div>
							<div class="md-radio">
								<input type="radio" id="checkbox1_3" name="check" value="3"
									class="md-radiobtn test" onclick="check_select(this.value);">
								<label for="checkbox1_3" style="color: black;"> <span></span>
									<span class="check" style="background: #ff2524;"></span> <span
									class="box"></span> ALL
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn sbold blue" id="back-btn"
							style="text-align: center;">
							<i class="fa fa-search"></i>&nbsp;Search
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<form action="save_approve" method="post" name="form">
		<div class="portlet-body flip-scroll" style="text-align: center;">
			<table
				class="table table-bordered table-striped table-condensed flip-content table-hover">
				<thead class="flip-content">
					<tr>
						<th><center>No</center></th>
						<th><center>Type</center></th>
						<th><center>Work Time</center></th>
						<th><center>User</center></th>
						<th><center>Time Create</center></th>
						<th><center>IP</center></th>
						<th style="text-align: center;" width="10%">Check-out</th>
						<th><center>Status</center></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${logonUser != 'All' && checkradio != null}">
							<c:forEach var="workhours" items="${workapprove}"
								varStatus="status">
								<tr>
									<td>${status.count}<input type="hidden" name="idcheckout"
										value="${workhours.work_hours_id}"><input
										type="hidden" name="checkradio" value="${checkradio}"></td>
									<td><c:if
											test="${workhours.work_hours_type.toString() == '1'}">
											<i class="font-green-jungle fa fa-sign-in">เข้างาน</i>
										</c:if> <c:if test="${workhours.work_hours_type.toString() == '2'}">
											<i class="fa fa-sign-out font-red">ออกงาน</i>
										</c:if></td>

									<td><fmt:formatDate
											value="${workhours.work_hours_time_work}"
											pattern=" dd-MMM-yyyy HH:mm" /> <input type="hidden"
										class="form-control cannot" name="time"
										data-date-format="dd-mm-yyyy "
										value="${workhours.work_hours_time_work}"></td>
									<td>${workhours.user_create}<input type="hidden"
										name="usercheckin" value="${workhours.user_create}"></td>
									<td><fmt:formatDate value="${workhours.time_create}"
											pattern=" dd-MMM-yyyy HH:mm" /></td>
									<td>${workhours.ip_address}</td>
									<td>
										<div class="input-icon">
											<input type="text" name="timecheckout"
												value="${workhours.myhour}:${workhours.mymin}"
												class="form-control timepicker timepicker-24">
										</div>
									</td>
									<td><input type="hidden" name="checktype"
										value="${workhours.work_hours_type}"> <c:choose>
											<c:when
												test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour < '09'}">
												<span class="label label-sm label-success"> OnTime </span>
											</c:when>
											<c:when
												test="${workhours.work_hours_type.toString() == '1' && workhours.myhour >= '09'}">
												<span class="label label-sm label-danger"> Late </span>
											</c:when>
											<c:when test="${workhours.work_hours_type.toString() == '2'}">
												<c:choose>
													<c:when test="${workhours.hour == '0'}">
														<span class="label label label-primary">Waiting for
															appprove</span>
													</c:when>
													<c:when
														test="${workhours.hour >= '09' && workhours.min >= '00'}">
														<span class="label label-sm btn default">Finish
															Work </span>
													</c:when>
													<c:when
														test="${workhours.hour < 09 && workhours.min <= '59'}">
														<span class="label label-sm label-danger">Unfinish
															work</span>
													</c:when>
												</c:choose>
											</c:when>
										</c:choose></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${logonUser == 'All' && checkradio != null}">
							<c:forEach var="workhours" items="${workall}" varStatus="status">
								<tr>
									<td>${status.count}<input type="hidden" name="idcheckout"
										value="${workhours.work_hours_id}"><input
										type="hidden" name="checkradio" value="${checkradio}"></td>
									<td><c:if
											test="${workhours.work_hours_type.toString() == '1'}">
											<i class="font-green-jungle fa fa-sign-in">เข้างาน</i>
										</c:if> <c:if test="${workhours.work_hours_type.toString() == '2'}">
											<i class="fa fa-sign-out font-red">ออกงาน</i>
										</c:if></td>

									<td><fmt:formatDate
											value="${workhours.work_hours_time_work}"
											pattern=" dd-MMM-yyyy HH:mm" /> <input type="hidden"
										class="form-control cannot" name="time"
										data-date-format="dd-mm-yyyy "
										value="${workhours.work_hours_time_work}"></td>
									<td>${workhours.user_create}<input type="hidden"
										name="usercheckin" value="${workhours.user_create}"></td>
									<td><fmt:formatDate value="${workhours.time_create}"
											pattern=" dd-MMM-yyyy HH:mm" /></td>
									<td>${workhours.ip_address}</td>
									<td>
										<div class="input-icon">
											<input type="text" name="timecheckout"
												value="${workhours.myhour}:${workhours.mymin}"
												class="form-control timepicker timepicker-24">
										</div>
									</td>
									<td>
									<input type="hidden" name="checktype"
										value="${workhours.work_hours_type}"><c:choose>
											<c:when
												test="${workhours.work_hours_type.toString() == '1'  && workhours.myhour < '09'}">
												<span class="label label-sm label-success"> OnTime </span>
											</c:when>
											<c:when
												test="${workhours.work_hours_type.toString() == '1' && workhours.myhour >= '09'}">
												<span class="label label-sm label-danger"> Late </span>
											</c:when>
											<c:when test="${workhours.work_hours_type.toString() == '2'}">
												<c:choose>
													<c:when test="${workhours.hour == '0'}">
														<span class="label label label-primary">Waiting for
															appprove</span>
													</c:when>
													<c:when
														test="${workhours.hour >= '09' && workhours.min >= '00'}">
														<span class="label label-sm btn default">Finish
															Work </span>
													</c:when>
													<c:when
														test="${workhours.hour < 09 && workhours.min <= '59'}">
														<span class="label label-sm label-danger">Unfinish
															work</span>
													</c:when>
												</c:choose>
											</c:when>
										</c:choose></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>

			<div class="row ">
				<div class="col-md-12" style="text-align: center;">
					<button type="submit" id="demo15" class="btn sbold blue "
						onclick="save();">
						<i class="fa fa-save"></i>&nbsp;Save
					</button>
					<button type="reset" class="btn red" onclick="back()">
						<i class="fa fa-close"></i>&nbsp;Cancel
					</button>
				</div>
			</div>
		</div>
	</form>
</div>

<script>
	function pageload() {
		var check = "${checkradio}".trim();
		if (check == 1) {
			checkbox1_1.checked = true;
		} else if (check == 2) {
			checkbox1_2.checked = true;
		} else if (check == 3) {
			checkbox1_3.checked = true;
		} else {
			checkbox1_1.checked = true;
		}
	}
	window.onload = pageload;
	/* 
	 function check_select(){
	
	 alert("AAAAAAAAAAAAAAAA");
	 } 
	 */
	 
	 function back() {
			document.location = "approve_list";
		}
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
<script>$(document).ready(function () {
    $('.select2me').select2();
});</script>