package com.jk.projectp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "self_review")
public class SelfReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;



    @Size(max = 2000)
    @NotNull
    @Column(name = "contribution", nullable = false, length = 2000)
    private String contribution;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_fresh_id", nullable = false)
    private Fresh fromFresh;

    public SelfReview() {
    }

    public SelfReview(String contribution, Fresh fromFresh) {
        this.contribution = contribution;
        this.fromFresh = fromFresh;
    }

    public Fresh getFromFresh() {
        return fromFresh;
    }

    public void setFromFresh(Fresh fromFresh) {
        this.fromFresh = fromFresh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }


}