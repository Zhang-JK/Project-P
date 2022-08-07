package com.jk.projectp.request;

public class CommentRequest {
    private String msg;
    private Long fbId;
    private Integer commentId;

    public CommentRequest(String msg, Long fbId, Integer commentId) {
        this.msg = msg;
        this.fbId = fbId;
        this.commentId = commentId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
