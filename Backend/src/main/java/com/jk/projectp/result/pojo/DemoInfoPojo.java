package com.jk.projectp.result.pojo;

import com.jk.projectp.model.DemoFresh;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class DemoInfoPojo {
    private LocalDate date;
    private String startTime;

    public DemoInfoPojo(DemoFresh i) {
        this.date = i.getDemo().getDate();
        this.startTime = i.getDemo().getStartTime();
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
}
