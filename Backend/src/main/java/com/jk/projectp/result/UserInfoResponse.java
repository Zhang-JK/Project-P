package com.jk.projectp.result;

import com.jk.projectp.model.Role;
import com.jk.projectp.model.User;
import com.jk.projectp.model.UserProject;
import com.jk.projectp.result.pojo.RolePojo;
import com.jk.projectp.result.pojo.UserPojo;
import com.jk.projectp.result.pojo.UserProjectPojo;

import java.util.HashSet;
import java.util.Set;

public class UserInfoResponse {
    UserPojo user;
    Set<UserProjectPojo> projects;
    Set<RolePojo> roles;

    public UserInfoResponse(User userI, Set<UserProject> projectsI, Set<Role> rolesI) {
        this.user = new UserPojo(userI);
        this.projects = new HashSet<>();
        this.roles = new HashSet<>();
        for (UserProject project : projectsI) {
            this.projects.add(new UserProjectPojo(project));
        }
        for (Role role : rolesI) {
            this.roles.add(new RolePojo(role));
        }
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public Set<UserProjectPojo> getProjects() {
        return projects;
    }

    public void setProjects(Set<UserProjectPojo> projects) {
        this.projects = projects;
    }

    public Set<RolePojo> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolePojo> roles) {
        this.roles = roles;
    }
}
