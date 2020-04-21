<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
    span.btn {
        width: 120px;
    }
</style>

<div class="portlet light ">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-list font-red"></i> <span class="caption-subject font-red sbold uppercase">Equipment Status
                List</span> <span class="caption-helper font-red">
            </span>
        </div>
        <div class="actions">
            <a href="eStatusAdd" class="btn green-jungle"><i class="fa fa-plus"></i> Add new</a>
        </div>
    </div>
    <div class="portlet-body">
        <div class="table-scrollable">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="text-center"> Status </th>
                        <th class="text-center"> Description </th>
                        <th class="text-center"> Color </th>
                        <th class="text-center"> User Create </th>
                        <th class="text-center"> Time Create </th>
                        <th class="text-center"> User Update </th>
                        <th class="text-center"> Time Update </th>
                        <th class="text-center"> Edit </th>
						<th class="text-center"> Delete </th>
                    </tr>
                </thead>
                <tbody id="tbody">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<link href="../assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
<link href="../assets/global/plugins/bootstrap-sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<script src="../assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js" type="text/javascript"></script>

<script>
    var colors = ["default", "red", "red-mint", "blue", "blue-steel", "green", "green-jungle", "yellow", "yellow-lemon", "purple", "purple-seance", "grey-mint", "dark"];
    var sList = []
    sList = JSON.parse('${list}');
    var remove = (id) => {
        $.ajax({
            url: "checkEStatusRecord",
            data: {
                "status": id
            }
        }).done(function (response) {
            if (response.message == "ok") {
                swal({
                    title: "Are you sure!",
                    text: "Will you continue to delete Status : " + id,
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: 'btn-danger',
                    confirmButtonText: 'OK'
                }, function (selected) {
                    if (selected) {
                        document.location = "eStatusDelete?id=" + id;
                    }
                });
            } else {
                swal({
                    title: "You can't delete this!",
                    text: response.count+" records will be affected!",
                    type: "error",
                    confirmButtonClass: 'btn-danger'
                });
            }
        });
    }
</script>
<script>
    $(() => {
        let tbody = $('#tbody');

        sList.forEach((i) => {
            let row = "";
            let colorList = "";

            colorList += '<select id="' + i.statusId + '_color"class="form-control">';
            if (i.color == undefined) {
                colorList += '<option selected> </option>';
            }
            colors.forEach((c) => {
                if (i.color == c) {
                    colorList += '<option selected>' + c + '</option>';
                }
                else {
                    colorList += '<option>' + c + '</option>';
                }
            });
            colorList += '</select>';

            row += "<tr id='" + i.statusId + "'>";
            row += "<td><center>" + i.statusId + "</center></td>";
            row += "<td>" + "<span id='btn_" + i.statusId + "' class='btn btn-sm " + i.color + "'>" + i.description + "</span>" + "</td>";
            row += "<td>" + colorList + "</td>";
            row += "<td>" + i.userCreate + "</td>";
            row += "<td>" + i.timeCreate + "</td>";
            row += "<td><center>" + i.userUpdate + "</center></td>";
            row += "<td><center>" + i.timeUpdate + "</center></td>";
            row += "<td><center>"
                + "<a href='eStatusEdit?id=" + i.statusId + "' class='btn btn-circle btn-sm blue' title='Edit'><i class='fa fa-pencil'></i></a>"
                + "</center></td>";
            row += "<td><center>"
            	+ "<button name='" + i.statusId + "' onclick='remove(this.name)' class='btn btn-circle btn-sm red' title='Delete'><i class='fa fa-trash'></i></button>"
                + "</center></td>";
            row += "</tr>";

            tbody.append(row);

            $('#' + i.statusId + "_color").change(function () {
                let c = this.value;
                $.ajax({
                    url: "updateStatusColor",
                    data: {
                        "id": i.statusId,
                        "color": c
                    }
                }).done(function (response) {
                    if (response.includes("success")) {
                        $('#btn_' + i.statusId).removeClass();
                        $('#btn_' + i.statusId).addClass("btn btn-sm " + c);
                        toastr.success("Changed " + i.description + " color to " + c, "Sucessed");
                    }
                });
            });

        });
    })
</script>