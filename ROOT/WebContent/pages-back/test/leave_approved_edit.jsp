<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-latest.min.js"></script>

<style>
@media only screen and (max-width: 1200px) {
	.paddingmd {
		padding-top: 15px !important;
	}
}

@media only screen and (max-width: 960px) {
	.hiddensm {
		visibility: hidden;
	}
}

@media only screen and (max-width: 1300px) {
	.paddinglg {
		margin-left: 50px !important;
	}
}

@media only screen and (max-width: 769px) {
	.centerxs {
		display: block;
		margin-left: auto;
		margin-right: auto;
		text-align: center !important;
	}
}

.item-head {
	width: width:100% !important;
}

.select2-container--bootstrap .select2-results__group {
	color: #fff;
	background-color: gray;
	display: block;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857;
	white-space: nowrap
}

.boxContent {
	border: 1px solid lightblue;
	padding: 15px;
	margin: 10px;
	width: 90%;
}
</style>
<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="portlet light bordered">

	<div class="portlet-title">
		<div class="caption">
			<i class="icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Leave
				Approve</span>
		</div>
		<div class="actions">
			<a class="btn  btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
		<div class="actions">
			<button type="button" class="btn green-jungle" id="addLeave"
				style="margin-right: 30px; font-size: 16px !important;"
				onclick="add()">
				<i class="fa fa-plus"></i>&nbsp;Add leave
			</button>
		</div>
	</div>
	<div class="portlet-body">
		<div class="tools">
			<a href="javascript:;" class="collapse" data-original-title=""
				title=""> </a> <a href="#portlet-config" data-toggle="modal"
				class="config" data-original-title="" title=""> </a> <a
				href="javascript:;" class="reload" data-original-title="" title="">
			</a> <a href="javascript:;" class="remove" data-original-title=""
				title=""> </a>
		</div>
		<!--  search bar in 1 line -->
		<form id="actionForm" action="">
			<div class="portlet-body">
				<div class="form-group form-lg-line-input">
					<div>
						<div class="row">
							<div id="selectBar" class="col-lg-11">
								<label class="col-xs-8 col-sm-1 col-md-1 col-lg-1 control-label"
									for="form_control_1" style="padding-right: 0px !important;">Name
									:</label>
								<!-- start staff select -->
								<div class="col-xs-8 col-sm-5 col-md-6  col-lg-3"
									style="padding-left: 0px !important;">
									<select id="admin" class="form-control select2me"
										name="nameSelect">
										<option value="All" id="All">All</option>
										<optgroup label="Enable">
											<c:forEach var="user" items="${userseq}">

												<c:if test="${user.enable == 1 }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:forEach>
										</optgroup>
										<optgroup label="Disable">
											<c:forEach var="user" items="${userseq}">

												<c:if test="${user.enable == 0 }">
													<option value="${user.id}" id="${user.id}"
														<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
														- ${user.name}</option>
												</c:if>
											</c:forEach>
										</optgroup>
									</select>
								</div>
								<!-- end staff select -->
							</div>

						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-11">
								<!-- xx edit -->
								<label class="col-xs-2 col-sm-1 col-md-1 col-lg-1 control-label"
									for="form_control_1" style="padding-right: 0px !important">Status
									: </label>
								<!-- type select -->
								<div class="col-xs-8 col-sm-5 col-md-4 col-lg-2"
									style="padding-left: 0px !important">
									<select class="form-control select2me" name="appr">
										<option value="0" id="All1"
											<c:if test="${ appr == 0 }"><c:out value="selected=selected"/></c:if>>
											Waiting approve</option>
										<option value="3"
											<c:if test="${ appr == 3 }"><c:out value="selected=selected"/></c:if>>All</option>
										<option value="1"
											<c:if test="${ appr == 1 }"><c:out value="selected=selected"/></c:if>>Approved</option>
										<option value="2"
											<c:if test="${ appr == 2 }"><c:out value="selected=selected"/></c:if>>Reject</option>
									</select>
								</div>
								<!-- xx edit -->
								<!-- type label -->
								<label class="col-xs-2 col-sm-1 col-md-1 col-lg-1 control-label"
									for="form_control_1" style="padding-right: 0px !important">Type
									:</label>
								<!-- type select -->
								<div class="col-xs-8 col-sm-5 col-md-4 col-lg-2"
									style="padding-left: 0px !important">
									<select class="form-control select2me" name="type"
										style="padding-left: 0px !important; padding-right: 0px">
										<option value="All" id="All1">All</option>
										<c:forEach var="leavetypelistChoice"
											items="${leavetypelistChoice}">
											<option value="${leavetypelistChoice.leaveTypeId}"
												id="${leavetypelistChoice.leaveTypeId}"
												<c:if test="${type == leavetypelistChoice.leaveTypeId }"><c:out value="selected=selected"/></c:if>>${leavetypelistChoice.leaveTypeName}</option>
										</c:forEach>
									</select>
								</div>
								<!--end  type select -->
								<label
									class="centersx col-xs-12 col-sm-1 col-md-1 col-lg-1 control-label paddingmd"
									style="padding-right: 0px !important;">Date :</label>
								<div
									class="centerxs col-sx-12 col-sm-6  col-md-7 col-lg-4 paddingmd"
									style="padding-left: 0px !important;">
									<div
										class="input-group input-large date-picker input-daterange"
										data-date-format="dd-mm-yyyy"
										style="padding-left: 0px !important">
										<c:if test="${startdate == null}">
											<input type="text" class="form-control cannot"
												name="startdate" id="startdate" size="13"
												data-date-format="dd-MM-yyyy" placeholder="Start date"
												value="<fmt:formatDate type="date" value="${now}" pattern="01-01-yyyy"  />">
										</c:if>
										<c:if test="${startdate != null}">
											<input type="text" class="form-control cannot"
												name="startdate" id="startdate"
												data-date-format="dd-MM-yyyy" placeholder="Start date"
												size="13"
												value="<fmt:formatDate type="date" value="${startdate}" pattern="dd-MM-yyyy"  />">
										</c:if>
										<span class="input-group-addon"> To </span>
										<c:if test="${enddate == null}">
											<input type="text" class="form-control cannot" name="enddate"
												id="enddate" data-date-format="dd-MM-yyyy"
												placeholder="End date" size="13"
												value="<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy"  />">
										</c:if>
										<c:if test="${enddate != null}">
											<input type="text" class="form-control cannot" name="enddate"
												id="enddate" data-date-format="dd-MM-yyyy"
												placeholder="End date" size="13"
												value="<fmt:formatDate type="date" value="${enddate}" pattern="dd-MM-yyyy"  />">
										</c:if>
									</div>
								</div>
								<div align="center"
									class="col-xs-10 col-sm-3 col-md2 col-lg-1 paddingmd">
									<button id="btnSearch" class="btn paddinglg sbold blue">
										<i class="fa fa-search"></i> Search
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--  Dashboard  -->
				<div id="dashboard">
					<div class="row">
						<div class="col-md-12 col-lg-12">
							<!-- personal leave -->
							<div class="col-lg-4">
								<a class="dashboard-stat dashboard-stat-v2 blue-steel">
									<div class="visual">
										<i class="fa fa-photo"></i>
									</div>
									<div class="details">
										<form action="myleave_list" method="POST">
											<div class="number">
												<c:choose>
													<c:when test="${leave_1 != null}">
														<div id="divType1">
															<span id="leaveType1" data-counter="counterup"
																data-value="">${leave_1} </span> <span
																id="quotaThisYear">/ ${quotaThisYear}</span>
														</div>
														<div class="desc">${type_1}</div>
													</c:when>
													<c:when test="${leave_1 == null}">
														<span id="leaveType1" data-counter="counterup"
															data-value="">0 / ${quotaThisYear}</span>
														<div class="desc">${type_1}</div>
													</c:when>
												</c:choose>
											</div>
										</form>
									</div>

								</a>
							</div>
							<!-- sick leave -->
							<div class="col-md-4">
								<a class="dashboard-stat dashboard-stat-v2 red">
									<div class="visual">
										<i class="fa fa-ambulance"></i>
									</div>
									<div class="details">

										<form action="myleave_list" method="POST">
											<div class="number">
												<c:choose>
													<c:when test="${leave_3 != null}">
														<div id="divType3">
															<span id="leaveType3" data-counter="counterup"
																data-value="">${leave_3}</span>
														</div>
														<div class="desc">${type_3}</div>
													</c:when>
													<c:when test="${leave_3 == null}">
														<span id="leaveType3" data-counter="counterup"
															data-value="">0</span>
														<div class="desc">${type_3}</div>
													</c:when>
												</c:choose>
											</div>
										</form>

									</div>

								</a>
							</div>
							<!-- leave quota from last year -->
							<div class="col-lg-4">
								<a class="dashboard-stat dashboard-stat-v2 purple">
									<div class="visual">
										<i class="fa fa-credit-card"></i>
									</div>
									<div class="details">
										<form action="myleave_list" method="POST">
											<div class="number">
												<c:choose>
													<c:when test="${leave_1 != null}">
														<div id="divType6">
															<span id="leaveType6" data-counter="counterup"
																data-value="sad">${leave_6}</span> <span
																id="quotaLastYear">/ ${quotaLastYear}</span>
														</div>
														<div class="desc">${type_6}</div>
													</c:when>
													<c:when test="${leave_1 == null}">
														<span id="leaveType6" data-counter="counterup"
															data-value="">0 / ${quotaLastYear}</span>
														<div class="desc">${type_6}</div>
													</c:when>
												</c:choose>
											</div>
										</form>
									</div>

								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<br>
		</form>

		<!-- start content portlet -->
		<div id="myLeave" class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption caption-md">
					<i class="icon-bar-chart theme-font hide"></i> <span
						class="caption-subject font-blue-madison bold uppercase">Leave List</span> <span class="caption-helper"></span>
				</div>
				<div class="inputs">
					<div class="portlet-input input-inline input-small ">
						<div class="input-icon right"></div>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<div data-always-visible="1" data-rail-visible1="0"
					data-handle-color="#D7DCE2">
					<div class="general-item-list">
						<%-- <c:forEach var="leave" items="${leave}" varStatus="status">
							<c:if test="${leave.leave_status_id.toString() == '0'}">
								<center>
									<div
										style="border: 1px solid lightblue; padding: 15px; margin: 10px; width: 90%;">
										<div class="row">
											<div align="center"
												class="col-xs-12 col-sm-3 col-md-3 col-lg-2">
												<div class="row centerxs">
													<div align="center"
														class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<c:choose>
															<c:when test="${leave.path == null}">
																<img width="60px" height="60px"
																	style="object-fit: cover; margin: 3px;"
																	src="${leave.path}">
															</c:when>
															<c:otherwise>
																<img width="60px" height="60px"
																	style="object-fit: cover; margin: 3px;"
																	src="${leave.path}"
																	onerror="this.onerror=null;this.src='pages-back/img/profile.png';">
															</c:otherwise>
														</c:choose>
														<div align="center"
															class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
															<a title="ID: ${leave.leave_id}"
																href="LeaveEdit?id=${leave.leave_id}"
																class="item-name primary-link"> <font
																color="#105AB0" size="4">${leave.user_id} </font></a>
														</div>
													</div>
												</div>
											</div>

											<div class="centerxs col-xs-12 col-sm-6 col-md-5 col-lg-6">
												<div style="text-align: left;"
													class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<font size="3" class="open-sans" color="#366FB1"> <b>${leave.leave_type_name}
															&nbsp;&nbsp;&nbsp;&nbsp; <c:set var="amoutLeaveDay"
																value="${leave.no_day}" /> <span hidden
															id="amountLeaveType-${leave.leave_id}">
																${leave.leave_type_id} </span> <span
															id="amountLeave-${leave.leave_id}"> <fmt:formatNumber
																	type="number" pattern="#.##" value="${amoutLeaveDay}" /></span>
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;วัน
													</b>
													</font>
												</div>

												<div style="text-align: left;"
													class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<font size="3" class="open-sans" color="#366FB1"><fmt:formatDate
															value="${leave.start_date}" pattern="dd-MMM-yyyy"></fmt:formatDate>
														&nbsp; ~&nbsp; &nbsp; <fmt:formatDate
															value="${leave.end_date}" pattern="dd-MMM-yyyy"></fmt:formatDate></font>
												</div>
												<div style="text-align: left;"
													class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<font size="3" class="open-sans" color="#366FB1">Submit
														date : <fmt:formatDate value="${leave.time_create}"
															pattern="dd-MM-yyyy HH:mm"></fmt:formatDate>
													</font>
												</div>

												<div style="text-align: left;"
													class="centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p>
														<font size="3" class="open-sans" color="#366FB1">
															${leave.description}</font>
													</p>
												</div>
											</div>
											<div class="col-xs-12 col-sm-2 col-md-4 col-lg-4">
												<div class="row">
													<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
														<c:if test="${leave.leave_status_id.toString() == '0'}">

															<div class="wait-${leave.leave_id}">
																<span class="item-status"> <span
																	class="badge badge-empty badge-warning"></span> <a
																	class="centerxs hiddensm"> Waiting for approve </a>
																</span>
															</div>


															<div hidden class="app-${leave.leave_id}">
																<span class="item-status"> <span
																	class="badge badge-empty badge-success"></span> <a
																	class="centerxs hiddensm"> Approved </a>
																</span>
															</div>
														</c:if>

														<c:if test="${leave.leave_status_id.toString() == '1'}">
															<span class="item-status"> <span
																class="badge badge-empty badge-success"></span> <a
																class="centerxs hiddensm"> Approved </a>
															</span>

														</c:if>
														<c:if test="${leave.leave_status_id.toString() == '2'}">
															<span class="item-status"> <span
																class="badge badge-empty badge-danger"></span> <a
																class="centerxs hiddensm"> Reject </a>
															</span>
														</c:if>
													</div>
												</div>
											</div>
											<!-- button -->
											<div class="col-xs-12 col-sm-2 col-md-4 col-lg-4">
												<div class="row">
													<br> <br>
													<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
														<button class="btn btn-warning btn-circle"
															onclick="sweet(${leave.leave_id})">Approve</button>
														<button class="btn btn-warning btn-circle"
															onclick="sweet(${leave.leave_id})">Approve</button>
													</div>
												</div>
											</div>
											<!-- / button -->
											<!-- this below tag /div is main row -->
										</div>
										<!-- this below tag /div is css box -->
									</div>
								</center>
							</c:if>
						</c:forEach> --%>
						<div class="portlet-body">
							<div id="divShow" data-always-visible="1" data-rail-visible1="0"
								data-handle-color="#D7DCE2">
								<div id="divList" class="general-item-list"></div>
							</div>
						</div>

						<!-- end of portlet content -->
					</div>
				</div>
			</div>
		</div>
		<!-- end of main portlet body -->
	</div>
