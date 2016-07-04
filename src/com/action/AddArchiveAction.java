package com.action;

import com.Constants;
import com.hibernate.ArchiveTraceEntity;
import com.hibernate.ArchivesEntity;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.Map;

/**
 * Created by drc on 16-5-25.
 */
public class AddArchiveAction {
    private String name;
    private Integer archiveNum = null;
    private String tagNum;
    private String createdTime;
    private boolean result = false;

    public void setName(String name) {
        this.name = name;
    }

    public void setArchiveNum(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isResult() {
        return result;
    }

    private void addArchive() throws Exception {
        Map map = ActionContext.getContext().getSession();
        Session session = Constants.getSessionFactory().openSession();
        Transaction transaction = null;
        ArchivesEntity archive = new ArchivesEntity();
        archive.setArchiveNum(archiveNum);
        archive.setName(name);
        archive.setTagNum(tagNum);
        archive.setStatus(0);
        ArchiveTraceEntity archiveTrace = new ArchiveTraceEntity();
        archiveTrace.setArchiveNum(archiveNum);
        archiveTrace.setUserId((int) map.get(Constants.getUserID()));
        archiveTrace.setAction(0);
        try {
            Date date = Constants.getSimpleDateFormat().parse(createdTime);
            archive.setCreatedTime(date);
            archiveTrace.setTime(date);
            transaction = session.beginTransaction();
            session.save(archive);
            session.save(archiveTrace);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            result = false;
            throw e;
        } finally {
            session.close();
        }
    }

    public String execute() {
        try {
            addArchive();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
