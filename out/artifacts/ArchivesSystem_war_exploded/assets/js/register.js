/**
 * Created by drc on 16-6-30.
 */
$(function () {
    $.validator.addMethod('matchPassword', function (value, element) {
        var password = $('div#signup form input[name="password"]').val();
        return this.optional(element) || value == password;
    }, '和密码不一致')

    $('div#signup form').validate({
        rules: {
            account: {
                required: true,
                remote: 'check_register_account_inexistent.action'
            },
            id: {
                required: true,
                digits: true,
                remote: 'check_register_id_inexistent.action'
            },
            name: {
                required: true
            },
            password: {
                required: true
            },
            rePassword: {
                required: true,
                matchPassword: true
            }
        },
        messages: {
            account: {
                remote: '用户名已经被使用'
            },
            id: {
                remote: 'ID已经被使用'
            }
        },
        errorClass: 'help-block',
        errorElement: 'span',
        highlight: function (element, errorClass, validClass) {
            $(element).removeClass('has-success').addClass('has-error');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('has-error').addClass('has-success');
        }
    })
});