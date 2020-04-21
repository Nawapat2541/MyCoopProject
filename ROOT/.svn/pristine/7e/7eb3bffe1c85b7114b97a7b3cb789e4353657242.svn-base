
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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


	<input type="hidden" value="${flag}" />
	<input type="hidden" id="demo" value="${date}" />
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
				<form action="leave_form_edit" method="POST">
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
								<select class="bs-select form-control select2me" disabled
									id="name1">
									<!-- <option value="${onlineUser.id}">${onlineUser.name}</option> -->
									<optgroup label="Enable">
										<c:forEach var="user" items="${userseq}">
											<c:if test="${user.enable == 1 }">
												<c:if test="${userSelect == nulll }">
													<option value="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,leave.user_id)}"><c:out value="selected=selected"/></c:if>>
														${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,userSelect)}"><c:out value="selected=selected"/></c:if>>
														${user.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
									<optgroup label="Disable">
										<c:forEach var="user" items="${userseq}">
											<c:if test="${user.enable == 0 }">
												<c:if test="${userSelect == nulll }">
													<option value="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,leave.user_id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
												<c:if test="${userSelect != nulll }">
													<option value="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,userSelect)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
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
													<c:if test="${type.getLeaveTypeId() eq leave.leave_type_id.toString()}"> checked </c:if>>
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
							<div class="col-md-2">
								<input type="text" required class="form-control" id="Amount"
									min="1" max="1000"
									onkeypress="return fun_AllowOnlyAmountAndDot(this.id);"
									name="noDay" maxlength="3">
							</div>
							<label class="control-label col-md-1">Day</label>
							<div class="col-md-2">
								<select class="bs-select form-control" id="comboA" name="comboA">
									<c:if test="${leave.no_day > 1.0}">
										<c:set var="valueDate" value="${leave.no_day%1*1000}" />

										<c:if test="${valueDate == 125}">
											<option value="0.125" selected>1</option>
										</c:if>
										<c:if test="${valueDate == 250}">
											<option value="0.25" selected>2</option>
										</c:if>
										<c:if test="${valueDate == 375}">
											<option value="0.375" selected>3</option>
										</c:if>
										<c:if test="${valueDate == 500}">
											<option value="0.5" selected>4</option>
										</c:if>
										<c:if test="${valueDate == 625}">
											<option value="0.625" selected>5</option>
										</c:if>
										<c:if test="${valueDate == 750}">
											<option value="0.75" selected>6</option>
										</c:if>
										<c:if test="${valueDate == 875}">
											<option value="0.875" selected>7</option>
										</c:if>
									</c:if>

									<option value="0">0</option>
									<option value="0.125">1</option>
									<option value="0.25">2</option>
									<option value="0.375">3</option>
									<option value="0.5">4</option>
									<option value="0.625">5</option>
									<option value="0.75">6</option>
									<option value="0.875">7</option>
								</select>

							</div>
							<label class="control-label col-md-1">Hours</label>

						</div>
						<c:if test="${leave.leave_id  == param.Id}">
							<div class="form-group form-md-line-input">
								<label class="col-md-3 control-label" for="form_control_1">A
									half day leave :</label>
								<div class="col-md-9">
									<div class="form-group form-md-radios has-sucess">
										<div class="md-radio-inline">
											<div class="md-radio">
												<input type="radio" id="radio14" name="halfDay"
													onclick="choie_half()" class="md-radiobtn"
													<c:if test="${leave.half_day.toString() eq '1' }">checked <c:set var="check_radio" value="1" />  </c:if>
													value="1"> <label for="radio14"
													style="color: #2F353B;"> <span class="inc"></span>
													<span class="check" style="background: #32C5D2;"></span> <span
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
													style="color: #2F353B;"> <span class="inc"></span>
													<span class="check" style="background: #32C5D2;"></span> <span
													class="box" style="border-color: #2F353B;"></span>
													Afternoon
												</label>
											</div>
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
						<br>
						<div style="margin-top: 2cm;"></div>
						<div class="form-group form-md-line-input">

							<label class="col-md-3 control-label" for="form_control_1">Approvers
								:</label>
							<div class="col-md-4">
								<c:choose>
									<c:when test="${id != null}">
										<select class="bs-select form-control select2me"
											name="apprUserId" id="appUserId" disable>
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
										<c:forEach var="leave" items="${approve }">

											<option name="leaveStatusId" value="0"
												<c:if test="${leave.leave_status_id.toString() eq '0'}"><c:out value="selected=selected"/></c:if>>Waiting
												for approve</option>
											<option name="leaveStatusId" value="1"
												<c:if test="${leave.leave_status_id.toString() eq '1'}"><c:out value="selected=selected"/></c:if>>Approved</option>
											<option name="leaveStatusId" value="2"
												<c:if test="${leave.leave_status_id.toString() eq '2'}"><c:out value="selected=selected"/></c:if>>Reject</option>
										</c:forEach>
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
										<button style="margin-left: 1cm;" type="button" onclick="ss()"
											class="btn red">
											<i class="fa fa-times-circle"></i>&nbsp;Cancel
										</button>
									</center>

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

		document.location = "myleave_list?Id=${onlineUser.id}";

	}
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
<script type="text/javascript">
	function fun_AllowOnlyAmountAndDot(txt) {
		if (event.keyCode > 47 && event.keyCode < 58 || event.keyCode == 46) {
			var txtbx = document.getElementById(txt);
			var amount = document.getElementById(txt).value;
			var present = 0;
			var count = 0;

			if (amount.indexOf(".", present)
					|| amount.indexOf(".", present + 1))
				;
			{
				// alert('0');
			}

			/*if(amount.length==2)
			{
			  if(event.keyCode != 46)
			  return false;
			}*/
			do {
				present = amount.indexOf(".", present);
				if (present != -1) {
					count++;
					present++;
				}
			} while (present != -1);
			if (present == -1 && amount.length == 0 && event.keyCode == 46) {
				event.keyCode = 0;
				//alert("Wrong position of decimal point not  allowed !!");
				return false;
			}

			if (count >= 1 && event.keyCode == 46) {

				event.keyCode = 0;
				//alert("Only one decimal point is allowed !!");
				return false;
			}
			if (count == 1) {
				var lastdigits = amount.substring(amount.indexOf(".") + 1,
						amount.length);
				if (lastdigits.length >= 2) {
					//alert("Two decimal places only allowed");
					event.keyCode = 0;
					return false;
				}
			}
			return true;
		} else {
			event.keyCode = 0;
			//alert("Only Numbers with dot allowed !!");
			return false;
		}

	}
