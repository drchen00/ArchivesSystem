package com;

import com.bean.UserBean;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by drc on 16-6-27.
 */
public class OnlineUserListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        if (session.getAttribute(Constants.getUserID()) == null) return;
        UserBean user = UserBean.getCurrentUser();
        user.logout();
    }
}
