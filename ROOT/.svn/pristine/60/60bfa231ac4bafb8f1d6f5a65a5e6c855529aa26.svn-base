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
				class="caption-subject font-red sbold "> XY Charts </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">


		<form class="form-inline margin-bottom-40"  action="search_graph"style="margin-left:5%;">
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Staff : </label> 
				<select
					class="form-control select2me" name="name">
					<optgroup label="Enable">
						<c:forEach var="user" items="${userseq}">

							<c:if test="${user.enable == 1 }">
								<c:if test="${userSelect == null }">
									<option value="${user.id}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
								<c:if test="${userSelect != null }">
									<option value="${userSelect}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</optgroup>
					<optgroup label="Disable">
						<c:forEach var="user" items="${userseq}">

							<c:if test="${user.enable == 0 }">
								<c:if test="${userSelect == nulll }">
									<option value="${user.id}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
								<c:if test="${userSelect != nulll }">
									<option value="${userSelect}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</optgroup>
				</select>
			</div>
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Month : </label> <select
					class="form-control select2me" name="month">
					<option value='01' id='01' <c:if test="${month_now == 01 }"><c:out value="selected=selected"/> </c:if>>Janaury</option>
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
				<label class="control-label" for="form_control_1">Year : </label> <select
					class="form-control select2me" name="year">
					<c:forEach begin="0" end="4" var="i">
        <option value="${year_now - i}" id="${year_now - i}">${year_now - i}</option>
</c:forEach>
<option value="All" id="All">All</option>
				</select>
			</div>
			
					<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Enable : </label> <select
					class="form-control select2me" name="enable">
<option value="1" id="1">Enable</option>
<option value="0" id="0">All</option>
				</select>
			</div>
			

<button  type="submit" class="btn sbold blue">
					<i class="fa fa-search"></i> Search
				</button>
		</form>









		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat2 bordered">
					<div class="display">
						<div class="number">
							<h3 class="font-green-sharp">
								<c:set var="time_in" value='${avg_in}' />
								<c:set var="str_timein1" value="${fn:substring(time_in,0,2)}" />
								<c:set var="str_timein2" value="${fn:substring(time_in,4,6)}" />
								<c:if test="${str_timein1 < 10 }">0</c:if>
								<span class="counter" data-count="${str_timein1}">0</span> h
								<c:if test="${str_timein2 < 10 }">0</c:if>
								<span class="counter" data-count="${str_timein2}">0</span> m

							</h3>
							<small>AVERAGE CHECK IN </small>
						</div>
						<div class="icon">
							<i class="fa fa-clock-o"></i>
						</div>
					</div>

				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat2 bordered">
					<div class="display">
						<div class="number">
							<h3 class="font-red-haze">
								<c:set var="time_out" value='${avg_out}' />
								<c:set var="str_timout1" value="${fn:substring(time_out,0,2)}" />
								<c:set var="str_timout2" value="${fn:substring(time_out,4,6)}" />
								<c:if test="${str_timout1 < 10 }">0</c:if>
								<span class="counter" data-count="${str_timout1}">0</span> h
								<c:if test="${str_timout2 < 10 }">0</c:if>
								<span class="counter" data-count="${str_timout2}">0</span> m
							</h3>
							<small>AVERAGE CEHCK OUT</small>
						</div>
						<div class="icon">
							<i class="fa fa-sign-out"></i>
						</div>
					</div>

				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat2 bordered">
					<div class="display">
						<div class="number">
							<h3 class="font-blue-sharp">

								<c:set var="time_in_all" value='${avg_in_all}' />
								<c:set var="str_time_in_all1"
									value="${fn:substring(time_in_all,0,2)}" />
								<c:set var="str_time_in_all2"
									value="${fn:substring(time_in_all,4,6)}" />
								<c:if test="${str_time_in_all1 < 10 }">0</c:if>
								<span class="counter" data-count="${str_time_in_all1}">0</span>
								h
								<c:if test="${str_time_in_all2 < 10 }">0</c:if>
								<span class="counter" data-count="${str_time_in_all2}">0</span>
								m
							</h3>
							<small>AVERAGE TOTAL</small>
						</div>
						<div class="icon">
							<i class="fa fa-clock-o"></i>
						</div>
					</div>

				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat2 bordered">
					<div class="display">
						<div class="number">
							<h3 class="font-purple-soft">
								<c:set var="time_out_all" value='${avg_out_all}' />
								<c:set var="str_time_out_all1"
									value="${fn:substring(time_out_all,0,2)}" />
								<c:set var="str_time_out_all2"
									value="${fn:substring(time_out_all,4,6)}" />
								<c:if test="${str_time_out_all1 < 10 }">0</c:if>
								<span class="counter" data-count="${str_time_out_all1}">0</span>
								h
								<c:if test="${str_time_out_all2 < 10 }">0</c:if>
								<span class="counter" data-count="${str_time_out_all2}">0</span>
								m
							</h3>
							<small>AVERAGE TOTAL</small>

						</div>
						<div class="icon">
							<i class="fa fa-sign-out"></i>
						</div>
					</div>
			
				</div>
			</div>
		</div>
		<div id="chart_1" class="chart"
			style="height: 500px; margin-top: 0px;"></div>

	</div>
