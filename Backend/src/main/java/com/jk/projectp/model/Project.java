package com.jk.projectp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "budget", nullable = false, precision = 9, scale = 2)
    private BigDecimal budget;

    @Column(name = "used", precision = 9, scale = 2)
    private BigDecimal used;

    @OneToMany(mappedBy = "project")
    private Set<UserProject> userProjects = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<UserProject> getUserProjects() {
        return userProjects;
    }

    public void setUserProjects(Set<UserProject> userProjects) {
        this.userProjects = userProjects;
    }

}