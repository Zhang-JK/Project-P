package com.jk.projectp.controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.result.pojo.DemoPojo;
import com.jk.projectp.service.UserService;
import com.jk.projectp.service.DemoService;
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
public class DemoController {


   @Autowired
   private DemoService demoService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/demo/getSections")
    @ResponseBody
    public BaseResult<List<DemoPojo>> getSections(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }

        return new BaseResult<>(ResponseCode.SUCCESS, demoService.getDemos().stream().map(DemoPojo::new).toList());
    }

}
