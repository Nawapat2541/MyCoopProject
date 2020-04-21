<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="favicon.ico" />
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>

<fmt:formatDate pattern="yyyy" value="${dateTimeNow}" var="year_now" />
<fmt:formatDate pattern="MM" value="${dateTimeNow}" var="month_now" />
<fmt:formatDate pattern="E-dd" value="${dateTimeNow}" var="day_now" />
<fmt:formatDate pattern="E-dd-MM-yyyy" value="${dateTimeNow}" var="dateNow" />
<fmt:formatDate pattern="HH:mm" value="${dateTimeNow}" var="timeNow" />

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold "> Timesheet Approve </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body" style="text-align: center;">


		<form class="form-inline margin-bottom-40" action="search_timesheet"
			method="POST">
			<!-- --------------------------------------------------------------Start search------------------------------------------------------ -->
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Staff : </label> 
				
				<select
					class="form-control select2me" name="name" id=name>
					<optgroup label="Enable">
						<c:forEach var="user" items="${userseq}">

							<c:if test="${user.enable == 1 }">
								<c:if test="${userSelect == null }">
									<option value="${user.id}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
								<c:if test="${userSelect != null }">
									<option value="${userSelect}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</optgroup>
					<optgroup label="Disable">
						<c:forEach var="user" items="${userseq}">

							<c:if test="${user.enable == 0 }">
								<c:if test="${userSelect == nulll }">
									<option value="${user.id}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
								<c:if test="${userSelect != nulll }">
									<option value="${userSelect}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</optgroup>
				</select>
			</div>
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Month : </label> <select
					class="form-control select2me" name="monthSearch" id=monthSearch>

					<option value='01' id='01'
						<c:if test="${month_now == 01 }"><c:out value="selected=selected"/> </c:if>>January</option>
					<option value='02' id='02'
						<c:if test="${month_now == 02 }"><c:out value="selected=selected"/> </c:if>>February</option>
					<option value='03' id='03'
						<c:if test="${month_now == 03 }"><c:out value="selected=selected"/> </c:if>>March</option>
					<option value='04' id='04'
						<c:if test="${month_now == 04 }"><c:out value="selected=selected"/> </c:if>>April</option>
					<option value='05' id='05'
						<c:if test="${month_now == 05 }"><c:out value="selected=selected"/> </c:if>>May</option>
					<option value='06' id='06'
						<c:if test="${month_now == 06 }"><c:out value="selected=selected"/> </c:if>>June</option>
					<option value='07' id='07'
						<c:if test="${month_now == 07 }"><c:out value="selected=selected"/> </c:if>>July</option>
					<option value='08' id='08'
						<c:if test="${month_now == 08 }"><c:out value="selected=selected"/> </c:if>>August</option>
					<option value='09' id='09'
						<c:if test="${month_now == 09 }"><c:out value="selected=selected"/> </c:if>>September</option>
					<option value='10' id='10'
						<c:if test="${month_now == 10 }"><c:out value="selected=selected"/> </c:if>>October</option>
					<option value='11' id='11'
						<c:if test="${month_now == 11 }"><c:out value="selected=selected"/> </c:if>>November</option>
					<option value='12' id='12'
						<c:if test="${month_now == 12 }"><c:out value="selected=selected"/> </c:if>>December</option>
				</select>
			</div>

			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Year : </label> <select
					class="form-control select2me" name="yearSearch" id=yearSearch>
					<c:forEach begin="0" end="4" var="i">
						<option value="${year_now - i}" id="${year_now - i}">${year_now - i}</option>
					</c:forEach>
				</select>
			</div>

			<button type="submit" class="btn sbold blue">
				<i class="fa fa-search"></i> Search
			</button>
		</form>
		<!-- --------------------------------------------------------------End search------------------------------------------------------ -->
		<form action="listUpdate" method="POST" id=timesheet>
		<div class="portlet-body" style="text-align: center;">
			<table class="table table-lover table-condensed flip-content"
					name=timesheetTable>
				<thead class="flip-content">
					<tr>
						<th><center>DATE</center></th>
						<th><center>Description</center></th>
						<th><center>Check-in</center></th>
						<th><center>Check-out</center></th>
						<th><center>Status</center></th>
						<th><center>Approve form</center></th>
					</tr>
				</thead>
				<c:forEach var="date" items="${arrayDay}" varStatus="status">
					
					<c:choose>
						<c:when test="${fullDateKub[status.index].toString().substring(0,3) != 'Sat' and fullDateKub[status.index].toString().substring(0,3) != 'Sun' }">
						<tr>
						<td>
						<fmt:formatDate value="${fullDateKub[status.index]}"
								pattern="E-dd-MMM-yyyy"></fmt:formatDate>
						 <c:set var="dateSelect" value="${arrayDayHidden[status.index]}-${monthSearch}-${yearList}"/></td>
						<td style="width: 30%;">
							<input type="text" 
											name="discription" id="discription${date}" disabled
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
						<td style="width: 10%;">
							<input type="text"
											name="time_check_in" id="time_check_in${date}" disabled
											value="${arrayTimeIn[status.index]}"
											class="form-control ">		
						<td style="width: 10%;">
							<input type="text"
											name="time_check_out" id="time_check_out${date}" disabled
											value="${arrayTimeOut[status.index]}"
											class="form-control "></td>
						<td><c:if test="${arrayStatus[status.index].toString() == 'W' }">
								<span class="label label-sm label-warning">Waiting for
									approve</span>
							</c:if> <c:if test="${arrayStatus[status.index].toString() == 'A'}">
								<span class="label label-sm label-success">Approved</span>
							</c:if> <c:if test="${arrayStatus[status.index].toString() == 'R'}">
								<span class="label label-sm label-danger">Reject</span>
							</c:if></td>
									<td>
						<a href="javascript:;" id="${arrayId[status.index]}"
										class="btn blue bg-green bg-font-green"
										onclick="ajaxCallupdateA('${arrayId[status.index]}');"
										> <i
											class="fa fa-file-o"></i> Approve
									</a>
									
									
								<a href="javascript:;" id="${arrayId[status.index]}"
										class="btn bg-red bg-font-green"
										onclick="ajaxCallupdateR('${arrayId[status.index]}');"
										> <i
											class="fa fa-reply"></i> Reject
									</a>
									
								
						
						</td>
					</tr>
						
						
						
						
						
						</c:when>
						<c:otherwise>
							<tr class= "bg-grey-steel bg-font-grey-steel">
					
						<td>
						<fmt:formatDate value="${fullDateKub[status.index]}"
								pattern="E-dd-MMM-yyyy"></fmt:formatDate>
						 <c:set var="dateSelect" value="${arrayDayHidden[status.index]}-${monthSearch}-${yearList}"/></td>
						<td style="width: 30%;">
							<input type="text" 
											name="discription" id="discription${date}" disabled
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
						<td style="width: 10%;">
							<input type="text"
											name="time_check_in" id="time_check_in${date}" disabled
											value="${arrayTimeIn[status.index]}"
											class="form-control ">		
						<td style="width: 10%;">
							<input type="text"
											name="time_check_out" id="time_check_out${date}" disabled
											value="${arrayTimeOut[status.index]}"
											class="form-control "></td>
						<td><c:if test="${arrayStatus[status.index].toString() == 'W' }">
								<span class="label label-sm label-warning">Waiting for
									approve</span>
							</c:if> <c:if test="${arrayStatus[status.index].toString() == 'A'}">
								<span class="label label-sm label-success">Approved</span>
							</c:if> <c:if test="${arrayStatus[status.index].toString() == 'R'}">
								<span class="label label-sm label-danger">Reject</span>
							</c:if></td>
						<td>
						<a href="javascript:;" id="${arrayId[status.index]}"
										class="btn blue bg-green bg-font-green"
										onclick="ajaxCallupdateA('${arrayId[status.index]}');"
										> <i
											class="fa fa-file-o"></i> Approve
									</a>
									
									
								<a href="javascript:;" id="${arrayId[status.index]}"
										class="btn bg-red bg-font-green"
										onclick="ajaxCallupdateR('${arrayId[status.index]}');"
										> <i
											class="fa fa-reply"></i> Reject
									</a>
									
								
						
						</td>
					</tr>
						</c:otherwise>
					</c:choose>
					</c:forEach>
			</table>
			<a href="javascript:;" id="${arrayId[status.index]}"
										class="btn bg-green bg-font-green"
										onclick="ajaxApproveAll();"
										> <i
											class="fa fa-files-o"></i> Approve ALL
									</a>
			<a href="javascript:;" id="${arrayId[status.index]}"
										class="btn bg-red bg-font-green"
										onclick="ajaxRejectAll();"
										> <i
											class="fa fa-reply-all"></i> Reject ALL
									</a>
	
			
		</div>
		
		</form>
	</div>




