<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
<%-- ${expensegroupList} --%>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<!-- <script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
	function check() {
		var dropdown = document.getElementById("status");
		var current_value = dropdown.options[dropdown.selectedIndex].value;
		if (current_value == "W" || current_value == "A"
				|| current_value == "R") {
			document.getElementById("hidden").style.display = "none";
		} else if (current_value == "P") {
			document.getElementById("hidden").style.display = "block";
		} else {
			document.getElementById("hidden").style.display = "none";
		}
	}
	/* date cannot delete*/
	$('.cannot').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8) {
			return false;
		}
	});
</script>
<script>
	function pageload() {
		var dropdown = document.getElementById("status");
		var current_value = dropdown.options[dropdown.selectedIndex].value;
		if (current_value == "W" || current_value == "A"
				|| current_value == "R") {
			document.getElementById("hidden").style.display = "none";
		} else if (current_value == "P") {
			document.getElementById("hidden").style.display = "block";
		} else {
			document.getElementById("hidden").style.display = "none";
		}
		if (current_value != "W")  {
			document.getElementById("hidden_edit").style.display = "none";
		}
		if (current_value != "W")  {
			document.getElementById("hidden_delete").style.display = "none";
		}
	}
	window.onload = pageload;
</script>
<!-- (current_value == "W" && current_value == "A" && current_value == "R") -->
<c:forEach var="exp" items="${expensegroupList}">
	<c:if test="${exp.expenseGroupId  == param.Id}">
		<%-- ${exp.totalAmount} ${exp.statusId} --%>
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption">
					<div class="caption">
						<i class="fa fa-th-list font-red"></i> <span
							class="caption-subject font-red sbold uppercase">Request
							travel detail</span>
					</div>
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
			</div>
			<div class="portlet-body">
				<form action="request_approve" method="post" class="form-horizontal">

					<div class="portlet-body">
						<div class="form-group form-lg-line-input">
							<label class="col-md-2 control-label" for="form_control_1">Reimbursement
								form :</label>
							<div class="col-md-4">
								<input type="text" class="form-control cannot"
									name="expenseGroupId" value="${exp.expenseGroupId}">
							</div>
							<label class="col-md-2 control-label" for="form_control_1">Requester
								:</label>
							<div class="col-md-4">
				<select class="bs-select form-control select2me" name="name">
					<optgroup label="Enable">
					<c:forEach var="user" items="${userseq}">					  
					  <c:if test="${user.enable == 1 }">
					<c:if test="${userSelect == nulll }">
						<option value="${user.id}" id="${user.id}"
							<c:if test="${fn:containsIgnoreCase(user.id,exp.userId)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
							- ${user.name}</option></c:if>
							<c:if test="${userSelect != nulll }">
							<option value="${userSelect}"
							<c:if test="${fn:containsIgnoreCase(user.id,exp.userId)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
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
							<c:if test="${fn:containsIgnoreCase(user.id,exp.userId)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
							- ${user.name}</option></c:if>
							<c:if test="${userSelect != nulll }">
							<option value="${userSelect}"
							<c:if test="${fn:containsIgnoreCase(user.id,exp.userId)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
							- ${user.name}</option>
							</c:if>
							</c:if>
						</c:forEach>
						 </optgroup>
				</select>		
							</div>
						</div>
						<div class="form-group form-lg-line-input">
							<label class="col-md-2 control-label" for="form_control_1">Date
								:</label>
							<div class="col-md-4">
								<input name="timeCreate" type="text" class="form-control cannot"
									required
									value="<fmt:formatDate value="${exp.timeCreate}"
									type="date" pattern="dd-MM-yyyy" />">
							</div>
							<label class="col-md-2 control-label" for="form_control_1">Amount
								:</label>
							<div class="col-md-4" align="right">
								<input type="text" class="form-control cannot" placeholder="Number" 
									style="text-align: left;" onkeypress="validate(event)"
									name="noDay" 
									value=" <fmt:formatNumber minFractionDigits="2" value="${exp.totalAmount}"/> " />
							</div>
						</div>
						<div class="form-group form-lg-line-input status">
							<label class="col-md-2 control-label" for="form_control_1">Status
								:</label>
							<div class="col-md-4">
								<select class="bs-select form-control" name="statusId"
									onchange="check();" id="status">
									<option name="statusId" value="P" id="statusT"
										<c:if test="${exp.statusId.toString() == 'P'}">selected</c:if>>Paid
									</option>
									<option name="statusId" value="W" id="statusT"
										<c:if test="${exp.statusId.toString() == 'W'}">selected</c:if>>Waiting
										for approve</option>
									<option name="statusId" value="A" id="statusT"
										<c:if test="${exp.statusId.toString() == 'A'}">selected</c:if>>Approved
									</option>
									<option name="statusId" value="R" id="statusT"
										<c:if test="${exp.statusId.toString() == 'R'}">selected</c:if>>Reject
									</option>
								</select>
							</div>
							<div id="hidden">
								<div id="paidday">
									<label class="col-md-2 control-label" for="form_control_1">Month
										:</label>
									<div class="col-md-2">
										<select class="bs-select form-control" name="paidmonth">
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '1'}">selected</c:if>>
												<i>January</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '2'}">selected</c:if>>
												<i>February</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '3'}">selected</c:if>>
												<i>March</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '4'}">selected</c:if>>
												<i>April</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '5'}">selected</c:if>>
												<i>May</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '6'}">selected</c:if>>
												<i>June</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '7'}">selected</c:if>>
												<i>July</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '8'}">selected</c:if>>
												<i>August</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '9'}">selected</c:if>>
												<i>September</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '10'}">selected</c:if>>
												<i>October</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '11'}">selected</c:if>>
												<i>November</i></option>
											<option value="${exp.paidMonth}"
												<c:if test="${exp.paidMonth.toString() == '12'}">selected</c:if>>
												<i>December</i></option>
										</select>
									</div>
									<div class="col-md-2">
										<select id="paidyear" class="bs-select form-control"
											name="paidyear" id="status">
											<option name="statusId" value="${exp.paidYear}" id="status">
												${exp.paidYear}</option>
										</select>
									</div>
								</div>
							</div>

							<script>
								var min = new Date().getFullYear(), max = min + 6, year = min - 2, select = document
										.getElementById('paidyear');

								for (var i = year; i < max; i++) {
									var opt = document.createElement('option');
									opt.value = i;
									opt.innerHTML = i;
									select.appendChild(opt);
								}
							</script>
						</div>
						<div class="form-group form-lg-line-input">
							<label class="col-md-2 control-label" for="form_control_1">Report
								:</label>
							<div class="col-md-4">
								<a class="btn sbold green"
									href="http://ts.cubesofttech.com/CA/ReportAction.do?method=generateExpTravel&expenseGroupId=${exp.expenseGroupId}"
									title="" style="color: white;"><i class="fa fa-print"></i>
									Print</a>
							</div>
						</div>
						<perm:permission object="expense.edit">
						<div class="form-group form-lg-line-input">
							<center>
								<button type="submit" class="btn sbold blue">
									<i class="fa fa-save"></i>&nbsp;Save
								</button>
								<button style="margin-left: 1cm;" type="cancel" class="btn red">
									<i class="fa fa-close"></i>&nbsp;Cancel
								</button>
							</center>
						</div>
						</perm:permission>
					</div>
				</form>
			</div>
			<div class="portlet-body">
				<div class="portlet-body flip-scroll" style="text-align: center;">
					<table
						class="table table-bordered table-striped table-condensed flip-content">
						<thead class="flip-content">
							<tr>
								<th style="text-align: center;">Expense ID</th>
								<th style="text-align: center;">Date</th>
								<th style="text-align: center;">Time</th>
								<th style="text-align: start;" width="40%">From ~ To</th>
								<th style="text-align: center;">Go by</th>
								<th style="text-align: end;">Amount</th>
								<th style="text-align: center;" id="hidden_edit">Edit</th>
								<th style="text-align: center;" id="hidden_delete">Delete </th>
								
							</tr>
