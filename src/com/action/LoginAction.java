package com.action;

/**
 * Created by drc on 16-4-27.
 * 用户登陆动作的执行
 */

import com.Constants;
import com.bean.UserBean;
import com.hibernate.AccountEntity;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Map;

public class LoginAction {
    private String username;
    private String password;
    private String flag = "fail";
    private String errInfo;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public String execute() {
        try {
            idenfication(username, password);
            return flag;
        } catch (Exception e) {
            System.err.print(e);
            errInfo = e.toString();
            return "error";
        }
    }

    private void idenfication(String name, String psw) {
        Session hsession = Constants.getSessionFactory().openSession();
        Transaction transaction = hsession.beginTransaction();
        if (name != null && !"".equals(name)) {
            Query query = hsession.createQuery("from AccountEntity where account='" + name + "' and password='" + psw + "'");
            AccountEntity account = (AccountEntity) query.uniqueResult();
            transaction.commit();
            if (account != null) {
                UserBean user = new UserBean(account.getId(), account.getName());
                user.login();
                flag = "success";
            } else {
                errInfo = "用户名密码不匹配";
                flag = "fail";
            }
        }
        hsession.close();
    }
}
