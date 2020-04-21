<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="../assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet"
    type="text/css" />
<style>
    span.btn {
        width: 120px;
    }
</style>

<div class="portlet light ">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-list font-red"></i> <span class="caption-subject font-red sbold uppercase">Borrow
                List</span> <span class="caption-helper font-red">
            </span>
        </div>
        <div class="actions">
            <a href="eBorrow" class="btn green-jungle"><i class="fa fa-plus"></i> Add new</a>
        </div>
    </div>
    <div class="portlet-body">
        <table class="table table-striped table-bordered table-hover dt-responsive" width="100%" id="table">
            <thead>
                <tr>
                    <th colspan="6">
                        <div class="mt-checkbox-inline" id="status"></div>
                    </th>
                </tr>
                <tr>
                    <th class="all">Date Create</th>
                    <th class="all">ITEM NO</th>
                    <th class="all">Equipment</th>
                    <th class="none">Equipment Id</th>
                    <th class="none">Serial NO</th>
                    <th class="none">Borrow ID</th>
                    <th class="none">Location</th>
                    <th class="none">Reason</th>
                    <th class="none">Remark</th>
                    <th class="none">Date Start</th>
                    <th class="none">Date End</th>
                    <th class="all">Borrower</th>
                    <th class="all">Location</th>
                    <th class="all">Status</th>
                    <th class="all"></th>
                </tr>
            </thead>
            <tbody id='tbody'>
            </tbody>
        </table>
    </div>
</div>
<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../assets/global/scripts/datatable.js" type="text/javascript"></script>
<script src="../assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
    type="text/javascript"></script>
