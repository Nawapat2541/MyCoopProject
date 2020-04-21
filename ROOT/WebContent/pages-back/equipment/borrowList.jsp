<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%-- ${onlineUser.id} --%>
<%-- ${searchuser} --%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="glyphicon glyphicon-list-alt font-red"></i> <span
				class="caption-subject font-red sbold uppercase"> Borrow List
			</span> <span class="caption-helper font-red">${role.name}</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<!-- BEGIN FORM-->
		<form action="borrowList_search" class="form-horizontal" method="post"
			autocomplete="off">

			<div class="input-group form-md-line-input col-md-10">
				<div class="row">
					<label class="col-md-1 control-label" for="form_control_1" >Today
						:</label>
					<div class="col-md-4">
						<input type="text" class="form-control" placeholder=""
							value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy H:mm"  />"
							disabled>
					</div>
				</div>
			</div>
			<div class="form-group form-md-line-input col-md-13 ">
				<perm:permission object="borrowlist.searchall">
					<div class="row">
						<label class="col-md-1 control-label" for="form_control_1"
							style="text-align: right;">Name : </label>
						<div class="col-md-4 ">
							<c:choose>
								<c:when test="${searchuser == null}">
											<select class="form-control select2me" name="userborrowId">
							<option value="All" selected=true >All</option>
							<optgroup label="Enable">
								<c:forEach var="user" items="${userseq}">

									<c:if test="${user.enable == 1 }">
										<c:if test="${userSelect == nulll }">
											<option value="${user.id}" id="${user.id}"
											>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}" id="${user.id}"
												>${user.department_id}
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
											>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}" id="${user.id}"
												>${user.department_id}
												- ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
						</select>
								</c:when>
								<c:when test="${searchuser != null}">
							<select class="form-control select2me" name="userborrowId">
							<option value="All">All</option>
							<optgroup label="Enable">
								<c:forEach var="user" items="${userseq}">

									<c:if test="${user.enable == 1 }">
										<c:if test="${userSelect == nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,searchuser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,searchuser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
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
												<c:if test="${fn:containsIgnoreCase(user.id,searchuser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
										<c:if test="${userSelect != nulll }">
											<option value="${user.id}" id="${user.id}"
												<c:if test="${fn:containsIgnoreCase(user.id,searchuser)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
												- ${user.name}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</optgroup>
						</select>
								</c:when>
							</c:choose>
						</div>

						<div class="col-md-1 " style="text-align: right;">
							<button type="submit" id="search" class="btn sbold blue">
								<i class="glyphicon glyphicon-search"></i>&nbsp;Search
							</button>
						</div>
					</div>
				</perm:permission>
			</div>
		</form>
		
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th style="text-align: center;" width="10%">Serial NO.</th>
								<th style="text-align: center;" width="35%">Name</th>
								<th style="text-align: center;" width="15%">Borrow By</th>
								<th style="text-align: center;" width="10%">Status</th>
								<th style="text-align: center;" width="15%">Function</th>
								<th style="text-align: center;" width="15%">Report</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="borrowlist" items="${list}" varStatus="count">
								
									<c:if test="${borrowlist.statusborrow != 'S' && borrowlist.status != 'Z' && borrowlist.status != 'C' && borrowlist.status != 'L' && borrowlist.status != 'F' }">
										<tr>
											<td style="text-align: center;" height="30"><!-- 1. Serial  -->
												<a href="approve_id?borrowid=${borrowlist.borrow_id}">${borrowlist.item_no}</a>
												<c:choose>
													<c:when test="${borrowlist.image == null}">
														<div class="mt-element-overlay">
															<div class="mt-overlay-1">
																<center>
																	<img
																		src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
																		style="text-align: center; width: 100px; height: 100px;">
																</center>
																<div class="mt-overlay">
																	<ul class="mt-info">
																		<li><a class="btn default btn-outline"
																			href="productBorrow?idborrow=${borrowlist.borrow_id}">
																				<i class="icon-magnifier"></i>
																		</a></li>
																	</ul>
																</div>
															</div>
														</div>
													</c:when>
													<c:otherwise> <!-- when not null -->
														<div class="mt-element-overlay">
															<div class="mt-overlay-1">
																<center>
																	<img src="${borrowlist.image}"
																		style="text-align: center; width: 100px; height: 100px;">
																</center>
																<div class="mt-overlay">
																	<ul class="mt-info">
																		<li><a class="btn default btn-outline"
																			href="productBorrow?idborrow=${borrowlist.borrow_id}">
																				<i class="icon-magnifier"></i>
																		</a></li>
																	</ul>
																</div>
															</div>
														</div>
													
													</c:otherwise>
												</c:choose>
											</td>
											
											<td style="text-align: left;"><!-- 3. Equipment Detail  -->
												<c:choose>
													<c:when test="${borrowlist.type == 'c'}">
														${borrowlist.name} - ${borrowlist.ram} - ${borrowlist.process}
													</c:when>
													<c:when test="${borrowlist.type == 'o'}">
														${borrowlist.name}
													</c:when>
												</c:choose>
											</td>
											
											<td style="text-align: center;"><!-- 4. Borrower By  -->
												${borrowlist.user_borrowid}
											</td>
											
											<td style="text-align: center;"><!-- 5. Status  -->
												<c:if test="${borrowlist.statusborrow != null}">
													<c:choose>
														<c:when test="${borrowlist.statusborrow == 'C'}">
															<span class="label label-danger"> Unapprove </span>
														</c:when>
														<c:when test="${borrowlist.statusborrow == 'W'}">
															<span class="label label label-primary"> Waiting for Approved </span>
														</c:when>
														<c:when test="${borrowlist.statusborrow == 'B'}">
															<span class="label label label-success"> Borrowed </span>
														</c:when>
														<c:when test="${borrowlist.statusborrow == 'R'}">
															<span class="label label-danger"> Return Equipment </span>
														</c:when>
														<c:when test="${borrowlist.statusborrow == 'X'}">
															<span class="label label-danger"> Borrowed </span>
														</c:when>
													</c:choose>
													<br/>
													<c:choose>
														<c:when test="  ${borrowlist.type == 'C' || 
																		borrowlist.type == 'W' || 
																		borrowlist.type == 'B' ||
																		borrowlist.type == 'X'}">
															<div style="text-align: center; margin-top: 5%">
																${borrowlist.reasona}
															</div>
														</c:when>
													</c:choose>
												</c:if>
											</td>
											
											<td style="text-align: center"><!--  6.Function -->
												<c:choose>
													<c:when test="${borrowlist.statusborrow == 'C'}">
														<button type="button" class="btn btn-borrow btn-xs red  sweet-${borrowlist.borrow_id}" style=" width: 130px; text-align: left" 
														act-url="unapprove?status_unapprove=${borrowlist.borrow_id}&eqid=${borrowlist.equipment_id}" 
														act-qst="Are you sure?"
														bor-id="${borrowlist.borrow_id}" >
															<i class="fa fa-check-circle"></i>&nbsp;Accept
														</button>
													</c:when>
													
													<c:when test="${borrowlist.statusborrow == 'R'}">
														<button type="button" class="btn btn-borrow btn-xs red  sweet-${borrowlist.borrow_id}" style=" width: 130px; text-align: left" 
														act-url="cancel_return?Cancel_Return=${borrowlist.borrow_id}&eqi_id=${borrowlist.equipment_id}" 
														act-qst="Are you sure?"
														bor-id="${borrowlist.borrow_id}">
															<i class="fa fa-times-circle"></i>&nbsp;Cancel
														</button>
													</c:when>
													
													<c:when test="${borrowlist.status == 'D' && borrowlist.statusborrow != 'C'}">
														<button type="button" class="btn btn-borrow btn-xs red  sweet-${borrowlist.borrow_id}" style=" width: 130px; text-align: left"
														act-url="borrowList_cancle?cancle=${borrowlist.borrow_id}&online=${onlineUser.id}&eqid=${borrowlist.equipment_id}" 
														act-qst="Do you want to cancel?"
														bor-id="${borrowlist.borrow_id}">
															<i class="fa fa-times-circle"></i>&nbsp;Cancel
														</button>
													</c:when>
													
													<c:when test="${borrowlist.statusborrow == 'X'}">
														<button type="button" class="btn btn-borrow btn-xs green mt-ladda-btn 
															ladda-button sweet-${borrowlist.borrow_id}" style=" width: 130px; text-align: left" 
														act-url="borrow_return?returnborrow=${borrowlist.borrow_id}&online=${onlineUser.id}&eqi_id=${borrowlist.equipment_id}" 
														act-qst="Are you sure?"
														bor-id="${borrowlist.borrow_id}">
															<i class="fa fa-arrow-circle-up"></i>&nbsp;Return
														</button>
													</c:when>
													
													<c:when test="${borrowlist.status == 'B' && 
																	borrowlist.statusborrow != 'R' && 
																	borrowlist.statusborrow != 'X' && 
																	borrowlist.statusborrow != 'C'}">
														<button type="button" class="btn btn-borrow btn-xs green mt-ladda-btn 
															ladda-button sweet-${borrowlist.borrow_id}"  style=" width: 130px; text-align: left"
														act-url="borrow_return?returnborrow=${borrowlist.borrow_id}&online=${onlineUser.id}&eqi_id=${borrowlist.equipment_id}" 
														act-qst="Are you sure?"
														bor-id="${borrowlist.borrow_id}">
															<i class="fa fa-arrow-circle-up"></i>&nbsp;Return
														</button>
													</c:when>
													
												</c:choose>
											</td>
											<td>
												<button type="button" class="btn btn-xs blue" style="width: 130px; text-align: left"
														onclick="viewBorrowReport(${borrowlist.borrow_id});">
													<i class="fa fa-file-pdf-o"></i>&nbsp;Borrow Print
												</button>
												<br/>
												<button type="button" class="btn btn-xs blue" style="margin-top: 5px; width: 130px; text-align: left"
														onclick="viewBorrowSendBackReport(${borrowlist.borrow_id});">
													<i class="fa fa-file-code-o"></i>&nbsp;Sendback Print
												</button>
											</td>
											<%-- 
											
											<c:if test="${borrowlist.type == 'c'}">
												<td style="text-align: left;">${borrowlist.name}-
													${borrowlist.ram} - ${borrowlist.process}</td>
											</c:if>
											<c:if test="${borrowlist.type == 'o'}">
												<td style="text-align: left;">${borrowlist.name}</td>
											</c:if>
											<c:if test="${borrowlist.statusborrow == 'C'}">
												<td style="text-align: center;"><span
													class="label label-danger"><i class=""></i>
														Unapprove </span>
													<div style="text-align: center; margin-top: 5%">
														${borrowlist.reasona}</div></td>
											</c:if>
												<td style="text-align: center;">
													 ${borrowlist.user_borrowid}</td>
											<c:if
												test="${borrowlist.statusborrow == 'W'}">
												<td style="text-align: center;"><span
													class="label label label-primary"> Waiting for
														Approved </span></td>
											</c:if>
											<c:if
												test="${borrowlist.statusborrow == 'B'  }">

												<td style="text-align: center;"><span
													class="label label label-success"> Borrowed </span>
													<div style="text-align: center; margin-top: 5%">
														${borrowlist.reasona}</div></td>
											</c:if>
											<c:if test="${borrowlist.statusborrow == 'R'}">
												<td style="text-align: center;"><span
													class="label label-danger"> Return Equipment </span></td>
											</c:if>
											<c:if test="${borrowlist.statusborrow == 'X'}">
												<td style="text-align: center;"><span
													class="label label label-success"> Borrowed </span>
													<div style="text-align: center; margin-top: 5%">
														${borrowlist.reasona}</div></td>
											</c:if>
											<c:choose>
												<c:when test="${borrowlist.statusborrow == 'C'}">
													<td style="text-align: center">
														<button type="button"
															class="btn btn-xs red  sweet-${borrowlist.borrow_id}"
															onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);">
															<i class="fa fa-check-circle"></i>&nbsp;Accept
														</button>
													</td>
													<script>
