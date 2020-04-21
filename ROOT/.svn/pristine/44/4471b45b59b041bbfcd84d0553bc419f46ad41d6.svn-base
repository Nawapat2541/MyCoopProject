<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  
   <script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>	

    <div>
      from <input id="date_from" style="text-align:center;">
						to <input id="date_to" style="text-align:center;">
    </div>
   <script>
	$(function(){
		$("#date_from").datepicker({
			numberOfMonths:1,
			dateFormat:'dd,mm,yyyy',
			onSelect:function(selectdate){
				var dt=new Date(selectdate);
				dt.setDate(dt.getDate()+1)
				$("#date_to").datepicker("option","minDate",dt);
			}
		});
		
		$("#date_to").datepicker({
			numberOfMonths:1,
			dateFormat:'dd,mm,yyyy',
			onSelect:function(selectdate){
				var dt=new Date(selectdate);
				dt.setDate(dt.getDate()-1)
				$("#date_from").datepicker("option","maxDate",dt);
			}
		});
		
	)};
</script>
