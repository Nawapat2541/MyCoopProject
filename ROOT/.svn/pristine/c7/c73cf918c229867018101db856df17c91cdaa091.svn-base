<%@page import="org.apache.velocity.runtime.directive.Foreach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy" var="date_now" />
<fmt:formatDate type="date" value="${now}" pattern="31-03-yyyy" var="lastday"/>

<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-paperclip font-red"></i>
			<span id="title" class="caption-subject font-red sbold uppercase">Leave application form ( ${action} )</span>
		</div>
		<div class="actions">
			<a class="btn  btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form method="post" class="form-horizontal">
			<div class="form-body">

				<div class="form-group">
					<label class="col-md-3 control-label">Applicant :</label>
					<div class="col-md-6">
						<select id="user" name="user" class="form-control select2 select2me" required>
							<option></option>
							<optgroup id='u_enable' label="Enable"></optgroup>
							<optgroup id='u_disable' label="Disable"></optgroup>
                        </select>
                        <input hidden name="user_hidden" id="user_hidden" type="text">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Type of leave :</label>
					<div class="col-md-6">
						<div id="leaveTypes" class="md-radio-inline">
						</div>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">Duration :</label>
					<div class="col-md-6">
						<div class="input-group input-large date-picker input-daterange"
							data-date-format="dd-mm-yyyy">
							<input type="text" class="form-control" id="date_from" name="from" required>
							<span class="input-group-addon"> to </span>
							<input type="text" class="form-control" id="date_to" name="to" required>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Amount :</label>
					<div class="col-md-2">
						<input type="text" class="form-control" id="amount" name="amount" min="1" max="1000" maxlength="3" required>
					</div>
					<label class="col-md-1 control-label" style="text-align: left">Day</label>
					<div class="col-md-2">
						<select class="bs-select form-control" name="amount_sub" id="amount_sub">
							<option value="0" selected>0</option>
							<option value="0.125">1</option>
							<option value="0.25">2</option>
							<option value="0.375">3</option>
							<option value="0.5">4</option>
							<option value="0.625">5</option>
							<option value="0.75">6</option>
							<option value="0.875">7</option>
						</select>
					</div>
					<label class="col-md-1 control-label" style="text-align: left">Hours</label>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">half day leave :</label>
					<div class="col-md-6">
						<div class="md-radio-inline">
							<label class="mt-radio col-md-6">
								<input type="radio" name="halfDay" id="hd_1" value="1">Morning<span></span>
							</label>
							<label class="mt-radio col-md-6">
								<input type="radio" name="halfDay" id="hd_2" value="2">Afternoon<span></span>
							</label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Description :</label>
					<div class="col-md-6">
						<textarea style="word-break: break-all; white-space: normal;"
							maxlength="1024" class="form-control" rows="6" name="description" id="description"></textarea>
						<div class="form-control-focus"></div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Approvers :</label>
					<div class="col-md-6">
						<select id="approver" name="approver" class="form-control select2 select2me" required>
							<option></option>
                        </select>
                        <input hidden name="approver_hidden" id="approver_hidden" type="text">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Status :</label>
					<div class="col-md-6">
						<select class="form-control" id='status' name='status' required>
							<option value=0>Wait for approve</option>
							<option value=1>Approved</option>
							<option value=2>Reject</option>
						</select>
						<input hidden name="status_hidden" id="status_hidden" type="text">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Reason :</label>
					<div class="col-md-6">
						<textarea class="form-control" name="reason" id="reason" rows="3" maxlength="1024"></textarea>
						<div class="form-control-focus"></div>
					</div>
				</div>

			</div>
			<div class="form-actions">
				<div class="row">
					<div class="col-xs-12" style="text-align: center;">
						<button type="submit" class="btn blue"><i class="fa fa-send-o"></i> Submit</button>
						<button type="reset" class="btn red"><i class="fa fa-close"></i> Cancel</button>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>

<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>

<script>
	var toISODate = (date) => {
		return date.substring(6,10)+"-"+date.substring(3,5)+"-"+date.substring(0,2);
	}
	var toTimestamp = (date) => {
		return Date.parse(toISODate(date));
	}
	var toDisplayDate = (date) => {
		return date.toLocaleDateString('en-GB').replace('/','-').replace("/",'-');
	}
</script>

<!-- Start leaveType Radio -->
<c:if test="${leaveType != null}">
	<script>
		$(function(){
			var leaveTypes;
			leaveTypes = JSON.parse('${leaveType}');
			for(let i=0; i<leaveTypes.length; i++){
				if(leaveTypes[i].id != '4' && leaveTypes[i].id != '9'){
					let radio =	'<label class="mt-radio col-md-6">'
							+'<input type="radio" name="leaveType" onclick="checkamount()" id="lt_'+leaveTypes[i].id+'" value="'+leaveTypes[i].id+'" required>'+leaveTypes[i].name
							+'<span></span></label>';
					$('#leaveTypes').append(radio);
				}
			}

			$('input:radio[name="leaveType"]').change(function(){
				if( $(this).val() == '6' ){
					let lastday = '${lastday}';
					let lastday_ts = toTimestamp(lastday);
					let selectedDateTo = $('#date_to').val();
					let selectedDateTo_ts = toTimestamp(selectedDateTo);
					$('#date_to').datepicker('setEndDate',lastday);
					if(selectedDateTo_ts > lastday_ts){
						$('#date_to').val(lastday);
					}
				}
				else{
					$('#date_to').datepicker('setEndDate','');
				}
			})
		})
	</script>