document.querySelector('.sweet-${borrowlist.borrow_id}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
       document.location = "unapprove?status_unapprove=${borrowlist.borrow_id}&eqid=${borrowlist.equipment_id}";
      });
}; 
</script>
												</c:when>
												<c:when
													test="${borrowlist.status == 'D' && borrowlist.statusborrow != 'C'}">
													<td style="text-align: center">
														<button type="button"
															class="btn btn-xs red  sweet-${borrowlist.borrow_id}"
															onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);">
															<i class="fa fa-times-circle"></i>&nbsp;Cancel
														</button>
													</td>


													<script>
document.querySelector('.sweet-${borrowlist.borrow_id}').onclick = function(){
    swal({
      title: "do you want to cancel ?",
      text: "",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "borrowList_cancle?cancle=${borrowlist.borrow_id}&online=${onlineUser.id}&eqid=${borrowlist.equipment_id}";
      });
    }; 
</script>
												</c:when>
												<c:when
													test="${borrowlist.status == 'B' && borrowlist.statusborrow != 'R' && borrowlist.statusborrow != 'X' && borrowlist.statusborrow != 'C'}">
													<td style="text-align: center">
														<button type="button"
															class="btn btn-xs green mt-ladda-btn ladda-button  sweet-${borrowlist.borrow_id}"
															onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);">
															<i class="fa fa-arrow-circle-up"></i>&nbsp;Return
														</button>
													</td>
													<script>
