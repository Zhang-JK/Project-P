package com.jk.projectp.controller;

import com.jk.projectp.model.User;
import com.jk.projectp.request.AnnouncementRequest;
import com.jk.projectp.request.FeedbackRequest;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.AnnouncementService;
import com.jk.projectp.service.ProjectService;
import com.jk.projectp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/"}, allowCredentials = "true")
    @PostMapping(value = "/api/createAnnouncement")
    @ResponseBody
    public BaseResult<String> createAnnouncement(@RequestBody AnnouncementRequest data, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // TODO:check permission here

        if (announcementService.createAnnouncement(data.getTitle(), data.getContent(), user,
                LocalDateTime.now().toInstant(ZoneOffset.of("+8")), projectService.getProjectById(data.getProject_id()))) {
            return new BaseResult<>(ResponseCode.SUCCESS);
        } else
            return new BaseResult<>(ResponseCode.CREATE_ANNOUNCEMENT_ERROR);
    }

}
