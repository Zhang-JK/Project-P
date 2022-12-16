package com.jk.projectp.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FreshPositionId implements Serializable {
    private static final long serialVersionUID = 8798219140574518094L;
    @NotNull
    @Column(name = "fresh_id", nullable = false)
    private Integer freshId;

    @NotNull
    @Column(name = "position_id", nullable = false)
    private Integer positionId;

    public Integer getFreshId() {
        return freshId;
    }

    public void setFreshId(Integer freshId) {
        this.freshId = freshId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FreshPositionId entity = (FreshPositionId) o;
        return Objects.equals(this.freshId, entity.freshId) &&
                Objects.equals(this.positionId, entity.positionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freshId, positionId);
    }

}