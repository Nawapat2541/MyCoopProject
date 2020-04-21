<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
  
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="../assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
<link rel="shortcut icon" href="favicon.ico" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate pattern="yyyy" value="${dateTimeNow}" var="year_now" />
<fmt:formatDate pattern="MM" value="${dateTimeNow}" var="month_now" />
<fmt:formatDate pattern="dd" value="${dateTimeNow}" var="day_now" />
<fmt:formatDate pattern="E MMM dd yyyy" value="${dateTimeNow}"
	var="dateNow" />
<fmt:formatDate pattern="HH:mm" value="${dateTimeNow}" var="timeNow" />
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">FEEDBACK
				MANAGEMENT</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
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
		<form action="search_feed_mm" method="post">
			<div class="portlet-body">
				<div class="form-group form-md-line-input">
					<label class="control-label col-md-1">Date :</label>
					<div class="col-md-2">
						<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy">
							<c:if test="${startdate == null}">
								<input type="text" class="form-control cannot" name="startdate"
									id="startdate" data-date-format="dd-MM-yyyy"
									placeholder="Start date" size="13"
									value="<fmt:formatDate type="date" value="${now}" pattern="01-01-yyyy"  />">
							</c:if>
							<c:if test="${startdate != null}">
								<input type="text" class="form-control cannot" name="startdate"
									id="startdate" data-date-format="dd-MM-yyyy"
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
				<div class="form-group form-md-line-input">
					<div class="col-md-1" style="margin-left: 40%; margin-top: 10px;"></div>
					<button id="" type="submit" class="btn sbold blue">
						<i class="fa fa-search"></i> Search
					</button>
				</div>
			</div>
		</form>
		<div class="portlet-body" style="text-align: center;">
			<table
				class="table table-striped table-bordered table-hover" id="sample_3" cellspacing="0" width="100%" >
				<thead>
					<tr>
						<th style="width: 5%;">
							<center>ID</center>
						</th>
						<th style="width: 20%;"><center>Title</center></th>
						<th style="width: 12%;"><center>Submit date</center></th>
						<th style="width: 15%;"><center>The applicant</center></th>
						<th style="width: 20%;"><center>Description</center></th>
						<th style="width: 10%;"><center>Status</center></th>
						<th  style="width: 15%;"><center>Functionform</center></th>
						<%-- <th height="41"><center>Print</center></th> --%>
						<perm:permission object="leave.approve">
							<%-- <th><center>Approved</center></th>
							<th><center>Delete</center></th> --%>
						</perm:permission>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="feedback" items="${feedback}" varStatus="status">
					<tr>
						<td>${feedback.idfeedback}</td>
						<td style="text-align: left;" title="${feedback.title}">
						<c:if test="${feedback.title.length() > 20}">
							${feedback.title.toString().substring(0,20)}...
							</c:if>
							<c:if test="${feedback.title.length() < 20}">
							${feedback.title}
							</c:if>
						</td>
						<td><fmt:formatDate value="${feedback.time_create}"
								pattern="dd-MMM-yyyy"></fmt:formatDate></td>
						<td title="${feedback.user_create}">${feedback.user_create}</td>
						<td style="text-align: left;" title="${feedback.description}">
							<c:if test="${feedback.description.length() > 20}">
							${feedback.description.toString().substring(0,20)}...
							</c:if>
							<c:if test="${feedback.description.length() < 20}">
							${feedback.description}
							</c:if>
							</td>
						<td title="Status"><c:if
								test="${feedback.status.toString() == 'R'}">
								<span class="label label-sm label-info">Request</span>
							</c:if> <c:if test="${feedback.status.toString() == 'F'}">
								<span class="label label-sm label-warning">Fixing</span>
							</c:if> <c:if test="${feedback.status.toString() ==  'D'}">
								<span class="label label-sm label-success">Fixed</span>
							</c:if> <c:if test="${feedback.status.toString() ==  'J'}">
								<span class="label label-sm label-danger">Reject</span>
							</c:if></td>
						<td title="Action"><a title="View"
							class="btn btn-circle btn-icon-only green-turquoise btn-outline sbold uppercase"
							href="open_feed_mm?id_feed=${feedback.idfeedback}"
							style="color: green-turquoise;"><i class="fa fa-send"></i></a> 
							<a title="Delete" class="btn btn-circle btn-icon-only red-mint btn-outline sbold uppercase"
									onclick="ajaxCalldel(${feedback.idfeedback});"><i class="fa fa-close"></i></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>



		</div>

	</div>








</div>
<script type="text/javascript">
	function ajaxCalldel(id) {
		swal({
			  title: "Are you sure?",
			  text: "Your will not be able to recover this imaginary file!",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-danger",
			  confirmButtonText: "Yes, delete it!",
			  closeOnConfirm: false
			},
			function(){
				if(id !=""){
					$.ajax({
						url : "delFeedback",
						method : "POST",
						type : "JSON",
						data : {
								"id":id
						},
						success : function(data) {
								console.log(data);
							   console.log($('.timesheetTable').html(data));
							   swal('Delete!','Success!','warning');
							   location.reload();
						}
					})}
				  swal("Deleted!", "Your imaginary file has been deleted.", "success");
				});
		

	};
</script>
<script>
	$(document).ready(function() {
		var value = "${flag}";
		if (value == "1") {
			var name = "${name}";
			var monthSearch = "${monthSearch}";
			var yearSearch = "${yearSearch}";
			document.getElementById(name).selected = "true";
			document.getElementById(monthSearch).selected = "true";
			document.getElementById(yearSearch).selected = "true";
		} else {
			$('.select2me').select2();
		}

	});
</script>
<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<!-- BEGIN CORE PLUGINS -->
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>$(document).ready(function () {
    $('.select2me').select2();
});</script>