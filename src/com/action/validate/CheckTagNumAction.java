package com.action.validate;

import com.Constants;
import com.hibernate.ArchivesEntity;
import com.hibernate.TagsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by drc on 16-5-25.
 */
public class CheckTagNumAction {
    private String tagNum;
    private boolean used = false;
    private boolean unused = false;

    private String name;
    private int archiveNum = -1;
    private Map<String, Object> status;
    private String createdTime;

    public String getName() {
        return name;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public int getArchiveNum() {
        return archiveNum;
    }

    public Map getStatus() {
        return status;
    }

    public boolean isUsed() {
        return used;
    }

    public boolean isUnused() {
        return unused;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    public String execute() {
        check();
        return "success";
    }

    private void check() {
        Session session = Constants.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from TagsEntity where tagNum = '" + tagNum + "' ";
            if (session.createQuery(hql).uniqueResult() == null) {
                TagsEntity tag = new TagsEntity();
                tag.setTagNum(tagNum);
                session.save(tag);
                used = false;
                unused = true;
            } else {
                hql = "from ArchivesEntity where tagNum = '" + tagNum + "'";
                ArchivesEntity archive = (ArchivesEntity) session.createQuery(hql).uniqueResult();
                if (archive != null) {
                    name = archive.getName();
                    archiveNum = archive.getArchiveNum();
                    status = new HashMap<>();
                    status.put("data", archive.getStatus());
                    status.put("display", Constants.getArchivesStatusDict().get(archive.getStatus()));
                    createdTime = Constants.getSimpleDateFormat().format(archive.getCreatedTime());
                    used = true;
                    unused = false;
                } else {
                    used = false;
                    unused = true;
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            used = false;
            unused = false;
        } finally {
            session.close();
        }

    }
}
