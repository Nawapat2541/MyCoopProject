<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />

 <div class="portlet light bordered">
	<div class="portlet-title" style="text-align: center;">
		<div class="caption" style="margin-right: 2%;">
			<i class="fa fa-balance-scale font-red"></i> <span
				class="caption-subject font-red sbold uppercase"> Borrow
				Detail</span>
		</div>
		
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<c:forEach var="eqi" items="${list}" >
	
		<div class="portlet-body form">
			<!-- BEGIN FORM-->
			<form action="Update_Borrow_Detail" class="form-horizontal" method="get"
				autocomplete="off">
				<div class="input-group form-lg-line-input col-md-12 " style="margin-right:5%;">

					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Today
							:</label>
						<div class="col-md-3">
							<input type="text" class="form-control"
								value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy H:mm"  />"
								disabled>
						</div>
					</div>

					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-2 control-label" for="form_control_1"
							style="text-align: right; margin-right: 3%">Name : </label>
						<div class="col-md-3 ">
							<input value="${eqi.user_borrowid }" type="text" class="form-control" required
								readonly>
						</div>
					</div>

					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-2 control-label" for="form_control_1"
							style="text-align: right; margin-right: 3%" >Select : </label>
						<div class="col-md-3 ">
							<input value="${eqi.name}" type="text" class="form-control"
								 readonly>
						</div>
					</div>

		<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1" style="text-align: right; margin-right: 3%">Date Start
						:</label>
					<div class="col-md-3">
						<div class="input-group input-large date-picker input-daterange" 
							data-date-format="dd-mm-yyyy">
							<input  type="text" class="form-control cannot" 
								aria-required="true" value="${eqi.date_start }"  readonly>
							<span class="input-group-addon"> To :</span> 
							
							<input type="text"  
							name="date_end"  value="${eqi.date_end}"
								class="form-control cannot" 
								aria-required="true">
						</div>
					</div>			
					</div>

					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-2 control-label" for="form_control_1"
							style="text-align: right; margin-right: 3%">Reason :</label>
						<div class="col-md-6">
							<input value="${eqi.reason}" type="text" class="form-control" name="reason">
						</div>
					</div>

					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-2 control-label" for="form_control_1"
							style="text-align: right; margin-right: 3%">Location :</label>
						<div class="col-md-6">
							<input value="${eqi.location }" type="text" class="form-control" name="location"> 
						</div>
					</div>

					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-2 control-label" for="form_control_1"
							style="text-align: right; margin-right: 3%">Contact address
							:</label>
						<div class="col-md-6">
							<input value="${eqi.contact_addr }" type="text" class="form-control" name="contact_address">
						</div>
					</div>

					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-2 control-label" for="form_control_1"
							style="text-align: right; margin-right: 3%">Remark :</label>
						<div class="col-md-6">
							<input value="${eqi.remark }" type="text" class="form-control" name="remark">
						</div>
					</div>


 <!--  Update Status Borrow -->
 
 <div class="form-group form-lg-line-input col-md-12">
	<label class="col-md-2 control-label" for="form_control_1 " style="text-align: right; margin-right: 3%">Status
		:</label>
	<div class="col-md-3">
		
			<select class="bs-select form-control" name="status_borrow"> 
					<option  value="W"
						<c:if test="${eqi.statusborrow.toString() eq 'W'}"><c:out value="selected=selected"/></c:if>>Waiting for approve</option>
					<option  value="B"
						<c:if test="${eqi.statusborrow.toString() eq 'B'}"><c:out value="selected=selected"/></c:if>>Borrowing</option>
					<option  value="R"
						<c:if test="${eqi.statusborrow.toString() eq 'R'}"><c:out value="selected=selected"/></c:if>>Return</option>
							<option  value="C"
						<c:if test="${eqi.statusborrow.toString() eq 'C' || eqi.statusborrow.toString() eq 'U'}"><c:out value="selected=selected"/></c:if>>Cancel</option>
				
			</select>
	
	</div>

</div>


				
				<input type="hidden" name="id_borrow" value="${eqi.borrow_id}">
				<input type="hidden" name="id_equipment" value="${eqi.equipmentid}">
			<div class="form-group form-lg-line-input col-md-12">	
			<div class="col-md-6">
				<center>
				<button type="submit" class="btn sbold blue">
								<i class="glyphicon glyphicon-search"></i>&nbsp; Update
							</button>
							</center>
						</div>	
						</div>
							</div>
			</form>
			<!-- END FORM-->
		</div>
		
	</c:forEach>
</div> 
