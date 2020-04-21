<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>




<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />


<script src="../assets/global/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>


<script src="../assets/layouts/global/scripts/quick-sidebar.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/global/scripts/quick-nav.min.js"
	type="text/javascript"></script>
<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-folder-open"></i>Add New Product
			</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	
	<div class="portlet-body">
		<form action="save-new" class="form-horizontal" method="post"
			enctype="multipart/form-data" name="form1" >
			<center style="margin-top: 5%;">
				<div class="fileinput fileinput-new" data-provides="fileinput">
					<div class="fileinput-new thumbnail"
						style="width: 200px; height: 150px;">
						<img
							src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
							alt="cc">
					</div>
					<div class="fileinput-preview fileinput-exists thumbnail"
						style="max-width: 200px; max-height: 150px; line-height: 10px;"></div>
					<div>
						<span class="btn default btn-file"> <span
							class="fileinput-new"> Select File</span> <span
							class="fileinput-exists"> Change </span> <input type="hidden"
							value="" name="..."> <input type="file" name="fileUpload"
							id="myFile" value="" accept="image/x-png,image/gif,image/jpeg">
						</span> <a href="javascript:;" class="btn red fileinput-exists"
							data-dismiss="fileinput"> Remove </a>
					</div>
				</div>
			</center>
			<!-- <div class="col-md-12" style="text-align: center;">

				<div class="fileinput fileinput-new" data-provides="fileinput">
					<div class="fileinput-new thumbnail"
						style="width: 200px; height: 150px;">
						<img
							src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
							alt="">
					</div>
					<div class="fileinput-preview fileinput-exists thumbnail"
						style="max-width: 200px; max-height: 150px; line-height: 10px;">
					</div>
					<div>
						<span class="btn default btn-file"> <span
							class="fileinput-new"> Select image </span> <span
							class="fileinput-exists"> Change </span> <input type="hidden"><input
							type="file" name="...">
						</span> <a href="javascript:;" class="btn red fileinput-exists"
							data-dismiss="fileinput"> Remove </a>
					</div>
				</div>
			</div> -->
				<div class="form-body">
					<div class="form-group form-lg-line-input col-md-12">
						<label class="control-label col-md-3">Type: </label>
						<div class="col-md-7">
							<select class="bs-select form-control" name="type" id="mytype">
								<option value="c" selected>Notebook</option>
								<option value="o">Other</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="itemnolabel">Item
									NO : </label>
								<div class="col-md-8">
									<input type="text" name="itemNo" class="form-control"
										value="${newItemNo}" id="itemnoid" maxlength="13" required>
									<div class="form-control-focus"></div>
<!-- 									 -->
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="serialnolabel">Serial
									NO : </label>
								<div class="col-md-8">
									<input type="text" name="serialno" class="form-control"
										id="serialnoid" maxlength="60" required>
									<div class="form-control-focus"></div>
<!-- 									 -->
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="namelabel">Name
									: </label>
								<div class="col-md-8">
									<input type="text" name="name" class="form-control" id="nameid"
										maxlength="240" required>
									<div class="form-control-focus"></div>
<!-- 									 -->
								</div>
							</div>
						</div>

						<div class="col-md-6 hide" id="detail-form-group">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="detaillabel">Detail
									: </label>
								<div class="col-md-8">
									
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>

						<div class="col-md-6" id="processor-form-group">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="processlabel">Process
									: </label>
								<div class="col-md-8">
									<input type="text" name="process" class="form-control"
										maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>

					<div class="row computer-form">
						<div class="col-md-6" id="bnh">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="batterylabel">Battery
									: </label>
								<div class="col-md-8">
									<input type="text" name="battery" class="form-control"
										id="batteryid" maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6" id="bnh">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="hddlabel">Hdd
									: </label>
								<div class="col-md-8">
									<input type="text" name="hdd" class="form-control" id="hddid"
										maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>

					<div class="row computer-form" id="wnr">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="windowlabel">Windows
									: </label>
								<div class="col-md-8">
									<input type="text" name="window" class="form-control"
										id="windowid" maxlength="60">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3" id="ramlabel">Ram
									: </label>
								<div class="col-md-8">
									<input type="text" name="ram" class="form-control" id="ramid"
										maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3">Location : </label>
								<div class="col-md-8">
									<input type="text" name="location" class="form-control"
										maxlength="250">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3">Amount : </label>
								<div class="col-md-8">
									<input type="text" name="amount" class="form-control" value="1"
										readonly>
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3">Date : </label>
								<div class="col-md-8">
									<input type="text" name="timeCreate" class="form-control"
										value="<fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm"/>"
										readonly>
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3">Status : </label>
								<div class="col-md-8">
									<select class="bs-select form-control" name="status">
										<option value="A">Available</option>
										<option value="B">Borrowed</option>
										<option value="D">Waiting for Approved</option>
										<option value="C">Corrupted</option>
										<option value="L">Lost</option>
										<option value="F">Fixed</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
						<div class="col-md-6">
						</div>
							<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3">Remark : </label>
								<div class="col-md-8">
									<input type="text" name="detail" class="form-control">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>
			
			<div class="form-actions action right">
				<div class="row ">
					<div class="col-md-12">
						<center>
							<button type="submit" id="demo15" class="btn blue">
								<i class="fa fa-save"></i> Save
							</button>
							<button type="reset" class="btn red" onclick="ss()">
								<i class="fa fa-times-circle"></i> Cancel
							</button>
						</center>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	$('#myFile').bind('change', function() {
		var fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
		fSize = this.files[0].size;
		i = 0;
		while (fSize > 900) {
			fSize /= 1024;
			i++;
		}
		var size_n = (Math.round(fSize * 100) / 100);
		var size = size_n + ' ' + fSExt[i];
		$('#size').val(size);
		/*  <c:set var="size"  value="size"/> */
		/*  alert(size);  */
	});
</script>
<script>
	function ss() {
		document.location = "borrowing";
	}
</script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#mytype").on("change", function() {
			console.log($(this).val());
			if ($(this).val() == "o") {
				$(".computer-form").addClass("hide");
				$("#detail-form-group").removeClass("hide");
				$("#processor-form-group").addClass("hide");
			} else {
				$(".computer-form").removeClass("hide");
				$("#detail-form-group").addClass("hide");
				$("#processor-form-group").removeClass("hide");
			}
		});
	});
</script>
<!-- <script>
	$("#demo15").click(function() {
		var itemnoid = $("#itemnoid").val().length;
		;
		var serialnoid = $("#serialnoid").val().length;
		;
		var nameid = $("#nameid").val().length;
		;

		if (itemnoid != 0 && serialnoid != 0 && nameid != 0) {
			$.blockUI({
				css : {
					border : 'none',
					padding : '15px',
					backgroundColor : '#000',
					'-webkit-border-radius' : '10px',
					'-moz-border-radius' : '10px',
					opacity : .5,
					color : '#fff'
				}
			});

			setTimeout($.unblockUI, 2000);

		}
	});
</script> -->
