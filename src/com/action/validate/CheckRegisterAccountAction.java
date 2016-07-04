package com.action.validate;

import com.Constants;
import org.hibernate.Session;

/**
 * Created by drc on 16-6-30.
 */
public class CheckRegisterAccountAction {
    private String account;
    private boolean inexistent;

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isInexistent() {
        return inexistent;
    }

    private void check() {
        Session session = Constants.getSessionFactory().openSession();
        String hql = "from AccountEntity where account = '" + account + "'";
        inexistent = session.createQuery(hql).uniqueResult() == null;
        session.close();
    }

    public String execute() {
        check();
        return "success";
    }
}
