package com.action.validate;

import com.Constants;
import org.hibernate.Session;

/**
 * Created by drc on 16-5-30.
 */
public class CheckArchiveNameExistedAction {
    private String name;
    private boolean inexistent;

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInexistent() {
        return inexistent;
    }

    private void check() {
        Session session = Constants.getSessionFactory().openSession();
        String hql = "from ArchivesEntity where name = '" + name + "'";
        inexistent = session.createQuery(hql).uniqueResult() == null;
        session.close();
    }

    public String execute() {
        check();
        return "success";
    }
}
