<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="../assets/global/plugins/moment.min.js"
	type="text/javascript"></script>
<!--    <script src="../assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>  -->
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="../assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<!--         <script src="../assets/apps/scripts/calendar.min.js" type="text/javascript"></script> -->
<!-- END PAGE LEVEL SCRIPTS -->

<style>
.fc-sat, .fc-sun {
	background-color: #E9EDEF;
}

.fully_colored_holiday, .fully_colored_holidaydiv, .fully_colored_holidayspan {
	background: #E9EDEF;
	text-color: black;
}
</style>

<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-clock-o"></i> <span
				class="caption-subject font-red sbold uppercase">Check All calendar</span>
		</div>

		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>

	<form action="SearchAllinCalendar" method="post">
		<div class="portlet-body">
			<div class="portlet-body">
				<p id="demo"></p>

				<div class="form-group form-md-line-input">
					<label class="col-md-1 control-label">Name :</label>
					<div class="form-group">
						<div class="col-xs-8 col-md-4">
							<select class="form-control select2me" name="usercalendar">
								<optgroup label="Enable">
									<c:forEach var="user" items="${cubeUser}">
										<c:if test="${user.enable == 1 }">
											<c:if test="${logonUser == nulll }">
												<option value="${user.id}" id="${user.id}"
													<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/>
													</c:if>>${user.department_id}
													- ${user.name}</option>
											</c:if>
											<c:if test="${logonUser != nulll }">
												<option value="${user.id}" id="${user.id}"
													<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/>
													</c:if>>${user.department_id}
													- ${user.name}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</optgroup>
								<optgroup label="Disable">
									<c:forEach var="user" items="${cubeUser}">
										<c:if test="${user.enable == 0 }">
											<c:if test="${logonUser == nulll }">
												<option value="${user.id}" id="${user.id}"
													<c:if test="${fn:containsIgnoreCase(user.id,onlineUser.id)}"><c:out value="selected=selected"/>
													</c:if>>${user.department_id}
													- ${user.name}</option>
											</c:if>
											<c:if test="${logonUser != nulll }">
												<option value="${user.id}" id="${user.id}"
													<c:if test="${fn:containsIgnoreCase(user.id,logonUser)}"><c:out value="selected=selected"/>
													</c:if>>${user.department_id}
													- ${user.name}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</optgroup>
							</select>
						</div>
						<div class="col-xs-2 col-md-4">
							<button id="buttonSave" type="submit" class="btn sbold blue"
								style="margin-bottom: 20px; margin-lift: 50px;">
								<i class="fa fa-search"></i> Search
							</button>
						</div>
					</div>
				</div>
				<label class="hidden-lg"></label>

				<div class="row">
					<div class="col-md-12">
						<div class="portlet light portlet-fit bordered calendar">
							<div class="portlet-title">
								<div class="caption">
									<i class=" icon-layers font-green"></i> <span
										class="caption-subject font-green sbold uppercase">CHECK
										CALENDAR </span>
								</div>
							</div>

							<div class="portlet-body">
								<div class="row">
									<!-- Calendar-->
									<div class="col-md-12 col-sm-12">
										<div id="calendar" class="has-toolbar"></div>
									</div>
									<div class="col-md-12 col-sm-12">
										<div id="calendar" class="has-toolbar"></div>
										<input type="hidden" name="monthcalendar" value="${month}">
									</div>
									<!-- Calendar-->
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END PAGE BASE CONTENT -->
				<%-- <input type="hidden" value="${datenow}" id="test"> --%>
			</div>
		</div>
	</form>
</div>

<script>
	$(document).ready(function() {
		// page is now ready, initialize the calendar...
		$('#calendar').fullCalendar();
	});
</script>

