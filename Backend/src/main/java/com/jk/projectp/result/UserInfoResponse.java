package com.jk.projectp.result;

import com.jk.projectp.model.*;
import com.jk.projectp.result.pojo.FreshPojo;
import com.jk.projectp.result.pojo.RolePojo;
import com.jk.projectp.result.pojo.UserPojo;
import com.jk.projectp.result.pojo.UserProjectPojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserInfoResponse {
    UserPojo user;
    FreshPojo freshInfo;
    Set<UserProjectPojo> projects;
    Set<RolePojo> roles;
    Map<String, Boolean> permissions;

    public UserInfoResponse(User userI, Fresh freshInfo, Set<UserProject> projectsI, Set<Role> rolesI) {
        this.user = new UserPojo(userI);
        if (freshInfo == null) this.freshInfo = null;
        else this.freshInfo = new FreshPojo(freshInfo);
        this.projects = new HashSet<>();
        this.roles = new HashSet<>();
        this.permissions = new HashMap<>();
        for (UserProject project : projectsI) {
            this.projects.add(new UserProjectPojo(project));
        }
        for (Role role : rolesI) {
            this.roles.add(new RolePojo(role));
            for (RolePage page : role.getRolePages()) {
                if (permissions.get(page.getPage().getWebPage()) == null || !permissions.get(page.getPage().getWebPage()))
                    this.permissions.put(page.getPage().getWebPage(), page.getAllowModify());
            }
        }
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public FreshPojo getFreshInfo() {
        return freshInfo;
    }

    public void setFreshInfo(FreshPojo freshInfo) {
        this.freshInfo = freshInfo;
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

    public Map<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }
}
