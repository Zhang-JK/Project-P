package com.jk.projectp.model;

import com.jk.projectp.utils.dataenum.InterviewRoom;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "interview_fresh")
public class InterviewFresh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "time_index", nullable = false)
    private Integer timeIndex;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "interview_id", nullable = false)
    private Interview interview;

    @Column(name = "room", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private InterviewRoom room;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fresh_id", nullable = false)
    private Fresh fresh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(Integer timeIndex) {
        this.timeIndex = timeIndex;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public InterviewRoom getRoom() {
        return room;
    }

    public void setRoom(InterviewRoom room) {
        this.room = room;
    }

    public Fresh getFresh() {
        return fresh;
    }

    public void setFresh(Fresh fresh) {
        this.fresh = fresh;
    }

}