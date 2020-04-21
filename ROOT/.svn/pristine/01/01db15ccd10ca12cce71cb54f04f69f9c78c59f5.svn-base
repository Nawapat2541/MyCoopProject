<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/css/line-login.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/global/css/croppie.css" rel="stylesheet"
	type="text/css" />

<style>
.control-label {
	padding-top: 0px !important;
}
</style>





<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-edit font-red"></i> <span
				class="caption-subject font-red sbold uppercase">EDIT USER
				PROFILE</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="user-perform-edit" method="POST"
			enctype="multipart/form-data" class="form-horizontal">
			<div class="form-body">

				<%-- <div style="text-align: center; margin-bottom: 20px;">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="fileinput-new thumbnail"
							style="width: 200px; height: 150px;">
							<c:if test="${user.path == null}">
								<img
									src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
									alt="cc">
							</c:if>
							<c:if test="${user.path != null}">
								<img src="${user.path}" alt="">
							</c:if>
						</div>
						<div class="fileinput-preview fileinput-exists thumbnail" 
							style="max-width: 200px; max-height: 150px; line-height: 10px;"></div>
						<div>
						
							<span class="btn default btn-file"> <span
								class="fileinput-new"><i class="fa fa-picture-o"></i> Select File</span> <span
								class="fileinput-exists"> Change </span> <input type="hidden"
								value="" name="..."> <input type="file"
								name="fileUpload" id="myfile" value=""
								accept="image/x-png,image/gif,image/jpeg">
							</span> <a href="javascript:;" class="btn red fileinput-exists"
								data-dismiss="fileinput"> Remove </a>  
						</div>
					</div>
				</div> --%>



				 <div style="text-align: center; margin-bottom: 20px;">
					<div class="fileinput fileinput-new"  data-provides="fileinput">
						 <div class="fileinput-new thumbnail"
							style="width: 200px; height: 150px;">
							<c:if test="${user.path == null}">
								<img
									src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
									alt="cc">
							</c:if>
							<c:if test="${user.path != null}">
								<img src="${user.path}" alt="">
							</c:if>
						</div> 
						<div class="fileinput-exists  thumbnail"  id="upload-demo"
						style="max-width: 200px; max-height: 150px; line-height: 10px;">	
						 </div>
							
                    
						 <div>
							<span class="btn default btn-file"> <span
								class="fileinput-new"  ><i class="fa fa-picture-o"></i>
									Select File</span> <span class="fileinput-exists"> Change </span> 
									<input type="hidden" value="" name="..."> <input type="file" id="images" name="fileUpload"
									accept="image/x-png,image/gif,image/jpeg">
							</span> <a href="javascript:;" class="btn red fileinput-exists"
								data-dismiss="fileinput"> Remove </a>
						</div> 
						
					</div>

				</div>  
				
