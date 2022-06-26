package com.jk.projectp.service;

import cn.hutool.core.util.IdUtil;
import com.jk.projectp.dao.UserDAO;
import com.jk.projectp.model.User;
import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public boolean verifyPassword(User userDB, String rawPassword) {
        String password = SecureUtil.md5(rawPassword.concat(userDB.getSalt()));
        return Objects.equals(password, userDB.getPassword());
    }

    public String createSession(User userDB) {
        String session = IdUtil.simpleUUID();
        userDB.setSession(session);
        userDAO.save(userDB);
        return session;
    }

    public void clearSession(User userDB) {
        userDB.setSession("");
        userDAO.save(userDB);
    }

    public void updatePassword(User userDB, String newPassword) {
        String salt = IdUtil.simpleUUID();
        userDB.setSalt(salt);
        userDB.setPassword(SecureUtil.md5(newPassword.concat(salt)));
        userDAO.save(userDB);
        clearSession(userDB);
    }

    public boolean verifySession(String username, String session) {
        User user = getByUsername(username);
        if (user != null){
            return user.getSession().equals(session);
        }
        return false;
    }
}
