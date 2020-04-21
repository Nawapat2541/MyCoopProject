<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="portlet-body">
	<div style="margin-top: 2cm;">
		<div class="form-group form-md-line-input">
			<label class="col-md-3 control-label" for="form_control_1">Approvers
				:</label>
			<div class="col-md-4">
				<c:choose>
					<c:when test="${userId != null}">
						<select class="bs-select form-control" name="apprUserId"
							id="apprUserId">
							<c:forEach var="manager" items="${leader}">
								<option value="${manager.manager_id}"
									<c:if test="${user.managerId eq manager.manager_id }"> selected </c:if>>${manager.manager_id}</option>
							</c:forEach>
							<c:forEach var="manager" items="${userList}">
								<option value="${manager.id}"
									<c:if test="${manager.id eq user.managerId }"></c:if>>${manager.name}</option>
							</c:forEach>
						</select>
					</c:when>
					<c:when test="${userId != null}">
						<select class="bs-select form-control" name="apprUserId"
							id="apprUserId">
							<c:forEach var="manager" items="${leader}">
								<option value="${user.id}"
									<c:if test="${user.managerId eq manager.manager_id }"> selected </c:if>>${manager.manager_id}</option>
							</c:forEach>
							<c:forEach var="manager" items="${userList}">
								<option value="${manager.id}"
									<c:if test="${manager.id eq user.managerId }"></c:if>>${manager.name}</option>
							</c:forEach>
						</select>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<div style="margin-top: 2cm;">
		<div class="form-group form-md-line-input">
			<label class="col-md-3 control-label" for="form_control_1">Status
				:</label>
			<div class="col-md-4">
			<c:if test="${flag.toString() eq '1' }">
				<select class="bs-select form-control" name="leaveStatusId"
					id="leaveStatusId">
					<option name="leaveStatusId" value="0"
						<c:if test="${leave.leaveStatusId.toString() == '0'}">selected</c:if>>
						<i>Waiting for approve</i>
					</option>
					<option name="leaveStatusId" value="1"
						<c:if test="${leave.leaveStatusId.toString() == '1'}">selected</c:if>>
						<i>Approved</i>
					</option>
					<option name="leaveStatusId" value="2"
						<c:if test="${leave.leaveStatusId.toString() == '2'}">selected</c:if>>
						<i>Reject</i>
					</option>
				</select>
			</c:if>
		
	
		<!-- <select class="bs-select form-control" name="leaveStatusId"
			id="leaveStatusId"> -->
			<c:if test="${flag.toString() eq '0' }">
					<option name="leaveStatusId" value="0"
						<c:if test="${leave.leaveStatusId.toString() == '0'}">selected</c:if>>
						<i>Waiting for approve</i>
					</option>
			</c:if>
			</div>
		</div>
	</div>
	<div class="form-group form-md-line-input">
		<label class="col-md-3 control-label" for="form_control_1">Reason
			:</label>
		<div class="col-md-4">
			<textarea class="form-control" name="reason" rows="3"
				maxlength="1024">${leave.reason}</textarea>
			<div class="form-control-focus"></div>
			<span class="help-block">Please fill the information</span>
		</div>
	</div>
	<div class="form-group form-md-line-input">
		<div style="margin-top: 3cm;">
			<div class="form-group form-md-line-input">
				<center>
					<button type="submit" class="btn sbold blue">
						<i class="fa fa-save"></i>&nbsp;Save leave form
					</button>
					<button style="margin-left: 1cm;" type="reset" class="btn red">
						<i class="fa fa-times-circle"></i>&nbsp;Cancel
					</button>
				</center>
			</div>
		</div>
	</div>
</div>