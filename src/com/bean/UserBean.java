package com.bean;

import com.Constants;
import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

/**
 * Created by drc on 16-5-3.
 */
public class UserBean {
    private String name;
    private int id;

    public String getName() {
        Map session = ActionContext.getContext().getSession();
        return (String) session.get(Constants.getUserName());
    }

    public int getId() {
        Map session = ActionContext.getContext().getSession();
        return (int) session.get(Constants.getUserID());
    }
}
