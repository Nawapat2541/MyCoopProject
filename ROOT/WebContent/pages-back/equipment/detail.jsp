<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%-- ${user} --%>
<%-- ${onlineUser.id} --%>
<%-- ${idborrow } --%>
<%-- ${selectchect} --%>
<%-- ${select} --%>
<%--  ${idborrow} --%>
<%-- ${Showdetail}  --%>
<%-- ${now} --%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-folder-open"></i> DETAIL
			</span>
		</div>
	</div>
	<!-- BEGIN FORM-->
	<form action="Equipment" class="form-horizontal" method="post">
		<div class="portlet light bordered">
			<div class="portlet-body">
				<c:forEach var="list" items="${Showdetail}" varStatus="count">
					<c:choose>
						<c:when test="${list.type == 'c' }">
							<c:choose>
								<c:when
									test="${list.borrow_id == idborrow  && list.statusborrow != 'S' }">
									<center>
										<c:if test="${list.image == null}">
											<div class="fileinput-new thumbnail"
												style="width: 200px; height: 150px;">
												<img
													src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
													class="img-responsive" alt="Cinque Terre" width="320px"
													padding="10px" border="5px solidgray" margin="0";>
											</div>
										</c:if>

										<c:if test="${list.image != null}">
											<div class="mt-overlay">
												<img src="${list.image}" class="img-responsive"
													alt="Cinque Terre" width="320px" padding="10px"
													border="5px solidgray" margin="0";>
											</div>
										</c:if>
									</center>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Item
											NO : </label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.item_no}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Serial
											NO : </label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.serial_no}" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Name
											: </label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder="4 GB"
												value="${list.name}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Process
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.process}" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Batterry
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.battery}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">HDD
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.hdd}" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Windows
											: </label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.windows}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Ram
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.ram}" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Location
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.location}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Amount
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="1" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>

									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Date
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control"
												value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"  />"
												disabled>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Status
											:</label>
										<c:if test="${ list.statusborrow == 'U' }">
											<div class="col-md-4">
												<span class="label label-danger"> Unapprove </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.statusborrow == 'R' && list.status == 'B' }">
											<div class="col-md-4">
												<span class="label label-danger"> Return Equipment </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.statusborrow == 'W' && list.status == 'D' }">
											<div class="col-md-4">
												<span class="label label label-primary"> Waiting for
													Approved </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.status == 'B' && list.statusborrow == 'X'  }">
											<div class="col-md-4">
												<span class="label label label-success"> Borrowed </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.status == 'B' && list.statusborrow == 'W'  }">
											<div class="col-md-4">
												<span class="label label label-success"> Borrowed </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
									</div>
								</c:when>
							</c:choose>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:forEach var="list" items="${Showdetail}" varStatus="count">
					<c:choose>
						<c:when test="${list.type == 'o' && list.statusborrow != 'S'  }">
							<c:choose>
								<c:when test="${list.borrow_id == idborrow}">
									<center>
										<c:if test="${list.image == null}">
											<div class="fileinput-new thumbnail"
												style="width: 200px; height: 150px;">
												<img
													src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"
													class="img-responsive" alt="Cinque Terre" width="320px"
													padding="10px" border="5px solidgray" margin="0";>
											</div>
										</c:if>

										<c:if test="${list.image != null}">
											<div class="mt-overlay">
												<img src="${list.image}" class="img-responsive"
													alt="Cinque Terre" width="320px" padding="10px"
													border="5px solidgray" margin="0";>
											</div>
										</c:if>
									</center>
									<div></div>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Item
											NO : </label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.item_no}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Serial
											NO : </label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="ITEM0001" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>

									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Name
											: </label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder="4 GB"
												value="${list.name}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Detail
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.detail}" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Location
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="${list.location}" readonly>
											<div class="form-control-focus"></div>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Amount
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control" placeholder=""
												value="1" readonly>
											<div class="form-control-focus"></div>
										</div>
									</div>
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1">Date
											:</label>
										<div class="col-md-4">
											<input type="text" class="form-control"
												value="<fmt:formatDate type="both" value="${now}" pattern="dd-MM-yyyy"  />"
												disabled>
										</div>
										<label class="col-md-2 control-label" for="form_control_1">Status
											:</label>
										<c:if test="${ list.statusborrow == 'U'}">
											<div class="col-md-4">
												<span class="label label-danger"> Unapprove </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.statusborrow == 'R' && list.status == 'B' }">
											<div class="col-md-4">
												<span class="label label-danger"> Return Equipment </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.status == 'B' && list.statusborrow == 'W'  }">
											<div class="col-md-4">
												<span class="label label label-success"> Borrowed </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.status == 'B' && list.statusborrow == 'X'  }">
											<div class="col-md-4">
												<span class="label label label-success"> Borrowed </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>
										<c:if
											test="${ list.statusborrow == 'W' && list.status == 'D'}">
											<div class="col-md-4">
												<span class="label label label-primary"> Waiting for
													Approved </span>
												<div class="form-control-focus"></div>
											</div>
										</c:if>

									</div>
								</c:when>
							</c:choose>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</form>
	<!-- END FORM-->
</div>



