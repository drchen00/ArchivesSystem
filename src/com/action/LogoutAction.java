package com.action;

import com.Constants;
import com.bean.UserBean;
import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

/**
 * Created by drc on 16-5-5.
 */
public class LogoutAction {
    public String execute() throws Exception {
        UserBean user = UserBean.getCurrentUser();
        user.logout();
        return "success";
    }
}
