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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<head>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet"
        type="text/css" />
    <link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet"
        type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="../assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="../assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="../assets/pages/css/lock-2.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <!-- END THEME LAYOUT STYLES -->
    <link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->

<style>
    label {
        color: white;
    }

    .box {
        border: 2px solid white !important;
    }

    .md-radio label>.check {
        background: yellow;
    }

    .clock {

        color: white;
        font-size: 30px;
        letter-spacing: 7px;
        padding: 8px;
    }


    .block {
        background-color: rgba(255, 255, 255, 0.3);
        float: left;
        padding-bottom: 5%;
        width: auto;
        text-align: center;

    }

    @media screen and (max-width: 768px) {
        .block {
            width: -webkit-fill-available;
        }
    }
    .page-lock .page-body {
        width: auto;
        background-color: rgba(0, 0, 0, 0.4)!important;
    }
	.page-lock .page-lock-info input {
   	 	width: inherit!important;
   	 	text-align:center;
	}
    .circle-loader {
  margin-bottom: 3.5em;
  border: 2px solid rgba(0, 0, 0, 0.2);
  border-left-color: #5cb85c;
  animation: loader-spin 1.2s infinite linear;
  position: relative;
  display: inline-block;
  vertical-align: top;
  border-radius: 50%!important;
  width: 7em;
  height: 7em;

}
	
	.load-complete {
	  -webkit-animation: none;
	  animation: none;
	  border-color: yellow;
	  transition: border 500ms ease-out;
	}
	
	.checkmark {
	  display: none;
	}
	.checkmark.draw:after {
	  animation-duration: 800ms;
	  animation-timing-function: ease;
	  animation-name: checkmark;
	  transform: scaleX(-1) rotate(135deg);
	}
	.checkmark:after {
	  opacity: 1;
	  height: 3.5em;
	  width: 1.75em;
	  transform-origin: left top;
	  border-right: 3px solid yellow;
	  border-top: 3px solid yellow;
	  content: "";
	  left: 1.75em;
	  top: 3.5em;
	  position: absolute;
	}
	
	@keyframes loader-spin {
	  0% {
	    transform: rotate(0deg);
	  }
	  100% {
	    transform: rotate(360deg);
	  }
	}
	@keyframes checkmark {
	  0% {
	    height: 0;
	    width: 0;
	    opacity: 1;
	  }
	  20% {
	    height: 0;
	    width: 1.75em;
	    opacity: 1;
	  }
	  40% {
	    height: 3.5em;
	    width: 1.75em;
	    opacity: 1;
	  }
	  100% {
	    height: 3.5em;
	    width: 1.75em;
	    opacity: 1;
	  }
	}


</style>

<body class="">
	<div class="page-lock">
		<div class="page-logo">
			<a class="brand" href="http://www.cubesofttech.com/"> <img
				src="images/logoCubeSoftTech.png" alt="logo" width="200px" />
			</a>
		</div>
		<div class="page-body row">
			<div class="block col">

				<div id="MyClockDisplay" class="clock" onload="showTime()"></div>

				<div class="circle-loader">
					<div class="checkmark draw"></div>
				</div>
			</div>
			<div class="page-lock-info col">
			<br><br>
				<h1 id="welcome">${logonUser}</h1>

				<span class="email"> </span>
				<c:forEach var="workhours" items="${work}" varStatus="status">
					<div class="md-radio-inline" id="checkbox" style="padding: 5px;">
						<div class="md-radio">
							<input type="radio" class="md-radiobtn" checked
								onclick="show_text(this.value);"> <label
								for="checkbox1_1";"> <span></span> <span class="check"></span>
								<span class="box"></span> <c:if
									test="${workhours.work_hours_type.toString() == '1'}">
                                    Check-IN
                                </c:if> <c:if
									test="${workhours.work_hours_type.toString() == '2'}">
                                    Check-OUT
                                </c:if>
							</label>
						</div>
					</div>
				</c:forEach>

				<div class="input-group input-medium" id="phonenumform">
					<input type="text" class="form-control" id="phone" name="number"
						value="${phoneNum}" maxlength="10" disabled> <span
						class="input-group-btn"> </span>
				</div>
				<br><br>
			</div>
		</div>
	</div>
	<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<script src="../assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->
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
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script
		src="../assets/global/plugins/backstretch/jquery.backstretch.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<script src="../assets/global/scripts/app.min.js"
		type="text/javascript"></script>
	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../assets/pages/scripts/lock-22.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<!-- END THEME LAYOUT SCRIPTS -->


	<script>

        function pageload() {

            var day = new Date();
            var mytime = day.getHours();
            if (mytime < 12) {
                checkbox1_1.checked = true;
                $("#detail").hide();
                $("#labeldetail").hide();
                $("#welcome").text("Hurry up!");

            } else {
                checkbox1_2.checked = true;
                $("#detail").hide();
                $("#labeldetail").hide();
                $("#welcome").text("See Yah!!");

            }
            }
        
     

       var rescan = document.getElementById("rescan").value;
        if (rescan == 1 ){
            $("#warn").text("Checkout too early!");
            $("#welcome").hide();
        } else if (rescan == 2){
           	$("#warn").text("Checkout again?");
            $("#welcome").hide();
        } else if (rescan == 3){
        	$("#warn").text("QR Code was used");
            $("#welcome").hide();
        	$("#phonenumform").hide();
        }
        
        window.onload = pageload;
       
    
    </script>

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

        }

        showTime();
        setTimeout(checkmark(),5000);

        function checkmark() {
  			$('.circle-loader').toggleClass('load-complete');
  			$('.checkmark').toggle();
		}
    </script>
</body>

</html>