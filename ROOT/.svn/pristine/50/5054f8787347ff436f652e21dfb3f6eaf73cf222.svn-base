<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-user"></i> <span
				class="caption-subject bold uppercase">User Profile</span> <span
				class="caption-helper">${user.name}</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default"
				href="javascript:;"> <i class="icon-cloud-upload"></i>
			</a> <a class="btn btn-circle btn-icon-only btn-default"
				href="javascript:;"> <i class="icon-wrench"></i>
			</a> <a class="btn btn-circle btn-icon-only btn-default"
				href="javascript:;"> <i class="icon-trash"></i>
			</a>
		</div>
	</div>
	<div class="portlet-body form">
		<c:set var="pageContext.request.locale" scope="session" value="th_TH"/>
		[TEST] Default Locale: ${pageContext.request.locale} + <BR> 
		
		<c:forEach var="loc" items="${pageContext.request.locales}">
			${loc.displayName}, 
		</c:forEach>
		
		<br>
		<fmt:formatDate value="<%=new java.util.Date()%>" />
		<br>

		<fmt:setLocale value="de_DE" />
		Locale de_DE:
		<fmt:formatDate value="<%=new java.util.Date()%>" />
		<br>

		<fmt:setLocale value="en_GB" scope="session" />
		Locale en_GB:
		<fmt:formatDate value="<%=new java.util.Date()%>" />
		<br>

		<!-- BEGIN FORM-->
		<form action="user-perform-edit" class="form-horizontal" method="post">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-3 control-label">User Id</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Username"
							name="user.id" value="${user.id}">
					</div>

				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Name</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Username"
							name="user.name" value="${user.name}">

					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">NickName</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Username"
							name="user.nickName" value="${user.nickName}">

					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Password</label>
					<div class="col-md-4">
						<div class="input-group">
							<input type="password" class="form-control" name="user.password"
								value="${user.password}" placeholder="Password"> <span
								class="input-group-addon"> <i class="fa fa-user"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">Confirm Password</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="fa fa-user"></i> <input type="password"
								value="${user.password}" class="form-control"
								name="user.confirmPassword" placeholder="Confirm Password">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">Email Address</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="fa fa-envelope"></i> <input type="email"
								class="form-control" name="user.email"
								placeholder="Email Address">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-3">Role</label>
					<div class="col-md-4">
						<select class="bs-select form-control" name="user.roleId">
							<c:forEach var="role" items="${roleList}">
								<option value="${role.id}"
									<c:if test="${user.roleId eq role.id }"> selected </c:if>>${role.id}</option>
							</c:forEach>

						</select>

					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-3">Start Date</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="icon-calendar"></i> <input
								class="form-control date-picker" type="text" id="startDate"
								value="<fmt:formatDate value='${user.startDate}' pattern='MM/dd/yyyy' />"
								name="user.startDate" placeholder="Start Working Date">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">End Date</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="icon-calendar"></i> <input
								class="form-control date-picker" type="text" name="user.endDate"
								value="<fmt:formatDate value='${user.endDate}' pattern='MM/dd/yyyy' />"
								placeholder="Last Working Date">
							<div class="input-group">
								<input type="text" class="form-control timepicker timepicker-24">
								<span class="input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-clock-o"></i>
									</button>
								</span>
							</div>

						</div>


					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">Birth Date</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="icon-calendar"></i> <input
								class="form-control date-picker" type="text"
								name="user.birthDate"
								value="<fmt:formatDate value='${user.birthDate}' pattern='MM/dd/yyyy' />"
								placeholder="Birth Date">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">Time Update</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Time Update"
							name="user.timeUpdate" value="${user.timeUpdate}">

					</div>
				</div>
				
			</div>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-offset-3 col-md-4">

						<button type="submit" class="btn blue">Submit</button>
						<button type="button" class="btn default">Cancel</button>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>


<script>
	
</script>