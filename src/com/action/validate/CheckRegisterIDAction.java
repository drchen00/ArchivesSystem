package com.action.validate;

import com.Constants;
import org.hibernate.Session;

/**
 * Created by drc on 16-6-30.
 */
public class CheckRegisterIDAction {
    private int id = -1;
    private boolean inexistent;

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInexistent() {
        return inexistent;
    }

    private void check() {
        Session session = Constants.getSessionFactory().openSession();
        String hql = "from AccountEntity where id = " + id;
        inexistent = session.createQuery(hql).uniqueResult() == null;
        session.close();
    }

    public String execute() {
        check();
        return "success";
    }
}
