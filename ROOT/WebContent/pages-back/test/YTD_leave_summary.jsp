<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate pattern="yyyy" value="${now}" var="yearnow" />

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-edit font-red"></i> <span
				class="caption-subject font-red sbold uppercase">&nbsp;Leaves Summary</span>
		</div>
		<div class="actions right ">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<form action="searchLeaveSummary" method="post" name="form">
		<div class="portlet-body">
		<label class="col-md-1 control-label">Type :</label>
					<div class="col-md-2">
						<select class="form-control select2me" name="type">
									<option value="1"
										<c:if test="${ type == 1 }"><c:out value="selected=selected"/></c:if>>Approved</option>
									<option value="0"
										<c:if test="${ type == 0 }"><c:out value="selected=selected"/></c:if>>Disapproved</option>
							
								</select>
				</div>
			<div class="form-group form-lg-line-input">
				<label class="col-md-1 control-label">Year :</label>
				<div class="col-sm-2">
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
				<br> <br> <br>
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-body">
					 <div style="text-align:center;vertical-align:middle;">
                    <span  class="	fa fa-cube" style="color:rgb(251, 225, 227);"></span>&nbsp จำนวนคนลา 
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <span  class="	fa fa-cube" style="color:rgb(249, 228, 145);"></span>&nbsp วันหยุด
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                     <span  class="	fa fa-cube" style="color:rgb(238, 241, 245);"></span>&nbsp เสาร์-อาทิตย์
                     &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                                    </div>
						<div class="table-scrollable">
							<table class="table table-bordered">
								<thead>
									<tr class="tableday" name="day">
										<th><center>D/M</th>

										<c:forEach var="i" begin="1" end="32">

											<th><center = id="d${i}"></th>

										</c:forEach>
									</tr>
								</thead>
								<c:forEach var="i" begin="1" end="12">
									<tr>
										<th><center = id="m${i}"></th>
										<c:forEach var="j" begin="1" end="32">
											<td id="day${j}_${i}"></td>
										</c:forEach>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<label></label>
			</div>
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

<script>
	var dayindex = [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
			"23", "24", "25", "26", "27", "28", "29", "30", "31", "32" ];
	var m = [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" ];
	var month = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec" ];
	
	jQuery.each(m, function(i, val) {
		$("#m" + val).text(month[i]);
	});
	jQuery.each(dayindex, function(i, val) {
		if (val == "32") {
			$("#d" + val).text("Sum");
		} else {
			$("#d" + val).text(dayindex[i]);
		}
	});
	
	
	/*  leaves */
	var data = ${alldata};
	var d = 0; // index of leaves on day
	var count = 0; // count leaves
	var w = 0; // index of weekend
 	var weekEndDay = ${weekEndDay};
	var weekEndMonth = ${weekEndMonth};
	var m = 0; // index of holiday
	
	/* holidayList */
    var holDay = [];
    var holMonth = [];
    var holDayEnd = [];
    var holMonthEnd = [];
     
    <c:forEach var="hol" items="${holidayList}">
    	var hdstart = "${hol.daystart}";
   		var hmstart = "${hol.monthstart}";
   		var hdEnd = "${hol.dayend}";
   		var hmEnd = "${hol.monthend}";
   		
   		holDay.push(hdstart);
   		holMonth.push(hmstart);
   		holDayEnd.push(hdEnd);
   		holMonthEnd.push(hmEnd);
	</c:forEach>
	
	var list = [];
	var s;
	<c:forEach var="n" items="${allname}">
		var name = "${n}";
		s = name.split(",");
		list.push(s);	
	</c:forEach>
	
	for(var i = 1 ; i <= 12 ; i++ ){
		for(var j = 1 ; j<= 31 ; j++){
			if(data[d]!=0){
				count+=data[d];
			$( "#day"+j+"_"+i).append("<center><span title='"+ list[d] + "' style='color:rgb(220,20,60)';>"+data[d]);
			
			$( "#day"+j+"_"+i).addClass("danger");
			}
			if(j==weekEndDay[w] && i == parseInt(weekEndMonth[w])+1){
				$( "#day"+j+"_"+i).removeClass( "danger" );
	  	   		$("#day"+j+"_"+i).empty();
				$( "#day"+j+"_"+i).addClass("active");
				if(data[d]!=0){
				count-=data[d];
				}
				w++;
			}
			/* Insert Holiday in Table */
	  		if(j==holDay[m] && i==holMonth[m] ){
	  			if(parseInt(holDay[m]) <  parseInt(holDayEnd[m])) {
	  				$( "#day"+j+"_"+i).removeClass( "danger" );
		  	   		$("#day"+j+"_"+i).empty();
	  	  			$( "#day"+j+"_"+i).addClass( "warning" );
	          		holDay[m] = parseInt(holDay[m])+1;
	          		if(data[d]!=0){
	    				count-=data[d];
	    				}
	  			} else { 
	  				$( "#day"+j+"_"+i).addClass( "warning" );
	  				if(data[d]!=0){
	    				count-=data[d];
	    				}
	  				m++;	
	  			} 
	      	}
			if(j==31){
			$( "#day"+32+"_"+i).append("<center>"+count);
			$( "#day"+32+"_"+i).addClass("success");
			}
			d++;
			
		}
		count=0;
	}

</script>