</div>
<script>
function sweet(id) {
	var user = "${userRole[0].user_id}";
	swal({
		title : "Approve Success!",
		text : "",
		type : "success",
		showCancelButton : false,
		closeOnConfirm : true
	}, function(isConfirm) {
		if (isConfirm) {
			console.log("Approve: " + id);
			$.ajax({
		    	type: "post",
		    	url: "${pageContext.request.contextPath}/leave_update_status",
		    	crossorigin: true,
		    	dataType: "json",
		    	data: {
		    		"leaveId" : id,
		    		"user" : user
		    	},
		    	success: function(json){
		    		console.log(json);
		    		$( "#btnSearch" ).trigger( "click" );
		  		},
		  		error: function(jqXHR, textStatus, errorThrown){
		      		alert('error update');
		  		}  
		    });
		}
	});
	
	
}
</script>
<script>
$(document).ready(function(){
	getRole();
	$( "#btnSearch" ).trigger( "click" );
});
</script>
<script>
function getRole() {
	var role = "${userRole[0].role_id}";
	var data;
	
	if(role==("admin"||"HR")){
		console.log("${userRole[0].role_id}");
		data = {};
	}else{
		console.log("${userRole[0].role_id}");
		$('#admin').attr('disabled',true);
		$("#showSelectBar").hide();
		data = {nameSelect: "${onlineUser.id}"};
	}
	return data
}

