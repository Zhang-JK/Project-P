package com.jk.projectp.model;

import com.jk.projectp.utils.dataenum.OrderPlatform;
import com.jk.projectp.utils.dataenum.OrderState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "state", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(name = "platform", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private OrderPlatform platform;

    @Column(name = "link", length = 500)
    private String link;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "`usage`", length = 200)
    private String usage;

    @Column(name = "price", precision = 9, scale = 2)
    private BigDecimal price;

    @Column(name = "purchase_num", length = 200)
    private String purchaseNum;

    @Column(name = "shipping_num", length = 200)
    private String shippingNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private User payer;

    @Column(name = "has_receipt")
    private Boolean hasReceipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser")
    private User purchaser;

    @Column(name = "purchase_time")
    private Instant purchaseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collector")
    private User collector;

    @Column(name = "collect_time")
    private Instant collectTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    private User manager;

    @Column(name = "settle_time")
    private Instant settleTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(OrderPlatform platform) {
        this.platform = platform;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(String purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getShippingNum() {
        return shippingNum;
    }

    public void setShippingNum(String shippingNum) {
        this.shippingNum = shippingNum;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public Boolean getHasReceipt() {
        return hasReceipt;
    }

    public void setHasReceipt(Boolean hasReceipt) {
        this.hasReceipt = hasReceipt;
    }

    public User getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
    }

    public Instant getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Instant purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public User getCollector() {
        return collector;
    }

    public void setCollector(User collector) {
        this.collector = collector;
    }

    public Instant getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Instant collectTime) {
        this.collectTime = collectTime;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Instant getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Instant settleTime) {
        this.settleTime = settleTime;
    }

}