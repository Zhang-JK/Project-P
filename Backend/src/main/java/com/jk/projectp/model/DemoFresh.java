package com.jk.projectp.model;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "demo_id", insertable = false, updatable = false)
    private Demo demo;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fresh_id", insertable = false, updatable = false)
    private Fresh fresh;
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

    public void setFresh(Fresh fresh) { this.fresh = fresh; }

    public Fresh getFresh() { return fresh; }

    public void setDemo(Demo demo) { this.demo = demo; }

    public Demo getDemo() { return demo; }
}