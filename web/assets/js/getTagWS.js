/**
 * Created by drc on 16-6-3.
 */
// console.log(window.location.hostname);
var webSocketURL = 'ws://' + window.location.hostname + ':' + '8080' + '/webSocket/getTag';
var socket = new WebSocket(webSocketURL);
socket.onerror = function (event) {
    console.log('ERROR:' + JSON.stringify(event));
};
