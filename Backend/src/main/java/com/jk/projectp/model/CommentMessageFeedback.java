package com.jk.projectp.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comment_message_feedback")
public class CommentMessageFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "message_id", nullable = false, referencedColumnName = "id")
    private Feedback message;

    @Column(name = "content", length = 512)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private CommentMessageFeedback parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_uid")
    private User fromUid;

    @Column(name = "time", nullable = false)
    private Instant time;

    public CommentMessageFeedback(String msg, User user, CommentMessageFeedback parent, Feedback messageFeedback, Instant time) {
        this.content = msg;
        this.fromUid = user;
        this.message = messageFeedback;
        this.parent = parent;
        this.time = time;
    }

    public CommentMessageFeedback() {
        super();
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public User getFromUid() {
        return fromUid;
    }

    public void setFromUid(User fromUid) {
        this.fromUid = fromUid;
    }

    public CommentMessageFeedback getParent() {
        return parent;
    }

    public void setParent(CommentMessageFeedback parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Feedback getMessage() {
        return message;
    }

    public void setMessage(Feedback message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
