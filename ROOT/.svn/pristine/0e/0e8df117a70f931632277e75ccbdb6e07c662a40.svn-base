<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="portlet light ">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-list font-red"></i> <span class="caption-subject font-red sbold uppercase">Equipment Status
            </span>
        </div>
        <div class="actions">
        </div>
    </div>
    <div class="portlet-body form">
        <form id="form" class="form-horizontal" action="eStatusSave">

            <div class="form-body">
                <div class="form-group">
                    <label class="col-md-3 control-label">Status :</label>
                    <div class="col-md-6">
                        <input id="status" name="status" type="text" class="form-control" maxlength="3">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Description :</label>
                    <div class="col-md-6">
                        <input id="description" name="description" type="text" class="form-control" maxlength="45">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Color :</label>
                    <div class="col-md-6">
                        <select name="color" id="color" class="form-control"></select>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-12 text-center" id="preview">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn blue"><i class="fa fa-send-o"></i> Submit</button>
                        <button type="button" class="btn red"><i class="fa fa-close"></i> Cancel</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script>
    var colors = ["default", "red", "red-mint", "blue", "blue-steel", "green", "green-jungle", "yellow", "yellow-lemon", "purple", "purple-seance", "grey-mint", "dark"];
</script>

<script>
    $(() => {
        colors.forEach((c) => {
            $('#color').append("<option value='" + c + "'>" + c + "</option>");
        })

        var preview = () => {
            let des = $('#description').val();
            let c = $('#color').val();
            $('#preview').html("<span class='btn " + c + "'>" + des + "</span>");
        }

        $('#color').change(() => { preview(); });
        $('#description').keyup(() => { preview(); });
    })
</script>

<c:if test="${info != null}">
<script>
    $(()=>{
        $('#form').prop("action","eStatusUpdate");
        var info = {};
        info = JSON.parse('${info}');
        $('#status').val(info.statusId);
        $('#description').val(info.description);
        $('#color').val(info.color);
        preview();
    })
</script>
</c:if>