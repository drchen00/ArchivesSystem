package com.action;

import com.hibernate.TagsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by drc on 16-5-11.
 */
public class GetTagsAction extends TableAction {
    @Override
    protected String getEntity() {
        return "TagsEntity";
    }

    @Override
    protected List buildResult(List list) {
        List<Map> result = new ArrayList<>();
        for (Object aList : list) {
            TagsEntity tag = (TagsEntity) aList;
            Map<String, Object> map = new HashMap<>();
            map.put("num", tag.getTagNum());
            result.add(map);
        }
        return result;
    }

    public String execute() {
        return super.execute();
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
    protected String where() {
        if (getSearch().getValue() != null && !getSearch().getValue().equals("")) {
            return " where tagNum like '" + getSearch().getValue() + "%'";
        } else {
            return "";
        }
    }
}
