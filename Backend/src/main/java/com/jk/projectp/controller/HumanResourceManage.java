package com.jk.projectp.controller;

import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.MemberResponse;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.RoleService;
import com.jk.projectp.service.UserService;
import com.jk.projectp.utils.dataenum.WebPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class HumanResourceManage {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(value = "/api/humanResource/list")
    @ResponseBody
    public BaseResult<Set<MemberResponse>> list(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, false)) {
            return new BaseResult<>(ResponseCode.SUCCESS, userService.getAllUsersWithRole());
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }
}
