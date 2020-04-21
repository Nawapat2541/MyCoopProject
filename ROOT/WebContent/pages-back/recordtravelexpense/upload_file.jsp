<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%-- ${filepathList} --%>
<%--  ${fileuploadList}    --%>
<%-- ${fileuploadList} --%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-upload font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Upload File</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="upload_files" class="form-horizontal" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="userUploadId" value="${user.id}">
			<input type="hidden" name="fileUploadSize" value="${size}" id="size">
			<div class="form-body">
				<div class="form-row">
					<div class="form-group form-md-line-input">
						<label class="control-label col-md-3">user :</label>
						<div class="col-md-3">
							<select class="form-control select2me" name="userUploadCreate">
								<optgroup label="Enable">
									<c:forEach var="userList" items="${userseq}">
										<c:if test="${userList.enable == 1 }">
											<c:if test="${userSelect == nulll }">
												<option value="${userList.id}" id="${userList.id}"
													<c:if test="${fn:containsIgnoreCase(userList.id,user.id)}"><c:out value="selected=selected"/></c:if>>${userList.department_id}
													- ${userList.name}</option>
											</c:if>
											<c:if test="${userSelect != nulll }">
												<option value="${userSelect}" id="${userList.id}"
													<c:if test="${fn:containsIgnoreCase(userList.id,user.id)}"><c:out value="selected=selected"/></c:if>>${userList.department_id}
													- ${userList.name}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</optgroup>
								<optgroup label="Disable">
									<c:forEach var="userList" items="${userseq}">
										<c:if test="${userList.enable == 0 }">
											<c:if test="${userSelect == nulll }">
												<option value="${userList.id}" id="${userList.id}"
													<c:if test="${fn:containsIgnoreCase(userList.id,user.id)}"><c:out value="selected=selected"/></c:if>>${userList.department_id}
													- ${userList.name}</option>
											</c:if>
											<c:if test="${userSelect != nulll }">
												<option value="${userSelect}" id="${userList.id}"
													<c:if test="${fn:containsIgnoreCase(userList.id,user.id)}"><c:out value="selected=selected"/></c:if>>${userList.department_id}
													- ${userList.name}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</optgroup>
							</select>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<div class="control-label col-md-3">File Upload :</div>
						<div class="col-md-3 text-center">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-new thumbnail"
									style="width: 200px; height: 150px;">
									<img
										src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
										alt="cc">
								</div>
								<div class="fileinput-preview fileinput-exists thumbnail"
									style="max-width: 200px; max-height: 150px; line-height: 10px;"></div>
								<div>
									<span class="btn default btn-file"> <span
										class="fileinput-new"><i class="fa fa-picture-o"></i> Select File</span> <span
										class="fileinput-exists"> Change </span> <input type="hidden"
										value="" name="..."><input type="file"
										name="fileUpload" id="myFile">
									</span> <a href="javascript:;" class="btn red fileinput-exists"
										data-dismiss="fileinput"> Remove </a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions action right">
				<div class="row">
					<div class="col-md-12">
						<center>
							<button type="submit" class="btn blue">
								<i class="fa fa-save"></i>&nbsp;Upload
							</button>
							<button type="reset" class="btn red">
								<i class="fa fa-close"></i>&nbsp;Cancel
							</button>
							<!-- <img alt="" src="upload/user/17_482611.jpg"> -->
						</center>
					</div>
				</div>
			</div>
		</form>

		<!-- END FORM-->
	</div>
</div>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-upload font-red"></i> <span
				class="caption-subject font-red sbold uppercase">My File</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div>
		<div id="div_input" style="text-align: right;">
			Search:&nbsp;<input type="text" id="myInput" onkeyup="myFunction()"
				placeholder="Search for names.." title="Type in a name"
				class="form-control input-sm input-small input-inline">
		</div>
	</div>
	<div class="portlet-body flip-scroll">
		<table
			class="table table-bordered table-striped table-condensed flip-content table-hover"
			data-toggle="table" data-search="true" id="myTable">

			<thead>
				<tr>
					<th style="text-align: right;">#</th>
					<th style="text-align: center;">Name</th>
					<th style="text-align: center;">Date</th>
					<th style="text-align: center;">Type</th>
					<th style="text-align: center;">Size</th>
					<th style="text-align: center;">Delete</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="fileupload" items="${fileuploadList}"
					varStatus="status">
					<tr>
						<td style="text-align: right;">${status.count}&nbsp;</td>
						<td style="text-align: left;"><a href="${fileupload.path}"
							target="_blank">${fileupload.name}${fileupload.type}&nbsp;</a></td>
						<td style="text-align: center;"><fmt:formatDate
								value="${fileupload.timeCreate}" pattern=" dd-MM-yyyy HH:mm" /></td>
						<%-- <td>${fileupload.timeCreate}</td> --%>
						<td style="text-align: center;">${fileupload.type}&nbsp;</td>
						<td style="text-align: right;">${fileupload.size}&nbsp;</td>
						<td style="text-align: center;"><i
							class="fa fa-trash font-red sweet-${fileupload.fileId}" title="Delete"
							onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);"></i>
						</td>
						<script>
document.querySelector('.sweet-${fileupload.fileId}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "You will be deleting this id!",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "delete_file?fileId=${fileupload.fileId}";
      });
    }; 
</script>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- End Table -->
	</div>
</div>
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
<script>
$('#myFile').bind('change', function() {
	  var fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
	  fSize = this.files[0].size; i=0;while(fSize>900){fSize/=1024;i++;}
	  var size_n = (Math.round(fSize*100)/100);
	  var size = size_n+' '+fSExt[i];
	  $('#size').val(size);
	 /*  <c:set var="size"  value="size"/> */
	  /*  alert(size);  */
	});
</script>
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