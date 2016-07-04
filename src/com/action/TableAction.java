package com.action;

import com.Constants;
import com.bean.ColumnBean;
import com.bean.OrderBean;
import com.bean.SearchBean;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by drc on 16-5-11.
 */
public abstract class TableAction extends ActionSupport {
    private List result = new ArrayList<Map<String, Objects>>();
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private int start;
    private int length;
    private SearchBean search;
    private List<OrderBean> order;
    private List<ColumnBean> columns;

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public List<OrderBean> getOrder(){
        return order;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    protected abstract List buildResult(List list);

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public SearchBean getSearch() {
        return search;
    }

    public void setSearch(SearchBean search) {
        this.search = search;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public List getResult() {
        return result;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public List<ColumnBean> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnBean> columns) {
        this.columns = columns;
    }

    protected abstract String getEntity();

    protected abstract String where() throws ParseException;

    protected String order(){
        String order = " order by ";
        for (OrderBean t : this.order) {
            order = order + getColumn(t.getColumn()) + " " + t.getDir() + ",";
        }
        order = order.substring(0, order.length() - 1);
        return order;
    }

    protected String getColumn(int index) {
        return columns.get(index).getName();
    }

    protected void query() throws Exception {
        Session session = Constants.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "select count(*) from " + getEntity();
        recordsTotal = (int) (long) session.createQuery(hql).uniqueResult();
        String where = where();
        if (where.equals("")) {
            recordsFiltered = recordsTotal;
        } else {
            hql += where;
            recordsFiltered = (int) (long) session.createQuery(hql).uniqueResult();
        }

        hql = "from " + getEntity() + where + order();
        Query query = session.createQuery(hql);
        if (start >= 0) {
            query.setFirstResult(start);
            if (length != -1) query.setMaxResults(length);
        }
        List list = query.list();
        transaction.commit();
        session.close();
        result = buildResult(list);
    }

    public String execute() {
        try {
            query();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}
