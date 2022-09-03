package com.jk.projectp.controller;

import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.UserInfoResponse;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.FreshService;
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

    @Autowired
    private FreshService freshService;

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/user/login")
    @ResponseBody
    public BaseResult<String> login(@RequestBody User requestUser, HttpServletResponse response) {
        User user = userService.getByUsername(requestUser.getUsername());
        if (user == null) {
            userService.removeCookie(response);
            return new BaseResult<>(ResponseCode.LOGIN_USER_NOT_EXIST);
        }
        if (!userService.verifyPassword(user, requestUser.getPassword())) {
            userService.removeCookie(response);
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


    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/user/logout")
    @ResponseBody
    public BaseResult<String> logout(HttpServletRequest request, HttpServletResponse response) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        userService.clearSession(user);
        userService.removeCookie(response);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }


    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
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


    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/user/getInfo")
    @ResponseBody
    public BaseResult<UserInfoResponse> getInfo(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }

        UserInfoResponse response = new UserInfoResponse(user, freshService.getFreshByUser(user), user.getUserProjects(), user.getRoles());
        return new BaseResult<>(ResponseCode.SUCCESS, response);
    }
}
