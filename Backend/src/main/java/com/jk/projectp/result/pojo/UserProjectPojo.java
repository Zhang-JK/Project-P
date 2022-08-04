package com.jk.projectp.result.pojo;

import com.jk.projectp.model.UserProject;

public class UserProjectPojo {
    String roleName;
    ProjectPojo project;

    public UserProjectPojo(UserProject userProject) {
        this.roleName = userProject.getRole().getRoleName();
        this.project = new ProjectPojo(userProject.getProject());
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public ProjectPojo getProject() {
        return project;
    }

    public void setProject(ProjectPojo project) {
        this.project = project;
    }
}
