<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="now" value="<%=new java.util.Date()%>" />
 <fmt:formatDate pattern = "yyyy"  value = "${now}" var="yearnow" />
<style>
span[title]:hover:after {
    content: attr(title);
    padding: 4px 8px;
    color: red;
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
	<form action="searchWorkHourSummary" method="post" name="form">

		<div class="portlet-body">
			<div class="form-group form-lg-line-input">
				<div class="portlet light portlet-fit bordered">
                                
                                    <div class="portlet-body">
                                    <div style="text-align:center;vertical-align:middle;">
                                   วันครบรอบทำงาน  <span  class="glyphicon glyphicon-tag" style="color:rgb(171, 231, 237);"></span>
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
												<c:forEach var="j" begin="1" end="13">
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
var m = ["1", "2", "3", "4", "5","6","7","8","9","10","11","12","13"];
var month = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec",""]; 

jQuery.each( m, function( i, val ) {
  $( "#m" + val ).text(month[i]);

});
jQuery.each( dayindex, function( i, val ) {
	  $( "#d" + val).text(dayindex[i]);

	}); 
	
	var ytdAnniversaryDayMin = [];
	var ytdAnniversaryMonthMin = [];
	var ytdAnniversaryDayMax = [];
	var ytdAnniversaryMonthMax = [];
	var ytdAnniversaryYearMin = [];
	var ytdUser = [];
	var ytdYearMin = [];
	var ytdYearMax = [];
	<c:forEach var="anniversary" items="${whAnniversary}">
	var whdaymin = "${anniversary.DAYMIN}";
	var whdaymax = "${anniversary.DAYMAX}";
	var whmonthmin = "${anniversary.MONTHMIN}";
	var whmonthmax = "${anniversary.MONTHMAX}";
	var whyearmin = "${anniversary.YEARMIN}";
	var whuser = "${anniversary.user_create}";
	var whyearmin = "${anniversary.YEARMIN}";
	var whyearmax = "${anniversary.YEARMAX}";
	ytdAnniversaryDayMin.push(whdaymin);
	ytdAnniversaryMonthMin.push(whmonthmin);
	
	ytdAnniversaryDayMax.push(whdaymax);
	ytdAnniversaryMonthMax.push(whmonthmax);
	
	ytdUser.push(whuser);
	
	ytdYearMin.push(whyearmin);
	ytdYearMax.push(whyearmax);
	</c:forEach>

	var k = 0;
	var l = 0;
	var sumyear = 0;
	var sumyearAnniversary = 0;
	var sumday = 0;
	var summonth = 0;
	for(var i = 1 ; i <= 12 ; i++ ){
		for(var j = 1 ; j<= 31 ; j++){
			
			if(j==ytdAnniversaryDayMin[k] && i==ytdAnniversaryMonthMin[k]){
				sumyear = (parseInt(ytdYearMax[l]) - parseInt(ytdYearMin[l]));
				summonth = (parseInt(ytdAnniversaryMonthMax[l]) - parseInt(ytdAnniversaryMonthMin[l]));
				sumday = (parseInt(ytdAnniversaryDayMax[l]) - parseInt(ytdAnniversaryDayMin[l]));
				if(sumyear >= 1){
					$( "#day"+j+"_"+i).append("<center><span title='"+ sumyear + "Y' style='color:rgb(220,20,60)';>"+ ytdUser[l] +"</span>");
					$( "#day"+j+"_"+i).addClass( "success" );
				}else{
					$( "#day"+j+"_"+i).append("<center><span>"+ ytdUser[l] +"</span>");
					$( "#day"+j+"_"+i).addClass( "success" );
				}
				k++;
				l++;
			}else if(j==parseInt(ytdAnniversaryDayMin[k])+1 && i==ytdAnniversaryMonthMin[k]){
				j = j-1;
				k++;
				sumyear = (parseInt(ytdYearMax[l]) - parseInt(ytdYearMin[l]));
				sumday = (parseInt(ytdAnniversaryDayMax[l]) - parseInt(ytdAnniversaryDayMin[l]));
				summonth = (parseInt(ytdAnniversaryMonthMax[l]) - parseInt(ytdAnniversaryMonthMin[l]));
				if(sumyear >= 1){
					$( "#day"+j+"_"+i).append("<center><span title='"+ sumyear + "Y' style='color:rgb(220,20,60)';>"+ ytdUser[l] +"</span>");
					$( "#day"+j+"_"+i).addClass( "success" );
				}else{
					$( "#day"+j+"_"+i).append("<center><span>"+ ytdUser[l] +"</span>");
					$( "#day"+j+"_"+i).addClass( "success" );
				}
				l++;
			}else{
				$( "#day"+j+"_"+i).append("<center><span>  </span>");
			}
	}
}
	
</script>
