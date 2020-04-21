<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.js"></script>
<!-- <script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"
	var="date_now" />
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
		<form action="leave_perform_add" method="POST">
			<div class="portlet-body">
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1"
						class="required" aria-required="true">Date :</label>
					<div class="col-md-6">
						<input type="text" class="form-control" placeholder=""
							value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"  />">
						<div class="form-control-focus"></div>
						<span class="help-block">Please fill the information</span>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">Applicant
						:</label>
					<div class="col-md-4 ">
						<select class="bs-select form-control select2me  " name="name"
							id="userId1" onchange="s(this);">
							<optgroup label="Enable">
								<c:forEach var="user" items="${userseq}">

									<c:if test="${user.enable == 1 }">
										<c:if test="${userSelect == nulll }">
											<option value="${user.id}"
												<c:if test="${user.id eq onlineUser.id}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${userSelect}"
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
											<option value="${user.id}"
												<c:if test="${user.id eq onlineUser.id}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${userSelect}"
												<c:if test="${user.id eq userSelect}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
						</select>
					</div>
				</div>


				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">Type
						of leave :</label>
					<div class="col-md-9">
						<div class="md-radio-inline">
							<c:forEach var="type" items="${leavetypeList}" varStatus="Count">
								<div class="md-radio">
									<input type="radio" id="${type.getLeaveTypeId()}"
										name="leaveTypeId" class="md-radiobtn"
										value="${type.getLeaveTypeId()}"
										<c:if test="${Count.count eq '1'}"> Checked </c:if>
									>
										<label
										for="${type.getLeaveTypeId()}" style="color: #2F353B;">
										${type.leaveTypeName}
										 <span class="check"
										style="background: #32C5D2;"></span> <span class="box"
										style="border-color: #2F353B;"></span>
									</label>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">Duration
						:</label>
					<div class="col-md-6">
						<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy">
							<input name="from" type="text" class="form-control cannot"
								id="date_from" aria-required="true" value="${date_now}" required>
							<span class="input-group-addon"> To :</span> <input type="text"
								name="to" id="date_to" required value="${date_now}"
								class="form-control cannot" class="required"
								aria-required="true">
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
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">A
						half day leave :</label>
					<div class="col-md-9">
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
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">Description
						:</label>
					<!-- <div>
						<label class="col-md-3 control-label" for="form_control_1">Relevant
							person</label>
					</div> -->
					<div class="col-md-4">
						<textarea style="word-break: break-all; white-space: normal;"
							maxlength="1024" class="form-control" rows="6" name="description"></textarea>
						<div class="form-control-focus"></div>
						<span class="help-block">Please fill the information</span>
					</div>
				</div>
				<div class="portlet-body test">
					<div style="margin-top: 2cm;"></div>
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Approvers
							:</label>
						<div class="col-md-4">
							<c:choose>
								<%-- <c:when test="${userId != null}">
				<select class="bs-select form-control" name="apprUserId"
					id="apprUserId">
					<c:forEach var="manager" items="${leader}">
						<option value="${user.id}"
							<c:if test="${user.managerId eq manager.manager_id }"> selected </c:if>>${manager.manager_id}</option>
					</c:forEach>
					<c:forEach var="manager" items="${userList}">
						<option value="${manager.id}"
							<c:if test="${manager.id eq user.managerId }"></c:if>>${manager.name}</option>
					</c:forEach>
				</select>
			</c:when> --%>
								<c:when test="${userId != null}">
									<select class="bs-select form-control select2me"
										name="apprUserId" id="apprUserId">
										<c:forEach var="leader" items="${leader}">
											<option value="${leader.manager_id}"
												<c:if test="${userId != null}">  selected  </c:if>>${leader.manager_id}</option>
										</c:forEach>
										<!-- the value of user.id (not must be change value) -->
										<c:forEach var="userList" items="${userList}">
											<option value="${userList.id}"
												<c:if test="${userList.id eq leave.apprUserId}"></c:if>>${userList.id}</option>
										</c:forEach>
									</select>
								</c:when>
							</c:choose>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Status
							:</label>
						<div class="col-md-4">
							<select class="bs-select form-control" name="leaveStatusId"
								id="leaveStatusId">
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
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Reason
							:</label>
						<div class="col-md-4">
							<textarea class="form-control" name="reason" rows="3"
								maxlength="1024">${leave.reason}</textarea>
							<div class="form-control-focus"></div>
							<span class="help-block">Please fill the information</span>
						</div>
					</div>
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
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {

		$('.select2me').select2();

		/*     var date1 = new Date("06/22/2017");
		 var date2 = new Date("06/23/2017"); 
		 var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		 var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
		 alert(diffDays);  */

	});
</script>
<script>
	var a, b;
	var diffDays;
	var flag = true;
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

		document.getElementById("Amount").value = diffDays;
		var radios = document.getElementsByName("halfDay");
		radios[0].checked = false;
		radios[1].checked = false;

	};
	function choie_half() {
		var radios = document.getElementsByName("halfDay");
		while (flag) {
			if (radios[0].checked) {
				diffDays = diffDays - 0.5;
				document.getElementById("Amount").value = diffDays;
				flag = false;
			}
			if (radios[1].checked) {
				diffDays = diffDays - 0.5;
				document.getElementById("Amount").value = diffDays;
				flag = false;
			}
		}
	}
</script>
<script>
	function ss() {
		document.location = "leave_list";

	}
	function s(sel) {
		// alert(sel.value) ;
		var userId = $('#userId1').val();

		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/search_select",
			data : {
				"userId" : userId
			},
		}).done(function(data) {
			//console.log(data);
			console.log($('.test').html(data));
		}).fail(function() {
			//alert("เกิดข้อผิดพลาด");
		});
	}
</script>
<script>
	/* 	 $('#userId1').on('change',function(e) {
			var userId = $('#userId1').val();
		alert(userId);
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/search_select",
			data : {
				"userId" : userId
			},
		}).done(function(data) {
			//console.log(data);
			console.log($('.test').html(data));
		}).fail(function() {
			//alert("เกิดข้อผิดพลาด");
		});
	});  
	
	 *//* 	$("#userId1").select2({
		    minimumInputLength: 2,
		    tags: [],
		    ajax: {
		        url: "${pageContext.request.contextPath}/search_select",
		        dataType: 'json',
		        type: "GET",
		        quietMillis: 50,
		        data: function (term) {
		            return {
		                term: term
		            };
		        },
		        results: function (data) {
		            return {
		                results: $.map(data, function (item) {
		                    return {
		                        text: item.completeName,
		                        slug: item.slug,
		                        id: item.id
		                    }
		                })
		            };
		        }
		    }
		}); */
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
