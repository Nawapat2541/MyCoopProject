<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<%--  ${expTravelTypeList} --%>
<%-- ${expSearchList}  --%>
<%-- ${expenseList} --%>

<%--  ${expenseList}   --%>

<%--  ${user.id} --%>
<%--  ${expenseList}  --%>
<%-- ${expenseList.dtStar} --%>
<%-- ${user.id} --%>
<%-- ${expSearchList} --%>
<%-- ${expense} --%>


<style>
p {
	margin: 20px 0 0;
}

b {
	color: blue;
}
</style>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-money font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Record
				travel expense form (Edit)</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<form action="expense_edit" method="post" class="form-horizontal">
			<input type="hidden" value="${user.id}" id="user" name="userUpdate"
				class="user"> <input type="hidden" value="${expenseId}"
				id="expenseId" name="expenseId" class="user">

			<c:forEach var="expense" items="${expense}">
				<input type="hidden" value="${expense.expense_group_id}"
					id="expenseGroupId" name="expenseGroupId" class="user">
			</c:forEach>

			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Record
					date :</label>
				<div class="col-md-6">
					<input type="text" class="form-control"
						value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"  />"
						disabled>
					<div class="form-control-focus"></div>

				</div>
			</div>
			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Requester
					: </label>
				<div class="col-md-6">
					<c:forEach var="expense" items="${expense}">
						<input type="text" class="form-control" name="name"
							value="${expense.department_id} - ${expense.name}" disabled>
						<div class="form-control-focus"></div>
					</c:forEach>
				</div>
			</div>

			<div class="form-group form-lg-line-input">

				<label class="col-md-3 control-label" for="form_control_1">Day
					of departure :</label>
				<div class="caption col-md-2 control-label">
					<div class="input-group input-large date-picker input-daterange"
						data-date-format="dd-mm-yyyy">
						<c:forEach var="expense" items="${expense}">

							<input name="dayOfDeparture" class="form-control" maxlength="13"
								id="date" required
								value="<fmt:formatDate type="both" value="${expense.dt_start}" pattern="dd-MM-yyyy"  />"
								style="text-align: left;">
						</c:forEach>
					</div>
				</div>
			</div>

			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Go
					by :</label>
				<div class="col-md-3">
					<select class="bs-select form-control" name="expense.DtBy">
						<c:forEach var="expense" items="${expense}">
							<option value="${expense.exp_travel_type_id}" selected=selected>
								${expense.exp_travel_name}</option>

						</c:forEach>
						<c:forEach var="expTravelTypeList" items="${expTravelTypeList}"
							varStatus="theCount">
							<option value="${expTravelTypeList.expTravelTypeId}">
								${expTravelTypeList.name}</option>
						</c:forEach>


					</select>
				</div>
				<div class="form-row">
					<label class="control-label col-md-1">Other</label>
					<div class="col-md-2">
						<div class="input-group">
							<input name="expense.Goby" id="goBy" type="text"
								class="form-control goBy" maxlength="200" style="width: auto"
								placeholder="">
							<div class="form-control-focus"></div>
							<span class="input-group-btn">
								<button type="submit" id="other" class="btn green-jungle"
									onclick="addOther();">
									<i class="fa fa-plus"></i>&nbsp;Add
								</button>
							</span>
						</div>
					</div>

				</div>
			</div>
			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Beginning
					:</label>
				<div class="col-md-3">
					<c:forEach var="expense" items="${expense}">
						<input name="expense.fromLocation" type="text"
							class="form-control" maxlength="100" placeholder=""
							value="${expense.from_location}">
						<div class="form-control-focus"></div>
					</c:forEach>

				</div>
				<div class="form-row">
					<label class="control-label col-md-1">Time</label>
					<div class="col-md-3">
						<div class="input-icon">
							<c:forEach var="expense" items="${expense}">
								<input type="text" name="startTimeEdit"
									class="form-control timepicker timepicker-24 "
									value="<fmt:formatDate value="${expense.dt_start}" pattern="HH:mm"></fmt:formatDate>">
						</div>
						</c:forEach>

					</div>
				</div>
			</div>
			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Destination
					:</label>
				<div class="col-md-3">
					<c:forEach var="expense" items="${expense}">

						<input name="expense.toLocation" type="text" class="form-control"
							maxlength="100" placeholder="" value="${expense.to_location}">
						<div class="form-control-focus"></div>
					</c:forEach>

				</div>
				<div class="form-row">
					<label class="control-label col-md-1">Time</label>
					<div class="col-md-3">
						<div class="input-icon">
							<c:forEach var="expense" items="${expense}">

								<input type="text" name="endTimeEdit"
									class="form-control timepicker timepicker-24"
									value="<fmt:formatDate value="${expense.dt_end}" pattern="HH:mm"></fmt:formatDate>">
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Purpose
					of journey :</label>
				<div class="col-md-6">
					<c:forEach var="expense" items="${expense}">
						<textarea style="word-break: break-all; white-space: normal;"
							maxlength="255" id="textarea1" class="form-control" rows="3"
							name="expense.description">${expense.description}</textarea>
						<div class="form-control-focus"></div>
					</c:forEach>
				</div>
			</div>
			<br> 
			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Total
					:</label>
				<div class="col-md-6">
					<c:forEach var="expense" items="${expense}">

						<input name="expense.amount" type="text" min="1"
							class="form-control"
							onkeypress="if(event.keyCode < 47 || event.keyCode > 58)event.returnValue = false"
							value="${expense.amount }" required>
					</c:forEach>
					<div class="form-control-focus"></div>

				</div>
				<label style="margin-left: 0.5cm;">บาท (Baht)</label>
			</div>
			<div class="form-group form-lg-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Status
					:</label>
				<div class="col-md-6">
					<label for="form_control_1"
						style="padding-top: 10px; padding-bottom: 10px;">Pending</label>
				</div>
			</div>
			<div style="margin-top: 2cm;">
				<div class="form-group form-md-line-input">
					<center>
						<button type="submit" id="demo" class="btn sbold blue">
							<i class="fa fa-save"></i>&nbsp;Save Travel Expense form request
						</button>
						<button type="reset" class="btn red">
							<i class="fa fa-close"></i>&nbsp;Cancel
						</button>
					</center>
				</div>
			</div>
		</form>
	</div>
