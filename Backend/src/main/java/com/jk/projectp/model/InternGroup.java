package com.jk.projectp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "intern_group")
public class InternGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "group_num", nullable = false)
    private Integer groupNum;

    @Size(max = 255)
    @NotNull
    @Column(name = "group_name", nullable = false)
    private String groupName;

    public InternGroup() {
        super();
    }
    public InternGroup(Integer groupNum, String groupName) {
        super();
        this.groupNum = groupNum;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}