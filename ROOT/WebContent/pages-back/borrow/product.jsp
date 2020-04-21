<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%-- <c:forEach var="eqi" items="${eqid}"> --%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-folder-open"></i> Product Detail
			</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<form action="productupdate" class="form-horizontal" method="post"
			enctype="multipart/form-data">
			<input type="hidden" class="form-control" value="${eqid.equipmentId}"
				name="eqid">
			<center style="margin-top: 5%;">
				<div class="fileinput fileinput-new" data-provides="fileinput">
					<div class="fileinput-new thumbnail"
						style="width: 200px; height: 150px;">
						<a href="${eqid.image}"> <c:if test="${eqid.image == null }">
								<img
									src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
									alt="cc">
							</c:if> <c:if test="${eqid.image != null }">
								<img class="timeline-body-img pull-left" src="${eqid.image}"
									alt="" style="text-align: center; width: 200px; height: 150px;"></a>
						</c:if>
					</div>
					<div class="fileinput-preview fileinput-exists thumbnail"
						style="max-width: 200px; max-height: 150px; line-height: 10px;"></div>
					<div>
						<span class="btn default btn-file"> <span
							class="fileinput-new"> Select File</span> <span
							class="fileinput-exists"> Change </span> <input type="hidden"
							value="" name="..."> <input type="file" name="fileUpload"
							id="myFile" value="">
						</span> <a href="javascript:;" class="btn red fileinput-exists"
							data-dismiss="fileinput"> Remove </a>
					</div>
				</div>
			</center>
			<div class="portlet-body">
				<div class="form-body">
					<div class="form-group form-lg-line-input col-md-12">
						<label class="col-md-3 control-label" for="form_control_1">Type
							: </label>
						<div class="col-md-7">
							<select class="bs-select form-control" name="type" readonly>
								<c:if test="${eqid.type.toString()=='o'}">
									<option value="o" hidden>Other</option>
								</c:if>
								<c:if test="${eqid.type.toString()=='c'}">
									<option value="c" hidden>Notebook</option>
								</c:if>
								<!-- <option value="c">Computer</option>
									<option value="o">Other</option> -->

							</select>

						</div>
					</div>
					<c:choose>
						<c:when test="${eqid.type eq 'o' }">
							<div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group form-lg-line-input col-md-12">
											<label class="col-md-3 control-label" for="form_control_1">Item
												NO : </label>
											<div class="col-md-8">
												<input type="text" class="form-control"
													value="${eqid.itemNo}" name="itemNo" maxlength="13"
													id="itemnoid">
												<div class="form-control-focus"></div>
												
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-lg-line-input col-md-12">
											<label class="col-md-3 control-label" for="form_control_1">Serial
												NO : </label>
											<div class="col-md-8">
												<input type="text" class="form-control"
													value="${eqid.serialNo}" name="serialno" maxlength="60"
													id="serialnoid">
												<div class="form-control-focus"></div>
												
											</div>
										</div>
									</div>
								</div>
							</div>

							<div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group form-lg-line-input col-md-12">
											<label class="col-md-3 control-label" for="form_control_1">Name
												: </label>
											<div class="col-md-8">
												<input type="text" name="name" class="form-control"
													value="${eqid.name }" maxlength="250" id="nameid">
												<div class="form-control-focus"></div>
												
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-lg-line-input col-md-12">
											<label class="col-md-3 control-label" for="form_control_1">Detail
												: </label>
											<div class="col-md-8">
												
												<div class="form-control-focus"></div>
												
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group form-lg-line-input col-md-12">
										<label class="col-md-3 control-label" for="form_control_1">Location
											: </label>
										<div class="col-md-8">
											<input type="text" name="location" class="form-control"
												value="${eqid.location }" maxlength="250">
											<div class="form-control-focus"></div>
											
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group form-lg-line-input col-md-12">
										<label class="col-md-3 control-label" for="form_control_1">Amount
											: </label>
										<div class="col-md-8">
											<input type="text" class="form-control"
												value="${eqid.amount}" name="amount" readonly>
											<div class="form-control-focus"></div>
											
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group form-lg-line-input col-md-12">
										<label class="col-md-3 control-label" for="form_control_1">Date
											: </label>
										<div class="col-md-8">
											<input type="text" name="timeUpdate" class="form-control"
												value="<fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm"  />"
												readonly>
											<div class="form-control-focus"></div>
											
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group form-lg-line-input col-md-12">
										<label class="col-md-3 control-label" for="form_control_1">Status
											: </label>
										<div class="col-md-8">
											<select class="bs-select form-control" name="status">
												<c:if test="${eqid.status.toString()=='A'}">
													<option value="A" hidden>Available</option>
												</c:if>
												<c:if test="${eqid.status.toString()=='F'}">
													<option value="F" hidden>Fixed</option>
												</c:if>
												<c:if test="${eqid.status.toString()=='C'}">
													<option value="C" hidden>Corrupted</option>
												</c:if>
												<c:if test="${eqid.status.toString()=='B'}">
													<option value="B" hidden>Borrowed</option>
												</c:if>
												<c:if test="${eqid.status.toString()=='L'}">
													<option value="L" hidden>Lost</option>
												</c:if>
												<c:if test="${eqid.status.toString()=='D'}">
													<option value="D" hidden>Waiting for Approved</option>
												</c:if>
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
							</div>
				</div>
			</div>
			</c:when>
			<c:otherwise>
				<div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Item
									NO : </label>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${eqid.itemNo}"
										name="itemNo" maxlength="13" required id="itemnoid">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Serial
									NO : </label>
								<div class="col-md-8">
									<input type="text" class="form-control" id="serialnoid"
										value="${eqid.serialNo}" name="serialno" maxlength="60"
										required>
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Name
									: </label>
								<div class="col-md-8">
									<input type="text" name="name" class="form-control" id="nameid"
										value="${eqid.name }" maxlength="250" required>
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<!-- <div class="form-group form-lg-line-input col-md-12"> -->
								<label class="col-md-3 control-label" for="form_control_1">Process
									: </label>
								<div class="col-md-8">
									<input type="text" name="process" class="form-control"
										value="${eqid.process}" maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Battery
									: </label>
								<div class="col-md-8">
									<input type="text" name="battery" class="form-control"
										value="${eqid.battery }" maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">HDD
									: </label>
								<div class="col-md-8">
									<input type="text" name="hdd" class="form-control"
										value="${eqid.hdd }" maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Windows
									: </label>
								<div class="col-md-8">
									<input type="text" name="window" class="form-control"
										value="${eqid.windows}" maxlength="60">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Ram
									: </label>
								<div class="col-md-8">
									<input type="text" name="ram" =
								class="form-control"
										value="${eqid.ram }" maxlength="120">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Location
									: </label>
								<div class="col-md-8">
									<input type="text" name="location" class="form-control"
										value="${eqid.location }" maxlength="1000">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Amount
									: </label>
								<div class="col-md-8">
									<input type="text" class="form-control" value="${eqid.amount}"
										name="amount" readonly>
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Date
									: </label>
								<div class="col-md-8">
									<input type="text" name="timeUpdate" class="form-control"
										value="<fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm"  />"
										readonly>
									<div class="form-control-focus"></div>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group form-lg-line-input col-md-12">
								<label class="col-md-3 control-label" for="form_control_1">Status
									: </label>
								<div class="col-md-8">
									<select class="bs-select form-control" name="status">
										<c:if test="${eqid.status.toString()=='A'}">
											<option value="A" hidden>Available</option>
										</c:if>
										<c:if test="${eqid.status.toString()=='F'}">
											<option value="F" hidden>Fixed</option>
										</c:if>
										<c:if test="${eqid.status.toString()=='C'}">
											<option value="C" hidden>Corrupted</option>
										</c:if>
										<c:if test="${eqid.status.toString()=='B'}">
											<option value="B" hidden>Borrowed</option>
										</c:if>
										<c:if test="${eqid.status.toString()=='L'}">
											<option value="L" hidden>Lost</option>
										</c:if>
										<c:if test="${eqid.status.toString()=='D'}">
											<option value="D" hidden>Waiting for Approved</option>
										</c:if>

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
					</div>
					<div class="row">
						<div class="col-md-6">
							
						</div>
						<div class="col-md-6">
						<div class="form-group form-lg-line-input col-md-12">
								<label class="control-label col-md-3">Remark : </label>
								<div class="col-md-8">
									<input type="text" name="detail" class="form-control" value="${eqid.detail}">
									<div class="form-control-focus"></div>
									
								</div>
							</div>
													</div>
					</div>
				</div>
			</c:otherwise>
			</c:choose>


			<div class="form-actions action right">
				<div class="row ">
					<div class="col-md-12">
						<center>
							<button type="submit" class="btn blue" id="demo15">
								<i class="fa fa-save"></i>&nbsp;Save
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

