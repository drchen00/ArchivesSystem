/**
 * Created by drc on 16-5-30.
 */
$(function () {
    $('#time').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: 'zh-cn',
        ignoreReadonly: true,
        showTodayButton: true,
        showClear: true,
        defaultDate: moment()
    });

    var statusCode = -1;
    var validator = $('form').validate({
        rules: {
            tagNum: {
                required: true,
                tag: true,
                remote: {
                    url: 'check_tag_used.action',
                    type: 'post',
                    async: false
                }
            },
            status: {
                required: true,
                remote: {
                    url: 'check_archive_in_store.action',
                    type: 'post',
                    data: {
                        status: function () {
                            return statusCode;
                        }
                    }
                }
            },
            archiveNum:{
                required: true,
                digits: true
            },
            ignore: '.ignore'
        },
        messages: {
            tagNum: {
                required: '请获取标签编号',
                remote: '该标签没有被使用',
                tag: '标签格式不正确'
            },
            status: {
                required: '请获取状态',
                remote: '只有在库中才能执行'
            }
        },
        success: function () {
            $('#time').data('DateTimePicker').date(moment());
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parents('.col-md-8'));
        },
        errorClass: 'help-block col-lg-6',
        errorElement: 'span',
        highlight: function (element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-success').addClass('has-error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error').addClass('has-success');
        }
    });

    function getTag() {
        $('div#helpModal p').text("正在获取标签");
        socket.send(getTagMsg);
        socket.onmessage = function (event) {
            validator.resetForm();
            $('form')[0].reset();
            var data = JSON.parse(event.data);
            if (data.success) {
                var tagNum = $('#tagNum').val(data.data);
                tagNum.valid();
                if (tagNum.valid()) {
                    $.ajax({
                        url: 'get_archive.action',
                        data: {
                            tagNum: tagNum.val()
                        },
                        success: function (data) {
                            $('#name').val(data.name);
                            $('#archiveNum').val(data.archiveNum);
                            if (data.status != null) {
                                $('#status').val(data.status.display);
                                statusCode = data.status.data;
                            } else {
                                $('#status').val(null);
                            }
                            $('#createdTime').val(data.createdTime);
                        },
                        complete: function () {
                            if ($('#status').valid()) {
                                $('#time').data('DateTimePicker').date(moment());
                            }
                        }
                    });
                }
            } else {
                alert('Error:' + data.info + "\n请重试");
            }
            $('#helpModal').modal('hide');
            socket.onmessage = null;
        }
    }

    $('input#tagNum+span').click(function () {
        refresh(getTag);
    });

    $('div#helpModal button').click(function () {
        cancel(cancelTag);
    });
});
