<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-pie-chart"></i> User Charts
			</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<form action="user-report" method="POST">
		<div class="portlet-body">
			<div class="form-group form-lg-line-input">
				<label class="col-md-1 control-label">Select :</label>
				<div class="col-md-2">
					<select class="form-control select2me" name="active">
						<option
							<c:if test="${ appr == 1 }"><c:out value="selected=selected"/></c:if>
							value="1">Active</option>
						<option
							<c:if test="${ appr == 0 }"><c:out value="selected=selected"/></c:if>
							value="0">Inactive</option>
					</select>
				</div>
				<div class="form-group form-lg-line-input">
					<label></label>
					<div class="col-md-2 text-center">
						<button type="submit" class="btn sbold blue" id="searchbutton"
							onclick="search()">
							<i class="fa fa-search"></i>&nbsp;Search
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>

<!-- Styles -->
<style>
#chartdiv {
  width: 100%;
  height: 300px;
}

</style>

<!-- Resources -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/frozen.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_frozen);
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("chartdiv", am4charts.PieChart);
<c:forEach var="active" items="${userActive}"> 
<c:forEach var="Inactive" items="${userInactive}"> 

// Add data
chart.data = [ {
  "country": "User Active",
  "litres": ${active.total}

},{
  "country": "User Inactive",
  "litres": ${Inactive.total}
} ];
</c:forEach>
</c:forEach>

// Add and configure Series
var pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.dataFields.value = "litres";
pieSeries.dataFields.category = "country";
pieSeries.slices.template.stroke = am4core.color("#fff");
pieSeries.slices.template.strokeWidth = 2;
pieSeries.slices.template.strokeOpacity = 1;

// This creates initial animation
pieSeries.hiddenState.properties.opacity = 1;
pieSeries.hiddenState.properties.endAngle = -90;
pieSeries.hiddenState.properties.startAngle = -90;

}); // end am4core.ready()
</script>

<!-- HTML -->
<div id="chartdiv"></div>

	<div class="portlet-body flip-scroll text-center">
		<table class="table table-bordered table-striped table-condensed flip-content table-hover">
			<thead class="flip-content">
				<tr>
					<th class="numeric text-center">ID</th>
					<th class="numeric text-center">Name</th>
					<th class="numeric text-center">Department</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userA}">
					<tr>
						<td>
							<div class="btn blue-hoki default btn-outline" style="width: 280px">
								<i class="fa fa-user"></i> ${user.id}
							</div>
						</td>
						<td>
							<div class="btn blue default btn-outline" style="width: 280px">
								<i class="fa fa-user"></i> ${user.name}
							</div>
						</td>
						<td>
							<div class="btn red default btn-outline" style="width: 180px">
								<i class="fa fa-suitcase"></i> ${user.department_id}
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>