document.querySelector('.sweet-${borrowlist.borrow_id}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "borrow_return?returnborrow=${borrowlist.borrow_id}&online=${onlineUser.id}&eqi_id=${borrowlist.equipment_id}";
      });
    }; 
</script>
												</c:when>
												<c:when test="${borrowlist.statusborrow == 'R'}">
													<td style="text-align: center">
														<button type="button"
															class="btn btn-xs red  sweet-${borrowlist.borrow_id}"
															onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);">
															<i class="fa fa-times-circle"></i>&nbsp;Cancel
														</button>
													</td>
													<script>
document.querySelector('.sweet-${borrowlist.borrow_id}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "cancel_return?Cancel_Return=${borrowlist.borrow_id}&eqi_id=${borrowlist.equipment_id}";
      });
    }; 
</script>
												</c:when>
												<c:when test="${borrowlist.statusborrow == 'X'}">
													<td style="text-align: center">
														<button type="button"
															class="btn btn-xs green mt-ladda-btn ladda-button  sweet-${borrowlist.borrow_id}"
															onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Primary']);">
															<i class="fa fa-arrow-circle-up"></i>&nbsp;Return
														</button>
													</td>
													<script>
document.querySelector('.sweet-${borrowlist.borrow_id}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "borrow_return?returnborrow=${borrowlist.borrow_id}&online=${onlineUser.id}&eqi_id=${borrowlist.equipment_id}";
      });
    }; 
</script>
												</c:when>
											</c:choose> --%>
										</tr>
									</c:if>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		
		<!-- </form> -->
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
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
	$(document).ready(function () {
	    $('.select2me').select2();
	    
	    $('.btn.btn-borrow').on('click',function(){
	    	var url = $(this).attr('act-url');
	    	var qst = $(this).attr('act-qst')
	    	swal({
	  	      title: qst,
	  	      text: "",
	  	      type: "info",
	  	      showCancelButton: true,
	  	      confirmButtonClass: 'btn-primary',
	  	      confirmButtonText: 'OK'
	  	    }, function (inputValue) {
	  	        if (inputValue === false) return false;
	  	        if (inputValue === "") {
	  	          return false
	  	        }
	  	        document.location =  url;
  	        });
	    });
	    
	});
	

	function viewBorrowReport(borrowId){
		window.open("${pageContext.request.contextPath}/borrowReport?borrowId="+borrowId, "", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");
	}

	function viewBorrowSendBackReport(borrowId){
		window.open("${pageContext.request.contextPath}/borrowSendBackReport?borrowId="+borrowId, "", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");
	}
	
	
</script>