<%-- 				<div class="container">
	  
	  
	    
	      <div class="row">
	      <div style="text-align: center; margin-bottom: 20px;">
					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="fileinput-new thumbnail "
							style="width: 200px; height: 150px;">
							<c:if test="${user.path == null}">
								<img
									src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
									alt="cc">
							</c:if>
							<c:if test="${user.path != null}">
								<img src="${user.path}" alt="">
							</c:if>
						</div>
	       
	        	<div id="upload-demo"
	        	></div>
	      
	     
	        </div>
	       <div style="max-width: 200px; max-height: 150px; line-height: 10px;">
							<span class="btn default btn-file" > <span
								class="fileinput-new"  ><i class="fa fa-picture-o"></i>
									Select File</span> <span class="fileinput-exists"> Change </span> 
									<input type="hidden" value="" name="..."> <input type="file" id="images" name="image">
							</span> <a href="javascript:;" class="btn red fileinput-exists"
								data-dismiss="fileinput"> Remove </a>
						</div>
	        	
	        
	      </div>
	    </div>
	 
	</div> --%>





				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Login :</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="user_id" readonly
									value="${logonUser}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Email :</label>
							<div class="col-md-8">
								<input type="email" class="form-control" name="user_email"
									value="${user.email}" maxlength="50">
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Name :</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="user_name"
									value="${user.name}" maxlength="190">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Nick Name
								:</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="user_nickName"
									value="${user.nickName}" maxlength="32">
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Role :</label>
							<div class="col-md-8">
								<select class="bs-select form-control" name="role_id" disabled>
									<c:forEach var="role" items="${roleList}">
										<option value="${role.id}"
											<c:if test="${user.roleId eq role.id }"> selected
                                            </c:if>>${role.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Address :</label>
							<div class="col-md-8">
								<textarea class="form-control" rows="2"
									placeholder="Please add your address" name="user_address"
									maxlength="250">${user.address}</textarea>
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Phone
								Number : </label>
							<div class="col-md-8">
								<input type="text" name="user_phonenum" id="phone"
									maxlength="10" class="form-control" value="${user.phonenum}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Gender :</label>
							<div class="col-md-8">
								<div class="md-radio-inline">
									<div class="md-radio has-success">
										<input type="radio" id="radio20" class="md-radiobtn"
											name="user_gender"
											<c:if test="${user.gender eq 'M' }">
                                          checked
                                     </c:if>
											value="M"> <label for="radio20" style="color: #333;">
											<span class="inc"></span> <span class="check"
											style="background: #26C281;"></span> <span class="box"
											style="border-color: #26C281;"></span> Male
										</label>
									</div>
									<div class="md-radio has-error">
										<input type="radio" id="radio21" class="md-radiobtn"
											name="user_gender"
											<c:if test="${user.gender eq 'F' }">
                                          checked
                                   </c:if>
											value="F"> <label for="radio21" style="color: #333;">
											<span class="inc"></span> <span class="check"></span> <span
											class="box"></span> Female
										</label>
									</div>
								</div>
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Department
								:</label>
							<div class="col-md-8">
								<select class="bs-select form-control" name="department_id"
									disabled>
									<c:forEach var="department" items="${departmentList}">
										<option value="${department.id}"
											<c:if
                                            test="${user.departmentId eq department.id }"> selected </c:if>>${department.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Birth Date
								:</label>
							<div class="col-md-8">
								<input
									class="form-control form-control-inline date-picker
									test"
									type="text" data-date-format="dd-mm-yyyy"
									value='<fmt:formatDate value="${user.birthDate}"
                                    pattern=" dd-MM-yyyy" />'
									name="birthDate" onkeypress='return false'>
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Manager :</label>
							<div class="col-md-8">
								<select class="bs-select form-control" name="manager_id"
									disabled>
									<c:forEach var="manager" items="${userList}">
										<option value="${manager.id}"
											<c:if test="${user.managerId eq manager.id }">
                                            selected </c:if>>${manager.departmentId}
											- ${manager.id}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-md-4">Start
								Working Date :</label>
							<div class="col-md-8">
								<input class="form-control form-control-inline  "
									value='<fmt:formatDate value="${user.startDate}" pattern=" dd-MM-yyyy" />'
									name="startDate" disabled>
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-4">Password :</label>
							<div class="col-xs-8">
								<a href="change-password?userId=${user.id}"
									class="btn blue btn-sm btn-outline">เปลี่ยนรหัสผ่าน</a>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-4">Enabled :</label>
							<div class="col-xs-8">
								<c:if test="${user.enable eq 1}">
									<span class="label label-sm label-success"
										style="background-color: #26C281;"> Enable </span>
								</c:if>
								<c:if test="${user.enable eq 0}">
									<span class="label label-sm label-success"
										style="background-color: #e7505a;"> disable </span>
								</c:if>
								<div class="form-control-focus"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-4">Facebook :</label>
							<c:choose>
								<c:when
									test="${user.facebookid != '' && user.facebookid != null}">
									<div class="col-xs-8">
										<span class="label label-sm label-success"
											style="background-color: #26C281;"> Linked </span>

									</div>
									<div class="col-xs-12"
										style="padding-top: 7px; text-align: center;">
										<a id='unlinkFacebook' class="btn red btn-sm btn-outline">Click
											here to unlink</a>
									</div>
								</c:when>
								<c:when
									test="${user.facebookid == '' || user.facebookid == null}">
									<div class="col-xs-8">
										<span class="label label-sm label-success"
											style="background-color: #e7505a;"> Unlinked </span>
									</div>
									<div class="col-xs-12"
										style="padding-top: 7px; text-align: center;">
										<div class="fb-login-button" data-size="large"
											data-width="150px" data-button-type="continue_with"
											data-auto-logout-link="false" data-use-continue-as="true"
											onlogin="location.href='goFbAuth?page=profile'"></div>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-4">Line :</label>
							<c:choose>
								<c:when test="${user.line_id != '' && user.line_id != null}">
									<div class="col-xs-8">
										<span class="label label-sm label-success"
											style="background-color: #26C281;"> Linked </span>

									</div>
									<div class="col-xs-12" style="text-align: center;">
										<a id='unlinkLine'
											class="padding-top:7px; btn red btn-sm btn-outline">Click
											here to unlink</a>
									</div>
								</c:when>
								<c:when test="${user.line_id == '' || user.line_id == null}">
									<div class="col-xs-8">
										<span class="label label-sm label-success"
											style="background-color: #e7505a;"> Unlinked </span>
									</div>
									<div class="col-xs-12"
										style="padding-top: 7px; text-align: center;">
										<div id="web-login-button">
											<a class="center-block" href="/goLineAuth?page=profile"></a>
										</div>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>


			</div>
			<input type="hidden" name="page" value="1"> <input
				type="hidden" name="mypic" value="${user.path}">

			<div class="form-actions">
				<div class="row">
					<div class="col-xs-12" style="text-align: center;">
						<button type="submit" class="btn btn-circle blue image-upload">
							<i class="fa fa-send-o"></i> Submit
						</button>
						<button type="reset" class="btn btn-circle red">
							<i class="fa fa-close"></i> Cancel
						</button>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-paperclip font-red"></i> <span
				class="caption-subject font-red sbold uppercase">&nbsp;Leave</span>
		</div>
	</div>
	<div class="row" style="padding-bottom: 15px; padding-top: 15px;">
		<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
			<a class="dashboard-stat dashboard-stat-v2 blue-steel">
				<div class="visual">
					<i class="fa fa-photo"></i>
				</div>
				<div class="details">
					<form action="myleave_list" method="POST">
						<div class="number">
							<c:choose>
								<c:when test="${leave_1 != null}">
									<span data-counter="counterup" data-value="">${leave_1}/${quotaThisYear}</span>
									<div class="desc">${type_1}</div>
								</c:when>
								<c:when test="${leave_1 == null}">
									<span data-counter="counterup" data-value="">0/
										${quotaThisYear}</span>
									<div class="desc">${type_1}</div>
								</c:when>
							</c:choose>
						</div>
					</form>
				</div>
			</a>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
			<a class="dashboard-stat dashboard-stat-v2 yellow-gold">
				<div class="visual">
					<i class="fa fa-suitcase"></i>
				</div>
				<div class="details">
					<form action="myleave_list" method="POST">
						<div class="number">
							<c:if test="${tnow >= tend}">
								<!-- if now over april -->
										${leave_6}/${quotaLastYear}
											<%-- <c:if test="${leave_6l < 0}">
												<span data-counter="counterup" data-value="">${leave_6l}</span>
											</c:if>
											<c:if test="${leave_6l >= 0}">
												<span data-counter="counterup" data-value="">0</span>
											</c:if> --%>
								<div class="desc" style="font-size: 13px;">${type_6}</div>
							</c:if>
							<c:if test="${tnow < tend}">
								<span data-count="${quotaLastYear - leave_6}" class="counter">${quotaLastYear - leave_6}</span>
								<div class="desc" style="font-size: 13px;">${type_6}
									<u>คงเหลือ</u>
								</div>
							</c:if>
						</div>
					</form>
				</div>
			</a>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
			<a class="dashboard-stat dashboard-stat-v2 red">
				<div class="visual">
					<i class="fa fa-ambulance"></i>
				</div>
				<div class="details">
					<form action="myleave_list" method="POST">
						<div class="number">
							<c:choose>
								<c:when test="${leave_3 != null}">
									<span data-counter="counterup" data-value="">${leave_3}</span>
									<div class="desc">${type_3}</div>
								</c:when>
								<c:when test="${leave_3 == null}">
									<span data-counter="counterup" data-value="">0</span>
									<div class="desc">${type_3}</div>
								</c:when>
							</c:choose>
						</div>
					</form>
				</div>
			</a>
		</div>

		<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
			<a class="dashboard-stat dashboard-stat-v2 green-jungle">
				<div class="visual">
					<i class="fa fa-newspaper-o"></i>
				</div>
				<div class="details">
					<form action="myleave_list" method="POST">
						<div class="number">
							<c:choose>
								<c:when test="${leave_2 != null}">
									<span data-counter="counterup" data-value="">${leave_2}</span>
									<div class="desc">${type_2}</div>
								</c:when>
								<c:when test="${leave_2 == null}">
									<span data-counter="counterup" data-value="">0</span>
									<div class="desc">${type_2}</div>
								</c:when>
							</c:choose>
						</div>
					</form>
				</div>
			</a>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
			<a class="dashboard-stat dashboard-stat-v2 green">
				<div class="visual">
					<i class="fa fa-battery-quarter"></i>
				</div>
				<div class="details">
					<form action="myleave_list" method="POST">
						<div class="number">
							<c:choose>
								<c:when test="${leave_5 != null}">
									<span data-counter="counterup" data-value="">${leave_5}</span>
									<div class="desc">${type_5}</div>
								</c:when>
								<c:when test="${leave_5 == null}">
									<span data-counter="counterup" data-value="">0</span>
									<div class="desc">${type_5}</div>
								</c:when>
							</c:choose>
						</div>
					</form>
				</div>
			</a>
			<div class="col-md-2"></div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
			<a class="dashboard-stat dashboard-stat-v2 red-flamingo">
				<div class="visual">
					<i class="fa fa-pencil-square-o"></i>
				</div>
				<div class="details">
					<form action="myleave_list" method="POST">
						<div class="number">
							<c:choose>
								<c:when test="${leave_7 != null}">
									<span data-counter="counterup" data-value="">${leave_7}</span>
									<div class="desc">จำนวนวันที่รออนุมติ</div>
								</c:when>
								<c:when test="${leave_7 == null}">
									<span data-counter="counterup" data-value="">0</span>
									<div class="desc">จำนวนวันที่รออนุมติ</div>
								</c:when>
							</c:choose>
						</div>
					</form>
				</div>
			</a>
			<div class="col-md-2"></div>
		</div>
	</div>
</div>

<div class="portlet light bordered">
	<div class="test ">
		<jsp:include page="/pages-back/borrow/bTable.jsp" flush="true"></jsp:include>
	</div>
</div>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script src="../assets/global/scripts/croppie.js" type="text/javascript"></script>
<script src="../assets/global/scripts/exif.js" type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>
<script async defer
	src="https://connect.facebook.net/th_TH/sdk.js#xfbml=1&version=v3.2&appId=2445999708959494&autoLogAppEvents=1"></script>
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

	$('#unlinkLine').click(function() {
		var c = confirm("Do you want to unlink your Line account?");
		if (c == true) {
			window.location.href = "/unlinkLine?userId=${user.id}"
		}
	})
	$('#unlinkFacebook').click(function() {
		var c = confirm("Do you want to unlink your Facebook account?");
		if (c == true) {
			window.location.href = "/unlinkFacebook?userId=${user.id}"
		}
	})

	$.ajaxSetup({
	  headers: {
	    'X-CSRF-TOKEN': $('meta[name="token"]').attr('content')
	  }
	});
	//$(document).ready(function(){
	var resize = $('#upload-demo').croppie({
	    enableExif: true,
	    viewport: { 
	        width: 300,
	        height:200,
	        type: 'circle'
	    },

	    boundary: {
	        width: 200,
	        height: 200
	        
	    }
		});
	// });
	

	$('#images').on('change', function () { 
		alert("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	  var reader = new FileReader();
	    reader.onload = function (e) {
	      resize.croppie('bind',{
	        url: e.target.result
	      }).then(function(){
	        console.log('success bind image');
	      });

	    }
	    reader.readAsDataURL(this.files[0]);
	});

	 $('.image-upload').on('click', function (ev) {
		 alert("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
	  resize.croppie('result', {
	    type: 'canvas',
	    size: 'viewport'

	  }).then(function (img) {
	    $.ajax({
	      url: "{{route('upload.image')}}",
	      type: "POST",
	      data: {"image":img},
	      success: function (data) {
	        html = '<img src="' + img + '" />';
	        $("#show-crop-image").html(html);
	      }
	    });
	  });  
	}); 
</script>