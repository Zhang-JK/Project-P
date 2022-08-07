package com.jk.projectp.result.pojo;

import com.jk.projectp.model.FeedbackComment;

import java.time.Instant;

public class FeedbackCommentPojo {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    private Long fbId;

    private String content;

    private Integer parentId;

    private Integer fromUid;

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    private Instant time;

    public FeedbackCommentPojo(FeedbackComment comment) {
        this.id = comment.getId();
        this.fbId = comment.getFeedback().getId();
        this.content = comment.getContent();
        FeedbackComment parent = comment.getParent();
        if (parent!= null)
            this.parentId = parent.getId();
        else
            this.parentId = -1;
        this.fromUid = comment.getFromUid().getId();
        this.time = comment.getTime();

    }
}
