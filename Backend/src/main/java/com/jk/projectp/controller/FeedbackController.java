package com.jk.projectp.controller;

import com.jk.projectp.model.Feedback;
import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.service.FeedbackService;
import com.jk.projectp.service.ResponseCode;
import com.jk.projectp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Controller
public class FeedbackController {
    @Autowired
    FeedbackService fbService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/createFeedback")
    @ResponseBody
    public BaseResult<String> createFeedback(@RequestParam String msg, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // Check permission here

        if (fbService.createFB(user, msg, LocalDateTime.now().toInstant(ZoneOffset.UTC))) {
            return new BaseResult<>(ResponseCode.SUCCESS);
        }
        return new BaseResult<>(ResponseCode.CREATING_FB_ERROR);
    }

    @CrossOrigin
    @PostMapping(value = "/api/createComment")
    @ResponseBody
    public BaseResult<String> createComment(@RequestParam String msg, @RequestParam Long fbId, @RequestParam Integer commentId, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // Check permission here

        Feedback fb = fbService.getFeedbackById(fbId);

        if (fb == null) {
            return new BaseResult<>(ResponseCode.FB_NOT_EXIST);
        }
        if (fbService.createComment(user, msg, commentId, fb, LocalDateTime.now().toInstant(ZoneOffset.UTC)))
            return new BaseResult<>(ResponseCode.SUCCESS);
        else
            return new BaseResult<>(ResponseCode.CREATING_COMMENT_ERROR);


    }
}
