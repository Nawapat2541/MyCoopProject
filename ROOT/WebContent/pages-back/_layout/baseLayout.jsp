<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${onlineUser == null}">
	<%
		response.sendRedirect("index.jsp");
	%>
</c:if>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta
	content="Preview page of Metronic Admin Theme #4 for responsive bootstrap table demos"
	name="description" />
<meta content="" name="author" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="../assets/global/plugins/datatables/datatables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link
	href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/clockface/css/clockface.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL STYLES -->
<link href="../assets/global/css/components.min.css" rel="stylesheet"
	id="style_components" type="text/css" />
<link href="../assets/global/css/plugins.min.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->

<%-- Layout 1 
<link href="../assets/layouts/layout/css/layout.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/layouts/layout/css/themes/default.min.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="../assets/layouts/layout/css/custom.min.css"
	rel="stylesheet" type="text/css" />
--%>	
<%-- Layout 4 --%>
<link href="../assets/layouts/layout4/css/layout.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/layouts/layout4/css/themes/default.min.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="../assets/layouts/layout4/css/custom.min.css"
	rel="stylesheet" type="text/css" />

	
<link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/select2/css/select2.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/select2/css/select2-bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- END THEME LAYOUT STYLES -->
<link rel="shortcut icon" href="favicon.ico" />


	
</head>
<!-- END HEAD -->

