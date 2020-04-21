<meta name="viewport"
	content="width=device-width, initial-scale=1.0,  minimum-scale=1.0">
<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="js/jquery.datetimepicker.css">
<link
	href="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="../assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/css/components.min.css" rel="stylesheet"
	id="style_components" type="text/css" />
<link href="../assets/global/css/plugins.min.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/pages/css/lock-2.min.css" rel="stylesheet"
	type="text/css" />
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" type="text/css" href="../assets/global/font/stylesheet.css">

<!-- START STYLE -->
<style>
body {
	background: black;
	font-family: 'rsuregular', sans-serif !important;
	
}

.page-lock .page-body {
    background-color: rgba(0, 0, 0, 0.4)!important;
    }
h1 {
	padding-top: 30%;
}

img {
	padding-top: 15%;
}

.clock {
	color: white;
	font-size: 40px;
	letter-spacing: 7px;
}

.text {
	font-size: 35px;
}
</style>
<!-- END STYLE -->
<body>
	<div class="page-lock">
		<div class="row page-body">
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
			<a href="QRCode_form?qrID=${qrIdCheck}">
				<img class="cube" id="qrimg" src="upload/QRCode.png" width="98%">
				</a>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<p class="text-center clock text">SCAN FOR CHECK</p>
				<br> <br>
				<div id="MyClockDisplay" class="clock text-center"
					onload="showTime()"></div>
			</div>
			<br> <br> <br>
		</div>
	</div>
	<script>

		function showTime() {
			var date = new Date();
			var h = date.getHours(); // 0 - 23
			var m = date.getMinutes(); // 0 - 59
			var s = date.getSeconds(); // 0 - 59
			var session = "AM";

			if (h == 0) {
				h = 12;
			}

			if (h > 12) {
				h = h - 12;
				session = "PM";
			}

			h = (h < 10) ? "0" + h : h;
			m = (m < 10) ? "0" + m : m;
			s = (s < 10) ? "0" + s : s;

			var time = h + ":" + m + ":" + s + " " + session;
			document.getElementById("MyClockDisplay").innerText = time;
			document.getElementById("MyClockDisplay").textContent = time;

			$("#qrimg").attr({ "src": "upload/QRCode.png?dummy="+date });
			
			setTimeout(showTime, 1000);

		}

		showTime();
	</script>

	<!-- BEGIN CORE PLUGINS -->
	<script src="../assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/js.cookie.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/backstretch/jquery.backstretch.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/scripts/app.min.js"
		type="text/javascript"></script>
	<script src="../assets/pages/scripts/lock-22.min.js"
		type="text/javascript"></script>