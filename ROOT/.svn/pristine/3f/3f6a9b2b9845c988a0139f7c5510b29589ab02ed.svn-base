<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-rss font-red"></i> <span
				class="caption-subject font-red sbold uppercase">FEEDBACK RESPONSE </span> <span class="caption-helper font-red">${role.name}</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<form action="saveU?id_feedd=${feedback.idfeedback}" class="form-horizontal" method="post" autocomplete="off">
		
			<div class="form-group form-md-line-input" id="blockui_sample_1_portlet_body">
			
				<div class="form-group form-md-line-input has-success">
					<label class="col-md-2 control-label" for="form_control_1">Date Update:</label>
					<div class="col-md-2">
						<input type="text" class="form-control"
							value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy H:mm"  />"
							disabled>
							<div class="form-control-focus"></div>
					</div>
				</div>
				
				<div class="form-group form-md-line-input has-success">
					<label class="col-md-2 control-label" for="form_control_1">Name's feedback:</label>
					<div class="col-md-2">
						<input type="text" class="form-control"
							value="${feedback.user_create}"
							disabled>
					</div>
				
				
				
				</div>
				<div class="form-group form-md-line-input has-success">
                	<label class="col-md-2 control-label" for="form_control_1">Title:</label>
                    	<div class="col-md-10" style ="width: 80%;">
                        	<textarea class="form-control"  rows="3" disabled>${feedback.title}</textarea>
                            	<div class="form-control-focus"></div>
                        </div>
                </div>
                <div class="form-group form-md-line-input has-success">
                	<label class="col-md-2 control-label" for="form_control_1">Description:</label>
                    	<div class="col-md-10" style ="width: 80%;">
                        	<textarea class="form-control"  rows="3" disabled>${feedback.description}</textarea>
                            	<div class="form-control-focus"></div>
                        </div>
                </div>
                <div class="form-group form-md-line-input has-success">
               		<label class="col-md-2 control-label" for="form_control_1">Response:</label>
                    	<div class="col-md-10" style ="width: 80%;">
                          	<textarea class="form-control" placeholder="Enter more text" rows="3" name="response">${feedback.description_res}</textarea>
                            	<div class="form-control-focus"> </div>
                            	 <span class="help-block">Please
							enter your Response</span>
                       </div>
                </div>
				<div class="form-group form-md-line-input has-success">
					<label class="col-md-2 control-label" for="form_control_1">Status:</label>
					<div class="col-md-5" style ="width: 20%;">
						<select class="form-control select2me" name="statusU" id=statusU>
						<option value='R' id='R'
						<c:if test="${feedback.status.toString() == 'R' }"><c:out value="selected=selected"/> </c:if>>Request</option>
						<option value='F' id='F'
						<c:if test="${feedback.status.toString() == 'F' }"><c:out value="selected=selected"/> </c:if>>Fixing</option>
						<option value='D' id='D'
						<c:if test="${feedback.status.toString() == 'D' }"><c:out value="selected=selected"/> </c:if>>Fixed</option>
						</select>
					</div>
				</div>
				<div class="row ">
					<div class="col-md-12" style="text-align: center;">
						<button type="submit" id="demo15" class="btn sbold blue "
							onclick="save();">
							<i class="fa fa-save"></i>&nbsp;Save
						</button>
						<button type="reset" class="btn red">
							<i class="fa fa-times-circle"></i>&nbsp;Cancel
						</button>
					</div>
				</div>
			
			
			</div>
			
		</form>
	
	
	
	</div>



