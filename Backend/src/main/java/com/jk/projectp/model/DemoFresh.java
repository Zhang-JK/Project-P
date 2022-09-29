package com.jk.projectp.model;

import javax.persistence.*;

@Entity
@Table(name = "demo_fresh")
public class DemoFresh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "demo_id")
    private Integer demoId;

    @Column(name = "fresh_id")
    private Integer freshId;

    @Column(name = "time_index")
    private Integer timeIndex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDemoId() {
        return demoId;
    }

    public void setDemoId(Integer demoId) {
        this.demoId = demoId;
    }

    public Integer getFreshId() {
        return freshId;
    }

    public void setFreshId(Integer freshId) {
        this.freshId = freshId;
    }

    public Integer getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(Integer timeIndex) {
        this.timeIndex = timeIndex;
    }

}