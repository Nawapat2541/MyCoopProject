<!-- BEGIN CORE PLUGINS -->
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>

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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="now" value="<%=new java.util.Date()%>" />

<fmt:formatDate pattern="yyyy" value="${now}" var="year_now" />
<fmt:formatDate pattern="MM" value="${now}" var="month_now" />
<style>
#chartdiv {
	width: 100%;
	height: 800px;
}
@media only screen and (max-width: 990px) {
 .marginsm{
 margin-top: 3px!important;
 }
 .widthsm{
 width: 90%;
 }
}
</style>

<!-- Resources -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/dataviz.js"></script>
<!-- Chart code -->
<script>
	am4core.ready(function() {

		
		
		// Themes begin
		am4core.useTheme(am4themes_dataviz);
		am4core.useTheme(am4themes_animated);
		
		// Themes end
		
		var chart = am4core.create("chartdiv", am4charts.XYChart);

		chart.data = [];
	
		
		
		<c:forEach var="first" items="${NullworkBarcharts}">
		chart.data.push({
				<c:forEach var="sec" items="${first}">
				<c:set var = "tempCon" value = "${1}"/>
				<c:forEach var="third" items="${NotNullworkBarcharts}">
					<c:if test="${sec.user_create == third.user_create}">
					<c:set var = "Hourtemp" value = "${(sec.total+third.total)/60}"/>
						"user_name" : "${sec.user_create}",
						"workhour" : ${Hourtemp} 
						<c:set var = "tempCon" value = "${2}"/>
					</c:if>
				</c:forEach>
				<c:if test="${tempCon==1}">
				<c:set var = "HourtempNull" value = "${sec.total/60}"/>
					"user_name" : "${sec.user_create}",
					"workhour" : ${HourtempNull} 
				</c:if>})
				</c:forEach> //sec
		
		</c:forEach>  //first
		
		var len = chart.data.length;
		
		
		$( document ).ready(function() {
			for(var i=0;i<len;i++){
				for(var j=0;j<len-1;j++){
					if((chart.data[j].workhour)<(chart.data[j+1].workhour)){
						var temp = chart.data[j+1].workhour;
						var tempName = chart.data[j+1].user_name;
						chart.data[j+1].user_name = chart.data[j].user_name;
						chart.data[j+1].workhour = chart.data[j].workhour;
						chart.data[j].workhour = temp;
						chart.data[j].user_name = tempName;
					}
				}
			}
		}); //sort data
		 

		
		
		//create category axis for years
		var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "user_name";
		categoryAxis.renderer.inversed = true;
		categoryAxis.renderer.grid.template.location = 0;

		//create value axis for income and expenses
		var valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
		valueAxis.renderer.opposite = true;
		 
		
		//create columns
		var series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.categoryY = "user_name";
		series.dataFields.valueX = "workhour";
		series.name = "Work hours";
		series.columns.template.fillOpacity = 0.9;
		series.columns.template.strokeOpacity = 0;
		series.tooltipText = "{categoryY} work hours is : {valueX.value} hours";

		//add chart cursor
		chart.cursor = new am4charts.XYCursor();
		chart.cursor.behavior = "zoomY";

		//add legend
		chart.legend = new am4charts.Legend();

	}); // end am4core.ready()
</script>

