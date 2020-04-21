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
<%-- <script
	src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.js"></script> --%>
<c:set var="now" value="<%=new java.util.Date()%>" />
 <fmt:formatDate pattern = "yyyy"  value = "${now}" var="year_now" />
  <fmt:formatDate pattern = "MM" value = "${now}"  var="month_now"/>
<div class="portlet light bordered">
<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-area-chart font-blue"></i> <span
				class="caption-subject font-red sbold "> Leaves Charts Report </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
<div class="portlet-body">
		<form class="form-inline margin-bottom-40" action="search_leavechartsReport" style="margin-left: 20%;" >
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Month : </label> 
				<select class="form-control select2me" name="month">
					<option value="all_M"id="all_M">All</option>
					<option value='01' id='01' <c:if test="${month_now == 01 }"><c:out value="selected=selected"/> </c:if>>January</option>
					<option value='02' id='02'<c:if test="${month_now == 02 }"><c:out value="selected=selected"/> </c:if>>February</option>
					<option value='03' id='03'<c:if test="${month_now == 03 }"><c:out value="selected=selected"/> </c:if>>March</option>
					<option value='04' id='04'<c:if test="${month_now == 04 }"><c:out value="selected=selected"/> </c:if>>April</option>
					<option value='05' id='05'<c:if test="${month_now == 05 }"><c:out value="selected=selected"/> </c:if>>May</option>
					<option value='06' id='06'<c:if test="${month_now == 06 }"><c:out value="selected=selected"/> </c:if>>June</option>
					<option value='07' id='07' <c:if test="${month_now == 07 }"><c:out value="selected=selected"/> </c:if>>July</option>
					<option value='08' id='08' <c:if test="${month_now == 08 }"><c:out value="selected=selected"/> </c:if>>August</option>
					<option value='09' id='09' <c:if test="${month_now == 09 }"><c:out value="selected=selected"/> </c:if>>September</option>
					<option value='10' id='10' <c:if test="${month_now == 10 }"><c:out value="selected=selected"/> </c:if>>October</option>
					<option value='11' id='11'<c:if test="${month_now == 11 }"><c:out value="selected=selected"/> </c:if>>November</option>
					<option value='12' id='12'<c:if test="${month_now == 12 }"><c:out value="selected=selected"/> </c:if>>December</option>	
				</select>
			</div>
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Year : </label>
				<select class="form-control select2me" name="year">
					<c:forEach begin="0" end="4" var="i">
        				<option value="${year_now - i}" id="${year_now - i}">${year_now - i}</option>
					</c:forEach>
						<option value="all_Y"id="all_Y">All</option>
				</select>
			</div>
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Sort  : </label>
				<select class="form-control select2me" name="sort">
						<option value="1"id="1">Large to Small</option>
						<option value="2"id="2">Small to Large</option>
				</select>
			</div>
			<button type="submit" class="btn sbold blue" > <i class="fa fa-search"></i> Search</button>
		</form>
	</div>
</div>	
<!-- Chart	 -->
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-bar-chart font-blue"></i>
			<span class="caption-subject font-red sbold "> LEAVES</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
			href="javascript:;" data-original-title="" title=""></a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="chart_4" class="chart" style="height: 540px; margin-top: 0px;"></div>
	</div>
</div>

