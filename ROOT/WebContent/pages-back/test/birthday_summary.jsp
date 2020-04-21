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
padding: 4px 4px;
color: DarkKhaki;
}
i { 
    color: DarkKhaki;
    }
    
</style>
 
 
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-edit font-red"></i> <span
				class="caption-subject font-red sbold uppercase">&nbsp;BIRTHDAY SUMMARY</span>
		</div>
		
		<div class="actions right ">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>

	</div>
	<form>

		<div class="portlet-body">
			<div class="form-group form-lg-line-input">
				
				
				
				
				
				 <div style="text-align:center;vertical-align:middle;">
                                   
                                    <h1> Birth Day <span  class='fa fa-birthday-cake' style="color:#00cccc;"></span> </h1>
                                   <c:forEach var="i" begin="1" end="12">
                                   <c:forEach var="j" begin="1" end="31">
                                   <span id = "day1111${j}_${i}"></span>
                                   
                                   </c:forEach>
                                   </c:forEach>
                                    
                                    </div>
				 
				<div class="portlet light portlet-fit bordered">
                                <div class="portlet-body">
                                   
                                    <div class="portlet-body">
                             
                                        <div class="table-scrollable">
                                        <table class="table table-bordered">
                                                <thead>
                                                <tr  class="tableday" name="day">
                                       <th><center>D/M</th>
										
										<c:forEach var="i" begin="1" end="31">
											
        									<th><center = id = "d${i}"></th>
        									
        									
										</c:forEach>
									</tr>
									 </thead>
                                                	
										
										<c:forEach var="i" begin="1" end="12">
												<tr>
												<th><center = id = "m${i}"></th>
												<c:forEach var="j" begin="1" end="31">
												<td id = "day${j}_${i}" >
											
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
		,"29","30","31"];
	var m = ["1", "2", "3", "4", "5","6","7","8","9","10","11","12"];
	var month = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	
	
	jQuery.each( m, function( i, val ) {
	  $( "#m" + val ).text(month[i]);

	});
	jQuery.each( dayindex, function( i, val ) {
		  $( "#d" + val).text(dayindex[i]);

		}); 
	
	 
	/*birthday*/
	 var birthdayMonth = [];
	 var birthdayDay = [];
	 var birthdayname = [];
	 var nowy = new Date().getFullYear();
	 var nowm = new Date().getMonth()+1;
	 var nowd = new Date().getDate();
	 
	 <c:forEach var="birthday" items="${bd}">
  		var d = "${birthday.DAY}";
  		var m = "${birthday.MONTH}";
  		var y = "${birthday.YEAR}";
  		var idd = "${birthday.id}";
  		birthdayDay.push(d);
  		birthdayMonth.push(m);
  		birthdayname.push(idd);
	</c:forEach>

	
	var k = 0;
	var l = 0;
	var m = 0;
	var n = 0;
	
	for(var i = 1 ; i <= 12 ; i++ ){
		for(var j = 1 ; j<= 31 ; j++){
			
			if(j==birthdayDay[k] && i==birthdayMonth[k]){
				$( "#day"+j+"_"+i).addClass( "success" );
				$( "#day"+j+"_"+i).append("<center><span>"+birthdayname[l]+"</span>");
				
				
				k++;
				l++;
        	}
			if(j==parseInt(birthdayDay[k])+1 && i==birthdayMonth[k]){
				j = j-1;
				k++;
				$( "#day"+j+"_"+i).addClass( "success" );
				$( "#day"+j+"_"+i).append("<center><span>"+birthdayname[l]+"</span>");
				
				l++;
        	}
			if(j==birthdayDay[m] && i==birthdayMonth[m]){
			if(parseInt(nowd) == birthdayDay[m]  && parseInt(nowm)==birthdayMonth[m]){
				$( "#day1111"+j+"_"+i).append("<center><h3> Happy BirthDay<strong> "+birthdayname[n]+"</strong></h3> ")
				$( "#day"+j+"_"+i).addClass( "fa fa-gift" );
				$( "#day11111"+j+"_"+i).addClass( "fa fa-gift" );
				
				
			}
			m++;
			n++;
    	}
			
			
			
	}
}
</script>