<body
	class="page-container-bg-solid page-sidebar-closed-hide-logo">
	<!-- BEGIN HEADER -->
	<div class="page-header navbar">
		<!-- BEGIN HEADER INNER -->
		<div class="page-header-inner ">
			<!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="Dashboard"> <img width="140" style="margin:10px"
					src="pages-back/_layout/logo2.png" alt="logo2" class="logo-default">
				</a>
				<div class="menu-toggler sidebar-toggler"></div>
			</div>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="menu-toggler responsive-toggler"
				data-toggle="collapse" data-target=".navbar-collapse"> </a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN PAGE ACTIONS -->
			<!-- DOC: Remove "hide" class to enable the page header actions -->
			<div class="page-actions">
				<div class="btn-group">

					<ul class="dropdown-menu" role="menu">
						<li><a href="javascript:;"> <i class="icon-docs"></i> New
								Post
						</a></li>
						<li><a href="javascript:;"> <i class="icon-tag"></i> New
								Comment
						</a></li>
						<li><a href="javascript:;"> <i class="icon-share"></i>
								Share
						</a></li>
						<li class="divider"></li>
						<li><a href="javascript:;"> <i class="icon-flag"></i>
								Comments <span class="badge badge-success">4</span>
						</a></li>
						<li><a href="javascript:;"> <i class="icon-users"></i>
								Feedbacks <span class="badge badge-danger">2</span>
						</a></li>
					</ul>
				</div>
			</div>
			<!-- END PAGE ACTIONS -->
			<!-- BEGIN PAGE TOP -->
			<div style="float: right;">
				<!-- BEGIN HEADER SEARCH BOX -->
				<!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->

				<!-- END HEADER SEARCH BOX -->
				<!-- BEGIN TOP NAVIGATION MENU -->
				<div class="top-menu">
					<ul class="nav navbar-nav pull-right">
						<li class="dropdown dropdown-user dropdown-dark"><a
							href="javascript:;" class="dropdown-toggle"
							data-toggle="dropdown" data-hover="dropdown"
							data-close-others="true"> <span
								class="username username-hide-on-mobile">

									${onlineUser.id} </span> <!-- DOC: Do not remove below empty space(&nbsp;) as its purposely used -->
								<c:if test="${user.path == ''}">

									<img src="../pages-back/_layout/no-image-box.png">
								</c:if> <c:if test="${user.path != null}">
									<img src="${user.path}" alt="">
								</c:if>
						</a>
							<ul class="dropdown-menu dropdown-menu-default">
								<li><a href="user-edit-profile?userId=${onlineUser.id}">
										<i class="icon-user"></i> My Profile
								</a></li>



								<li class="divider"></li>

								<li><a href="logout"> <i class="icon-key"></i> Log Out
								</a></li>
							</ul></li>
						<!-- END USER LOGIN DROPDOWN -->
						<!-- BEGIN QUICK SIDEBAR TOGGLER -->
						<!-- <li class="dropdown dropdown-extended quick-sidebar-toggler">
							<span class="sr-only">Toggle Quick Sidebar</span> <i
							class="icon-logout"></i>
						</li> -->
						<!-- END QUICK SIDEBAR TOGGLER -->
					</ul>
				</div>
				<!-- END TOP NAVIGATION MENU -->
			</div>
			<!-- END PAGE TOP -->
		</div>
		<!-- END HEADER INNER -->
	</div>
	<!-- END HEADER -->
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<!-- BEGIN SIDEBAR -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
				<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
				<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
				<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
				<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
				<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->

				<tiles:insertAttribute name="menu" ignore="true" />

				<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<!-- BEGIN CONTENT BODY -->
			<div class="page-content"
				style="min-height: 686px; padding-top: 0px;">
				<!-- BEGIN PAGE HEAD-->
				<div class="page-head" style="height: 0px;">
					<!-- BEGIN PAGE TITLE -->

					<!-- END PAGE TITLE -->
					<!-- BEGIN PAGE TOOLBAR -->
					<div class="page-toolbar">
						<!-- BEGIN THEME PANEL -->
						<div class="btn-group btn-theme-panel">
							<a href="javascript:;" class="btn dropdown-toggle"
								data-toggle="dropdown"> <i class="icon-settings"></i>
							</a>
							<div
								class="dropdown-menu theme-panel pull-right dropdown-custom hold-on-click">
								<div class="row">
									<div class="col-md-4 col-sm-4 col-xs-12">
										<h3>HEADER</h3>
										<ul class="theme-colors">
											<li class="theme-color theme-color-default active"
												data-theme="default"><span class="theme-color-view"></span>
												<span class="theme-color-name">Dark Header</span></li>
											<li class="theme-color theme-color-light " data-theme="light">
												<span class="theme-color-view"></span> <span
												class="theme-color-name">Light Header</span>
											</li>
										</ul>
									</div>
									<div class="col-md-8 col-sm-8 col-xs-12 seperator">
										<h3>LAYOUT</h3>
										<ul class="theme-settings">
											<li>Theme Style <select
												class="layout-style-option form-control input-small input-sm">
													<option value="square">Square corners</option>
													<option value="rounded" selected="selected">Rounded
														corners</option>
											</select>
											</li>
											<li>Layout <select
												class="layout-option form-control input-small input-sm">
													<option value="fluid" selected="selected">Fluid</option>
													<option value="boxed">Boxed</option>
											</select>
											</li>
											<li>Header <select
												class="page-header-option form-control input-small input-sm">
													<option value="fixed" selected="selected">Fixed</option>
													<option value="default">Default</option>
											</select>
											</li>
											<li>Top Dropdowns <select
												class="page-header-top-dropdown-style-option form-control input-small input-sm">
													<option value="light">Light</option>
													<option value="dark" selected="selected">Dark</option>
											</select>
											</li>
											<li>Sidebar Mode <select
												class="sidebar-option form-control input-small input-sm">
													<option value="fixed">Fixed</option>
													<option value="default" selected="selected">Default</option>
											</select>
											</li>
											<li>Sidebar Menu <select
												class="sidebar-menu-option form-control input-small input-sm">
													<option value="accordion" selected="selected">Accordion</option>
													<option value="hover">Hover</option>
											</select>
											</li>
											<li>Sidebar Position <select
												class="sidebar-pos-option form-control input-small input-sm">
													<option value="left" selected="selected">Left</option>
													<option value="right">Right</option>
											</select>
											</li>
											<li>Footer <select
												class="page-footer-option form-control input-small input-sm">
													<option value="fixed">Fixed</option>
													<option value="default" selected="selected">Default</option>
											</select>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!-- END THEME PANEL -->
					</div>
					<!-- END PAGE TOOLBAR -->
				</div>
				<!-- END PAGE HEAD-->
				<!-- BEGIN PAGE BREADCRUMB -->

				<!-- END PAGE BREADCRUMB -->
				<!-- BEGIN PAGE BASE CONTENT -->

				<tiles:insertAttribute name="body" />
				<!-- END PAGE BASE CONTENT -->
			</div>
			<!-- END CONTENT BODY -->
		</div>
		<!-- END CONTENT -->

	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<tiles:insertAttribute name="footer" />

	<!-- END FOOTER -->

	<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<script src="../assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->
	<!-- BEGIN CORE PLUGINS -->
	<script src="../assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/js.cookie.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="../assets/global/plugins/moment.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery-ui/jquery-ui.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/clockface/js/clockface.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<script src="../assets/global/scripts/app.min.js"
		type="text/javascript"></script>
	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script
		src="../assets/pages/scripts/components-date-time-pickers.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script src="../assets/layouts/layout4/scripts/layout.min.js"
		type="text/javascript"></script>
	<script src="../assets/layouts/layout4/scripts/demo.min.js"
		type="text/javascript"></script>
	<script src="../assets/layouts/global/scripts/quick-sidebar.min.js"
		type="text/javascript"></script>
	<script src="../assets/layouts/global/scripts/quick-nav.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/select2/js/select2.full.min.js"
		type="text/javascript"></script>
	<!--  Calendar -->
	<script src="../assets/global/plugins/fullcalendar/fullcalendar.min.js"
		type="text/javascript"></script>
	<!-- เป็นที่ตัวนี้  Date Picker ใช้ไม่ได้ --
        <!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="../assets/global/scripts/datatable.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/datatables/datatables.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../assets/pages/scripts/table-datatables-buttons.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->



</body>
<script>
	
</script>

</html>
