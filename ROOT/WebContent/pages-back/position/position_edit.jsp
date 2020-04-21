
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.js"
	type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	type="text/javascript"></script>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"
	type="text/javascript"></script>

<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-paperclip font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Position Edit</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<form action="updatePosition" method="POST">
		
		<!-- Start Hidden ID เอาไว้ไม่ใช้โชว์ใน view -->
		<input type="hidden" name="positionId" value="${positionList.positionId}">
		<!-- End Hidden -->

		
			<div class="form-group form-lg-line-input col-md-12">
				<label class="control-label col-md-3" id="namelabel">DescriptionID :</label>
				<div class="col-md-4">
					<select class="bs-select form-control" name="departmentId" id="departmentId">
							<c:forEach var="department" items="${departmentList}">
								<option value="${department.id}"
									<c:if test="${positionList.departmentId eq department.id }"> selected </c:if>>${department.id}</option>
							</c:forEach>
						</select>
				</div>
			</div>
			
			
			
			
			<div class="form-group form-lg-line-input col-md-12">
				<label class="control-label col-md-3" id="namelabel">Name :
				</label>
				<div class="col-md-4">
					<input type="text" name="name" class="form-control" id="name"
						maxlength="240" value="${positionList.name}" required> <input
						type="hidden" name="id_date" class="form-control" required>
					<div class="form-control-focus"></div>
<!-- 					<span class="help-block">Please fill the information</span> -->
				</div>
			</div>
			
			<div class="form-group form-lg-line-input col-md-12">
				<label class="control-label col-md-3" id="namelabel">Description :
				</label>
				<div class="col-md-4">
					<input type="text" name="description" class="form-control" id="description"
						maxlength="240" value="${positionList.description}" required> <input
						type="hidden" name="id_date" class="form-control" required>
					<div class="form-control-focus"></div>
<!-- 					<span class="help-block">Please fill the information</span> -->
				</div>
			</div>

			<div class="form-group form-lg-line-input col-md-12">
				<label class="control-label col-md-3" id="namelabel"></label>
				<div class="col-md-4">
					<center>
						<button type="submit" class="btn blue">
							<i class="fa fa-save"></i> Update
						</button>
						<button type="button" class="btn red" onclick="ss()">
							<i class="fa fa-close"></i> Cancel
						</button>
					</center>
				</div>
			</div>

			<!-- กำหนดวันที่ Time Create -->
			<div>
				<input type="hidden" name="time" id="time"
					class="form-control input-lg timepicker timepicker-24 test"
					value="${time}" data-time-format=" HH:mm" style="width: 200px;"
					onclick="timechenge()" onkeypress='return false'>
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

			</div>
	</div>

		<div class="row ">
			<div class="col-md-12"></div>
		</div>

	</form>
</div>




</div>
<script>
	function datechenge() {
		var fulldate = "${fulldate}".trim();//??????????????
		var Userdate = $("#mydate").val();//???????????
		if (fulldate != Userdate) {
			$("#detail").show();
			$("#labeldetail").show();
		} else {
			$("#detail").hide();
			$("#labeldetail").hide();
		}
	}
</script>

<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
