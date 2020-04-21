<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
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
<!-- BEGIN THEME GLOBAL STYLES -->
<link href="../assets/global/css/components.min.css" rel="stylesheet"
	id="style_components" type="text/css" />
<link href="../assets/global/css/plugins.min.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="../assets/pages/css/faq.min.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->
<link href="../assets/layouts/layout4/css/layout.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/layouts/layout4/css/themes/default.min.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="../assets/layouts/layout4/css/custom.min.css"
	rel="stylesheet" type="text/css" />
<!-- END THEME LAYOUT STYLES -->
<link rel="shortcut icon" href="favicon.ico" />


<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-question font-red"></i> <span
				class="caption-subject font-red sbold uppercase">FAQ
				(Frequently asked questions)</span>
		</div>
	</div>
	<!-- ADD -->
	<div class="faq-page faq-content-1">
		<div class="faq-content-container">
			<div class="row">
				<div class="col-md-12">
					<div class="faq-section bordered">
						<h2 class="faq-title uppercase font-blue">Create Question &
							Answer</h2>
						<div>
							<div class="form-group form-md-line-input">
								<label class="col-md-2">Type : </label>
								<div class="col-md-6" style="margin-left: -.4cm;">
									<select class="form-control">
										<option>General</option>
										<option>Technical</option>
									</select>
								</div>
							</div>
							<div class="form-group form-md-line-input">
								<label class="col-md-2">Question :</label>
								<div class="col-md-6">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group form-md-line-input">
								<label class="col-md-2">Answer :</label>
								<div class="col-md-6">
									<textarea class="form-control" rows="6"></textarea>
								</div>
							</div>
							<div style="margin-top: 4cm;"
								class="form-group form-md-line-input">
								<center style="margin-right: 8cm;">
									<button type="submit" class=" fa-item btn sbold blue">
										<i class="fa fa-save" style="color: white;"></i>&nbsp;&nbsp;&nbsp;Save
									</button>
								</center>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--END ADD -->
	<div class="portlet-body">

		<!-- BEGIN PAGE BASE CONTENT -->
		<div class="faq-page faq-content-1">
			<div class="faq-content-container">
				<div class="row">
					<div class="col-md-6">
						<div class="faq-section bordered">
							<h2 class="faq-title uppercase font-blue">General</h2>
							<div class="panel-group accordion faq-content" id="accordion1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion1"
												href="#collapse_1"> How do I vote or respond to a poll?</a>
										</h4>
									</div>
									<div id="collapse_1" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion1"
												href="#collapse_2"> Do you accept purchase orders?</a>
										</h4>
									</div>
									<div id="collapse_2" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion1"
												href="#collapse_3"> How many responses per poll (which
												plan) do I need?</a>
										</h4>
									</div>
									<div id="collapse_3" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion1"
												href="#collapse_4"> What if my audience does not have a
												phone or a web-enabled device with internet access?</a>
										</h4>
									</div>
									<div id="collapse_4" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion1"
												href="#collapse_5"> How fast do responses show up?</a>
										</h4>
									</div>
									<div id="collapse_5" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="faq-section bordered">
							<h2 class="faq-title uppercase font-blue">Technical</h2>
							<div class="panel-group accordion faq-content" id="accordion2">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion2"
												href="#collapse_2_1"> How much does it cost?</a>
										</h4>
									</div>
									<div id="collapse_2_1" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion2"
												href="#collapse_2_2"> Do you accept purchase orders?</a>
										</h4>
									</div>
									<div id="collapse_2_2" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion2"
												href="#collapse_2_3"> What is the K-12 classroom size
												promise?</a>
										</h4>
									</div>
									<div id="collapse_2_3" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion2"
												href="#collapse_2_4"> What if my audience does not have
												a phone or a web-enabled device with internet access?</a>
										</h4>
									</div>
									<div id="collapse_2_4" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<i class="fa fa-circle"></i> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion2"
												href="#collapse_2_5"> How can I share my poll with
												remote participants?</a>
										</h4>
									</div>
									<div id="collapse_2_5" class="panel-collapse collapse">
										<div class="panel-body">
											<a class="btn btn-circle btn-icon-only btn-default"
												onclick="nextStep();"> <i class="icon-wrench"></i>
											</a>
											<p>Duis autem vel eum iriure dolor in hendrerit in
												vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci
												tation ullamcorper suscipit lobortis nisl ut.</p>
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END PAGE BASE CONTENT -->
	</div>
	<!-- END CONTENT BODY -->
</div>

<script>
	function nextStep() {
		document.location = "demo?pageName=assistance_helper_edit";
	}
</script>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script>
<script src="../assets/global/plugins/ie8.fix.min.js"></script>
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
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="../assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="../assets/layouts/layout4/scripts/layout.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/layout4/scripts/demo.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/global/scripts/quick-sidebar.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/global/scripts/quick-nav.min.js"
	type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->