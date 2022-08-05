package com.jk.projectp.result;

import com.jk.projectp.model.User;
import com.jk.projectp.result.pojo.UserPojo;

import java.util.HashSet;
import java.util.Set;

public class UserUpdateResponse {
    Set<UserPojo> updatedUsers;
    Set<UserPojo> createdUsers;
    Set<UserPojo> failedUsers;
    Set<String> failReasons;

    public UserUpdateResponse() {
        this.updatedUsers = new HashSet<>();
        this.createdUsers = new HashSet<>();
        this.failedUsers = new HashSet<>();
        this.failReasons = new HashSet<>();
    }

    public void addToUpdatedUsers(User user) {
        this.updatedUsers.add(new UserPojo(user));
    }

    public void addToCreatedUsers(User user) {
        this.createdUsers.add(new UserPojo(user));
    }

    public void addToFailedUsers(User user, String reason) {
        this.failedUsers.add(new UserPojo(user));
        this.failReasons.add(reason);
    }

    public Set<UserPojo> getUpdatedUsers() {
        return updatedUsers;
    }

    public void setUpdatedUsers(Set<UserPojo> updatedUsers) {
        this.updatedUsers = updatedUsers;
    }

    public Set<UserPojo> getCreatedUsers() {
        return createdUsers;
    }

    public void setCreatedUsers(Set<UserPojo> createdUsers) {
        this.createdUsers = createdUsers;
    }

    public Set<UserPojo> getFailedUsers() {
        return failedUsers;
    }

    public void setFailedUsers(Set<UserPojo> failedUsers) {
        this.failedUsers = failedUsers;
    }

    public Set<String> getFailReasons() {
        return failReasons;
    }

    public void setFailReasons(Set<String> failReasons) {
        this.failReasons = failReasons;
    }
}
