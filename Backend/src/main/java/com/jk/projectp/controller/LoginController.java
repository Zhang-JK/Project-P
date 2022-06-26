package com.jk.projectp.controller;

import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.service.ResponseCode;
import com.jk.projectp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public BaseResult<String> login(@RequestBody User requestUser, HttpServletResponse response){
        User user = userService.getByUsername(requestUser.getUsername());
        if (user == null){
            return new BaseResult<>(ResponseCode.LOGIN_USER_NOT_EXIST);
        }
        if (!userService.verifyPassword(user, requestUser.getPassword())){
            return new BaseResult<>(ResponseCode.LOGIN_WRONG_PASSWORD);
        }
        String session = userService.createSession(requestUser);
        Cookie sessionCookie = new Cookie("session", session);
        sessionCookie.setDomain("/");
        Cookie usernameCookie = new Cookie("username", requestUser.getUsername());
        usernameCookie.setDomain("/");
        response.addCookie(usernameCookie);
        response.addCookie(sessionCookie);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }
}
