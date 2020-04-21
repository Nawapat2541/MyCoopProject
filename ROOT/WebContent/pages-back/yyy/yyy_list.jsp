<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-md-6">
                            <div class="portlet light bordered" id="blockui_sample_1_portlet_body">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bubble font-green-sharp"></i>
                                        <span class="caption-subject font-green-sharp sbold">Portlet Blocking</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                            <a class="btn green-haze btn-outline btn-circle btn-sm" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> Actions
                                                <i class="fa fa-angle-down"></i>
                                            </a>
                                            <ul class="dropdown-menu pull-right">
                                                <li>
                                                    <a href="javascript:;"> Option 1</a>
                                                </li>
                                                <li class="divider"> </li>
                                                <li>
                                                    <a href="javascript:;">Option 2</a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;">Option 3</a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;">Option 4</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <p> At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique. </p>
                                    <p> Aet accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum. At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores
                                        et. </p>
                                    <p>
                                        <a href="javascript:;" class="btn btn-outline sbold blue-madison" id="blockui_sample_1_1"> Block Portlet With Default Message </a>
                                    </p>
                                    <p>
                                        <a href="javascript:;" class="btn btn-outline sbold red-intense" id="blockui_sample_1_2"> Block Portlet With Boxed Message </a>
                                    </p>
                                    <p>
                                        <a href="javascript:;" class="btn btn-outline sbold green-haze" id="blockui_sample_1_3"> Block Portlet With CSS3 Animation</a>
                                    </p>
                                </div>
                            </div>
                        </div>


<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption font-dark">
			<i class="icon-settings font-dark"></i> <span
				class="caption-subject bold uppercase">XXX Management</span>

		</div>
		
		<div class="actions right">
			<a href="javascript:;" class="btn btn-circle dark" title="Add User"> <i
				class="fa fa-plus"></i> Add XXX
			</a> <a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>

	</div>
	<div class="portlet-body">

		<div class="table-scrollable">
			<table
				class="table table-bordered table-striped table-condensed table-hover small">
				<thead>
					<tr>
						<th>#</th>
						<th>ID XXXXX</th>
						<th>XXXX</th>
						<th>Department</th>
						<th>Email</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td><a href="user-edit?userId=${user.id}">${user.name}</a></td>
							<td>${user.roleId}</td>
							<td>${user.departmentId}</td>
							<td>${user.email}</td>
							<td><c:choose>
									<c:when test="${user.enable eq '1' }">
										<span class="label label-sm label-success"> Enable </span>
									</c:when>
									<c:otherwise>
										<span class="label label-sm label-danger"> Disabled </span>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>