<div class="portlet light bordered" style="margin-top: 50px;">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-history font-blue"></i> <span
				class="caption-subject font-red sbold ">Borrow History</span>
		</div>
		
		

	</div>
	
	<div class="portlet-body">
	
		<div class="table-responsive">
			<table
				class="table table-bordered table-striped table-condensed flip-content table-hover ">
				<thead>
					<tr>
					    <th><center>ID Borrow</center></th>
						<th><center>Borrow By</center></th>
						<th><center>From</center></th>
						<th><center>To</center></th>
						<th><center>Status</center></th>
						<th><center>Time Create</center></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="borrow_history" items="${borrow_history}">
						<tr>
						     	<td><center><a href="approve_id?borrowid=${borrow_history.borrow_id}">${borrow_history.borrow_id}</a></center></td>
								<td><center>${borrow_history.user_borrowid}</center></td>
								<td><center><fmt:formatDate value="${borrow_history.date_start}"
													pattern="dd-MM-yyyy"></fmt:formatDate></center></td>
								
								<td><center><fmt:formatDate value="${borrow_history.date_end}"
													pattern="dd-MM-yyyy"></fmt:formatDate></center></td>
								<c:if test="${borrow_history.status == 'W'  }">
								<td style="text-align: center;"><span
														class="label label-sm label-info">Waiting for
															Approved </span></td>
								</c:if>	
								
									<c:if test="${borrow_history.status == 'B'  }">
								<td style="text-align: center;"><span
														class="label label-sm label-warning">Borrowing
															 </span></td>
								</c:if>							
					
						<c:if test="${borrow_history.status == 'R' }">
								<td style="text-align: center;"><span
														class="label label-sm label-default"> Request for Return
														 </span></td>
								</c:if>	
													
						<c:if test="${borrow_history.status == 'F' }">
								<td style="text-align: center;"><span
														class="label label-sm label-primary"> Finish
														 </span></td>
								</c:if>								
											
						<c:if test="${borrow_history.status == 'C' }">
								<td style="text-align: center;"><span
														class="label label-sm label-danger"> Cancel
														 </span></td>
								</c:if>			
					<c:if test="${borrow_history.statusborrow == 'U' || borrow_history.statusborrow == 'X'  }">
								<td style="text-align: center;"><span
														class="label label-sm label-danger"> Unapproved
														 </span></td>
								</c:if>		
								
								<td><center><fmt:formatDate    value="${borrow_history.time_create}"
													pattern="dd-MM-yyyy HH:mm:ss"></fmt:formatDate></center></td>
					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>


<%-- </c:forEach> --%>

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
		/*  _$tag____________________________ */
		/*  alert(size);  */
	});
</script>
<script>
	function ss() {
		document.location = "borrowing";
	}
</script>

<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<script>
	src = "../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type = "text/javascript"
</script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>

<script
	src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>

<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>

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
