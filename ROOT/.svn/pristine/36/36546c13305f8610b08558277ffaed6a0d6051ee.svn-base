<%@page import="org.apache.velocity.runtime.directive.Foreach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"
	var="date_now" />

<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-paperclip font-red"></i> <span
				class="caption-subject font-red sbold uppercase">My Leave
				application form</span>
		</div>
		<div class="actions">
			<a class="btn  btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="myleave_perform_add" method="post"
			class="form-horizontal">
			<div class="form-body">

				<div class="form-group">
					<label class="col-md-3 control-label">Date :</label>
					<div class="col-md-6">
						<input type="text" class="form-control" placeholder=""
							value="<fmt:setLocale value="en_US" /><fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"  />">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Applicant :</label>
					<div class="col-md-6">
						<select id="name1" class="form-control select2 select2me" disabled>
							<option></option>
							<optgroup label="Enable">
								<c:forEach var="user" items="${userseq}">
									<c:if test="${user.enable == 1 }">
										<c:if test="${userSelect == nulll }">
											<option value="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,userSelect)}"><c:out value="selected=selected"/></c:if>>${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
							<optgroup label="Disable">
								<c:forEach var="user" items="${userseq}">
									<c:if test="${user.enable == 0 }">
										<c:if test="${userSelect == nulll }">
											<option value="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,userSelect)}"><c:out value="selected=selected"/></c:if>>${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Type of leave :</label>
					<div class="col-md-6">
						<div class="md-radio-inline">
							<c:forEach var="type" items="${leavetypeList}" varStatus="Count">
								<c:if
									test="${!type.getLeaveTypeId().equals('4') && !type.getLeaveTypeId().equals('9')}">
									<div class="md-radio">
										<input type="radio" id="${type.getLeaveTypeId()}"
											name="leaveTypeId" class="md-radiobtn"
											value="${type.getLeaveTypeId()}"
											<c:if test="${Count.count eq '1'}"> Checked </c:if>>
										<label for="${type.getLeaveTypeId()}" style="color: #2F353B;">${type.leaveTypeName}
											<span class="inc"></span> <span class="check"
											style="background: #32C5D2;"></span> <span class="box"
											style="border-color: #2F353B;"></span>
										</label>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Duration :</label>
					<div class="col-md-6">
						<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy">
							<input type="text" class="form-control" id="date_from"
								name="from"> <span class="input-group-addon"> to
							</span> <input type="text" class="form-control" id="date_to" name="to">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Amount :</label>
					<div class="col-md-2">
						<input type="text" required class="form-control" id="Amount"
							min="1" max="1000"
							onkeypress="return fun_AllowOnlyAmountAndDot(this.id);"
							name="noDay" maxlength="3" value=1>
					</div>
					<label class="col-md-1 control-label" style="text-align: left">Day</label>
					<div class="col-md-2">
						<select class="bs-select form-control" name="comboA" id="comboA">
							<option value="0" selected>0</option>
							<option value="0.125">1</option>
							<option value="0.25">2</option>
							<option value="0.375">3</option>
							<option value="0.5">4</option>
							<option value="0.625">5</option>
							<option value="0.75">6</option>
							<option value="0.875">7</option>
						</select>
					</div>
					<label class="col-md-1 control-label" style="text-align: left">Hours</label>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">half day leave :</label>
					<div class="col-md-6">
						<div class="form-group form-md-radios has-sucess">
							<div class="md-radio-inline">
								<div class="md-radio">
									<input type="radio" id="checkbox2_2" name="halfDay" value="1"
										onclick="choie_half()" class="md-radiobtn"> <label
										for="checkbox2_2"> <span></span> <span class="check"></span>
										<span class="box"></span> Morning
									</label>
								</div>
								<div class="md-radio">
									<input type="radio" id="checkbox2_3" name="halfDay" value="2"
										onclick="choie_half()" class="md-radiobtn"> <label
										for="checkbox2_3"> <span></span> <span class="check"></span>
										<span class="box"></span> Afternoon
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Description :</label>
					<div class="col-md-6">
						<textarea style="word-break: break-all; white-space: normal;"
							maxlength="1024" class="form-control" rows="6" name="description"></textarea>
						<div class="form-control-focus"></div>
						<span class="help-block">Please fill the information</span>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Approvers :</label>
					<div class="col-md-6">
						<c:choose>
							<c:when test="${userId != null}">
								<select class="bs-select form-control" id="name2" disabled>
									<c:forEach var="manager" items="${leader}">
										<option value="${manager.manager_id}"
											<c:if test="${user.managerId eq manager.manager_id }"> selected </c:if>>${manager.manager_id}</option>
									</c:forEach>
									<c:forEach var="manager" items="${userList}">
										<option value="${manager.id}"
											<c:if test="${manager.id eq user.managerId }"></c:if>>${manager.name}</option>
									</c:forEach>
								</select>
							</c:when>
							<c:when test="${userId != null}">
								<select class="bs-select form-control" name="name2" id="name2"
									disabled>
									<c:forEach var="manager" items="${leader}">
										<option value="${user.id}"
											<c:if test="${user.managerId eq manager.manager_id }"> selected </c:if>>${manager.manager_id}</option>
									</c:forEach>
									<c:forEach var="manager" items="${userList}">
										<option value="${manager.id}"
											<c:if test="${manager.id eq user.managerId }"></c:if>>${manager.name}</option>
									</c:forEach>
								</select>
							</c:when>
						</c:choose>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Status :</label>
					<div class="col-md-6">
						<c:if test="${flag.toString() eq '1' }">
							<select class="bs-select form-control" name="leaveStatusId"
								id="leaveStatusId" disabled>
								<option name="leaveStatusId" value="0"
									<c:if test="${leave.leaveStatusId.toString() == '0'}">selected</c:if>>
									<i>Waiting for approve</i>
								</option>
								<option name="leaveStatusId" value="1"
									<c:if test="${leave.leaveStatusId.toString() == '1'}">selected</c:if>>
									<i>Approved</i>
								</option>
								<option name="leaveStatusId" value="2"
									<c:if test="${leave.leaveStatusId.toString() == '2'}">selected</c:if>>
									<i>Reject</i>
								</option>
							</select>
						</c:if>
						<c:if test="${flag.toString() eq '0' }">
							<option name="leaveStatusId" value="0"
								<c:if test="${leave.leaveStatusId.toString() == '0'}">selected</c:if>>
								<i>Waiting for approve</i>
							</option>
						</c:if>
					</div>
				</div>


				<div class="form-group">
					<label class="col-md-3 control-label">Reason :</label>
					<div class="col-md-6">
						<textarea class="form-control" name="reason" rows="3"
							maxlength="1024">${leave.reason}</textarea>
						<div class="form-control-focus"></div>
						<span class="help-block">Please fill the information</span>
					</div>
				</div>

			</div>
			<div class="form-actions">
				<div class="row">
					<div class="col-xs-12" style="text-align: center;">
						<button type="submit" class="btn  green">Submit</button>
						<button type="reset" class="btn  grey-salsa btn-outline">Cancel</button>
					</div>
				</div>
			</div>
			<input type="hidden" name="name" id="name" /> <input type="hidden"
				name="apprUserId" id="apprUserId" />
		</form>
		<!-- END FORM-->
	</div>
</div>

<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script>
	$('#userId').change(function() {
		var userId = $('#userId').val();
		alert(userId);
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/select_myleader",
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
</script>
<script>
	$('.cannot').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8) {
			return false;
		}
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var id = "${userId}";
		var date = '${date}';
		var dd, mm, yy, s;
		document.getElementById("date_s").placeholder = "";
		document.getElementById("date_e").placeholder = "";
		document.getElementById("date_s").value = date;
		document.getElementById("date_e").value = date;
		function covert(x) {
			var dd, mm, yy;

			yy = x.substring(0, 4);

			mm = x.substring(5, 7);

			dd = x.substring(8, 10);

			var date = dd + "-" + mm + "-" + yy;

			return date;
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
	function validate(evt) {
		var theEvent = evt || window.event;
		var key = theEvent.keyCode || theEvent.which;
		key = String.fromCharCode(key);
		var regex = /[0-9]|\./;
		if (!regex.test(key)) {
			theEvent.returnValue = false;
			if (theEvent.preventDefault)
				theEvent.preventDefault();
		}
	}
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
				alert("Wrong position of decimal point not  allowed !!");
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
	function error() {
		var flag = '${flag_1}';

		if (flag != "2") {
			document.location = "leavecalendar?date="
					+ document.getElementById("date_s").value;
		} else {
			document.location = "leavecalendar";
		}
	}
</script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.select2me').select2();
		$('#name').val($('#name1').val());
		$('#apprUserId').val($('#name2').val());
		$('#date_from').val('${date_now}');
		$('#date_to').val('${date_now}');
		var flag = '${flag_1}';
		if (flag == "2") {
			document.getElementById("date_from").value = '${date_calendar}';
			document.getElementById("date_to").value = '${date_calendar}';
			}
		});
	$('#name1').change(() => { $('#name').val($('#name1').val()); });
	$('#name2').change(() => { $('#apprUserId').val($('#name2').val()); });
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
     <c:set var="date" value="<%=new java.util.Date()%>" />
     <fmt:formatDate pattern="31-03-yyyy" value="${date}" var="LastDayOfLastYearQuota" />
     $(document).on('click', '#6', function(){
         $('#date_to').datepicker('setEndDate', '${LastDayOfLastYearQuota}');
         });
     
 
});
</script>


