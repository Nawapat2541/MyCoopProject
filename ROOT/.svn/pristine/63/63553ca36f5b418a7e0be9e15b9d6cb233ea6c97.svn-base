<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>

<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="favicon.ico" />

	<div class="portlet light bordered">
		<div class="portlet-title">
		<div class="caption">
			<i class="icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">My Feedback</span>
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
		<div class="portlet-body" style="text-align: center;">
			<table
				class="table table-striped table-bordered table-hover" id="sample_3" cellspacing="0" width="100%" >
				<!--  HEAD  -->
				<thead>
					<tr>
						<th style="text-align: center;"># Track</th>
						<th style="text-align: center;">Title</th>
						<th style="text-align: center;">Description</th>
						<th style="text-align: center;">Date</th>
						<th style="text-align: center;">Status</th>
						<th style="text-align: center;">Detail / Delete</th>

					</tr>
				</thead>
				<!-- END HEAD -->
				<!-- START BODY -->
				<tbody>


					<c:forEach items="${feedback}" var="feedback" varStatus="status">

						<tr>
							<td style="width: 10%;">${feedback.idfeedback}</td>
							<td style="width: 30%;text-align: left;" title="${feedback.title}">
							<div style="display:block;text-overflow: ellipsis;max-width: 250px;overflow: hidden; white-space: nowrap;">
							${feedback.title}
							</div>
							</td>
							<td style="width: 30%;text-align: left;" title="${feedback.description}">
							<div style="display:block;text-overflow: ellipsis;max-width: 250px;overflow: hidden; white-space: nowrap;">
							${feedback.description}
							</div>
							</td>
							<td style="width: 10%;"><fmt:formatDate
									value="${feedback.timeCreate}" pattern=" dd/M/yyyy" /></td>
							<td style="width: 10%;"><c:if
									test="${feedback.status.toString() == 'R'}">
									<span class="label label-sm label-warning">Waiting for
										check</span>
								</c:if> <c:if test="${feedback.status.toString() == 'F'}">
									<span class="label label-sm label-success"
										OnMouseOver="${feedback.description}">Fixing</span>
								</c:if> <c:if test="${feedback.status.toString() == 'C'}">
									<span class="label label-sm label-primary">Fixed</span>
								</c:if> <c:if test="${feedback.status.toString() == 'D'}">
									<span class="label label-sm label-default">Deleted</span>
								</c:if></td>
							<td style="text-align: center;"><a
								class="btn btn-circle btn-icon-only green-haze btn-outline sbold uppercase fa fa-search"
								href="feedback_MyDetail?id_feed=${feedback.idfeedback}"></a><i
								class="btn btn-circle btn-icon-only red-mint btn-outline sbold uppercase fa fa-close"
								onclick="ajaxCalldel(${feedback.idfeedback});"></i></td>

						</tr>
					</c:forEach>
				</tbody>
				<!-- END BODY -->
			</table>
			</div>
			<!-- ============== End Table =============== -->

		</div>
	</div>

<script type="text/javascript">
	function ajaxCalldel(id,user_create,uniq) {
	
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
						url : "feedback_del",
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


<script type="text/javascript">
	function callDetail(id,uniq,fileuniq) {
		var id = id;
		var uniq = uniq;
		var fileuniq = fileuniq;
		

		
				if(id !=""){
					$.ajax({
						url : "feedback_MyDetail",
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
					})}};
		

	};
</script>

<script>
	function Alert(userid, enable) {
		if (enable == 1) {
			var massage = "enable"
		} else if (enable == 0) {
			var massage = "dissable"
		}
		toastr.success(userid + " " + massage + " success");
	}

	function myFunction() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			td1 = tr[i].getElementsByTagName("td")[1];
			td2 = tr[i].getElementsByTagName("td")[2];
			td3 = tr[i].getElementsByTagName("td")[3];
			td4 = tr[i].getElementsByTagName("td")[4];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
					if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
						if (td2.innerHTML.toUpperCase().indexOf(filter) > -1) {
							tr[i].style.display = "";
						} else {
							tr[i].style.display = "none";
							if (td3.innerHTML.toUpperCase().indexOf(filter) > -1) {
								tr[i].style.display = "";
							} else {
								tr[i].style.display = "none";
								if (td4.innerHTML.toUpperCase().indexOf(filter) > -1) {
									tr[i].style.display = "";
								}
							}
						}
					}
				}
			}
		}
	}
</script>


