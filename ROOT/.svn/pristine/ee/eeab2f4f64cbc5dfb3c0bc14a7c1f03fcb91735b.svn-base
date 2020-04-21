<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i class="fa fa-list-alt"></i> My News</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	
<div class="portlet-body flip-scroll" style="text-align: center;">
		<table
			class="table table-bordered table-striped table-condensed flip-content table-hover">
			<thead class="flip-content">
				<tr>
					<th class="numeric"><center>#</center></th>
					<th class="numeric"><center>FileName</center></th>
					<th class="numeric"><center>Title</center></th>
					<th class="numeric"><center>Detail</center></th>
					<th class="numeric"><center>TimeCreate</center></th>
					<th class="numeric"><center>Edit</center></th>
					<th class="numeric"><center>Delete</center></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="news" items="${newsfeed}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td style="text-align: left;word-break: break-all; white-space: normal;"><a href="${news.path}" download="${news.name}">${news.name}${news.type}&nbsp;</a></td>
						<td style="text-align: left;word-break: break-all; white-space: normal;" >${news.news_head}</td>
						<td style="text-align: left;word-break: break-all; white-space: normal;">${news.news_description}</td>
						<td><fmt:formatDate value="${news.time_create}"
							pattern="EEE dd-MMM-yyyy HH:mm" /></td>
						<td>
							<a class="btn btn-circle btn-sm sbold blue" title="Edit"
							href="news_edit?newId=${news.news_id}">
							<i class="fa fa-pencil"></i></a></td>
						<td>
							<a class="btn btn-circle btn-sm sbold red-mint sweet-${news.news_id}" 
							onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);" title="Delete">
							<i class="fa fa-trash"></i></a></td>
					</tr>		
<script>
document.querySelector('.sweet-${news.news_id}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "You will be deleting this id!",
      type: "warning",
      showCancelButton: true,
      confirmButtonClass: 'btn-danger',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "delete_news?news_id=${news.news_id}";
      });
    }; 
</script>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
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
	
<script>
$('#myFile').bind('change', function() {
	  var fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
	  fSize = this.files[0].size; i=0;while(fSize>900){fSize/=1024;i++;}
	  var size_n = (Math.round(fSize*100)/100);
	  var size = size_n+' '+fSExt[i];
	  $('#size').val(size);
	
	});
	
$("#accept").click(
		function() {
			var title = $("#title").val();	
			var detail = $("#detail").val();
			if((title == "") || (detail==""))
				{
					document.getElementById("accept").type="button";
					swal('Please Input Title and Detail');
				}
			else
				{
					document.getElementById("accept").type="submit";
				}
		});
	
function back() {
	document.location = "new_s";
}
	
</script>
	