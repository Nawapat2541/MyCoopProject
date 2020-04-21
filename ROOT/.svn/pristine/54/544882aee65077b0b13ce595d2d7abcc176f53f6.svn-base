<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>
<%-- <script
	src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.js"></script> --%>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<c:forEach var="leave" items="${leaveList}">

	<c:if test="${leave.leave_id  == param.Id}">
		${leave.apprUserId}
		<div class="portlet light bordered">
			<div class="portlet-title">

				<div class="caption">
					<i class="fa fa-paperclip font-red"></i> <span
						class="caption-subject font-red sbold uppercase">Leave
						application form</span>
				</div>
				<div class="actions">
					<a class="btn  btn-icon-only btn-default fullscreen"
						href="javascript:;" data-original-title="" title=""> </a>
				</div>
			</div>


			<div class="portlet-body">
				<form action="leave_perform_form" method="POST">
					<input type="hidden" value="${leave.leave_id}" name="leaveId">
					<div class="portlet-body">
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">Date
								:</label>
							<div class="col-md-2">
								<input name="timeCreate" type="text" class="form-control"
									value="<fmt:formatDate value="${leave.time_create}"
									type="date" pattern="dd-MM-yyyy" />">
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">Applicant
								:</label>
							<div class="col-md-4 ">
								<select class="bs-select form-control select2me" name="name">
									<optgroup label="Enable">
										<c:forEach var="user" items="${userseq}">

											<c:if test="${user.enable == 1 }">
												<c:if test="${userSelect == nulll }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${user.id eq onlineUser.id}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${userSelect}" id="${user.id}"
														<c:if test="${user.id eq userSelect}"><c:out value="selected=selected"/></c:if>>${user.department_id}
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
														<c:if test="${user.id eq onlineUser.id}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${userSelect}" id="${user.id}"
														<c:if test="${user.id eq userSelect}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
								</select>
							</div>
						</div>
						<c:if test="${leave.leave_id  == param.Id}">
							<div class="form-group form-md-line-input">
								<label class="col-md-3 control-label" for="form_control_1">Type
									of leave :</label>
								<div class="col-md-9">
									<div class="md-radio-inline">
										<c:forEach var="type" items="${leavetypeList}"
											varStatus="Count">
											<div class="md-radio">
												<input type="radio" id="${type.getLeaveTypeId()}"
													name="leaveTypeId" class="md-radiobtn"
													value="${type.getLeaveTypeId()}" required
													<c:if test="${type.leaveTypeId eq leave.leave_type_id.toString()}"> checked </c:if>>
												<label for="${type.getLeaveTypeId()}"
													style="color: #2F353B;">${type.leaveTypeName} <span
													class="inc"></span> <span class="check"
													style="background: #32C5D2;"></span> <span class="box"
													style="border-color: #2F353B;"></span>
												</label>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:if>
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">Duration
								:</label>
							<div class="col-md-6">
								<div class="input-group input-large date-picker input-daterange"
									data-date-format="dd-mm-yyyy">
									<input name="startDate" type="text" class="form-control cannot"
										id="date_from" placeholder="Start date" required
										value="<fmt:formatDate value="${leave.start_date}" type="date" 
										pattern="dd-MM-yyyy"/>">
									<span class="input-group-addon">To :</span> <input
										name="endDate" type="text" required id="date_to"
										value="<fmt:formatDate value="${leave.end_date}" type="date" 
										pattern="dd-MM-yyyy"/>"
										placeholder="End date" class="form-control cannot">
								</div>
							</div>
						</div>
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">Amount
								:</label>
							<div class="col-md-3">
								<input type="text" required class="form-control" id="Amount"
									min="0.5" max="1000"
									onkeypress="return fun_AllowOnlyAmountAndDot(this.id);"
									name="noDay" readonly maxlength="4">
							</div>
							<label style="margin-left: 0.5cm;">Day</label>
						</div>
						<c:if test="${leave.leave_id  == param.Id}">
							<div class="form-group form-md-line-input">
								<label class="col-md-3 control-label" for="form_control_1">A
									half day leave :</label>
								<div class="form-group form-md-radios has-sucess">
									<div class="md-radio-inline">
										<div class="md-radio">
											<input type="radio" id="radio14" name="halfDay"
												onclick="choie_half()" class="md-radiobtn"
												<c:if test="${leave.half_day.toString() eq '1' }">checked <c:set var="check_radio" value="1" />  </c:if>
												value="1"> <label for="radio14"
												style="color: #2F353B;"> <span class="inc"></span> <span
												class="check" style="background: #32C5D2;"></span> <span
												class="box" style="border-color: #2F353B;"></span> Morning
											</label>
										</div>
										<div class="md-radio">
											<input type="radio" id="radio15" name="halfDay"
												onclick="choie_half()" class="md-radiobtn"
												<c:if test="${leave.half_day.toString() eq '2' }"> <c:set var="check_radio" value="1" />
                                          checked
                                   </c:if>
												value="2"> <label for="radio15"
												style="color: #2F353B;"> <span class="inc"></span> <span
												class="check" style="background: #32C5D2;"></span> <span
												class="box" style="border-color: #2F353B;"></span> Afternoon
											</label>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${leave.leave_id  == param.Id}">
							<div class="form-group form-md-line-input">
								<label class="col-md-3 control-label" for="form_control_1">Description
									:</label>
								<div class="col-md-4">
									<textarea style="word-break: break-all; white-space: normal;"
										class="form-control" rows="6" name="description"
										maxlength="1024" value="leave.description">${leave.description}</textarea>
									<div class="form-control-focus"></div>
									<span class="help-block">Please fill the information</span>
								</div>
							</div>
						</c:if>
						<div class="portlet-body test">
							<div style="margin-top: 2cm;"></div>
							<div class="form-group form-md-line-input">

								<label class="col-md-3 control-label" for="form_control_1">Approvers
									:</label>
								<div class="col-md-4">
									<c:choose>
										<c:when test="${id != null}">
											<select class="bs-select form-control select2me" name="apprUserId"
												id="appUserId">
												<c:forEach var="user" items="${approve}">
													<option value="${user.appr_user_id}"
														<c:if test="${user.appr_user_id eq leave.apprUserId}">  selected  </c:if>>${user.appr_user_id}</option>
												</c:forEach>
												<!-- the value of user.id (not must be change value) -->
												<c:forEach var="userList" items="${userList}">
													<option value="${userList.id}"
														<c:if test="${userList.id eq leave.apprUserId}"></c:if>>${userList.id}</option>
												</c:forEach>
											</select>
										</c:when>
				
										<%-- <c:when test="${userId != null}">
				<select class="bs-select form-control" name="apprUserId"
					id="apprUserId">
					<c:forEach var="manager" items="${leader}">
						<option value="${manager.managerId}"
							<c:if test="${user.managerId eq manager.manager_id}"> selected </c:if>>${manager.manager_id}</option>
						<c:forEach var="userList" items="${userList}">
							<option value="${userList.id}"
								<c:if test="${userList.id eq leave.appr_user_id}"></c:if>>${userList.name}</option>
						</c:forEach>
					</c:forEach>
				</select>
			</c:when> --%>
									</c:choose>
								</div>
							</div>
							<div class="form-group form-md-line-input">
								<label class="col-md-3 control-label" for="form_control_1">Status
									:</label>
								<div class="col-md-4">
									<c:if test="${flag.toString() eq '1' }">
										<select class="bs-select form-control" name="leaveStatusId"
											id="leaveStatusId">
											<option name="leaveStatusId" value="0"
												<c:if test="${leave.leave_status_id.toString() eq '0'}"> selected </c:if>>Waiting
												for approve</option>
											<option name="leaveStatusId" value="1"
												<c:if test="${leave.leave_status_id.toString() eq '1'}"> selected </c:if>>Approved</option>
											<option name="leaveStatusId" value="2"
												<c:if test="${leave.leave_status_id.toString() eq '2'}"> selected </c:if>>Reject</option>

										</select>
									</c:if>

									<c:if test="${flag.toString() eq '0' }">

										<c:if test="${leave.leave_status_id.toString() eq '0'}">


											<option name="leaveStatusId" value="0"
												<c:out value="selected=selected"/>>Waiting for
												approve</option>
										</c:if>

										<c:if test="${leave.leave_status_id.toString() eq '1'}">
											<option name="leaveStatusId" value="1"
												<c:out value="selected=selected"/>>Approved</option>
										</c:if>

										<c:if test="${leave.leave_status_id.toString() eq '2'}">
											<option name="leaveStatusId" value="2"
												<c:out value="selected=selected"/>>Reject</option>
										</c:if>

									</c:if>
								</div>

							</div>
							<c:forEach var="leave" items="${leaveList}">
								<c:if test="${leave.leave_id  == param.Id}">
									<div class="form-group form-md-line-input">
										<label class="col-md-3 control-label" for="form_control_1">Reason
											:</label>
										<div class="col-md-4">
											<textarea style="word-break: break-all; white-space: normal;"
												maxlength="1024" class="form-control" name="reason" rows="3"
												value="leave.reason">${leave.reason}</textarea>
											<div class="form-control-focus"></div>
											<span class="help-block">Please fill the information</span>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<!-- <input type="hidden" name="saveedit"> -->
							<div class="form-group form-md-line-input">
								<div style="margin-top: 3cm;">
									<div class="form-group form-md-line-input">
										<center>
											<button type="submit" class="btn sbold blue">
												<i class="fa fa-save"></i>&nbsp;Save
											</button>
											<button style="margin-left: 1cm;" onclick="ss()"
												class="btn red">
												<i class="fa fa-times-circle"></i>&nbsp;Cancel
											</button>
										</center>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</c:if>