</div>
<%-- ${userList} --%>
<%-- ${userseq} --%>






<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.select2me').select2();
	});
</script>
<script>
	function addOther() {
		var other = $("#goBy").val();
		var user = $("input.user").val();
		//alert("user= "+user+" text= "+othor);	
		if (other != "") {
			$
					.ajax({
						type : 'POST',
						url : "${pageContext.request.contextPath}/add-other",
						data : {
							"other" : other,
							"user" : user
						},
					})
					.done(
							function(data) {
								console.log(data);
								console.log($('#goBy').html(data));

								swal(
										{
											title : "SUCCESS",
											type : "success",
											showConfirmButton : true
										},
										function(isConfirm) {
											if (isConfirm) {
												document.location = "travelexp_form?userId=${onlineUser.id}";
											}
										})

							}).fail(
							function() {
								swal('Go by already exist,',
										'Please select or change your Go by!',
										'error');
							});

		} else {
			swal('Please!', 'Input your GO by', 'warning');
		}

	}
</script>
<script>
	$("input").change(function() {
		var $input = $(this);
		$("p").html($input.is(":checked") + "</b>");
	}).change();
</script>
<script>
	$(document).ready(function() {
		var flag = '${flag}';
		if (flag != "") {
			var date = '${date}';
			document.getElementById("date").value = date;
		}
	});
</script>
<script>
	$("#demo15").click(function() {

		if ($("p:first").text() == "false") {
			swal("Please select request expense!")
			document.getElementById("demo15").type = "button";
		} else {
			document.getElementById("demo15").type = "submit";
		}

	});
</script>
<script>
	$('form').submit(function() {
		$('#demo').attr("disabled", true);
	});

	function format2(n) {

		return n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");

	}
</script>

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
<script>
	$(document).ready(function() {

	});
</script>

<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>

<c:set var="now" value="<%=new java.util.Date()%>" />

