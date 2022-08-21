package com.jk.projectp.result.pojo;

import com.jk.projectp.model.Feedback;
import com.jk.projectp.utils.dataenum.FeedbackStatus;

import java.time.Instant;

public class FeedbackPojo {
    private Long id;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    private Integer fromUid;

    private FeedbackStatus status;

    private Instant time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;


    public FeedbackPojo(Feedback fb){
        this.id = fb.getId();
        this.content = fb.getContent();
        this.fromUid = fb.getFromUid().getId();
        this.status = fb.getStatus();
        this.time = fb.getTime();
        this.title = fb.getTitle();
    }
}