function checkRole() {
	var role = "${userRole[0].role_id}";
	
	
	if(role==("admin"||"HR")){
		return true;
	}else{
		return false;
	}
}
</script>
<script>
  $("#btnSearch").click(function(e){  
	  e.preventDefault();
	  var jsonData = getRole();
	  var formData = $("#actionForm").serializeArray();
	   $.each(formData, function() {
	        if (jsonData[this.name]) {
	           if (!jsonData[this.name].push) {
	               jsonData[this.name] = [jsonData[this.name]];
	           }
	           jsonData[this.name].push(this.value || '');
	       } else {
	           jsonData[this.name] = this.value || '';
	       }
	   });
	searchShowDashboard(jsonData);
	searchShowQuota(jsonData);
    $.ajax({
    	type: "post",
    	url: "${pageContext.request.contextPath}/leave_approved_edit_json",
    	crossorigin: true,
    	dataType: "json",
    	data: jsonData,
    	success: function(json){
            console.log(json);
            var divShow = document.getElementById("divShow");
            document.getElementById("divList").remove();
            var divList = document.createElement("div");
            divList.setAttribute('id', 'divList');
            divShow.appendChild(divList);
            $.each(json, function () {
                console.log("Id: " + this.user_id);
                console.log("Leave Id: " + this.leave_id);
                console.log("Leave Type: " + this.leave_type_name);
                console.log("Leave Status: " + this.leave_status_id);
                console.log(" ");
              	//profile
                var	center = document.createElement('center');
                center.setAttribute('id', 'contentAll');
                divList.appendChild(center);
                var divStyle = document.createElement('div');
                divStyle.setAttribute("style", "border: 1px solid lightblue; padding: 15px; margin: 10px; width: 90%;");
                center.appendChild(divStyle);
                var divRow = document.createElement('div');
                divRow.setAttribute("class", "row");
                divStyle.appendChild(divRow);
                var divAlignCenter = document.createElement('div');
                divAlignCenter.setAttribute("align", "center");
                divAlignCenter.setAttribute("class", "col-xs-12 col-sm-3 col-md-3 col-lg-2");
                divRow.appendChild(divAlignCenter);
                var divRowCenterxs = document.createElement('div');
                divRowCenterxs.setAttribute("class", "row centerxs");
                divAlignCenter.appendChild(divRowCenterxs);
                var divAlignCenterxs = document.createElement('div');
                divAlignCenterxs.setAttribute("align", "center");
                divAlignCenterxs.setAttribute("class", "col-xs-12 col-sm-12 col-md-12 col-lg-12");
                divRowCenterxs.appendChild(divAlignCenterxs);
                //show img
                var img = document.createElement("IMG");
                img.setAttribute("src", this.path);
                img.setAttribute("width", "60px");
                img.setAttribute("height", "60px");
                img.setAttribute("style", "object-fit: cover; margin: 3px;");
                img.setAttribute("onerror", "this.onerror=null;this.src='pages-back/img/profile.png';");
                divRowCenterxs.appendChild(img);
                //close show img //show ID
                var divId = document.createElement('div');
                divId.setAttribute("align", "center");
                divId.setAttribute("class", "col-xs-12 col-sm-12 col-md-12 col-lg-12");
                divRowCenterxs.appendChild(divId);
                
                var aId = document.createElement('a');
                aId.setAttribute("title", "ID:"+ this.leave_id);
                aId.setAttribute("href", "LeaveEdit?id="+ this.leave_id);
                aId.setAttribute("class", "item-name primary-link");
                divId.appendChild(aId);
                
                var fontId = document.createElement('font');
                fontId.setAttribute("color", "#105AB0");
                fontId.setAttribute("size", "4");
                var textFontId = document.createTextNode(this.user_id);
                fontId.appendChild(textFontId);
                aId.appendChild(fontId);
              	//close show ID //show detail
              	var divDetail = document.createElement('div');
              	divDetail.setAttribute("class", "centerxs col-xs-12 col-sm-6 col-md-5 col-lg-6");
              	divRow.appendChild(divDetail);
              	//no day
              	var divStyleTextNoDay = document.createElement('div');
              	divStyleTextNoDay.setAttribute("style", "text-align: left;");
              	divStyleTextNoDay.setAttribute("class", "centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12");
              	divDetail.appendChild(divStyleTextNoDay);
              	var fontDetailNoDay = document.createElement('font');
              	fontDetailNoDay.setAttribute("size", "3");
              	fontDetailNoDay.setAttribute("class", "open-sans");
              	fontDetailNoDay.setAttribute("color", "#366FB1");
              	divStyleTextNoDay.appendChild(fontDetailNoDay);
              	var bDetailNoDay = document.createElement('b');
              	var textBDetailNoDay= document.createTextNode(this.leave_type_name + '\u00A0\u00A0\u00A0\u00A0' + this.no_day +'\u00A0\u00A0\u00A0\u00A0วัน');
              	bDetailNoDay.appendChild(textBDetailNoDay);
              	fontDetailNoDay.appendChild(bDetailNoDay);
              	//close no day // show day
              	var divStyleTextShowDay = document.createElement('div');
              	divStyleTextShowDay.setAttribute("style", "text-align: left;");
              	divStyleTextShowDay.setAttribute("class", "centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12");
              	divDetail.appendChild(divStyleTextShowDay);
              	var fontDetailShowDay = document.createElement('font');
              	fontDetailShowDay.setAttribute("size", "3");
              	fontDetailShowDay.setAttribute("class", "open-sans");
              	fontDetailShowDay.setAttribute("color", "#366FB1");
              	divStyleTextShowDay.appendChild(fontDetailShowDay);
              	var aShowDay = document.createElement('a');
              	var textAShowDay= document.createTextNode(this.start_date + '\u00A0\u00A0~\u00A0\u00A0' + this.end_date);
              	aShowDay.appendChild(textAShowDay);
              	fontDetailShowDay.appendChild(aShowDay);
             	//close show day // show submit day
              	var divStyleTextShowsubmitDay = document.createElement('div');
              	divStyleTextShowsubmitDay.setAttribute("style", "text-align: left;");
              	divStyleTextShowsubmitDay.setAttribute("class", "centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12");
              	divDetail.appendChild(divStyleTextShowsubmitDay);
              	var fontDetailShowsubmitDay = document.createElement('font');
              	fontDetailShowsubmitDay.setAttribute("size", "3");
              	fontDetailShowsubmitDay.setAttribute("class", "open-sans");
              	fontDetailShowsubmitDay.setAttribute("color", "#366FB1");
              	divStyleTextShowsubmitDay.appendChild(fontDetailShowsubmitDay);
              	var aShowsubmitDay = document.createElement('a');
              	var textAShowsubmitDay= document.createTextNode("Submit Date:\u00A0\u00A0" + this.time_create);
              	aShowsubmitDay.appendChild(textAShowsubmitDay);
              	fontDetailShowsubmitDay.appendChild(aShowsubmitDay);
             	//close show submit day
             	// show description
              	var divStyleTextDescription = document.createElement('div');
              	divStyleTextDescription.setAttribute("style", "text-align: left;");
              	divStyleTextDescription.setAttribute("class", "centerxs col-xs-12 col-sm-12 col-md-12 col-lg-12");
              	divDetail.appendChild(divStyleTextDescription);
              	var fontDescription = document.createElement('font');
              	fontDescription.setAttribute("size", "3");
              	fontDescription.setAttribute("class", "open-sans");
              	fontDescription.setAttribute("color", "#366FB1");
              	divStyleTextDescription.appendChild(fontDescription);
              	var aDescription = document.createElement('a');
              	var textADescription = document.createTextNode(this.description);
              	aDescription.appendChild(textADescription);
              	fontDescription.appendChild(aDescription);
             	//close show description //close show detail
             	// show status
             	var divStatus = document.createElement('div');
             	divStatus.setAttribute("class", "col-xs-12 col-sm-2 col-md-4 col-lg-4");
             	divRow.appendChild(divStatus);
             	var divRowStatus = document.createElement('div');
             	divRowStatus.setAttribute("class", "row");
             	divStatus.appendChild(divRowStatus);
             	var divShowStatus = document.createElement('div');
             	divShowStatus.setAttribute("class", "col-xs-7 col-sm-7 col-md-7 col-lg-7");
             	divRowStatus.appendChild(divShowStatus);
             	var divCheckStatus = document.createElement('div');
             	divCheckStatus.setAttribute("class", "check-"+this.leave_id);
             	divShowStatus.appendChild(divCheckStatus);
             	var spanStatus = document.createElement('span');
             	spanStatus.setAttribute("class", "item-status");
             	divCheckStatus.appendChild(spanStatus);
             	var spanBadge = document.createElement('span');
             	var aShowStatus = document.createElement('a');
             	aShowStatus.setAttribute("class", "centerxs hiddensm");
             	switch(this.leave_status_id) {
             	  case '0':
             		 		spanBadge.setAttribute("class", "badge badge-empty badge-warning");
             		 		var textAStatus = document.createTextNode("Waiting for approve");	
             	    break;
             	  case '1':
             		 		spanBadge.setAttribute("class", "badge badge-empty badge-success");
             		 		var textAStatus = document.createTextNode("Approved");
             	    break;
             	  case '2':
             				spanBadge.setAttribute("class", "badge badge-empty badge-danger");
	 						var textAStatus = document.createTextNode("Reject");
      	    		break;
             	  default:
             		 		spanBadge.setAttribute("class", "badge badge-empty badge-danger");
	 						var textAStatus = document.createTextNode("Error");
   		 					console.log('*************************** '+this.leave_status_id);
             	}
             	spanStatus.appendChild(spanBadge);
             	aShowStatus.appendChild(textAStatus);
             	spanStatus.appendChild(aShowStatus);
             	//close show status
             	//button
             	if(checkRole()){
             		if(this.leave_status_id=='0'){
             			var divButton = document.createElement('div');
                     	divButton.setAttribute("class", "col-xs-12 col-sm-2 col-md-4 col-lg-4");
                     	divRow.appendChild(divButton);
                     	var divRowButton = document.createElement('div');
                     	divRowButton.setAttribute("class", "row");
                     	divButton.appendChild(divRowButton);
                     	var br1 = document.createElement('br');
                     	var br2 = document.createElement('br');
                     	divRowButton.appendChild(br1);
                     	divRowButton.appendChild(br2);
                     	var divShowButton = document.createElement('div');
                     	divShowButton.setAttribute("class", "col-xs-7 col-sm-7 col-md-7 col-lg-7");
                     	divRowButton.appendChild(divShowButton);
                     	var sweetButton = document.createElement('button');
                     	sweetButton.setAttribute("class", "btn btn-success btn-circle");
                     	sweetButton.setAttribute("onclick", "sweet("+this.leave_id+")");
                     	var textSweet = document.createTextNode("Approve");
                     	sweetButton.appendChild(textSweet);
                     	divShowButton.appendChild(sweetButton); 
             		}
             		
             	}
             	//close button
              	//end profile
              	//
              	console.log(center);
              	//
                
            });
  		},
  		error: function(jqXHR, textStatus, errorThrown){
      		alert('error');
  		}  
    });
    console.log(jsonData)
    return false;
  });
