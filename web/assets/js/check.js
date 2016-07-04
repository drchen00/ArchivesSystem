/**
 * Created by drc on 16-6-7.
 */
$(function () {
    $('#newCheck').click(function () {
        $('.dataTables_filter', archivesTable.table().container()).hide();
        var checkStart = $('#checkStart').closest('.box').show('slow');
        checkStart.closest('.box').children('.collapse').collapse('show');
        $('input.form-control', checkStart).val(null);
        $('html,body').animate({scrollTop: checkStart.offset().top}, 500);
        setTimeout(function () {
            archivesTable.columns.adjust();
            archivesTable.columns().search('');
            archivesTable.column(3).search($('#status').val()).draw();
        }, 500);
    });

    $('.remove-row').click(function () {
        $(this).closest('.row').remove();
    });

    var checkRecord = $('#checkRecord').DataTable({
        processing: true,
        serverSide: true,
        searching: false,
        select: {
            style: 'single',
            blurable: true
        },
        language: {
            "url": 'Chinese.json'
        },
        ajax: {
            url: 'get_check_records.action',
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
                data: 'id',
                name: 'id'
            },
            {
                data: 'condition',
                name: 'checkCondition',
                render: {
                    _: 'display',
                    filter: 'data'
                }
            },
            {
                data: 'content',
                name: 'checkContent'
            },
            {
                data: 'user',
                name: 'userId'
            },
            {
                data: 'startTime',
                name: 'startTime'
            },
            {
                data: 'endTime',
                name: 'endTime'
            }
        ]
    });

    $('table#checkRecord tbody').on('dblclick', 'tr', function () {
        var row = checkRecord.row(this);
        var rowData = row.data();
        console.log(rowData);
        checkRecord.rows().deselect();
        row.select();

        var resultDivID = $('#' + rowData.id);
        if (resultDivID.length == 0) {
            var newResult = $('#demo-result').clone(true);
            $('h5', newResult).text('编号' + rowData.id + '盘点结果');
            $('.body', newResult).attr('id', rowData.id);
            $('.minimize-box', newResult).attr('href', '#' + rowData.id);
            newResult.appendTo('.inner');
            $('.box', newResult).show('slow');

            var table = $('table', newResult).DataTable({
                processing: true,
                serverSide: true,
                searching: false,
                language: {
                    "url": 'Chinese.json'
                },
                ajax: {
                    url: 'get_check_results.action',
                    dataSrc: "result",
                    type: 'post',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: function (d) {
                        d.condition = rowData.condition.data;
                        d.content = rowData.content;
                        d.recordNum = rowData.id;
                        d.startTime = rowData.startTime;
                        d.endTime = rowData.endTime;
                        return JSON.stringify(d);
                    }
                },
                columns: [
                    {
                        data: 'num',
                        name: 'archiveNum'
                    },
                    {
                        data: 'name',
                        name: 'name'
                    },
                    {
                        data: 'info',
                        // orderable: false,
                        render: {
                            _: 'display',
                            filter: 'data'
                        }
                    }
                ]
            });

            setTimeout(function () {
                $('html,body').animate({scrollTop: $('.body', newResult).offset().top}, 500);
                table.columns.adjust().draw();
            }, 600);
        } else {
            resultDivID.closest('.box').children('.collapse').collapse('show');
            $('html,body').animate({scrollTop: resultDivID.offset().top}, 500);
        }
    });

    var checkMsg;

    function startCheck() {
        $('div#helpModal p').text("正在下载盘点数据");
        socket.send(checkMsg);
        socket.onmessage = function (event) {
            var data = JSON.parse(event.data);
            if (data.success) {
                alert('盘点数据下载完成，盘点完成后请重新刷新页面');
                $('#checkStart').closest('.box').hide('slow');
            } else {
                alert('Error:' + data.info + "\n请重试");
            }
            $('#helpModal').modal('hide');
            socket.onmessage = null;
        }
    }

    $('div#checkStart button').click(function () {
        checkMsg = JSON.stringify({
            'type': 'Check',
            'command': 'Check',
            'info': null,
            'data': JSON.stringify({
                'userID': userID,
                'condition': 0,
                'content': JSON.stringify({
                    'StartTime': $('#startTime').val(),
                    'EndTime': $('#endTime').val()
                })
            })
        });
        if (archivesTable.data().length == 0) {
            alert('盘点列表没有数据');
        } else {
            refresh(startCheck);
        }
    });

    $('div#helpModal button').click(function () {
        cancel(cancelCheck);
    });
});