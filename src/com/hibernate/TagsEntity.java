package com.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by drc on 16-6-13.
 */
@Entity
@Table(name = "Tags", schema = "archivesSystem", catalog = "")
public class TagsEntity {
    private String tagNum;

    @Id
    @Column(name = "TagNum", nullable = false, length = 45)
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

        TagsEntity that = (TagsEntity) o;

        if (tagNum != null ? !tagNum.equals(that.tagNum) : that.tagNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tagNum != null ? tagNum.hashCode() : 0;
    }
}
