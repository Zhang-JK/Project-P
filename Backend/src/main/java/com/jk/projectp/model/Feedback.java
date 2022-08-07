package com.jk.projectp.model;


import com.jk.projectp.utils.dataenum.FeedbackStatus;

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

    @Column(name = "content", length = 512)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_uid", nullable = false)
    private User fromUid;

    @Column(name = "status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private FeedbackStatus status;

    @Column(name = "time", nullable = false)
    private Instant time;

    public Feedback(String msg, User user, Instant time) {
        this.content = msg;
        this.fromUid = user;
        this.time = time;
        this.status = FeedbackStatus.STARTED;
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

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
