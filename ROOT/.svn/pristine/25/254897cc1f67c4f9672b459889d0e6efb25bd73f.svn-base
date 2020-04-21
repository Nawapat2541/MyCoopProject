<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%-- <c:if test="${bor. eq expSearchList.dt_by}"> ${bor.name} </c:if> --%>
<%-- ${user} --%>
<%-- ${onlineUser.id} --%>
<%-- ${selectchect} --%>
<%-- ${select} --%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="glyphicon glyphicon-shopping-cart font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Borrow
				Equipment </span> <span class="caption-helper font-red">${role.name}</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="equipment" class="form-horizontal" method="post"
			autocomplete="off">
			<div class="input-group form-md-line-input"
				id="blockui_sample_1_portlet_body">

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Today :</label>
					<div class="col-md-2">
						<input type="text" class="form-control"
							value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy H:mm"  />"
							disabled>	
					</div>
				</div>

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Name : </label>
					<div class="col-md-5 ">
						<select class="form-control select2me" name="userborrowId">
							<optgroup label="Enable">
					<c:forEach var="user" items="${userseq}">
					  
					  <c:if test="${user.enable == 1 }">
					<c:if test="${userSelect == nulll }">
						<option value="${user.id}"
							<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
							- ${user.name}</option></c:if>
							<c:if test="${userSelect != nulll }">
							<option value="${userSelect}"
							<c:if test="${fn:containsIgnoreCase(user.id,userSelect)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
							- ${user.name}</option>
							</c:if>
							</c:if>
						</c:forEach>
						 </optgroup>
						 <optgroup label="Disable">
					<c:forEach var="user" items="${userseq}">
					  
					  <c:if test="${user.enable == 0 }">
					<c:if test="${userSelect == nulll }">
						<option value="${user.id}"
							<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if> >${user.department_id}
							- ${user.name}</option></c:if>
							<c:if test="${userSelect != nulll }">
							<option value="${userSelect}"
							<c:if test="${fn:containsIgnoreCase(user.id,userSelect)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
							- ${user.name}</option>
							</c:if>
							</c:if>
						</c:forEach>
						 </optgroup>
						</select>
					</div>
				</div>

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Select : </label>
					<div class="col-md-5 ">
						<select class="form-control select2me" name="equipmentid" id="mylist">
							<c:forEach items="${select}" var="underling_view" varStatus="status">
							
									<c:if test="${underling_view.status == 'A' && underling_view.amount == '1'}">
										<option value="${underling_view.equipment_id}" id="test"  <c:if test="${underling_view.equipment_id == eqi_id}"><c:out value="selected=selected"/></c:if>  >${underling_view.item_no}
											- ${underling_view.name}</option>
									</c:if>

							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group form-lg-line-input col-md-12">
				<label class="col-md-2 control-label" for="form_control_1"
					style="text-align: right; margin-right: 3%">Date Start :</label>
					<div class="col-md-5">
                       <div class="input-group input-large date-picker input-daterange" data-date="10/11/2012" data-date-format="dd/mm/yyyy">
                          <input type="text"  class="form-control" name="datestart" id="start" onkeypress='return false'>
                             <span class="input-group-addon"> To </span>
                                   <input type="text" class="form-control" name="dateend"> </div><span class="help-block">
					</span>
                                                    <!-- /input-group -->
                              <span class="help-block"> </span>
                          </div>
				</div>
				
                          
                          



				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Reason :</label>
					<div class="col-md-5">
						<input name="reason" id="demo11" type="text" class="form-control"
							maxlength="128" required> 
					</div>
				</div>

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Location :</label>
					<div class="col-md-5">
						<input name="location" type="text" class="form-control"
							maxlength="128" id="demo12" required> 
					</div>
				</div>

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Contact address
						:</label>
					<div class="col-md-5">
						<input name="contract" type="text" class="form-control"
							maxlength="128" id="demo13" required> 
					</div>
				</div>

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-2 control-label" for="form_control_1"
						style="text-align: right; margin-right: 3%">Remark :</label>
					<div class="col-md-5">
						<input name="remarks" type="text" class="form-control"
							maxlength="128"> 
					</div>
				</div>

				<div class="row ">
					<div class="col-md-12" style="text-align: center;">
						<button type="submit" id="demo15" class="btn sbold blue "
							onclick="save();">
							<i class="fa fa-save"></i>&nbsp;Save
						</button>
						<!-- <a href="javascript:;" class="btn sbold blue"
							id="blockui_sample_1_1"><i class="fa fa-save"></i>&nbsp; Save</a> -->
						<button type="reset" class="btn red">
							<i class="fa fa-times-circle"></i>&nbsp;Cancel
						</button>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>
<script>
	$('form').submit(function(e) {
		$('#demo15').attr("disabled", true);
	});
</script>

<!-- <script>
    $("#demo15").click(function() {
    	var date = $("#start").val().length;;
    	var reson = $("#demo11").val().length;;
    	var location = $("#demo12").val().length;;
    	var contact = $("#demo13").val().length;;
    	
    	
    	if (date != 0 && reson != 0 && location != 0 && contact != 0 ){
        $.blockUI({ css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        } }); 
   
        setTimeout($.unblockUI, 2000); 
   
    	}
    }); 
    
</script> -->
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	function approved() {
		document.location = "leave_check_approve";
	}
</script>

<script>
	$('.test').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8) {
			return false;
		}
	});
</script>
