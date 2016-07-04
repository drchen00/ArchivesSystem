package com.bean;

import com.Constants;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by drc on 16-6-2.
 */
public class ArchiveStatusBean {
    private Map status;

    public Map getStatus() {
        Map map = Constants.getArchivesStatusDict();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (!(entry.getKey() instanceof Integer)) {
                iterator.remove();
            }
        }
        return map;
    }
}
