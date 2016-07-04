package com.action;

import com.Constants;
import com.hibernate.AccountEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by drc on 16-6-30.
 */
public class RegisterAction {
    private String account;
    private int id;
    private String password;
    private String name;

    public void setAccount(String account){
        this.account = account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void register() throws Exception {
        AccountEntity user = new AccountEntity();
        user.setId(id);
        user.setAccount(account);
        user.setName(name);
        user.setPassword(password);
        Session session = Constants.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw e;
        }finally {
            session.close();
        }
    }

    public String execute(){
        try {
            register();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
