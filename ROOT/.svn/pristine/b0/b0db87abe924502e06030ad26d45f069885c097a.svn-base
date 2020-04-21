<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
<c:set var="now" value="<%=new java.util.Date()%>" />
 <fmt:formatDate pattern = "yyyy"  value = "${now}" var="yearnow" />

<style >
.active {background-color: #eef1f5!important}
.success{background-color: #abe7ed!important}
.danger{background-color: #fbe1e3!important}
.warning{background-color: #f9e491!important}

</style>



<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-edit font-red"></i> <span
				class="caption-subject font-red sbold uppercase">&nbsp;My
				WORK HOUR SUMMARY</span>
		</div>
		
		<div class="actions right">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>

	</div>
	<form action="searchWorkHourSummary1" method="post" name="form">

		<div class="portlet-body">
			<div class="form-group form-lg-line-input">
				<label class="col-md-1 control-label">Name :</label>
					<div class="col-sm-3">
						<select class="form-control select2me" name="user_id"  
							style='width: 50%;'>									
									<c:forEach var="user" items="${cubeUser}">
									<option value="${user.id}" id="${user.id}"
									<c:if test="${fn:containsIgnoreCase(searchuser , user.id)}"><c:out value="selected=selected"/></c:if>>
									${user.department_id} - ${user.name}</option>
									</c:forEach>
						</select>
				</div>
				<label class="col-md-1 control-label">Type :</label>
					<div class="col-md-2">
						<select class="form-control select2me" name="type">
										<option value="0" id="All1">All</option>
									<option value="1"
										<c:if test="${ type == 1 }"><c:out value="selected=selected"/></c:if>>Worked</option>
									<option value="2"
										<c:if test="${ type == 2 }"><c:out value="selected=selected"/></c:if>>Leave</option>
							
								</select>
				</div>
				
				<label class="col-md-1 control-label">Year :</label>
				<div class="col-sm-2">
					<select class="form-control select2me" name="year" data-live-search="true">
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
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="icon-bubble font-dark"></i>
                                            <span class="caption-subject font-dark bold uppercase">WORK HOUR SUMMARY</span>
                                        </div>
                                        
                                    </div>
                                    <div class="portlet-body">
                                    <div style="text-align:center;vertical-align:middle;">
                                    วันลา  <span  class="glyphicon glyphicon-tag" style="color:rgb(251, 225, 227);"></span>
                                    วันหยุด  <span  class="glyphicon glyphicon-tag" style="color:rgb(249, 228, 145);"></span>
                                    วันมาทำงาน  <span  class="glyphicon glyphicon-tag" style="color:rgb(171, 231, 237);"></span>
                                    เสาร์-อาทิตย์ <span  class="glyphicon glyphicon-tag" style="color:rgb(238, 241, 245);"></span>
                                    </div>
                                        <div class="table-scrollable">
                                        <table class="table table-bordered">
                                                <thead>
                                                <tr  class="tableday" name="day">
                                       <th><center>D/M</th>
										
										<c:forEach var="i" begin="1" end="12">
											
        									<th><center = id = "m${i}"></th>
        									
										</c:forEach>
									</tr>
									 </thead>
                                                	
										
										<c:forEach var="i" begin="1" end="31">
												<tr>
												<th><center = id = "d${i}"></th>
												<c:forEach var="j" begin="1" end="12">
												<td id = "day${i}_${j}">
											
												</td>
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
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script>
$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});
	
var dayindex = [ "1", "2", "3", "4", "5","6","7","8","9","10","11","12","13", "14", "15", "16", "17","18","19","20","21","22","23","24","25","26","27","28"
	,"29","30","31"];
var m = ["1", "2", "3", "4", "5","6","7","8","9","10","11","12"];
var month = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]; 

jQuery.each( m, function( i, val ) {
  $( "#m" + val ).text(month[i]);

});
jQuery.each( dayindex, function( i, val ) {
	  $( "#d" + val).text(dayindex[i]);

	}); 
	
var i = 1;  /* month */
var k = 0;	/* workday.index */
var l = 0;	/* leaveday.index */
var m = 0;	/* holiday.index */
var n = 0;  /* weekend.index */
var o = 0;	/* checkout.indax */ 
var p = 0;	/* checkout.indax */ 
var index1 = 0;
var index2 = 0;
var weekEndDay = ${weekEndDay}
var weekEndMonth = ${weekEndMonth}
var nowy = new Date().getFullYear();
var nowm = new Date().getMonth()+1;
var nowd = new Date().getDate();

/*  leaves */
var startLeaveMonth = [];
var startLeaveDay = [];
var endLeaveMonth = [];
var endLeaveDay = [];
var leavetypename = [];


<c:forEach var="lev" items="${leaves}">
	var lstart = "${lev.start_date}";
		var endleave = "${lev.end_date}"
		var leavetype = "${lev.leave_type_name}" 

	var xxx = lstart.substr(0,10);
	var zzz = endleave.substr(0,10);
	var sstart = xxx.split("-")
	var send = zzz.split("-")

	startLeaveMonth.push(sstart[1]);
	startLeaveDay.push(sstart[2]);
	
	endLeaveMonth.push(send[1]);
	 	endLeaveDay.push(send[2]);
	 	
	 	leavetypename.push(leavetype);	
 </c:forEach>
 
 /*workhour*/
 var workMonth = [];
 var workDay = [];
 
	 <c:forEach var="workhour" items="${wh}">
     var whsp = "${workhour.work_hours_time_work}"
     var xxx = whsp.substr(0,10);
     var whwh = xxx.split("-")
     
     workMonth.push(whwh[1]);
     workDay.push(whwh[2]);
    
	 </c:forEach>
	 
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
 	
 	/* timeckeckin */
		 var dayCheck = [];
		 var monthCheck = [];
		 var timeCheck = [];
		 
		<c:forEach var="checkDayTimeAndMonth" items="${timeckeckin}">
   var checkDay = "${checkDayTimeAndMonth.DAY}"
   var checkMonth = "${checkDayTimeAndMonth.MONTH}"
   var checkTime = "${checkDayTimeAndMonth.TIME}"
   	
   	dayCheck.push(checkDay);
   	monthCheck.push(checkMonth);
   	timeCheck.push(checkTime);
	 </c:forEach>
	 
	/* timeckeckout */
	 var dayCheckout = [];
	 var monthCheckout = [];
	 var timeCheckout = [];
	 var colorclass = [];
	 
	<c:forEach var="checkDayTimeAndMonth" items="${timeckeckout}">
var checkDay = "${checkDayTimeAndMonth.DAY}"
var checkMonth = "${checkDayTimeAndMonth.MONTH}"
var checkTime = "${checkDayTimeAndMonth.TIME}"
	

dayCheckout.push(checkDay);
monthCheckout.push(checkMonth);
timeCheckout.push(checkTime);
</c:forEach>
	 
	 
	 
 for(var i = 1 ; i <= 12 ; i++ ){
		for(var j = 1 ; j<= 31 ; j++){
			
			 index2=index1;
				if(j==weekEndDay[n] && i == parseInt(weekEndMonth[n])+1){
					$( "#day"+j+"_"+i).addClass( "active" );
		    		n++;
		    		index1++;
		    	}
			 

			/* Insert Work in Table */
			if(j==workDay[k] && i==workMonth[k]){
				$( "#day"+j+"_"+i).addClass( "success" );
        		k++;
        		index1++;
        	}
			/* Insert Holiday in Table */
	  		if(j==holDay[m] && i==holMonth[m] ){
	  			if(parseInt(holDay[m]) <  parseInt(holDayEnd[m])) {
	  	  			$( "#day"+j+"_"+i).addClass( "warning" );
	          		holDay[m] = parseInt(holDay[m])+1;
	  			} else { 
	  				$( "#day"+j+"_"+i).addClass( "warning" );
	  				m++;
	  				
	  			} 
	  			index1++;
	      	}
	  		
			
	  		/* Insert Leave in Table */
	   		  if(j==startLeaveDay[l] && i==startLeaveMonth[l]){
	   			if(parseInt(startLeaveDay[l]) < parseInt(endLeaveDay[l])) {
					
	  				if(document.getElementById("day"+j+"_"+i).classList.contains("active")){
	   					l = l;
	  				} else {
	  					
	  					$( "#day"+j+"_"+i).addClass( "danger" );
	  					$( "#day"+j+"_"+i).append("<center><i class='fas fa-file-export' style='font-size: 1.75em ;padding-top: 10px';></i>");
	  	   				$( "#day"+j+"_"+i).prop('title', leavetypename[l]);
	  				}
	  			//show work and tag leave
	  				if(${type} == 1){
	  	  	   			$( "#day"+j+"_"+i).removeClass( "danger" );
	  	  	   			$("#day"+j+"_"+i).empty();
	  	  	   		if(document.getElementById("day"+j+"_"+i).classList.contains("active")==false){
		  	   			$( "#day"+j+"_"+i).append("<center><span  class='glyphicon glyphicon-tag' style='color:rgb(251, 225, 227);padding-top: 10px';></span>");

	  				}

	  				}
	   				startLeaveDay[l] = parseInt(startLeaveDay[l])+1;
	   				
	   			}  else {if(document.getElementById("day"+j+"_"+i).classList.contains("warning")){
						l = l;
					} else {
	   				$( "#day"+j+"_"+i).addClass( "danger" );
	   				$( "#day"+j+"_"+i).append("<center><i class='fas fa-file-export' style='font-size: 1.75em ;padding-top: 10px';></i>");
	   				$( "#day"+j+"_"+i).prop('title', leavetypename[l]);
	   				
					}
	   			//show work and tag leave
	   			if(${type} == 1){
		  	   			$( "#day"+j+"_"+i).removeClass( "danger" );
		  	   			$("#day"+j+"_"+i).empty();
		  	   			$( "#day"+j+"_"+i).append("<center><span  class='glyphicon glyphicon-tag' style='color:rgb(251, 225, 227);padding-top: 10px';></span>");
		  	   	
	   			}
	           		l++;
	   			} 
	   			index1++;
	       	}
  		/* InsertTime in Table */
		if(j==workDay[k-1] && i==workMonth[k-1]){
    		if(parseInt(workDay[k-1])==parseInt(dayCheck[p])){
    			
    			$( "#day"+j+"_"+i).append("<center>"+ timeCheck[p] + "<br>");
    			p++;
    		
    		} else {
    			$( "#day"+j+"_"+i).append("<center>- <br>");
    		}
    		if(parseInt(workDay[k-1])==parseInt(dayCheckout[o])){
    			
    			$( "#day"+j+"_"+i).append("<center>"+ timeCheckout[o] +"</td>");
    			o++;
    		}else{
    			
    			$( "#day"+j+"_"+i).append("<center> - ");
    			if(${type} == 2){
    				$( "#day"+j+"_"+i).removeClass( "success" );
      	  	   		$("#day"+j+"_"+i).empty();
      	   			$( "#day"+j+"_"+i).append("<center><span  class='glyphicon glyphicon-tag' style='color:rgb(171, 231, 237);padding-top: 10px';></span>");

      				}
    		}
    		index1++;
		}
		// null data 
 		 if(index1==index2 ){
 				$("#day"+j+"_"+i).append("<center>"+"N/A");
 				if( j >= parseInt(nowd) && ${year} == parseInt(nowy)){
 					$( "#day"+j+"_"+i).empty();
 				}
 	 		if( i > parseInt(nowm) && ${year} == parseInt(nowy)){
 				$( "#day"+j+"_"+i).empty();
 			}
 		} 
 		
	}
		
}
</script>
