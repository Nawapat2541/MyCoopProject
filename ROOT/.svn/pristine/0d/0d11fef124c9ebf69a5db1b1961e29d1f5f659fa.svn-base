<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- ${user} --%>
<style>
.success {
	color: #26c281;
}

.error {
	color: red;
}
</style>
<%
	String massage = request.getParameter("massage");
	if (massage == null) {
		massage = "";
	} else {
		massage = "รหัสผ่านไม่ถูกต้อง โปรดกรองใหม่อีกครั้ง";
	}
%>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-exchange font-red"></i> <span
				class="caption-subject font-red sbold uppercase"> Change
				Password</span>
		</div>
	</div>
	<div class="portlet-body form">
		<!-- Begin New from -->
		<div class="portlet-body" style="margin-right: 2%; margin-left: 2%;">
			<!-- BEGIN FORM-->
			<form action="user-perform-changepass" class="form-horizontal" method="post" autocomplete="off">
				<div class="form-group form-md-line-input  ">
					<div class="caption caption col-md-2"></div>
					<div class="caption caption col-md-2">
						Old Password :<br>
					</div>
					<div class="caption col-md-4">
						<input type="hidden" value="${user.id}" name="user.id"> <input
							type="hidden" value="${user.password}" name="oldpass"> <input
							type="password" class="form-control" name="oldpass_i"
							placeholder="" value="" autocomplete="off" required>
						<div class="form-control-focus"></div>
					</div>
					<div class="caption col-md-4">
						<p  class="error"><%=massage%></p>
					</div>
				</div>
				<div class="form-group form-md-line-input  ">
					<div class="caption caption col-md-2"></div>
					<div class="caption caption col-md-2">
						New Password :<br>
					</div>
					<div class="caption col-md-4">
						<input type="password" class="form-control" placeholder=""
							value="" name="newpass" id="newpass" autocomplete="off" required>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input  ">
					<div class="caption caption col-md-2"></div>
					<div class="caption caption col-md-2">
						Confirm Password :<br>
					</div>
					<div class="caption col-md-4">
						<input type="password" class="form-control" id="conpass"
							oninput="myFunction()" required>
						<div class="form-control-focus"></div>
					</div>
					<div class="caption col-md-4">
						<p id="demo"></p>
					</div>
				</div>
		</div>

		<div class="note note-info">
			<p style="text-align: justify; color: #8294a6;">
				<b style="margin-left: 10%;">คำแนะนำ :</b> สร้างรหัสผ่านอย่างน้อย 8
				ตัวอักษร ที่ประกอบไปด้วย อักษรภาษาอังกฤษ ตัวเลข และอักขระพิเศษ เช่น
				#,\,/,@
			</p>
			<br>
			<p style="text-align: justify; color: #8294a6;">
				<b style="margin-left: 10%;">ตัวอย่างรหัสผ่านที่ปลอดภัย :</b>เช่น
				yourname@1234
			</p>
		</div>
		<div class="form-actions action right">
			<div class="row ">
				<div class="col-md-12">
					<center>
						<button type="submit" class="btn blue" id="submit">
							<i class="fa fa-save"></i>&nbsp;Save
						</button>
						<button type="button" class="btn red">
							<i class="fa fa-times-circle"></i> Cancel
						</button>
					</center>
				</div>
			</div>
		</div>
		</form>
	</div>
	<!-- End New From -->
</div>
<script>
	function myFunction() {
		var newpass = document.getElementById("newpass").value;
		var conpass = document.getElementById("conpass").value;
		var value = document.getElementById("demo").value;
		if (newpass == conpass) {
			document.getElementById("demo").innerHTML = "รหัสผ่านตรงกัน";
			document.getElementById("submit").type = "submit";
			if (newpass == conpass) {
				document.getElementById("demo").className = "success";
			}
		}
		if (newpass != conpass) {
			document.getElementById("demo").innerHTML = "รหัสผ่านไม่ตรงกัน โปรดกรอกใหม่อีกครั้ง";
			document.getElementById("submit").type = "button";
			if (newpass != conpass) {
				document.getElementById("demo").className = "error";
			}
		}

	}
</script>
<!--  -->
