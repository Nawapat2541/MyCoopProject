<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"> <i
				class="fa fa-history"></i> Create New Job
			</span>
		</div>
	</div>
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-3">
				<div class="mt-widget-4">
					<div class="mt-img-container">
						<img src="../assets/pages/img/background/34.jpg">
					</div>
					<div class="mt-container bg-green-jungle-opacity">
						<div class="mt-head-title">เปิดรหัสสินค้าใหม่</div>
						
						<div class="mt-footer-button">
							<button type="button" id="button1"
								class="btn btn-circle bg-green-jungle btn-sm" value="1"
								onclick="show_text(this.value)">NEW MAT</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="mt-widget-4">
					<div class="mt-img-container">
						<img src="../assets/pages/img/background/46.jpg">
					</div>
					<div class="mt-container bg-green-opacity">
						<div class="mt-head-title">เปิดรหัสสินค้าใหม่
							(เพื่อทดแทนสินค้าเก่า)</div>
						
						<div class="mt-footer-button">
							<button type="button" class="btn btn-circle bg-green btn-sm" value="2"
								onclick="show_text(this.value)" id="button2">REPLACE</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="mt-widget-4">
					<div class="mt-img-container">
						<img src="../assets/pages/img/background/37.jpg">
					</div>
					<div class="mt-container bg-yellow-lemon-opacity">
						<div class="mt-head-title">แจ้งแก้ไขข้อมูลสินค้า (ชื่อ /
							ราคา)</div>
						
						<div class="mt-footer-button">
							<button type="button"
								class="btn btn-circle bg-yellow-lemon btn-sm" value="3"
								onclick="show_text(this.value)" id="button3">MODIFY</button>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-3">
				<div class="mt-widget-4">
					<div class="mt-img-container">
						<img src="../assets/pages/img/background/34.jpg">
					</div>
					<div class="mt-container bg-red-opacity">
						<div class="mt-head-title">ยกเลิกรหัสสินค้า</div>
						
						<div class="mt-footer-button">
							<button type="button" class="btn btn-circle bg-red btn-sm" value="4"
								onclick="show_text(this.value)" id="button4">OBSOLETE</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>







<!-- BEGIN VALIDATION STATES-->
<div class="portlet light portlet-fit portlet-form bordered" id="box1">
	<div class="portlet-title">
		<div class="caption">
			<i class=" icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Are you sure
				to create new material?</span>
		</div>
	</div>
	<div class="portlet-body">
		<!-- BEGIN FORM-->

		<div class="form-group form-md-line-input" style="text-align: center;">
			<label class="col-md-1 control-label" for="form_control_1"
				style="margin-top: 0.3%">Select</label>
			<div class="col-md-9">
				<div class="md-radio-inline">
					<div class="md-radio">

						<input type="radio" id="checkbox1_1" name="work_hours_type"
							value="1" class="md-radiobtn"> <label for="checkbox1_1">
							<span></span> <span class="check"></span> <span class="box"></span>
							Part: ส่วนประกอบของสินค้าเพียงอย่างใดอย่างหนึ่ง
						</label>
					</div>
					<div class="md-radio">
						<input type="radio" id="checkbox1_2" name="work_hours_type"
							value="2" class="md-radiobtn"> <label for="checkbox1_2">
							<span></span> <span class="check"></span> <span class="box"></span>
							Application: สินค้าทั้งระบบ เช่น ระบบบานเลื่อนคู่ เป็นต้น
						</label>
					</div>

				</div>
			</div>

		</div>
		<div style="text-align: center; margin-top: 5%; margin-bottom: 3.5%;">
			<button type="button" class="btn blue" onclick="nextStep()">Yes</button>
			<button type="button" class="btn red" onclick="hide_text(this.value)" id="hide1" value="1">No</button>
		</div>
		<!-- END FORM-->
	</div>
</div>



<div class="portlet light portlet-fit portlet-form bordered" id="box2">
	<div class="portlet-title">
		<div class="caption">
			<i class=" icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Are you sure
				to create new material for replace the old ?</span>
		</div>
	</div>
	<div class="portlet-body">
		<!-- BEGIN FORM-->

		<div class="form-group form-md-line-input" style="text-align: center;">
			<label class="col-md-1 control-label" for="form_control_2"
				style="margin-top: 0.3%">Select</label>
			<div class="col-md-9">
				<div class="md-radio-inline">
					<div class="md-radio">

						<input type="radio" id="checkbox1_3" name="123" value="3"
							class="md-radiobtn"> <label for="checkbox1_3"> <span></span>
							<span class="check"></span> <span class="box"></span> Part:
							ส่วนประกอบของสินค้าเพียงอย่างใดอย่างหนึ่ง
						</label>
					</div>
					<div class="md-radio">
						<input type="radio" id="checkbox1_4" name="123" value="4"
							class="md-radiobtn"> <label for="checkbox1_4"> <span></span>
							<span class="check"></span> <span class="box"></span>
							Application: สินค้าทั้งระบบ เช่น ระบบบานเลื่อนคู่ เป็นต้น
						</label>
					</div>

				</div>
			</div>

		</div>
		<div style="text-align: center; margin-top: 5%; margin-bottom: 3.5%;">
			<button type="button" class="btn blue" onclick="nextStep()">Yes</button>
			<button type="button" class="btn red" onclick="hide_text(this.value)" id="hide2" value="2">No</button>
		</div>
		<!-- END FORM-->
	</div>
</div>
<!-- END VALIDATION STATES-->

