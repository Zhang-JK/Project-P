package com.jk.projectp.request;

public class PeerReviewRequest {
    private Integer targetId;
    private boolean whetherKnow;

    private Integer attendanceScore;

    private Integer contributionScore;

    private Integer communicationScore;

    private String comments;

    public PeerReviewRequest(Integer targetId, boolean whetherKnow, Integer attendanceScore, Integer contributionScore, Integer communicationScore, String comments) {
        this.targetId = targetId;
        this.whetherKnow = whetherKnow;
        this.attendanceScore = attendanceScore;
        this.contributionScore = contributionScore;
        this.communicationScore = communicationScore;
        this.comments = comments;
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