</script>
<script>
	function herfCalendar() {
		var date = '${date}';
		document.getElementById("demo").value = date;
		var x = document.getElementById("demo").value; // Date Format (dd-MM-yyyy)

		document.location = "leavecalendar?date=" + x;
	}
</script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {

		var value = "${flag_search}";
		if (value == "1") {
			var user = "${userId}";
			// alert(user);
			document.getElementById(user).selected = "true";

		} else {
			$('.select2me').select2();
		}

	});
</script>
<script>
	//------------------------------- *Day -----------------------------------
	var diffDays;
	var flag = true;
	//array
	var from = new Array(3);
	var to = new Array(3);
	document.querySelector('.date-picker').onchange = function() {
		flag = true;
		var holiday = 0;
		var dateFrom = document.getElementById("date_from").value;
		var dateTo = document.getElementById("date_to").value;
		from[0] = dateFrom.substring(0, 2); //00-00-0000
		from[1] = dateFrom.substring(3, 5);
		from[2] = dateFrom.substring(6, 10);
		to[0] = dateTo.substring(0, 2);
		to[1] = dateTo.substring(3, 5);
		to[2] = dateTo.substring(6, 10);
		var dayFrom = new Date(from[1] + "/" + from[0] + "/" + from[2]);
		var dayTo = new Date(to[1] + "/" + to[0] + "/" + to[2]);
		var dayFrom2 = new Date(from[1] + "-" + from[0] + "-" + from[2]);
		var dayTo2 = new Date(to[1] + "-" + to[0] + "-" + to[2]);
		
		var findholiday = [
      	  	<c:forEach var="setHoliday" items="${cutholiday}">
      	  	["${setHoliday.start_date}","${setHoliday.end_date}"],
      	  	</c:forEach>
      	  	[]
        ];		
		findholiday.pop();
		
		holidays = [];
		for(i = 0 ; i < findholiday.length ; i++) {
			start = new Date(findholiday[i][0]).getTime();
			end = new Date(findholiday[i][1]).getTime();
			for(j = start ; j <= end ; j += 86400000){
				day = new Date(j).getTime();
				holidays.push(day);
			}
		}
	
			if (dayFrom.getTime() <= dayTo.getTime()) {
				if (dayFrom.getTime() == dayTo.getTime()) {
					diffDays = "1";
				} else {
					var timeDiff = Math.abs(dayFrom.getTime() - dayTo.getTime());
					diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1;
					for (i = dayFrom; i <= dayTo; dayFrom.setDate(dayFrom.getDate() + 1)) {						 
						if (dayFrom.getDay() === 0 || dayFrom.getDay() === 6) {
							++holiday;
						} 
					} 
					
					for (j = dayFrom2; j <= dayTo2; dayFrom2.setTime(dayFrom2.getTime() + 86400000)) {
						for (n = 0 ; n < holidays.length ; n++) {
							if (dayFrom2.getTime() == holidays[n]) {
								++holiday;
							}
						}
					} 
				}
				
			} else if (dayFrom.getTime() > dayTo.getTime()) {
				diffDays = "1";
			}
			
		if (diffDays == undefined) {
			document.getElementById("Amount").value = "1";
		} else {
			document.getElementById("Amount").value = diffDays - holiday;
		}
		var radios = document.getElementsByName("halfDay");
		radios[0] = false;
		radios[1] = false;
		//  -------------------------------- End ----------------------------------
	};
	function choie_half() {
		var radios = document.getElementsByName("halfDay");
		while (flag) {
			if (radios[0].checked) {
				//document.getElementById("comboA").value = 0.500;
				flag = false;
			}
			if (radios[1].checked) {
				//document.getElementById("comboA").value = 0.500;
				flag = false;
			}
		}
	}
