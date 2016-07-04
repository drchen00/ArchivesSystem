package com.action;

import com.Constants;
import com.hibernate.ArchivesEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by drc on 16-6-30.
 */
public class UpdateArchiveAction {
    private int archiveNum;
    private String name;

    public void setArchiveNum(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void update() throws Exception {
        Session session = Constants.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            ArchivesEntity archive = (ArchivesEntity) session.load(ArchivesEntity.class, archiveNum);
            archive.setName(name);
            session.save(archive);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
            throw e;
        }finally {
            session.close();
        }
    }

    public String execute(){
        try {
            update();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