<!-- 							<tr> -->
<%-- 							<td>${param.Id}</td> --%>
<!-- 							</tr> -->
						</thead>
						<c:forEach var="exp" items="${groupId}" varStatus="status">
							<tr>
								<td title="${exp}">${exp.expense_id}</td>
								<td><fmt:formatDate value="${exp.dt_start}"
										pattern="dd-MM-yyyy"></fmt:formatDate> <%--  ~ <fmt:formatDate
										value="${exp.dt_end}" pattern="dd-MM-yyyy"></fmt:formatDate> --%></td>
								<td><fmt:formatDate value="${exp.dt_start}" pattern="HH:mm"></fmt:formatDate>
									~ <fmt:formatDate value="${exp.dt_end}" pattern="HH:mm"></fmt:formatDate></td>
								<td
									style="word-break: break-all; white-space: normal; text-align: start;">${exp.from_location}-${exp.to_location}<br>${exp.description}
								</td>
								<td>${exp.name}</td>
								<td align="right">
									<fmt:formatNumber minFractionDigits="2" value="${exp.amount}" /></td>
								<c:if test="${exp.status_id == 'W'}">
								<td >
									<a class="btn btn-circle btn-sm sbold blue" title="Edit"
										href="travelexp_edit?expenseId=${exp.expense_id}"
										style="color: white;"><i class="fa fa-pencil"></i></a>
									</td>
								<td > 
									<i class="fa fa-close font-red sweet-${exp.expense_id}"
										onclick="_gaq.push(['_trackEvent', 'example', 'try', 'Primary']);"></i>
										<script>
		document.querySelector('.sweet-${exp.expense_id}').onclick = function() {
						swal(
							{
								title : "Are you sure!",
								text : "You will be deleting this id!",
								type : "info",
								showCancelButton : true,
								confirmButtonClass : 'btn-danger',
								confirmButtonText : 'OK'
								},
								function(inputValue) {
									if (inputValue === false)
										return false;
									if (inputValue === "") {
										return false
											}
										document.location = "delete_group?expId=${exp.expense_id}&userId=${exp.user_id}&groupId=${param.Id}&from=${from}&to=${to}";
											});
							};
</script>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</c:if>
</c:forEach>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	var from = $("#from").val();
	var to = $("#to").val();
	/* alert(from+"  "+to); */

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
<script>
	var from = $("#from").text();
	var to = $("#to").text();

	<c:set var="from"  value="from"/>
	<c:set var="to"  value="to"/>
</script>
	
<script>$(document).ready(function () {
	var value = "${flag_search}";
	if (value == "1") {
    var user = "${userId}";
   // alert(user);
  	document.getElementById(user).selected = "true";
	}else{
    $('.select2me').select2();
	}
});</script>
<script>
	/* date cannot delete*/
	$('.cannot').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8) {
			return false;
		}
	});
</script>

<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>