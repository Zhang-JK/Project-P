package com.jk.projectp.model;

import com.jk.projectp.utils.dataenum.OrderState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "state", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "total_price", precision = 9, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposer")
    private User proposer;

    @Column(name = "propose_time")
    private Instant proposeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer")
    private User reviewer;

    @Column(name = "review_time")
    private Instant reviewTime;

    @OneToMany(mappedBy = "order")
    private Set<Item> items = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getProposer() {
        return proposer;
    }

    public void setProposer(User proposer) {
        this.proposer = proposer;
    }

    public Instant getProposeTime() {
        return proposeTime;
    }

    public void setProposeTime(Instant proposeTime) {
        this.proposeTime = proposeTime;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Instant getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

}