<script>
var ChartsAmcharts = function() {
   var initChartSample4 = function() {
	   //Setup graph
	var dataprovider = [];
   	var sum = [0,0,0,0,0,0,0,0,0,0] ;
   	<c:forEach var="first1" items="${leaveBarcharts}" varStatus="loop">
		<c:forEach var="second1" items="${first1}">
			<c:if test="${second1.leave_type_id.toString() != '1' && second1.leave_type_id.toString() != '5' && second1.leave_type_id.toString() != '3' && second1.leave_type_id.toString() != '4' }">
				sum[${loop.index}] = sum[${loop.index}] + parseInt('${second1.countL}');
		 </c:if>
		</c:forEach>	
	</c:forEach>
   	<c:forEach var="first" items="${leaveBarcharts}" varStatus="status">
		dataprovider.push({
		<c:forEach var="second" items="${first}" >
		  <c:choose>
			<c:when test="${second.leave_type_id.toString() == '1' || second.leave_type_id.toString() == '5' || second.leave_type_id.toString() == '3' || second.leave_type_id.toString() == '4'  }">
		 		'${second.name}'	: '${second.countL}',
			</c:when>
		  </c:choose>
		</c:forEach>	
		 'อื่น ๆ' : sum[${status.index}],
         "country": '${userLeaves[status.index].name}',
//          "bullet": pic[${status.index % 4}]
		})
		</c:forEach>
//     	 Bar Leaves Chart Report
    var chart1 = AmCharts.makeChart("chart_4", {
    	 "type": "serial",
    	  "theme": "light",
    	  "legend": {
    	        "horizontalGap": 10,
    	        "maxColumns": 1,
    	        "position": "right",
    			"useGraphSettings": true,
    			"markerSize": 10
    	    },
    	  "dataProvider": dataprovider,
    	  "valueAxes": [ {
    		  "stackType": "regular",
    		  "dashLength": 1,
    	        "axisAlpha": 10,
    	       " stroke-Width": 1
    	       
    	        } ],
    	  "gridAboveGraphs": true,
    	  "startDuration":2,
    	  "mouseWheelZoomEnabled":true,
    	//value of type name
    	  "graphs": [{
    	        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span> วัน",
    	        "fillAlphas": 0.8,
    	        "labelText": "[[value]]",
    	        "lineAlpha": 0.3,
    	        "title": "${type_1}",
    	        "type": "column",
    			"color": "#000000",
    	        "valueField": "${type_1}"
    	    }, {
    	        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span> วัน",
    	        "fillAlphas": 0.8,
    	        "labelText": "[[value]]",
    	        "lineAlpha": 0.3,
    	        "title": "${type_5}",
    	        "type": "column",
    			"color": "#000000",
    	        "valueField": "${type_5}"
    	    },{
    	        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span> วัน",
    	        "fillAlphas": 0.8,
    	        "labelText": "[[value]]",
    	        "lineAlpha": 0.3,
    	        "title": "อื่น ๆ",
    	        "type": "column",
    			"color": "#000000",
    	        "valueField": "อื่น ๆ"
    	    }, {
    	        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span> วัน",
    	        "fillAlphas": 0.8,
    	        "labelText": "[[value]]",
    	        "lineAlpha": 0.3,
    	        "customBulletField": "bullet",
    	        "bulletOffset": 10,
    	        "bulletSize": 52,
    	        "title": "${type_3}",
    	        "type": "column",
    			"color": "#000000",
    	        "valueField": "${type_3}"
    	    }, {
    	        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span> วัน",
    	        "fillAlphas": 0.8,
    	        "labelText": "[[value]]",
    	        "lineAlpha": 0.3,
    	        "customBulletField": "bullet",
    	        "bulletOffset": 10,
    	        "bulletSize": 52,
    	        "title": "${type_4}",
    	        "type": "column",
    			"color": "#000000",
    	        "valueField": "${type_4}"
    	    }],
    	    "chartScrollbar": {
    	        "graph": "g1",
    	        "scrollbarHeight": 11
    	    },
    	    "chartCursor": {
    	        "cursorPosition": "mouse"
    	    },
    	   rotate: !0,
    	  "categoryField": "country",
    	  "categoryAxis": {	
    		    "gridPosition": "start",
    		    "gridAlpha": 0,
    		    "tickPosition": "start",
    		    "tickLength": 20
    		  },
    	  "export": {	
    	    "enabled": true
    	  }
    	} );
    	chart1.addListener("rendered", zoomChart);
    	zoomChart();
    function zoomChart() {
        chart1.ignoreNextZoomed = true;
        chart1.zoomToIndexes(0,14); // show amount index
    }
    	$('#chart_4').closest('.portlet').find('.fullscreen').click(function() {
        chart1.invalidateSize();
    });
    }
  return {
      //main function to initiate the module
      init: function() {
          initChartSample4();
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
	
<script>$(document).ready(function () {
	var value = "${flag}";
	if (value == "1") {
    	var user = "${userId}";
   		var month = "${month1}";
    	var year = "${year1}";
   		var enable = '${statusEnable}';
    	var sort = "${sortAl}";

  		document.getElementById(year).selected = "true";
  		document.getElementById(month).selected = "true";
		document.getElementById(enable).selected = "true";
		document.getElementById(sort).selected = "true";
	}else{
    $('.select2me').select2();
	}
 });
 </script>

