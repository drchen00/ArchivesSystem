package com.action;

import com.Constants;
import com.hibernate.ArchiveTraceEntity;
import com.hibernate.ArchivesEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Map;

/**
 * Created by drc on 16-5-26.
 */
public abstract class SwitchArchiveStatusAction extends ActionSupport {
    protected boolean result = false;
    private int archiveNum = -1;
    private String time;

    public boolean isResult() {
        return result;
    }

    public void setArchiveNum(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected abstract int getStatus();

    protected abstract int getAction();

    protected abstract boolean checkStatus(int status);

    private void updateStatus() throws Exception {
        Map map = ActionContext.getContext().getSession();
        Session session = Constants.getSessionFactory().openSession();
        ArchiveTraceEntity archiveTrace = new ArchiveTraceEntity();
        archiveTrace.setAction(getAction());
        archiveTrace.setArchiveNum(archiveNum);
        archiveTrace.setUserId((int) map.get(Constants.getUserID()));
        Transaction transaction = null;
        try {
            archiveTrace.setTime(Constants.getSimpleDateFormat().parse(time));
            transaction = session.beginTransaction();
            ArchivesEntity archive = (ArchivesEntity) session.load(ArchivesEntity.class, archiveNum);
            if (!checkStatus(archive.getStatus())) {
                result = false;
                return;
            }
            archive.setStatus(getStatus());
            session.update(archive);
            session.save(archiveTrace);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public String execute() {
        try {
            updateStatus();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}
