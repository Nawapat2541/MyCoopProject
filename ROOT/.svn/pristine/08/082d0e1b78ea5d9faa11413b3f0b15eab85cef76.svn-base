<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<%--  ${expTravelTypeList} --%>
<%-- ${expSearchList}  --%>
<%-- ${expenseList} --%>

<%--  ${expenseList}   --%>

<%--  ${user.id} --%>
<%--  ${expenseList}  --%>
<%-- ${expenseList.dtStar} --%>
<%-- ${user.id} --%>
<%-- ${expSearchList} --%>
<style>
p {
	margin: 20px 0 0;
}

b {
	color: blue;
}
</style>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-money font-red"></i> <span
				class="caption-subject font-red sbold uppercase">TimeSheet form</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<form action="timesheet_add" method="post">
			<input type="hidden" value="${user.id}" id="user" name="userCreate" class="user">
			<div class="form-group form-md-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Record
					date :</label>
				<div class="col-md-6">
					<input type="text" class="form-control" placeholder=""
						value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"  />"
						disabled>
					<div class="form-control-focus"></div>
					<span class="help-block">Please fill the information</span>
				</div>
			</div>
			<div class="form-group form-md-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Requester
					:</label>
				<div class="col-md-6">
					<select class="bs-select form-control select2me" name="name" disabled>
						<optgroup label="Enable">
							<c:forEach var="user" items="${userseq}">

								<c:if test="${user.enable == 1 }">
									<c:if test="${userSelect == nulll }">
										<option value="${user.id}"
											<c:if test="${fn:containsIgnoreCase(user.id,useradd)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
											- ${user.name}</option>
									</c:if>
									<c:if test="${userSelect != nulll }">
										<option value="${user.id}"
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
											<c:if test="${fn:containsIgnoreCase(user.id,useradd)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
											- ${user.name}</option>
									</c:if>
									<c:if test="${userSelect != nulll }">
										<option value="${user.id}"
											<c:if test="${fn:containsIgnoreCase(user.id,userSelect)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
											- ${user.name}</option>
									</c:if>
								</c:if>
							</c:forEach>
						</optgroup>
					</select>

				</div>
			</div>
			<div class="form-group form-md-line-input">
				<div class="caption caption col-md-3">Day of departure :</div>
				<div class="caption col-md-2 control-label">
					<div class="input-group input-large date-picker input-daterange"
						data-date-format="dd-mm-yyyy">
						<input name="dayOfDeparture" class="form-control" maxlength="13"
							id="date" required
							value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"  />"
							style="text-align: left;">
					</div>
				</div>
			</div>
			<div class="form-group form-md-line-input">
				<label class="col-md-3 control-label" for="form_control_1">From :</label>
					<div class="col-md-6">
						<div class="input-icon">
							<input type="text" name="startTime" id="startTime"
								class="form-control timepicker timepicker-24">
						</div>
					</div>
			</div>
			<div class="form-group form-md-line-input">
				<label class="col-md-3 control-label" for="form_control_1">To :</label>
					<div class="col-md-6">
						<div class="input-icon">
							<input type="text" name="endTime" id="endTime"
								class="form-control timepicker timepicker-24">
						</div>
					</div>
			</div>
			<input type="hidden" id="demo1" name="expense.description">
			<div class="form-group form-md-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Description :</label>
				<div class="col-md-6">
					<textarea style="word-break: break-all; white-space: normal;"
						maxlength="255" id="textarea1" class="form-control" rows="3" required></textarea>
					<div class="form-control-focus"></div>
					<span class="help-block">Please fill the information</span>
				</div>
			</div>
			<div class="form-group form-md-line-input">
				<br>
			</div>
			<div class="form-group form-md-line-input">
				<label class="col-md-3 control-label" for="form_control_1">Status :</label>
				<div class="col-md-6">
					<label for="form_control_1">Waitting for approve</label>
				</div>
			</div>
			<div style="margin-top: 2cm;">
				<div class="form-group form-md-line-input" align="center">
						<button type="button" id="demo" class="btn sbold blue" onclick="check()">
							<i class="fa fa-save" ></i>&nbsp;Save Timesheet
						</button>
						<button type="reset" class="btn red">
							<i class="fa fa-times-circle"></i>&nbsp;Cancel
						</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>$(document).ready(function () {
    $('.select2me').select2();
});</script>
<script>
	function check(){
	var des =	document.getElementById('textarea1').value;
	var start = document.getElementById('startTime').value+':00';
	var end = document.getElementById('endTime').value+':00';
	var w = document.getElementById('date').value;
	var datenew = w.split("-").reverse().join("-");
	var timestart = datenew + " " + start;
	var endtime = datenew +" "+end;
	var user = '${user.id}';
	var useradd = '${useradd}';
		if(des==null || des==""){
			swal("Error!", "Required! Description", "error");
		}else{
			$.ajax({
				url: "timesheet_add",
				method : "POST",
				type : "JSON",
				data :{
					"name" : user,
					"useradd" : useradd,
					"description":des,
					"timestart" : timestart,
					"endtime" : endtime
				},success : function(data) {
					 swal({ 
						  title: "Pass",
						   text: "Saved Succcess",
						    type: "success" 
						  },
						  function(){
							  window.location.href="timesheet_calendar?userId="+useradd;
						});
				}
				
			})
		}
	}
	function addOther() {
		var other = $("#goBy").val();
		var user = $("input.user").val();
		//alert("user= "+user+" text= "+othor);	
		if(other!=""){
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/add-other",
				data : {
					"other" : other,
					"user"	: user
					},
			}).done(function(data) {
				console.log(data);
				console.log($('#goBy').html(data));
				
				swal({
					  title: "SUCCESS",
					  type: "success",
					  showConfirmButton: true
					},function(isConfirm){
						  if (isConfirm) {
							  document.location ="travelexp_form?userId=${onlineUser.id}";
							  }
					})
				
			}).fail(function() {
				swal('Go by already exist,', 'Please select or change your Go by!',
				'error');					
			});
			
		}else{swal('Please!', 'Input your GO by',
		'warning');
		}
			
	}
	</script>
