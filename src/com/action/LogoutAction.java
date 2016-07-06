package com.action;

import com.bean.UserBean;

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
