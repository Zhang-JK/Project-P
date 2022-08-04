package com.jk.projectp.service;

import cn.hutool.core.util.IdUtil;
import com.jk.projectp.dao.UserDAO;
import com.jk.projectp.model.User;
import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    public boolean verifySession(User userDB, String session) {
        if (session.equals(""))
            return false;
        if (userDB != null) {
            return userDB.getSession().equals(session);
        }
        return false;
    }
    public User checkLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String session = null;
        User user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
                user = getByUsername(username);
            }
            if (cookie.getName().equals("session")) {
                session = cookie.getValue();
            }
        }
        if (user == null || username == null || session == null) {
            return null;
        }
        if (!verifySession(user, session)) {
            return null;
        }
        return user;
    }
}
