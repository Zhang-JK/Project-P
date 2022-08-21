package com.jk.projectp.service;

import cn.hutool.core.util.IdUtil;
import com.jk.projectp.dao.UserDAO;
import com.jk.projectp.model.User;
import cn.hutool.crypto.SecureUtil;
import com.jk.projectp.result.MemberResponse;
import com.jk.projectp.result.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User getById(Integer id) {
        return userDAO.findById(id).isEmpty() ? null : userDAO.findById(id).get();
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

    public HttpServletResponse removeCookie(HttpServletResponse response) {
        Cookie sessionCookie = new Cookie("session", null);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(0);
        Cookie usernameCookie = new Cookie("username", null);
        usernameCookie.setPath("/");
        usernameCookie.setMaxAge(0);
        response.addCookie(usernameCookie);
        response.addCookie(sessionCookie);
        return response;
    }

    public void updatePassword(User userDB, String newPassword) {
        String salt = IdUtil.simpleUUID();
        userDB.setSalt(salt);
        userDB.setPassword(SecureUtil.md5(newPassword.concat(salt)));
        userDAO.save(userDB);
        clearSession(userDB);
    }

    public boolean verifySession(User userDB, String session) {
        if (userDB != null){
            String sessionDB = userDB.getSession();
            if (sessionDB != null)
                return sessionDB.equals(session);
            else
                return false;
        }
        return false;
    }

    public User checkLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
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

    public Set<MemberResponse> getAllUsersWithRole() {
        Set<MemberResponse> res = new HashSet<>();
        for (User user : userDAO.findAll()) {
            res.add(new MemberResponse(user, user.getRoles(), user.getUserProjects()));
        }
        return res;
    }

    public boolean deleteUser(Integer id) {
        User user = userDAO.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        userDAO.delete(user);
        return true;
    }

    public User updateUser(User user) {
        return userDAO.save(user);
    }
}
