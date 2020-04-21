<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="favicon.ico" />
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/fancybox/lib/jquery-1.10.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js"></script>

<fmt:formatDate pattern="yyyy" value="${dateTimeNow}" var="year_now" />
<fmt:formatDate pattern="MM" value="${dateTimeNow}" var="month_now" />
<fmt:formatDate pattern="dd" value="${dateTimeNow}" var="day_now" />
<fmt:formatDate pattern="dd-MM-yyyy" value="${dateTimeNow}"
	var="dateNow" />
<fmt:formatDate pattern="HH:mm" value="${dateTimeNow}" var="timeNow" />

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-edit"></i> <span
				class="caption-subject font-red sbold "> Timesheet </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body" style="text-align: center;">


		<form class="form-inline margin-bottom-40" action="searchTimesheet"
			method="POST">
			<!-- --------------------------------------------------------------Start search------------------------------------------------------ -->
			<input type="hidden" id="tempuser" name="tempuser"
				class="form-control" />

			<perm:permission object="timesheet.edit">
				<div class="form-group form-md-line-input ">
					<label class="control-label" for="form_control_1">Staff : </label>
					<select class="form-control select2me" name="name" id=name>
						<optgroup label="Enable">
							<c:forEach var="user" items="${userseq}">

								<c:if test="${user.enable == 1 }">
									<c:if test="${userSelect == null }">
										<option value="${user.id}" id="${user.id}"
											<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
											- ${user.name}</option>
									</c:if>
									<c:if test="${userSelect != null }">
										<option value="${userSelect}" id="${user.id}"
											<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
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
											<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
											- ${user.name}</option>
									</c:if>
									<c:if test="${userSelect != nulll }">
										<option value="${userSelect}" id="${user.id}"
											<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/></c:if>>${user.department_id}
											- ${user.name}</option>
									</c:if>
								</c:if>
							</c:forEach>
						</optgroup>
					</select>
				</div>
			</perm:permission>
			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Month : </label> <select
					class="form-control select2me" name="monthSearch" id=monthSearch>
					<option value='01' id='01'
						<c:if test="${month_now == 01 }"><c:out value="selected=selected"/> </c:if>>January</option>
					<option value='02' id='02'
						<c:if test="${month_now == 02 }"><c:out value="selected=selected"/> </c:if>>February</option>
					<option value='03' id='03'
						<c:if test="${month_now == 03 }"><c:out value="selected=selected"/> </c:if>>March</option>
					<option value='04' id='04'
						<c:if test="${month_now == 04 }"><c:out value="selected=selected"/> </c:if>>April</option>
					<option value='05' id='05'
						<c:if test="${month_now == 05 }"><c:out value="selected=selected"/> </c:if>>May</option>
					<option value='06' id='06'
						<c:if test="${month_now == 06 }"><c:out value="selected=selected"/> </c:if>>June</option>
					<option value='07' id='07'
						<c:if test="${month_now == 07 }"><c:out value="selected=selected"/> </c:if>>July</option>
					<option value='08' id='08'
						<c:if test="${month_now == 08 }"><c:out value="selected=selected"/> </c:if>>August</option>
					<option value='09' id='09'
						<c:if test="${month_now == 09 }"><c:out value="selected=selected"/> </c:if>>September</option>
					<option value='10' id='10'
						<c:if test="${month_now == 10 }"><c:out value="selected=selected"/> </c:if>>October</option>
					<option value='11' id='11'
						<c:if test="${month_now == 11 }"><c:out value="selected=selected"/> </c:if>>November</option>
					<option value='12' id='12'
						<c:if test="${month_now == 12 }"><c:out value="selected=selected"/> </c:if>>December</option>
				</select>
			</div>

			<div class="form-group form-md-line-input ">
				<label class="control-label" for="form_control_1">Year : </label> <select
					class="form-control select2me" name="yearSearch" id=yearSearch>
					<c:forEach begin="0" end="4" var="i">
						<option value="${year_now - i}" id="${year_now - i}">${year_now - i}</option>
					</c:forEach>
				</select>
			</div>

			<button type="submit" class="btn sbold blue" onclick="ddd()">
				<i class="fa fa-search"></i> Search
			</button>
			<script>
				function ddd(){
					 var x = $('select[name=name]').val();
					 var y = "${onlineUser.id}";
					 if(x==y && x!=null){
						 document.getElementById("tempuser").value=y;
					 }else if(x=="" || x==null){
						 document.getElementById("tempuser").value=y;
					 }else{
						 document.getElementById("tempuser").value=x;
					 }
					
				}
			</script>
		</form>
		<!-- --------------------------------------------------------------End search------------------------------------------------------ -->

		<!-- -----------------------------------------start add-------------------------------------------- -->
		<form action="addTimesheet" method="POST" id=timesheet>
			<div class="portlet-body" style="text-align: center;">
				<table class="table table-lover table-condensed flip-content"
					name=timesheetTable>
					<thead class="flip-content">
						<tr>
							<th><center>DATE</center></th>
							<th><center>Description</center></th>
							<th><center>Check-in</center></th>
							<th><center>Check-out</center></th>
							<th><center>Status</center></th>
							<th><center>Save</center></th>
							<th><center>Reset</center></th>

						</tr>
					</thead>
					<!-- 	-----------------------------------------Data--------------------------------------- -->
					<!-- --------------------Edit------------------------- -->
					<c:forEach items="${arrayDay}" var="date" varStatus="status">
					<c:set var = "timenoww" scope = "session" value ="${fullDateKub[status.index].getTime()}"/>
						<c:set var = "checkday" scope = "session" value ="${0}"/>
							  <c:forEach var="setHoli" items="${setHoli}">
							 	
							  <c:set var = "timeholiday" scope = "session" value ="${setHoli.start_date.getTime()}" />
							   <c:set var = "timeholiday2" scope = "session" value ="${setHoli.end_date.getTime()}" />
							<c:choose>
							<c:when test="${(timeholiday == timenoww or timeholiday2 == timenoww) and fullDateKub[status.index].toString().substring(0,3) != 'Sat' and fullDateKub[status.index].toString().substring(0,3) != 'Sun' }">
							 <c:set var = "checkday" scope = "session" value ="${1}"/>
							<tr class="bg-red bg-font-red">

									<td style="width: 25%;"><fmt:formatDate
											value="${fullDateKub[status.index]}" pattern="E dd-MMM-yyyy"></fmt:formatDate>
									</td>

									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<c:if test="${month_now<monthSearch}">


										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}" disabled
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													class="form-control " name="dateTimeIn" id="timeIn${date}"
													disabled></td>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control  " disabled></td>

											</c:when>

										</c:choose>

									</c:if>
									<c:if test="${month_now>monthSearch}">
										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}"
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="font-white form-control timepicker timepicker-24"
													value="0.00" onclick="showTimeIn(${date});"></td>


											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeIn[status.index]}" onclick="timechenge()"
													onkeypress='return false' style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}" value="0.00"
													class="font-white form-control timepicker timepicker-24 "
													onclick="showTimeOut(${date});" onkeypress='return false'></td>

											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeOut[status.index]}"
													onclick="timechenge()" onkeypress='return false'
													style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>

									</c:if>
									<c:if test="${month_now==monthSearch}">
										<c:if test="${day_now<arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}" disabled
												value="${arrayDescription[status.index]}"
												class="form-control "></td>
											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														class="form-control " name="dateTimeIn" id="timeIn${date}"
														disabled></td>
												</c:when>


											</c:choose>

											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" disabled
														class="form-control "></td>

												</c:when>

											</c:choose>

										</c:if>
										<c:if test="${day_now>=arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}"
												value="${arrayDescription[status.index]}"
												class="form-control "></td>

											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="font-white form-control timepicker timepicker-24"
														value="0.00" onfocus="showTimeIn(${date});"></td>


												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeIn[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" value="0.00"
														class="font-white form-control timepicker timepicker-24 "
														onclick="showTimeOut(${date});" onkeypress='return false'></td>

												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeOut[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>

										</c:if>
									</c:if>
									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<td><c:if
											test="${arrayStatus[status.index].toString() == 'W' }">
											<span class="label label-sm label-warning">Waiting for
												approve</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'A'}">
											<span class="label label-sm label-success">Approved</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'R'}">
											<span class="label label-sm label-danger">Reject</span>
										</c:if></td>
									<td><a href="javascript:;"
										class="btn bg-green bg-font-green"
										onclick="ajaxCall(${date},${monthList},${yearList});"> <i
											class="fa fa-file-o"></i> SAVE
									</a></td>
									<td><a href="javascript:;"
										class="btn bg-yellow-saffron bg-font-yellow-saffron"
										onclick="myFunctionReset(${date});"> <i class="fa fa-undo">
										</i> RESET
									</a></td>

									<!-- ///////////////////hidden////////////////////// -->
									<td><input type="hidden" name="" id=""
										value="${monthList}"></td>
									<td><input type="hidden" name="discription"
										id="discriptionHidden${date}"
										value="${arrayDescription[status.index]}"></td>
									<td style="width: 40%;"><input type="hidden" name="id"
										id="id${date}" value="${arrayId[status.index]}"></td>

									<c:choose>
										<c:when test="${arrayTimeIn[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 " value="9.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeIn[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${arrayTimeOut[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 " value="18.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeOut[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>

									<td><input type="hidden" name="time_now" id="time${date}"
										class="form-control input-lg timepicker timepicker-24 test"
										value="${timeNow}" onclick="timechenge()"
										onkeypress='return false'></td>
									<td><input type="hidden" name="date_now" id="date${date}"
										value="${dateNow}" onchange="datechenge()"
										class="form-control input-lg form-control-inline input-medium date-picker test"
										size="9" type="hidden" onkeypress='return false'></td>


									<!-- ///////////////////////////////////////// -->
								</tr>
							
							
							</c:when>
							
						
							
						<c:when test="${fullDateKub[status.index].getTime() > setHoli.start_date.getTime() and fullDateKub[status.index].getTime() < setHoli.end_date.getTime() 
							and (fullDateKub[status.index].toString().substring(0,3) != 'Sat' and fullDateKub[status.index].toString().substring(0,3) != 'Sun')
							and timeholiday != timenoww and timeholiday2 != timenoww}">
							 <c:set var = "checkday" scope = "session" value ="${1}"/>
							<tr class="bg-red bg-font-red">

									<td style="width: 25%;"><fmt:formatDate
											value="${fullDateKub[status.index]}" pattern="E dd-MMM-yyyy"></fmt:formatDate>
									</td>

									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<c:if test="${month_now<monthSearch}">


										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}" disabled
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													class="form-control " name="dateTimeIn" id="timeIn${date}"
													disabled></td>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control  " disabled></td>

											</c:when>

										</c:choose>

									</c:if>
									<c:if test="${month_now>monthSearch}">
										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}"
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="font-white form-control timepicker timepicker-24"
													value="0.00" onclick="showTimeIn(${date});"></td>


											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeIn[status.index]}" onclick="timechenge()"
													onkeypress='return false' style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}" value="0.00"
													class="font-white form-control timepicker timepicker-24 "
													onclick="showTimeOut(${date});" onkeypress='return false'></td>

											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeOut[status.index]}"
													onclick="timechenge()" onkeypress='return false'
													style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>

									</c:if>
									<c:if test="${month_now==monthSearch}">
										<c:if test="${day_now<arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}" disabled
												value="${arrayDescription[status.index]}"
												class="form-control "></td>
											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														class="form-control " name="dateTimeIn" id="timeIn${date}"
														disabled></td>
												</c:when>


											</c:choose>

											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" disabled
														class="form-control "></td>

												</c:when>

											</c:choose>

										</c:if>
										<c:if test="${day_now>=arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}"
												value="${arrayDescription[status.index]}"
												class="form-control "></td>

											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="font-white form-control timepicker timepicker-24"
														value="0.00" onfocus="showTimeIn(${date});"></td>


												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeIn[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" value="0.00"
														class="font-white form-control timepicker timepicker-24 "
														onclick="showTimeOut(${date});" onkeypress='return false'></td>

												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeOut[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>

										</c:if>
									</c:if>
									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<td><c:if
											test="${arrayStatus[status.index].toString() == 'W' }">
											<span class="label label-sm label-warning">Waiting for
												approve</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'A'}">
											<span class="label label-sm label-success">Approved</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'R'}">
											<span class="label label-sm label-danger">Reject</span>
										</c:if></td>
									<td><a href="javascript:;"
										class="btn bg-green bg-font-green"
										onclick="ajaxCall(${date},${monthList},${yearList});"> <i
											class="fa fa-file-o"></i> SAVE
									</a></td>
									<td><a href="javascript:;"
										class="btn bg-yellow-saffron bg-font-yellow-saffron"
										onclick="myFunctionReset(${date});"> <i class="fa fa-undo">
										</i> RESET
									</a></td>

									<!-- ///////////////////hidden////////////////////// -->
									<td><input type="hidden" name="" id=""
										value="${monthList}"></td>
									<td><input type="hidden" name="discription"
										id="discriptionHidden${date}"
										value="${arrayDescription[status.index]}"></td>
									<td style="width: 40%;"><input type="hidden" name="id"
										id="id${date}" value="${arrayId[status.index]}"></td>

									<c:choose>
										<c:when test="${arrayTimeIn[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 " value="9.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeIn[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${arrayTimeOut[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 " value="18.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeOut[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>

									<td><input type="hidden" name="time_now" id="time${date}"
										class="form-control input-lg timepicker timepicker-24 test"
										value="${timeNow}" onclick="timechenge()"
										onkeypress='return false'></td>
									<td><input type="hidden" name="date_now" id="date${date}"
										value="${dateNow}" onchange="datechenge()"
										class="form-control input-lg form-control-inline input-medium date-picker test"
										size="9" type="hidden" onkeypress='return false'></td>


									<!-- ///////////////////////////////////////// -->
								</tr>
							
							
							</c:when>
						
							</c:choose>
						
							</c:forEach>
							<c:if test="${checkday==0}">
						<c:choose>
							<c:when
								test="${fullDateKub[status.index].toString().substring(0,3) != 'Sat' and fullDateKub[status.index].toString().substring(0,3) != 'Sun' }">
								<tr class="bg-white bg-font-white">

									<td style="width: 25%;"><fmt:formatDate
											value="${fullDateKub[status.index]}" pattern="E dd-MMM-yyyy"></fmt:formatDate>
									</td>

									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<c:if test="${month_now<monthSearch}">


										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}" disabled
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													class="form-control " name="dateTimeIn" id="timeIn${date}"
													disabled></td>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control  " disabled></td>

											</c:when>

										</c:choose>

									</c:if>
									<c:if test="${month_now>monthSearch}">
										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}"
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="font-white form-control timepicker timepicker-24"
													value="0.00" onclick="showTimeIn(${date});"></td>


											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeIn[status.index]}" onclick="timechenge()"
													onkeypress='return false' style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}" value="0.00"
													class="font-white form-control timepicker timepicker-24 "
													onclick="showTimeOut(${date});" onkeypress='return false'></td>

											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeOut[status.index]}"
													onclick="timechenge()" onkeypress='return false'
													style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>

									</c:if>
									<c:if test="${month_now==monthSearch}">
										<c:if test="${day_now<arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}" disabled
												value="${arrayDescription[status.index]}"
												class="form-control "></td>
											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														class="form-control " name="dateTimeIn" id="timeIn${date}"
														disabled></td>
												</c:when>


											</c:choose>

											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" disabled
														class="form-control "></td>

												</c:when>

											</c:choose>

										</c:if>
										<c:if test="${day_now>=arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}"
												value="${arrayDescription[status.index]}"
												class="form-control "></td>

											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="font-white form-control timepicker timepicker-24"
														value="0.00" onfocus="showTimeIn(${date});"></td>


												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeIn[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" value="0.00"
														class="font-white form-control timepicker timepicker-24 "
														onclick="showTimeOut(${date});" onkeypress='return false'></td>

												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeOut[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>

										</c:if>
									</c:if>
									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<td><c:if
											test="${arrayStatus[status.index].toString() == 'W' }">
											<span class="label label-sm label-warning">Waiting for
												approve</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'A'}">
											<span class="label label-sm label-success">Approved</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'R'}">
											<span class="label label-sm label-danger">Reject</span>
										</c:if></td>
									<td><a href="javascript:;"
										class="btn bg-green bg-font-green"
										onclick="ajaxCall(${date},${monthList},${yearList});"> <i
											class="fa fa-file-o"></i> SAVE
									</a></td>
									<td><a href="javascript:;"
										class="btn bg-yellow-saffron bg-font-yellow-saffron"
										onclick="myFunctionReset(${date});"> <i class="fa fa-undo">
										</i> RESET
									</a></td>

									<!-- ///////////////////hidden////////////////////// -->
									<td><input type="hidden" name="" id=""
										value="${monthList}"></td>
									<td><input type="hidden" name="discription"
										id="discriptionHidden${date}"
										value="${arrayDescription[status.index]}"></td>
									<td style="width: 40%;"><input type="hidden" name="id"
										id="id${date}" value="${arrayId[status.index]}"></td>

									<c:choose>
										<c:when test="${arrayTimeIn[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 " value="9.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeIn[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${arrayTimeOut[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 " value="18.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeOut[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>

									<td><input type="hidden" name="time_now" id="time${date}"
										class="form-control input-lg timepicker timepicker-24 test"
										value="${timeNow}" onclick="timechenge()"
										onkeypress='return false'></td>
									<td><input type="hidden" name="date_now" id="date${date}"
										value="${dateNow}" onchange="datechenge()"
										class="form-control input-lg form-control-inline input-medium date-picker test"
										size="9" type="hidden" onkeypress='return false'></td>


									<!-- ///////////////////////////////////////// -->
								</tr>





							</c:when>
							<c:otherwise>
								<tr class="bg-grey bg-font-grey">

									<td style="width: 25%;"><fmt:formatDate
											value="${fullDateKub[status.index]}" pattern="E dd-MMM-yyyy"></fmt:formatDate>
									</td>

									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<c:if test="${month_now<monthSearch}">


										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}" disabled
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													class="form-control " name="dateTimeIn" id="timeIn${date}"
													disabled></td>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control  " disabled></td>

											</c:when>

										</c:choose>



									</c:if>
									<c:if test="${month_now>monthSearch}">
										<td style="width: 40%;"><input type="text"
											name="discription" id="discription${date}"
											value="${arrayDescription[status.index]}"
											class="form-control "></td>
										<c:choose>
											<c:when test="${arrayTimeIn[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="font-white form-control timepicker timepicker-24"
													value="0.00" onclick="showTimeIn(${date});"></td>


											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="dateTimeIn" id="timeIn${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeIn[status.index]}" onclick="timechenge()"
													onkeypress='return false' style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${arrayTimeOut[status.index]==null}">
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}" value="0.00"
													class="font-white form-control timepicker timepicker-24 "
													onclick="showTimeOut(${date});" onkeypress='return false'></td>

											</c:when>
											<c:otherwise>
												<td style="width: 15%;"><input type="text"
													name="datetimeOut" id="timeOut${date}"
													class="form-control timepicker timepicker-24 "
													value="${arrayTimeOut[status.index]}"
													onclick="timechenge()" onkeypress='return false'
													style="height: 34px;"></td>

											</c:otherwise>
										</c:choose>

									</c:if>
									<c:if test="${month_now==monthSearch}">
										<c:if test="${day_now<arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}" disabled
												value="${arrayDescription[status.index]}"
												class="form-control "></td>
											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														class="form-control " name="dateTimeIn" id="timeIn${date}"
														disabled></td>
												</c:when>


											</c:choose>

											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" disabled
														class="form-control "></td>

												</c:when>

											</c:choose>

										</c:if>
										<c:if test="${day_now>=arrayDayHidden[status.index]}">
											<td style="width: 40%;"><input type="text"
												name="discription" id="discription${date}"
												value="${arrayDescription[status.index]}"
												class="form-control "></td>

											<c:choose>
												<c:when test="${arrayTimeIn[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="font-white form-control timepicker timepicker-24"
														value="0.00" onclick="showTimeIn(${date});"></td>


												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="dateTimeIn" id="timeIn${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeIn[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${arrayTimeOut[status.index]==null}">
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}" value="0.00"
														class="font-white form-control timepicker timepicker-24 "
														onclick="showTimeOut(${date});" onkeypress='return false'></td>

												</c:when>
												<c:otherwise>
													<td style="width: 15%;"><input type="text"
														name="datetimeOut" id="timeOut${date}"
														class="form-control timepicker timepicker-24 "
														value="${arrayTimeOut[status.index]}"
														onclick="timechenge()" onkeypress='return false'
														style="height: 34px;"></td>

												</c:otherwise>
											</c:choose>

										</c:if>
									</c:if>
									<!-- ///////////////////////Don't input data in future//////////////////////////// -->
									<td><c:if
											test="${arrayStatus[status.index].toString() == 'W' }">
											<span class="label label-sm label-warning">Waiting for
												approve</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'A'}">
											<span class="label label-sm label-success">Approved</span>
										</c:if> <c:if test="${arrayStatus[status.index].toString() == 'R'}">
											<span class="label label-sm label-danger">Reject</span>
										</c:if></td>
									<td><a href="javascript:;"
										class="btn bg-green bg-font-green"
										onclick="ajaxCall(${date},${monthList},${yearList});"> <i
											class="fa fa-file-o"></i> SAVE
									</a></td>
									<td><a href="javascript:;"
										class="btn bg-yellow-saffron bg-font-yellow-saffron"
										onclick="myFunctionReset(${date});"> <i class="fa fa-undo">
										</i> RESET
									</a></td>

									<!-- ///////////////////hidden////////////////////// -->
									<td><input type="hidden" name="" id=""
										value="${monthList}"></td>
									<td><input type="hidden" name="discription"
										id="discriptionHidden${date}"
										value="${arrayDescription[status.index]}"></td>
									<td style="width: 40%;"><input type="hidden" name="id"
										id="id${date}" value="${arrayId[status.index]}"></td>

									<c:choose>
										<c:when test="${arrayTimeIn[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 " value="9.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeIn" id="timeInHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeIn[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${arrayTimeOut[status.index]==null}">
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 " value="18.00"
												onclick="timechenge()" onkeypress='return false'
												style="height: 34px;"></td>

										</c:when>
										<c:otherwise>
											<td style="width: 15%;"><input type="hidden"
												name="dateTimeOut" id="timeOutHidden${date}"
												class="form-control timepicker timepicker-24 "
												value="${arrayTimeOut[status.index]}" onclick="timechenge()"
												onkeypress='return false' style="height: 34px;"></td>

										</c:otherwise>
									</c:choose>

									<td><input type="hidden" name="time_now" id="time${date}"
										class="form-control input-lg timepicker timepicker-24 test"
										value="${timeNow}" onclick="timechenge()"
										onkeypress='return false'></td>
									<td><input type="hidden" name="date_now" id="date${date}"
										value="${dateNow}" onchange="datechenge()"
										class="form-control input-lg form-control-inline input-medium date-picker test"
										size="9" type="hidden" onkeypress='return false'></td>


									<!-- ///////////////////////////////////////// -->
								</tr>
							</c:otherwise>
						</c:choose>
						</c:if>
					</c:forEach>
				</table>
				<!-- 		<a href="javascript:;" class="btn bg-green bg-font-green"
					onclick="ajaxSaveAll(${daysInMonth},${monthList},${yearList});">
					<i class="fa fa-files-o"></i> SAVE ALL
				</a> <a href="javascript:;"
					class="btn bg-yellow-saffron bg-font-yellow-saffron"
					onclick="myFunctionResetAll();"> <i class="fa fa-undo"></i>
					RESET ALL
				</a>
		 -->
			</div>
		</form>
		<!-- -----------------------------------------End add-------------------------------------------- -->
	</div>
