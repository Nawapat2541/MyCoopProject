<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="../assets/global/plugins/datatables/datatables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<style>
span.btn {
	width: 120px;
}
</style>

<div class="portlet light ">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-list font-red"></i> <span
				class="caption-subject font-red sbold uppercase">Equipment
				List</span> <span class="caption-helper font-red"> </span>
		</div>
		<div class="actions">
			<a href="eAdd" class="btn green-jungle"><i class="fa fa-plus"></i>
				Add new</a>
		</div>
	</div>
	<div class="portlet-body">
		<table
			class="table table-striped table-bordered table-hover dt-responsive"
			width="100%" id="table">
			<thead>
				<tr>
					<th colspan="3">
						<div class="mt-checkbox-inline" id="type"></div>
					</th>
					<th colspan="4">
						<div class="mt-checkbox-inline" id="status"></div>
					</th>
				</tr>
				<tr>
					<th class="all">ITEM NO</th>
					<th class="all">Type</th>
					<th class="all">Equipment</th>
					<th class="none">Equipment Id</th>
					<th class="none">Serial NO</th>
					<th class="none">OS</th>
					<th class="none">Processor</th>
					<th class="none">Ram</th>
					<th class="none">HDD</th>
					<th class="none">Battery</th>
					<th class="all">Detail</th>
					<th class="all">Status</th>
					<th class="all">Borrower</th>
					<th class="none">Borrower History</th>
					<th class="all"></th>
				</tr>
			</thead>
			<tbody id='tbody'>
			</tbody>
		</table>
	</div>
</div>
<script src="../assets/global/plugins/jquery.min.js"
	type="text/javascript"></script>
<script src="../assets/global/scripts/datatable.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/datatables/datatables.min.js"
	type="text/javascript"></script>
<script
	src="../assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
	type="text/javascript"></script>
<script src="../assets/global/plugins/sweetalert/sweetalert.min.js"></script>
<script>
    var status_list = [];
    status_list = JSON.parse('${status}');

    var borrow = [];
    borrow = JSON.parse('${borrows}');

    var type = [];
    type = JSON.parse('${type}');

    var s_filter = [];
    var t_filter = [];

    var removeElement = (a, i) => {
        let x = a.indexOf(i);
        return a.splice(x, 1);
    }

    var eStatus = (s) => {
        let label = "";
        status_list.forEach((i) => {
            if (s == i.statusId) {
                label = "<span class='btn btn-sm " + i.color + "'>" + i.description + "</span>";
            }
        });
        return label;
    }

    var eType = (t) => {
        let output = "";
        type.forEach((i) => {
            if (t == i.TypeID) {
                output = i.description;
            }
        });
        return output;
    }

    var search = () => {
        let sf = s_filter.join("|");
        let tf = t_filter.join("|");
        let table = $('#table').DataTable();

        table.column(11).search(sf, true);
        table.column(1).search(tf, true);
        table.draw();
    }

</script>

<c:if test="${selStatus != null && selType != null}">
	<script>
        var selected = () => {
            s_filter = [];
            t_filter = [];
            var selStatus = "${selStatus}";
            status_list.forEach((i) => {
                if (selStatus == i.statusId) {
                    s_filter.push("(" + i.description + ")");
                    $('#s_' + i.statusId).prop("checked", true);
                }
            })

            var selType = "${selType}";
            var type_split = selType.split(",");
            type_split.forEach((i) => {
                type.forEach((j) => {
                    if (i == j.TypeID) {
                        t_filter.push("(" + j.description + ")");
                        $('#t_' + j.TypeID).prop("checked", true);
                    }
                })
            })
        }
    </script>
</c:if>


<c:if test="${userAuthority.contains('equipment.edit')}">
	<script>
        var editButton = (id) => {
            result = "";
            result += "<a class='btn btn-xs blue' href='eEdit?id=" + id + "' title='Edit'><i class='fa fa-pencil'></i></a>";
            result += "<a class='btn btn-xs red' onclick='removeE(" + id + ")' title='Delete'><i class='fa fa-trash'></i></a>";
            return result;
        }
        var removeE = (id) => {
            $.ajax({
                url: "eCheckAllBorrow",
                data: {
                    "eId": id
                }
            }).done(function (r) {
                let b_check = r;

                if (b_check.length > 0) {
                    let body = document.createElement("div");
                    let head = document.createElement("p"); 
                
                    if (b_check.length > 1) {
                        let text = document.createTextNode("These records will be affected");
                        head.appendChild(text);
                    }
                    else {
                        let text = document.createTextNode("This record will be affected");
                        head.appendChild(text);
                    }

                    body.appendChild(head);

                    b_check.forEach((b) => {
                        let item = document.createElement("a");
                        item.setAttribute("href","eBorrowEdit?id="+b.borrowId);
                        item.setAttribute("style","display:block");
                        let borrow_id = " (#"+b.borrowId+")";
                        let text = document.createTextNode(b.userBorrowid + borrow_id);
                        item.appendChild(text);
                        body.appendChild(item);
                    });

                    swal({
                        title: "You can't delete this!",
                        content: body,
                        icon: "error"
                    });
                } else {
                    swal({
                        title: "Are you sure!",
                        text: "Will you continue to delete Equipment  : " + id,
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    }).then((selected)=>{
                        if (selected) {
                            document.location = "deleteE?id=" + id;
                        }
                    });
                }


            });

        }
    </script>
