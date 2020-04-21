<%@ taglib uri="/WEB-INF/tlds/permission.tld" prefix="perm"%>
<style>
#div_input {
	text-align: right;
	margin-bottom: 1%;
	margin-top: 2%;
	font-family: "Open Sans", sans-serif;
}
</style>
<!-- END THEME LAYOUT SCRIPTS -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.*,java.sql.*,java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-user font-red"></i> <span id="asdasd"
				class="caption-subject font-red sbold uppercase">&nbsp; USER
				LIST</span>
				<a class="btn btn-xs red" href="userAllReport"><i class="fa fa-pencil"></i> Print </a>
		</div>
		<perm:permission object="user.edit">
		<div class="actions right">
			<button type="button" class="btn green-jungle" id="addUser" onclick="add()">
				<i class="fa fa-plus"></i>&nbsp;Add User
			</button>
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
		</perm:permission>
	</div>
	<div class="test">
		<jsp:include page="/pages-back/test/radio_user_list2.jsp" flush="true"></jsp:include>
	</div>
</div>

<script>
	function add() {
		document.location = "user-add";
	}
</script>

<link href="../assets/global/plugins/bootstrap-toastr/toastr.min.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/bootstrap-toastr/toastr.min.js"
	type="text/javascript"></script>
<link href="../assets/global/css/components.min.css" rel="stylesheet"
	id="style_components" type="text/css" />


<script
	src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js"
	type="text/javascript"></script>
<script src="../assets/pages/scripts/ui-sweetalert.min.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />