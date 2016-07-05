/**
 * Created by drc on 16-7-5.
 */
$(function () {
    var time = 3;

    function countdown() {
        console.log(time);
        $('#second').text(time);
        if (time == 0) {
            window.history.go(-1);
        }
        if (time > 0) {
            time--;
            setTimeout(countdown, 1000);
        }
    }

    countdown();
});