</c:if>

<c:if test="${userAuthority.contains('borrow.view')}">
	<script>
    var viewBorrow = (bId) => {
        let output = "";
        let borrowing = borrow.filter((b)=>{ return b.equipmentId == bId && ( b.status == "W" || b.status == "B") });
        borrowing.forEach((b)=>{
            output += '<p><a href="eBorrowEdit?id=' + b.borrowId + '">' + b.userBorrowid + '</a></p>';
        })
        return output;
    }
</script>
</c:if>

<script>
    var initTable = function () {

        var equipments = [];
        var borrows = [];

        equipments = JSON.parse('${equipments}');
        borrows = JSON.parse('${borrows}');

        var table = $('#table');

        var oTable = table.DataTable({
            "data": equipments,
            "columns": [
                { "data": "itemNo", "defaultContent": "" },
                { "data": "type", "defaultContent": "", "render": function (data) { return eType(data) } },
                { "data": "name", "defaultContent": "" },
                { "data": "equipmentId", "defaultContent": "" },
                { "data": "serialNo", "defaultContent": "" },
                { "data": "windows", "defaultContent": "" },
                { "data": "process", "defaultContent": "" },
                { "data": "ram", "defaultContent": "" },
                { "data": "hdd", "defaultContent": "" },
                { "data": "battery", "defaultContent": "" },
                { "data": "detail", "defaultContent": "" },
                { "data": "status", "defaultContent": "", "render": function (data) { return eStatus(data) } },
                { "data": "borrower", "defaultContent": "", "render": function (data, type, row) {let output = "";

                        <c:if test="${userAuthority.contains('borrow.view')}">
                        let id = row['equipmentId'];
                        output += viewBorrow(id);
                        </c:if>

                        return output;
                    }
                },
                { "data": "", "defaultContent": "",
                	"render": function (data, type, row) {
                        let output = "";
                        let id = data; 
                        let s = row["status"];
                       output += '<a class="btn btn-xs blue-dark" href="borrower-list?id='+row["equipmentId"]+'"><i class="fa fa-eye"></i>&nbsp<label>View</label></a>';
                    return output;
                    }
                },
                {   "data": "equipmentId",
                    "render": function (data, type, row) {
                        let output = "";
                        let id = data;
                        let s = row["status"];
                        if (s == "A") { output += '<a class="btn btn-xs green" id="'+id+'" name="borrow" title="Borrow"><i class="fa fa-share"></i></a>'; }

                        <c:if test="${userAuthority.contains('equipment.edit')}">
                            output += editButton(id);
                        </c:if>

                    return output;
                    }
                }
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
                [0, 'asc']
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

    }

    var initFilter = () => {
        status_list.forEach((i) => {
            let string = "";
            string = '<label class="mt-checkbox"><input type="checkbox" name="status" id="s_' + i.statusId + '" value="' + i.description + '">' + i.description + '<span></span></label>';
            $('#status').append(string);

            if (window.selected == undefined) { if (i.statusId != "Z") { $('#s_' + i.statusId).prop('checked', true); s_filter.push("(" + i.description + ")"); } }
        });

        type.forEach((i) => {
            let string = "";
            string = '<label class="mt-checkbox"><input type="checkbox" name="type" id="t_' + i.TypeID + '" value="' + i.description + '">' + i.description + '<span></span></label>';
            $('#type').append(string);

            if (window.selected == undefined) { $('#t_' + i.TypeID).prop('checked', true); t_filter.push("(" + i.description + ")"); }
        });

        $('#status').append("<br>");
        $('#status').append('<button class="btn btn-sm green" name="selectAll" id="status"> Select All </button>');
        $('#status').append('<button class="btn btn-sm default" name="selectNone" id="status"> Select None </button>');

        $('[name=selectAll]').click(function(){
            let filter = this.id;
            $('input[name='+filter+']').prop("checked",true);
            if(filter == "status"){
                s_filter = [];
                let selected = $('input[name=status]');
                for(let i=0; i<selected.length; i++){
                    s_filter.push("(" + selected[i].value + ")");
                }
            }
            if(filter == "type"){
                t_filter = [];
                let selected = $('input[name=type]');
                for(let i=0; i<selected.length; i++){
                    s_filter.push("(" + selected[i].value + ")");
                }
            }
            search();
        });
        $('[name=selectNone]').click(function(){
            let filter = this.id;
            $('input[name='+filter+']').prop("checked",false);
            if(filter == "status"){ s_filter = []; }
            if(filter == "type"){ t_filter = []; }
            search();
        });

    }

</script>
<script>
    $(() => {
        initFilter();
        initTable();
        if (window.selected != undefined) { selected(); }
        search();

        $('input[name="status"]').change(function () {
            if (this.checked) {
                s_filter.push("(" + this.value + ")");
            }
            else {
                removeElement(s_filter, "(" + this.value + ")");
            }
            search();
        });

        $('input[name="type"]').change(function () {
            if (this.checked) {
                t_filter.push("(" + this.value + ")");
            }
            else {
                removeElement(t_filter, "(" + this.value + ")");
            }
            search();
        });

        $('[name=borrow]').click(function(){
            let id = this.id;
            console.log(id);
            window.location.href = "eBorrow?id="+id;
        })
    })
</script>