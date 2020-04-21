<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
 <fmt:formatDate pattern = "yyyy"  value = "${now}" var="yearnow" />
 <style>
 span[title]:hover {
    content: attr(title);
    padding: 4px 8px;
    }

        .dollars:before { content:'$'; }
    
  </style>
 
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-edit font-red"></i> <span
				class="caption-subject font-red sbold uppercase">&nbsp;TRAVEL SUMMARY</span>
		</div>
		
		<div class="actions right ">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>

	</div>
	<form action="SearchTravelSummary" method="post" name="form">

		<div class="portlet-body">
			<div class="form-group form-lg-line-input">
				<label class="col-md-1 control-label">Name :</label>
					<div class="col-sm-3">
						<select class="form-control select2me" name="user_id"  
							style='width: 50%;'>		
							<option value="All" id="All">All</option>							
									<c:forEach var="user" items="${cubeUser}">
									<option value="${user.id}" id="${user.id}"
									<c:if test="${fn:containsIgnoreCase(searchuser , user.id)}"><c:out value="selected=selected"/></c:if>>
									${user.department_id} - ${user.name}</option>
									</c:forEach>
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
                                <div style="text-align:center;vertical-align:middle;">
                                <br>
                                    <span  class="glyphicon glyphicon-tag" style="color:rgb(10, 78, 151);"></span>A = Approved   &nbsp;&nbsp;
                                  <span  class="glyphicon glyphicon-tag" style="color:rgb(204, 153, 0);"></span>  W = Waiting for approve   &nbsp;&nbsp;
                                <span  class="glyphicon glyphicon-tag" style="color:rgb(10, 199, 3);"></span>  P = Paid   &nbsp;&nbsp;
                                 <span  class="glyphicon glyphicon-tag" style="color:rgb(254, 13, 3);"></span> R = Reject  &nbsp;&nbsp;
                                
                                 
                                 
                                    </div>
                                    <div class="portlet-body">
                              
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
                                                	
										
										<c:forEach var="i" begin="1" end="32">
												<tr>
												<th><center = id = "d${i}"></th>
												<c:forEach var="j" begin="1" end="12">
												<td id = "day${i}_${j}"  >
											
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
	$(document).ready(function() {
		$('.select2me').select2();
	});
	var dayindex = [ "1", "2", "3", "4", "5","6","7","8","9","10","11","12","13", "14", "15", "16", "17","18","19","20","21","22","23","24","25","26","27","28"
		,"29","30","31","32"];
	var m = ["1", "2", "3", "4", "5","6","7","8","9","10","11","12"];
	var month = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]; 

	jQuery.each( m, function( i, val ) {
	  $( "#m" + val ).text(month[i]);

	});
	jQuery.each( dayindex, function( i, val ) {
			if(val == "32"){
				$( "#d" + val).text("Sum");
			}else{
		  $( "#d" + val).text(dayindex[i]);
			}
		}); 
	
	
	 
	//travel
	 var travelMonth = [];
	 var travelDay = [];
	 var travelStatus = [];
	 var travelname = [];
	 

	 <c:forEach var="travel" items="${ts}">
  		var d = "${travel.DAY}";
  		var m = "${travel.MONTH}";
  		var y = "${travel.YEAR}";
  		var status = "${travel.status_id}";
  		var name = "${travel.user_id}";
  		travelDay.push(d);
  		travelMonth.push(m);
  		travelStatus.push(status);
  		travelname.push(name);
  		
	</c:forEach>
	
	
	//travel P
	 var travelMonthP = [];
	 var travelSumP = [];

	 <c:forEach var="travelP" items="${tsP}">
 		var m = "${travelP.MONTH}";
 		var status = "${travelP.SUM}";
 		
 		
 		travelMonthP.push(m);
 		travelSumP.push(new Intl.NumberFormat('th-TH',{style: 'currency', currency: 'THB', maxFractionDigits: 3}).format(status));
	</c:forEach>
	
	
	  //travel W
	 var travelMonthW = [];
	 var travelSumW = [];
	 <c:forEach var="travelW" items="${tsW}">
 		var m = "${travelW.MONTH}";
 		var status = "${travelW.SUM}";
 		 
 		
 		travelMonthW.push(m);
 		travelSumW.push(new Intl.NumberFormat('th-TH',{ maxFractionDigits: 3,style: 'currency', currency: 'THB'}).format(status));
	</c:forEach>
	//travel R
	var travelMonthR = [];
	 var travelSumR = [];
	 <c:forEach var="travelR" items="${tsR}">
 		var m = "${travelR.MONTH}";
 		var status = "${travelR.SUM}";
 		travelMonthR.push(m);
 		travelSumR.push(new Intl.NumberFormat('th-TH',{style: 'currency', currency: 'THB', maxFractionDigits: 3}).format(status));
	</c:forEach>
	//travel A
	 var travelMonthA = [];
	 var travelSumA = [];
	 <c:forEach var="travelA" items="${tsA}">
 		var m = "${travelA.MONTH}";
 		var status = "${travelA.SUM}";
 		travelMonthA.push(m);
 		travelSumA.push(new Intl.NumberFormat('th-TH',{style: 'currency', currency: 'THB', maxFractionDigits: 3}).format(status));
	</c:forEach>
	var k = 0;
	var l = 0;
	var mm = 0;
	var mmm = 0;
	var mmmm = 0;
	var m = 0;
	var m = 0;
	var n = 0;
	var w = 0;
	var p = 0;
	var a = 0;
	var r = 0;
	
	for(var i = 1 ; i <= 12 ; i++ ){
		for(var j = 1 ; j<= 32 ; j++){
			
			if(j==travelDay[k] && i==travelMonth[k]){
				if(travelStatus[l] == 'W'){
				//$( "#day"+j+"_"+i).addClass( "success" );
				$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(204, 153, 0);padding-top: 10px';>"+  travelStatus[l] +"</span>");
				
				k++;
				l++;
				}
        	}
			if(j==travelDay[k] && i==travelMonth[k]){
				if(travelStatus[l] == 'P'){
				//$( "#day"+j+"_"+i).addClass( "success" );
				$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(10, 199, 3);padding-top: 10px';>"+  travelStatus[l] +"</span>");
				
				k++;
				l++;
				}
        	}
			if(j==travelDay[k] && i==travelMonth[k]){
				if(travelStatus[l] == 'R'){
				//$( "#day"+j+"_"+i).addClass( "success" );
				$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(254, 13, 3);padding-top: 10px';>"+  travelStatus[l] +"</span>");
				
				k++;
				l++;
				}
        	}
			
			if(j==travelDay[k] && i==travelMonth[k]){
				if(travelStatus[l] == 'A'){
				//$( "#day"+j+"_"+i).addClass( "success" );
				$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(10, 78, 151);padding-top: 10px';>"+  travelStatus[l] +"</span>");
				
				k++;
				l++;
				}
        	}
			
			
			if(j==parseInt(travelDay[k])+1 && i==travelMonth[k]){
				if(travelStatus[l] == 'P'){
				j = j-1;
				k++;
				$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(10, 199, 3);padding-top: 10px';>"+ travelStatus[l] +"</span>");
				
				l++;
				}
				if(travelStatus[l] == 'R'){
					j = j-1;
					k++;
					$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(254, 13, 3);padding-top: 10px';>"+  travelStatus[l] +"</span>");
					
					l++;
					}
				if(travelStatus[l] == 'W'){
					j = j-1;
					k++;
					$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(204, 153, 0);padding-top: 10px';>"+  travelStatus[l] +"</span>");
					
					l++;
					}
				if(travelStatus[l] == 'A'){
					j = j-1;
					k++;
					$( "#day"+j+"_"+i).append("<center><span title='"+ travelname[l] + "'style='color:rgb(10, 78, 151);padding-top: 10px';>"+  travelStatus[l] +"</span>");
					
					l++;
					}
        	}
			if(j==32){
				if(i==travelMonthP[mm]){
					$( "#day"+j+"_"+i).addClass( "text-right" );
					$( "#day"+j+"_"+i).append("<span style='color:rgb(10, 199, 3)'>P=" + travelSumP[p] +"</span><br>");
					mm++;
					p++;
				}}
			
				if(j==32){
				if(i==travelMonthW[m]){
					
					$( "#day"+j+"_"+i).addClass( "text-right" );
					
					$( "#day"+j+"_"+i).append("<span  style='color:rgb(204, 153, 0)'>W=" + travelSumW[w] +"</span><br>");
					m++;
					w++;
				}
				
				if(j==32){
				if(i==travelMonthA[mmm]){
					$( "#day"+j+"_"+i).addClass( "text-right" );
					$( "#day"+j+"_"+i).append("<span style='color:rgb(10, 78, 151)'>A=" + travelSumA[a] +"</span><br>");
					mmm++;
					a++;
				}
				}
				if(j==32){
				if(i==travelMonthR[mmmm]){
					
					$( "#day"+j+"_"+i).addClass( "text-right" );
					$( "#day"+j+"_"+i).append("<span style='color:rgb(254, 13, 3)'>R=" + travelSumR[r] +"</span><br>");
					mmmm++;
					r++;
				}}
				
					
			}
			
			
			
			
	   		
		
			
	}
}
</script>
