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
<fmt:formatDate pattern="yyyy" value="${now}" var="year_now" />
<fmt:formatDate pattern="MM" value="${now}" var="month_now" />
<style>
@media only screen and (max-width: 1200px) {
}

@media only screen and (max-width: 1300px) {
	.paddinglg {
		margin-left: 50px !important;
	}
}

@media only screen and (max-width: 960px) {
	.hiddensm {
		visibility: hidden;
	}
	.paddinglg {
		margin-left: 0px !important;
	}
}

@media only screen and (max-width: 769px) {
	.centerxs {
		display: block;
		margin-left: auto;
		margin-right: auto;
		text-align: center !important;
	}
	.paddinglg {
		margin-left: 0px !important;
	}
}

.item-head {
	width: width:100% !important;
}
</style>
<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="portlet light bordered">

	<div class="portlet-title">
		<div class="caption">
			<i class="icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase"> Travel
				Expense Charts</span>
		</div>
		<div class="actions">
			<a class="btn btn-icon-only btn-default fullscreen"
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
		<!--  search bar in 1 line -->
		<form action="search_travel_exp" method="post">
			<div class="portlet-body">
				<div class="form-group form-lg-line-input">
					<label class="col-md-1 control-label">Staff :</label>
					<div class="col-md-3">
						<select class="form-control select2me" name="name">
							<option value="All" id="All">All</option>
							<optgroup label="Enable">
								<c:forEach var="user" items="${userseq}">
									<c:if test="${user.enable == 1 }">
										<c:if test="${userSelect == nulll }">
											<option value="${user.id}" id="${user.id}"<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}">
											<c:out value="selected=selected"/></c:if>>${user.department_id} - ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}" id="${user.id}"<c:if test="${user.id == userSelect}">
											<c:out value="selected=selected"/></c:if>>${user.department_id} - ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
							<optgroup label="Disable">
								<c:forEach var="user" items="${userseq}">
									<c:if test="${user.enable == 0 }">
										<c:if test="${userSelect == nulll }">
											<option value="${user.id}" id="${user.id}"<c:if test="${user.id == userSelect}">
											<c:out value="selected=selected"/></c:if>>${user.department_id} - ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}" id="${user.id}"<c:if test="${user.id == userSelect}">
											<c:out value="selected=selected"/></c:if>>${user.department_id} - ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
						</select>
					</div>
					<label class="col-md-1 control-label">Type :</label>
					<div class="col-md-2">
						<select class="form-control select2me" name="type"
							style="padding-left: 0px !important; padding-right: 0px;">
							<option value="0" id="All1">All</option>
							<c:forEach var="exptype" items="${exptype}">
								<option value="${exptype.exp_travel_type_id}"id="${exptype.exp_travel_type_id}"
								<c:if test="${type == exptype.exp_travel_type_id }"><c:out value="selected=selected"/></c:if>>${exptype.name}</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-md-1 control-label">Year :</label>
					<div class="col-md-2">
						<select class="form-control select2me" name="year">
							<c:forEach begin="0" end="4" var="i">
								<option value="${yearnow - i}" id="${yearnow - i}"
								<c:if test="${yearnow - i == year }"><c:out value="selected=selected"/></c:if>>${yearnow - i}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group form-lg-line-input">
						<label></label>
						<div class="col-md-2 text-center">
							<button id="" type="submit" class="btn paddinglg sbold blue">
								<i class="fa fa-search"></i> Search
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>

		<br>
		<!--  Dashboard  -->
		<div id="dashboard">
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<!-- All travel exp cost -->
					<div class="col-lg-4">
						<a class="dashboard-stat dashboard-stat-v2 blue-steel">
							<div class="visual">
								<i class="fa fa-check-circle"></i>
							</div>
							<div class="details">
								<div class="number">
									<c:forEach var="total" items="${allAmount}">
										<c:choose>
											<c:when test="${total.total != null}">
												<span id="totalAmount" data-counter="counterup"
													data-value=""><fmt:formatNumber type="number"
														pattern="###,###,###" value="${total.total}" /> </span>
												<span> บาท </span>
												<div class="desc">ค่าเดินทางทั้งหมด</div>
											</c:when>
											<c:otherwise>
												<span id="totalAmount" data-counter="counterup"
													data-value=""><fmt:formatNumber type="number"
														pattern="###,###,###" value="0" /> </span>
												<span> บาท </span>
												<div class="desc">ค่าเดินทางทั้งหมด</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>
						</a>
					</div>
					<!-- paid travel expense  -->
					<div class="col-lg-4">
						<a class="dashboard-stat dashboard-stat-v2 red">
							<div class="visual">
								<i class="fa fa-money"></i>
							</div>
							<div class="details">
								<div class="number">
									<c:forEach var="total" items="${AmountP}">
										<c:choose>
											<c:when test="${total.total != null}">
												<span id="totalAmount" data-counter="counterup"
													data-value=""><fmt:formatNumber type="number"
														pattern="###,###,###" value="${total.total}" /> </span>
												<span> บาท </span>
												<div class="desc">ค่าเดินทางที่จ่ายแล้ว</div>
											</c:when>
											<c:otherwise>
												<span id="totalAmount" data-counter="counterup"
													data-value=""><fmt:formatNumber type="number"
														pattern="###,###,###" value="0" /> </span>
												<span> บาท </span>
												<div class="desc">ค่าเดินทางที่จ่ายแล้ว</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>
						</a>
					</div>
					<!-- wait travel expense -->
					<div class="col-lg-4">
						<a class="dashboard-stat dashboard-stat-v2 purple">
							<div class="visual">
								<i class="fa fa-credit-card"></i>
							</div>
							<div class="details">
								<div class="number">
									<c:forEach var="total" items="${AmountW}">
										<c:choose>
											<c:when test="${total.total != null}">
												<span id="totalAmount" data-counter="counterup"
													data-value=""><fmt:formatNumber type="number"
														pattern="###,###,###" value="${total.total}" /> </span>
												<span> บาท </span>
												<div class="desc">ค่าเดินทางค้างจ่าย</div>
											</c:when>
											<c:otherwise>
												<span id="totalAmount" data-counter="counterup"
													data-value=""><fmt:formatNumber type="number"
														pattern="###,###,###" value="0" /> </span>
												<span> บาท </span>
												<div class="desc">ค่าเดินทางค้างจ่าย</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	</form>

	<!--  Show CHART & Show Text -->
	<c:forEach var="total" items="${allAmount}">
		<c:choose>
			<c:when test="${total.total != null}">
				<div class="portlet light bordered">
					<div class="portlet-title">
						<div class="portlet-body">
							<div id="chart_4" class="chart"
								style="height: 700px; margin-top: 0px;"></div>
						</div>
					</div>
			</c:when>
			<c:otherwise>
				<div class="portlet-title">
					<div class="portlet-body">
						<h3>
							คุณ ${userSelect} ไม่มีข้อมูลการใช้ค่าเดินทาง
							<c:forEach var="exptype" items="${exptype}">
								<c:if test="${type == exptype.exp_travel_type_id }">
									<c:out value=" โดย ${exptype.name}" />
								</c:if>
							</c:forEach>
						</h3>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>


	<script>

