package com.jk.projectp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "fresh_position")
public class FreshPosition {
    @EmbeddedId
    private FreshPositionId id;

    @MapsId("freshId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fresh_id", nullable = false)
    private Fresh fresh;

    @MapsId("positionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    public FreshPositionId getId() {
        return id;
    }

    public void setId(FreshPositionId id) {
        this.id = id;
    }

    public Fresh getFresh() {
        return fresh;
    }

    public void setFresh(Fresh fresh) {
        this.fresh = fresh;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}