</div>

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-pie-chart font-blue"></i> <span
				class="caption-subject font-red sbold "> PIE Charts </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="chart_2" class="chart"
			style="height: 500px; margin-top: 20px;"></div>
	</div>
</div>

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold ">Simple Column Chart</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="chart_3" class="chart"
			style="height: 500px; margin-top: 20px;"></div>
	</div>
</div>


<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold ">Top 10 LEAVES</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">

		
		<div id="chart_4" class="chart"
			style="height: 500px; margin-top: 0px;"></div>
</div>
</div>

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold ">LEAVES of Years</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		
		<div id="chart_5" class="chart"
			style="height: 500px; margin-top: 0px;"></div>
</div>
</div>
<script>
function convertMinsToHrsMins (minutes) {
	  var h = Math.floor(minutes / 60);
	  var m = minutes % 60;
	  h = h < 10 ? '0' + h : h;
	  m = m < 10 ? '0' + m : m;
	  return h + ':' + m;
	}
function pad2(number) {
	   
    return (number < 10 ? '0' : '') + number
  
}
var ChartsAmcharts = function() {
    	     
    var initChartSample1 = function() { 
    	
    	var chartData = [];
       	<c:forEach var="work_in" items="${work_checkin}">
    	<c:set var = "string1" value ='${work_in}'/>
    	<c:set var = "string2" value = "${fn:substring(string1,22,33)}" />
    	<c:set var = "string3" value = "${fn:substring(string1,22,38)}" />
    	<c:set var = "string4" value = "${fn:substring(string3,0,7)}" />
    	<c:set var = "string5" value = "${fn:substring(string3,11,16)}" />	

       		 chartData.push({'date1':'${string2}',
    			 'y1': '${string4}-02 ${string5}',
    			
    		 })    	   
    		 
    	</c:forEach>    		 
       		 
    
       	    	<c:forEach var="work_out" items="${work_checkout}">
       	    	<c:set var = "string11" value ='${work_out}'/>
       	    	<c:set var = "string12" value = "${fn:substring(string11,22,33)}" />
       	    	<c:set var = "string13" value = "${fn:substring(string11,22,38)}" />
       	    	<c:set var = "string14" value = "${fn:substring(string13,0,7)}" />
       	    	<c:set var = "string15" value = "${fn:substring(string13,11,16)}" />
       	    	<c:set var = "string16" value = "${work_out.workinghours}" />
       	    	var k = "0";
       	    	var a = '${string16}';
       	    	if (a != "" ){
       	    	
       	    var hours = Math.floor(a / 60);
          	var minutes = a % 60;
          	var j  = pad2(minutes) ;
          	var x = hours.toString();
          	var y = j.toString();
           k = x +"." + y ; 
           if(k > 5){
        	   k = k - 1 ;
           }
       	    	}
  	       		 chartData.push({'date2':'${string12}',
       	    			 'y2': '${string14}-02 ${string15}',
       	    			 "aValue": k
       	    		 })    	         	    		 
       	    	</c:forEach> 	
  	       		 
  	       	// Average all

  	       	
          	  	<c:forEach var="all_work_in" items="${avg_hour_in}" varStatus="status">
   
       		 chartData.push({'date3':'${avg_day_in[status.index]}',
    			 'y3': '${string4}-02 ${all_work_in}',
    			
    		 })    	   
    		 
    	</c:forEach>    	 
       		 
    
   	       	
       	  	<c:forEach var="all_work_out" items="${avg_hour_out}" varStatus="status">

    		 chartData.push({'date4':'${avg_day_out[status.index]}',
 			 'y4': '${string4}-02 ${all_work_out}',
 			
 		 })    	   
 		 
 	</c:forEach>  		  
  	       		 
  	       		 
       		 
       

    		
        var chart = AmCharts.makeChart("chart_1", {
        	  "type": "xy",
        	  "theme": "light",
        	  "marginTop": 20,
        	  "marginLeft": 64,
        	  "marginBottom": 60,
        	  "dataDateFormat": "YYYY-MM-DD HH:NN",
        	  "startDuration": 1.0,
        	  "dataProvider":chartData,
        	  "balloon": {
        		    "adjustBorderColor": false,
        		    "shadowAlpha": 0,
        		    "fixedPosition": true
        		  },
        	  "graphs": [{	  
        		  "balloonText": "<div style='margin:5px;'><b>[[x]]</b></div>",
        	    "bullet": "diamond", 
        	    "maxBulletSize": 10,
        	    "minBulletSize": 10,
          	    "lineAlpha": 0.7,
        	    "lineThickness": 2,
        	    "lineColor": "#578EBE", //สีน้ำเงิน Login
        	    "xField": "date1",
        	    "yField": "y1",
        	   
        	  
        	  },{	          		           
           	    "lineAlpha": 0.7,
          	    "lineThickness": 3,
          	    "lineColor": "#2C3E50", //สีเหลืองอ่อน เฉลี่ยของทุกคน Login
          	    "xField": "date3",
          		"dashLength": 5,
          	    "yField": "y3",
          	   
          	  
          	  },{	          		           
             	    "lineAlpha": 0.7,
              	    "lineThickness": 3,
              	   "lineColorField": "y4",
              	    "lineColor": "#2C3E50", //สีส้ม เฉลี่ยของทุกคน Login
              	    "xField": "date4",
              	  	"dashLength": 5,
              	    "yField": "y4",
              	   
              	  
              	  }, {
        		  "balloonText": "<div style='margin:5px;'><b>[[x]]</b><br>จำนวนชั่วโมง :<b> [[value]]</b></div>",
        		  "bullet": "round",
        	    "maxBulletSize": 10,
        	    "minBulletSize": 10,
        	    "lineAlpha": 0.7,
        	    "lineThickness": 2,
        	    "lineColor": "#D05454", //สีแดง Logout
        	    "xField": "date2",
        	    "yField": "y2",
        	    "valueField": "aValue",
        	   
        	  }],
        	  "valueAxes": [{                    	
        	    "id": "v1",
        	    "axisAlpha": 0,
        	    "type": "date",       	    		   
        	    "position": "left" ,
          	     "offset" : 20 ,
        	     "minPeriod" : '2hh',
        	     "minVerticalGap" : 0,
        	    "maximumDate" : '${string4}-03',
        	    "minimumDate" :'${string4}-02',
        	  }, {
        	    "id": "v2",
        	    "axisAlpha": 0,
        	    "position": "bottom",
        	    "type": "date",
        	    "maximumDate" : '${string4}-31',
        	    "minimumDate" :'${string4}-01',
        	   
        	  }],
         	 "chartCursor": {
         	    "pan": false,
         	    "cursorAlpha": 0,
         	    "valueLineAlpha": 0
         	  }
         	           
        });
     
        $('#chart_1').closest('.portlet').find('.fullscreen').click(function() {
            chart.invalidateSize();
        });
      
    }
    var initChartSample2 = function() {

    	var count_IT = '${count}' ;
    	var count_Intern = '${count2}';
    	var count_HR = '${count3}' ;
    	var count_admin = '${count4}' ;
    var chart1 = AmCharts.makeChart("chart_2", {
  	  "type": "pie",
  	  "theme": "light",
  	 "autoMargins": false,
     "marginLeft": 30,
     "marginRight": 8,
     "marginTop": 10,
     "marginBottom": 26,
     "fontFamily": 'Open Sans',            
  
  	  "dataProvider": [{
  	    "country": "HR",
  	    "litres": count_HR,
  	    "color" :  "#1BA39C"
  	  }, {
  	    "country": "IT",
  	    "litres": count_IT,
  	    "color": "#2C3350"
  	  }, {
    	    "country": "Admin",
      	    "litres": count_admin,
      	    "color": "#D05454"
      	  }, {
  	    "country": "InternShip",
  	    "litres": count_Intern,
  	  "color": "#555555"
  	  }],
  	  "valueField": "litres",
  	  "titleField": "country",
  	  "colorField": "color",
  	  "balloon": {
  	    "fixedPosition": true
  	  }
    
  	});
    $('#chart_2').closest('.portlet').find('.fullscreen').click(function() {
        chart1.invalidateSize();
    });
 
    }
    
    var initChartSample3 = function() {

    	var count_taxi = '${count5}' ;
    	var count_mrt = '${count6}';
    	var count_bts = '${count7}' ;
    	var count_morter = '${count8}' ;
     	var count_express = '${count9}' ;
       	var count_all_exp = '${count10}';
    	
    var chart1 = AmCharts.makeChart("chart_3", {
  	   
    	 "type": "serial",
    	  "theme": "light",
    	  "dataProvider": [ {
    	    "country": "TAXI",
    	    "visits": Math.round((count_taxi)*100)/100,
    	    "persent": Math.round( (Math.round((count_taxi)*100))/count_all_exp)
    	  }, {
    	    "country": "MRT",
    	    "visits": Math.round((count_mrt)*100)/100,
    	    "persent":Math.round( (Math.round((count_mrt)*100))/count_all_exp)
    	  }, {
    	    "country": "BTS",
    	    "visits": Math.round((count_bts)*100)/100,
    	    "persent": Math.round( (Math.round((count_bts)*100))/count_all_exp)
    	  }, {
    	    "country": "มอเตอร์ไซต์วิน",
    	    "visits": Math.round((count_morter)*100)/100,
    	    "persent": Math.round( (Math.round((count_morter)*100))/count_all_exp)
    	  }, {
    	    "country": "ทางด่วน",
    	    "visits": Math.round((count_express)*100)/100,
    	    "persent":  Math.round( (Math.round((count_express)*100))/count_all_exp)
    	  }, {
    	    "country": "อื่น ๆ ",
    	    "visits":  Math.round(((count_all_exp-count_taxi-count_mrt-count_bts-count_morter-count_express))*100)/100,
    	    "persent": Math.round( (Math.round((count_all_exp-count_taxi-count_mrt-count_bts-count_morter-count_express)*100))/count_all_exp)
    	  } ],
    	  "valueAxes": [ {
    		  "gridColor": "#FFFFFF",
    		    "gridAlpha": 0.2,
    		    "dashLength": 0
    	  } ],
    	  "gridAboveGraphs": true,
    	  "startDuration": 2,
    	  "graphs": [ {
    	    "balloonText": " การเดินทางประเภท : [[category]] <br> โดยเฉลี่ยเท่ากับ [[persent]] % ",
    	    "fillAlphas": 0.8,
    	    "lineAlpha": 0.2,
    	    "type": "column",
    	    "valueField": "visits"
    	  } ],
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

    	
    	$('#chart_3').closest('.portlet').find('.fullscreen').click(function() {
        chart1.invalidateSize();
    });
 
    }
    
    
    var initChartSample4 = function() {
    	var dataprovider = [];
    	var sum = [0,0,0,0,0,0,0,0,0,0] ;
    	var pic = ["https://www.amcharts.com/lib/images/faces/A04.png", "https://www.amcharts.com/lib/images/faces/C02.png", "https://www.amcharts.com/lib/images/faces/D02.png", "https://www.amcharts.com/lib/images/faces/E01.png"];    	
    	<c:forEach var="first1" items="${leaveBarcharts}" varStatus="loop">
		<c:forEach var="second1" items="${first1}">
		<c:if test="${second1.leave_type_id.toString() != '1' && second1.leave_type_id.toString() != '5' && second1.leave_type_id.toString() != '3' }">
		sum[${loop.index}] = sum[${loop.index}] + parseInt('${second1.count}');
		 </c:if>
		</c:forEach>	
		</c:forEach>

    	<c:forEach var="first" items="${leaveBarcharts}" varStatus="status">
		dataprovider.push({
		<c:forEach var="second" items="${first}" varStatus="Status1">
		  <c:choose>
		<c:when test="${second.leave_type_id.toString() == '1' || second.leave_type_id.toString() == '5' || second.leave_type_id.toString() == '3' }">
		 '${second.name}'	: '${second.count}'	,
		 </c:when>
		 <c:otherwise>
		
      </c:otherwise>
	 </c:choose>
		</c:forEach>	
		 'อื่น ๆ' : sum[${status.index}] ,
          "country": '${top10users[status.index].user_id}',
          "bullet": pic[${status.index % 4}]
		})
		
		</c:forEach>
		
    
    	
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
    	        "axisAlpha": 0.3,
    	        } ],
    	  "gridAboveGraphs": true,
    	  "startDuration": 2,
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
    
    var initChartSample5 = function() {
   	var month = ["January","February","March","April","May","June","July","August","September","October","November","December"];
   	var dataprovider = [];
   	

		
	<c:forEach var="first" items="${leavesArraylist}" varStatus="status">
	dataprovider.push({
	<c:forEach var="second" items="${first}" >
	 '${second.name}'	: '${second.counts}',
	</c:forEach>	
      "country": month[${status.index}]
     

	})
	
	</c:forEach>
   	
    var chart1 = AmCharts.makeChart("chart_5", {
    	 "type": "serial",
    	  "theme": "light",
    	  "marginRight": 70,
    	  "legend": {
  	        "horizontalGap": 10,
  	        "maxColumns": 1,
  	        "position": "right",
  			"useGraphSettings": true,
  			"markerSize": 10
  	    },
    	  "dataProvider": dataprovider,
    	  "valueAxes": [{
    	    "axisAlpha": 0,
    	    "position": "left",
    	    "title": "Leaves Type of  year"
    	  }],
    	  "startDuration": 1,
    	  "graphs": [{
    	    "balloonText": "${type_1} in [[category]] (${year1}): <b>[[value]]</b> วัน",
    	    "fillColorsField": "color",
    	    "fillAlphas": 0.9,
    	    "lineAlpha": 0.2,
    	    "title": "${type_1}",
    	    "type": "column",
    	    "valueField": "${type_1}"
    	  },{
    	        "balloonText": "${type_5} [[category]] (${year1}): <b>[[value]]</b> วัน ",
    	        "fillAlphas": 0.9,
    	        "lineAlpha": 0.2,
    	        "title": "${type_5}",
    	        "type": "column",
    	        "valueField": "${type_5}"
    	    },{ "balloonText": "${type_3} in [[category]] (${year1}): <b>[[value]]</b> วัน",
	        "fillAlphas": 0.9,
	        "lineAlpha": 0.2,
	        "title": "${type_3}",
	        "type": "column",
	        "valueField": "${type_3}"
	    } ],
    	  "chartCursor": {
    	    "categoryBalloonEnabled": false,
    	    "cursorAlpha": 0,
    	    "zoomable": false
    	  },
    	  "categoryField": "country",
    	  "categoryAxis": {
    	    "gridPosition": "start",
    	    "labelRotation": 45
    	   
    	  },
    	  "export": {
    	    "enabled": true
    	  }
    	} );

    	
    	$('#chart_5').closest('.portlet').find('.fullscreen').click(function() {
        chart1.invalidateSize();
    });
 
    }
    
    return {
        //main function to initiate the module

        init: function() {
        	
            initChartSample1();
            initChartSample2();
            initChartSample3();
            initChartSample4();
            initChartSample5();
          
        }

    };

}();

jQuery(document).ready(function() {    
   ChartsAmcharts.init(); 
});
</script>
<script>
$('.counter').each(function() {
	  var $this = $(this),
	      countTo = $this.attr('data-count');
	  
	  $({ countNum: $this.text()}).animate({
	    countNum: countTo
	  },

	  {

	    duration:1500,
	    easing:'linear',
	    step: function() {
	      $this.text(Math.floor(this.countNum));
	    },
	    complete: function() {
	      $this.text(this.countNum);
	      //alert('finished');
	    }

	  });  
	  
	  

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
