<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />

<style>
@media only screen and (max-width: 1200px) {
	.paddingmd {
		padding-top: 15px !important;
	}
}

@media only screen and (max-width: 960px) {
	.hiddensm {
		visibility: hidden;
	}
}

@media only screen and (max-width: 1300px) {
	.paddinglg {
		margin-left: 50px !important;
	}
}

@media only screen and (max-width: 769px) {
	.centerxs {
		display: block;
		margin-left: auto;
		margin-right: auto;
		text-align: center !important;
	}
}

.item-head {
	width: width:100% !important;
}

.select2-container--bootstrap .select2-results__group {
	color: #fff;
	background-color: gray;
	display: block;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857;
	white-space: nowrap
}

.boxContent {
	border: 1px solid lightblue;
	padding: 15px;
	margin: 10px;
	width: 90%;
}
</style>
<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="portlet light bordered">

	<div class="portlet-title">
		<div class="caption">
			<i class="icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Today Login</span>
		</div>
		<div class="actions">
			<a class="btn  btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<div class="tools">
			<a href="javascript:;" class="collapse" data-original-title=""
				title=""> </a> <a href="#portlet-config" data-toggle="modal"
				class="config" data-original-title="" title=""> </a> <a
				href="javascript:;" class="reload" data-original-title="" title="">
			</a> <a href="javascript:;" class="remove" data-original-title=""
				title=""> </a>
		</div>

		<!-- table -->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-comments"></i>Show Today Login User
				</div>
			</div>
			<div class="portlet-body">
				<div id="divTable" class="table-scrollable">
					<table id="table"></table>
				</div>
			</div>
		</div>
		<!-- close table -->
	</div>

	<script>
		$(document).ready(function() {
			tableOnload();
			console.log("ready");
		});
	</script>
	<script>
		var data = "x";
		function checkChange(data) {
			var json = data
			return json;
		}

		function tableOnload() {
			console.log("tableOnload");
			callAjax()
			setTimeout("doLoop();", 30000);
		}

		function doLoop() {
			console.log("doLoop");
			tableOnload();

		}
		function callAjax() {
			console.log("callAjax");
			console.log("data");
			console.log(data);
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/todayLoginJson",
				crossorigin : true,
				dataType : "json",
				data : data,
				success : function(json) {
					console.log("success ajax");
					/* if (data == ("'" + json[0].work_hours_id + "'")) {
						console.log("เท่ากัน================" + data);
					} else { */
						console.log("ไม่เท่ากัน+++++++++++++++" + data);
						var divTable = document.getElementById("divTable");
						document.getElementById("table").remove();
						var table = document.createElement("table");
						table.setAttribute('id', 'table');
						table.setAttribute('class',
								'table table-striped table-hover');
						divTable.appendChild(table);
						var tHead = document.createElement("thead");
						table.appendChild(tHead);
						var trHead = document.createElement("tr");
						tHead.appendChild(trHead);
						var thHead1 = document.createElement("th");
						var thHead2 = document.createElement("th");
						var thHead3 = document.createElement("th");
						var thHead4 = document.createElement("th");
						var textThHead = document.createTextNode("User Name");
						thHead1.appendChild(textThHead);
						textThHead = document.createTextNode("Work Hours ID");
						thHead2.appendChild(textThHead);
						textThHead = document.createTextNode("Type");
						thHead3.appendChild(textThHead);
						textThHead = document.createTextNode("Time");
						thHead4.appendChild(textThHead);
						trHead.appendChild(thHead1);
						trHead.appendChild(thHead2);
						trHead.appendChild(thHead3);
						trHead.appendChild(thHead4);
						var tBody = document.createElement("tbody");
						table.appendChild(tBody);
						$.each(json, function() {
							var trBody = document.createElement("tr");
							tBody.appendChild(trBody);
							var td = document.createElement("td");
							td.setAttribute('id', this.user_create);
							var text = document
									.createTextNode(this.user_create);
							td.appendChild(text);
							trBody.appendChild(td);
							
							var td = document.createElement("td");
							td.setAttribute('id', this.work_hours_id);
							text = document.createTextNode(this.work_hours_id);
							td.appendChild(text);
							trBody.appendChild(td);

							switch (this.work_hours_type) {
							case '1':
								var td = document.createElement("td");
								td.setAttribute('id', this.work_hours_type);
								text = document
										.createTextNode('Check in');
								td.appendChild(text);
								trBody.appendChild(td);
								break;
							case '2':
								var td = document.createElement("td");
								td.setAttribute('id', this.work_hours_type);
								text = document
										.createTextNode('Check out');
								td.appendChild(text);
								trBody.appendChild(td);
								break;
							default:
							}
							var td = document.createElement("td");
							td.setAttribute('id', this.time_create);
							text = document.createTextNode(this.time_create);
							td.appendChild(text);
							trBody.appendChild(td);

							console.log("--------");
							console.log(trBody);
							console.log("--------");

						});
					/* } */
					data = checkChange("'" + json[0].work_hours_id + "'");
					console.log(json);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert('error');
				}
			});
		}
	</script>