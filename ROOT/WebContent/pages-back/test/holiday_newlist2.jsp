<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="portlet light bordered">
	<div class="portlet-title" style="margin-left: 2%;">
		<div class="caption">
			<i class="fa fa-list font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Holiday list</span>
			<span class="caption-helper font-red"> <%-- ${role.name} --%>
			</span>
		</div>
		<div class="actions right">
			<button type="button" class="btn green-jungle" onclick="add()"
				style="margin-right: 13px; padding: 8px;">
				<i class="fa fa-plus"></i>&nbsp;Add Holiday
			</button>
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<!-- BEGIN FORM-->
		<div>
			<form action="SearchHoliday2" class="form-horizontal" method="post">
				<!-- <form action="search-avi" class="form-horizontal" method="post"> -->
				<div class="form-group form-lg-line-input">
					<label class="col-md-3 control-label" for="form_control_1">Search :</label>
					<div class="col-md-2">
						<select class="bs-select form-control" name="myselect" onchange="this.form.submit()">
							<option disabled="disabled">Choose Year</option>
							<option value="2" id="all">All</option>
							<!-- <option value="<fmt:formatDate type="date" value="${now}" pattern="yyyy" />"><fmt:formatDate type="date" value="${now}" pattern="yyyy" /></option> -->
							<c:forEach var="holiday_year" items="${holidayList_year}">
								<option value="${holiday_year}" id="${holiday_year}">${holiday_year}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="text-center">
						<a href="holiday_calendar"
							class="icon-btn btn red-pink btn-outline sbold "> <i class="fa fa-calendar"></i>
							<div style="color: #E08283; font-size: 13px;">Calendar</div> <span class="badge badge-info"> new </span>
						</a>
						<a href="#" id="mySelect"
							class="icon-btn btn green btn-outline sbold "> <i class="fa fa-print"></i>
							<div style="color: #32C5D2; font-size: 13px;">Print</div>
						</a>
					</div>
					
				</div>
			</form>
		</div>
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
					<table class="table table-bordered table-striped table-condensed flip-content table-hover">
						<thead >
							<tr>
								<th style="width: 10%;"><center>Start Date</center></th>
								<th style="width: 10%;"><center>End Date</center></th>
								<th style="text-align: center; width: 20%;">Name</th>
								<th style="text-align: center; width: 50%;">Description</th>
								<th style="width: 5%;"><center>Detail</center></th>
								<th style="width: 5%;"><center>Delete</center></th>
							</tr>
						</thead>
						<tbody  >
							<c:forEach var="holiday" items="${holidayList}">
								<tr >
									<c:choose>
										<c:when test="${holiday.end_date != holiday.start_date}">
											<td style="text-align: -webkit-right; padding-left:10px;padding-top:10px;">
												<fmt:formatDate value="${holiday.start_date}"
													pattern="dd-MM-yyyy"></fmt:formatDate>
											</td>

											<td style="text-align: -webkit-right; padding-left: 10px;padding-top:10px;"><fmt:formatDate
													value="${holiday.end_date}" pattern="dd-MM-yyyy"></fmt:formatDate></td>
										</c:when>

										<c:otherwise>
											<td 
												style="text-align: -webkit-right; padding-left:10px;padding-top:10px;">
												<fmt:formatDate value="${holiday.start_date}"
													pattern="dd-MM-yyyy"></fmt:formatDate>
											</td>
											<td></td>
										</c:otherwise>

									</c:choose>
									<td style="padding-top:10px;">${holiday.head}</td>
									<td style="text-align: -webkit-left; padding-left: 10px;padding-top:10px; white-space: normal; max-width:200px;word-wrap:break-word;">${holiday.description}</td>
									<td style="padding-top:10px;">
										<div align="center">
											<a class="btn btn-circle btn-sm sbold blue" title="Detail" href="holiday_edit?id=${holiday.id_date}&flag=1">
											<i class="fa fa-clipboard"></i></a>
										</div>
									</td>
									<td style="text-align: center; padding-top: 10px;">
										<button
											class="btn btn-circle btn-sm sbold red sweet-${holiday.id_date}" title="Delete"
											onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);">
											<i class="fa fa-trash"></i>
										</button>
									</td>
<script>
document.querySelector('.sweet-${holiday.id_date}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "You will be deleting this id!",
      type: "warning",
      showCancelButton: true,
      confirmButtonClass: 'btn-danger',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "DeleteHoliday?id=${holiday.id_date}";
      });
    }; 
</script>
								</tr>
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
	function add() {
		
    var today = moment().format('DD-MM-YYYY');
	
	document.location = "holiday_add?flag=1&date_cal="+today;
	}	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){	

	var value="${myselect}" ; 
	var d = new Date();
	if(value == 0 ){
		document.getElementById(d.getFullYear()).selected = "true";	
	}else if(value == 2 ){  
		document.getElementById("all").selected = "true";
	}else{
	document.getElementById(value).selected = "true";
   }
	

		$('#mySelect').on('click', function(){
			var year = $(this).val();
			var value="${myselect}";
			var CurrentYear = new Date().getFullYear()
			
			if(value == 2){
				value = "All";
			}else if(value == ''){
				value = CurrentYear;
			}
			document.location = "HolidayReport?year="+value;
			
	});	

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
