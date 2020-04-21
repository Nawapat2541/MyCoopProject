<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-user font-red"></i> <span
				class="caption-subject font-red sbold uppercase">User Profile</span>
			<span class="caption-helper font-red">${user.name}</span>
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
	<form action="user-perform-edit" method="get">
	<div class="portlet-body form">
		<!-- Begin New from -->
		<div class="portlet-body" style="margin-right: 2%; margin-left: 2%;">
			<!-- BEGIN FORM-->
				<div class="form-group form-md-line-input  ">

					<div class="caption caption col-md-2">
						User Id :<br>
					</div>
					<div class="caption col-md-4">
						<input type="text" class="form-control" placeholder=""
							value="${user.id}" disabled>
						<div class="form-control-focus"></div>
					</div>
					<div class="caption caption col-md-2">
						Role :<br>
					</div>
					<div class="caption col-md-4" style="margin-top: -6px;">
						<select class="form-control" name="user.roleId">
							<c:forEach var="role" items="${roleList}">
								<option value="${role.id}"
									<c:if test="${user.roleId eq role.id }"> selected </c:if>>${role.id}</option>
							</c:forEach>

						</select>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">

					<div class="caption caption col-md-2">
						Name :<br>
					</div>
					<div class="caption col-md-4">
						<input type="text" class="form-control" placeholder=""
							value="${user.name}" maxlength="200">
						<div class="form-control-focus"></div>
					</div>
					<div class="caption caption col-md-2">
						Start Working Date :<br>
					</div>
					<div class="caption col-md-4">
						<input class="form-control form-control-inline  date-picker"
							id="startDate" type="text"
							value="<fmt:formatDate value='${user.startDate}' pattern='MM/dd/yyyy' />"
							name="user.startDate" placeholder="Start Working Date">

						<div class="form-control-focus"></div>
					</div>
				</div>
		</div>

		<div class="form-group form-md-line-input  ">
			<div class="caption caption col-md-2">
				Password :<br>
			</div>
			<div class="caption col-md-4">
				<input type="password" class="form-control" placeholder="" maxlength="32"
					value="${user.password }">
				<div class="form-control-focus"></div>
			</div>

			<div class="caption caption col-md-2">
				Birth Date :<br>
			</div>
			<div class="caption col-md-4">
				<input class="form-control form-control-inline  date-picker"
					type="text" name="user.birthDate"
					value="<fmt:formatDate value='${user.birthDate}' pattern='MM/dd/yyyy' />"
					placeholder="Birth Date">

				<div class="form-control-focus"></div>
			</div>
		</div>

		<div class="form-group form-md-line-input  ">

			<div class="caption caption col-md-2">
				Confirm Password :<br>
			</div>
			<div class="caption col-md-4">
				<input type="password" class="form-control" placeholder="" maxlength="32"
					value="${user.password}">
				<div class="form-control-focus"></div>
			</div>
			<div class="caption caption col-md-2">
				Time Update :<br>
			</div>
			<div class="caption col-md-4">
				<input class="form-control form-control-inline  date-picker"
					placeholder="Time Update" name="user.timeUpdate"
					value="${user.timeUpdate}">

				<div class="form-control-focus"></div>
			</div>
		</div>

		<div class="form-group form-md-line-input  ">

			<div class="caption caption col-md-2">Email Address :</div>
			<div class="caption col-md-4">
				<input type="text" class="form-control" placeholder="" maxlength="50"
					value="${user.email}">
				<div class="form-control-focus"></div>
			</div>

		</div>

	</div>

	<div class="row ">
		<div class="col-md-12"style="margin-top: 5%;text-align: center;">
				<button type="submit" class="btn blue">
					<i class="fa fa-save"></i>&nbsp;Save
				</button>
				<button type="button" class="btn red">
					<i class="fa fa-times-circle"></i> Cancel
				</button>
		</div>
	</div>
	</form>
</div>




<!-- End New From -->
