package com.jk.projectp.result.pojo;

import com.jk.projectp.model.Role;

public class RolePojo {

    public RolePojo(Role role) {
        id = role.getId();
        name = role.getName();
        description = role.getDescription();
    }

    int id;
    String name;
    String description;

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
}
