package com.jk.projectp.controller;

import com.jk.projectp.model.Feedback;
import com.jk.projectp.model.FeedbackComment;
import com.jk.projectp.model.User;
import com.jk.projectp.request.CommentRequest;
import com.jk.projectp.request.FeedbackRequest;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.FeedbackCommentResponse;
import com.jk.projectp.result.FeedbackResponse;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.FeedbackService;
import com.jk.projectp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Controller
public class FeedbackController {
    @Autowired
    FeedbackService fbService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/createFeedback")
    @ResponseBody
    public BaseResult<String> createFeedback(@RequestBody String msg, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // TODO:check permission here

        if (fbService.createFB(user, msg, LocalDateTime.now().toInstant(ZoneOffset.UTC))) {
            return new BaseResult<>(ResponseCode.SUCCESS);
        }
        return new BaseResult<>(ResponseCode.CREATING_FB_ERROR);
    }

    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/createComment")
    @ResponseBody
    public BaseResult<String> createComment(@RequestBody CommentRequest data, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // TODO: check permission here

        Feedback fb = fbService.getFeedbackById(data.getFbId());

        if (fb == null) {
            return new BaseResult<>(ResponseCode.FB_NOT_EXIST);
        }
        if (fbService.createComment(user, data.getMsg(), data.getCommentId(), fb, LocalDateTime.now().toInstant(ZoneOffset.UTC)))
            return new BaseResult<>(ResponseCode.SUCCESS);
        else
            return new BaseResult<>(ResponseCode.CREATING_COMMENT_ERROR);
    }

    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/fetchFeedback")
    @ResponseBody
    public BaseResult<FeedbackResponse> fetchFeedback(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // TODO: check permission here
        List<Feedback> result = fbService.getAllFeedback();

        return new BaseResult<>(ResponseCode.SUCCESS, new FeedbackResponse(result));
    }

    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/fetchComment")
    @ResponseBody
    public BaseResult<FeedbackCommentResponse> fetchComment(@RequestBody Long fbId, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }

        // TODO: check permission here
        List<FeedbackComment> result = fbService.getCommentOfFeedback(fbId);

        return new BaseResult<>(ResponseCode.SUCCESS, new FeedbackCommentResponse(result));
    }


    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/editFeedback")
    @ResponseBody
    public BaseResult<String> editFeedback(@RequestBody FeedbackRequest data, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        Feedback fb = fbService.getFeedbackById(data.getFbId());
        if (fb == null){
            return new BaseResult<>(ResponseCode.FB_NOT_EXIST);
        }
        if (!Objects.equals(fb.getFromUid().getId(), user.getId())){ // If not the publisher
            // TODO: check permission here
        }
        fb.setContent(data.getMsg());
        fb.setTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        fbService.saveFeedback(fb);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }

    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/editComment")
    @ResponseBody
    public BaseResult<String> editComment(@RequestBody CommentRequest data, HttpServletRequest request){
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        FeedbackComment comment = fbService.getCommentById(data.getCommentId());
        if (comment == null)
        {
            return new BaseResult<>(ResponseCode.COMMENT_NOT_EXIST);
        }
        if (!Objects.equals(comment.getFromUid().getId(), user.getId())){ // If not the publisher
            // TODO: check permission here
        }
        comment.setContent(data.getMsg());
        comment.setTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        fbService.saveComment(comment);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }

    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/deleteFeedback")
    @ResponseBody
    public BaseResult<String> deleteFeedback(@RequestBody Long fbId, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        Feedback fb = fbService.getFeedbackById(fbId);
        if (fb == null){
            return new BaseResult<>(ResponseCode.FB_NOT_EXIST);
        }
        if (!Objects.equals(fb.getFromUid().getId(), user.getId())){ // If not the publisher
            // TODO: check permission here
        }
        fbService.deleteFeedback(fb);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }

    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/deleteComment")
    @ResponseBody
    public BaseResult<String> deleteComment(@RequestBody Integer commentId, HttpServletRequest request){
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        FeedbackComment comment = fbService.getCommentById(commentId);
        if (comment == null)
        {
            return new BaseResult<>(ResponseCode.COMMENT_NOT_EXIST);
        }
        if (!Objects.equals(comment.getFromUid().getId(), user.getId())){ // If not the publisher
            // TODO: check permission here
        }
        fbService.deleteComment(comment);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }
}
