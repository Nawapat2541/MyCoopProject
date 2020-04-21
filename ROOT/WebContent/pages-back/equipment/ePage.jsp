<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="portlet light bordered">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red sbold uppercase"> <i class="fa fa-folder-open"></i> Equipment </span>
        </div>
        <div class="actions">
            <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title=""
                title=""> </a>
        </div>
    </div>

    <div class="portlet-body form">
        <form action="newE" class="form-horizontal" name="form" id="form" method="post" enctype="multipart/form-data">
            <div class="form-body">

                <div class="row text-center">
                    <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                            <img id="img" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt="" /> </div>
                        <div class="fileinput-preview fileinput-exists thumbnail"
                            style="max-width: 200px; max-height: 150px;"> </div>
                        <div>
                            <span class="btn default btn-file">
                                <span class="fileinput-new"><i class="fa fa-picture-o"></i> Select image </span>
                                <span class="fileinput-exists"> Change </span>
                                <input type="file" name="image"> </span>
                            <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> Remove </a>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="itemnolabel">Item NO : </label>
                            <div class="col-md-8">
                                <input type="text" name="itemNo" class="form-control" id="itemNo" maxlength="13"
                                    >
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="typeLabel">Type : </label>
                            <div class="col-md-8">
                                <select name="type" id="type" class="form-control" ></select>
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="namelabel">Name : </label>
                            <div class="col-md-8">
                                <input type="text" name="name" class="form-control" id="name" maxlength="240"
                                    >
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="serialnolabel">Serial NO : </label>
                            <div class="col-md-8">
                                <input type="text" name="serialNo" class="form-control" id="serialNo" maxlength="60"
                                    >
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="amountLabel">Amount : </label>
                            <div class="col-md-8">
                                <input type="number" name="amount" class="form-control" id="amount" maxlength="10" value=1>
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="locationLabel">Location : </label>
                            <div class="col-md-8">
                                <input type="text" name="location" class="form-control" id="location" maxlength="250">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr class="computer">

                <div class="row computer">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="windowsLabel">Windows : </label>
                            <div class="col-md-8">
                                <input type="text" name="windows" class="form-control" id="windows" maxlength="60">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="processLabel">Processor : </label>
                            <div class="col-md-8">
                                <input type="text" name="process" class="form-control" id="process" maxlength="120">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row computer">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="ramLabel">Ram : </label>
                            <div class="col-md-8">
                                <input type="text" name="ram" class="form-control" id="ram" maxlength="120">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="hddLabel">Hdd : </label>
                            <div class="col-md-8">
                                <input type="text" name="hdd" class="form-control" id="hdd" maxlength="120">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row computer">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="batteryLabel">Battery : </label>
                            <div class="col-md-8">
                                <input type="text" name="battery" class="form-control" id="battery" maxlength="120">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="detailLabel">Detail : </label>
                            <div class="col-md-8">
                                <input type="text" name="detail" class="form-control" id="detail" maxlength="1000">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
    
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="statusLabel">Status : </label>
                            <div class="col-md-8">
                                <select name="status" id="status" class="form-control" ></select>
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group form-lg-line-input col-md-12">
                            <label class="control-label col-md-3" id="noteLabel">Note : </label>
                            <div class="col-md-8">
                                <input type="text" name="note" class="form-control" id="note" maxlength="1000">
                                <div class="form-control-focus"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="form-actions">
                <div class="row ">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn blue">
                            <i class="fa fa-save"></i> Save
                        </button>
                        <button onclick="goBack()" class="btn red">
                            <i class="fa fa-close"></i> Cancel
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<link href="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
<script src="../assets/global/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    var goBack = () => {
        window.history.back();
    }
    var s_list = [];
    s_list = JSON.parse('${status}');
    var t_list = [];
    t_list = JSON.parse('${type}');
</script>

<script>
    $(()=>{
        s_list.forEach((s)=>{
            $('#status').append("<option value='"+s.statusId+"'>"+s.description+"</option>")
        });
        t_list.forEach((t)=>{
            $('#type').append("<option value='"+t.TypeID+"'>"+t.description+"</option>")
        });

        $('#type').change(function(){
            if(this.value != "c"){
                $(".computer").css("display","none");
            } else {
                $(".computer").css("display","block");
            }
        });

        $('#itemNo').change(function(){
            let val = this.value;
            $.ajax({
                    url: "eCheckItemNo",
                    data: {
                        "itemNo": val
                    }
                }).done(function (r) {
                    if(r.message == "used"){
                        swal("Warning!", "ItemNo is already used by "+r.name, "warning");
                    }
                });
        });
    });
</script>

<!-- Edit Equipment -->
<c:if test="${equipment!=null}">
    <script>
        var e = {};
        e = JSON.parse('${equipment}');

        $(()=>{
            $('#form').prop("action","updateE");

            $('#itemNo').val(e.itemNo);
            $('#type').val(e.type);
            $('#name').val(e.name);
            $('#serialNo').val(e.serialNo);
            $('#amount').val(e.amount);
            $('#location').val(e.location);
            $('#windows').val(e.windows);
            $('#process').val(e.process);
            $('#ram').val(e.ram);
            $('#hdd').val(e.hdd);
            $('#battery').val(e.battery);
            $('#detail').val(e.detail);
            $('#status').val(e.status);
            $('#note').val(e.note);

            if(e.image != null){
                $("#img").prop("src",e.image);
            }

            if(e.status == "B"){
                $('#status').click(()=>{
                    $.ajax({
                        url: "eCheckBorrow",
                        data: {
                            "eId": e.equipmentId
                        }
                    }).done(function (borrowing) {
                        if(borrowing.length>0){

                            let body = document.createElement("div");

                            let head = document.createElement("p");
                            let text = document.createTextNode("Please change this borrow status before change this equipment status!");
                            head.appendChild(text); 
                            body.appendChild(head);

                            borrowing.forEach((b) => {
                                let item = document.createElement("a");
                                item.setAttribute("href","eBorrowEdit?id="+b.borrowId);
                                item.setAttribute("style","display:block");
                                let text = document.createTextNode(b.userBorrowid);
                                item.appendChild(text);
                                body.appendChild(item);
                            });
                            swal({
                                title: "You shouldn't do this!",
                                content: body,
                                icon: "warning"
                            });
                        }
                    });

                });

            }
        });
    </script>
</c:if>
<!-- End Edit Equipment -->