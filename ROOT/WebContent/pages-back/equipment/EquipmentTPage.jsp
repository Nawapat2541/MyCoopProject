<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-list font-red"></i> 
			<span class="caption-subject font-red sbold uppercase">Equipment Type </span>
		</div>
		<div class="actions"></div>
	</div>
	<div class="portlet-body form">
		<form id="form" class="form-horizontal" action="SaveType">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-3 control-label">Type :</label>
					<div class="col-md-6">
						<input id="Type" name="Type" type="text" class="form-control" maxlength="3">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Description :</label>
					<div class="col-md-6">
						<input id="description" name="description" type="text" class="form-control" maxlength="45">
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 text-center">
						<button type="submit" class="btn blue"><i class="fa fa-send-o"></i> Submit</button>
						<button type="button" class="btn red" onclick="back()"><i class="fa fa-close"></i> Cancel</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript">
</script>
	
<script>
	function back() {
		document.location = "EquipmentType";
	}
</script>

<c:if test="${info != null}">
	<script>
    $(()=>{
    	
        $('#form').prop("action","UpdateType");
        var info = {};
        info = JSON.parse('${info}');
        $('#Type').val(info.TypeID);
        $('#description').val(info.description);
    })
</script>

</c:if>
<!-- test -->
<c:if test="${save != null}">
	<script>
    $(()=>{
        $('#form').prop("action","SaveType");
        var info = {};
        info = JSON.parse('${save}');
        $('#Type').val(info.TypeID);
        $('#description').val(info.description);
        
        swal("Duplicate Type!", "Plese, use new type.") 
		document.getElementById("back").type = "button";
    })
</script>
</c:if>
<script src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
