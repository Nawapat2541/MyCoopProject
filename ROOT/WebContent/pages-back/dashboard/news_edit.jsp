<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i class="fa fa-wrench"></i> Edit News</span>
		</div>
	</div>
	<div class="portlet-body">
	<form action="edit-newfeed" class="form-horizontal" method="POST" enctype="multipart/form-data">	
		<div class="form-group form-md-line-input col-md-12">
			<label class="col-md-3 control-label" for="form_control_1"> </label>
			<label class="col-md-1 control-label" for="form_control_1">Title :
				<span class="required" aria-required="true">*</span>
			</label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="" name="title_News" id="title" value="${news.newsHead}">
				<div class="form-control-focus"></div>
				<span class="help-block">enter your title</span>
			</div>
			<label class="col-md-3 control-label" for="form_control_1"> </label>
		</div>
		
		<div class="form-group form-md-line-input col-md-12">
			<label class="col-md-3 control-label" for="form_control_1"> </label>
			<label class="col-md-1 control-label" for="form_control_1">Detail :
			<span class="required" aria-required="true">*</span>
			</label>
			<div class="col-md-4">
				<textarea class="form-control" name="detail_News" rows="3" id="detail">${news.newsDescription}</textarea>
				<div class="form-control-focus"></div>
				<span class="help-block">enter your detail feed</span>
			</div>
			<label class="col-md-3 control-label" for="form_control_1"> </label>
		</div>
		
		<div style="text-align: center;">
			<div class="fileinput fileinput-new" data-provides="fileinput">
							<div class="fileinput-new thumbnail"
								style="width: 200px; height: 150px;">
								<c:forEach var="file" items="${pathfile}" varStatus="status">
								 <img
									src="${file.path}"
									alt="">
								</c:forEach>
							</div>
							<div class="fileinput-preview fileinput-exists thumbnail"
								style="max-width: 200px; max-height: 150px; line-height: 10px;">
							</div>
							<div>
								<span class="btn default btn-file"> <span
									class="fileinput-new"><i class="fa fa-picture-o"></i> Select File</span> <span
									class="fileinput-exists"> Change </span> <input type="hidden"
									value="" name="...">
									<input type="file" name="fileUpload" id="myFile" value="">
								</span> <a href="javascript:;" class="btn red fileinput-exists"
									data-dismiss="fileinput"> Remove </a>
							</div>
						</div>
		</div>
		<input type="hidden" name="mypic" value="${news.fileId}">
		<input type="hidden" name="mynewsId" value="${news.newsId}">
		<input type="hidden" name="userUploadId" value="${user.id}">
		<input type="hidden" name="fileUploadSize" value="${size}" id="size">
		<div style="text-align: center;">
		<button type="submit" class="btn btn-primary" id="accept"><i class="fa fa-save"></i> SAVE</button>
		<button type="reset" class="btn btn-danger" onclick="back()"><i class="fa fa-close"></i> CANCEL</button>
		</div>
</form>
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
	
<script>
$('#myFile').bind('change', function() {
	  var fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
	  fSize = this.files[0].size; i=0;while(fSize>900){fSize/=1024;i++;}
	  var size_n = (Math.round(fSize*100)/100);
	  var size = size_n+' '+fSExt[i];
	  $('#size').val(size);
	
	});
	
$("#accept").click(
		function() {
			var title = $("#title").val();	
			var detail = $("#detail").val();
			if((title == "") || (detail==""))
				{
					document.getElementById("accept").type="button";
					alert('Please Input Title and Detail');
				}
			else
				{
					document.getElementById("accept").type="submit";
				}
		});
function back() {
	document.location = "my_news";
}
</script>
	