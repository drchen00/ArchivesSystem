package com.action;

import com.Constants;
import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

/**
 * Created by drc on 16-5-5.
 */
public class LogoutAction {
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        Constants.getOnlineUser().remove(session.get(Constants.getUserID()));
        session.clear();
        return "success";
    }
}
