package com.jk.projectp.controller;

import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.service.ResponseCode;
import com.jk.projectp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/user/login")
    @ResponseBody
    public BaseResult<String> login(@RequestBody User requestUser, HttpServletResponse response) {
        User user = userService.getByUsername(requestUser.getUsername());
        if (user == null) {
            return new BaseResult<>(ResponseCode.LOGIN_USER_NOT_EXIST);
        }
        if (!userService.verifyPassword(user, requestUser.getPassword())) {
            return new BaseResult<>(ResponseCode.LOGIN_WRONG_PASSWORD);
        }
        String session = userService.createSession(user);
        Cookie sessionCookie = new Cookie("session", session);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(60 * 60 * 24 * 7);
        Cookie usernameCookie = new Cookie("username", requestUser.getUsername());
        usernameCookie.setPath("/");
        usernameCookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(usernameCookie);
        response.addCookie(sessionCookie);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }


    @CrossOrigin
    @PostMapping(value = "/api/user/logout")
    @ResponseBody
    public BaseResult<String> logout(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        userService.clearSession(user);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }


    @CrossOrigin
    @GetMapping(value = "/api/user/changePassword")
    @ResponseBody
    public BaseResult<String> changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (!userService.verifyPassword(user, oldPassword)) {
            return new BaseResult<>(ResponseCode.LOGIN_WRONG_PASSWORD);
        }
        userService.updatePassword(user, newPassword);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }
}
