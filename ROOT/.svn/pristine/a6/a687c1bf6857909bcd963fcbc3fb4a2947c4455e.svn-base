<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-user font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Role Edit</span> <span
				class="caption-helper font-red">${role.name}</span>
		</div>
		<perm:permission object="role.edit">
			<div class="actions right">
				<i class="btn btn-danger btn-xs sweet-${role.id}"
					onclick="_gaq.push(['_trackEvent', 'example', 'try', 'Primary']);">
					<i class="fa fa-trash"></i> delete</i>
			</div>
			<div class="actions right">
				<a class="btn btn-circle btn-icon-only btn-default fullscreen"
					href="javascript:;" data-original-title="" title=""></a>&nbsp;
			</div>
		</perm:permission>

	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="role-perform-edit" class="form-horizontal" method="post"
			autocomplete="off">
			<div class="form-body">
				<div class="form-group form-lg-line-input">
					<label class="col-md-3 control-label" style="color: #666666;">Role
						Id</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Role ID"
							maxlength="32" name="role.id" value="${role.id}" required>
						<input type="hidden" class="form-control" placeholder="Role ID"
							name="roleId" value="${role.id}" required>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-lg-line-input ">
					<label class="col-md-3 control-label" style="color: #666666;">Name</label>
					<div class="col-md-4 ">
						<input type="text" class="form-control" placeholder="Username"
							maxlength="64" name="role.name" value="${role.name}" required>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-lg-line-input">
					<label class="col-md-3 control-label" style="color: #666666;">Description</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Username"
							maxlength="200" name="role.description"
							value="${role.description}" required>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">Authorization</label>
					<div class="col-md-9">
                                   <table id="myTable" class="table table-bordered small table-striped table-condensed table-hover compact">
                                  <thead>
                                      <tr>
                                        <th><center>#</center></th>
										<th><center>ID</center></th>
										<th>Description</th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                   	<c:forEach var="ao" items="${aoList}" varStatus="Count">
									<tr>
                                            	<td>
												<div class="md-checkbox small">
												<input type="checkbox" name="authId"
														id="checkbox${Count.count}" class="md-check"
														value="${ao.authorizedObjectId}"
														<c:forEach var="rao" items="${raoList}">
																<c:if test="${ao.authorizedObjectId eq rao.authorizedObjectId}"> checked </c:if>
														</c:forEach> >
															<label class="label-xs" for="checkbox${Count.count}"> <span class="inc"></span>
																						<span class="check"></span> <span class="box"></span>
														</label> <span></span>
												</div>

												</td>
												<td>
												${ao.authorizedObjectId}
												</td>
									<td><div class="label-sm">${ao.description}</div></td>   
										</tr>
			</c:forEach>
			
                                  </tbody>
                                  </table>
                      </div>               
<%--
					<div class="col-md-9">
						<c:forEach var="ao" items="${aoList}" varStatus="Count">

							<div class="col-sm-6 col-md-6 col-lg-6">
								<div class="md-checkbox small">
									<input type="checkbox" name="authId"
										id="checkbox${Count.count}" class="md-check"
										value="${ao.authorizedObjectId}"
										<c:forEach var="rao" items="${raoList}">
							<c:if test="${ao.authorizedObjectId eq rao.authorizedObjectId}"> checked </c:if>
						</c:forEach>

									<label class="label-xs" for="checkbox${Count.count}"> <span class="inc"></span>
										<span class="check"></span> <span class="box"></span>
										${ao.authorizedObjectId} - ${ao.name}
									</label> <span></span>
								</div>
							</div>
						</c:forEach>
					</div>
					
					 --%>
					
				</div>

			</div>
			<perm:permission object="role.edit">
				<div class="form-actions action right">
					<div class="row ">
						<div class="col-md-12" style="text-align: center;">
							<button type="submit" class="btn blue" id="save">
								<i class="fa fa-save"></i>&nbsp;Save
							</button>
							<button type="reset" class="btn red"
								onclick="_gaq.push(['_trackEvent', 'example', 'try', 'Primary'])">
								<i class="fa fa-close"></i> Cancel
							</button>
						</div>
					</div>
				</div>
			</perm:permission>
		</form>
		<!-- END FORM-->
	</div>
</div>

<script>
	document.querySelector('.sweet-${role.id}').onclick = function() {
		swal(
				{
					html : true,
					title : "User may using this role!",
					text : "<c:forEach var="user" items="${userlist}" varStatus="Count"><span>${Count.count}.${user.userId}<br></span></c:forEach>"+
					'<br>delete this role?',
					type : "warning",
					showCancelButton : true,
					confirmButtonClass : 'btn-danger',
					confirmButtonText : 'YES'
				}, function(inputValue) {
					if (inputValue === false)
						return false;
					if (inputValue === "") {
						return false
					}
					document.location = "role-delete?id=${role.id}"; //?id คือ parameter
				});
	};

	$('#save').click(function() {
		location.reload();
	});
	
	$(document).ready( function () {
	    $('#myTable').DataTable(
	    		{
	    	        paging : false,
	    	        "searching" : false
	    		});
	} );
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />

<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>

<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
	
	