var ChartsAmcharts = function() {
    
    var initChartSample4 = function() {
    	var dataprovider = [];
    	var sum = [0,0,0,0,0,0,0,0,0,0] ;
    	var pic = ["https://www.amcharts.com/lib/images/faces/A04.png", "https://www.amcharts.com/lib/images/faces/C02.png", "https://www.amcharts.com/lib/images/faces/D02.png", "https://www.amcharts.com/lib/images/faces/E01.png"];    	
 

    	<c:forEach var="first" items="${expBarcharts}" varStatus="status">
		dataprovider.push({
		<c:forEach var="second" items="${first}" varStatus="Status1">
		  <c:choose>
		<c:when test="${second.exp_travel_type_id.toString() <= 35 }">
		 '${second.field}'	: '${second.total}'	,
		 </c:when>
		 <c:otherwise>
		
      </c:otherwise>
	 </c:choose>
		</c:forEach>	
          "user": '${top10P[status.index].user_id}',
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
    	  "graphs": [ <c:set var="Income" scope="session" value="${expTypeChart}" />
    			<c:forEach var="exptype" items="${expTypeChart}" varStatus="status2">
    			{
    			<c:choose>
    				<c:when test="${status2.index < (fn:length(Income)-1) }">
    	    			  	  			"balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span> บาท ",
    	    			      	        "fillAlphas": 0.8,
    	    			      	        "labelText": "[[value]]",
    	    			      	        "lineAlpha": 0.3,
    	    			      	        "title": "${exptype.name}",
    	    			      	        "type": "column",
    	    			      			"color": "#000000",
    	    			      	        "valueField": "${exptype.field}"
    	    			      	         },
    				</c:when>
 
    				<c:otherwise>
    					"balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span> บาท",
    	    	        "fillAlphas": 0.8,
    	    	        "labelText": "[[value]]",
    	    	        "lineAlpha": 0.3,
    	    	 /*      add picture  
    	    	 		"customBulletField": "bullet",
    	    	        "bulletOffset": 10,
    	    	        "bulletSize": 52, */

    	    	        "title": "${exptype.name}",
    	    	        "type": "column",
    	    			"color": "#000000",
    	    	        "valueField": "${exptype.field}"
    					},
    			</c:otherwise>
    			</c:choose>
    		</c:forEach>
    			  ],
    	  "categoryField": "user",
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
	<!--  End Chart -->


	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="../assets/global/plugins/amcharts/amcharts/amcharts.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/amcharts/amcharts/serial.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/amcharts/amcharts/pie.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/amcharts/amcharts/radar.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/amcharts/amcharts/themes/light.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/amcharts/amcharts/themes/patterns.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/amcharts/amcharts/themes/chalk.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/amcharts/ammap/ammap.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/amcharts/ammap/maps/js/worldLow.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/amcharts/amstockcharts/amstock.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/amcharts/amcharts/xy.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/counterup/jquery.waypoints.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/counterup/jquery.counterup.min.js"
		type="text/javascript"></script>

	
	<!-- END PAGE LEVEL PLUGINS -->
