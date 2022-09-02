package com.jk.projectp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false, length = 20)
    private String startTime;

    @Column(name = "full", nullable = false)
    private Boolean full = false;

    @OneToMany(mappedBy = "interview")
    private Set<InterviewFresh> interviewFreshes = new LinkedHashSet<>();

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

    public Set<InterviewFresh> getInterviewFreshes() {
        return interviewFreshes;
    }

    public void setInterviewFreshes(Set<InterviewFresh> interviewFreshes) {
        this.interviewFreshes = interviewFreshes;
    }

}