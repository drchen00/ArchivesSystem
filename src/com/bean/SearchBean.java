package com.bean;

/**
 * Created by drc on 16-5-20.
 * Datatables 请求数据处理相关类
 */
public class SearchBean {
    private String value;
    private boolean regex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }
}
