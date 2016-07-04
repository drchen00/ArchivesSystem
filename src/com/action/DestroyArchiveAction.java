package com.action;

import com.Constants;
import com.hibernate.*;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by drc on 16-5-27.
 */
public class DestroyArchiveAction {
    private int archiveNum;
    private String tagNum;
    private String time;
    private boolean result = false;

    public void setArchiveNum(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isResult() {
        return result;
    }

    private void destroyArchive() throws Exception {
        Map map = ActionContext.getContext().getSession();
        Session session = Constants.getSessionFactory().openSession();
        Transaction transaction = null;
        ArchivesHistoryEntity archiveHistory = new ArchivesHistoryEntity();
        try {
            transaction = session.beginTransaction();
            ArchivesEntity archive = (ArchivesEntity) session.load(ArchivesEntity.class, archiveNum);
            if (!checkStatus(archive.getStatus())) {
                result = false;
                return;
            }
            archiveHistory.setArchiveNum(archive.getArchiveNum());
            archiveHistory.setName(archive.getName());
            session.save(archiveHistory);

            ArchiveTraceHistoryEntity traceHistory = new ArchiveTraceHistoryEntity();
            traceHistory.setAction(3);
            traceHistory.setArchiveNum(archiveNum);
            traceHistory.setUserId((int) map.get(Constants.getUserID()));
            traceHistory.setTime(Constants.getSimpleDateFormat().parse(time));
            session.save(traceHistory);

            String hql = "from ArchiveTraceEntity where archiveNum = " + archiveNum;
            List traceList = session.createQuery(hql).list();
            for (Object aTraceList : traceList) {
                ArchiveTraceEntity trace = (ArchiveTraceEntity) aTraceList;
                traceHistory = new ArchiveTraceHistoryEntity();
                traceHistory.setArchiveNum(trace.getArchiveNum());
                traceHistory.setUserId(trace.getUserId());
                traceHistory.setTime(trace.getTime());
                traceHistory.setAction(trace.getAction());
                session.save(traceHistory);
                session.delete(trace);
            }

            hql = "delete CheckResultsEntity where tagNum = '" + archive.getTagNum() + "'";
            session.createQuery(hql).executeUpdate();

            session.delete(archive);
            TagsEntity tag = (TagsEntity) session.load(TagsEntity.class, tagNum);
            session.delete(tag);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    private boolean checkStatus(int status) {
        return status == 0;
    }

    public String execute() {
        try {
            destroyArchive();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
