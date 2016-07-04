package com.action;

import com.Constants;
import com.bean.OrderBean;
import com.hibernate.ArchiveTraceEntity;
import com.hibernate.ArchivesEntity;
import com.hibernate.CheckResultsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.util.*;

/**
 * Created by drc on 16-6-13.
 */
public class GetCheckResultsAction extends TableAction {
    private int condition = -1;
    private String content;
    private int recordNum;
    private String startTime;
    private String endTime;
    private Map<String, String> contentMap = new HashMap<>();
    private List errorResults;

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private void parseContent() {
        String[] KVList = content.split("\\|");
        for (String KV : KVList) {
            String[] temp = KV.split(":", 2);
            contentMap.put(temp[0], temp[1]);
        }
    }

    private void setErrorResults() {
        Session session = Constants.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from CheckResultsEntity where checkId = " + recordNum;
        errorResults = session.createQuery(hql).list();
        transaction.commit();
        session.close();
    }

    private boolean isLegal(ArchivesEntity archive){
        Session session = Constants.getSessionFactory().openSession();
        String hql = "from ArchiveTraceEntity where archiveNum = " + archive.getArchiveNum() + " and time < '" + startTime + "' order by time desc ";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        ArchiveTraceEntity trace = (ArchiveTraceEntity) query.uniqueResult();
        return !(trace == null || trace.getAction() == 1);
    }

    private int isError(String tagNum) {
        for (Object result : errorResults) {
            CheckResultsEntity error = (CheckResultsEntity) result;
            if (tagNum.equals(error.getTagNum())) {
                return error.getExceptionInfo();
            }
        }
        return 0;
    }

    private List orderByInfo(List list) {
        for (OrderBean t : getOrder()) {
            if (t.getColumn() == 2) {
                if (t.getDir().equals("desc")) {
                    Collections.sort(list, new Comparator<Map<String, Map>>() {
                        @Override
                        public int compare(Map o1, Map o2) {
                            Map info1 = (Map) o1.get("info");
                            Map info2 = (Map) o2.get("info");
                            Integer compare1 = (Integer) info1.get("data");
                            Integer compare2 = (Integer) info2.get("data");
                            return compare1.compareTo(compare2);
                        }
                    });
                }else if(t.getDir().equals("asc")){
                    Collections.sort(list, new Comparator<Map<String, Map>>() {
                        @Override
                        public int compare(Map o1, Map o2) {
                            Map info1 = (Map) o1.get("info");
                            Map info2 = (Map) o2.get("info");
                            Integer compare1 = (Integer) info1.get("data");
                            Integer compare2 = (Integer) info2.get("data");
                            return  compare2.compareTo(compare1);
                        }
                    });
                }
            }
        }
        return list;
    }

    @Override
    protected List buildResult(List list) {
        List<Map> result = new ArrayList<>();
        Iterator iterator = list.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            ArchivesEntity archive = (ArchivesEntity) iterator.next();
            if(!isLegal(archive))continue;
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> info = new HashMap<>();
            int errorCode = isError(archive.getTagNum());
            info.put("data", errorCode);
            info.put("display", Constants.getExceptionInfoDict().get(errorCode));
            map.put("id", i++);
            map.put("num", archive.getArchiveNum());
            map.put("name", archive.getName());
            map.put("info", info);
            result.add(map);
        }
        orderByInfo(result);
        return result;
    }

    @Override
    public int getDraw() {
        return super.getDraw();
    }

    @Override
    public int getRecordsFiltered() {
        return super.getRecordsFiltered();
    }

    @Override
    public int getRecordsTotal() {
        return super.getRecordsTotal();
    }

    @Override
    public List getResult() {
        return super.getResult();
    }

    @Override
    protected String getEntity() {
        return "ArchivesEntity";
    }

    @Override
    protected String order() {
        String order = " order by ";
        for (OrderBean t : getOrder()) {
            if (t.getColumn() != 2) {
                order = order + getColumn(t.getColumn()) + " " + t.getDir() + ",";
            }
        }
        order = order.substring(0, order.length() - 1);
        if (order.endsWith("by")) order = "";
        return order;
    }

    @Override
    protected String where() throws ParseException {
        parseContent();
        setErrorResults();
        String where = " where ";
        switch (condition) {
            case 0:
                where += "createdTime between '" + contentMap.get("StartTime") + "' and '" + contentMap.get("EndTime") + "'";
                break;
            default:
                where = "";
        }
        return where;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
