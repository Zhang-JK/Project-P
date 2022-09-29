package com.jk.projectp.result.pojo;

import java.time.LocalDate;
import com.jk.projectp.model.Demo;

public class DemoPojo {
    private Integer id;
    private LocalDate date;
    private String startTime;
    private Boolean isFull;

    public Integer getId() { return id;}
    public void setId(Integer id) { this.id = id;}

    public LocalDate getData() { return date; }
    public void setDate(LocalDate date) { this.date = date;}

    public String getStartTime() { return startTime; }
    public void setStartTime(String time) { this.startTime = time; }

    public Boolean getFull() { return isFull; }
    public void setFull(Boolean full) { this.isFull = full; }

    public DemoPojo(Demo d) {
        this.id = d.getId();
        this.date = d.getDate();
        this.startTime = d.getStartTime();
        this.isFull = d.getFull();
    }
}