<div class="portlet light portlet-fit portlet-form bordered" id="box3">
	<div class="portlet-title">
		<div class="caption">
			<i class=" icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Are you sure
				to modify material data (name / price)?</span>
		</div>
	</div>
	<div class="portlet-body">
		<!-- BEGIN FORM-->

		<div class="form-group form-md-line-input" style="margin-left: 2%;">
			<label class="col-md-2 control-label" for="form_control_1">Option</label>
			<div class="col-md-10">
				<div class="md-radio-inline">
					<div class="md-radio">
						<input type="radio" id="checkbox1_8" name="radio2" value="1"
							class="md-radiobtn"> <label for="checkbox1_8"> <span></span>
							<span class="check"></span> <span class="box"></span>
							แก้ไขชื่อสินค้า (ไทย-Eng)
						</label>
					</div>
					<div class="md-radio">
						<input type="radio" id="checkbox1_9" name="radio2" value="2"
							class="md-radiobtn"> <label for="checkbox1_9"> <span></span>
							<span class="check"></span> <span class="box"></span>
							แก้ไขราคาสินค้า
						</label>
					</div>
					<div class="md-radio">
						<input type="radio" id="checkbox1_10" name="radio2" value="2"
							class="md-radiobtn"> <label for="checkbox1_10"> <span></span>
							<span class="check"></span> <span class="box"></span>
							แก้ไขน้ำหนักสินค้า
						</label>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group form-md-line-input" style="margin-left: 2%;">
			<label class="col-md-2 control-label" for="form_control_1">Detail</label>
			<div class="col-md-5">
				<textarea class="form-control" name="memo" rows="3"></textarea>
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<div class="col-md-5"></div>
		</div>
		<div class="form-group form-md-line-input" style="margin-left: 2%;">
			<label class="col-md-2 control-label" for="form_control_1">Name
			</label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="" name="name">
				<div class="form-control-focus"></div>
				<span class="help-block">enter your full name</span>
			</div>
			
			<label class="col-md-1 control-label" for="form_control_1"></label>
			<div class="col-md-4">
				<select class="form-control" name="delivery">
					<option value="">ประตูโรยอล</option>
					<option value="2">เก้าอี้จักพรรดิ์</option>
					<option value="3">ตู้แวร์ซาย</option>
					<option value="4">ราวตากผ้าอนาคต</option>
				</select>
				<div class="form-control-focus"></div>
			</div>
			
		</div>

		

		<div style="text-align: center; margin-top: 5%; margin-bottom: 3.5%;">
			<button type="button" class="btn blue" onclick="nextStep()">Yes</button>
			<button type="button" class="btn red" onclick="hide_text(this.value)" id="hide3" value="3">No</button>
		</div>
		<!-- END FORM-->
	</div>
</div>
<!-- END VALIDATION STATES-->

<div class="portlet light portlet-fit portlet-form bordered" id="box4">
	<div class="portlet-title">
		<div class="caption">
			<i class=" icon-layers font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Are you sure
				to obsolete material?</span>
		</div>
	</div>
	<div class="portlet-body">
		<!-- BEGIN FORM-->
		<div class="form-group form-md-line-input" style="margin-left: 2%;">
			<label class="col-md-2 control-label" for="form_control_1">Detail</label>
			<div class="col-md-5">
				<textarea class="form-control" name="memo" rows="3"></textarea>
				<div class="form-control-focus"></div>
			</div>
		</div>

		<div class="form-group form-md-line-input">
			<div class="col-md-5"></div>
		</div>

		<div class="form-group form-md-line-input" style="margin-left: 2%;">
			<label class="col-md-2 control-label" for="form_control_1">Name
			</label>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="" name="name">
				<div class="form-control-focus"></div>
				<span class="help-block">enter your full name</span>
			</div>
			
			<label class="col-md-1 control-label" for="form_control_1"></label>
			<div class="col-md-4">
				<select class="form-control" name="delivery">
					<option value="">ประตูโรยอล</option>
					<option value="2">เก้าอี้จักพรรดิ์</option>
					<option value="3">ตู้แวร์ซาย</option>
					<option value="4">ราวตากผ้าอนาคต</option>
				</select>
				<div class="form-control-focus"></div>
			</div>
			
		</div>

		<div style="text-align: center; margin-top: 5%; margin-bottom: 3.5%;">
			<button type="button" class="btn blue" onclick="nextStep()">Yes</button>
			<button type="button" class="btn red" onclick="hide_text(this.value)" id="hide4" value="4">No</button>
		</div>
		<!-- END FORM-->
	</div>
</div>

<script>
	function nextStep() {
		document.location = "demo?pageName=jobmain-page";
	}
	
	function show_text(id) 
	{
		if(id == 1)
			{
			$("#box1").show();
			$("#box2").hide();
			$("#box3").hide();
			$("#box4").hide();
			}
		else if (id == 2)
			{
			$("#box1").hide();
			$("#box2").show();
			$("#box3").hide();
			$("#box4").hide();
			}
		else if (id == 3)
		{
			$("#box1").hide();
			$("#box2").hide();
			$("#box3").show();
			$("#box4").hide();
		}
		else if (id == 4)
		{
			$("#box1").hide();
			$("#box2").hide();
			$("#box3").hide();
			$("#box4").show();
		}
	}
	function hide_text(id) 
	{
		if(id == 1)
			{
				$("#box1").hide();
			}
		else if (id == 2)
			{
				$("#box2").hide();
			}
		else if (id == 3)
			{
				$("#box3").hide();
			}
		else if (id == 4)
			{
				$("#box4").hide();
			}
	}
	function pageload() {
		$("#box1").hide();
		$("#box2").hide();
		$("#box3").hide();
		$("#box4").hide();
	}
	window.onload = pageload;
</script>