</c:forEach>
<script>
	function ss() {
		document.location = "leave_list";

	}
</script>
<script>
	$('#userId').change(function() {
		var userId = $('#userId').val();
		alert(userId);
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/select_approve",
			data : {
				"userId" : userId
			},
		}).done(function(data) {
			console.log(data);
			console.log($('.test').html(data));
		}).fail(function() {
			alert("เกิดข้อผิดพลาด");
		});
	});
	/* approver change*/
	/* $('#apprUserId').change(function() {
		var apprUserId = $('#apprUserId').val();
		alert(apprUserId);
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/changesapprove",
			data : {
				"apprUserId" : apprUserId
			},
		}).done(function(data) {
			console.log(data);
			console.log($('.test').html(data));
		}).fail(function() {
			alert("เกิดข้อผิดพลาด");
		});
	}); */
	/* date cannot delete*/
	$('.cannot').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8) {
			return false;
		}
	});
</script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		var value = "${flag_search}";
		if (value == "1") {
			var user = "${user_name}";
			// alert(user);
			document.getElementById(user).selected = "true";
		} else {
			$('.select2me').select2();
		}

	});
</script>
<script>
	var a, b;
	var diffDays = 0;
	var flag = true;
	var radios = document.getElementsByName("halfDay");
	var crc_radio = "${check_radio}";
	document.querySelector('.date-picker').onchange = function() {
		flag = true;
		a = document.getElementById("date_from").value;
		var day = a.substring(0, 2);
		var month = a.substring(3, 5);
		var year = a.substring(6, 10);

		b = document.getElementById("date_to").value;

		var day2 = b.substring(0, 2);
		var month2 = b.substring(3, 5);
		var year2 = b.substring(6, 10);
		var date1 = new Date(month + "/" + day + "/" + year);
		var date2 = new Date(month2 + "/" + day2 + "/" + year2);

		var timeDiff = Math.abs(date1.getTime() - date2.getTime());
		diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1;
		var radios = document.getElementsByName("halfDay");
		if (crc_radio == 1) {
			diffDays = diffDays - 0.5;
		} else {
			radios[0].checked = false;
			radios[1].checked = false;
		}

		document.getElementById("Amount").value = diffDays;
	};
	function choie_half() {

		while (flag) {
			if (radios[0].checked) {
				if (crc_radio != 1) {
					diffDays = diffDays - 0.5;
				}
				document.getElementById("Amount").value = diffDays;
				flag = false;
			}
			if (radios[1].checked) {
				if (crc_radio != 1) {
					diffDays = diffDays - 0.5;
				}
				document.getElementById("Amount").value = diffDays;
				flag = false;
			}
		}
	}
	document.querySelector('#date_from').onclick = function() {
		crc_radio = 0;
		radios[0].checked = false;
		radios[1].checked = false;
	};
</script>