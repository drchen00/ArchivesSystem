package com.hibernate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by drc on 16-6-13.
 */
@Entity
@Table(name = "Archives", schema = "archivesSystem", catalog = "")
public class ArchivesEntity {
    private int archiveNum;
    private String name;
    private String tagNum;
    private int status;
    private Date createdTime;

    @Id
    @Column(name = "ArchiveNum", nullable = false)
    public int getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(int archiveNum) {
        this.archiveNum = archiveNum;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TagNum", nullable = false, length = 45)
    public String getTagNum() {
        return tagNum;
    }

    public void setTagNum(String tagNum) {
        this.tagNum = tagNum;
    }

    @Basic
    @Column(name = "Status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CreatedTime", nullable = false)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArchivesEntity that = (ArchivesEntity) o;

        if (archiveNum != that.archiveNum) return false;
        if (status != that.status) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tagNum != null ? !tagNum.equals(that.tagNum) : that.tagNum != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = archiveNum;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tagNum != null ? tagNum.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        return result;
    }
}
