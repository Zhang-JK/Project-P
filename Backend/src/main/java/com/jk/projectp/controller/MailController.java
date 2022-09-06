package com.jk.projectp.controller;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MailController {
    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/"}, allowCredentials = "true")
    @GetMapping(value = "/api/testMail")
    @ResponseBody
    public BaseResult<String> createFeedback() {
    try {
        for (int i = 0; i < 10; i++)
        {
            MailService.sendEmail("zhuozeng@gmail.com", "jzhanger@connect.ust.hk", "Online Briefing - Minor Program in Entrepreneurship (6:15pm, 6 Sep 2022)", "Dear Students,\n" +
                    "\n" +
                    "The School of Engineering, the School of Business and Management and the School of Science are offering a Joint Minor in Entrepreneurship Program. This Minor Program is designed mainly for engineering, business and science students, but is also open to all students to apply. Any undergraduate students with an overall CGA of 2.7 or above may apply for this Minor Program.\n" +
                    "\n" +
                    "In order to provide students with more information about this program and the related courses (including ENTR 1001 and ENTR 4901 -- 4904), a briefing session will be held. You are cordially invited to attend this online briefing session. The details are as below:        \n" +
                    "Date: 6 Sep 2022\n" +
                    "Time: 6:15pm\n" +
                    "Join Zoom Meeting at https://hkust.zoom.us/j/97245994004?pwd=NXZIcTFlVnJIUWNpNXVQTmt1K0cydz09 (Pls log in by HKUST Zoom account) Meeting ID: 972 4599 4004\n" +
                    "Passcode: 299188\n" +
                    "\n" +
                    "You can find the ENTR Minor Program website at https://seng.ust.hk/academics/undergraduate/minor-programs/entrepreneurship, and the enrollment procedures for ENTR4901-4 Student-led Entrepreneurship Acceleration Project at https://seng.ust.hk/sites/default/files/IMCE/UG_Minor_ENTR4901-4_Application_Procedures.pdf. \n" +
                    "\n" +
                    "Should you have any enquiry, please feel free to email us at sengug@ust.hk, bmminor@ust.hk or sscicourse@ust.hk. \n" +
                    "\n" +
                    "School of Engineering\n" +
                    "School of Business and Management\n" +
                    "School of Science\n");
        }
        return new BaseResult<>(ResponseCode.SUCCESS);
    }catch (Exception e){
        return new BaseResult<>(ResponseCode.CREATE_ANNOUNCEMENT_ERROR, e.getMessage());
    }
    }
}
