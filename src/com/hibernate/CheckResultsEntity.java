package com.hibernate;

import javax.persistence.*;

/**
 * Created by drc on 16-6-22.
 */
@Entity
@Table(name = "CheckResults", schema = "archivesSystem", catalog = "")
@IdClass(CheckResultsEntityPK.class)
public class CheckResultsEntity {
    private int checkId;
    private String tagNum;
    private int exceptionInfo;

    @Id
    @Column(name = "CheckID", nullable = false)
    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    @Id
    @Column(name = "TagNum", nullable = false, length = 45)
    public String getTagNum() {
        return tagNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    @Basic
    @Column(name = "ExceptionInfo", nullable = false)
    public int getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(int exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckResultsEntity that = (CheckResultsEntity) o;

        if (checkId != that.checkId) return false;
        if (exceptionInfo != that.exceptionInfo) return false;
        if (tagNum != null ? !tagNum.equals(that.tagNum) : that.tagNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = checkId;
        result = 31 * result + (tagNum != null ? tagNum.hashCode() : 0);
        result = 31 * result + exceptionInfo;
        return result;
    }
}
