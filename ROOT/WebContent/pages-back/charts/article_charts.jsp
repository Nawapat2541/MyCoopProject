<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="shortcut icon" href="favicon.ico" />
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold "> Most Writer Charts </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
		<div class="portlet-body">
			<div id="chart_1" class="chart"
				style="height: 500px; margin-top: 20px;"></div>
		</div>
	</div>
</div>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold "> Most Type Charts </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
		<div class="portlet-body">
			<div id="chart_2" class="chart"
				style="height: 500px; margin-top: 20px;"></div>
		</div>
	</div>
</div>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold "> Most Tag Charts </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
		<div class="portlet-body">
			<div id="chart_3" class="chart"
				style="height: 500px; margin-top: 20px;"></div>
		</div>
	</div>
</div>
<script>
	var ChartsAmcharts = function() {

		var initChartSample1 = function() {

			var chart1 = AmCharts.makeChart("chart_1", {
				"type" : "pie",
				"theme" : "light",
				"autoMargins" : false,
				"marginLeft" : 30,
				"marginRight" : 8,
				"marginTop" : 10,
				"marginBottom" : 26,
				"fontFamily" : 'Open Sans',

				"dataProvider" : [ 
				<c:forEach var="writer" items="${writer}" varStatus="Count">
					{
						"name" : "${writer.user_id}",
						"num" : ${writer.articlenum},
						"color" : "#00cccc"
					},
				</c:forEach>
				],
				"valueField" : "num",
				"titleField" : "name",
				"colorField" : "color",
				"balloon" : {
					"fixedPosition" : true
				}

			});
			$('#chart_1').closest('.portlet').find('.fullscreen').click(
					function() {
						chart1.invalidateSize1();
					});

		}
		
		var initChartSample2 = function() {

			var chart1 = AmCharts.makeChart("chart_2", {
				"type" : "pie",
				"theme" : "light",
				"autoMargins" : false,
				"marginLeft" : 30,
				"marginRight" : 8,
				"marginTop" : 10,
				"marginBottom" : 26,
				"fontFamily" : 'Open Sans',

				"dataProvider" : [ 
				<c:forEach var="type" items="${type}" varStatus="Count">
					{
						"name" : "${type.name}",
						"num" : ${type.typenum},
						"color" : "#ff00ff"
					},
				</c:forEach>
				],
				"valueField" : "num",
				"titleField" : "name",
				"colorField" : "color",
				"balloon" : {
					"fixedPosition" : true
				}

			});
			$('#chart_2').closest('.portlet').find('.fullscreen').click(
					function() {
						chart1.invalidateSize2();
					});

		}

		var initChartSample3 = function() {

			var chart1 = AmCharts.makeChart("chart_3", {
				"type" : "pie",
				"theme" : "light",
				"autoMargins" : false,
				"marginLeft" : 30,
				"marginRight" : 8,
				"marginTop" : 10,
				"marginBottom" : 26,
				"fontFamily" : 'Open Sans',

				"dataProvider" : [ 
				<c:forEach var="tag" items="${tag}" varStatus="Count">
					{
						"name" : "${tag.name}",
						"num" : ${tag.tagnum},
						"color" : "#EEEE00"
					},
				</c:forEach>
				],
				"valueField" : "num",
				"titleField" : "name",
				"colorField" : "color",
				"balloon" : {
					"fixedPosition" : true
				}

			});
			$('#chart_3').closest('.portlet').find('.fullscreen').click(
					function() {
						chart1.invalidateSize3();
					});

		}


		return {
			//main function to initiate the module

			init : function() {

				initChartSample1();
				initChartSample2();
				initChartSample3();

			}

		};

	}();

	jQuery(document).ready(function() {
		ChartsAmcharts.init();
	});
</script>

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="../assets/global/plugins/amcharts/amcharts/amcharts.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/amcharts/serial.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/amcharts/pie.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/amcharts/radar.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/amcharts/themes/light.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/amcharts/amcharts/themes/patterns.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/amcharts/themes/chalk.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/ammap/ammap.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/amcharts/ammap/maps/js/worldLow.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/amstockcharts/amstock.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/amcharts/amcharts/xy.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/counterup/jquery.waypoints.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/counterup/jquery.counterup.min.js"
	type="text/javascript"></script>