package com.jk.projectp.result.pojo;

import com.jk.projectp.model.PeerReview;

public class PeerReviewPojo {
    private Integer id;

    private Integer fromId;
    private Integer targetId;

    private boolean whetherKnow;

    private Integer attendanceScore;

    private Integer contributionScore;

    private Integer communicationScore;

    private String comments;

    public PeerReviewPojo(PeerReview review){
        this.id = review.getId();
        this.fromId = review.getFromFresh().getId();
        this.targetId = review.getTargetFresh().getId();
        this.whetherKnow = review.getWhetherKnow();
        this.attendanceScore = review.getAttendanceScore();
        this.contributionScore = review.getContributionScore();
        this.communicationScore = review.getCommunicationScore();
        this.comments = review.getComments();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public boolean isWhetherKnow() {
        return whetherKnow;
    }

    public void setWhetherKnow(boolean whetherKnow) {
        this.whetherKnow = whetherKnow;
    }

    public Integer getAttendanceScore() {
        return attendanceScore;
    }

    public void setAttendanceScore(Integer attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    public Integer getContributionScore() {
        return contributionScore;
    }

    public void setContributionScore(Integer contributionScore) {
        this.contributionScore = contributionScore;
    }

    public Integer getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(Integer communicationScore) {
        this.communicationScore = communicationScore;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


}
