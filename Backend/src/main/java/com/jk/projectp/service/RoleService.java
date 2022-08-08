package com.jk.projectp.service;

import com.jk.projectp.model.Role;
import com.jk.projectp.model.User;
import com.jk.projectp.utils.dataenum.WebPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    UserService userService;

    public boolean verifyPermission(String username, WebPages page, boolean modify) {
        User user = userService.getByUsername(username);
        return verifyPermission(user, page, modify);
    }

    public boolean verifyPermission(User user, WebPages page, boolean modify) {
        if (user == null)
            return false;
        for (Role role : user.getRoles()) {
            if (role.getRolePages().stream().anyMatch(rolePage -> rolePage.getPage().equals(page) && (!modify || rolePage.getAllowModify().equals(true))))
                return true;
        }
        return false;
    }
}
