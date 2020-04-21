<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		<div class="col-lg-12 col-xs-12 col-sm-12">
			<!-- <div class="portlet light bordered"> -->
		<div class="portlet-body">
			<div class="mt-element-list">
				<div class="mt-list-head list-news ext-1 font-white bg-grey-mint">
					<div class="list-head-title-container">
						<h3 class="list-title">News</h3>
					</div>
				</div>

					<div class="mt-list-container list-news ext-1">
						<ul>
						<c:forEach var="news" items="${newsfeed}" varStatus="status">
							<li class="mt-list-item">
							
								<div class="list-icon-container"></div>
								<div class="list-thumb" style="word-break: break-all; white-space: normal;">
									<a href="${news.userprofile}" target="_blank"><img
												class="timeline-body-img pull-left" src="${news.userprofile}"
												alt="" width="25px"></a>
								</div>
								<div class="list-datetime bold font-dark" style="word-break: break-all; white-space: normal;">
									<fmt:formatDate value="${news.time_create}"
										pattern=" dd-MMM-yyyy HH:mm" /> &nbsp; ${news.user_create}
										
								</div>
								<div class="list-item-content">
									<h3 class="uppercase" style="word-break: break-all; white-space: normal;">
										<b>${news.news_head}</b>
									</h3>									
									<p style="word-break: break-all; white-space: normal;">${news.news_description} </p>	
									<c:choose>
										<c:when
											test="${news.type == '.png' || news.type == '.jpg' || news.type == '.gif' || news.type == '.jpeg' }">
											<a href="${news.path}" target="_blank"><img src="${news.path}"  alt=""   style="text-align:center; width:50%; height:10%;"></a>
										</c:when>
										<c:otherwise>
											<a href="${news.path}" download="${news.name}" >${news.name}${news.type}&nbsp;</a>
										</c:otherwise>
									</c:choose>	
									<br/>						
								</div>
								
							</li>
						</c:forEach>
						</ul>
					</div>
				
				</div>		
			<div style="text-align: center;" id ="btndiv">
            <button type="button" class="btn btn-default test" name="mylimit" value="${realLimit}" id="morebtn"
			onclick="update('${realLimit}')"  style="margin-top:5%;${show_hidebtn}">MORE NEWS</button>
			</div>	
		</div>
	</div>
<!-- </div> -->
<script>
	function update(mylimit) {
		var mylimit = mylimit;
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/plus_limit",
			data : {
				"mylimit" : mylimit
			},
		}).done(function(data) {
			console.log(data);
			console.log($('.test').html(data));
		}).fail(function() {

		});
	}

$("#morebtn").click(function() 
		{	
			var totalReal = parseInt("${realLimit}");
			var totalnews = parseInt("${allNews}");
			alert(totalReal > totalnews);
			if (totalReal > totalnews) 
			{
				$(".test").hide();
			} 
			 else 
			{
				$("#morebtn").show();
			}	
	});
</script>
