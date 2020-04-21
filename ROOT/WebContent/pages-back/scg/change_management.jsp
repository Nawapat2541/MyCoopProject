
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
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
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME GLOBAL STYLES -->
<link href="../assets/global/css/components.min.css" rel="stylesheet"
	id="style_components" type="text/css" />
<link href="../assets/global/css/plugins.min.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->
<link href="../assets/layouts/layout4/css/layout.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/layouts/layout4/css/themes/default.min.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link href="../assets/layouts/layout4/css/custom.min.css"
	rel="stylesheet" type="text/css" />
<!-- END THEME LAYOUT STYLES -->
<link rel="shortcut icon" href="favicon.ico" />

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<span class="caption-subject font-red sbold uppercase"><i
				class="fa fa-refresh"></i>&nbsp; Change Management Detail</span>
			<!--<div id="tab-nor">nor</div><div id="tab-spe">spe</div><div id="tab-pro">pro</div>-->
		</div>

	</div>
	<div class="portlet-body form"
		style="margin-right: 2%; margin-left: 2%;">
		<div class="portlet-body">
			<div class="row">
				<div class="col-md-3">
					<h4 class="mt-body-title">Job No : 17-003</h4>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn red btn-sm" onclick="nextStep()"
						style="margin-left: -0%;">
						<i class="fa fa-remove"></i>&nbsp;Cancle Job
					</button>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="row">
					<div class="col-md-1">
						<h5 class="mt-body-title">Status :</h5>
					</div>
					<div class="col-md-2">
						<h5 class="mt-body-title">
							<i class="fa fa-circle font-green-jungle"></i>&nbsp;Start
							Workflow
						</h5>
					</div>
					<div class="col-md-2">
						<h5 class="mt-body-title">
							<i class="fa fa-circle font-green-jungle"></i>&nbsp;Upload SAP
						</h5>
					</div>
					<div class="col-md-2">
						<h5 class="mt-body-title">
							<i class="fa fa-circle font-red"></i>&nbsp;Incompleted Job
						</h5>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="row">
					<div class="col-md-1">Detail :</div>
					<div class="col-md-4">
						<textarea class="form-control" rows="3"
							placeholder="Enter more text" style="height: 20%; width: 100%;"></textarea>
					</div>
				</div>
				<div class="row" style="">
					<div class="col-md-1"></div>
					<div class="col-md-1" style="text-align: left; margin-top: 0.5%;">
						<div>
							<button type="button" class="btn green-jungle btn-sm"
								onclick="nextStep()">
								<i class="fa fa-plus"></i>&nbsp;Add Detail
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="note note-info"
					style="padding-bottom: 0px; padding-top: 0px; margin-top: 3%;">
					<h5 class="block">Process Flows</h5>
				</div>
			</div>
			<div class="row">
				<div class="portlet light portlet-fit ">

					<div class="portlet-body"
						style="padding-left: 0px; padding-right: 0px;">
						<div class="mt-element-step">
							<div class="row step-thin">
								<div class="col-md-3 bg-grey mt-step-col">
									<div class="mt-step-number bg-white font-grey">1</div>
									<div class="mt-step-title uppercase font-grey-cascade">PD</div>
									<div class="mt-step-content font-grey-cascade">Purchasing
										the item&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
								</div>
								<div class="col-md-3 bg-grey mt-step-col">
									<div class="mt-step-number bg-white font-grey">2</div>
									<div class="mt-step-title uppercase font-grey-cascade">QA</div>
									<div class="mt-step-content font-grey-cascade">Complete
										your payment</div>
								</div>
								<div class="col-md-3 bg-grey done mt-step-col active">
									<div class="mt-step-number bg-white font-grey">3</div>
									<div class="mt-step-title uppercase font-grey-cascade">SRC</div>
									<div class="mt-step-content font-grey-cascade">Receive
										item integration</div>
								</div>
								<div class="col-md-3 bg-grey mt-step-col">
									<div class="mt-step-number bg-white font-grey">4</div>
									<div class="mt-step-title uppercase font-grey-cascade">MKT</div>
									<div class="mt-step-content font-grey-cascade">Complete
										your payment</div>
								</div>
							</div>
							<br> <br>

						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="portlet-body flip-scroll"
					style="text-align: center; margin-top: -5%;">
					<table
						class="table table-bordered table-striped table-condensed flip-content table-hover "
						data-toggle="table" data-search="true" id="myTable">

						<thead>
							<tr>
								<th style="text-align: center;">No</th>
								<th style="text-align: center;">Name</th>
								<th style="text-align: center;">Detail</th>
								<th style="text-align: center;">Date</th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<td>1</td>
								<td>admin.j</td>
								<td>แก้ใขที่</td>
								<td>02-Mar-2017 10:00</td>
							</tr>
							<tr>
								<td>2</td>
								<td>admin.t</td>
								<td>แก้ใขที่</td>
								<td>02-Mar-2017 10:00</td>
							</tr>

						</tbody>


					</table>
					<!-- End Table -->
				</div>

			</div>
		</div>
	</div>
