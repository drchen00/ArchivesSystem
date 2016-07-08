/**
 * Created by drc on 16-5-30.
 */
$(function () {
    $('#createdTime').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: 'zh-cn',
        ignoreReadonly: true,
        showTodayButton: true,
        showClear: true,
        defaultDate: moment()
    });

    $('form').validate({
        rules: {
            name: {
                required: true,
                remote: 'check_archive_name_existed.action'
            },
            archiveNum: {
                required: true,
                digits: true,
                remote: 'check_archive_num_existed.action'
            },
            tagNum: {
                required: true,
                tag: true,
                remote: 'check_tag_unused.action'
            }
        },
        ignore: '.ignore',
        messages: {
            name: {
                required: "请输入权证名",
                remote: '已存在该权证名'
            },
            archiveNum: {
                required: "请输入权证编号",
                digits: '权证编号必须是数字',
                remote: '已存在该编号'
            },
            tagNum: {
                required: '请获取标签编号',
                tag: '标签格式不正确',
                remote: '该标签已经被使用'
            }
        },
        success: function () {
            $('#createdTime').data('DateTimePicker').date(moment());
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

    socket.onmessage = function (event) {
        console.log(event.data);
        $('#tagNum').val(event.data);
    };
});
