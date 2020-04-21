<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="shortcut icon" href="favicon.ico" />
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>
<c:set var="now" value="<%=new java.util.Date()%>" />
 <fmt:formatDate pattern = "yyyy"  value = "${now}" var="year_now" />
  <fmt:formatDate pattern = "MM" value = "${now}"  var="month_now"/>

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold ">10 Employee Check-in on time</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="row">
		<form method="post" name="form" action="checkinOnTimeChart">
		<div class="col-lg-8">
			<div class="col-lg-6 col-md-12" style="margin-right:-15px; text-align:center;">
			<div class="form-group form-md-line-input " >
					<div class="col-lg-2"><label class="control-label" for="form_control_1">Month: </label> </div>
					<div class="col-lg-10" style="padding-right:0;">
						<select class="form-control select2me" name="month">
							<option value='01' id='01' <c:if test="${month == 01 }"><c:out value="selected=selected"/> </c:if>>Janaury</option>
							<option value='02' id='02'<c:if test="${month == 02 }"><c:out value="selected=selected"/> </c:if>>February</option>
							<option value='03' id='03'<c:if test="${month == 03 }"><c:out value="selected=selected"/> </c:if>>March</option>
							<option value='04' id='04'<c:if test="${month == 04 }"><c:out value="selected=selected"/> </c:if>>April</option>
							<option value='05' id='05'<c:if test="${month == 05 }"><c:out value="selected=selected"/> </c:if>>May</option>
							<option value='06' id='06'<c:if test="${month == 06 }"><c:out value="selected=selected"/> </c:if>>June</option>
							<option value='07' id='07' <c:if test="${month == 07 }"><c:out value="selected=selected"/> </c:if>>July</option>
							<option value='08' id='08' <c:if test="${month == 08 }"><c:out value="selected=selected"/> </c:if>>August</option>
							<option value='09' id='09' <c:if test="${month == 09 }"><c:out value="selected=selected"/> </c:if>>September</option>
							<option value='10' id='10' <c:if test="${month == 10 }"><c:out value="selected=selected"/> </c:if>>October</option>
							<option value='11' id='11'<c:if test="${month == 11 }"><c:out value="selected=selected"/> </c:if>>November</option>
							<option value='12' id='12'<c:if test="${month == 12 }"><c:out value="selected=selected"/> </c:if>>December</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-12" style="text-align:center;">
				<div class="form-group form-md-line-input ">
				<div class="col-lg-2">
					<label class="control-label" for="form_control_1">Year:</label>
				</div>
				<div class="col-lg-10">
				 <select class="form-control select2me" name="year">
					<c:forEach begin="2009" end="${year_now}" var="select">
			        	<option value="${select}" <c:if test="${year == select }"><c:out value="selected=selected"/> </c:if>>${select}</option>
					</c:forEach>
				</select>
				</div>
				</div>
			</div>
		</div>
		<div class="col-lg-2 col-md-12" style="text-align:center;">
			<div class="form-group form-md-line-input ">
				<select class="form-control select2me" name="limit">
					<option value='10' id='10'<c:if test="${limit == 10 }"><c:out value="selected=selected"/> </c:if>>10</option>
					<option value='20' id='20'<c:if test="${limit == 20 }"><c:out value="selected=selected"/> </c:if>>20</option>
					<option value='30' id='30'<c:if test="${limit == 30 }"><c:out value="selected=selected"/> </c:if>>30</option>
					<option value='40' id='40'<c:if test="${limit == 40 }"><c:out value="selected=selected"/> </c:if>>40</option>
					<option value='50' id='50'<c:if test="${limit == 50 }"><c:out value="selected=selected"/> </c:if>>50</option>
				</select>
			</div>
		</div>
		<div class="col-lg-2 col-md-12" style="text-align:center;">
			<div class="form-group form-md-line-input ">
			<div class="col-lg-12 ">
				<button  type="submit" class="btn sbold blue">
					<i class="fa fa-search"></i> Search
				</button>
				</div>
			</div>
		</div>
		<c:forEach var="statusL" items="${status}">
		</c:forEach>
	</form>
	</div>
	
	<div class="row">
		<div class="portlet-body">
<%-- 				${month} --%>
<%-- 				${year} --%>
			
			<div id="chart_4" class="chart"
				style="height: 500px; margin-top: 20px;"></div>
		
	</div>
</div>
<script>
var ChartsAmcharts = function() {
    var initChartSample4 = function() {
            var dataprovider = [];

	
            <c:forEach var="second" items="${status}" varStatus="status">
            dataprovider.push({
            	'${second.name}'	:'${second.count}' ,
            	
	            "country": '${second.user_create}',
	            "visits": '${second.count}',
	            "name": '${second.name}',
	  
            })
             </c:forEach>	
		
        var chart1 = AmCharts.makeChart("chart_4", {
        
            "type": "serial",
            "theme": "light",

            "dataProvider": dataprovider,
            "valueAxes": [ {
                "stackType": "regular",
                "dashLength": 1,
                    "axisAlpha": 10,
                    "stroke-Width": 1
                    } ],
            "gridAboveGraphs": true,
            "startDuration": 2,
            "graphs": [{
                    "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[name]]: <b>[[value]]</b></span> ครั้ง",
                    "fillAlphas": 1,
                    "labelText": "[[value]]",
                    "lineAlpha": 0.3,
                   
                    "title": "On Time",
                    "type": "column",
                    "color": "#000000",
                    "valueField": "visits"
                }],
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