</c:if>
<!-- End leaveType Radio -->

<c:if test="${leave == null}"><c:set var="leave" value="''"/></c:if>
<script>
    $(()=>{
        var userList = ${userList};
        var action = '${action}';
        var user;
        var manager;
        if(action == 'Edit'){
            var leave = ${leave};
            user = leave.userId;
            manager = leave.apprUserId;
            $('form').attr('action','LeaveEdit_Do');
        } else {
            user = "${onlineUser.id}";
            manager = "${onlineUser.managerId}";
            $('form').attr('action','LeaveAdd_Do');
        }
        user = user.toLowerCase();
        manager = manager.toLowerCase();

        /* Date from leave calendar */
        if('${date}' != '' && action=='Add'){
            $('#date_from').val('${date}');
			$('#date_to').val('${date}');
            $('#amount').val(1);
        }

        /* Start Applicant/Approver List */
        for(let i=0; i<userList.length; i++){
            let id = userList[i].id.toLowerCase();
            let name = userList[i].name;
            let status = userList[i].enable;
            let option = '<option value="'+id+'">'+name+'</option>';

            if(status == '1'){
                $('#u_enable').append(option);
                $('#approver').append(option);
            }
            else{
                $('#u_disable').append(option);
            }
        }
        $('#user').val(user);
        $('#user').trigger('change');
        $('#approver').val(manager);
        $('#approver').trigger('change');
        $('#user').change(()=>{
            let val = $('#user').val();
            for(let i=0; i<userList.length; i++){
                let user = userList[i].id.toLowerCase();
                let mng = userList[i].manager;
                if( user == val ){
                    $('#approver').val(mng.toLowerCase());
                    $('#approver').trigger('change');
                }
            }
        })
        /* End Applicant/Approver List */

        /* Start Leave Edit init */
        if(leave != null){
			var noDay = leave.noDay.toString().split(".");
			var amount = noDay[0];
			var amount_sub = '0.'+noDay[1];
			if(isNaN(amount_sub)){ amount_sub = 0;}
			$('#date_from').val(toDisplayDate(new Date(leave.startDate)));
			$('#date_to').val(toDisplayDate(new Date(leave.endDate)));
			$('#amount').val(amount);
			$('#amount_sub').val(amount_sub);
			$('#description').html(leave.description);
			$('#status').val(leave.leaveStatusId);
			$('#reason').html(leave.reason);
			$('#lt_'+leave.leaveTypeId).prop('checked','checked');
			if(leave.halfDay != 0) { $('#hd_'+leave.halfDay).prop('checked','checked'); }
        }
        /* End Leave Edit init */
    })
</script>

<!-- Start check authority -->
<c:if test="${!userAuthority.contains('leave.approve')}">
	<script>
        $(()=>{
            $('#status').attr('disabled',true);
		    $('#status_hidden').val($('#status').val());
		    $('#status').change(() => {
			    $('#status_hidden').val($('#status').val());
		    });
        })
	</script>
</c:if>
<c:if test="${!userAuthority.contains('leave.viewall')}">
	<script>
        $(()=>{
            $('#user').attr('disabled',true);
		    $('#user_hidden').val($('#user').val());
		    $('#user').change(() => { $('#user_hidden').val($('#user').val()); });

            $('#approver').attr('disabled',true);
		    $('#approver_hidden').val($('#approver').val());
		    $('#approver').change(() => { $('#approver_hidden').val($('#approver').val()); });
        })
	</script>
</c:if>
<!-- End check authority -->

<!-- Start datepicker -->
<c:if test="${holiday!=null}">
	<script>
		$(function(){
			var holiday;
			var holidays = [];
			holiday = JSON.parse('${holiday}');

			for(let i=0;i<holiday.length;i++){
				let start = new Date(holiday[i].start);
				let end = new Date(holiday[i].end);
				for(let j=start; j<=end; j.setDate(j.getDate()+1)){
					holidays.push(toDisplayDate(j));
				}
			}
		
			$('#date_from').datepicker({
				format: 'dd-mm-yyyy',
				daysOfWeekDisabled: [0,6],
				datesDisabled: holidays ,
				autoclose: true, 
			});
			$('#date_to').datepicker({
				format: 'dd-mm-yyyy',
				daysOfWeekDisabled: [0,6],
				datesDisabled: holidays ,
				autoclose: true, 
			});

			$('.input-daterange').change(function(){
				let amount = 0;
				let holiday_count = 0;
				let from = new Date( toISODate( $('#date_from').val() ) );
				let to = new Date( toISODate( $('#date_to').val() ) );
				if(from == to){
					amount = 1;
				}
				if(from < to){
					amount = ((to-from)/86400000)+1;
					for(let i=from; i<to; i.setDate(i.getDate()+1)){
						for(let j=0; j<holidays.length; j++){
							let holiday_ts = toTimestamp(holidays[j]);
							if(i.getTime() == holiday_ts){
								holiday_count++;
							}
						}
						if(i.getDay() == '0' || i.getDay() == '6'){
							holiday_count++;
						}
					}
					amount -= holiday_count;
				}
				else{
					amount = 1;
				}
				$('#amount').val(amount);
			});
		});
	</script>
</c:if>
<!-- End datepicker -->

<!--  alert leave type -->
<script>
function checkamount() {
	if (document.getElementById('amount').value == "0"){
		alert("Amount of day going to change to 1 day \n'Please Check Amount of Day Again'");
	}
}
</script>
