package com.bean;

import com.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.webSocket.WebSocket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by drc on 16-5-3.
 * 服务器在线用户管理
 */
public class UserBean {
    private static Map<String, UserBean> onlineUser = new HashMap<>();
    private String name;
    private String id;
    private Map<String, Object> session;
    private WebSocket webSocket;

    public UserBean(String id, String name) {
        this.id = id;
        this.name = name;
        session = ActionContext.getContext().getSession();
    }

    public UserBean(){
        session = ActionContext.getContext().getSession();
        id = (String) session.get(Constants.getUserID());
        name = (String) session.get(Constants.getUserName());
    }

    public static UserBean getCurrentUser(){
        Map session = ActionContext.getContext().getSession();
        return onlineUser.get((String) session.get(Constants.getUserID()));
    }

    public static UserBean getUserByID(String id){
        return onlineUser.get(id);
    }

    public void login(){
        session.put(Constants.getUserID(), id);
        session.put(Constants.getUserName(), name);
        if(onlineUser.containsKey(id)){
            onlineUser.get(id).logout();
        }
        onlineUser.put(id, this);
    }

    public void logout(){
        session.clear();
        if(onlineUser.containsKey(id)){
            onlineUser.remove(id);
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }
}
