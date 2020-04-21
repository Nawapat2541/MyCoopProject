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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%-- <script
	src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.js"></script> --%>
<c:set var="nowtime" value="<%=new java.util.Date()%>" />
<fmt:formatDate pattern="yyyy" value="${datetime}" var="year_now" />
<fmt:formatDate pattern="MM" value="${datetime}" var="month_now" />
<fmt:formatDate pattern="yyyy" value="${nowtime}" var="yearnow" />


<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-area-chart font-blue"></i> <span
				class="caption-subject font-red sbold "> TRAVEL EXPENSE Charts </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">


		<form class="form-inline margin-bottom-40" action="search_graphexpense" style="margin-left: 20%;">
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Month : </label> <select
					class="form-control select2me" name="month">
					<option value='01' id='01'
						<c:if test="${month_now == 01 }"><c:out value="selected=selected"/> </c:if>>Janaury</option>
					<option value='02' id='02'
						<c:if test="${month_now == 02 }"><c:out value="selected=selected"/> </c:if>>February</option>
					<option value='03' id='03'
						<c:if test="${month_now == 03 }"><c:out value="selected=selected"/> </c:if>>March</option>
					<option value='04' id='04'
						<c:if test="${month_now == 04 }"><c:out value="selected=selected"/> </c:if>>April</option>
					<option value='05' id='05'
						<c:if test="${month_now == 05 }"><c:out value="selected=selected"/> </c:if>>May</option>
					<option value='06' id='06'
						<c:if test="${month_now == 06 }"><c:out value="selected=selected"/> </c:if>>June</option>
					<option value='07' id='07'
						<c:if test="${month_now == 07 }"><c:out value="selected=selected"/> </c:if>>July</option>
					<option value='08' id='08'
						<c:if test="${month_now == 08 }"><c:out value="selected=selected"/> </c:if>>August</option>
					<option value='09' id='09'
						<c:if test="${month_now == 09 }"><c:out value="selected=selected"/> </c:if>>September</option>
					<option value='10' id='10'
						<c:if test="${month_now == 10 }"><c:out value="selected=selected"/> </c:if>>October</option>
					<option value='11' id='11'
						<c:if test="${month_now == 11 }"><c:out value="selected=selected"/> </c:if>>November</option>
					<option value='12' id='12'
						<c:if test="${month_now == 12 }"><c:out value="selected=selected"/> </c:if>>December</option>
				</select>
			</div>

			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Year : </label> <select
					class="form-control select2me" name="year">
					<c:forEach begin="0" end="9" var="i">
						<option value="${yearnow - i}" id="${yearnow - i}" <c:if test="${yearnow-i==year_now }"><c:out value="selected=selected"/> </c:if>>${yearnow - i}</option>
					</c:forEach>
				</select>
			</div>


			<button type="submit" class="btn sbold blue"> <i class="fa fa-search"></i> Search</button>
		</form>
	</div>
</div>
<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold ">TRAVEL EXPENSE CHART Day</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="expensechart_values" style="height: 500px; margin-top: 0px;"></div>
</div>
</div>

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold ">TRAVEL EXPENSE CHART Monney</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="expensechart_monney" style="height: 500px; margin-top: 0px;"></div>
</div>
</div>
<script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
    	 var data = google.visualization.arrayToDataTable([
             ['วัน','จำนวนการเดินทาง', { role: "style" } ],
             ['จันทร์',${monday}, "color: yellow"],
             ['อังคาร',${tueday}, "color: pink"],
             ['พุธ',${wenday}, "color: green"],
             ['พฤหัสบดี',${thuday}, "color: orange"],
             ['ศุกร์',${friday}, "color: blue"]
           ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "จำนวนครั้งที่เดินทางแต่ละวัน",
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("expensechart_values"));
      chart.draw(view, options);
  }
  $(window).resize(function(){
		drawChart();
	});
  </script>
<script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
    	 var data = google.visualization.arrayToDataTable([
             ['วัน','จำนวนการเดินทาง', { role: "style" } ],
             ['จันทร์',${monamount}, "color: yellow"],
             ['อังคาร',${tueamount}, "color: pink"],
             ['พุธ',${wenamount}, "color: green"],
             ['พฤหัสบดี',${thuamount}, "color: orange"],
             ['ศุกร์',${friamount}, "color: blue"]
           ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "ค่าเดินทางแต่ละวัน",
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("expensechart_monney"));
      chart.draw(view, options);
  }
  $(window).resize(function(){
		drawChart();
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
<script>$(document).ready(function () {
	var value = "${flag}";
	if (value == "1") {
    var user = "${userId}";
    var month = "${month1}";
    var year = "${year1}";
    var enable = '${statusEnable}';
  	document.getElementById(user).selected = "true";
  	document.getElementById(year).selected = "true";
  	document.getElementById(month).selected = "true";
	document.getElementById(enable).selected = "true";
	}else{
    $('.select2me').select2();
	}
});</script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<!-- <script src="../assets/pages/scripts/charts-amcharts.min.js"
	type="text/javascript"></script> -->
<!-- END PAGE LEVEL SCRIPTS -->
