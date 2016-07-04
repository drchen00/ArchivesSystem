package com.action;

import com.Constants;
import com.hibernate.CheckRecordsEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by drc on 16-6-12.
 */
public class GetCheckRecordsAction extends TableAction {
    @Override
    protected List buildResult(List list) {
        List<Map> result = new ArrayList<>();
        for (Object aList : list) {
            CheckRecordsEntity record = (CheckRecordsEntity) aList;
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> condition = new HashMap<>();
            condition.put("data", record.getCheckCondition());
            condition.put("display", Constants.getCheckConditionsDict().get(record.getCheckCondition()));
            map.put("id", record.getId());
            map.put("condition", condition);
            map.put("content", record.getCheckContent());
            map.put("user", record.getAccount().getName());
            map.put("startTime", Constants.getSimpleDateFormat().format(record.getStartTime()));
            map.put("endTime", Constants.getSimpleDateFormat().format(record.getEndTime()));
            result.add(map);
        }
        return result;
    }

    @Override
    protected String getEntity() {
        return "CheckRecordsEntity";
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

    @Override
    protected String where() throws ParseException {
        return "";
    }
}
