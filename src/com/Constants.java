package com;

import com.hibernate.ArchivesStatusDictEntity;
import com.hibernate.CheckConditionsDictEntity;
import com.hibernate.ExceptionInfoDictEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by drc on 16-5-5.
 */
public class Constants {
    private static final String USER_ID = "sessionID";
    private static final String USER_NAME = "sessionName";
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
    private static final Map ARCHIVES_STATUS_DICT = queryArchiveStatus();
    private static final Map CHECK_CONDITIONS_DICT = queryCheckConditions();
    private static final Map EXCEPTION_INFO_DICT = queryExceptionInfoDict();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getUserID() {
        return USER_ID;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return SIMPLE_DATE_FORMAT;
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static Map getArchivesStatusDict() {
        return ARCHIVES_STATUS_DICT;
    }

    public static Map getCheckConditionsDict() {
        return CHECK_CONDITIONS_DICT;
    }

    public static Map getExceptionInfoDict() {
        return EXCEPTION_INFO_DICT;
    }

    private static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        return cfg.buildSessionFactory(serviceRegistry);
    }

    private static Map queryArchiveStatus() {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.createQuery("from ArchivesStatusDictEntity as status");
        List list = query.list();
        session.close();
        Iterator iterator = list.iterator();
        Map<Object, Object> map = new HashMap<>();
        while (iterator.hasNext()) {
            ArchivesStatusDictEntity status = (ArchivesStatusDictEntity) iterator.next();
            map.put(status.getId(), status.getName());
            map.put(status.getName(), status.getId());
        }
        return map;
    }

    private static Map queryCheckConditions() {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.createQuery("from CheckConditionsDictEntity ");
        List list = query.list();
        session.close();
        Iterator iterator = list.iterator();
        Map<Object, Object> map = new HashMap<>();
        while (iterator.hasNext()) {
            CheckConditionsDictEntity condition = (CheckConditionsDictEntity) iterator.next();
            map.put(condition.getId(), condition.getType());
            map.put(condition.getType(), condition.getId());
        }
        return map;
    }

    private static Map queryExceptionInfoDict() {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.createQuery("from ExceptionInfoDictEntity ");
        List list = query.list();
        Iterator iterator = list.iterator();
        Map<Object, Object> map = new HashMap<>();
        while (iterator.hasNext()) {
            ExceptionInfoDictEntity exception = (ExceptionInfoDictEntity) iterator.next();
            map.put(exception.getId(), exception.getType());
            map.put(exception.getType(), exception.getId());
        }
        return map;
    }
}
