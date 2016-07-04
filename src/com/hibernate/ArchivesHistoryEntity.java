package com.hibernate;

import javax.persistence.*;

/**
 * Created by drc on 16-6-12.
 */
@Entity
@Table(name = "ArchivesHistory", schema = "archivesSystem", catalog = "")
public class ArchivesHistoryEntity {
    private int archiveNum;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArchivesHistoryEntity that = (ArchivesHistoryEntity) o;

        if (archiveNum != that.archiveNum) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = archiveNum;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