<script type="text/javascript">
	var AppCalendar = function() {
		return {
			//main function to initiate the module
			init : function() {
				this.initCalendar();
			},
			initCalendar : function() {
				if (!jQuery().fullCalendar) {
					return;
				}
				var date_time  = '${flag12}';
				if (date_time != "") {
					var noTime = $.fullCalendar.moment(date_time, "YYYY-MM-DD");
				} else {
					var noTime = moment();
				}
				// CheckIn,CheckOut,totaltimework,Status
				var start_date = [];
				var end_date   = [];
				var TimeIO     = [];
				var TimeIn     = [];
				var workhour   = [];
				var workmin    = [];
				var events1    = [];
				var desCheckin = [];
				var desTotalin = [];
				var destimeIn  = [];
				var destimeOut = [];				
				var StartTimeUser = '${stime}'; // Start time work of user
				var mycolor = [ 'red-intense', 'grey', 'red', 'yellow', 'green', 'purple','LightSeaGreen '];

				<c:forEach var="work" items="${work}">
				var IN 	  = '${work.mycheckins}';
				var text2 = IN.substring(0,16);
				var Tin   = '${work.mycheckin}';
				var Tout  = '${work.checkouttime}';
				var hour  = '${work.hour}';
				var min   = '${work.min}';

				var TcheckIn = Tin.substring(11, 16);
				var timetime = Tin.substring(6,10)
				var TcheckOut= Tout.substring(0, 5);
				var timefull = TcheckIn + " - " + TcheckOut;
				var timework = hour +":"+ min;

				var date1 = new Date(Tin);
				var date2 = new Date(text2);
				start_date.push(date1);
				end_date.push(date2);
				TimeIO.push(timefull);
				TimeIn.push(TcheckIn);
				workhour.push(hour);
				workmin.push(min);
				desCheckin.push(TcheckIn);
				destimeIn.push(TcheckIn);
				destimeOut.push(TcheckOut);
				desTotalin.push(timework);
				</c:forEach>
				
				//check status of check in
				for (usertime in start_date) {
					var checkin_time = TimeIn[usertime].split(":");
					var start = StartTimeUser.split(":");
					/*for(i = 0; i < checkin_time.length; i++){
							document.write("<br /> Element " + i + " = " + checkin_time[i]);
						}*/
					var checkin = parseInt(checkin_time[0]);
					var startTimeuser = parseInt(start[0]);
					if (checkin < startTimeuser) {
						Status = "On Time" ,
						color  = "LightSeaGreen ";
					}else if(checkin > startTimeuser){
						Status = "Late",
						color  = "red";
					}else{
						var checkin = parseInt(checkin_time[1]);
						var startTimeuser = parseInt(start[1]);
						if (checkin <= startTimeuser) {
							Status = "On Time" ,
							color  = "LightSeaGreen ";
						}else {
							Status = "Late",
							color  = "red";
						}
					}
					events1.push({
								title : Status,
								start : new Date(start_date[usertime].getFullYear(), 
										start_date[usertime].getMonth(), 
										start_date[usertime].getDate()),
										
								end   : new Date(end_date[usertime].getFullYear(),
										end_date[usertime].getMonth(),
										end_date[usertime].getDate() + 1),
								backgroundColor : App.getBrandColor(color),
								allDay: true,
								description:'<b>'+'User check-in : '+'</b>' + desCheckin[usertime] +'<br/><b>&nbsp;'+'User time work start  : '+'</b>' +'${stime}',
								className: 'status'
							});
				}
				
				//CheckIn and CheckOut
				var x;
				for (x in start_date) {
					events1.push({
						title : TimeIO[x],
						start : new Date(start_date[x].getFullYear(), 
								start_date[x].getMonth(), 
								start_date[x].getDate()),
								
						end   : new Date(end_date[x].getFullYear(),
								end_date[x].getMonth(),
								end_date[x].getDate() + 1),
						backgroundColor : App.getBrandColor('yellow'),
						description:'<b>'+'Check-in : '+'</b>' + destimeIn[x] +'<br/><b>&nbsp;'+'Check-out : '+'</b>' + destimeOut[x],
						allDay : true,
					});
				}
				
				//Total time work
				for (y in start_date) {
					var z = parseInt(workhour[y]);
					if (z >= 8) {
						myary = "blue";
					} else {
						myary = "grey";
					}
					events1.push({
								title : workhour[y] + ":" + workmin[y] + " " + " hrs. ",
								start : new Date(start_date[y].getFullYear(), 
										start_date[y].getMonth(), 
										start_date[y].getDate()),
										
								end   : new Date(end_date[y].getFullYear(),
										end_date[y].getMonth(),
										end_date[y].getDate() + 1),
								backgroundColor : App.getBrandColor(myary),
								allDay: true,
								description: '<b>'+'Total work : '+'</b>'+desTotalin[y]  + " hrs. ",
								className: 'totalwork'
							});
				}
				
				//leave in calendar
				var BeginDate= [];
	            var EndDate  = [];
	            var leave    =[];
	            var EventLeave =[];
	            var userLeave  =[];
	            var des =[];
	            var id_leave = [];
	            var mycolor  = ['red','grey','blue','yellow','green','purple'];
	            
	            <c:forEach var="leave" items="${leave}">
	            var leaveStart = '${leave.startDate}';
	            	leaveStart = leaveStart.substring(0,10);
	            var leaveEnd   = '${leave.endDate}';
	            	leaveEnd   = leaveEnd.substring(0,10);
	            
	            <c:if test="${leave.leaveTypeId.toString() == 1 }">
	            	var leaveType = 'ลากิจ/พักร้อน';
	            </c:if>
	            
	            <c:if test="${leave.leaveTypeId.toString() == 2 }">
	            	var leaveType = 'ลาอื่นๆ';
	            </c:if>
	            
	            <c:if test="${leave.leaveTypeId.toString() == 3 }">
	            	var leaveType = 'ลาป่วย';
	            </c:if>
	            
	            <c:if test="${leave.leaveTypeId.toString() == 4 }">
	            	var leaveType = 'ขาดงาน';
	            </c:if>
	            
	            <c:if test="${leave.leaveTypeId.toString() == 5 }">
	           		var leaveType = 'ลาโดยไม่รับค่าจ้าง';
	            </c:if>
	            
	            <c:if test="${leave.leaveTypeId.toString() == 6 }">
	            	var leaveType = 'ลาพักร้อนที่เหลือจากปีก่อน';
	            </c:if>
	            
	            <c:if test="${leave.leaveTypeId.toString() == 9 }">
	            	var leaveType = 'อื่นๆ';
	            </c:if>
	     
	            var dateLeave = new Date(leaveStart);
	            var dateLeaveE = new Date(leaveEnd);
	            
	            <c:set var = "string1" value = "${leave.description}"/>
	            	<%pageContext.setAttribute("newline", "\r\n");%>
	            <c:set var = "string2" value = "${fn:replace(string1,newline,'')}" />
	            
	            var desLeave = '${string2}';
	            var leaveID  = '${leave.leaveId}';
	            var user     = "${userId}";
	            BeginDate.push(dateLeave);
	            EndDate.push(dateLeaveE);
	            leave.push(leaveType);
	            des.push(desLeave);
	            id_leave.push(leaveID);
	            userLeave.push(user.toString());
	            </c:forEach>
	            
	            var dayLeave;  
	            var type_color ;
	            for(dayLeave in BeginDate){
	            	 switch (leave[dayLeave]) {
	            	 case "ลากิจ/พักร้อน": type_color ="#4B77BE"; break;
	            	 case "ลาป่วย":  type_color ="#E26A6A"; break;
	            	 case "ลาอื่นๆ": type_color ="#33CC66"; break;
	            	 case "ลาพักร้อนที่เหลือจากปีก่อน": type_color = "#E87E04"; break;
	            	 default: type_color ="#525E64";
	            	 }
	            EventLeave.push({
	            	id	 : id_leave[dayLeave],   
	            	title: leave[dayLeave],				   
					start: new Date(BeginDate[dayLeave].getFullYear(),
							   BeginDate[dayLeave].getMonth(),
							   BeginDate[dayLeave].getDate(),0,0),
					
					end	 : new Date(EndDate[dayLeave].getFullYear(),
							   EndDate[dayLeave].getMonth(),
							   EndDate[dayLeave].getDate() + 1,0,0 ),
				   description: des[dayLeave] ,
				   backgroundColor: type_color,
	               allDay: true,
	               className: 'leave'//userLeave[dayLeave]
	            
				   });            
	            }
				
				//Holiday 
				var start_dateHo= [];
				var end_dateHo  = [];
				var holiday     = [];
				var EventsHo    = [];
				var desHoliday  = [];
				var id13 = [];
				var mycolor13   = [ 'red', 'grey', 'blue', 'yellow', 'green', 'purple' ];
				
				<c:forEach var="holiday" items="${holidayList}">
				var HoStart = '${holiday.start_date}';
				var HoEnd   = '${holiday.end_date}';
				var HoTitle = '${holiday.head}';
				var Hodes   = '${holiday.description}';
				var Ho_ID   = '${holiday.id_date}';
				
				var dateHoSt  = new Date(HoStart);
				var dateHoEnd = new Date(HoEnd);

				start_dateHo.push(dateHoSt);
				end_dateHo.push(dateHoEnd);
				holiday.push(HoTitle);
				desHoliday.push(Hodes);
				id13.push(Ho_ID);
				</c:forEach>

				var a;
				for (a in start_dateHo) {
					EventsHo.push({
						id    : id13[a],
						title : holiday[a],
						start : new Date(start_dateHo[a].getFullYear(), 
									start_dateHo[a].getMonth(), 
									start_dateHo[a].getDate()),
									
						end   : new Date(end_dateHo[a].getFullYear(),
									end_dateHo[a].getMonth(),
									end_dateHo[a].getDate() + 1),
						backgroundColor : '#E9EDEF',
						allDay    : true,
						className : 'holiday',
						description: desHoliday[a] ,
						editable  : false
					});
				}

				//header
				var date = new Date();
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();
				var h = {};

				if (App.isRTL()) {
					if ($('#calendar').parents(".portlet").width() <= 720) {
						$('#calendar').addClass("mobile");
						  h = {
							right : 'title, prev, next',
							center: '',
							left  : 'agendaDay, agendaWeek, month, today'
						};
					} else {
						$('#calendar').removeClass("mobile");
						h = {
							right : 'title',
							center: '',
							left  : 'agendaDay, agendaWeek, month, today, prev,next'
						};
					}
				} else {
					if ($('#calendar').parents(".portlet").width() <= 720) {
						$('#calendar').addClass("mobile");
						h = {
							left  : 'title, prev, next',
							center: '',
							right : 'today,month,agendaWeek,agendaDay'
						};
					} else {
						$('#calendar').removeClass("mobile");
						h = {
							left  : 'title',
							center: '',
							right : 'prev,next,today,month,agendaWeek,agendaDay'
						};
					}
				}

				var initDrag = function(el) {
					// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
					// it doesn't need to have a start or end
					var eventObject = {
						title : $.trim(el.text())
					// use the element's text as the event title
					};
					// store the Event Object in the DOM element so we can get to it later
					el.data('eventObject', eventObject);
					// make the event draggable using jQuery UI
					el.draggable({
						zIndex : 999,
						revert : true, // will cause the event to go back to its
						revertDuration : 0
					//  original position after the drag
					});
				};

				var addEvent = function(title) {
					title = title.length === 0 ? "Untitled Event" : title;
					var html = $('<div class="external-event label label-default">' + title + '</div>');
					jQuery('#event_box').append(html);
					initDrag(html);
				};

				$('#external-events div.external-event').each(function() {
					initDrag($(this));
				});

				$('#event_add').unbind('click').click(function() {
					var title = $('#event_title').val();
					addEvent(title);
				});

				//predefined events
				$('#event_box').html("");
				addEvent("My Event 1");
				addEvent("My Event 2");
				addEvent("My Event 3");
				addEvent("My Event 4");
				addEvent("My Event 5");
				addEvent("My Event 6");

				$('#calendar').fullCalendar('destroy'); // destroy the calendar
				$('#calendar').fullCalendar(
								
						{ //re-initialize the calendar
									header : h,
									defaultView : 'month', // change default view with available options from http://arshaw.com/fullcalendar/docs/views/Available_Views/ 
									defaultDate : moment(noTime),
									slotMinutes : 15,
									editable    : false, //edit event
									droppable   : true, // this allows things to be dropped onto the calendar !!!

									drop : function(date, allDay) { // this function is called when something is dropped
										var check = '${checktimecalendar}'.trim();
										// retrieve the dropped element's stored Event Object
										var originalEventObject = $(this).data('eventObject');
										// we need to copy it, so that multiple events don't have a reference to the same object
										var copiedEventObject = $.extend({}, originalEventObject);

										// assign it the date that was reported
										copiedEventObject.start = date;
										copiedEventObject.allDay = allDay;
										copiedEventObject.className = $(this).attr("data-class");

										// render the event on the calendar
										// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
										$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

										// is the "remove after drop" checkbox checked?
										if ($('#drop-remove').is(':checked')) {
											// if so, remove the element from the "Draggable Events" list
											$(this).remove();
										}
									},
									
									selectable   : true,
									selectHelper : true,
									eventRender  : function(calEvent, element) {
										var start = moment(calEvent.start).get('date');
										var end = moment(calEvent.end).get('date');
										var moment1 = moment(calEvent.start);
					                	var moment2 = moment(calEvent.end);             	
					                    element.popover({
					                        animation:  true,
					                        placement: 'bottom',
					                        container: "body",
					                        html : true,
					                        delay: 100,
					                        title: '<b>'+calEvent.title +'</b>',
					                        content: '<b>' +$.fullCalendar.formatRange(moment1, moment2 -1 , 'D MMMM YYYY') + "</b><br/>"
					                        + "<p style=\"max-width:500px;word-wrap:break-word;margin-bottom:50px;\">&nbsp;"+ calEvent.description + " <br/>"  + "</p>",   
					                        trigger: 'hover'
					                    });
					                    
										if (calEvent.className == "holiday") {
											var for_i = end - start;
											for (var i = 0; i < for_i; i++) {
													var dataToFind = moment(calEvent.start).format('YYYY-MM-DD');
													var date_new   = new Date(dataToFind);
												date_new.setDate(start + i);
												dataToFind = moment(date_new).format('YYYY-MM-DD');
												$("td[data-date='"+dataToFind+ "']").addClass('fully_colored_holiday');
											}
										}
										
										//icon of event
										if (calEvent.className == "holiday" ) {
											element.find(".fc-title").prepend("<i class='fa fa-home'> </i>&nbsp;");
											element.find(".fc-title").css('color', 'black');
										} else if (calEvent.className =="leave"){
											element.find(".fc-title").prepend("<i class='fa fa-thumb-tack searchon'></i>&nbsp;")
											element.find("div.fc-content").append( "<span> <i class='fa fa-trash closeon' style='float:right;position:absolute;right:0;top:0position:absolute;right:0;top:0'></i> </span>" );
										} else if (calEvent.className =="status"){
											element.find(".fc-title").prepend("<i class='fa fa-hourglass-start'></i>&nbsp;")
										} else if (calEvent.className =="totalwork"){
											element.find(".fc-title").prepend("<i class='fa fa-hourglass'></i>&nbsp;")
										} else {
											element.find(".fc-title").prepend("<i class='fa fa-hourglass-half'> </i>&nbsp;");
										}
										 element.find(".closeon").on('click',function() {                //Delete Function 
						                	  if (confirm("Are you sure Delete ?")) {                 		
						                		     document.location = "delete_leave_calecdar?leave_id="+ calEvent.id ;
						                  	  }
						                });
										 element.find(".searchon").on('click',function() { //Search Function
							                  
					                     	  document.location = "myleave_edit?flag=1&Id="+calEvent.id+"&date="+moment(calEvent.start).format('DD-MM-YYYY')+"&thisuser=" +calEvent.className ;                     
					                	                  });
									},
									
									/* 	viewRender: function (view , element){
												var b = $('#calendar').fullCalendar('getDate');
												alert(b.format('L'));
												document.getElementById("test").value = b ;
											},   */
									eventLimit : true,
									events : events1 
								});
				$('#calendar').fullCalendar('addEventSource', EventsHo);
				$('#calendar').fullCalendar('addEventSource', EventLeave);
			}
		};
	}();
	
	//Search month
		jQuery(document).ready(
				function() {
					AppCalendar.init();
					$('.fc-next-button').click(function() {
	                            var usercalendar  = '${logonUser}'.trim();
								var test = '${datenowcalendar}'.trim();
								var monthcalendar = '${month}'.trim();
								var b = $('#calendar').fullCalendar('getDate');
								var x = b.format('L');
								document.location = 'searchAllmonth?timecalendar='
													+ test + "&datecalendar=" 
													+ x + "&usercalendar=" + usercalendar;
							});
					
					var events2 = [];
					$('.fc-prev-button').click(function() { // 	refresh หน้า
								var usercalendar  = '${logonUser}'.trim();
								var test = '${datenowcalendar}'.trim();
								var monthcalendar = '${month}'.trim();
								var b = $('#calendar').fullCalendar('getDate');
								var x = b.format('L');
								document.location = 'searchAllmonth?timecalendar='
													+ test + "&datecalendar=" + x + 
													"&usercalendar=" + usercalendar;
							});
				});
</script>


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