</div>
<div class="portlet light bordered">
	<div class="note note-info"
		style="padding-bottom: 0px; padding-top: 0px;">
		<h5 class="block">Show Product Details</h5>
	</div>
	<div>
		<div class="form-group form-md-line-input">
			<label class="col-md-2 control-label" for="form_control_1">Job
				Name <span class="required" aria-required="true">*</span>
			</label>
			<div class="col-md-3">
				<input type="text" class="form-control" value="window" name="name">
				<div class="form-control-focus"></div>
				<span class="help-block">enter your full name</span>
			</div>
		</div>
		<div class="form-group form-md-checkboxes has-error"
			style="margin-top: 5%;">
			<label class="col-md-2 control-label" for="form_control_1"
				style="color: black;">Status</label>
			<div class="col-md-10">
				<div class="md-radio-inline">
					<div class="md-radio">
						<input type="radio" id="checkbox1_3" class="md-radiobtn"
							name="radio1"> <label for="checkbox1_3"> <span></span>
							<span class="check"></span> <span class="box"></span> Start
							Workflow
						</label>
					</div>
					<div class="md-radio">
						<input type="radio" id="checkbox1_4" class="md-radiobtn"
							name="radio1"> <label for="checkbox1_4"> <span></span>
							<span class="check"></span> <span class="box"></span> Upload SAP
						</label>
					</div>
					<div class="md-radio">
						<input type="radio" id="checkbox1_5" class="md-radiobtn"
							name="radio1"> <label for="checkbox1_5"> <span></span>
							<span class="check"></span> <span class="box"></span> Incompleted
							Job
						</label>
					</div>
					<div class="md-radio">
						<input type="radio" id="checkbox1_6" class="md-radiobtn"
							name="radio1" checked> <label for="checkbox1_6">
							<span></span> <span class="check"></span> <span class="box"></span>
							คุณกำลังดำเนินงานในส่วนของ: Product Dev
						</label>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group form-md-line-input" style="margin-top: 5%;">
			<label class="col-md-2 control-label" for="form_control_1">Job's
				Note:</label>
			<div class="col-md-3">
				<textarea class="form-control" name="memo" rows="3"></textarea>
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input" style="margin-top: 5%;">
			<label class="col-md-2 control-label" for="form_control_1">Datepicker:</label>
			<div class="col-md-3">
				<input
					class="form-control form-control-inline input-medium date-picker"
					size="16" type="text" value="03/22/2017"> 
			</div>
		</div>

	</div>

</div>

