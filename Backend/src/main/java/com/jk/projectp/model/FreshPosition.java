package com.jk.projectp.model;

import com.jk.projectp.utils.dataenum.Position;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fresh_position")
public class FreshPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "position", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fresh_id", nullable = false)
    private Fresh fresh;

    public FreshPosition(Position position, Fresh fresh) {
        this.position = position;
        this.fresh = fresh;
    }

    public FreshPosition() {
        super();
    }

    public Fresh getFresh() {
        return fresh;
    }

    public void setFresh(Fresh fresh) {
        this.fresh = fresh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof FreshPosition)) {
            return false;
        } else
            return Objects.equals(((FreshPosition) obj).getPosition(), this.getPosition())
                    && ((FreshPosition) obj).getFresh().equals(this.getFresh());
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