</div>
<script>
	function datechenge() {
		var fulldate = "${fulldate}".trim();
		var Userdate = $("#mydate").val();
		if (fulldate != Userdate) {
			$("#detail").show();
			$("#labeldetail").show();
		} else {
			$("#detail").hide();
			$("#labeldetail").hide();
		}
	}

	for (i = 1; i <= 6; i++) {
		!function(i) {
			$.post("ajax.php", {
				param : i
			}, function(response) {
			});
		}(i);
	}
</script>
<script>
		function ajaxCallupdateA(id) {
    swal({
        title: "Are you sure!",
        text: "You will be Apporve this id!",
        type: "info",
        showCancelButton: true,
        confirmButtonClass: 'btn-primary',
        confirmButtonText: 'OK'
      }, function (inputValue) {
          if (inputValue === false){ 	    document.getElementById(id).click();
return false;}
          if (inputValue === "") {
        	
              return false
          }
			console.log(id);
			$.ajax({
				type : 'POST',
				url : "listUpdateA",
				data : {
					"id" : id
					
				},
				
			}).done(function(data) {
				
				location.reload();
			});
		});
}
    
		/* $("showtoast").click(function() {
			alert("Handler for .click() called.");
		}); */
	</script>
<script>
		function ajaxCallupdateR(id) {
    swal({
        title: "Are you sure!",
        text: "You will be Reject this id!",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: 'btn-primary',
        confirmButtonText: 'OK'
      }, function (inputValue) {
          if (inputValue === false){ 	    document.getElementById(id).click();
return false;}
          if (inputValue === "") {
        	
              return false
          }
			console.log(id);
			$.ajax({
				type : 'POST',
				url : "listUpdateR",
				data : {
					"id" : id
					
				},
				
			}).done(function(data) {
				
				location.reload();
			});
		});
}
    
		/* $("showtoast").click(function() {
			alert("Handler for .click() called.");
		}); */
	</script>
