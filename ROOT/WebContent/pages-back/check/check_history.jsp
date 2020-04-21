<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--  ${workall} --%>

<%--  ${work} --%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-history"></i> Work History
			</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<form action="search-his" method="post">
		<perm:permission object="workhistory.viewall">
			<div class="portlet-body">

				<div class="form-group form-lg-line-input">
					<label class="col-md-1 control-label">Name :</label>
					<div class="col-md-4">
						<!--<c:choose>
							<c:when test="${logonUser == null}">
								<select class="form-control select2me" name="user.roletId">
									<option>All</option>
									<c:forEach var="user" items="${cubeUser}">
										<option
											<c:if test="${user.id eq onlineUser.id}"> selected </c:if>
											value="${user.id}">${user.department_id}-${user.id}</option>
									</c:forEach>
								</select>
							</c:when>
							<c:when test="${logonUser != null}">
								<select class="form-control select2me" name="user.roletId">
									<option>All</option>
									<c:forEach var="user" items="${cubeUser}">
										<option <c:if test="${user.id eq logonUser}"> selected </c:if>
											value="${user.id}">${user.department_id}-${user.id}</option>
									</c:forEach>
								</select>
							</c:when>
						</c:choose>-->
						<select class="form-control select2me" name="user.roletId">
							<option value="All" id="All">All</option>
							<optgroup label="Enable">
								<c:forEach var="user" items="${cubeUser}">

									<c:if test="${user.enable == 1 }">
										<c:if test="${logonUser == nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${logonUser != nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
							<optgroup label="Disable">
								<c:forEach var="user" items="${cubeUser}">

									<c:if test="${user.enable == 0 }">
										<c:if test="${logonUser == nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${logonUser != nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
						</select>
					</div>
							<div class="form-group form-lg-line-input" >
			<label class="control-label col-md-1">Date Range :</label>
			<div class="col-md-4">
				<div class="input-group input-large date-picker input-daterange"
					data-date-format="dd-mm-yyyy">
					<%-- <c:if test="${startdate != ''}">
						<input type="text" class="form-control" name="Date-Start" id="F-date"
							data-date-format="dd-mm-yyyy" value="${startdate}">
					</c:if> --%>
					<c:choose>
						<c:when test="${startmonths == null && startdate == null}">
							<input type="text" class="form-control cannot" name="Date-Start"
								id="F-date" data-date-format="dd-mm-yyyy" value="${datenow}">
						</c:when>
						<c:when test="${startdate == null && startdate == null}">
							<input type="text" class="form-control cannot" name="Date-Start"
								id="F-date" data-date-format="dd-mm-yyyy" value="${startmonths}">
						</c:when>
						<c:when test="${startdate != ''}">
							<input type="text" class="form-control cannot" name="Date-Start"
								id="F-date" data-date-format="dd-mm-yyyy" value="${startdate}">
						</c:when>
					</c:choose>
					 <span
						class="input-group-addon"> to </span>
					<%-- <c:if test="${enddate != ''}">
						<input type="text" class="form-control" name="Date-End" id="E-date"
							data-date-format="dd-mm-yyyy" value="${enddate}">
					</c:if> --%>
					<c:choose>
						<c:when test="${enddate == null}">
							<input type="text" class="form-control cannot" name="Date-End"
								id="E-date" data-date-format="dd-mm-yyyy" value="${datenow}">
						</c:when>
						<c:when test="${enddate != ''}">
							<input type="text" class="form-control cannot" name="Date-End"
								id="E-date" data-date-format="dd-mm-yyyy" value="${enddate}">
						</c:when>
					</c:choose>
					 

				</div>
				
			</div>
		<label></label>
		<div class="col-md-2 text-center">
			<button type="submit"
				class="btn sbold blue" id="searchbutton" onclick="search()">
				<i class="fa fa-search"></i>&nbsp;Search
			</button>
		</div>


		</div>
				</div>

				<%-- <div class="form-group form-md-line-input">
					<label class="col-md-2 control-label">Position :</label>
					<div class="col-md-4">

						<c:choose>
							<c:when test="${department == null}">
								<select class="bs-select form-control" name="user.departmentId">
									<option hidden></option>
									<c:forEach var="department" items="${departmentList}">
										<option value="${department.id}"
											<c:if test="${department.id eq position}">selected </c:if>>
											${department.id}</option>
									</c:forEach>
								</select>
							</c:when>
							<c:when test="${department != null}">
								<select class="bs-select form-control" name="user.departmentId">
									<option hidden>${department}</option>
									<c:forEach var="department" items="${departmentList}">
										<option value="${department.id}" <c:if test="true">  </c:if>>${department.id}
										</option>
									</c:forEach>
									<option></option>
								</select>
							</c:when>
						</c:choose>
					</div>
				</div> --%>
	
			</div>
		</perm:permission>

		
		
		<div class="col-md-5"></div>
	</form>
	<div class="portlet-body flip-scroll" style="text-align: center;">
		<table
			class="table table-bordered table-striped table-condensed flip-content table-hover">
			<thead class="flip-content">
				<tr>
					<th class="numeric"><center>Day</center></th>
					<th class="numeric"><center>Date</center></th>
					<th class="numeric"><center>Task Description</center></th>
					<th class="numeric"><center>User Create</center></th>

				</tr>
			</thead>
			<tbody>
				<c:if test="${logonUser != 'All'}">
					<c:forEach var="workhours" items="${work}" varStatus="status">
						<tr>
							<td><fmt:formatDate
									value="${workhours.work_hours_time_work}" pattern="E" /></td>

							<td><fmt:formatDate
									value="${workhours.work_hours_time_work}"
									pattern="EEE dd-MMM-yyyy HH:mm" /></td>

							<td
								style="word-break: break-all; white-space: normal; text-align: start;">${workhours.description}</td>
							<td>${workhours.user_create}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${logonUser == 'All'}">
					<c:forEach var="workhours" items="${workall}" varStatus="status">
						<tr>
							<td><fmt:formatDate
									value="${workhours.work_hours_time_work}" pattern="E" /></td>

							<td><fmt:formatDate
									value="${workhours.work_hours_time_work}"
									pattern="EEE dd-MMM-yyyy HH:mm" /></td>

							<td style="word-break: break-all; white-space: normal;">${workhours.description}</td>
							<td>${workhours.user_create}</td>
						</tr>
					</c:forEach>
				</c:if>


			</tbody>

		</table>
		<!-- <button type="approve_list" class="btn btn-primary blue" onclick="search()"
			id="searchbutton">
			<i class="fa fa-search"> &nbsp;Search</i>
		</button> -->
	</div>
</div>



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
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.select2me').select2();
	});
</script>
<script>
	function search() {
		var DateStart = $("#F-date").val();
		var DateEnd = $("#E-date").val();
		if ((DateStart == "") || (DateEnd == "")) {
			document.getElementById("searchbutton").type = "button";
			swal("You miss Date", "Please Try It Agian :)", "error");
		} else {
			document.getElementById("searchbutton").type = "submit";
		}
	}
</script>
