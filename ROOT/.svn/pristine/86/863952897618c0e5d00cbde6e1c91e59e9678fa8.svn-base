<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-paperclip font-red"></i>
            <span id="title" class="caption-subject font-red sbold uppercase">Borrow Equipment</span>
        </div>
        <div class="actions">
            <a class="btn  btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
            </a>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form method="post" class="form-horizontal">
            <div class="form-body">

                <div class="form-group" id="createDateDiv" style="display:none">
                    <label class="col-md-3 control-label">Create Date :</label>
                    <div class="col-md-6">
                        <input type="text" name="createDate" id="createDate" class="form-control" disabled>
                        <div class="form-control-focus"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Borrower :</label>
                    <div class="col-md-6">
                        <select id="user" name="user" class="form-control select2 select2me" required>
                            <option></option>
                            <optgroup id='u_enable' label="Enable"></optgroup>
                            <optgroup id='u_disable' label="Disable"></optgroup>
                        </select>
                        <input name="user_hidden" id="user_hidden" type="hidden">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Equipment :</label>
                    <div class="col-md-6">
                        <select id="equipment" name="equipment" class="form-control select2 select2me" required>
                            <option></option>
                        </select>
                        <input hidden name="equipment_hidden" id="equipment_hidden" type="text">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Duration :</label>
                    <div class="col-md-6">
                        <div class="input-group input-large date-picker input-daterange" data-date-format="dd-mm-yyyy">
                            <input type="text" class="form-control" id="date_from" name="date_from" required>
                            <span class="input-group-addon"> to </span>
                            <input type="text" class="form-control" id="date_to" name="date_to">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Reason :</label>
                    <div class="col-md-6">
                        <input type="text" name="reason" id="reason" maxlength="250" class="form-control" required>
                        <div class="form-control-focus"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Location :</label>
                    <div class="col-md-6">
                        <textarea style="word-break: break-all; white-space: normal;" maxlength="1024"
                            class="form-control" rows="2" name="location" id="location" required></textarea>
                        <div class="form-control-focus"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Contact address :</label>
                    <div class="col-md-6">
                        <textarea style="word-break: break-all; white-space: normal;" maxlength="1024"
                            class="form-control" rows="2" name="contact" id="contact"></textarea>
                        <div class="form-control-focus"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Status :</label>
                    <div class="col-md-6">
                        <select class="form-control" id="status" name="status" required>
                            <option value="W">Wait for approve</option>
                            <option value="B">Borrowing</option>
                            <option value="R">Return</option>
                            <option value="C">Cancel</option>
                        </select>
                        <input name="status_hidden" id="status_hidden" type="hidden">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Remark :</label>
                    <div class="col-md-6">
                        <textarea style="word-break: break-all; white-space: normal;" maxlength="1024"
                            class="form-control" rows="2" name="remark" id="remark"></textarea>
                        <div class="form-control-focus"></div>
                    </div>
                </div>

            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-xs-12" style="text-align: center;">
                        <button type="submit" class="btn blue"><i class="fa fa-send-o"></i> Submit</button>
                        <button onclick="goBack()" class="btn red"><i class="fa fa-close"></i> Cancel</button>
                    </div>
                </div>
            </div>
        </form>
        <!-- END FORM-->
    </div>
</div>

<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script>
    var goBack = () => {
        window.history.back();
    }
    $(() => {
        var userList = [];
        userList = ${userList};
        var equipments = [];
        equipments = JSON.parse('${equipments}');
        var eType = [];
        eType = JSON.parse('${type}');

        /* Start user List */
        for (let i = 0; i < userList.length; i++) {
            let id = userList[i].id.toLowerCase();
            let name = userList[i].name;
            let status = userList[i].enable;
            let option = '<option value="' + id + '">' + name + '</option>';

            if (status == '1') {
                $('#u_enable').append(option);
            }
            else {
                $('#u_disable').append(option);
            }
        }
        //
        eType.forEach((t)=>{
            let optgroup = "<optgroup id='t_"+t.TypeID+"' label='"+t.description+"'></optgroup>";
            $('#equipment').append(optgroup);
        })
        equipments.forEach((e)=>{
            let option = '<option value="' + e.equipmentId + '">' + e.itemNo + " : " + e.name + '</option>';
            $('#t_'+e.type).append(option);
        });
    })
</script>

<c:choose>

    <c:when test="${borrow != null}">
        <script>
            $(()=>{
                $('form').prop('action','eBorrowUpdate');
                var borrow = JSON.parse('${borrow}');

                $('#createDateDiv').css("display","block");
                $('#createDate').val(borrow.timeCreate);
                $('#user').val(borrow.userBorrowid.toLowerCase());
                $('#equipment').val(borrow.equipmentId);
                $('#equipment').prop("disabled",true);
                $('#equipment_hidden').val($('#equipment').val());
                if(borrow.dateStart){
                    $('#date_from').val(
                        new Date(borrow.dateStart).toLocaleDateString("en-GB").replace("\/","-").replace("\/","-")
                    );
                }
                if(borrow.dateEnd != undefined){
                    $('#date_to').val(
                        new Date(borrow.dateEnd).toLocaleDateString("en-GB").replace("\/","-").replace("\/","-")
                    );
                }
                $('#reason').val(borrow.reason);
                $('#location').html(borrow.location);
                $('#contact').html(borrow.contactAddr);
                $('#remark').html(borrow.remark);
                $('#status').val(borrow.status);
            })
        </script>
    </c:when>

    <c:otherwise>
        <script>
            $(()=>{
                $('form').prop('action','eBorrowAdd');
                let user = '${onlineUser.id}';
                $('#user').val(user.toLocaleLowerCase());
            })
        </script>
    </c:otherwise>

</c:choose>

<c:if test="${!userAuthority.contains('borrow.approved')}">
    <script>
        $(()=>{
            $('#remark').prop('readonly', true);

            $('#status').attr('disabled',true);
            $('#status_hidden').val($('#status').val());
            $('#status').change(() => {
                $('#status_hidden').val($('#status').val());
            });

            $('#user').attr('disabled',true);
            $('#user_hidden').val($('#user').val());
            $('#user').change(() => {
                $('#user_hidden').val($('#user').val());
            });
        })
    </script>
</c:if>

<script>
$(()=>{
    $('#date_from').datepicker({
	    format: 'dd-mm-yyyy',
		autoclose: true, 
	});
	$('#date_to').datepicker({
		format: 'dd-mm-yyyy',
		autoclose: true, 
	});
})
</script>

<c:if test="${eId != null}">
    <script>
        $(()=>{
            $('#equipment').val('${eId}');
        })
    </script>
</c:if>