<script>
		function ajaxApproveAll() {
			var m = $('select[name=monthSearch]').val();
          	var y = $('select[name=yearSearch]').val();
			var date = y+"-"+m;
    swal({
        title: "Are you sure!",
        text: "You will be Approve all id!",
        type: "info",
        showCancelButton: true,
        confirmButtonClass: 'btn-primary',
        confirmButtonText: 'OK'
      }, function (inputValue) {
          if (inputValue === false){ 	    
				return false;
		}
          if (inputValue === "") {
        	
              return false
          }
          	var a = $('select[name=name]').val();
 
			console.log();
			$.ajax({
				type : 'POST',
				url : "listUpdateAall",
				data : {
					"id" : a,
					"s" : date
					
				},
			
			}).done(function(data) {
				
				location.reload();
			});
		});
			}

    
		/* $("showtoast").click(function() {
			alert("Handler for .click() called.");
		}); */
	</script>

<script>
		function ajaxRejectAll() {
			var m = $('select[name=monthSearch]').val();
          	var y = $('select[name=yearSearch]').val();
			var date = y+"-"+m;
    swal({
        title: "Are you sure!",
        text: "You will be Reject all id!",
        type: "info",
        showCancelButton: true,
        confirmButtonClass: 'btn-primary',
        confirmButtonText: 'OK'
      }, function (inputValue) {
          if (inputValue === false){ 	    document.getElementById(id).click();
return false;}
          if (inputValue === "") {
        	
              return false
          }
          
          var a = $('select[name=name]').val();;
          
			console.log();
			$.ajax({
				type : 'POST',
				url : "listUpdateRall",
				data : {
					"id" : a,
					"s"	: date
					
				},
				
			}).done(function(data) {
				
				location.reload();
			});
		});
}
    
		/* $("showtoast").click(function() {
			alert("Handler for .click() called.");
		}); */
	</script>
<script>$(document).ready(function () {
	
	var value = "${flag_search}";
	if (value == "1") {
    var user = "${userId}";
  	document.getElementById(user).selected = "true";
	}else{

    $('.select2me').select2();
	}
    
});</script>
<script>
	$(document).ready(function() {
		var value = "${flag}";
		if (value == "1") {
			var name = "${name}";
			var monthSearch = "${monthSearch}";
			var yearSearch = "${yearSearch}";
			document.getElementById(name).selected = "true";
			document.getElementById(monthSearch).selected = "true";
			document.getElementById(yearSearch).selected = "true";
		} else {
			$('.select2me').select2();
		}

	});
</script>


<script type="text/javascript">
function  showTimeIn(day) {
	var timeInHidden = $('#timeInHidden'+day).val();
	$('#timeIn'+day).val(timeInHidden);
	document.getElementById("timeIn"+day ).style.color = "#555";
}
function  showTimeOut(day) {
	var timeOutHidden = $('#timeOutHidden'+day).val();
	$('#timeOut'+day).val(timeOutHidden);
	document.getElementById("timeOut"+day ).style.color = "#555";
};

</script>