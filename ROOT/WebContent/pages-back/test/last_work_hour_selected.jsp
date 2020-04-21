<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate pattern="yyyy" value="${now}" var="yearnow" />
<style>
span[title]:hover {
    content: attr(title);
    padding: 4px 8px;
    color: red;
    }
.row{
  display: flex;
  flex-wrap: wrap;
} 
.portlet-title{
text-align: right;
}
.box{
background-color: #696880;

}
.box:hover {
background-color: #3598dc;
}
</style>




<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-edit font-red"></i> <span
				class="caption-subject font-red sbold uppercase">&nbsp;My
				WORK HOUR SUMMARY</span>
		</div>

		<div class="actions right ">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>

	</div>
	<form action="searchworkHoursSelectMonth" method="post" name="form">

		<div class="portlet-body">
			<div class="form-group form-lg-line-input">
				
				<label class="col-md-1 control-label">Type :</label>
				<div class="col-md-4">
					<select class="form-control select2me" name="type">
						<option value="1"
							<c:if test="${ type == 1 }"><c:out value="selected=selected"/></c:if>>Jan</option>
						<option value="2"
							<c:if test="${ type == 2 }"><c:out value="selected=selected"/></c:if>>Feb</option>
							<option value="3"
							<c:if test="${ type == 3 }"><c:out value="selected=selected"/></c:if>>Mar</option>
							<option value="4"
							<c:if test="${ type == 4 }"><c:out value="selected=selected"/></c:if>>Apr</option>
							<option value="5"
							<c:if test="${ type == 5 }"><c:out value="selected=selected"/></c:if>>May</option>
							<option value="6"
							<c:if test="${ type == 6 }"><c:out value="selected=selected"/></c:if>>Jun</option>
							<option value="7"
							<c:if test="${ type == 7 }"><c:out value="selected=selected"/></c:if>>Jul</option>
							<option value="8"
							<c:if test="${ type == 8 }"><c:out value="selected=selected"/></c:if>>Aug</option>
							<option value="9"
							<c:if test="${ type == 9 }"><c:out value="selected=selected"/></c:if>>Sep</option>
							<option value="10"
							<c:if test="${ type == 10 }"><c:out value="selected=selected"/></c:if>>Oct</option>
							<option value="11"
							<c:if test="${ type == 11 }"><c:out value="selected=selected"/></c:if>>Nov</option>
							<option value="12"
							<c:if test="${ type == 12 }"><c:out value="selected=selected"/></c:if>>Dec</option>

					</select>
				</div>

				<label class="col-md-1 control-label">Year :</label>
				<div class="col-sm-4">
					<select class="form-control select2me" name="year"
						data-live-search="true">
						<c:forEach begin="0" end="20" var="i">
							<option value="${yearnow - i}" id="${yearnow - i}"
								<c:if test="${yearnow - i == year }"><c:out value="selected=selected"/> </c:if>>${yearnow - i}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2 text-center">
					<button type="submit" class="btn sbold blue" id="searchbutton"
						onclick="search()">
						<i class="fa fa-search"></i>&nbsp;Search
					</button>
				</div>
				<br>  

			</div>
			<c:forEach var="i" begin="${type}" end="${type}">
				<div class="row">
				<center><h1 id="m${i}"></h1>
					<div class="col-md-12">
						<div class="portlet light portlet-fit bordered">
							<div class="portlet-body">
								<div class="mt-element-card mt-card-round ">
									<div class="row">
										<c:forEach var="j" begin="1" end="31">
											<div class="col-md-3">
											<div class="portlet box green-steel" data-toggle="collapse" data-target="#s${j}">
											<div class="portlet-title " ><span class="badge badge-dark" id="user${j}_${i}"></span>
											<div class="caption">${j}</div>
											<div class="collapse" id="s${j}">
  											<div class="card card-body">
											<div class="portlet-body" style="display: block; height: auto;">
											<div class="style"><span id = "day${j}_${i}"></span></div>											</div>
											</div>
											</div>
											</div>
											</div>
											</div>
											
										</c:forEach>
										
									</div>
									
									</div>
									
									
									
								</div>
							</div>
						</div>
					</div>
			</c:forEach>
			</div>

	</form>
</div>

<script>
	function add() {
		document.location = "testAder";
	}
</script>






<link href="../assets/global/plugins/ladda/ladda-themeless.min.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/ladda/spin.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/ladda/ladda.min.js"
	type="text/javascript"></script>

<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />

<link href="../assets/global/plugins/ladda/ladda-themeless.min.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/pages/scripts/ui-buttons.min.js"
	type="text/javascript"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});

	var dayindex = [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
			"23", "24", "25", "26", "27", "28", "29", "30", "31" ];
	var m = ["1","2","3","4","5","6","7","8","9","10","11","12"];
	var month = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec", "" ];

	jQuery.each(m, function(i, val) {
		$("#m" + val).text(month[i]);

	});
	jQuery.each(dayindex, function(j, val) {
		$("#d" + val).text(dayindex[j]);

	});
	var selectday = [];
	var selectmonth = [];
	var selectyear = [];
	var showuser = [];
	var workTime = [];
	<c:forEach var="selected" items="${whLastWorkHour}">
	var whselectday = "${selected.DAY}";
	var whselectmonth = "${selected.MONTH}";
	var user = "${selected.user_create}";
	var time = "${selected.time}";
	workTime.push(time);
	selectday.push(whselectday);
	selectmonth.push(whselectmonth);
	showuser.push(user);
	</c:forEach>
	var countday = [];
	var countmonth = [];
	var count = [];
	<c:forEach var="count" items="${whLastWorkHour2}">
	var whcount = "${count.count}";
	
	countday.push(whselectday);
	countmonth.push(whselectmonth);
	count.push(whcount);
	</c:forEach>
	
	var k = 0;
	var l = 0;
	var m = 0;
	var sum = 0;
 	for(var i = 1 ; i <= 12 ; i++ ){
		for(var j = 1 ; j<= 32 ; j++){
			if(j == selectday[k] && i == selectmonth[k]){
				$( "#user"+j+"_"+i).append(count[m]);
				m++;
				$( "#day"+j+"_"+i).append("<p style='text-align:left;'>"+ showuser[l] +"<span style='float:right;'>" + workTime[l] + "</span></p>");
				k++;
				l++;
			}else if(j==parseInt(selectday[k])+1 && i == selectmonth[k]){
				j = j-1;
				k++;
				$( "#day"+j+"_"+i).append("<p style='text-align:left;'>"+ showuser[l] +"<span style='float:right;'>" + workTime[l] + "</span></p>");
				l++;
			}else{
				$( "#day"+j+"_"+i).append("<span></span>");
			}			
		}
} 
</script>
