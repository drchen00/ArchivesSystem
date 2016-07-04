/**
 * Created by drc on 16-5-10.
 */
$(function () {
    var tagsTable = $('#tagsTable').DataTable({
        processing: true,
        serverSide: true,
        language: {
            "searchPlaceholder": "全局搜索",
            "url": 'Chinese.json'
        },
        ajax: {
            url: 'get_tags.action',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            dataSrc: "result",
            data: function (d) {
                return JSON.stringify(d);
            }
        },
        columns: [
            {
                "data": "num",
                'name': 'tagNum'
            }
        ]
    });
});