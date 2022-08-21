package com.jk.projectp.request;

import com.jk.projectp.utils.dataenum.FeedbackStatus;
import org.springframework.web.bind.annotation.RequestParam;

public class FeedbackRequest {
    private Long fbId;

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FeedbackRequest(Long fbId, String msg, String title) {
        this.fbId = fbId;
        this.msg = msg;
        this.title = title;
    }

    private String msg;
    private String title;

    private FeedbackStatus status;

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }
}