</div>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script>
	function datechenge() {
		var fulldate = "${fulldate}".trim();
		var Userdate = $("#mydate").val();
		if (fulldate != Userdate) {
			$("#detail").show();
			$("#labeldetail").show();
		} else {
			$("#detail").hide();
			$("#labeldetail").hide();
		}
	}

	for (i = 1; i <= 6; i++) {
		!function(i) {
			$.post("ajax.php", {
				param : i
			}, function(response) {
			});
		}(i);
	}
</script>
<!-- ////////////////////////////////////////start ajax///////////////////////////////////////////////// -->

<script type="text/javascript">
	function ajaxCall(day,month,year) {
		//alert(day);
		var discription = $('#discription'+day).val();
		var timeIn = $('#timeIn'+day).val();
		var timeOut = $('#timeOut'+day).val();
		var date = $('#date'+day).val();
		var time = $('#time'+day).val();
		var id= $('#id'+day).val();
		
		swal({
			  title: "Are you sure?",
			  text: "You will save this row.",
			  type: "info",
			  showCancelButton: true,
			  confirmButtonClass: "btn-info",
			  confirmButtonText: "Yes, save it!",
			  cancelButtonText: "No, cancel please!",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			function(isConfirm) {
			  if (isConfirm) {
				  if(discription !=""&& timeIn !="" && timeOut !=""&& timeIn !="0:00" && timeOut !="0:00"){
						$.ajax({
							url : "addTimesheet",
							method : "POST",
							type : "JSON",
							data : {
								"discription" : discription,
								"time_in" : timeIn,
								"time_out" : timeOut,
								"date_now" : date,
								"time_now" : time,
								"dateInOut":day,
								"monthInOut":month,
								"yearInOut":year,
								"id":id
							},
							success : function(data) {
									console.log(data);
								   console.log($('.timesheetTable').html(data));
								   swal("Done!", "Your timesheet has been save.", "success");
								   setTimeout(location.reload.bind(location), 800);
							}
						})
						}else {
							swal('Please!', 'Input your discription,time check,time check out', 'error');
						}
			  
			  } else {
			    swal("Cancelled", "Your timesheet did not save :)", "error");
			  }
			});
		

	};
</script>
<script type="text/javascript">
	function ajaxCalldel(day,month,year) {
	
		var discription = $('#discription'+day).val();
		var timeIn = $('#timeIn'+day).val();
		var timeOut = $('#timeOut'+day).val();
		var date = $('#date'+day).val();
		var time = $('#time'+day).val();
		var id= $('#id'+day).val();
		if(discription !=""&& timeIn !="" && timeOut !=""&& timeIn !="0:00" && timeOut !="0:00"){
		$.ajax({
			url : "delTimesheet",
			method : "POST",
			type : "JSON",
			data : {
				"discription" : discription,
				"time_in" : timeIn,
				"time_out" : timeOut,
				"date_now" : date,
				"time_now" : time,
				"dateInOut":day,
				"monthInOut":month,
				"yearInOut":year,
				"id":id
			},
			success : function(data) {
					console.log(data);
				   console.log($('.timesheetTable').html(data));
				   swal('Delete!','Success!','warning');
			}
		})
		}else {
			swal('Please!', 'Input your discription,time check,time check out', 'error');
		}

	};
</script>
<script>
document.querySelector('.sweet-${status.count}').onclick = function(){
    swal({
      title: "Are you sure!",
      text: "You will be deleting this id!",
      type: "info",
      showCancelButton: true,
      confirmButtonClass: 'btn-primary',
      confirmButtonText: 'OK'
    }, function (inputValue) {
        if (inputValue === false) return false;
        if (inputValue === "") {
          return false
        }
        document.location = "delTimesheet?id=${timesheet.id}";
      });
    }; 
</script>

<script type="text/javascript">
	function ajaxSaveAll(endDay,month,year) {
		var day;
		swal({
			  title: "Are you sure?",
			  text: "Your will not be able to recover this imaginary file!",
			  type: "info",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  confirmButtonText: "Yes, Save it!",
			  closeOnConfirm: false
			},
			function(){
				for(day=1;day<=endDay;day++){
					var discription = $('#discription'+day).val();
					var timeIn = $('#timeIn'+day).val();
					var timeOut = $('#timeOut'+day).val();
					var date = $('#date'+day).val();
					var time = $('#time'+day).val();
					var id= $('#id'+day).val();
					if(discription !=""&& timeIn !="" && timeOut !="" && timeIn !="0:00" && timeOut !="0:00"){
					
					$.ajax({
						url : "addTimesheet",
						method : "POST",
						type : "JSON",
						data : {
							"discription" : discription,
							"time_in" : timeIn,
							"time_out" : timeOut,
							"date_now" : date,
							"time_now" : time,
							"dateInOut":day,
							"monthInOut":month,
							"yearInOut":year,
							"id":id
						},
						success : function(data) {
					
						}
					})
					}
					}
			  swal("Done!", "", "success");
			});
		

	};
</script>

<!-- ////////////////////////////////////////end ajax///////////////////////////////////////////////// -->
<script type="text/javascript">
function myFunctionReset(day) {
	var discription = $('#discription'+day).val();
	var timeIn = $('#timeIn'+day).val();
	var timeOut = $('#timeOut'+day).val();
	var date = $('#date'+day).val();
	var time = $('#time'+day).val();
	var discriptionHidden = $('#discriptionHidden'+day).val();
	var timeInHidden = $('#timeInHidden'+day).val();
	var timeOutHidden = $('#timeOutHidden'+day).val();
	
	swal({
		  title: "Are you sure?",
		  text: "Your will reset this row.",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-warning",
		  confirmButtonText: "Yes, reset it!",
		  closeOnConfirm: false
		},
		function(){
			$('#discription'+day).val(discriptionHidden);
			$('#timeIn'+day).val(timeInHidden);
			$('#timeOut'+day).val(timeOutHidden);
		  swal("Reset!", "", "success");
		  setTimeout(location.reload.bind(location), 800);
		});
	
	
}
</script>
<script>
            function myFunctionResetAll() {
            	swal({
            		  title: "Are you sure?",
            		  text: "Confirm RESET this page",
            		  type: "warning",
            		  showCancelButton: true,
            		  confirmButtonClass: "btn-warning",
            		  confirmButtonText: "Yes, reset it!",
            		  closeOnConfirm: false
            		},
            		function(){
            			document.getElementById("timesheet").reset();
            		  swal("Done!", "", "success");
            		});
                        
                    };
</script>
<script>
	$(document).ready(function() {
		var value = "${flag}";
		if (value == "1") {
			var name = "${name}";
			var monthSearch = "${monthSearch}";
			var yearSearch = "${yearSearch}";
			document.getElementById(name).selected = "true";
			document.getElementById(monthSearch).selected = "true";
			document.getElementById(yearSearch).selected = "true";
		} else {
			$('.select2me').select2();
		}

	});
</script>


<script type="text/javascript">
function  showTimeIn(day) {
	var timeInHidden = $('#timeInHidden'+day).val();
	$('#timeIn'+day).val(timeInHidden);
	document.getElementById("timeIn"+day ).className = "font-black form-control timepicker timepicker-24";
}
function  showTimeOut(day) {
	var timeOutHidden = $('#timeOutHidden'+day).val();
	$('#timeOut'+day).val(timeOutHidden);
	document.getElementById("timeOut"+day ).className = "font-black form-control timepicker timepicker-24";
}

</script>
<script>
	function myFunction() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			td1 = tr[i].getElementsByTagName("td")[1];
			td2 = tr[i].getElementsByTagName("td")[2];
			td3 = tr[i].getElementsByTagName("td")[3];
			td4 = tr[i].getElementsByTagName("td")[4];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
					if (td1.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
						if (td2.innerHTML.toUpperCase().indexOf(filter) > -1) {
							tr[i].style.display = "";
						} else {
							tr[i].style.display = "none";
							if (td3.innerHTML.toUpperCase().indexOf(filter) > -1) {
								tr[i].style.display = "";
							} else {
								tr[i].style.display = "none";
								if (td4.innerHTML.toUpperCase().indexOf(filter) > -1) {
									tr[i].style.display = "";
								}
							}
						}
					}
				}
			}
		}
	}


<script src="../assets/global/plugins/counterup/jquery.waypoints.min.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/counterup/jquery.counterup.min.js"
	type="text/javascript"></script>
