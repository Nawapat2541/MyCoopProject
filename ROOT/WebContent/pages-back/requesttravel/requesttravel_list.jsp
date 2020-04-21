<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-plane font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Request
				travel list</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<form action="searchtable">
		<div class="portlet-body">
			<perm:permission object="requesttravellist.searchstaff">
				<div class="form-group form-lg-line-input">
					<label class="col-lg-1 control-label">Staff :</label>
					<div class="col-lg-3">
						<select class="bs-select form-control select2me" name="name">
							<option value="All" id="All">All</option>
							<optgroup label="Enable">
								<c:forEach var="user" items="${userseq}">
									<c:if test="${user.enable == 1 }">
										<c:if test="${nameofuser == nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${nameofuser != nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,nameofuser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
							<optgroup label="Disable">
								<c:forEach var="user" items="${userseq}">
									<c:if test="${user.enable == 0 }">
										<c:if test="${nameofuser == nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${nameofuser != nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,nameofuser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
						</select>
					</div>

					<label class="control-label col-md-1">Date :</label>
					<div class="col-lg-4"><left>
						<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy">
							<c:if test="${startdate == null}">
								<input type="text" class="form-control cannot" name="startdate"
									data-date-format="dd-MM-yyyy" placeholder="Start date"
									size="13"
									value="<fmt:formatDate type="date" value="${now}" pattern="01-01-yyyy"  />">
							</c:if>
							<c:if test="${startdate != null}">
								<input type="text" class="form-control cannot" name="startdate"
									data-date-format="dd-MM-yyyy" placeholder="Start date"
									size="10"
									value="<fmt:formatDate type="date" value="${startdate}" pattern="dd-MM-yyyy"  />">
							</c:if>
							<span class="input-group-addon"> To </span>
							<c:if test="${enddate == null}">
								<input type="text" class="form-control cannot" name="enddate"
									data-date-format="dd-MM-yyyy" placeholder="End date" size="13"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy"  />">
							</c:if>
							<c:if test="${enddate != null}">
								<input type="text" class="form-control cannot" name="enddate"
									data-date-format="dd-MM-yyyy" placeholder="End date" size="13"
									value="<fmt:formatDate type="date" value="${enddate}" pattern="dd-MM-yyyy"  />">
							</c:if>
						</div></left>
					</div>
					<label></label>
				<div class="col-lg-2"><center>
					<button type="submit" class="btn sbold blue" id="searchbutton"
					onclick="search()">
					<i class="fa fa-search"></i>&nbsp;Search
					</button></center>
				</div>
			</div>
		</div>
		</perm:permission>


		
		<div class="col-md-5"></div>
	</form>
	<div class="portlet-body flip-scroll" style="text-align: center;">
		<table
			class="table table-bordered table-striped table-condensed flip-content table-hover">
			<thead class="flip-content">
				<tr>
					<th width="15%"><center>Expense group ID</center></th>
					<th width="20%"><center>Date</center></th>
					<th><center>User</center></th>
					<th style="text-align: end;">Amount</th>
					<th><center>Status</center></th>
					<th height="44"><center>Detail</center></th>
					<th height="44"><center>Print</center></th>
					<th height="44"><center>PrintTH</center></th>
				</tr>
			</thead>
			<c:if test="${nameofuser != 'All'}">
			<c:forEach var="exp" items="${onlyone}" varStatus="status">
				<tr>
					<td>${exp.expense_group_id}</td>
					<td><fmt:setLocale value="en_us" /> <fmt:formatDate
							value="${exp.time_create}" pattern="dd/MM/yyyy HH:mm"></fmt:formatDate></td>
					<td>${exp.user_id}</td>
					<td align="right"><c:set var="balance"
									value="${exp.total_amount}" /> <fmt:setLocale value="th-TH" />
								<fmt:formatNumber type="currency" maxFractionDigits="3"
									value="${balance}" /></td>
					<td height="30"><c:if
							test="${exp.status_id.toString() == 'W'}">
							<span class="label label-sm label-warning">Waiting for
								approve</span>
						</c:if> <c:if test="${exp.status_id.toString() == 'A'}">
							<span class="label label-sm label-success">Approved</span>
						</c:if> <c:if test="${exp.status_id.toString() == 'P'}">
							<span class="label label-sm label-primary">Paid</span>
						</c:if> <c:if test="${exp.status_id.toString() == 'R'}">
							<span class="label label-sm label-danger">Reject</span>
						</c:if></td>
					<td><a class="btn btn-circle btn-sm sbold blue"
						href="request_form?Id=${exp.expense_group_id}" title="Detail"
						style="color: white;"><i class="fa fa-clipboard"></i></a></td>
					<td><a class="btn btn-circle btn-sm sbold blue"
						href="travelReport?expenseGroupId=${exp.expense_group_id}"
						title="Print" style="color: white;"><i class="fa fa-print"></i></a></td>
						<td><a class="btn btn-circle btn-sm sbold blue"
						href="travelReportTH?expenseGroupId=${exp.expense_group_id}"
						title="PrintTH" style="color: white;"><i class="fa fa-print"></i></a></td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${nameofuser == 'All'}">
			<c:forEach var="exp" items="${userAll}" varStatus="status">
				<tr>
					<td>${exp.expense_group_id}</td>
					<td><fmt:setLocale value="en_us" /> <fmt:formatDate
							value="${exp.time_create}" pattern="dd-MM-yyyy HH:mm"></fmt:formatDate></td>
					<td>${exp.user_id}</td>
					<td align="right"><c:set var="balance"
									value="${exp.total_amount}" /> <fmt:setLocale value="th-TH" />
								<fmt:formatNumber type="currency" maxFractionDigits="3"
									value="${balance}" /></td>
					<td height="30"><c:if
							test="${exp.status_id.toString() == 'W'}">
							<span class="label label-sm label-warning">Waiting for
								approve</span>
						</c:if> <c:if test="${exp.status_id.toString() == 'A'}">
							<span class="label label-sm label-success">Approved</span>
						</c:if> <c:if test="${exp.status_id.toString() == 'P'}">
							<span class="label label-sm label-primary">Paid</span>
						</c:if> <c:if test="${exp.status_id.toString() == 'R'}">
							<span class="label label-sm label-danger">Reject</span>
						</c:if></td>
					<td><a class="btn btn-circle btn-sm sbold blue"
						href="request_form?Id=${exp.expense_group_id}" title=""
						style="color: white;"><i class="fa fa-clipboard"></i></a></td>
					<td><a class="btn btn-circle btn-sm sbold blue"
						href="travelReport?expenseGroupId=${exp.expense_group_id}"
						title="" style="color: white;"><i class="fa fa-print"></i></a></td>
						<td><a class="btn btn-circle btn-sm sbold blue"
						href="travelReportTH?expenseGroupId=${exp.expense_group_id}"
						title="" style="color: white;"><i class="fa fa-print"></i></a></td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
	</div>
</div>
</div>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.select2me').select2();
	});
</script>
<script>
	var today = moment().format('YYYY-MM-DD');
	document.getElementById("datePicker").value = today; 
</script>