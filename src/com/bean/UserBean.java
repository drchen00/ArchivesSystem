package com.bean;

import com.Constants;
import com.opensymphony.xwork2.ActionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by drc on 16-5-3.
 */
public class UserBean {
    private static Map<Integer, UserBean> onlineUser = new HashMap<>();
    private String name;
    private int id;
    private Map<String, Object> session;

    public UserBean(int id, String name) {
        this.id = id;
        this.name = name;
        session = ActionContext.getContext().getSession();
    }

    public UserBean(){
        session = ActionContext.getContext().getSession();
        id = (int) session.get(Constants.getUserID());
        name = (String) session.get(Constants.getUserName());
    }

    public static UserBean getCurrentUser(){
        Map session = ActionContext.getContext().getSession();
        return onlineUser.get((int) session.get(Constants.getUserID()));
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

    public int getId() {
        return id;
    }
}
