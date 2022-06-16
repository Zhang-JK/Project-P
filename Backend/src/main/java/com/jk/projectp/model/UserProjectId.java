package com.jk.projectp.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserProjectId implements Serializable {
    @Serial
    private static final long serialVersionUID = 2186792416012378819L;
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserProjectId entity = (UserProjectId) o;
        return Objects.equals(this.projectId, entity.projectId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, userId);
    }

}