package com.jk.projectp.controller;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.User;
import com.jk.projectp.request.FreshRequest;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.result.pojo.FreshPojo;
import com.jk.projectp.service.FreshService;
import com.jk.projectp.service.RoleService;
import com.jk.projectp.service.UserService;
import com.jk.projectp.utils.dataenum.FreshStage;
import com.jk.projectp.utils.dataenum.WebPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class FreshController {

    @Autowired
    UserService userService;

    @Autowired
    FreshService freshService;

    @Autowired
    RoleService roleService;


    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/fresh/list")
    @ResponseBody
    public BaseResult<Set<FreshPojo>> listFresh(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, false)) {
            return new BaseResult<>(ResponseCode.SUCCESS, freshService.getAll().stream().map(FreshPojo::new).collect(Collectors.toSet()));
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }



    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
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
        fresh.setStage(FreshStage.NONE);
        fresh.setRegisterTime(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
        freshService.setPositions(fresh, data.getPositions());
        fresh.setUser(user);
        userService.setRoles(user, new HashSet<>(List.of("Fresh")));
        if (freshService.createFresh(fresh)) {
            return new BaseResult<>(ResponseCode.SUCCESS);
        } else {
            return new BaseResult<>(ResponseCode.ITSC_ALREADY_EXIST);
        }
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/fresh/stage")
    @ResponseBody
    public BaseResult<Integer> changeStage(HttpServletRequest request, @RequestParam Integer freshId, @RequestParam FreshStage stage, @RequestParam String msg) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, true)) {
            return freshService.updateStage(user, freshId, stage, msg) ? new BaseResult<>(ResponseCode.SUCCESS) : new BaseResult<>(ResponseCode.USER_NOT_EXIST);
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }
}
