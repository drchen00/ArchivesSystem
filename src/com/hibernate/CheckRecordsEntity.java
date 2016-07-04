package com.hibernate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by drc on 16-6-12.
 */
@Entity
@Table(name = "CheckRecords", schema = "archivesSystem", catalog = "")
public class CheckRecordsEntity {
    private int id;
    private int checkCondition;
    private String checkContent;
    private AccountEntity account;
    private Date startTime;
    private Date endTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CheckCondition", nullable = false)
    public int getCheckCondition() {
        return checkCondition;
    }

    public void setCheckCondition(int checkCondition) {
        this.checkCondition = checkCondition;
    }

    @Basic
    @Column(name = "CheckContent", nullable = false, length = 128)
    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @Basic
    @Column(name = "StartTime", nullable = false)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "EndTime", nullable = false)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckRecordsEntity that = (CheckRecordsEntity) o;

        if (id != that.id) return false;
        if (checkCondition != that.checkCondition) return false;
        if (account != that.account) return false;
        if (checkContent != null ? !checkContent.equals(that.checkContent) : that.checkContent != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + checkCondition;
        result = 31 * result + (checkContent != null ? checkContent.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
