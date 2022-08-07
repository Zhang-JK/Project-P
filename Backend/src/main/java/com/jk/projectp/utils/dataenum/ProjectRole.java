package com.jk.projectp.utils.dataenum;

public enum ProjectRole {
    LEADER("Leader"),
    MANAGER("Manager"),
    MEMBER("Member");

    private final String projectRole;

    public String getRoleName() {
        return projectRole;
    }

    ProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }
}
