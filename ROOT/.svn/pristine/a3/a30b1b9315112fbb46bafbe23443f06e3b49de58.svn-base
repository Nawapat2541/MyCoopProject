<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-user font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Role Add :</span> <span
				class="caption-helper font-red">${role.name}</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="role-perform-add" class="form-horizontal" method="post"
			autocomplete="off">
			<div class="form-body">
				<div class="form-group form-lg-line-input">
					<label class="col-md-3 control-label" style="color: #666666;">Role
						Id :</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Role ID"
							maxlength="32" name="role.id" required> <input
							type="hidden" class="form-control" placeholder="Role ID"
							name="roleId" value="${role.id}">
						<div class="form-control-focus"></div>
					</div>

				</div>
				<div class="form-group form-lg-line-input ">
					<label class="col-md-3 control-label" style="color: #666666;">Name :</label>
					<div class="col-md-4 ">
						<input type="text" class="form-control" placeholder="Username"
							maxlength="64" name="role.name" value="${role.name}">
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-lg-line-input">
					<label class="col-md-3 control-label" style="color: #666666;">Description :</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder="Description"
							maxlength="200" name="role.description"
							value="${role.description}">
						<div class="form-control-focus"></div>
					</div>
				</div>
				<!-- 
				<div class="form-group">
					<label class="col-md-3 control-label">Authorization</label>

					<div class="col-md-9">
						<c:forEach var="ao" items="${aoList}">
							<div class="col-sm-6 col-md-6 col-lg-4">
								<label class="mt-checkbox" title="${ao.description}"> <input
									type="checkbox" value="${ao.authorizedObjectId}"
									<c:forEach var="rao" items="${raoList}">
							<c:if test="${ao.authorizedObjectId eq rao.authorizedObjectId}"> checked </c:if>
						</c:forEach>>
									${ao.name} <span></span>
								</label>
							</div>
						</c:forEach>
					</div>
				</div>
				 -->
				<div class="form-group form-lg-line-input">
					<label class="col-md-3 control-label">Authorization :</label>

					<div class="col-md-9">
						<c:forEach var="ao" items="${aoList}" varStatus="Count">

							<div class="col-sm-6 col-md-6 col-lg-4">
								<div class="md-checkbox ">
									<input name="authId" type="checkbox"
										id="checkbox${Count.count}" class="md-check"
										value="${ao.authorizedObjectId}"
										<c:forEach var="rao" items="${raoList}">
							<c:if test="${ao.authorizedObjectId eq rao.authorizedObjectId}"> checked </c:if>
						</c:forEach>>

									<label for="checkbox${Count.count}"> <span class="inc"></span>
										<span class="check"></span> <span class="box"></span>
										${ao.name}
									</label> <span></span>

								</div>
							</div>
						</c:forEach>
					</div>
				</div>



			</div>
			<div class="form-actions action right">
				<div class="row ">
					<div class="col-md-12">
						<center>
							<button type="submit" class="btn blue">
								<i class="fa fa-save"></i>&nbsp;Save
							</button>
							<button type="reset" class="btn red">
								<i class="fa fa-close"></i>&nbsp;Cancel
							</button>
						</center>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>