<script>
$( "input" )
  .change(function() {
    var $input = $( this );
    $( "p" ).html(     
     $input.is( ":checked" ) + "</b>" );
  })
  .change();
 
</script>
<script>$(document).ready(function () {
    var flag = '${flag}';
    if(flag != ""){
    	var date = '${date}';
    	document.getElementById("date").value = date ;
    }
});
</script>
<script>
    $("#demo15").click(function() {
    	
    	if($( "p:first" ).text() == "false" ) { 
    	swal("Please select request expense!")
		document.getElementById("demo15").type = "button";
    	 }
    	else
		{
			document.getElementById("demo15").type = "submit";
		}
  
    }); 
    
</script>
<script>
	$('form').submit(function() {
		$('#demo').attr("disabled", true);
	});
	
    function format2(n) {

        return   n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
        
      } 
</script>

<script>
	var from = $("#from").val();
	var to = $("#to").val();
	/* alert(from+"  "+to); */
	
	function validate(evt) {
		var theEvent = evt || window.event;
		var key = theEvent.keyCode || theEvent.which;
		key = String.fromCharCode(key);
		var regex = /[0-9]|\./;
		if (!regex.test(key)) {
			theEvent.returnValue = false;
			if (theEvent.preventDefault)
				theEvent.preventDefault();
		}
	}
</script>
<script>
	var from = $("#from").text();
	var to = $("#to").text();
	
	<c:set var="from"  value="from"/>
	<c:set var="to"  value="to"/>
</script>
<script>$(document).ready(function () {
	

     
    
  
    
});</script>
	
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>

<c:set var="now" value="<%=new java.util.Date()%>" />

