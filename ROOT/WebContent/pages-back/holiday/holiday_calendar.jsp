<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="../assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->


	
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        
        <script src="../assets/global/plugins/moment.min.js" type="text/javascript"></script>

   <!--    <script src="../assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>  -->
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
     <!--    <script src="../assets/global/scripts/app.min.js" type="text/javascript"></script>
 -->        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<!--         <script src="../assets/apps/scripts/calendar.min.js" type="text/javascript"></script> -->
        <!-- END PAGE LEVEL SCRIPTS -->
<style>
.fc-sat, .fc-sun {
    background-color:#E9EDEF;
}
.fc-day-grid-event > .fc-content {
    white-space: normal;
    
}

.red{
     background:#F3565D ;   
}
.blue{
     background:#89C4F4 ;   
}
.green{
     background:#1bbc9b ;   
}
.purple{
     background:#9b59b6 ;   
}
.grey{
     background:#95a5a6 ;   
}
.yellow{
     background:#F8CB00 ;   
}


</style>
	
	     
        
<c:set var="now" value="<%=new java.util.Date()%>" />

<div class="portlet light bordered">
	<div class="portlet-title">

		<div class="caption">
			<i class="fa fa-calendar-check-o font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Holiday Calendar</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body">
		
			<div class="portlet-body">


				<div class="row">
                        <div class="col-md-12">
                            <div class="portlet light portlet-fit bordered calendar">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class=" icon-layers font-green"></i>
                                        <span class="caption-subject font-green sbold uppercase">Holiday </span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12">
                                            <div id="calendar" class="has-toolbar"> </div>
                                        </div>
                                        <!-- Calendar-->

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END PAGE BASE CONTENT -->
				
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
var myevent = [];
var AppCalendar = function() {

    return {
        //main function to initiate the module
        init: function() {
            this.initCalendar();
        },

        initCalendar: function() {

            if (!jQuery().fullCalendar) {
                return;
            }
            var date_time = '${flag12}';
            if(date_time != ""){           
            var noTime = $.fullCalendar.moment(date_time,"YYYY-MM-DD");
             }else{ var noTime = moment(); }        
            var start_date = [];
            var end_date = [];
            var name =[];
            var events1 =[];
            var des = [];
            var id =[];
            var mycolor =['red','grey','blue','yellow','green','purple'];
            var mytype=[1,2,3,4,5,6];
            <c:forEach var="holiday" items="${holidayList}">
            
            var text = '${holiday.start_date}';
            var text2 =  '${holiday.end_date}';
            var text3 = '${holiday.head}';
            
            <c:set var = "string1" value = "${holiday.description}"/>
            	<% pageContext.setAttribute("newline", "\r\n"); %>
            <c:set var = "string2" value = "${fn:replace(string1,newline,'')}" />
            
            var text4 = '${string2}';
            
            var text5 =  '${holiday.id_date}';
            var date1 = new Date(text);
            var date2 = new Date(text2);
            
            start_date.push(date1);
            end_date.push(date2);
            name.push(text3);
            des.push(text4);
            id.push(text5);
            </c:forEach>
            var x;
           
          
            for(x in start_date){
            events1.push(	{
				   id:id[x],
            	   title: name[x],
				   start: new Date(start_date[x].getFullYear(),start_date[x].getMonth(),start_date[x].getDate()),
				   end: new Date(end_date[x].getFullYear(),end_date[x].getMonth(),end_date[x].getDate() + 1 ),
				   description: des[x] ,
			   backgroundColor: App.getBrandColor(mycolor[x%6]),
			   className: mytype[x%6],
               allDay: true,
			   });            
            }
            myevent = events1;
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            
        
            var h = {};

            if (App.isRTL()) {
                if ($('#calendar').parents(".portlet").width() <= 720) {
                    $('#calendar').addClass("mobile");
                    h = {
                       right: 'title, prev, next',
                        center: '',
                        left: 'agendaDay, agendaWeek, month, today,day'
                    };
                } else {
                    $('#calendar').removeClass("mobile");
                    h = {
                        right: 'title',
                        center: '',
                        left: 'agendaDay, agendaWeek, month, today, prev,next'
                    };
                }
            } else {
                if ($('#calendar').parents(".portlet").width() <= 720) {
                    $('#calendar').addClass("mobile");
                    h = {
                        left: 'title, prev, next',
                        center: '',
                        right: 'today,month,agendaWeek,agendaDay'
                    };
                } else {
                    $('#calendar').removeClass("mobile");
                    h = {
                        left: 'title',
                        center: '',
                        right: 'prev,next,today,month,agendaWeek,agendaDay'
                    };
                }
            }
            

            var initDrag = function(el) {
            	
                // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                // it doesn't need to have a start or end
                var eventObject = {
                    title: $.trim(el.text()) // use the element's text as the event title
                };
                // store the Event Object in the DOM element so we can get to it later
                el.data('eventObject', eventObject);
                // make the event draggable using jQuery UI
                
                el.draggable({
                    zIndex: 999,
                    revert: true, // will cause the event to go back to its
                    revertDuration: 0 //  original position after the drag
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
            
            
 
            $('#calendar').fullCalendar('destroy'); // destroy the calendar
            $('#calendar').fullCalendar({ //re-initialize the calendar
                header: h,
                defaultView: 'month', // change default view with available options from http://arshaw.com/fullcalendar/docs/views/Available_Views/ 
                defaultDate: moment(noTime),
                slotMinutes: 15,
                editable: true,
                droppable: true, // this allows things to be dropped onto the calendar !!!
             
                drop: function(date, allDay) { // this function is called when something is dropped
                	

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
                
             	selectable: true,
    			selectHelper: true,
                eventRender: function(calEvent, element){
                	
                	
                	//Set Background
                   	  var type_color = "";
                	  var start = moment(calEvent.start).get('date');
                      var end = moment(calEvent.end).get('date');                     
                   	 switch(calEvent.className) {
                	 case 1: type_color = 'red';  break;
                	 case 2:  type_color ='grey'; break;
                	 case 3: type_color ='blue'; break;
                	 case 4: type_color ='yellow'; break;
                	 case 5: type_color ='green'; break;
                	 case 6: type_color ='purple'; break;
                    
                	 }
                      var for_i = end-start;                      
                      for(var i  = 0 ; i < for_i ; i++ ){
                  	var dataToFind = moment(calEvent.start).format('YYYY-MM-DD');
                   var date_new = new Date(dataToFind);
                    date_new.setDate(start+i);
                   dataToFind = moment(date_new).format('YYYY-MM-DD');
                      $("td[data-date='"+dataToFind+"']").addClass(type_color);	
                      }	
                	var moment1 = moment(calEvent.start);
                	var moment2 = moment(calEvent.end);             	
                    element.popover({
                        animation:true,
                        placement: 'bottom',
                        container:"body",
                        html:true,
                        delay: 100,
                        title: '<b>'+calEvent.title +'</b>',
                        content:   '<b>' +$.fullCalendar.formatRange(moment1, moment2 -1 , 'D MMMM YYYY') + "</b><br/>"
                        + "<p style=\"max-width:500px;word-wrap:break-word;margin-bottom:50px;\"> &nbsp; "+ calEvent.description + " <br/>"
                        
                       + "</p>",                 
                        trigger: 'hover'
                    });
                          
                        element.find(".fc-title").prepend("<i class='fa fa-search searchon' style='margin:5px;' ></i>");                    
                        element.find("div.fc-content").append( "<span> <i class='fa fa-trash closeon' style='float:right;position:absolute;right:0;top:0'></i> </span>" );
                       
                    element.find(".closeon").on('click',function() {                //Delete Function
                    	swal({
                    		  title: "Are you sure?",
                    		  text: "Your will not be able to recover this imaginary file!",
                    		  type: "warning",
                    		  showCancelButton: true,
                    		  confirmButtonClass: "btn-danger",
                    		  confirmButtonText: "Yes, delete it!",
                    		  closeOnConfirm: false
                    		},
                    		function(){
                    			swal({ 
          						  title: "Deleted!",
          						   text: "Your imaginary file has been deleted.",
          						    type: "success" 
          						  },
          						  function(){
          							document.location = "DeleteHoliday_calendar?id=" + calEvent.id +"&date=" + calEvent.start.format("DD-MM-YYYY");
          						});
                    		});

                    });
                    element.find(".searchon").on('click',function() { //Search Function
                    	document.location = "holiday_edit_calendar?flag=0&id=" + calEvent.id ;                     	  
                    	                  });
                    
                  },
                 
                  dayClick: function(date, jsEvent, view) {
                		 var events = $('#calendar').fullCalendar('clientEvents');
                         for (var i = 0; i < events.length; i++) {
                             if (moment(date).isSame(moment(events[i].start))) {
                                 alert("Dont Click");
                                 break;
                             }
                             else if (i == events.length - 1) {
                            	 document.location = "holiday_add?flag=0&date_cal=" + date.format("DD-MM-YYYY") ;
                             }
                         }

                	 
                         
                  },
                   eventDrop: function(event, delta, revertFunc) { // ลากเพื่อแก้ไข
                		var moment2 = moment(event.end);                		
                		 var day = moment(moment2).get('date') - 1;
                		  var mon =  moment(moment2).get('month') ;
                			 moment2 = moment().set({'date':day,'month':mon}); 
                			 
                			 var moment3 = moment(event.start);                		
                    		 var day = moment(moment3).get('date') - 1;
                    		  var mon =  moment(moment3).get('month') ;
                    			 moment3 = moment().set({'date':day,'month':mon}); 	 
                			 
                			 

                      if (confirm("Are you sure about this change?")) {                         
                         document.location ="UpdateMoving_calendar?id_date=" +event.id +"&Date-Start="+event.start.format("DD-MM-YYYY")+"&Date-End="+moment2.format("DD-MM-YYYY")  ;                        
                      }
                  },
               
                  eventResize: function(event, delta, revertFunc) {

                	  var moment2 = moment(event.end);
              		  var day = moment(moment2).get('date') - 1;
              		  var mon =  moment(moment2).get('month') ;
              		//  var yea = moment(moment2).get('year') ;
              
              		 moment2 = moment().set({'date':day,'month':mon}); 
             
            
                          if (confirm("Are you sure Update ?")) {
                       	document.location = "UpdateHoliday_calendar?id_date="+event.id +"&date_end="+moment2.format("DD-MM-YYYY");
                         }   
                     }, 

                editable: true,
                eventLimit: true,
                events: events1,
                
              
       
             });
            


        }

    };

}();

jQuery(document).ready(function() {    
   AppCalendar.init();

   
   var count_month = '${num_month}';
   var count_year = '${num_year}';
  
   var a = parseInt(count_year);
   var b = parseInt(count_month);
   
   var events2 = [];
   $('.fc-next-button').click(function(){
	   b ++;
	
	   if(b == 12  ){	  
	   alert('next year :'+ (a+1) );  
	 // alert(myevent);
       	$.ajax({
			url : "holiday_findnextYear",
			method : "POST",
			type : "JSON",
			data : {
				"year_next" : a+1
			},
			success : function(data) {				
				var obj = JSON.parse(data);
				var i = 0 ;
		         var mycolor =['red','grey','blue','yellow','green','purple'];
		            var mytype=[1,2,3,4,5,6];
				for(i in obj.id) {
			        var start_date = new Date(obj.start[i]);
		            var end_date = new Date(obj.end[i]);
					//alert(start_date.getFullYear());
		             events2.push({
						   id: obj.id[i],
		            	   title: obj.title[i],
		            	   start: new Date(start_date.getFullYear(),start_date.getMonth(),start_date.getDate()),
						   end: new Date(end_date.getFullYear(),end_date.getMonth(),end_date.getDate() + 1 ),						  					 
		                   allDay: true,
		                   description: obj.des[i] ,
		                   className: mytype[i%6],
		                   backgroundColor: App.getBrandColor(mycolor[i%6]),
					   }); 									 
				}
				 $('#calendar').fullCalendar( 'removeEventSource', myevent );
				 $('#calendar').fullCalendar( 'addEventSource', events2 );
				
			}
		});              	   	           
       	b = 0 ;
       	a = a + 1 ;
	   }	  
	});
   $('.fc-prev-button').click(function(){
	 b--;	 
	 if(b == -1 ){	  
		   alert('next year :'+ (a-1) );	            
	       	$.ajax({
				url : "holiday_findnextYear",
				method : "POST",
				type : "JSON",
				data : {
					"year_next" : a-1
				},
				success : function(data) {				
					var obj = JSON.parse(data);
					var i = 0 ;
			         var mycolor =['red','grey','blue','yellow','green','purple'];
			            var mytype=[1,2,3,4,5,6];
					for(i in obj.id) {
				        var start_date = new Date(obj.start[i]);
			            var end_date = new Date(obj.end[i]);
			             events2.push({
							   id: obj.id[i],
			            	   title: obj.title[i],
			            	   start: new Date(start_date.getFullYear(),start_date.getMonth(),start_date.getDate()),
							   end: new Date(end_date.getFullYear(),end_date.getMonth(),end_date.getDate() + 1 ),							  					 
			                   allDay: true,	
			                   description: obj.des[i] ,
			                   className: mytype[i%6],
			                   backgroundColor: App.getBrandColor(mycolor[i%6]),
						   }); 									 
					}	
					 $('#calendar').fullCalendar( 'removeEventSource', myevent );
					 $('#calendar').fullCalendar( 'addEventSource', events2 );
				}
			});  	                      
	       	 b = 11;
	       	a = a - 1  ;
		   }	 	 
	});

});
</script>
	<link href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
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
				
		
	   		
		