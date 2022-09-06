package com.jk.projectp.controller;

import com.jk.projectp.model.InterviewFresh;
import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.InterviewFreshResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.result.pojo.InterviewInfoPojo;
import com.jk.projectp.result.pojo.InterviewPojo;
import com.jk.projectp.service.InterviewService;
import com.jk.projectp.service.RoleService;
import com.jk.projectp.service.UserService;
import com.jk.projectp.utils.dataenum.WebPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/interview/getSections")
    @ResponseBody
    public BaseResult<List<InterviewPojo>> getSections(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        return new BaseResult<>(ResponseCode.SUCCESS, interviewService.getInterviews().stream().map(InterviewPojo::new).toList());
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
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

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/interview/update")
    @ResponseBody
    public BaseResult<InterviewInfoPojo> update(HttpServletRequest request, @RequestParam Integer freshId, @RequestParam Integer interviewId) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        ResponseCode code = interviewService.updateInterview(user.getId(), freshId, interviewId);
        if (code == ResponseCode.SUCCESS) {
            return new BaseResult<>(ResponseCode.SUCCESS, new InterviewInfoPojo(interviewService.getInfoByFreshId(freshId)));
        } else {
            return new BaseResult<>(code);
        }
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/interview/getInfo")
    @ResponseBody
    public BaseResult<InterviewInfoPojo> getInfo(HttpServletRequest request, @RequestParam Integer freshId) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        InterviewFresh iFresh = interviewService.getInfoByFreshId(freshId);
        if (interviewService.getInfoByFreshId(freshId) == null) {
            return new BaseResult<>(ResponseCode.USER_NOT_EXIST);
        }
        if (!Objects.equals(iFresh.getFresh().getUser().getId(), user.getId())) {
            return new BaseResult<>(ResponseCode.PERMISSION_DENY);
        }
        return new BaseResult<>(ResponseCode.SUCCESS, new InterviewInfoPojo(iFresh));
    }
}
