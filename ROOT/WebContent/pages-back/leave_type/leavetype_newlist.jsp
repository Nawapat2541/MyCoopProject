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
				class="caption-subject font-red sbold uppercase">Leave Type list</span>
			<span class="caption-helper font-red"> <%-- ${role.name} --%>
			</span>
		</div>
		<div class="actions right">
			
			<a href="createleavetype"class="btn green-jungle"  ><i class="fa fa-plus"></i>&nbsp;Create Leave type</a>
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""></a>
		</div>
	</div>
	
	<div class="portlet-body">
		<!-- BEGIN FORM-->
	
		<!-- คนหาคนอัพเดท -->
				<div>
				<!-- ---------------------------------------start search------------------------------------------------ -->
			<form action="Searchleavetype" class="form-horizontal" method="post">
				<!-- <form action="search-avi" class="form-horizontal" method="post"> -->

					<div class="form-group form-lg-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Search:</label>
						<div class="col-md-2">
							<select class="bs-select form-control" name="myselect"
								onchange="this.form.submit()">
								<option disabled="disabled">Choose user update</option>
								<option >All</option>
								<c:forEach var="list" items="${leavetypelist_user}">
									<option value="${list}" id="${list}">${list}
									
									</option>
								</c:forEach>
							</select>

						</div>


					</div>

					
			</form>
			<!-- ---------------------------------------end search------------------------------------------------ -->
		<div class="portlet-body flip-scroll" style="text-align: center;"
			data-toggle="table">
			<table
				class="table table-bordered table-striped table-condensed flip-content table-hover">
				<thead class="flip-content">
					<tr>
								<th style="width:10%;"><center>Leave Type ID</center></th>
								<th style="width:20%;"><center>Name</center></th>
								<th style="text-align: center; width:20%;">Description</th>
								<th style="text-align: center; width:10%;">User create</th>
								<th style="width:10%;"><center>User update</center></th>
								<th style="width:10%;"><center>Time Create</center></th>
								<th style="width:10%;"><center>Time Update</center></th>
								<th style="width:5%;"><center>Detail</center></th>
								<th style="width:5%;"><center>Delete</center></th>
							</tr>
				</thead>

				<c:forEach var="leave_type" items="${leavetypelist}">
								<tr>
									
							 <td>${leave_type.leave_type_id}</td>
								 <td>${leave_type.leave_type_name}</td> 
								<td>${leave_type.description}</td>
									<td>${leave_type.user_create}</td>
									<td>${leave_type.user_update}</td>
									<td><fmt:formatDate value="${leave_type.time_create}" pattern="dd MMM yyyy " /></td>
									<td><fmt:formatDate value="${leave_type.time_update}" pattern="dd MMM yyyy " /></td>
									
									<!-- ทำปุ่ม detail & Delete -->
									
									
									<td style="padding-top:10px;">
										<div>
											<a class="btn btn-circle btn-sm sbold blue" title="Detail"
											href="Editleavetype?id=${leave_type.leave_type_id}&flag=1">
											<i class="fa fa-clipboard"></i></a>
									</td>
									<td style="padding-top:10px;"><button
										class="btn btn-circle btn-sm sbold red sweet-${leave_type.leave_type_id}" title="Delete"
										onclick="_gaq.push();"><i class="fa fa-trash"></i></button>
									</td>
								<script>
document.querySelector('.sweet-${leave_type.leave_type_id}').onclick = function(){
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
        document.location = "toDeleteleavetype?id=${leave_type.leave_type_id}";
      });
    }; 
    </script>	
<script>
$(document).ready(function(){	

	var value="${myselect}" ; 
	
	if(value == 0 ) {
		document.getElementById(value).selected = "true";	
	} else  if(value == 2 ){  
		document.getElementById("all").selected = "true";
	}  else { 
	document.getElementById(value).selected = "true";
   } 

}); 
</script>
	</tr>
							</c:forEach>

			</table>

		</div>
		
			
		</div>
		

		<!-- END FORM-->
	</div>
</div>


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
