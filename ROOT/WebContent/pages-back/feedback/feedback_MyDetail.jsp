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
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>

<div class="portlet light bordered">

	<div class="portlet light bordered">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-list font-red"></i> <span
					class="caption-subject font-red sbold uppercase">My Feedback</span>
			</div>
			<div class="actions">
				<a class="btn btn-circle btn-icon-only btn-default fullscreen"
					href="javascript:;" data-original-title="" title=""> </a>
			</div>
		</div>
		<div class="portlet-title">
			<div>
				<div class="form-group" style="text-align: left;" class="">
					<b>Track: &nbsp;# ${feedback.idfeedback}</b>
				</div>
				<div style="text-align: left;" class="">Title:
					&nbsp;${feedback.title}</div>
				<div style="text-align: left;">
					Create Date: &nbsp;
					<fmt:formatDate value="${feedback.time_create}"
						pattern="E dd-MMM-yyyy KK:mm:ss "></fmt:formatDate>
				</div>
				<div style="text-align: left;">
					Status : &nbsp;
					<c:if test="${feedback.status.toString() == 'R'}">
						<span class="label label-sm label-warning">Waiting for
							check</span>
					</c:if>
					<c:if test="${feedback.status.toString() == 'F'}">
						<span class="label label-sm label-success"
							OnMouseOver="${feedback.description}">Fixing</span>
					</c:if>
					<c:if test="${feedback.status.toString() == 'D'}">
						<span class="label label-sm label-primary">Fixed</span>
					</c:if>
					<c:if test="${feedback.status.toString() == 'C'}">
						<span class="label label-sm label-default">Deleted</span>
					</c:if>
					(update:
					<fmt:formatDate value="${feedback.time_update}"
						pattern="dd-MMM-y KK:mm:ss "></fmt:formatDate>
					)
				</div>

				<label class=" ">Description:&nbsp;</label>
				<div class="">
					<textarea name="textarea-input" class="form-control" rows="3"
						style="width: 80%;" disabled> ${feedback.description}</textarea>
				</div>


				<label class="control-label">Response:&nbsp;</label>
				<div class="">
					<textarea name="textarea-input" class="form-control" rows="3"
						style="width: 80%;" disabled>${feedback.description_res}</textarea>
				</div>

				<!-- End Table -->
				<div class="portlet-body flip-scroll">
					<table
						class="table table-bordered table-striped table-condensed flip-content table-hover "
						data-toggle="table" data-search="true" id="myTable">

						<thead>
							<tr>
								<th style="text-align: right;">#</th>
								<th style="text-align: left;">Name</th>
								<th style="text-align: center;">Date</th>
								<th style="text-align: center;">Size</th>
								<th style="text-align: right;">View</th>
								<th style="text-align: center;">Delete</th>

							</tr>
						</thead>

						<c:forEach var="fileupload" items="${myfileunique}"
							varStatus="status">
							<tr>

								<td style="text-align: right;">${status.count}&nbsp;</td>
								<td style="text-align: left;"><a href="${fileupload.path}"
									target="_blank">${fileupload.name}</a></td>
								<td style="text-align: center;"><fmt:formatDate
										value="${fileupload.timeCreate}" pattern=" dd-MM-yyyy HH:mm" /></td>
								<%-- <td>${fileupload.timeCreate}</td> --%>
								<td style="text-align: center;">${fileupload.size}&nbsp;</td>
								<td style="text-align: center;"><c:choose>
										<c:when
											test="${fileupload.type.toString()=='.png' || fileupload.type.toString()=='.jpg' || fileupload.type.toString()=='.gif'}">
											<a class="btn btn-circle btn-sm sbold yellow" target="_blank"
												href="${fileupload.path}"
												title="file type ${fileupload.type}" style="color: white;">
												<i class="fa fa-file-image-o"></i>
											</a>
										</c:when>
										<c:otherwise>
											<a class="btn btn-circle btn-sm sbold blue"
												href="${fileupload.path}" target="_blank"
												title="file type ${fileupload.type}" style="color: white;">
												<i class="fa fa-clipboard"></i>
											</a>
										</c:otherwise>
									</c:choose></td>
								<td style="text-align: center;"><a title="Delete"
									class="btn btn-circle btn-icon-only red-mint btn-outline sbold uppercase"
									onclick="ajaxCalldel(${fileupload.fileIdfb});"><i
										class="fa fa-close"></i></a></td>

							</tr>

						</c:forEach>
						</tbody>
					</table>
					<!-- End Table -->
				</div>
			</div>
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
						url : "delete_filejane",
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