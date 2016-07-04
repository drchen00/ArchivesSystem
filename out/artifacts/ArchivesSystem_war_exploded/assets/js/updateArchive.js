/**
 * Created by drc on 16-6-30.
 */
$(function () {
    $('table#archivesTable tbody').on('dblclick', 'tr', function () {
        var row = archivesTable.row(this);
        var rowData = row.data();
        console.log(rowData);
        archivesTable.rows().deselect();
        row.select();

        $('#name').val(rowData.name);
        $('#archiveNum').val(rowData.num);
        $('#helpModal').modal('show');
    });

    $('div#helpModal form').validate({
        rules:{
            name:{
                required:true,
                remote:'check_archive_name_existed.action'
            }
        },
        messages:{
            name:{
                remote:'权证名已存在'
            }  
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

    $('#submitUpdate').click(function () {
        $('div#helpModal form').submit();
    });
});
