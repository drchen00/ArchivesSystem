/**
 * Created by drc on 16-5-16.
 */

var archivesTable;
$(function () {
    $('.date').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: 'zh-cn',
        ignoreReadonly: true,
        showTodayButton: true,
        showClear: true
        // maxDate: moment()
    });

    archivesTable = $('#archivesTable').DataTable({
        processing: true,
        serverSide: true,
        select: {
            style: 'single',
            blurable: true
        },
        language: {
            "searchPlaceholder": "全局搜索(状态、日期除外）",
            "url": 'Chinese.json'
        },
        ajax: {
            url: 'get_archives.action',
            dataSrc: "result",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: function (d) {
                return JSON.stringify(d);
            }
        },
        columns: [
            {
                "data": "num",
                "name": 'archiveNum'
            },
            {
                "data": 'name',
                'name': 'name'
            },
            {
                'data': 'tagNum',
                'name': 'tagNum'
            },
            {
                'data': 'status',
                'name': 'status',
                'render': {
                    _: 'display',
                    filter: 'data'
                }
            },
            {
                'data': 'date',
                'name': 'createdTime',
                'width': '320px'
            }
        ],
        initComplete: function () {
            archivesTable.columns().every(function () {
                var that = this;
                var timeout;
                $('.form-control', this.footer()).on('input change', function () {
                    var input = this;
                    clearTimeout(timeout);
                    if (that.search() !== this.value) {
                        timeout = setTimeout(function () {
                            that.search(input.value).draw();
                        }, 400);
                    }
                });
                $('.date', this.footer()).on('dp.change', function () {
                    if (that.search() !== this.value) {
                        that.search($('#startTime').val() + '|' + $('#endTime').val()).draw();
                    }
                });
            });
        }
    });
});