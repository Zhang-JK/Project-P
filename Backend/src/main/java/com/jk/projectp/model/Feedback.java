package com.jk.projectp.model;


import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "message_feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message_id", nullable = false)
    private Integer messageId;

    @Column(name = "content", length = 512)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_uid", nullable = false)
    private User fromUid;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "time", nullable = false)
    private Instant time;

    public Feedback(String msg, User user, Instant time) {
        this.content = msg;
        this.messageId = UUID.randomUUID().toString().hashCode();
        this.fromUid = user;
        this.time = time;
        this.status = "Started";
    }

    public Feedback() {
        super();
    }


    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getFromUid() {
        return fromUid;
    }

    public void setFromUid(User fromUid) {
        this.fromUid = fromUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
