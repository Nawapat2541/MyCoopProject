<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="margin-top: 2cm;"></div>
<div class="form-group form-md-line-input">

	<label class="col-md-3 control-label" for="form_control_1">Approvers
		:</label>
	<div class="col-md-4">
		<c:choose>
			<c:when test="${id != null}">
				<select class="bs-select form-control" name="apprUserId"
					id="appUserId">
					<c:forEach var="user" items="${approve}">
						<option value="${user.appr_user_id}"
							<c:if test="${user.appr_user_id eq leave.apprUserId}">  selected  </c:if>>${user.appr_user_id}</option>
					</c:forEach>
					<!-- the value of user.id (not must be change value) -->
					<c:forEach var="userList" items="${userList}">
						<option value="${userList.id}"
							<c:if test="${userList.id eq leave.apprUserId}"></c:if>>${userList.id}</option>
					</c:forEach>
				</select>
			</c:when>
			<%-- <c:when test="${userId != null}">
				<select class="bs-select form-control" name="apprUserId"
					id="apprUserId">
					<c:forEach var="manager" items="${leader}">
						<option value="${manager.managerId}"
							<c:if test="${user.managerId eq manager.manager_id}"> selected </c:if>>${manager.manager_id}</option>
						<c:forEach var="userList" items="${userList}">
							<option value="${userList.id}"
								<c:if test="${userList.id eq leave.appr_user_id}"></c:if>>${userList.name}</option>
						</c:forEach>
					</c:forEach>
				</select>
			</c:when> --%>
		</c:choose>
	</div>
</div>
<div class="form-group form-md-line-input">
	<label class="col-md-3 control-label" for="form_control_1">Status
		:</label>
	<div class="col-md-4">
		<c:if test="${flag.toString() eq '1' }">
			<select class="bs-select form-control" name="leaveStatusId"
				id="leaveStatusId"> 
				<c:forEach var="leave" items="${approve }">

					<option name="leaveStatusId" value="0"
						<c:if test="${leave.leave_status_id.toString() eq '0'}"><c:out value="selected=selected"/></c:if>>Waiting
						for approve</option>
					<option name="leaveStatusId" value="1"
						<c:if test="${leave.leave_status_id.toString() eq '1'}"><c:out value="selected=selected"/></c:if>>Approved</option>
					<option name="leaveStatusId" value="2"
						<c:if test="${leave.leave_status_id.toString() eq '2'}"><c:out value="selected=selected"/></c:if>>Reject</option>
				</c:forEach>
			</select>
		</c:if>

		<c:if test="${flag.toString() eq '0' }">
			
				<c:if test="${leave.leave_status_id.toString() eq '0'}">


					<option name="leaveStatusId" value="0"
						<c:out value="selected=selected"/>>Waiting for approve</option>
				</c:if>

				<c:if test="${leave.leave_status_id.toString() eq '1'}">
					<option name="leaveStatusId" value="1"
						<c:out value="selected=selected"/>>Approved</option>
				</c:if>

				<c:if test="${leave.leave_status_id.toString() eq '2'}">
					<option name="leaveStatusId" value="2"
						<c:out value="selected=selected"/>>Reject</option>
				</c:if>

		</c:if>
	</div>

</div>
<c:forEach var="leave" items="${leaveList}">
	<c:if test="${leave.leave_id  == param.Id}">
		<div class="form-group form-md-line-input">
			<label class="col-md-3 control-label" for="form_control_1">Reason
				:</label>
			<div class="col-md-4">
				<textarea style="word-break: break-all; white-space: normal;"
					maxlength="1024" class="form-control" name="reason" rows="3"
					value="leave.reason">${leave.reason}</textarea>
				<div class="form-control-focus"></div>
				<span class="help-block">Please fill the information</span>
			</div>
		</div>
	</c:if>
</c:forEach>
<!-- <input type="hidden" name="saveedit"> -->
<div class="form-group form-md-line-input">
	<div style="margin-top: 3cm;">
		<div class="form-group form-md-line-input">
			<center>
				<button type="submit" class="btn sbold blue">
					<i class="fa fa-save"></i>&nbsp;Save
				</button>
				<button style="margin-left: 1cm;" type="cancel" class="btn red">
					<i class="fa fa-times-circle"></i>&nbsp;Cancel
				</button>
			</center>
		</div>
	</div>
</div>