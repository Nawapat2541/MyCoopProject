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

<div class="portlet light bordered">
	<div class="portlet-title" style="margin-left: 2%;">
		<div class="caption">
			<i class="fa fa-list font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Add Travel
				list</span> <span class="caption-helper font-red"> ${role.name} </span>
		</div>

	</div>

	<div class="portlet-body">
		<!-- BEGIN FORM-->
		<div class="panel-body">

			<form action="travel_Save" method="POST">

				<div class="form-group">
					<label for="recipient-name" class="control-label">Vehicle:</label>
					<input type="text" name="name" required class="form-control">
					<div class="form-control-focus"></div>
					<span class="help-block">Please fill the information</span>
				</div>
				
				<div class="form-group">
							<label for="recipient-name" class="control-label">Description:</label>
							<input type="text" name="description"class="form-control"  >
						</div>
				
				
				

				<!-- กำหนดวันที่ Time Create -->
				<div>
					<input type="hidden" name="time" id="time"
						value="<fmt:formatDate type = "time" 
         				value = "${now}" />">
				</div>

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
						<button type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
						<button type="reset" class="btn btn-info"><i class="fa fa-refresh"></i> Reset</button>
					</div>
				</div>
			</form>
		</div>


		<!-- END FORM-->
	</div>
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