</script>
<script>
	function add() {
		document.location = "LeaveAdd";
	}
</script>
<script>
	function searchShowDashboard(data) {
		$.ajax({
	    	type: "post",
	    	url: "${pageContext.request.contextPath}/leave_approved_edit_dashboard",
	    	crossorigin: true,
	    	dataType: "json",
	    	data: data,
	    	success: function(json){
	    		console.log(json);
	    		var divType1 = document.getElementById("divType1");
	            var spanType1 = document.createElement('span');
	            spanType1.id="leaveType1";
	            var text = document.createTextNode(json[0].type1);
	            spanType1.appendChild(text);
	            
	            divType1.replaceChild(spanType1, leaveType1);
	            
	            var divType3 = document.getElementById("divType3");
	            var spanType3 = document.createElement('span');
	            spanType3.id="leaveType3";
	            var text = document.createTextNode(json[0].type3);
	            spanType3.appendChild(text);
	            
	            divType3.replaceChild(spanType3, leaveType3);
	            
	            
	            var divType6 = document.getElementById("divType6");
	            var spanType6 = document.createElement('span');
	            spanType6.id="leaveType6";
	            var text = document.createTextNode(json[0].type6);
	            spanType6.appendChild(text);
	            
	            divType6.replaceChild(spanType6, leaveType6);
	 
	  		},
	  		error: function(jqXHR, textStatus, errorThrown){
	      		alert('error');
	  		}  
	    });
}
	

	function searchShowQuota(data) {
		$.ajax({
	    	type: "post",
	    	url: "${pageContext.request.contextPath}/leave_approved_edit_quota",
	    	crossorigin: true,
	    	dataType: "json",
	    	data: data,
	    	success: function(json){
	    		console.log(json);
	    		var divType1 = document.getElementById("divType1");
	            var spanQuotaThisYear = document.createElement('span');
	            spanQuotaThisYear.id="quotaThisYear";
	            var text = document.createTextNode("/ "+json[0].thisYear);
	            spanQuotaThisYear.appendChild(text);
	            
	            divType1.replaceChild(spanQuotaThisYear, quotaThisYear);
	            
	           	var divType6 = document.getElementById("divType6");
	            var spanQuotaLastYear = document.createElement('span');
	            spanQuotaLastYear.id="quotaLastYear";
	            var text = document.createTextNode("/ "+json[0].lastYear);
	            spanQuotaLastYear.appendChild(text);
	            
	            divType6.replaceChild(spanQuotaLastYear, quotaLastYear);
	 
	  		},
	  		error: function(jqXHR, textStatus, errorThrown){
	      		alert('error');
	  		}  
	    });
}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />