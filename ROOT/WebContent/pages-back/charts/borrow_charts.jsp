<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="portlet light bordered">
    <div class="portlet-title">
        <div class="caption">
            <i class="icon-bar-chart font-blue"></i> <span class="caption-subject font-red sbold ">BORROW CHART</span>
        </div>
        <div class="actions">
            <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title=""
                title=""> </a>
        </div>
    </div>
    <div class="portlet-body">
        <div class="form-group">
            <div id="selector" class="mt-checkbox-inline" style="text-align: center">
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div id="chart_div" style="min-height: 450px; width: 100%;"></div>
            </div>
        </div>
    </div>
</div>

<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script>
    var selType = [];

    var drawchart = (data) => {
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        var options = {};

        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
                var selected = data.getValue(selectedItem.row, 0);
                redirect(selected);
            }
        }

        options.colors = colors;

        google.visualization.events.addListener(chart, 'select', selectHandler);

        chart.draw(data, options);
    }

    var redirect = (s) => {
        for (let i = 0; i < e_status_list.length; i++) {
            let item = e_status_list[i];
            if (item.description == s) {
                let id = item.statusId;
                window.open("selectE?status=" + id + "&type=" + selType.join(','), "_blank");
            }
        }
    }

    var removeElement = (a, i) => {
        let x = a.indexOf(i);
        return a.splice(x, 1);
    }

    var getColorCode = (c) => {
        let code = "";
        switch (c) {
            case "default": code = "#E1E5EC"; break;
            case "red": code = "#E7505A"; break;
            case "red-mint": code = "#E43A45"; break;
            case "blue": code = "#3598DC"; break;
            case "blue-steel": code = "#4B77BE"; break;
            case "green": code = "#32C5D2"; break;
            case "green-jungle": code = "#26C281"; break;
            case "yellow": code = "#C49F47"; break;
            case "yellow-lemon": code = "#F7CA18"; break;
            case "purple": code = "#8E44AD"; break;
            case "purple-seance": code = "#9A12B3"; break;
            case "grey-mint": code = "#525E64"; break;
            case "dark": code = "#2F353B"; break;
            default: code = "#E1E5EC"; break;
        }
        return code;
    }
</script>
<c:if test="${info != null }">
    <script>
        var colors = [];
        var e_status_list = [];
        e_status_list = JSON.parse('${status}');

        e_status_list.forEach((s) => {
            colors.push(getColorCode(s.color));
        })

        var e_type_list = [];
        // e_type_list = [{ "id": "c", "description": "Computer" }, { "id": "o", "description": "Other" }];
        e_type_list = JSON.parse('${type}');

        $(() => {
            var info = [];
            info = JSON.parse('${info}');

            //e = equipment
            //Array that store number of e separate by e type
            var chart_info = [];

            e_type_list.forEach((t) => {
                let json = {};
                json['type'] = t.TypeID;
                json['data'] = [];
                e_status_list.forEach((s) => {
                    if (s.statusId != "Z") {
                        let data_json = {};
                        let count = info.filter((x) => {
                            return x.status == s.statusId && x.type == t.TypeID
                        });
                        data_json['id'] = s.statusId;
                        data_json['count'] = count.length;
                        data_json['description'] = s.description;
                        json['data'].push(data_json);
                    }

                })
                chart_info.push(json);
                selType.push(t.TypeID);
            })
            //

            var choice = "";
            e_type_list.forEach((c) => {
                choice += '<label class="mt-checkbox">';
                choice += '<input type="checkbox" id="' + c.TypeID + '" value="' + c.TypeID + '" name="choice" checked>' + c.description + '<span></span>';
                choice += '</label>';
            });

            $('#selector').html(choice);

            //init pie chart data
            var chart = [];
            e_status_list.forEach((s) => {
                chart.push([s.description, 0]);
            });

            chart_info.forEach((t) => {
                for (let i = 0; i < t.data.length; i++) {
                    let count = t.data[i].count;
                    chart[i][1] += count;
                }
            });

            google.charts.load('current', { 'packages': ['corechart', 'table'] });
            google.charts.setOnLoadCallback(() => {
                let data = new google.visualization.DataTable();
                data.addColumn('string', 'Status');
                data.addColumn('number', 'total');
                data.addRows(chart);
                drawchart(data);
            });
            //

            $('input[type="checkbox"]').click(function () {
                let t = this.value;

                let isChecked = (id) => {
                    if ($("#" + id + ":checked").length == 1) { return true }
                }

                let plus = (type) => {
                    chart_info.forEach((t) => {
                        if (t['type'] == type) {
                            for (let i = 0; i < t.data.length; i++) {
                                let count = t.data[i].count;
                                chart[i][1] += count;
                            }
                        }
                    })
                }

                let minus = (type) => {
                    chart_info.forEach((t) => {
                        if (t['type'] == type) {
                            for (let i = 0; i < t.data.length; i++) {
                                let count = t.data[i].count;
                                chart[i][1] -= count;
                            }
                        }
                    })
                }

                if (isChecked(t)) { plus(t); selType.push(t); }
                if (!isChecked(t)) { minus(t); removeElement(selType, t); }
                let data = new google.visualization.DataTable();
                data.addColumn('string', 'Status');
                data.addColumn('number', 'total');
                data.addRows(chart);
                drawchart(data);
            });

            $(window).resize(() => {
                let data = new google.visualization.DataTable();
                data.addColumn('string', 'Status');
                data.addColumn('number', 'total');
                data.addRows(chart);
                drawchart(data);
            });
        });
    </script>
</c:if>