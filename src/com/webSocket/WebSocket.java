package com.webSocket;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * Created by drc on 16-7-6.
 */
public interface WebSocket {
    void send(String msg);
}