<div style="margin: 5px;"  class="portlet light bordered">
	<div class="portlet-title">
		<div  class="caption">
			<i class="fa fa-area-chart font-blue"></i> <span 
				class="caption-subject font-red sbold "> Report Work Hours </span>
		</div>
	</div>


	<form action="workhours_chart" method="post">
		<div style="padding: 5px;"  class="row">
			<div align="center"  class="col-md-3 col-lg-3">
				<div class="row">
					<label align="left"  style="margin-top:3px;" class="col-xs-12 col-sm-2 col-md-5 col-lg-4 control-label">Month :</label>
					<div  class="col-xs-12 col-sm-10 col-md-7 col-lg-8 widthsm">
						<select class="form-control" name="monthSelect">
							<option
								<c:if test="${monthReturn == 1 }"><c:out value="selected=selected"/></c:if>
								value="1">Janaury</option>
							<option
								<c:if test="${monthReturn == 2 }"><c:out value="selected=selected"/></c:if>
								value="2">February</option>
							<option
								<c:if test="${monthReturn == 3 }"><c:out value="selected=selected"/></c:if>
								value="3">March</option>
							<option
								<c:if test="${monthReturn == 4 }"><c:out value="selected=selected"/></c:if>
								value="4">April</option>
							<option
								<c:if test="${monthReturn == 5 }"><c:out value="selected=selected"/></c:if>
								value="5">May</option>
							<option
								<c:if test="${monthReturn == 6 }"><c:out value="selected=selected"/></c:if>
								value="6">June</option>
							<option
								<c:if test="${monthReturn == 7 }"><c:out value="selected=selected"/></c:if>
								value="7">July</option>
							<option
								<c:if test="${monthReturn == 8 }"><c:out value="selected=selected"/></c:if>
								value="8">August</option>
							<option
								<c:if test="${monthReturn == 9 }"><c:out value="selected=selected"/></c:if>
								value="9">September</option>
							<option
								<c:if test="${monthReturn == 10 }"><c:out value="selected=selected"/></c:if>
								value="10">October</option>
							<option
								<c:if test="${monthReturn == 11 }"><c:out value="selected=selected"/></c:if>
								value="11">November</option>
							<option
								<c:if test="${monthReturn == 12 }"><c:out value="selected=selected"/></c:if>
								value="12">December</option>


						</select>
					</div>
				</div>
			</div>


			<div class="col-md-3 col-lg-3">
				<div class="row">
					
						<label  style="margin-top:3px;" class="col-xs-12 col-sm-2 col-md-4 col-lg-4 control-label">Year :</label>
					
					<div class="col-xs-12 col-sm-10 col-md-8 col-lg-8 marginsm widthsm">
						<select class="form-control" name="yearSelect">
							<c:forEach var="yearSelect" begin="2009" end="${year_now}">
								<option value="${yearSelect}"
									<c:if test="${yearReturn == yearSelect}"><c:out value="selected=selected"/></c:if>>${yearSelect}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="col-md-3 col-lg-3">
				<div class="row">
					
						<label style="margin-top:3px;" class="col-xs-12 col-sm-2 col-md-6 col-lg-6 control-label">  จำนวนข้อมูล : </label>
					
					<div class="col-xs-12 col-sm-10 col-md-6 col-lg-6 marginsm widthsm">
						<select class="form-control" name="amoutData">
							<option
								<c:if test="${amoutReturn == 5}"><c:out value="selected=selected"/></c:if>
								value="5">5</option>
							<option
								<c:if test="${amoutReturn == 10}"><c:out value="selected=selected"/></c:if>
								value="10">10</option>
							<option
								<c:if test="${amoutReturn == 20}"><c:out value="selected=selected"/></c:if>
								value="20">20</option>
							<option
								<c:if test="${amoutReturn == 40}"><c:out value="selected=selected"/></c:if>
								value="40">40</option>
						</select>
					</div>
				</div>
			</div>
			<div align="center" class="col-md-1 col-lg-3">
				<button type="submit" class="btn paddinglg sbold blue marginsm">
					<i class="fa fa-search"></i> Search
				</button>
			</div>
		</div>
	</form>




<%-- <c:forEach var="next" items="${NotNullworkBarcharts}"> --%>
<%-- 	<c:set var="cal" value="${next.total/60}"/> --%>
<%-- 	${next.total} : ${next.user_create} ปรับเป็น	${cal}	 ชั่วโมง <br> --%>

<%-- </c:forEach><br> --%>

<%-- <c:forEach var="next" items="${NullworkBarcharts}"> --%>
<%-- <c:forEach var="sec" items="${next}"> --%>
<%-- 	<c:set var="cal" value="${sec.total/60}"/> --%>
<%-- 	${sec.total} : ${sec.user_create} ทำงานปกติ  ${cal}	 ชั่วโมง	<br> --%>
<%-- </c:forEach> --%>
<%-- </c:forEach> --%>

	<div id="chartdiv"
		<c:if test="${amountReturn == 5}">style="height: 400px;"</c:if>
		<c:if test="${amountReturn == 10}">style="height: 1000px;"</c:if>
		<c:if test="${amountReturn == 20}">style="height: 1600px;"</c:if>
		<c:if test="${amountReturn == 40}">style="height: 2000px;"</c:if>></div>

</div>
