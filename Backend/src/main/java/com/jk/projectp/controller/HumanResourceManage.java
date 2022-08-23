package com.jk.projectp.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.MemberResponse;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.result.UserUpdateResponse;
import com.jk.projectp.service.RoleService;
import com.jk.projectp.service.UserService;
import com.jk.projectp.utils.dataenum.WebPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Controller
public class HumanResourceManage {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/"}, allowCredentials = "true")
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

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/"}, allowCredentials = "true")
    @PostMapping(value = "/api/humanResource/delete")
    @ResponseBody
    public BaseResult<Set<Integer>> delete(@RequestBody Set<Integer> ids, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, true)) {
            Set<Integer> result = new HashSet<>();
            for (Integer id : ids) {
                User targetUser = userService.getById(id);
                if (!Objects.equals(id, user.getId()) && targetUser != null && targetUser.getRoles().stream().noneMatch(r -> r.getId() == 1) && userService.deleteUser(id)) {
                    result.add(id);
                }
            }
            return new BaseResult<>(ResponseCode.SUCCESS, result);
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/"}, allowCredentials = "true")
    @PostMapping(value = "/api/humanResource/update")
    @ResponseBody
    public BaseResult<UserUpdateResponse> update(@RequestBody Set<User> updateUsers, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, true)) {
            UserUpdateResponse result = new UserUpdateResponse();
            for (User updateUser : updateUsers) {
                if (updateUser.getUsername() == null || updateUser.getEmail() == null || updateUser.getName() == null) {
                    result.addToFailedUsers(updateUser, "Username, Email, Name cannot be null");
                    continue;
                } else if (updateUser.getUsername().isEmpty() || updateUser.getEmail().isEmpty() || updateUser.getName().isEmpty()) {
                    result.addToFailedUsers(updateUser, "Username, Email, Name cannot be empty");
                    continue;
                }

                User targetUser = userService.getByUsername(updateUser.getUsername());
                if (targetUser != null) {
                    if (updateUser.getId() != null && Objects.equals(targetUser.getId(), updateUser.getId())) {
                        result.addToFailedUsers(updateUser, "ID not match");
                    } else {
                        targetUser.setName(updateUser.getName());
                        targetUser.setEmail(updateUser.getEmail());
                        if (updateUser.getPassword() != null) {
                            targetUser.setSalt(RandomUtil.randomString(32));
                            targetUser.setPassword(SecureUtil.md5(updateUser.getPassword() + targetUser.getSalt()));
                        }
                        result.addToUpdatedUsers(userService.updateUser(targetUser));
                    }
                } else {
                    if (updateUser.getId() != null) {
                        result.addToFailedUsers(updateUser, "Username not exist but ID exist");
                    } else {
                        updateUser.setSalt(RandomUtil.randomString(32));
                        updateUser.setPassword(SecureUtil.md5(SecureUtil.md5(updateUser.getUsername()) + updateUser.getSalt()));
                        result.addToCreatedUsers(userService.updateUser(updateUser));
                    }
                }
            }
            return new BaseResult<>(ResponseCode.SUCCESS, result);
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }
}
