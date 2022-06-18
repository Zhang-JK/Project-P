package com.jk.projectp.service;

import com.jk.projectp.dao.UserDAO;
import com.jk.projectp.model.User;
import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UserService {
    @Autowired
    UserDAO userDAO;

    User getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    boolean verifyPassword(User userDB, String rawPassword) {
        String password = SecureUtil.md5(rawPassword.concat(userDB.getSalt()));
        return Objects.equals(password, userDB.getPassword());
    }

    String createSession(User userDB) {
        return null;
    }

    void clearSession(User userDB) {

    }

    void updatePassword(User userDB, String newPassword) {

    }
}
