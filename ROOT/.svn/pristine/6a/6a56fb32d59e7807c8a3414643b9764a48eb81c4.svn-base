<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
<style type="text/css">
/* class สำหรับแถวแรกของรายละเอียด */
.tr_odd {
	background-color: #F8F8F8;
}
/* class สำหรับแถวสองของรายละเอียด */
.tr_even {
	background-color: #F2F2F2;
}
</style>
<div class="portlet light bordered">
	<div class="portlet-title" style="margin-left: 2%;">
		<div class="caption">
			<i class="fa fa-list font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Department</span> <span
				class="caption-helper font-red"> <%-- ${role.name} --%>
			</span>
		</div>
		<div align="right">
			<a href="department_add" class="btn green-jungle"><i class="fa fa-plus"></i>&nbsp;Add Department</a>
		</div>
		<br>
		<div class="portlet-body">
			<!-- BEGIN FORM-->
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
									<th style="width: 5%;"><center>No</center></th>
									<th style="width: 5%;"><center>ID</center></th>
									<th style="width: 10%;"><center>Name</center></th>
									<th style="text-align: center; width: 10%;">Description</th>
									<th style="text-align: center; width: 5%;">Prefix ID</th>
									<th style="text-align: center; width: 10%;">User Create</th>
									<th style="text-align: center; width: 10%;">User Update</th>
									<th style="text-align: center; width: 10%;">Time Create</th>
									<th style="text-align: center; width: 10%;">Time Update</th>
									<th style="text-align: center; width: 5%;">Edit</th>
									<th style="text-align: center; width: 5%;">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="test" items="${departmentList}">
									<c:set var="counter" value="${counter + 1}" />
									<tr>
										<td>${counter}</td>
										<td style="padding-top: 10px;">${test.id}</td>
										<td style="padding-top: 10px;">${test.name}</td>
										<td style="padding-top: 10px;">${test.description}</td>
										<td style="padding-top: 10px;">${test.prefix_id}</td>
										<td style="padding-top: 10px;">${test.user_create}</td>
										<td style="padding-top: 10px;">${test.user_update}</td>
										<td style="padding-top: 10px;"><fmt:formatDate
												value="${test.time_create}" pattern=" dd-MMM-yyyy" /></td>
										<td style="padding-top: 10px;"><fmt:formatDate
												value="${test.time_update}" pattern=" dd-MMM-yyyy" /></td>
										<td style="text-align:center; padding-top: 10px;">
												<a class="btn btn-circle btn-sm sbold blue editsweet-${test.id}" title="Edit"
												href="Department_edit?id=${test.id}">
												<i class="fa fa-pencil"></i></a></td>
										<td style="text-align:center; padding-top: 10px;">
												<a class="btn btn-circle btn-sm sbold red-mint sweet-${test.id}" 
												onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);" title="Delete">
												<i class="fa fa-trash"></i></a></td>		
									</tr>
									<script>
document.querySelector('.sweet-${test.id}').onclick = function(){
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
        document.location = "Department_delete?id=${test.id}";   //?id คือ parameter
      });
};
</script>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- END FORM-->
		</div>
	</div>
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