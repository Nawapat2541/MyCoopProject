<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.*,java.sql.*,java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-latest.min.js"></script>

<div class="portlet-body form"
	style="margin-right: 2%; margin-left: 2%;">
	<%--
	<div id="div_input">
		<button class="btn btn-info" style="border-radius: 8px!important; float: left;"  onclick="confirmFunc()">Reset
				Quota last year</button> Search:&nbsp;<input type="text" id="myInput"
			onkeyup="myFunction()" placeholder="Search for names.."
			title="Type in a name"
			class="form-control input-sm input-small input-inline">
	</div>
	--%>
	<!-- Begin Table -->
	<div class="portlet-body">

		<table
			class="table table-bordered table-striped table-condensed table-hover order-column compact"
			data-toggle="table" data-search="true" id="myTable">
			<thead>
				<tr>
					<th style="text-align: left;"><center>#</center></th>
					<th style="text-align: left;">Name</th>
					<th style="text-align: left;">Position</th>
					<th style="text-align: left;" class="visible-lg">Department</th>
					<th style="text-align: left;">Role</th>
					<th style="text-align: left;">Start Date</th>
					<th style="text-align: center;">Status</th>
					<th style="text-align: center;">LastCheckin</th>
					<th style="text-align: center;">Gender</th>
					<th style="text-align: left;">Document</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${cubesoftUsers}" varStatus="status">

					<c:if test="${user.flag_search=='1'}">
						<tr>
							<td data-order="${user.id}"><center>${status.count}&nbsp;</center></td>
							<td style="text-align: left;"><a
								href="user-edit?userId=${user.id}">${user.name}&nbsp;</a></td>
							<td>${user.position_id}&nbsp;</td>
							<td class="visible-lg">${user.department_id}&nbsp;</td>
							<td>${user.role_id}&nbsp;</td>
							<td align='center' data-order='${user.start_date}'><fmt:formatDate
									value="${user.start_date}" pattern=" dd-MMM-yyyy" /></td>
							<td data-order="${user.enable}">
								<div class="md-checkbox-list test">
									<div>
										<div class="md-checkbox" style="margin-left: 32%;">
											<input type="checkbox" id="${user.id}" class="md-check"
												onclick="update('${user.id}','<c:if test="${user.enable == 0}">1</c:if><c:if test="${user.enable == 1}">0</c:if>')"
												<c:if test="${user.enable == 1}">checked</c:if>> <label
												for="${user.id}"> <span></span> <span class="check"></span>
												<span class="box"></span>
											</label>
										</div>
									</div>
								</div>
							</td>
							<td align='center' data-order='${user.last_chackin}'><fmt:formatDate
									value="${user.last_chackin}" pattern="dd/MMM/yyyy HH:mm" /></td>

							<td><input type="radio" id="radio" value="${user.id}/M"
								name="${user.id}"
								<c:if test="${user.gendertrue == '1'}">
                                          checked
                                	</c:if>>
								Male <br> <input type="radio" id="radio"
								value="${user.id}/F" name="${user.id}"
								<c:if test="${user.gendertrue == '2'}">
                                          checked
                                    </c:if>>
								Female</td> 
								<td><center><a href="userReport?id=${user.id}"><i class="fa fa-file-pdf-o" ></i></a></center></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		<!-- End Table -->
	</div>
</div>

<script>
	$('input[type="radio"]').click(function(x) {
		var gender = $(this).val();
		/* console.log(gender); */
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/update-gender",
			data : {
				"gender" : gender,
			},
			success : function(json) {
				var setgender = gender.split("/");
				var userid = setgender[0];
				if (setgender[1] == "M") {
					setgender[1] = "Male";
				}
				if (setgender[1] == "F") {
					setgender[1] = "Female";
				}
				/* console.log(userid); */
				var enable = "1";
				Alert2(userid, setgender[1]);
			},
			error : function(json) {
			}
		});
	});
	function Alert2(userid, enable) {
		toastr.success(userid + " " + enable + " success ");
	}
	/* ======================================================= */
	var userid;
	var enable;
<%-- 
	function update(id, enable) {
		enable = enable;
		userid = id;
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/update-user-status",
			dataType: "json",
			data : {
				"enable" : enable,
				"userid" : userid
			},
		}).done(function(json) {
			console.log(json);
			Alert(userid, enable);
		}).fail(function() {
			swal("Here's a message!", "Fail")
		});
	}
	
	--%>
	function update(id, enable) {

		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/update-user-status",
			crossorigin : true,
			dataType : "json",
			data : {
				"enable" : enable,
				"userid" : id
			},
			success : function(json) {
				Alert(json.id, json.enable);
				console.log(json);
			}

		});

	}

	function Alert(userid, enable) {

		if (enable == "1") {
			var massage = "enable";
			toastr.success(userid + " " + massage + " success ");
		} else {
			var massage = "disable";
			toastr.warning(userid + " " + massage + " success ");
		}

	}

	/* $("showtoast").click(function() {
		alert("Handler for .click() called.");
	}); */
	function confirmFunc() {
		var r = confirm("ยืนยันการล้างข้อมูลจำนวนการลาคงเหลือจากปีที่แล้ว");
		if (r == true) {
			document.location = "Reset_lastyear";
		} else {

		}
	}

	$(document).ready(function() {
		$('#myTable').DataTable({
			"aLengthMenu" : [ [ 25, 50, 75, -1 ], [ 25, 50, 75, "All" ] ],
			"iDisplayLength" : 50,
			"order" : [ [ 6, "desc" ] ]

		});
	});
</script>
<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />

<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>


<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>