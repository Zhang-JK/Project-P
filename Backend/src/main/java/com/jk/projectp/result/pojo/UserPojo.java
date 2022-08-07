package com.jk.projectp.result.pojo;

import com.jk.projectp.model.User;

public class UserPojo {

    public UserPojo(User user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        name = user.getName();
    }

    int id;
    String username;
    String email;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
