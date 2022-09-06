package com.jk.projectp.result.pojo;

import com.jk.projectp.model.InterviewFresh;

import java.time.LocalDate;

public class InterviewInfoPojo {
    private LocalDate date;
    private String startTime;
    private String room;

    public InterviewInfoPojo(InterviewFresh i) {
        this.date = i.getInterview().getDate();
        this.startTime = i.getInterview().getStartTime();
        this.room = i.getRoom().getInterviewRoom();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