<div class="portlet light bordered">
	<div class="portlet-title tabbable-line">
		<!-- <div class="caption">
			<i class="icon-globe font-dark hide"></i> <span
				class="caption-subject font-dark bold uppercase">Job No:
				17-0030</span>
		</div> -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab_1_1" class="active"
				data-toggle="tab" aria-expanded="true"> สินค้าที่ NPI ผลิตเอง </a></li>
			<li class=""><a href="#tab_1_2" data-toggle="tab"
				aria-expanded="false"> EFG Standard </a></li>
			<li class=""><a href="#tab_1_3" data-toggle="tab"
				aria-expanded="false"> EFG Step Volume </a></li>
		</ul>
	</div>
	<div class="portlet-body">
		<!--BEGIN TABS-->
		<div class="tab-content">
			<div class="tab-pane active" id="tab_1_1">
				<div class="portlet-body">
					<div class="form-group" style="text-align: center;">

						<div class="col-md-12">
							<div class="fileinput fileinput-exists" data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"
									style="width: 200px; height: 150px; line-height: 150px;">
									<img src="images/window3.PNG">
									</div>
								<div>
									<span class="btn red btn-outline btn-file"> <span
										class="fileinput-new"> Select image </span> <span
										class="fileinput-exists"> Change </span> <input type="hidden"><input
										type="hidden" value="" name="..."><input type="file"
										name="">
									</span> <a href="javascript:;" class="btn red fileinput-exists"
										data-dismiss="fileinput"> Remove </a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							1 PRODUCT</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							2 BRAND</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							3 CATEGORY</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							4 SERIES</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>


					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							5 COLOUR</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							6 APPLICATION</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							7 TYPE</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							8 SUB TYPE</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							9 RUNNING</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							10 การทำสี /วัสดุ</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							11 ขนาดของเส้น </label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">Material
							Number</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>


					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Old
							Material Number </label>
						<div class="col-md-3">
							<input type="text" class="form-control" placeholder=""
								name="name">
							<div class="form-control-focus"></div>
							<span class="help-block">enter your full name</span>
						</div>
					</div>
					<div style="text-align: center; margin-top: 8%;">
						<button type="button" class="btn btn-primary">Edit Material</button>
					</div>

				</div>
			</div>
			<div class="tab-pane" id="tab_1_2">

				<div class="portlet-body">




					<div class="form-group" style="text-align: center;">

						<div class="col-md-12">
							<div class="fileinput fileinput-exists" data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"
									style="width: 200px; height: 150px; line-height: 150px;">
									<img src="images/window.PNG">
								</div>
								<div>
									<span class="btn red btn-outline btn-file"> <span
										class="fileinput-new"> Select image </span> <span
										class="fileinput-exists"> Change </span> <input type="hidden"><input
										type="hidden"><input type="file" name="...">
									</span> <a href="javascript:;" class="btn red fileinput-exists"
										data-dismiss="fileinput"> Remove </a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							1 PRODUCT</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							2 BRAND</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							3 CATEGORY</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							4 SERIES</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>


					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							5 COLOUR</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							6 APPLICATION</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							7 TYPE</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							8 SUB TYPE</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							9 RUNNING</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							10 การทำสี /วัสดุ</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							11 ขนาดของเส้น </label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">Material
							Number</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>


					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Old
							Material Number </label>
						<div class="col-md-3">
							<input type="text" class="form-control" placeholder=""
								name="name">
							<div class="form-control-focus"></div>
							<span class="help-block">enter your full name</span>
						</div>
					</div>
					<div style="text-align: center; margin-top: 8%;">
						<button type="button" class="btn btn-success">Edit Material</button>
					</div>

				</div>
			</div>

			<div class="tab-pane" id="tab_1_3">

				<div class="portlet-body">
					<div class="form-group" style="text-align: center;">

						<div class="col-md-12">
							<div class="fileinput fileinput-exists" data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput"
									style="width: 200px; height: 150px; line-height: 150px;">
									<img src="images/window1.PNG">
								</div>
								<div>
									<span class="btn red btn-outline btn-file"> <span
										class="fileinput-new"> Select image </span> <span
										class="fileinput-exists"> Change </span> <input type="hidden"><input
										type="hidden"><input type="file" name="...">
									</span> <a href="javascript:;" class="btn red fileinput-exists"
										data-dismiss="fileinput"> Remove </a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							1 PRODUCT</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							2 BRAND</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							3 CATEGORY</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							4 SERIES</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>


					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							5 COLOUR</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							6 APPLICATION</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							7 TYPE</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							8 SUB TYPE</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							9 RUNNING</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							10 การทำสี /วัสดุ</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>

					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">MAT.LEV
							11 ขนาดของเส้น</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>


						<label class="col-md-3 control-label" for="form_control_1">Material
							Number</label>
						<div class="col-md-3">
							<select class="form-control" name="delivery">
								<option value="">ข้อมูล</option>
								<option value="2">Option 1</option>
								<option value="3">Option 2</option>
								<option value="4">Option 3</option>
							</select>
						</div>
					</div>


					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">Old
							Material Number </label>
						<div class="col-md-3">
							<input type="text" class="form-control" placeholder=""
								name="name">
							<div class="form-control-focus"></div>
							<span class="help-block">enter your full name</span>
						</div>
					</div>
					<div style="text-align: center; margin-top: 8%;">
						<button type="button" class="btn purple">Edit Material</button>
					</div>

				</div>
			</div>
		</div>
		<!--END TABS-->
	</div>
</div>

<!-- BEGIN CORE PLUGINS -->

<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="../assets/layouts/layout4/scripts/layout.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/layout4/scripts/demo.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/global/scripts/quick-sidebar.min.js"
	type="text/javascript"></script>
<script src="../assets/layouts/global/scripts/quick-nav.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>
<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<!-- END THEME LAYOUT SCRIPTS -->

<link
	href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"
	type="text/javascript"></script>
