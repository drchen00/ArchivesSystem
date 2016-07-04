package com.action;

import com.Constants;
import com.bean.ColumnBean;
import com.hibernate.ArchivesEntity;

import java.text.ParseException;
import java.util.*;

/**
 * Created by drc on 16-5-13.
 */
public class GetArchivesAction extends TableAction {
    @Override
    protected String getEntity() {
        return "ArchivesEntity";
    }

    @Override
    protected String where() throws ParseException {
        String where = " where ";
        if (getSearch().getValue() != null && !getSearch().getValue().equals("")) {
            where += "(archiveNum like '" + getSearch().getValue() + "%' or " +
                    "name like '" + getSearch().getValue() + "%' or " +
                    "tagNum like '" + getSearch().getValue() + "%') and ";
        }
        for (ColumnBean column : getColumns()) {
            if (column.getSearch().getValue() == null || column.getSearch().getValue().equals("")) continue;
            if (column.getName().equals("createdTime")) {
                String start = Constants.getSimpleDateFormat().format(new Date(0));
                String end = Constants.getSimpleDateFormat().format(new Date());
                String[] time = column.getSearch().getValue().split("\\|", 2);
                if (time[0] != null && !time[0].equals("")) {
                    start = time[0];
                }
                if (time[1] != null && !time[1].equals("")) {
                    end = time[1];
                }
                where += " createdTime between '" + start + "' and '" + end + "' and ";
                continue;
            }
            where += column.getName() + " like '" + column.getSearch().getValue() + "%' and ";
        }
        if (where.endsWith("and ")) where = where.substring(0, where.length() - 4);
        return where.equals(" where ") ? "" : where;
    }

    @Override
    protected List buildResult(List list) {
        List<Map> result = new ArrayList<>();
        Iterator iterator = list.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            ArchivesEntity archive = (ArchivesEntity) iterator.next();
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> status = new HashMap<>();
            status.put("data", archive.getStatus());
            status.put("display", Constants.getArchivesStatusDict().get(archive.getStatus()));
            map.put("id", i++);
            map.put("num", archive.getArchiveNum());
            map.put("name", archive.getName());
            map.put("tagNum", archive.getTagNum());
            map.put("status", status);
            map.put("date", Constants.getSimpleDateFormat().format(archive.getCreatedTime()));
            result.add(map);
        }
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
    public List getResult() {
        return super.getResult();
    }

    @Override
    public int getRecordsTotal() {
        return super.getRecordsTotal();
    }

    public String execute() {
        return super.execute();
    }
}
