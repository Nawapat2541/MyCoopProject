<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="portlet light bordered">
	<div class="portlet-title" style="margin-left: 2%;">
		<div class="caption">
			<i class="fa fa-list font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Edit Leave Type list</span>
			<span class="caption-helper font-red"> ${role.name}
			</span>
		</div>
		
	</div>
	
	<div class="portlet-body">
		<!-- BEGIN FORM-->
		<div class="panel-body">
				
					<form action="Updateleavetype" method="POST">
					<div><input type="hidden" name="nameUser" value="${logonUser}"></div>
					<c:forEach var="leave_type" items="${leavetypelist}">
								<tr >
						<div class="form-group">
							<!-- <label for="recipient-name" class="control-label">ID Leave type:</label> -->
							
							<input type="hidden" name="leave_type_id" value="${leave_type.leave_type_id}" required class="form-control"  >
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">Name Leave type:</label>
							<input type="text" name="leave_type_name" value="${leave_type.leave_type_name}" required class="form-control"  >
							<input type="hidden" name="leave_type_id" value="${leave_type.leave_type_id}" required class="form-control"  >
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">Description:</label>
							<input type="text" name="leave_description" value="${leave_type.description}"   class="form-control"  >
						</div>
						<div class="form-group">
							 
							<%-- <input type="text" name="leave_user_update"  value="${leave_type.leave_user_update}" required class="form-control"  > --%>
							<input type="hidden" name="leave_user_create"  value="${leave_type.user_create}" required class="form-control"  >
							
						</div>
						<div class="form-group">
							
							<input type="hidden" name="leave_time_create"  value="${leave_type.time_create}" required class="form-control"  >
							<input type="hidden" name="time" id="time" class="form-control input-lg timepicker timepicker-24 test" value="${time}" data-time-format=" HH:mm" style="width: 200px;" onclick="timechenge()" onkeypress='return false'>
						</div>
						<div class="form-group form-md-line-input">
				
					<div class="col-md-4">
					<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy" >
							<input type="hidden" class="form-control" name="leave_time_update"  value="<fmt:formatDate value="${now}"  type = "both" 
        							timeStyle = "medium" pattern="dd-MM-yyyy "  />" 
									data-date-format="dd-mm-yyyy"  id="leave_time_create" >  

							</div>
					
					</div>
					
					
				</div>
		
											
						<div class="modal-footer">
							<button type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
							<button type="reset"  class="btn btn-info" ><i class="fa fa-refresh"></i> Reset</button>
						</div>
						
						</tr>
							</c:forEach>
				</form>
			</div>
		
		

		<!-- END FORM-->
	</div>
</div>
<script>

$(document).ready(function() {

	var value = "${flag}";
	if (value == 1) {

		swal('Please Check!', ' Leave type name  is duplicate', 'warning');
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
