package com.jk.projectp.result;

import com.jk.projectp.model.Project;
import com.jk.projectp.model.Role;
import com.jk.projectp.model.User;
import com.jk.projectp.model.UserProject;
import com.jk.projectp.result.pojo.ProjectPojo;
import com.jk.projectp.result.pojo.RolePojo;
import com.jk.projectp.result.pojo.UserPojo;

import java.util.HashSet;
import java.util.Set;

public class MemberResponse {
    UserPojo user;
    Set<RolePojo> roles;

    Set<ProjectPojo> projects;

    public MemberResponse(User userI, Set<Role> rolesI, Set<UserProject> projectsI) {
        this.user = new UserPojo(userI);
        this.roles = new HashSet<>();
        this.projects = new HashSet<>();
        for (Role role : rolesI) {
            this.roles.add(new RolePojo(role));
        }
        for (UserProject project : projectsI) {
            this.projects.add(new ProjectPojo(project.getProject()));
        }
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public Set<RolePojo> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolePojo> roles) {
        this.roles = roles;
    }

    public Set<ProjectPojo> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectPojo> projects) {
        this.projects = projects;
    }
}
