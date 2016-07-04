package com.hibernate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by drc on 16-6-12.
 */
@Entity
@Table(name = "ArchiveTrace", schema = "archivesSystem", catalog = "")
public class ArchiveTraceEntity {
    private int id;
    private int archiveNum;
    private int action;
    private Date time;
    private int userId;

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
    @Column(name = "ArchiveNum", nullable = false)
    public int getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    @Basic
    @Column(name = "Action", nullable = false)
    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Basic
    @Column(name = "Time", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "UserID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArchiveTraceEntity that = (ArchiveTraceEntity) o;

        if (id != that.id) return false;
        if (archiveNum != that.archiveNum) return false;
        if (action != that.action) return false;
        if (userId != that.userId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + archiveNum;
        result = 31 * result + action;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
