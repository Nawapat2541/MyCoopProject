<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>




<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />


<script src="../assets/global/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>


<script src="../assets/layouts/global/scripts/quick-sidebar.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/global/scripts/quick-nav.min.js"
	type="text/javascript"></script>
<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="../assets/ajax/jquery-1.10.2.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	function pageload() {
			document.getElementById("hidden").style.display = "none";
			document.getElementById("hidden_d").style.display = "none";
			}
	window.onload = pageload;
</script>	

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-file"></i>&nbsp;Article Feed
			</span>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover table-header-fixed" width="100%" id="table">
				<thead>
					<tr>
						<th class="text-center">No.</th>
						<th class="text-center">Type</th>
						<th class="text-center">Topic</th>
						<th class="text-center">Author</th>
						<th class="text-center">User create</th>
						<th class="text-center">Time create</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Detail</th>
					</tr>
				</thead>
					<tbody>
					<c:forEach var="article" items="${articleList}" varStatus="Count">
                    	<tr>
							<td class="text-center">${Count.count}</td>
							<c:forEach var="arList" items="${articleType}" varStatus="Count">
								<c:if test="${article.article_type_id == arList.article_type_id}">
								<td>${arList.name}</td>
								</c:if>
							</c:forEach>
							<td>${article.topic}</td>
							<td class="text-center">${article.user_id}</td>
							<td class="text-center">${article.user_create}</td>
							<td class="text-center"><fmt:formatDate value="${article.time_create}" pattern="dd-MM-yyyy HH:mm" /></td>
							<td style="text-align:center;"> <a class="btn btn-circle btn-sm sbold blue" title="Edit"
										href="article_edit?articleId=${article.article_id}"
										style="color: white;"><i class="fa fa-pencil"></i></a></td>
							<td style="text-align:center;"> <a class="btn btn-circle btn-sm sbold blue" title="Detail"
										href="article_edit?articleId=${article.article_id}"
										style="color: white;"><i class="fa fa-clipboard"></i></a></td>
						</tr>
					</c:forEach>
					</tbody>
				
			</table>
		</div>
	</div>
	
</div>
