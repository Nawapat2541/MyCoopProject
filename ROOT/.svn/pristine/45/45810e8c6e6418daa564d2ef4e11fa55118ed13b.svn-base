<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${bean.date}" pattern="dd-MM-yyyy" />

<!-- ${depart} -->

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-paperclip font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Edit Travel</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<form action="travel_update" method="POST">
			<div class="form-group">


				<!-- Start Hidden ID เอาไว้ไม่ใช้โชว์ใน view -->
				<input type="hidden" name="id" value="${depart.expTravelTypeId}">
				<!-- End Hidden -->

				<label for="recipient-name"class="control-label">Vehicle:</label> <input type="text"
					name="name" value="${depart.name}" required class="form-control">
				<div class="form-group">
					<label for="recipient-name" class="control-label">Description:</label>
					<input type="text" name="deptdes" value="${depart.description}"
						 class="form-control">
				</div>
				
				
				
				
				<!-- กำหนดวันที่ Time Create -->
				
					<input type="hidden" name="time" id="time" value="<fmt:formatDate type = "time" 
         					value = "${now}" />">
			

				<div class="form-group form-md-line-input">

					<div class="col-md-2">
						<input name="date" id="date"
							value="<fmt:formatDate value="${now}"  type = "both" 
        timeStyle = "medium" pattern="dd-MM-yyyy "  />"
							onchange="datechenge()"
							class="form-control input-lg form-control-inline input-medium date-picker test"
							size="9" type="hidden" onkeypress='return false'>
					</div>
					<!-- End Time Create -->	
					<div class="modal-footer">
						<button type="submit" class="btn btn-success"><i class="fa fa-save"></i> Update</button>
						<button type="reset" class="btn btn-info"><i class="fa fa-refresh"></i> Reset</button>
					</div>
					</div>
		</form>
	</div>



	<!-- END FORM-->
</div>

<script>
function datechenge() {
	var fulldate = "${fulldate}".trim();
	var Userdate = $("#mydate").val();
	if(fulldate != Userdate){
		$("#detail").show();
		$("#labeldetail").show();
	}else{
		$("#detail").hide();
		$("#labeldetail").hide();
	}		
}

$(document).ready(function() {

	var value = "${flag}";
	if (value == 1) {

		swal('Please Check!', ' Leave type ID  is duplicate', 'warning');
	}
});
</script>





<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
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
