package com.jk.projectp.controller;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.User;
import com.jk.projectp.request.FreshRequest;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.FreshService;
import com.jk.projectp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class FreshController {

    @Autowired
    UserService userService;

    @Autowired
    FreshService freshService;



    @CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
    @PostMapping(value = "/api/fresh/create")
    @ResponseBody
    public BaseResult<String> createFresh(@RequestBody FreshRequest data) {
        if (data.getName() == null
                || data.getGender() == null
                || data.getItsc() == null
                || data.getGrade() == null
                || data.getPassword() == null) {
            return new BaseResult<>(ResponseCode.INSUFFICIENT_INFO);
        }
        try {
            Pattern p = Pattern.compile("^[a-z]+@(connect\\.)?ust\\.hk$");
            Matcher m = p.matcher(data.getItsc());
            if (!(m.matches())) {
                return new BaseResult<>(ResponseCode.ITSC_FORMAT_WRONG);
            }
        } catch (Exception e) {
            return new BaseResult<>(ResponseCode.ITSC_FORMAT_WRONG);
        }
        if ((data.getPositions().size() == 0))
            return new BaseResult<>(ResponseCode.FRESH_POSITION_NOT_SELECTED);
        if (userService.getByUsername(data.getItsc().split("@")[0]) != null)
            return new BaseResult<>(ResponseCode.ITSC_ALREADY_EXIST);
        User user = userService.saveUser(data.getItsc().split("@")[0], data.getPassword(), data.getItsc(), data.getName());
        Fresh fresh = new Fresh();
        fresh.setName(data.getName());
        fresh.setChineseName(data.getChineseName());
        fresh.setNickName(data.getNickName());
        fresh.setGender(data.getGender());
        fresh.setItsc(data.getItsc());
        fresh.setGrade(data.getGrade());
        fresh.setMajor(data.getMajor());
        fresh.setInfo(data.getInfo());
        fresh.setRegisterTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        freshService.setPositions(fresh, data.getPositions());
        fresh.setUser(user);
        userService.setRoles(user, new HashSet<String>(List.of("Fresh")));
        if (freshService.createFresh(fresh)) {
            return new BaseResult<>(ResponseCode.SUCCESS);
        } else {
            return new BaseResult<>(ResponseCode.ITSC_ALREADY_EXIST);
        }
    }
}
