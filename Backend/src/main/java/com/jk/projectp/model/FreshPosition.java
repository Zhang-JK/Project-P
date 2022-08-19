package com.jk.projectp.model;

import javax.persistence.*;

@Entity
@Table(name = "fresh_position")
public class FreshPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "position", nullable = false, length = 20)
    private String position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fresh_id", nullable = false)
    private Fresh fresh;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}