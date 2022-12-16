package com.jk.projectp.model;

import com.jk.projectp.model.Fresh;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "peer_review")
public class PeerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_fresh_id", nullable = false)
    private Fresh fromFresh;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target_fresh_id", nullable = false)
    private Fresh targetFresh;

    @NotNull
    @Column(name = "whether_know", nullable = false)
    private Boolean whetherKnow = false;

    @Column(name = "attendance_score")
    private Integer attendanceScore;

    @Column(name = "contribution_score")
    private Integer contributionScore;

    @Column(name = "communication_score")
    private Integer communicationScore;

    @Size(max = 2000)
    @Column(name = "comments", length = 2000)
    private String comments;

    public PeerReview() {
    }

    public PeerReview(Fresh fromFresh, Fresh targetFresh, Boolean whetherKnow, Integer attendanceScore, Integer contributionScore, Integer communicationScore, String comments) {
        this.fromFresh = fromFresh;
        this.targetFresh = targetFresh;
        this.whetherKnow = whetherKnow;
        this.attendanceScore = attendanceScore;
        this.contributionScore = contributionScore;
        this.communicationScore = communicationScore;
        this.comments = comments;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fresh getFromFresh() {
        return fromFresh;
    }

    public void setFromFresh(Fresh fromFresh) {
        this.fromFresh = fromFresh;
    }

    public Fresh getTargetFresh() {
        return targetFresh;
    }

    public void setTargetFresh(Fresh targetFresh) {
        this.targetFresh = targetFresh;
    }

    public Boolean getWhetherKnow() {
        return whetherKnow;
    }

    public void setWhetherKnow(Boolean whetherKnow) {
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