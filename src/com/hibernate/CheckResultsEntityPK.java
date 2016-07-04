package com.hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by drc on 16-6-22.
 */
public class CheckResultsEntityPK implements Serializable {
    private int checkId;
    private String tagNum;

    @Column(name = "CheckID", nullable = false)
    @Id
    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    @Column(name = "TagNum", nullable = false, length = 45)
    @Id
    public String getTagNum() {
        return tagNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckResultsEntityPK that = (CheckResultsEntityPK) o;

        if (checkId != that.checkId) return false;
        if (tagNum != null ? !tagNum.equals(that.tagNum) : that.tagNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = checkId;
        result = 31 * result + (tagNum != null ? tagNum.hashCode() : 0);
        return result;
    }
}
