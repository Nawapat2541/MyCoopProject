<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<!-- BEGIN PAGE LEVEL PLUGINS -->
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




<c:set var="now" value="<%=new java.util.Date()%>" />
<%-- ${checkinhourtime} --%>
<%-- ${checktimecheckin} --%>
<%-- ${mintime} ${hourstime} --%>
<%-- ${hoursmin} --%>
<%-- ${datenow} --%>
<%-- ${startmonths} --%>
<%-- ${datenow} --%>
<%-- ${department} --%>
<%-- ${departments} --%>
<%-- ${workall} --%>
<%-- ${logonUser} --%>
<%-- ${departmentList} --%>
<%-- ${position} --%>
<%-- ${work} --%>
<%-- 
${datenowcalendar} --%>
<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-clock-o"></i> <span
				class="caption-subject font-red sbold uppercase">Check list
				calendar </span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		<div class="portlet light portlet-fit bordered calendar">
			<div class="portlet-body">
				<p id="demo"></p>

				<div class="row">
					<div class="col-md-12">

						<div class="portlet light portlet-fit bordered calendar">
							<div class="portlet-title">
								<div class="caption">
									<i class=" icon-layers font-green"></i> <span
										class="caption-subject font-green sbold uppercase">Calendar
										New </span>
								</div>
							</div>
							<div class="portlet-body">
								<div class="row">
									<!-- <div class="col-md-3 col-sm-12">
										BEGIN DRAGGABLE EVENTS PORTLET
										<h3 class="event-form-title margin-bottom-20">Draggable
											Events</h3>
										<div id="external-events">
											<form class="inline-form">
												<input type="text" value="" class="form-control"
													placeholder="Event Title..." id="event_title" /> <br /> <a
													href="javascript:;" id="event_add" class="btn green">
													Add Event </a>
											</form>
											<hr />
											<div id="event_box" class="margin-bottom-10"></div>
											<label
												class="mt-checkbox mt-checkbox-single mt-checkbox-outline"
												for="drop-remove"> remove after drop <input
												type="checkbox" class="group-checkable" id="drop-remove" />
												<span></span>
											</label>
											<hr class="visible-xs" />
										</div>
										END DRAGGABLE EVENTS PORTLET
									</div> -->
									<!-- Calendar-->
									<div class="col-md-12 col-sm-12">
										<div id="calendar" class="has-toolbar"></div>
									</div>
									<div class="col-md-12 col-sm-12">
										<div id="calendar" class="has-toolbar"></div>
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
	</div>
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

				var start_date = [];
				var end_date = [];
				var name = [];
				var workhour = [];
				var workmin = [];
				var events1 = [];
				var events2 = [];
				var mycolor = [ 'red', 'grey', 'blue', 'yellow', 'green',
						'purple' ];

				<c:forEach var="work" items="${work}">
				var text = '${work.mycheckins}';
				var text2 = '${work.mycheckins}';
				var text3 = '${work.mycheckin}';
				var text4 = '${work.checkouttime}';
				var hour = '${work.hour}';
				var min = '${work.min}';

				var timecheck = text3.substring(0, 5);
				var timechecks = text4.substring(0, 5);
				var timefull = timecheck + " - " + timechecks;

				var date1 = new Date(text);
				var date2 = new Date(text2);
				start_date.push(date1);
				end_date.push(date2);
				name.push(timefull);
				workhour.push(hour);
				workmin.push(min);

				</c:forEach>
				var x;
				for (x in start_date) {
					events1.push({
						title : name[x],
						start : new Date(start_date[x]),
						end : new Date(end_date[x]),
						backgroundColor : App.getBrandColor('blue'),
						/* backgroundColor: App.getBrandColor(mycolor[x%6]), */
						allDay : true,
					});
				}
				for (y in start_date) {
					var z = parseInt(workhour[y]);

					if (z >= 8) {
						myary = "grey";
					} else {
						myary = "red";
					}
					events1.push({
						title : workhour[y] + " : " + workmin[y] + " "
								+ " hrs ",
						start : new Date(start_date[y]),
						end : new Date(end_date[y]),
						backgroundColor : App.getBrandColor(myary),
						allDay : true,
					});
				}

				var date = new Date();
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();
				var h = {};
				/* var test = "${logonUser}".trim(); */

				if (App.isRTL()) {
					if ($('#calendar').parents(".portlet").width() <= 720) {
						$('#calendar').addClass("mobile");
						h = {
							right : 'title, prev, next',
							center : '',
							left : 'agendaDay, agendaWeek, month, today'
						};
					} else {
						$('#calendar').removeClass("mobile");
						h = {
							right : 'title',
							center : '',
							left : 'agendaDay, agendaWeek, month, today, prev,next'
						};
					}
				} else {
					if ($('#calendar').parents(".portlet").width() <= 720) {
						$('#calendar').addClass("mobile");
						h = {
							left : 'title, prev, next',
							center : '',
							right : 'today,month,agendaWeek,agendaDay'
						};
					} else {
						$('#calendar').removeClass("mobile");
						h = {
							left : 'title',
							center : '',
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
					var html = $('<div class="external-event label label-default">'
							+ title + '</div>');
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
							slotMinutes : 15,
							editable : true,
							droppable : true, // this allows things to be dropped onto the calendar !!!

							drop : function(date, allDay) { // this function is called when something is dropped
								var check = '${checktimecalendar}'.trim();
								// retrieve the dropped element's stored Event Object
								var originalEventObject = $(this).data(
										'eventObject');
								// we need to copy it, so that multiple events don't have a reference to the same object
								var copiedEventObject = $.extend({},
										originalEventObject);

								// assign it the date that was reported
								copiedEventObject.start = date;
								copiedEventObject.allDay = allDay;
								copiedEventObject.className = $(this).attr(
										"data-class");

								// render the event on the calendar
								// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
								$('#calendar').fullCalendar('renderEvent',
										copiedEventObject, true);

								// is the "remove after drop" checkbox checked?
								if ($('#drop-remove').is(':checked')) {
									// if so, remove the element from the "Draggable Events" list
									$(this).remove();
								}
							}

							,
							/* viewRender: function (view , element){
									var b = $('#calendar').fullCalendar('getDate');
									//alert(b.format('L'));
									document.getElementById("test").value = b ;
									
								},  */
							eventLimit : true,
							events : events1

						});

			}

		};

	}();
	//var test = $('#test').val();
	var test = '${datenowcalendar}'.trim();

	jQuery(document).ready(function() {
		AppCalendar.init();
		/* $('.fc-next-button').click(function(){
			
			   alert("AA");
		 });
		$('.fc-prev-button').click(function(){
			//document.location = "next_month?timecalendar=" + test;
			document.location = "next_month?timecalendar=" + test;
		 }); */
		$('.fc-prev-button').click(function() {
			$.ajax({
				url : "next_month",
				method : "POST",
				type : "JSON",
				data : {
					"timecalendar" : test
				},
				success : function(data) {
					alert("Succress");

				}
			});
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




