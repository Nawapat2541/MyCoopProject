<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<%-- <c:forEach var="eqi" items="${equipmentList}" varStatus="status">
	<c:if test="${eqi.equipmentId == param.Id }">${eqi.equipmentId}//${eqi.name}
	 --%>
<style>
a.disabled {
   pointer-events: none;
   cursor: default;
}

</style>

<div class="portlet light bordered">
	<div class="portlet-title" style="margin-left: 2%;">
		<div class="caption">
			<i class="fa fa-list font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Equipment
				list</span> <span class="caption-helper font-red"> <%-- ${role.name} --%>
			</span>
		</div>
		<perm:permission object="equipment.edit">
			<div class="actions right">
				<button type="button" id="addNew" class="btn green-jungle"
					onclick="nextStep()">
					<i class="fa fa-plus"></i>&nbsp;Add New
				</button>
				<a class="btn btn-circle btn-icon-only btn-default fullscreen"
					href="javascript:;" data-original-title="" title=""> </a>
			</div>
		</perm:permission>
	</div>
	<div class="portlet-body">
		<!-- BEGIN FORM-->
		
		<form class="form-inline margin-bottom-40 col-md-12"  action="borrowing" style="margin-left:0%;" method='post'>
			<div class="form-group form-lg-line-input col-md-6">
				<label class="control-label" for="form_control_1">Search : </label>
				<input type="text" class="form-control" name="name_search" style="width:80%;margin-left:3%;"  placeholder="Serial NO , Item NO , Name" value="${name_search}" >
			</div>
			<div class="form-group form-md-line-input col-md-2  ">
				<label class="control-label" for="form_control_1">Status : </label> 
				<select
					class="form-control  " name="s">
												<option value="All" 
								<c:if test="${status.equals('ALL')}"><c:out value="selected=selected"/></c:if>>ALL</option>
							<option value="A"
								<c:if test="${status.equals('A')}"><c:out value="selected=selected"/></c:if>>Available</option>
							<option value="B"
								<c:if test="${status.equals('B')}"><c:out value="selected=selected"/></c:if>>Borrowed</option>
					
				</select>
			</div>

			<div class="form-group form-md-line-input col-md-2 ">
				<label class="control-label" for="form_control_1">Type : </label> <select
					class="form-control " name="type">
							<option value="C" <c:if test="${status_type.equals('C')}"><c:out value="selected=true"/></c:if>>C</option>
							<option value="O" <c:if test="${status_type.equals('O')}"><c:out value="selected=selected"/></c:if>>Other</option>
							
				</select>
			</div>
<div class="form-group form-md-line-input col-md-1 ">
<button  type="submit" class="btn sbold blue">
					<i class="fa fa-search"></i> Search
				</button>
				</div>
		</form>
		
		<div class="portlet box white">
			<!-- <div class="portlet-title"> -->
			<div class="caption"></div>
			<div class="tools">
				<a href="javascript:;" class="collapse" data-original-title=""
					title=""> </a> <a href="#portlet-config" data-toggle="modal"
					class="config" data-original-title="" title=""> </a> <a
					href="javascript:;" class="reload" data-original-title="" title="">
				</a> <a href="javascript:;" class="remove" data-original-title=""
					title=""> </a>
			</div>
			<!-- </div> -->
			<div class="portlet-body" style="text-align: center;">
				<div class="table-responsive">
					<table
						class="table table-bordered table-striped table-condensed flip-content table-hover ">
						<thead>
							<tr>
								<th><center>#</center></th>
								<th><center>ITEM NO</center></th>
								<th><center>Serial NO</center></th>
								<th style="text-align: start">Equipment</th>
									<th><center>Detail</center></th>
								<th><center>Status</center></th>

								<perm:permission object="equipment.edit">
									<th><center>Function</center></th>
								</perm:permission>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="eqi" items="${xxx}" varStatus="status">
								<c:if test="${eqi.status != 'Z' }">
									<tr>
										<td>${status.count }</td>
										<td>${eqi.item_no}</td>
										<td>${eqi.serial_no}</td>
										<td style="text-align: -webkit-left;">${eqi.name}</td>
										<td style="text-align: -webkit-left;">${eqi.detail}</td>
										<td><c:if test="${eqi.status.toString()=='A'}">
												<span class="label label-sm label-primary">Available</span>
											</c:if> <c:if test="${eqi.status.toString()=='F'}">
												<span class="label label-sm label-default">Fixed</span>
											</c:if> <c:if test="${eqi.status.toString()=='C'}">
												<span class="label label-sm label-warning">Corrupted</span>
											</c:if> <c:if test="${eqi.status.toString()=='B'}">
												<span class="label label-sm label-success">Borrowed</span>

												<c:forEach var="borrow" items="${borrow_history}">
													<c:if test="${eqi.equipment_id == borrow.equipment_id}">
														<br>
														<a href="approve_id?borrowid=${borrow.borrow_id}"> <c:out
																value="${borrow.user_borrowid}" />
														</a>
													</c:if>
												</c:forEach>


											</c:if> <c:if test="${eqi.status.toString()=='D'}">
												<span class="label label-sm label-info">Waiting for
													Approved</span>
											</c:if> <c:if test="${eqi.status.toString()=='L'}">
												<span class="label label-sm label-danger">Lost</span>
											</c:if>
										<td>
											<c:if test="${eqi.status.toString()=='A'}">
										<a
											class="btn btn-circle btn-sm sbold green-turquoise"
											href="equipment_open?id_eqi=${eqi.equipment_id}"
											style="color: white;"><i class="fa fa-send"></i></a>
											</c:if>
											<c:if test="${eqi.status.toString()!='A'}">
										<a
											class="btn btn-circle btn-sm sbold white disabled"
											href=""
											style="color: white;"><i class="fa fa-send"></i></a>
											</c:if>
											
											<a class="btn btn-circle btn-sm sbold blue"
											href="product?product=${eqi.equipment_id}"
											style="color: white;"><i
												class="fa fa-clipboard"></i></a> <perm:permission
												object="equipment.edit">&nbsp;&nbsp;
										<i class="fa fa-close font-red sweet-${eqi.equipment_id}"
													onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);"></i></td>
										</perm:permission>
										<script>
document.querySelector('.sweet-${eqi.equipment_id}').onclick = function(){
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
        document.location = "delete_eqi?equipment_id=${eqi.equipment_id}";
      });
    }; 
</script>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- END FORM-->
	</div>
</div>
<script>
	function nextStep() {
		document.location = "addnew";
	}
	
	function product(){
		document.location = "product";
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
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
