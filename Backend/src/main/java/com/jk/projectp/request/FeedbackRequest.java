package com.jk.projectp.request;

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

    public FeedbackRequest(Long fbId, String msg) {
        this.fbId = fbId;
        this.msg = msg;
    }

    private String msg;
}
