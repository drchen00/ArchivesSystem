package com.action;

/**
 * Created by drc on 16-4-27.
 * 用户登陆动作的执行
 */

import com.Constants;
import com.bean.UserBean;
import com.hibernate.AccountEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginAction {
    private String username;
    private String password;
    private boolean mobile;
    private String flag = "fail";
    private boolean success;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() {
        try {
            idenfication(username, password);
            return flag;
        } catch (Exception e) {
            System.err.print(e);
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
                String id;
                if(mobile){
                    id = account.getId()+"M";
                }else {
                    id = Integer.toString(account.getId());
                }
                UserBean user = new UserBean(id, account.getName());
                user.login();
                success = true;
                flag = "success";
            } else {
                flag = "fail";
            }
        }
        hsession.close();
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public boolean isSuccess() {
        return success;
    }
}