</script>
<script>
$(document).ready(function () {
		var setHo = [
    	 <c:forEach items="${holidayList}" var="item">
    	 	<c:set var="sday" value="${item.start_date}"/>
        	 <c:set var="eday" value="${item.end_date}"/>
    	 		<c:if test="${sday == eday}">
    	 			'${item.start_date}' ,
    	 		</c:if>
    	 		<c:if test="${sday != eday}">
    	 		    '${item.start_date}' , '${item.end_date}', 	 		    
	 			</c:if>
		 </c:forEach>
	     []
     ];
     setHo.pop();
    
   
 
 	var timebetween= [
		<c:forEach items="${setHoli}" var="itemEnd">
	 	<c:set var="time1" value="${itemEnd.start_date.getTime()}"/>
    	 <c:set var="time2" value="${itemEnd.end_date.getTime()}"/>
    		 <c:if test="${time1 != time2}">
    		 	<c:set var="vtime" value="${time2 - time1}"/>
    		 		<c:set var="Rtime" value="${vtime / (1000 * 3600 * 24)}"/>
    		 			<c:forEach var = "i" begin = "1" end = "${Rtime-1}">
    		 				<c:set var="valuetime" value="${time1+86400000*i}"/>
    		 				${valuetime},
    		 			</c:forEach>
			</c:if>
	 </c:forEach>
	[]
 	];
	 timebetween.pop();
	 
	 SEholiday = [];
	 for( i = 0 ; i < setHo.length; i++ ){
		 SEholiday.push(setHo[i]);
	 }

	 holiday = [];
	 for( i = 0 ; i < timebetween.length; i++ ){
			holiday.push(timebetween[i]);
	 }

	 BWholiday = [];
	 for( j = 0 ; j < holiday.length; j++ ){
			ddd = new Date(holiday[j]).toLocaleString('en-GB');
			BWholiday.push(ddd);
	 }
	
	 BWholiday2 = [];
	 for( j = 0 ; j < holiday.length; j++ ){
			aaa = BWholiday[j].substring(0, 10);
			BWholiday2.push(aaa);
	 }

	
	 var newlist = SEholiday.concat(BWholiday2);
	 
     $('#date_from').datepicker({
    	 format: 'dd-mm-yyyy',
  		daysOfWeekDisabled: [0,6],
  		datesDisabled: newlist ,
 		autoclose: true, 
      });
     $('#date_to').datepicker({
 		format: 'dd-mm-yyyy',
 		startDate: "+0d",
 		daysOfWeekDisabled: [0,6],
  		datesDisabled: setHo ,
 		autoclose: true, 
      });
     $(document).on('change', '#date_from', function(){
     	var disbleDate = $(this).val();
     	$('#date_to').datepicker('setStartDate', disbleDate);
     }); 
 
});
</script>


