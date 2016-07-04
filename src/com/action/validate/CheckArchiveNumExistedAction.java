package com.action.validate;

import com.Constants;
import org.hibernate.Session;

/**
 * Created by drc on 16-5-30.
 */
public class CheckArchiveNumExistedAction {
    private int archiveNum = -1;
    private boolean inexistent;

    public void setArchiveNum(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    public boolean isInexistent() {
        return inexistent;
    }

    private void check() {
        Session session = Constants.getSessionFactory().openSession();
        String hql = "from ArchivesEntity where archiveNum=" + archiveNum;
        inexistent = session.createQuery(hql).uniqueResult() == null;
        if (inexistent) {
            hql = "from ArchivesHistoryEntity where archiveNum=" + archiveNum;
            inexistent = session.createQuery(hql).uniqueResult() == null;
        }
        session.close();
    }

    public String execute() {
        check();
        return "success";
    }
}
