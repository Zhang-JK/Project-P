package com.jk.projectp.result.pojo;

import com.jk.projectp.model.Interview;

import java.time.LocalDate;

public class InterviewPojo {
    private Integer id;
    private LocalDate date;
    private String startTime;
    private Boolean full;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    public InterviewPojo(Interview i) {
        this.id = i.getId();
        this.date = i.getDate();
        this.startTime = i.getStartTime();
        this.full = i.getFull();
    }
}
