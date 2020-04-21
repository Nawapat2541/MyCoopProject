<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script
	src="../assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>

<script
	src="../assets/global/plugins/bootstrap/js/bootstrap.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="../assets/global/plugins/moment.min.js"
		type="text/javascript"></script>
		
<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-paperclip font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Holiday
				application form</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<form action="AddHoliday" method="POST" onsubmit="return myVal_form()">
			<div class="portlet-body">
				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-3 control-label" for="form_control_1">Input
						Date :</label>
					<div class="col-md-4">
						<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy">

							<input type="text" class="form-control cannot" name="Date-Start"
								id="date_s" data-date-format="dd-mm-yyyy">
<!-- 								 <span class="help-block">Pick Date Start</span>  -->
								 <span class="input-group-addon input-sm"> to </span> 
								 <input type="text" class="form-control cannot" name="Date-End" id="date_e"
								data-date-format="dd-mm-yyyy"> 
<!-- 								<span class="help-block">Pick Date End</span> -->
						</div>
					</div>
				</div>
				<div class="form-group form-lg-line-input col-md-12">
					<label class="control-label col-md-3" id="namelabel">Name :
					</label>
					<div class="col-md-4">
						<input type="text" name="name" class="form-control" id="nameid"
							maxlength="240" required>
						<div class="form-control-focus"></div>
<!-- 						<span class="help-block">Please fill the information</span> -->
					</div>
				</div>

				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-3 control-label" for="form_control_1">Description
						:</label>
					<div class="col-md-4">
						<textarea style="word-break: break-all; white-space: normal;"
							maxlength="1024" class="form-control" rows="6" id="demo2" name="description"></textarea>
						<div class="form-control-focus"></div>
<!-- 						<span class="help-block">Please fill the information</span> -->
					</div>
				</div>
				<input type="hidden" id="demo3" >
				<div class="form-group form-lg-line-input col-md-12">
					<label class="col-md-3 control-label" for="form_control_1"></label>
					<div class="col-md-4">
						<center>
							<button type="submit" class="btn blue">
								<i class="fa fa-save"></i> Save
							</button>
							<button type="reset" class="btn red" onclick="ss()">
								<i class="fa fa-close"></i> Cancel
							</button>
						</center>
					</div>
				</div>
			</div>

				<div class="row ">
					<div class="col-md-12">
						
					</div>
				</div>

		</form>
	</div>

		<input type="hidden" value="${date}" id="date1">
		<input type="hidden" value="${date}" id="date2">
		
</div>

<script>
var flag_loop = 0 ;
	function ss() { // ฟังก์ชั่น reset
		var value = "${flag_form}";
		if (value != 1) {
		document.location = "holiday_calendar?date=${date}";
		}else{
		document.location = "holiday_list";
		}
	}
	function myVal_form(){
		var el = document.getElementById("demo2");
		var val = el.value.replace(/\s/g, "");
		document.getElementById("demo3").value = val;
		return true;
	}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		var value = "${flag}";
		if (value == 1) {

			swal('Please!', 'Check Date Duplicate', 'warning');
			document.getElementById("date_s").value = s ;
			document.getElementById("date_e").value = s1 ;
		}
	});
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	 var dd,mm,yy,s;
	 var flag1 = 0 ;
	 s = document.getElementById("date1").value ;
	 s1 = document.getElementById("date2").value ;

	var value = "${flag}";
	if (value == 1) {	
		swal('Please!', 'Check Start Date Duplicate', 'warning');
	}
    if(flag1 == 0){
    
document.getElementById("date_s").value = s ;
document.getElementById("date_e").value = s1 ;
    }
	flag1 = 1;
});
function covert(x){
	var dd,mm,yy;
	
	yy = x.substring(0,4);

	mm = x.substring(5,7);

	dd = x.substring(8,10);
	
	
	var date = dd +"-"+ mm+ "-"+ yy ;

	
	
	return date;
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