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


   

    .img-clock {
        width: 100px;
        height: 100px;
        position: relative;
        -webkit-animation: mymove 2s infinite;
        /* Safari 4.0 - 8.0 */
        animation: mymove 2s infinite;
    }

    .img-clock:hover {
        -webkit-animation-play-state: paused;
        /* Safari 4.0 - 8.0 */
        animation-play-state: paused;
    }

    /* Safari 4.0 - 8.0 */
    @-webkit-keyframes mymove {
        from {
            left: 0px;
        }

        to {
            left: 150px;
        }
    }

    @keyframes mymove {
        from {
            left: 0px;
        }

        to {
            left: 150px;
        }
    }
  
</style>

<body class="">
    <div class="page-lock">
        <div class="page-logo">
            <a class="brand" href="http://www.cubesofttech.com/">
                <img src="images/logoCubeSoftTech.png" alt="logo" width="200px" /> </a>
        </div>
        <form class="form-inline" action="QRcheckin" method="POST">
        <div class="page-body row">
            <div class="block col">

                <div id="MyClockDisplay" class="clock" onload="showTime()"></div>
                <div id="imageclock" class="img-clock"> <img src="images/run2.png" height="100px"></div>
            </div>
            <div class="page-lock-info col">
            <br>
                <h1 id="welcome"> </h1>
                <h1 id="warn"> </h1>
                <span class="email"> </span>
                <div class="md-radio-inline" id="checkbox" style="padding: 5px;">
                    <div class="md-radio">
                        <input type="radio" id="checkbox1_1" name="worktype" value="1" class="md-radiobtn"
                            onclick="show_text(this.value);"> <label for="checkbox1_1" ;"> <span></span>
                            <span class="check"></span> <span class="box"></span> Check-IN
                        </label>
                    </div>
                    <div class="md-radio">
                        <input type="radio" id="checkbox1_2" name="worktype" value="2" class="md-radiobtn"
                            onclick="show_text(this.value);"> <label for="checkbox1_2"> <span></span> <span
                                class="check" "></span> <span
                                class=" box"></span> Check-OUT
                        </label>
                    </div>
                </div>

                    <div class="input-group input-medium" id="phonenumform">
                        <input type="text" class="form-control" id="phone" name="number"  value="${phoneNum}" maxlength="10" placeholder="input your phone number">
                        <span class="input-group-btn">
                            <button type="submit" class="btn green icn-only">
                                <i class="m-icon-swapright m-icon-white"></i>
                            </button>
                        </span>
                    </div>
                    <!-- /input-group -->
                    <div class="relogin">
                        <a href="index.jsp"> Don't have phone number?</a>
                    </div>
                    <input type="hidden" value="${qrID}" name="qrcodeID">
                    <input type="hidden" value="${reScan}" name="rescan" id="rescan">
                </form>
            </div><br>
        </div>
    </div>
    <!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<script src="../assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->
    <!-- BEGIN CORE PLUGINS -->
    <script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
    <!-- END CORE PLUGINS -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="../assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL SCRIPTS -->
    <script src="../assets/global/scripts/app.min.js" type="text/javascript"></script>
    <!-- END THEME GLOBAL SCRIPTS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="../assets/pages/scripts/lock-22.min.js" type="text/javascript"></script>
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
        $("#checktime").click(function () {
            var checkourtime = $("#ourtime").val();//เช็ต ค่าใน detail 
            var radio1 = $('input[id=checkbox1_1]:checked').val();
            var radio2 = $('input[id=checkbox1_2]:checked').val();
        });
        
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
            setTimeout(showTime, 1000);

        }

        showTime();
    </script>
</body>

</html>