/**
 * Created by drc on 16-6-3.
 */
// console.log(window.location.hostname);
var webSocketURL = 'ws://' + '10.42.0.170' + ':' + '7000' + '/lyxweb' + '?userID=' + userID;
var socket = new WebSocket(webSocketURL);
socket.onerror = function (event) {
    console.log('ERROR:' + JSON.stringify(event));
};

socket.onclose = function (event) {
    var reason;
    alert("与手持机服务连接中断,错误码：" + event.code);
    // See http://tools.ietf.org/html/rfc6455#section-7.4.1
    if (event.code == 1000)
        reason = "Normal closure, meaning that the purpose for which the connection was established has been fulfilled.";
    else if (event.code == 1001)
        reason = "An endpoint is \"going away\", such as a server going down or a browser having navigated away from a page.";
    else if (event.code == 1002)
        reason = "An endpoint is terminating the connection due to a protocol error";
    else if (event.code == 1003)
        reason = "An endpoint is terminating the connection because it has received a type of data it cannot accept (e.g., an endpoint that understands only text data MAY send this if it receives a binary message).";
    else if (event.code == 1004)
        reason = "Reserved. The specific meaning might be defined in the future.";
    else if (event.code == 1005)
        reason = "No status code was actually present.";
    else if (event.code == 1006)
        reason = "The connection was closed abnormally, e.g., without sending or receiving a Close control frame";
    else if (event.code == 1007)
        reason = "An endpoint is terminating the connection because it has received data within a message that was not consistent with the type of the message (e.g., non-UTF-8 [http://tools.ietf.org/html/rfc3629] data within a text message).";
    else if (event.code == 1008)
        reason = "An endpoint is terminating the connection because it has received a message that \"violates its policy\". This reason is given either if there is no other sutible reason, or if there is a need to hide specific details about the policy.";
    else if (event.code == 1009)
        reason = "An endpoint is terminating the connection because it has received a message that is too big for it to process.";
    else if (event.code == 1010) // Note that this status code is not used by the server, because it can fail the WebSocket handshake instead.
        reason = "An endpoint (client) is terminating the connection because it has expected the server to negotiate one or more extension, but the server didn't return them in the response message of the WebSocket handshake. <br /> Specifically, the extensions that are needed are: " + event.reason;
    else if (event.code == 1011)
        reason = "A server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.";
    else if (event.code == 1015)
        reason = "The connection was closed due to a failure to perform a TLS handshake (e.g., the server certificate can't be verified).";
    else
        reason = "Unknown reason";

    alert("The connection was closed for reason: " + reason);
};

function refresh(sendCommand) {
    $('#helpModal').modal('show');
    var modalContent = $('div#helpModal p');
    switch (socket.readyState) {
        case 0:
            modalContent.text("正在连接服务器");
            socket.onopen = function () {
                sendCommand();
                socket.onopen = null;
            };
            break;
        case 1:
            sendCommand();
            break;
        case 2:
            modalContent.text("正在关闭连接，请重试");
            break;
        case 3:
            socket = new WebSocket(webSocketURL);
            modalContent.text("正在重连服务器");
            socket.onopen = function () {
                $('div#helpModal p').text("正在获取标签");
                sendCommand();
                socket.onopen = null;
            };
            break;
    }
}

function cancel(sendCommand) {
    switch (socket.readyState) {
        case 0:
            socket.close();
            break;
        case 1:
            sendCommand();
            break;
        case 2:
            break;
        case 3:
            alert('连接已关闭或无法建立连接，请刷新页面');
            break;
    }
}

var getTagMsg = {
    'type': 'Scan',
    'command': 'GetTag',
    'info': null,
    'data': null
};

getTagMsg = JSON.stringify(getTagMsg);

var cancelTagMsg = {
    'type': 'Scan',
    'command': 'Cancel',
    'info': null,
    'data': null
};

cancelTagMsg = JSON.stringify(cancelTagMsg);

var cancelCheckMsg = JSON.stringify({
    'type': 'Check',
    'command': 'Cancel',
    'info': null,
    'data': null
});

function cancelTag() {
    socket.send(cancelTagMsg);
    socket.onmessage = function (event) {
        var data = JSON.parse(event.data);
        if (data.type == 'Scan' && data.success) {
        } else {
            alert('ERROR:' + data.info + '\n 请刷新页面');
        }
        socket.onmessage = null;
    };
}

function cancelCheck() {
    socket.send(cancelCheckMsg);
    socket.onmessage = function (event) {
        var data = JSON.parse(event.data);
        if (data.type == 'Check' && data.success) {
        } else {
            alert('ERROR:' + data.info + '\n 请刷新页面');
        }
        socket.onmessage = null;
    };
}