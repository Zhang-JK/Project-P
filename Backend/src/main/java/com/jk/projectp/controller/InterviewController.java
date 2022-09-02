package com.jk.projectp.controller;

import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.InterviewFreshResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.result.pojo.InterviewPojo;
import com.jk.projectp.service.InterviewService;
import com.jk.projectp.service.RoleService;
import com.jk.projectp.service.UserService;
import com.jk.projectp.utils.dataenum.WebPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/"}, allowCredentials = "true")
    @GetMapping(value = "/api/interview/getSections")
    @ResponseBody
    public BaseResult<List<InterviewPojo>> getSections(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        return new BaseResult<>(ResponseCode.SUCCESS, interviewService.getInterviews().stream().map(InterviewPojo::new).toList());
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/"}, allowCredentials = "true")
    @GetMapping(value = "/api/interview/getAll")
    @ResponseBody
    public BaseResult<List<InterviewFreshResult>> getAll(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, false)) {
            return new BaseResult<>(ResponseCode.SUCCESS, interviewService.getInterviews().stream().map(InterviewFreshResult::new).toList());
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }
}