<script src="../assets/global/plugins/sweetalert/sweetalert.min.js"></script>
<script>
//document ready function
$(()=>{
    var borrows = [];
    var equipments = [];
    var s_filter = [];
    var user = "${onlineUser.getId()}";

    var status = [ {"id":"W","description":"Wait for approve","color":"default"}, 
        {"id":"B","description":"Borrowing","color":"yellow-lemon"}, 
        {"id":"R","description":"Returned","color":"green-jungle"}, 
        {"id":"C","description":"Cancel","color":"red-mint"} 
        ];

    var getStatusDescription = (id) => {
        result = "";
        status.forEach((s)=>{
            if(s.id == id){
                result = "<span class='btn btn-sm "+s.color+"'>" + s.description + "</span>";
            }
        });
        return result;
    }

    var removeElement = (a, i) => {
        let x = a.indexOf(i);
        return a.splice(x, 1);
    }

    var search = () => {
        let sf = s_filter.join("|");
        let table = $('#table').DataTable();

        table.column(13).search(sf, true);
        table.draw();
    }

    status.forEach((s)=>{
        let string = "";
        string = '<label class="mt-checkbox"><input type="checkbox" name="status" id="s_' + s.id + '" value="' + s.description + '">' + s.description + '<span></span></label>';
        $('#status').append(string);
    });

    borrows = JSON.parse('${borrows}');
    equipments = JSON.parse('${equipments}');

    borrows.forEach((b)=>{
        let eId = b.equipmentId;
        let equipment = {};
        b['equipment'] = {};
        equipments.forEach((e)=>{
            if(e.equipmentId == eId){ equipment = e; }
        });
        b['equipment'] = equipment;
    });

    //Start init table
    var table = $('#table');

    var oTable = table.DataTable({
        "data": borrows,
        "columns": [
            { "data": "timeCreate", "defaultContent": "",
                "render" : (data)=>{ 
                    return new Date(data).toLocaleDateString("en-GB",{ "month":"short","year":"numeric","day":"numeric","hour":"numeric","minute":"numeric","second":"numeric" })
                }
            },
            { "data": "equipment.itemNo", "defaultContent": "" },
            { "data": "equipment.name", "defaultContent": "" },
            { "data": "equipment.equipmentId", "defaultContent": "" },
            { "data": "equipment.serialNo", "defaultContent": "" },
            { "data": "borrowId", "defaultContent": "" },
            { "data": "location", "defaultContent": "" },
            { "data": "reason", "defaultContent": "" },
            { "data": "remark", "defaultContent": "" },
            { "data": "dateStart", "defaultContent": "",
                "render" : (data)=>{ 
                    if(data != undefined) { return new Date(data).toLocaleDateString("en-GB",{ "month":"short","year":"numeric","day":"numeric" }) }
                }
            },
            { "data": "dateEnd", "defaultContent": "",
                "render" : (data)=>{ 
                    if(data != undefined) { return new Date(data).toLocaleDateString("en-GB",{ "month":"short","year":"numeric","day":"numeric" }) }
                }
            },
            { "data": "userBorrowid", "defaultContent": "" },
            { "data": "location", "defaultContent": "" },
            { "data": "status", "defaultContent": "", "render" : (data)=>{ return getStatusDescription(data); } },
            { "data": "borrowId", "defaultContent": "", "render" : (data,type,row)=>{
                    let result = "";
                    let borrowUser = row['userBorrowid'];
                    let status = row['status'];
                    if(user == borrowUser && status == "W"){
                        result += '<a class="btn btn-xs red" id="'+data+'" name="bCancle" ><i class="fa fa-remove"></i> Cancle </a>';
                    }
                    if(user == borrowUser && status == "B"){
                        result += '<a class="btn btn-xs yellow-lemon" id="'+data+'" name="bReturn" ><i class="fa fa-mail-reply"></i> Return </a>';
                    }
                    <c:if test="${userAuthority.contains('borrow.view')}">
                        result += '<a class="btn btn-xs blue" href="eBorrowEdit?id='+data+'"><i class="fa fa-pencil"></i> Edit </a>';
                    </c:if>
                    
         
                    result += '<a class="btn btn-xs red" href="borrowReport?borrowId='+data+'"><i class="fa fa-pencil"></i> Print </a>';
                    return result;
            }}
        ],

        "search": { "regex": true },

        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "No data available in table",
            "info": "Showing _START_ to _END_ of _TOTAL_ entries",
            "infoEmpty": "No entries found",
            "infoFiltered": "(filtered1 from _MAX_ total entries)",
            "lengthMenu": "_MENU_ entries",
            "search": "Search:",
            "zeroRecords": "No matching records found"
        },

        // setup buttons extentension: http://datatables.net/extensions/buttons/
        buttons: [
        ],

        // setup responsive extension: http://datatables.net/extensions/responsive/
        responsive: {
            details: {

            }
        },

        "order": [
            [0, 'desc']
        ],

        "lengthMenu": [
            [5, 10, 15, 20, -1],
            [5, 10, 15, 20, "All"] // change per page values here
        ],
        // set the initial value
        "pageLength": 10,

        "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable

        // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
        // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
        // So when dropdowns used the scrollable div should be removed. 
        //"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
    });
    //End init Table

    $('input[name="status"]').change(function () {
        if (this.checked) {
            s_filter.push("(" + this.value + ")");
        }
        else {
            removeElement(s_filter, "(" + this.value + ")");
        }
        search();
    });

    $('#status').append("<br>");
    $('#status').append('<button class="btn btn-sm green" id="selectAll"> Select All </button>');
    $('#status').append('<button class="btn btn-sm default" id="selectNone"> Select None </button>');

    $('#selectAll').click(()=>{
        $('input[name=status]').prop("checked",true);
        s_filter = [];
        let selected = $('input[name=status]');
        for(let i=0; i<selected.length; i++){
            s_filter.push("(" + selected[i].value + ")");
        }
        search();
    });
    $('#selectNone').click(()=>{
        $('input[name=status]').prop("checked",false);
        s_filter = [];
        search();
    });

})
</script>

<script>
$(()=>{
    $('[name=bCancle]').click(function(){
        let id = this.id;
        swal({
            title: "Are you sure!",
            text: "Will you cancle borrow request : " + id,
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((selected)=>{
            if (selected) {
                $.ajax({
                    data : { "id" : id },
                    url : "cancleMyBorrow"
                }).done((r)=>{
                    if(r.message == "success"){
                        location.reload();
                    } else {
                        swal(r.message);
                    }
                });
            }
        });
    });

    $('[name=bReturn]').click(function(){
        let id = this.id;
        swal({
            title: "Are you sure!",
            text: "Will you return borrow request : " + id,
            icon: "info",
            buttons: true,
            dangerMode: true,
        }).then((selected)=>{
            if (selected) {
                $.ajax({
                    data : { "id" : id },
                    url : "returnMyBorrow"
                }).done((r)=>{
                    if(r.message == "success"){
                        location.reload();
                    } else {
                        swal(r.message);
                    }
                });
            }
        });
    });
})
</script>