</div>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-upload font-red"></i> <span
				class="caption-subject font-red sbold uppercase">File feedback </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div>
		<div id="div_input" style="text-align: right;">
			Search:&nbsp;<input type="text" id="myInput" onkeyup="myFunction()"
				placeholder="Search for names.." title="Type in a name"
				class="form-control input-sm input-small input-inline">
		</div>
	</div>
	<div class="portlet-body">
		<table
			class="table table-bordered table-striped table-condensed flip-content table-hover "
			data-toggle="table" data-search="true" id="myTable">

			<thead>
				<tr>
					<th style="text-align: right;">#</th>
					<th style="text-align: center;">Name</th>
					<th style="text-align: center;">Date</th>
					<th style="text-align: center;">Size</th>
					<th style="text-align: center;">View</th>
					<th style="text-align: center;">Delete</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="fileupload" items="${fileuploadList}"
					varStatus="status">
					<tr>
						
						<td style="text-align: right;">${status.count}&nbsp;</td>
						<td style="text-align: left;"><a href="${fileupload.path}"
							target="_blank">${fileupload.name}</a></td>
						<td style="text-align: center;"><fmt:formatDate
								value="${fileupload.timeCreate}" pattern=" dd-MM-yyyy HH:mm" /></td>
						<%-- <td>${fileupload.timeCreate}</td> --%>
						<td style="text-align: center;">${fileupload.size}&nbsp;</td>
						<td style="text-align: center;">
							<c:choose>
         						<c:when test = "${fn:containsIgnoreCase(fileupload.type.toString(),'.png') || fn:containsIgnoreCase(fileupload.type.toString(),'.jpg') || fn:containsIgnoreCase(fileupload.type.toString(),'.gif')}">
            						<a class="btn btn-circle btn-sm sbold yellow"  target="_blank" href="${fileupload.path}"
											title="file type ${fileupload.type}" style="color: white;">
										<i class="fa fa-file-image-o"></i></a>
         						</c:when>        
         						<c:otherwise>
           								<a class="btn btn-circle btn-sm sbold blue" href="${fileupload.path}" target="_blank"
											title="file type ${fileupload.type}" style="color: white;">
										<i class="fa fa-clipboard"></i></a>
         						</c:otherwise>
      						</c:choose>
						</td>
						<td style="text-align: center;"><a title="Delete" class="btn btn-circle btn-icon-only red-mint btn-outline sbold uppercase"
									onclick="deletefile(${fileupload.fileIdfb})"><i class="fa fa-close"></i></a>
						</td>
						
				
<script>
function deletefile(x){
    swal({
      title: "Are you sure!",
      text: "You will be deleting this id!",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) 
        	return false;
        if (inputValue === "") {
          return false
        }
        $.ajax({
		   	url:"delete_file_feed",
		   	method : "POST",
			type : "JSON",
			data :{
				"id_feeddd" : x,
				"id" : x
			},success : function(data) {
				location.reload();
			}
	   })
      });
    }; 
</script>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- End Table -->
	</div>
</div>
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
<!-- END PAGE LEVEL PLUGINS -->

<script>
	function Alert(userid, enable) {
		if (enable == 1) {
			var massage = "enable"
		} else if (enable == 0) {
			var massage = "dissable"
		}
		toastr.success(userid + " " + massage + " success");
	}

	function myFunction() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			td1 = tr[i].getElementsByTagName("td")[1];
			td2 = tr[i].getElementsByTagName("td")[2];
			td3 = tr[i].getElementsByTagName("td")[3];
			td4 = tr[i].getElementsByTagName("td")[4];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
					if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
						if (td2.innerHTML.toUpperCase().indexOf(filter) > -1) {
							tr[i].style.display = "";
						} else {
							tr[i].style.display = "none";
							if (td3.innerHTML.toUpperCase().indexOf(filter) > -1) {
								tr[i].style.display = "";
							} else {
								tr[i].style.display = "none";
								if (td4.innerHTML.toUpperCase().indexOf(filter) > -1) {
									tr[i].style.display = "";
								}
							}
						}
					}
				}
			}
		}
	}
</script>
<script>
$('#myFile').bind('change', function() {
	  var fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
	  fSize = this.files[0].size; i=0;while(fSize>900){fSize/=1024;i++;}
	  var size_n = (Math.round(fSize*100)/100);
	  var size = size_n+' '+fSExt[i];
	  $('#size').val(size);
	 /*  <c:set var="size"  value="size"/> */
	  /*  alert(size);  */
	});
</script>
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
<script>$(document).ready(function () {
    $('.select2me').select2();
});</script>