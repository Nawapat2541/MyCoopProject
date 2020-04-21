<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-rss font-red"></i> <span
				class="caption-subject font-red sbold uppercase">FeedBack</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<div>
		<form action="savefeedback" class="form-horizontal" method="post" enctype="multipart/form-data" id="data1">
			<div class="form-body">
				<div class="form-group last">
					<div class="form-group form-lg-line-input col-md-12">
							<label class="col-md-3 control-label" for="form_control_1">Title
								: <span class="required" aria-required="true">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder=""
									name="title_Feedback" id="title" required>
								<div class="form-control-focus"></div>
								 
							</div>
					</div>
					<div class="form-group form-lg-line-input col-md-12">
							<label class="col-md-3 control-label" for="form_control_1" id="description" name="description">Description
								: <span class="required" aria-required="true">*</span>
							</label>
							<div class="col-md-8">
								<textarea class="form-control" name="description_Feddback" rows="3"
									id="description" required></textarea>
								<div class="form-control-focus"></div>
								 
							</div>
					</div>
				</div>
			</div>
			
		</form>
		<div id="maindiv">
		<form id="m1" class="form-horizontal" method="post" enctype="multipart/form-data">
			<div class="form-group form-lg-line-input col-md-12">
							<label class="col-md-3 control-label" for="form_control_1">File Upload :</label>
							<div class="col-md-8">
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 100%; max-height: 150px; line-height: 100%;"></div>
									<div>
									1.&nbsp;<span class="btn default btn-file"> 
									<span class="fileinput-new"> Select File</span> 
									<span class="fileinput-exists" id="c1"> Change </span> <input type="hidden" value="" name="...">
									<input type="file" name="fileUpload" id="myFile" value="" onchange="ValidateSize(this,1)">
									</span> 
									<a href="#" class="btn red fileinput-exists" data-dismiss="fileinput" onclick='delCount(1);'> Remove </a>
									</div>
								</div>
							<hr>
							</div>
					</div>
		</form>
		</div>
		<div  align="center">
						<button type="button" class="btn green-jungle" onclick="addcontent()"><i class="fa fa-plus"></i> Add Files</button>
						<button type="button" class="btn btn-primary" id="accept" onclick="saveFeedback1()">
								<i class="fa fa-save"></i> SAVE
							</button>
							<button type="reset" class="btn btn-danger" onclick="back()">
								<i class="fa fa-close"></i> CANCEL
							</button>
		</div>
		</div>
		<!-- END FORM-->
	</div>

</div>
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">
    <div class="modal-dialog modal-sm">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Please Wait Uploading...</h4>
        </div>
        <div class="modal-body">
          <div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div> 
        </div>
      </div>
      
    </div>
  </div>
<script>
    var i=2;
    var count=0;
    var havefile=[];
    var unique=[];
    var r=1;
    var sum=1;
    function delCount(y){
    	count--;
    	havefile.remove(y);
    }
    
    Array.prototype.remove = function() {
        var what, a = arguments, L = a.length, ax;
        while (L && this.length) {
            what = a[--L];
            while ((ax = this.indexOf(what)) !== -1) {
                this.splice(ax, 1);
            }
        }
        return this;
    };
    
	function addcontent(){
		var str = "<form id='m"+i+"' class='form-horizontal' method='post' enctype='multipart/form-data'>"+
		"<div class='form-group form-md-line-input col-md-12'>"+
		"<label class='col-md-3 control-label' for='form_control_1'>File Upload :</label>"+
		"<div class='col-md-8'>"+
			"<div class='fileinput fileinput-new' data-provides='fileinput'>"+
				"<div class='fileinput-preview fileinput-exists thumbnail' style='max-width: 100%; max-height: 150px; line-height: 100%;'></div>"+
				"<div>"+
				i+".&nbsp;<span class='btn default btn-file'>"+
				"<span class='fileinput-new'> Select File</span>"+
				"<span class='fileinput-exists'> Change </span> <input type='hidden' value='' name='...'>"+
				"<input type='file' name='fileUpload' id='myFile' value='' onchange='ValidateSize(this,"+i+")'>"+
				"</span>"+ 
				"<a href='#' class='btn red fileinput-exists' data-dismiss='fileinput' onclick='delCount("+i+");'> Remove </a>"+
				"</div>"+
			"</div>"+
		"<hr>"+
		"</div>"+
"</div>"+
"</form>";
		
		$("#maindiv").append(str);
		i++;
	}
	
		function saveFeedback1(){
			var title = $("#title").val();
			var des = $("textarea#description").val();
			if(title==null || title =="" || des==null || des==""){
				alert("Please Input Data.");
				return false;
			}
			
			$('#myModal').modal('show');
			$.ajax({
				url : "Ukey",
				method : "POST",
				type : "JSON",
				data :{
					"active" : 1,
				},
				success : function(data) {
					var obj = JSON.parse(data, function (key, value) {
				        return value;
				    });
					var v = obj.k;
					unique = Array.from(new Set(havefile));
					if((title!=null || title !="") && (des!=null || des!="") && (unique.length<=0)){
						$.ajax({
							type : "POST",
							enctype : 'multipart/form-data',
							url : "savefeedback",
							method : "POST",
							type : "JSON",
							data :{
								"title" : title,
								"description": des,
								"ukey":v
							},success : function(data) {
								$('#myModal').modal('hide');
								window.location ="feedback_req";
							}
						});
						
					}else{
					var data;
					var form;
					var val=0;
					console.log("Size array : "+unique.length);
					for(r=0;r<unique.length;r++)
					{
						val = unique[r];
						form = $('#m' + val)[0];
						data = new FormData(form);
						$.ajax({
							type : "POST",
							enctype : 'multipart/form-data',
							url : "savefeedbackfile?ukey="+v,
							data : data,
							processData : false,
							contentType : false,
							cache : false,
							success : function(data) {
								var obj1 = JSON.parse(data, function (key, value) {
							        return value;
							    });
								sum=sum + obj1.input;
								console.log("Sum is  : ", sum);
							},
							error : function(e) {
								console.log("ERROR : ", e);
							}
						});
					}
					
					
					$.ajax({
						type : "POST",
						enctype : 'multipart/form-data',
						url : "savefeedback",
						method : "POST",
						type : "JSON",
						data :{
							"title" : title,
							"description": des,
							"ukey":v
						},
						async: false
					});
					setInterval(Checksum, 1000);
					
				}
			}
		});
		}
		function Checksum(){
			if(sum==(unique.length)+1){
				$('#myModal').modal('hide');
				window.location ="feedback_req";
			}
		}
		function checkstate(){}
		function ValidateSize(file,x) {
	        var FileSize = file.files[0].size/1024/1024; // in MB
	        if (FileSize > 1) {
	            alert('File size exceeds 1 MB');
	            $(file).val(''); //for clearing with Jquery
	        } else if(file!=null){
	        	havefile.push(x);
	        }
	    }
</script>
<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<!-- BEGIN CORE PLUGINS -->
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<!-- BEGIN PAGE LEVEL PLUGINS -->
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
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>