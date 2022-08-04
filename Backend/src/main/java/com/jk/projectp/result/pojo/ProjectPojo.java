package com.jk.projectp.result.pojo;

import com.jk.projectp.model.Project;

import java.math.BigDecimal;

public class ProjectPojo {

    public ProjectPojo(Project project) {
        id = project.getId();
        name = project.getName();
        description = project.getDescription();
        budget = project.getBudget();
        used = project.getUsed();
    }

    int id;
    String name;
    String description;
    BigDecimal budget;
    BigDecimal used;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getUsed() {
        return used;
    }

    public void setUsed(BigDecimal used) {
        this.used = used;
    }
}
