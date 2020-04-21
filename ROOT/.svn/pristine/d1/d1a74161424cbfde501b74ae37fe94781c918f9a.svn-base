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
				class="fa fa-history"></i> Online User
			</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="page-wrapper">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-body">
						<div class="mt-element-card mt-card-round mt-element-overlay">
							<div class="row">
								<c:forEach items="${sessionOnlineUser}" var="online">
									<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
										<div class="mt-card-item" style="border-color: white;">
											<div class="mt-card-avatar mt-overlay-1 mt-scroll-down">
												<img src="${online.path }"
												onerror="this.onerror=null;this.src='pages-back/img/profile.png';"/>
												<div class="mt-overlay">
						                          <ul class="mt-info">
						                            <li>
						                              <p id="pFL" class="mt-card-desc font-white" style="font-size:xxx-large"></p>
						                              <script	>
								                            var name = "${online.id}"
								                            var splitName = name.split(".");
								                            console.log(splitName)
								                            var f = (splitName[0][0]).toUpperCase();
								                            var l = (splitName[1]).toUpperCase();
								                            console.log(f)
								                            console.log(l)
						                             		document.getElementById("pFL").innerHTML = f+""+l;
						                            </script>
						                            </li>
						                          </ul>
						                        </div>
											</div>
											<div class="mt-card-content">
												<h3 class="mt-card-name">ID: ${online.id}</h3>
												<p class="mt-card-desc font-grey-mint">Department: ${online.name}</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- ${onlineSession}
<br>
${onlineUserShow}
<br>
${expBarcharts} --%>

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
	

