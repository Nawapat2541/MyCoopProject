<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="../assets/apps/scripts/calendar.min.js" type="text/javascript"></script>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i
				class="fa fa-area-chart"></i> DashBoard</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="row">
		<c:forEach var="travel" items="${sumtravel}" varStatus="status">

			<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat dashboard-stat-v2 red">
					<div class="display">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="number">
							<h3 class="font-red-mint"
								style="word-break: break-all; white-space: normal;"">
								<c:if test="${travel.price != null}">
									<span data-counter="counterup" data-value="${travel.price}"
										class="count"
										style="color: white; text-align: end; font-size: 50px;">${travel.price}</span>
								</c:if>

								<c:if test="${travel.price == null}">
									<span data-counter="counterup" data-value="${travel.price}"
										class="count">0</span>
								</c:if>
								<font style="color: white;">฿</font>
							</h3>
							<h4
								style="text-align: end; font-size: 18px; letter-spacing: 10; font-weight: 300; color: white;">ค่าเดินทางค้างจ่ายประจำเดือน</h4>

						</div>
					</div>

							<c:if test="${travel.mybill != null}">
								<span style="width: ${travel.mybill}%;"
									class="progress-bar progress-bar-success red-mint"> <span
									class="sr-only">${travel.mybill} total Bill</span>
								</span>
							</c:if>

							<c:if test="${travel.mybill == null}">
								<span style="text-align: end; font-size: 18px; letter-spacing: 10; font-weight: 300;color: white;"
									class="progress-bar progress-bar-success red-mint"> <span
									class="sr-only">0 total Bill</span>
								</span>
							</c:if>
						
						
							<div class="status-title" style="text-align: end; font-size: 18px; letter-spacing: 10; font-weight: 300;color: white;">total Bill</div>
							<c:if test="${travel.mybill != null}">
								<div class="status-number">${travel.mybill}</div>
							</c:if>

							<c:if test="${travel.mybill == null}">
								<div class="status-number">0</div>
							</c:if>


				</div>
			</div> --%>
			<%-- <c:forEach var="travel" items="${sumtravel}" varStatus="status"> --%>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 red">
					<div class="visual">
						<i class="fa fa-bar-chart-o"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${travel.price != null}">
								<span> <fmt:formatNumber type = "number" 
         minFractionDigits = "2" value = "${travel.price}" /> ฿</span>
							</c:if>
							<c:if test="${travel.price == null}">
								<span data-counter="counterup" data-value="${travel.price}">0฿</span>
							</c:if>
						</div>
						<div class="desc">ค่าเดินทางค้างจ่ายประจำเดือน</div>
					</div>
				</a>
			</div>
		</c:forEach>

		<%-- </c:forEach> --%>
		<c:forEach var="em" items="${Goodem}" varStatus="status">
			<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat dashboard-stat-v2 yellow-saffron">
					<div class="display">
						<div class="visual">
							<i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
						</div>
						<div class="number">
							<h3
								style="word-break: break-all; white-space: normal; color: white; text-align: center; font-size: 35px;">
								<c:if test="${em.user_create != null}">
									<span data-counter="counterup" data-value="${em.user_create}">${em.user_create}</span>
								</c:if>

								<c:if test="${em.user_create == null}">
									<span data-counter="counterup" data-value="${em.user_create}">-</span>
								</c:if>
							</h3>
							<h4
								style="text-align: end; font-size: 18px; letter-spacing: 9; font-weight: 300; color: white;">Diligent
								Em</h4>
							<hr>
							<!-- <small font-size: 18px;>YEAR 2017</small> -->
						</div>
						<!-- <div class="icon">
							<i class="icon-like font-yellow-saffron"></i>
						</div> -->
					</div>
					<div class="progress-info">
						<div class="progress">
							<span style="width: 100%;"
								class="progress-bar progress-bar-success yellow-saffron">
							</span>
						</div>
						<div class="status">
							<div class="status-title">
								YEAR
								<c:if test="${em.time_create != null}">
									<fmt:formatDate value="${em.time_create}" pattern="yyyy" />
								</c:if>

								<c:if test="${em.time_create == null}">
									<fmt:formatDate value="" pattern="yyyy" />
								</c:if>
							</div>
							<div class="status-number"></div>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 yellow-saffron">
					<div class="visual">
						<i class="fa fa-thumbs-o-up"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${em.user_create != null}">
								<span data-counter="counterup" data-value="${em.user_create}">${em.user_create}</span>
							</c:if>
						</div>
						<div class="desc">Diligent Em</div>
					</div>
				</a>
			</div>
		</c:forEach>

		<c:forEach var="borrow" items="${totalborrowNows}" varStatus="status">
			<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat dashboard-stat-v2 green-jungle">
					<div class="display">
						<div class="visual">
							<i class="fa fa-shopping-cart"></i>
						</div>
						<div class="number">
							<h3 class="font-green-jungle"
								style="word-break: break-all; white-space: normal;">
								<c:if test="${borrow.borrowitem != null}">
									<span data-counter="counterup"
										data-value="${borrow.borrowitem}" class="count"
										style="word-break: break-all; white-space: normal; color: white; font-size: 35px; text-align: center;">All
										Item &nbsp;Borrow${borrow.borrowitem}</span>

								</c:if>

								<c:if test="${borrow.borrowitem == null}">
									<span data-counter="counterup"
										data-value="${borrow.borrowitem}" class="count">0</span>
								</c:if>
							</h3>

						</div>
						<!-- <div class="icon">
							<i class="icon-basket font-green-jungle"></i>
						</div> -->
					</div>
					<!-- <div class="progress-info"> -->
					<!-- <div class="progress"> -->
					<c:if test="${borrow.persentage != null}">
						<span style="width: ${borrow.persentage}%;"
							class="progress-bar progress-bar-success green-jungle"> <span
							class="sr-only">${borrow.allitem}</span>
						</span>
					</c:if>

					<c:if test="${borrow.persentage == null}">
						<span style="width: 0%;"
							class="progress-bar progress-bar-success green-jungle"> <span
							class="sr-only">0</span>
						</span>
					</c:if>
					<!-- </div> -->

					<small class="status-title"
						style="word-break: break-all; white-space: normal; color: white; text-align: center;">Notebook
						& Other ${borrow.allitem}ชิ้น</small>
					<c:if test="${borrow.allitem != null}">
								<div class="status-number">${borrow.allitem}ชิ้น</div>
							</c:if>

					<c:if test="${borrow.allitem == null}">
						<small class="status-number"
							style="word-break: break-all; white-space: normal; color: white; text-align: center;">Notebook
							& Other 0 ชิ้น</small>
					</c:if>

					<!-- </div> -->
				</div>
			</div> --%>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 green-jungle">
					<div class="visual">
						<i class="fa fa-shopping-cart"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${borrow.borrowitem != null}">
								<span data-counter="counterup" data-value="${borrow.borrowitem}">${borrow.borrowitem} Items</span>
							</c:if>
							<c:if test="${borrow.borrowitem == null}">
								<span data-counter="counterup" data-value="${borrow.borrowitem}">0 Item</span>
							</c:if>
						</div>
						<c:if test="${borrow.allitem != null}">
							<div class="desc">Notebook & Other ${borrow.allitem}ชิ้น</div>
						</c:if>
						<c:if test="${borrow.allitem == null}">
							<div class="desc">Notebook & Other 0ชิ้น</div>
						</c:if>
					</div>
				</a>
			</div>
		</c:forEach>

		<c:forEach var="Newsnow" items="${News}" varStatus="status">
			<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat dashboard-stat-v2 blue-steel">
					<div class="display">
						<div class="visual">
							<i class="fa fa-newspaper-o"></i>
						</div>
						<div class="number">
							<h3 class="font-blue-steel" class="font-green-jungle"
								style="word-break: break-all; white-space: normal;">
								<c:if test="${Newsnow.user_create != null}">
									<span data-counter="counterup" data-value="3"
										style="color: white;">${Newsnow.user_create}</span>
								</c:if>

								<c:if test="${Newsnow.user_create == null}">
									<span data-counter="counterup" data-value="3">None</span>
								</c:if>
							</h3>
							<small><a href="new_s">NEWS</a></small>
						</div>
						<div class="icon">
							<i class="fa fa-newspaper-o font-blue-steel"></i>
						</div>
					</div>
					<div class="progress-info">
						<div class="progress">
							<span style="width: 100%;"
								class="progress-bar progress-bar-success blue-steel"> <span
								class="sr-only"></span>
							</span>
						</div>
						<div class="status">
							<div class="status-title">
								<c:if test="${Newsnow.time_create != null}">
									<fmt:formatDate value="${Newsnow.time_create}"
										pattern=" dd-MMM-yyyy HH:mm" />
								</c:if>

								<c:if test="${Newsnow.time_create == null}">
									<fmt:formatDate value=" " pattern=" dd-MMM-yyyy HH:mm" />
								</c:if>
							</div>
							<div class="status-number"></div>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 blue-steel">
					<div class="visual">
						<i class="fa fa-newspaper-o"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${Newsnow.user_create != null}">
								<span data-counter="counterup"
									data-value="${Newsnow.user_create}">${Newsnow.user_create}</span>
							</c:if>
							<%-- <c:if test="${Newsnow.user_create == null}">
								<span data-counter="counterup"
									data-value="${Newsnow.user_create}">None</span>
							</c:if> --%>
						</div>
						<div class="desc">News</div>
					</div>
				</a>
			</div>
		</c:forEach>

		<c:forEach var="myteavelNow" items="${mytravel}" varStatus="status">
			<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat2 bordered">
					<div class="display">
						<div class="number">
							<h3 class="font-purple-soft"
								style="word-break: break-all; white-space: normal;">
								<c:if test="${myteavelNow.allprice != null}">
									<span data-counter="counterup"
										data-value="${myteavelNow.allprice}" class="count">${myteavelNow.allprice}</span>
								</c:if>

								<c:if test="${myteavelNow.allprice == null}">
									<span data-counter="counterup"
										data-value="${myteavelNow.allprice}" class="count">0</span>
								</c:if>
								<small class="font-purple-soft">฿</small>
							</h3>
							<small>ค่าเดินทางที่กำลังเบิก</small>
						</div>
						<div class="icon">
							<i class="fa fa-car font-purple-soft"></i>
						</div>
					</div>
					<div class="progress-info">
						<div class="progress">
							<span style="width: 100%;"
								class="progress-bar progress-bar-success purple-soft"> <span
								class="sr-only">3</span>
							</span>
						</div>
						<div class="status">
							<div class="status-title">บิลค่าเดินทางที่กำลังเบิก</div>
							<c:if test="${myteavelNow.mybill != null}">
								<div class="status-number">${myteavelNow.mybill}</div>
							</c:if>

							<c:if test="${myteavelNow.mybill == null}">
								<div class="status-number">0</div>
							</c:if>
						</div>
					</div>
				</div>
			</div> --%>

			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12"
				style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 purple-soft">
					<div class="visual">
						<i class="fa fa-car"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${myteavelNow.mybill != '0'}">
								<span data-counter="counterup"
									data-value="${myteavelNow.mybill}">
									${myteavelNow.mybill} bills</span>
							</c:if>
							<c:if test="${myteavelNow.mybill == '0'}">
								<span data-counter="counterup"
									data-value="${myteavelNow.mybill}">0 bills</span>
							</c:if>
						</div>
						<div class="desc">บิลค่าเดินทางที่กำลังเบิก</div>
					</div>
				</a>
			</div>
		</c:forEach>
		<c:forEach var="money" items="${myobtainMoney}" varStatus="status">
			<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat2 bordered">
					<div class="display">
						<div class="number">
							<h3 class="font-green"
								style="word-break: break-all; white-space: normal;">
								<c:if test="${money.total_amount != null}">
									<span data-counter="counterup"
										data-value="${money.total_amount}" class="count">${money.total_amount}</span>
								</c:if>

								<c:if test="${money.total_amount == null}">
									<span data-counter="counterup"
										data-value="${money.total_amount}" class="count">0</span>
								</c:if>
								<small class="font-green">฿</small>
							</h3>
							<small>ค่าเดินทางที่ได้รับคืนแล้ว</small>
						</div>
						<div class="icon">
							<i class="fa fa-money font-green"></i>
						</div>
					</div>
					<div class="progress-info">
						<div class="progress">
							<c:if test="${money.total_amount != null}">
								<span style="width: 100%;"
									class="progress-bar progress-bar-success green"> <span
									class="sr-only">3</span>
								</span>
							</c:if>


							<c:if test="${money.total_amount == null}">
								<span style="width: 100%;"
									class="progress-bar progress-bar-success green"> <span
									class="sr-only">0</span>
								</span>
							</c:if>
						</div>
						<div class="status">
							<div class="status-title">บิลค่าเดินทางที่จ่ายคืนแล้ว</div>
							<div class="status-number">${money.bill}</div>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12"
				style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 green">
					<div class="visual">
						<i class="fa fa-money"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${money.amount != null}">
									<span> <fmt:formatNumber type = "number" 
         minFractionDigits = "2" value = "${money.amount}" /> ฿</span>

							</c:if>
							<c:if test="${money.amount == null}">
								<span data-counter="counterup"
									data-value="${money.amount}" class="count">0 ฿</span>
							</c:if>
						</div>
						<div class="desc">ค่าเดินทางที่ได้รับคืนแล้ว</div>
					</div>
				</a>
			</div>
		</c:forEach>

		<c:forEach var="myleavesNow" items="${myleaves}" varStatus="status">
			<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="dashboard-stat2 bordered">
					<div class="display">
						<div class="number">
							<c:if test="${myleaves != null}">
								<h3 class="font-yellow-casablanca"
									style="word-break: break-all; white-space: normal;">
									<span data-counter="counterup"
										data-value="${myleavesNow.totally}" class="count">${myleavesNow.totally}</span>
								</h3>
							</c:if>

							<c:if test="${myleaves == null}">
								<h3 class="font-yellow-casablanca"
									style="word-break: break-all; white-space: normal;">
									<span data-counter="counterup" data-value="0" class="count">0</span>
								</h3>
							</c:if>

							<small>วันลาคงเหลือ</small>
						</div>
						<div class="icon">
							<i class="fa fa-paperclip font-yellow-casablanca"></i>
						</div>
					</div>
					<div class="progress-info">
						<div class="progress">
							<c:if test="${myleaves != null}">
								<span style="width: ${myleavesNow.persentage}%;"
									class="progress-bar progress-bar-success yellow-casablanca">
									<span class="sr-only">${myleavesNow.persentage}%</span>
								</span>
							</c:if>

							<c:if test="${myleaves == null}">
								<span style="width: 0%;"
									class="progress-bar progress-bar-success yellow-casablanca">
									<span class="sr-only">0%</span>
								</span>
							</c:if>

						</div>
						<div class="status">
							<div class="status-title">My leaves Remain</div>
							<c:if test="${myleaves != null}">
								<div class="status-number">${myleavesNow.persentage}%</div>
							</c:if>

							<c:if test="${myleaves == null}">
								<div class="status-number">0%</div>
							</c:if>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12"
				style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 yellow-casablanca">
					<div class="visual">
						<i class="fa fa-paperclip"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${myleavesNow.totally != null}">
								<span data-counter="counterup"
									data-value="${myleavesNow.totally}">${myleavesNow.totally}
									Days</span>
							</c:if>
							<c:if test="${myleavesNow.totally == null}">
								<span data-counter="counterup"
									data-value="${myleavesNow.totally}">0 Days</span>
							</c:if>
						</div>
						<div class="desc">วันลาเหลือ</div>
						<%-- <c:if test="${myleavesNow.persentage != null}">
							<div class="desc">My leaves Remain
								${myleavesNow.persentage}%</div>
						</c:if>
						<c:if test="${myleavesmyleavesNow.persentage == null}">
							<div class="desc">My leaves Remain 0%</div>
						</c:if> --%>
					</div>
				</a>
			</div>
		</c:forEach>
		<%-- <a class="dashboard-stat dashboard-stat-v2 purple-soft">
			<div class="visual">
				<i class="fa fa-calendar"></i>
			</div>
			<div class="details">
				<div class="number">
					<c:if test="${x4 == null }">
						<span data-counter="counterup" data-value="">0 Days</span>
					</c:if>
					<c:if test="${x4 != null }">
						<span data-counter="counterup" data-value="">${x4} Day</span>
					</c:if>
				</div>
				<c:if test="${x1  == null}">
								<div class="desc">ลาพักร้อน 0 วัน</div>
							</c:if>
				<c:if test="${x1  != null}">
					<div class="desc">ลาพักร้อน ${x1} วัน</div>
				</c:if>

				<c:if test="${x2  == null}">
								<div class="desc">ลากิจ 0 วัน</div>
							</c:if>
				<c:if test="${x2  != null}">
					<div class="desc">ลากิจ ${x2} วัน</div>
				</c:if>

				<c:if test="${x3  == null}">
								<div class="desc">ลาป่วย 0 วัน</div>
							</c:if>
				<c:if test="${x3  != null}">
					<div class="desc">ลาป่วย ${x3} วัน</div>
				</c:if>

			</div>

		</a> --%>
		
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12"
				style="margin-top: 2%;">
				<a class="dashboard-stat dashboard-stat-v2 yellow-soft">
					<div class="visual">
						<i class="fa fa-calendar"></i>
					</div>
					<div class="details">
						<div class="number">
							<c:if test="${x4 == null }">
								<span data-counter="counterup" data-value="">0 Days</span>
							</c:if>
							<c:if test="${x4 != null }">
								<span data-counter="counterup" data-value="">${x4} Days</span>
								
							</c:if>
							
						</div>
						<%-- <c:if test="${x1  == null}">
						
								<div class="desc">ลาพักร้อน 0 วัน</div>
							</c:if> --%>
						<%-- <c:if test="${x1  != null}">
							<div class="desc">ลาพักร้อน ${x1} วัน</div>
						</c:if> --%>

						<%-- <c:if test="${x2  == null}">
								<div class="desc">ลากิจ 0 วัน</div>
							</c:if> --%>
						<%-- <c:if test="${x2  != null}">
							<div class="desc">ลากิจ ${x2} วัน</div>
						</c:if> --%>

						<%-- <c:if test="${x3  == null}">
								<div class="desc">ลาป่วย 0 วัน</div>
							</c:if> --%>
							<c:if test="${x3  == null}">
							<div class="desc">ลาป่วย  0  วัน</div>
						</c:if>
						<c:if test="${x3  != null}">
							<div class="desc">ลาป่วย ${x3} วัน</div>
						</c:if>
							
					</div>

				</a>
			</div>
		
	</div>
</div>





<script>
	/* $('.count').each(function () {
	 $(this).prop('Counter',0).animate({
	 Counter: $(this).text()
	 }, {
	 duration: 2000,
	 easing: 'swing',
	 step: function (now) {
	 $(this).text(Math.ceil(now));
	 }
	 });
	 }); */
</script>