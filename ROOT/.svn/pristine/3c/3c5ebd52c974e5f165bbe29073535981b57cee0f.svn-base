<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class=" fa fa-users font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Borrower
				History</span>
		</div>
		<perm:permission object="role.edit">
			<div class="actions right">
				<a class="btn btn-circle btn-icon-only btn-default fullscreen"
					href="javascript:;" data-original-title="" title=""> </a>
			</div>
		</perm:permission>
	</div>
	<div class="portlet-body  flip-scroll">
		<div class="row">
			<div class="col-md-3">
				<div class="form-group">
					<label>Item No</label>
					<div class="input-group">
						<span class="input-group-addon"> <i class="fa fa-laptop"></i>
						</span> <input class="form-control" type="text" value="${borrowList[0].item_no}" readonly>
					</div>
				</div>
			</div>
		</div>
		<div class="table-scrollable">
			<table
				class="table table-bordered table-striped table-condensed flip-content table-hover">
				<thead>
					<tr>
						<th><center>#</center></th>
						<th>Equipment Id</th>
						<th>Borrower</th>
						<th style="text-align: center;">Start Date</th>
						<th style="text-align: center;">End Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="borrower" items="${borrowList}" varStatus="status">
						<tr>
							<td><center>${status.count}<center></td>
							<td style="word-break: break-all; white-space: normal;">${borrower.equipment_id}</td>
							<td style="word-break: break-all; white-space: normal;">${borrower.user_borrowid}</td>
							<td align='center'><fmt:formatDate value="${borrower.date_start}" pattern="dd/MMM/yyyy HH:mm" /></td>
							<td align='center'><fmt:formatDate value="${borrower.date_end}" pattern="dd/MMM/yyyy HH:mm" /></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- END FORM-->
<script>
	$(document).ready(function() {

		var url = new URL(window.location.href);
		var id = url.searchParams.get("id");
		$equipmentId = $_GET['id'];

	});
</script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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