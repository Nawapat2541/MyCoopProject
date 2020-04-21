<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>

<div class="portlet light bordered" style="text-align: center;">
	<div class="portlet-title" style="margin-left: 2%;">
		<div class="caption">
			<i class="fa fa-laptop font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Borrow
				approved</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->

		<!-- <div class="row">
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">Search
						:</label>
					<div class="col-md-6">
						<select class="bs-select form-control" name="expense.userId">
							<option value="app" selected="">Approved</option>
							<option value="un">Unapproved</option>
							<option value="wait">Borrowing</option>
							<option value="wait">Waiting for approved</option>
							<option value="un">Return</option>
						</select>
					</div>
				</div>
			</div> -->
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
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><center>#</center></th>
								<th><center>Serial NO</center></th>
								<th><center>Equipment</center></th>
								<th><center>Borrowing by</center></th>
								<th><center>DateStart</center></th>
								<th><center>DateEnd</center></th>
								<th><center>Reason</center></th>
								<th><center>Status</center></th>
								<th><center>Approved</center></th>
								<th><center>Unapproved</center></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="list" items="${approveborrow}" varStatus="status">
								
									<c:if test="${list.equipmentid == list.equipment_id && list.status == 'W'  }">
										
												<tr>
													<td>${status.count}</td>
													<td><a href="approve_id?borrowid=${list.borrow_id}"
														>${list.item_no}</a></td>
													<td>${list.name}</td>
													<td>${list.user_borrowid}</td>
													<td>${list.date_start}</td>
													<td>${list.date_end}</td>
													<td><input type="text" name="reasona"
														class="form-control reason-textbox" "
														value="${list.reasona}" maxlength="128"></td>
													<td style="text-align: center;"><span
														class="label label-sm label-primary">Waiting for
															Approved </span></td>
													<td style="text-align: center;"><center>
															<div class="md-checkbox-list">
										<div class="md-checkbox " style="margin-left: 40%;" id="checkbox">
											<input type="checkbox"
												id="${list.borrow_id}" class="md-check sweet-${list.equipmentid}"> 
												<label for="${list.borrow_id}"> 
												<span class="inc"></span> 
												<span class="check"></span> 
												<span class="box"></span>
											</label>
										</div>
									</div>
								
														</center></td>
														
													<td style="text-align: center;">
														<center>
															
															<i class="fa fa-close font-red sweet-${list.borrow_id}n"
																onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);"></i>
														</center>
													</td>
													<script>
document.querySelector('.sweet-${list.equipmentid}').onclick = function(){
	var borrowId = $(this).attr("id");
    swal({
      title: "Approved this id!",
      text: "",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false){  document.getElementById(borrowId).click();	return false;}
        if (inputValue === "") {
    		  
    	    
          return false
        }
        document.location = "equipmen_up_status?statusequipment=${list.equipmentid}&id_borrow=${list.borrow_id}";
      });
    }; 
    document.querySelector('.sweet-${list.borrow_id}n').onclick = function(){
        swal({
          title: "Unapproved this id!",
          text: "",
          type: "info",
          showCancelButton: true,
          confirmButtonClass: 'btn-primary',
          confirmButtonText: 'OK'
        }, function (inputValue) {
            if (inputValue === false) return false;
            if (inputValue === "") {
              return false
            }
            document.location = "delete_eqix?borrowid=${list.borrow_id}";
          }
        );
        }; 
</script>
												</tr>
												</c:if>
									<c:if test="${list.equipmentid == list.equipment_id && list.status == 'R' }">
																					<tr>
													<td>${status.count}</td>
													<td><a href="approve_id?borrowid=${list.borrow_id}"
														onclick="appid()">${list.item_no}</a></td>
													<td>${list.name}</td>
													<td>${list.user_borrowid}</td>
													<td>${list.date_start }</td>
													<td>${list.date_end}</td>
													<td><input type="text" name="reasona"
														class="form-control reason-textbox" 
														value="" maxlength="255"></td>
													<td style="text-align: center;"><span
														class="label label-sm label-danger">Return
															Equipment</span></td>
													<td style="text-align: center;">
											<%-- 		<a
														href="javascript:void(0);" id="${list.borrow_id}"
														class="return_equipment"><i
															class="fa fa-check font-red"></i></a>
															
 --%>								<div class="md-checkbox-list">
										<div class="md-checkbox " style="margin-left: 40%;" id="checkbox">
											<input type="checkbox"
												id="${list.borrow_id}" class="md-check return_equipment"> 
												<label for="${list.borrow_id}"> 
												<span class="inc"></span> 
												<span class="check"></span> 
												<span class="box"></span>
											</label>
										</div>
									</div>
 													</td>
													<td style="text-align: center;"> <a id="${list.borrow_id}"
														href="javascript:void(0);" 
														class="not_return_equipment"><i
															class="fa fa-close font-red"></i></a></td>
													


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


<script type="text/javascript">
$(document).ready(function(){
	$("input.reason-textbox").on("keyup", function(e){
		//keycode ENTER is 13
		var borrowId = $(this).attr("id");		
		var reason = $(this).val();
		if(borrowId != undefined &&  borrowId != "") {
			//if we hit ENTER KEY
			if(e.keyCode == 13) {				
				$.ajax({
					url:"/update_borrow_reason",
					method: "POST",
					data: "borrow_id=" + borrowId + "&reason=" + reason
				}).done(function(response){
					if(response instanceof Object) {
						//alert("ปรับปรุงข้อมูลเสร็จแล้ว");
						console.log(response);
					}
				});
			}	
		}
	});
	
	$(".return_equipment").on("click", function(){
		var borrowId = $(this).attr("id");
		
			if(borrowId != "") {
			swal({
			  title: "Returned successfully!",
			  text: "",
			  type: "info",
			  showCancelButton: true,
			  confirmButtonClass: 'btn-primary',
			  confirmButtonText: 'OK'
			}, function (inputValue) {
			    if(inputValue) {
			    	//OK
			    	// change borrow status to "S"
			    	// change equipment amount from 0 to 1 and change status from "B" to "A"
			    	$.ajax({
						url:"/update_borrow_status",
						method: "POST",
						data: "borrow_id=" + borrowId + "&return_type=return",
					}).done(function(response){
						if(response instanceof Object) {
							//alert("ปรับปรุงข้อมูลเสร็จแล้ว");
							window.location = "/ROOT/borrow_app"
						}
					});
			    }else{document.getElementById(borrowId).click();			  
			    }
			});
		}
	});
	$(".not_return_equipment").on("click", function(){
		var borrowId = $(this).attr("id");
		
		if(borrowId != "") {
			if(borrowId != "") {
				swal({
				  title: "Has not returned!",
				  text: "",
				  type: "info",
				  showCancelButton: true,
				  confirmButtonClass: 'btn-primary',
				  confirmButtonText: 'OK'
				}, function (inputValue) {
				    if(inputValue) {
				    	//OK
				    	// change borrow status to "Y"
				    	$.ajax({
							url:"/update_borrow_status",
							method: "POST",
							data: "borrow_id=" + borrowId + "&return_type=not_return",
						}).done(function(response){
							if(response instanceof Object) {
								//alert("ปรับปรุงข้อมูลเสร็จแล้ว");
								window.location = "/ROOT/borrow_app"
							}
						});
				    }
				});
			}
		}
	});
});
</script>

