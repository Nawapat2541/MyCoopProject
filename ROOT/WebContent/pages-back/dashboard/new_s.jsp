<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i
				class="fa fa-newspaper-o"></i> News Feed</span>
		</div>
		<div class="actions right">
			<button type="button" class="btn green-jungle" onclick="add()">
				<i class="fa fa-plus"></i> My News
			</button>
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12 col-xs-12 col-sm-12">
		<div class="portlet light portlet-fit">
			<div class="portlet-body flip-scroll" style="text-align: center;">
			<div class="portlet box grey-mint">
									<div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-newspaper-o"></i> Add News</div>
                                    </div>
				<div class="portlet-body ">
					<form action="save-newfeed" class="form-horizontal" method="POST"
						enctype="multipart/form-data">
						<div class="col-lg-9 col-md-6 col-sm-6">
						<div class="form-group form-lg-line-input col-md-12">
							<label class="col-md-3 control-label" for="form_control_1">Title
								: <span class="required" aria-required="true">*</span>
							</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="title_News" id="title">
								<div class="form-control-focus"></div>							 
							</div>
						</div>
						<div class="form-group form-lg-line-input col-md-12">
							<label class="col-md-3 control-label" for="form_control_1">Detail
								: <span class="required" aria-required="true">*</span>
							</label>
							<div class="col-md-9">
								<textarea class="form-control" name="detail_News" rows="3"
									id="detail"></textarea>
								<div class="form-control-focus"></div>
								 
							</div>
						</div>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6">
						<div style="text-align: center;">
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
										class="fileinput-new"><i class="fa fa-picture-o"></i> Select File</span> <span
										class="fileinput-exists"> Change </span> <input type="hidden"
										value="" name="..."> <input type="file"
										name="fileUpload" id="myFile" value="">
									</span> <a href="javascript:;" class="btn red fileinput-exists"
										data-dismiss="fileinput"> Remove </a>
								</div>
							</div>
						</div>
						</div>
						<input type="hidden" name="userUploadId" value="${user.id}">
						<input type="hidden" name="fileUploadSize" value="${size}"
							id="size">
						<div style="text-align: center;">
							<button type="submit" class="btn btn-primary" id="accept">
								<i class="fa fa-plus"></i> ADDNEWS
							</button>
							<button type="reset" class="btn btn-danger" onclick="back()">
								<i class="fa fa-close"></i> CANCEL
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="test ">
			<jsp:include page="/pages-back/dashboard/detail.jsp" flush="true"></jsp:include>
		 </div>
		</div>
		</div>
		
	</div>
</div>
<script>
	function nextStep() {
		document.location = "check_in";
	}

	function add() {
		document.location = "my_news";
	}
</script>


<link href="../assets/global/plugins/ladda/ladda-themeless.min.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/ladda/spin.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/ladda/ladda.min.js"
	type="text/javascript"></script>

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

<link href="../assets/global/plugins/ladda/ladda-themeless.min.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/pages/scripts/ui-buttons.min.js"
	type="text/javascript"></script>
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

	});

	$("#accept").click(
			function() {
				var title = $("#title").val();
				var detail = $("#detail").val();

				var length_title = $("#title").val().length;
				var length_detail = $("#detail").val().length;
				if ((title == "") || (detail == "")) {
					document.getElementById("accept").type = "button";
					swal("You miss some Header or Detail",
							"Please Try It Agian :)", "error");
				} else if (length_title > 250 || length_detail > 2000) {
					document.getElementById("accept").type = "button";
					swal("Your Text too long Header or Detail",
							"Please Try It Agian :)", "error");
				} else {
					document.getElementById("accept").type = "submit";
				}
			});

	function back() {
		document.location = "new_s";
	}
</script>