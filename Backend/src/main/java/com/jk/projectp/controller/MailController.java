package com.jk.projectp.controller;

import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MailController {
    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @GetMapping(value = "/api/testMail")
    @ResponseBody
    public BaseResult<String> createFeedback() {
    try {
        for (int i = 0; i < 2; i++)
        {
            MailService.sendEmail("robomasterhkust@gmail.com", "jzhanger@connect.ust.hk", "THIS IS A TESTING EMAIL", "TESTING");
        }
        return new BaseResult<>(ResponseCode.SUCCESS);
    }catch (Exception e){
        return new BaseResult<>(ResponseCode.CREATE_ANNOUNCEMENT_ERROR, e.getMessage());
    }
    }
}
