<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i
				class="fa fa-calendar"></i> Calendar</span>
		</div>
		<div class="actions">
			<a class="btn btn-circle btn-icon-only btn-default fullscreen"
				href="javascript:;" data-original-title="" title=""> </a>
		</div>
	</div>
	<div style="text-align: center">
		<!-- ต้องเปลี่ยนเป็น iframe ของพี่บอล ด้วย -->
		<!-- <iframe src="https://calendar.google.com/calendar/embed?showTitle=0&amp;showPrint=0&amp;height=600&amp;wkst=1&amp;hl=en_GB&amp;bgcolor=%23FFFFFF&amp;src=tikcoma%40gmail.com&amp;color=%23333333&amp;ctz=Asia%2FBangkok"
		 style="border-width:0" width="100%" height="600" frameborder="0" scrolling="no"></iframe> -->
	</div>
</div>




<script>
	function nextStep() {
		document.location = "check_in";
	}

	function add() {
		document.location = "addnew_feed";
	}
</script>