<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
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
				class="caption-subject font-red sbold "> Leaves Charts </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">


		<form class="form-inline margin-bottom-40" action="search_leavecharts" style="margin-left: 20%;" method="post">
			<div class="form-group form-md-line-input " style="display:none">
				<input type="text" id="nameuser" name="nameuser" class="form-control" value="${onlineUser.id}"/>
			</div>
			<perm:permission object="leave.search">
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Staff : </label> 
				<select class="form-control select2me" name="name" id="name">
					<optgroup label="Enable">
						<c:forEach var="user" items="${userseq}">

							<c:if test="${user.enable == 1 }">
								<c:if test="${userSelect == null }">
									<option value="${user.id}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,newname)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
								<c:if test="${userSelect != null }">
									<option value="${userSelect}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,newname)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
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
										<c:if test="${fn:containsIgnoreCase(user.id,newname)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
								<c:if test="${userSelect != nulll }">
									<option value="${userSelect}" id="${user.id}"
										<c:if test="${fn:containsIgnoreCase(user.id,newname)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
										- ${user.name}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</optgroup>
				</select>
			</div>
			<div class="form-group form-md-line-input " style="display:none">
				<input type="text" id="nameuser" name="nameuser" class="form-control" value=""/>
			</div>
			</perm:permission>
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
			<button type="submit" class="btn sbold blue" onclick="check()"> <i class="fa fa-search"></i> Search</button>
		</form>
	</div>
</div>
<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="icon-bar-chart font-blue"></i> <span
				class="caption-subject font-red sbold ">LEAVES of Month</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="chart_5" style=" width: 100%;min-height: 450px;"></div>
</div>
</div>
<script>
	function check(){
		var user = $('select[name=name]').val();
		var x = "${onlineUser.id}";
		if(x!=user && user!=null){
			document.getElementById("nameuser").value = user;
		}
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
            ['วันทำงาน', 
          	  <c:forEach var="typeName" items="${nametypeleave}" varStatus="loop2">
  				'${typeName}'
  				<c:if test="${loop2.index != sizearray }">,</c:if>
  			  </c:forEach>
            ],
            ['จันทร์',
          	  <c:forEach var="total1" items="${countleave1}" varStatus="loop2">
  				${total1}
  			  	<c:if test="${loop2.index != sizearray }">,</c:if>
  		  	  </c:forEach>
            ],
            ['อังคาร', 
          	  <c:forEach var="total1" items="${countleave2}" varStatus="loop2">
  				${total1}
  		  	<c:if test="${loop2.index != sizearray }">,</c:if>
  	  	  </c:forEach>
            ],
            ['พุธ',
          	  <c:forEach var="total1" items="${countleave3}" varStatus="loop2">
  				${total1}
  		  		<c:if test="${loop2.index != sizearray }">,</c:if>
  	  	  </c:forEach>
            ],
            ['พฤหัสบดี',
          	  <c:forEach var="total1" items="${countleave4}" varStatus="loop2">
  				${total1}
  		  		<c:if test="${loop2.index != sizearray }">,</c:if>
  	  	  	</c:forEach>
            ],
            ['ศุกร์',
          	  <c:forEach var="total1" items="${countleave5}" varStatus="loop2">
  				${total1}
  		  	<c:if test="${loop2.index != sizearray }">,</c:if>
  	  	  </c:forEach>
            ]
          ]);

          var view = new google.visualization.DataView(data);

          var options = {
            title: "สถิติการลาประจำเดือน",
            legend: { position: 'rigth', maxLines: 3 },
            bar: { groupWidth: '75%' },
            isStacked: true,
            vAxis: {minValue: 0}
          };
          var chart = new google.visualization.ColumnChart(document.getElementById("chart_5"));
          chart.draw(view, options);
        }
      
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

	
</script>