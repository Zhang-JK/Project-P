package com.jk.projectp.controller;

import cn.hutool.core.util.IdUtil;
import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.InternGroup;
import com.jk.projectp.model.PeerReview;
import com.jk.projectp.model.User;
import com.jk.projectp.request.*;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.result.pojo.FreshPojo;
import com.jk.projectp.result.pojo.PeerReviewPojo;
import com.jk.projectp.service.FreshService;
import com.jk.projectp.service.RoleService;
import com.jk.projectp.service.StorageService;
import com.jk.projectp.service.UserService;
import com.jk.projectp.utils.dataenum.FreshStage;
import com.jk.projectp.utils.dataenum.WebPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @Autowired
    StorageService storageService;


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
    @PostMapping(value = "/api/fresh/sameGroupList")
    @ResponseBody
    public BaseResult<Set<FreshPojo>> listSameGroupFresh(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (freshService.findFreshByUser(user).isPresent()){
            Fresh fresh = freshService.findFreshByUser(user).get();
            if (fresh.getGroup() != null) {
                return new BaseResult<>(ResponseCode.SUCCESS, freshService.findFreshByGroup( fresh.getGroup()).stream().map(FreshPojo::new).collect(Collectors.toSet()));
            }
        } else {
            return new BaseResult<>(ResponseCode.FRESH_NOT_EXIST);
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

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/addGroup")
    @ResponseBody
    public BaseResult<String> addGroup(HttpServletRequest request, @RequestBody AddInternGroupRequest data) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, true)) {
            return freshService.addInternGroup(data.getGroupNum(), data.getGroupName()) ? new BaseResult<>(ResponseCode.SUCCESS) : new BaseResult<>(ResponseCode.CREATE_GROUP_ERROR);
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/getGroups")
    @ResponseBody
    public BaseResult<Set<InternGroup>> addGroup(HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        return new BaseResult<>(ResponseCode.SUCCESS, freshService.getAllInternGroups());
    }

    //set group
    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/setGroup")
    @ResponseBody
    public BaseResult<Integer> setGroup(HttpServletRequest request, @RequestBody SetGroupRequest data) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, true)) {
            return freshService.setInternGroup(data.getFreshId(), data.getGroupNum()) ? new BaseResult<>(ResponseCode.SUCCESS) : new BaseResult<>(ResponseCode.SET_INTERN_GROUP_ERROR);
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }

    // upload photo
    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/uploadPhoto")
    @ResponseBody
    public BaseResult<String> uploadPhoto(HttpServletRequest request, @RequestParam("photo")MultipartFile photo, @RequestParam("freshId") Integer freshId) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        Fresh fresh = freshService.getFreshById(freshId);
        if (fresh == null) {
            return new BaseResult<>(ResponseCode.FRESH_NOT_EXIST);
        }
        if (fresh.getUser().equals(user) || roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, true)) {
            String extension;
            try {
                extension = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
            }catch (Exception e) {
                extension = ".jpg";
            }
            String concatName = fresh.getId() + "_" + IdUtil.simpleUUID() + extension;
            storageService.store(photo, concatName);
            fresh.setPhotoPath(concatName);
            freshService.updateFresh(fresh);
            return new BaseResult<>(ResponseCode.SUCCESS);
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/getPhoto")
    @ResponseBody
    public BaseResult<byte[]> getPhoto(HttpServletRequest request, @RequestParam("freshId") Integer freshId) throws IOException {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        Fresh fresh = freshService.getFreshById(freshId);
        if (fresh == null) {
            return new BaseResult<>(ResponseCode.FRESH_NOT_EXIST);
        }
        if (roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, false)) {
            Resource resource = storageService.load(fresh.getPhotoPath());
            return new BaseResult<>(ResponseCode.SUCCESS, resource.getInputStream().readAllBytes());
        }
        return new BaseResult<>(ResponseCode.PERMISSION_DENY);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/selfReview")
    @ResponseBody
    public BaseResult<String> selfReview(HttpServletRequest request, @RequestBody SelfReviewRequest data) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        Fresh fresh = freshService.getFreshByUser(user);
        if (fresh == null) {
            return new BaseResult<>(ResponseCode.FRESH_NOT_EXIST);
        }
        freshService.selfReview(fresh, data.getContribution(), data.getDirectionIds());
        return new BaseResult<>(ResponseCode.SUCCESS);
    }
    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/peerReview")
    @ResponseBody
    public BaseResult<String> peerReview(HttpServletRequest request, @RequestBody PeerReviewRequest data) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        Fresh fresh = freshService.getFreshByUser(user);
        if (fresh == null) {
            return new BaseResult<>(ResponseCode.FRESH_NOT_EXIST);
        }
        Fresh targetFresh = freshService.getFreshById(data.getTargetId());
        if (targetFresh.getGroup() != fresh.getGroup())
            return new BaseResult<>(ResponseCode.PEER_REVIEW_ERROR);
        freshService.peerReview(fresh, targetFresh, data.isWhetherKnow(), data.getAttendanceScore(), data.getContributionScore(), data.getCommunicationScore(), data.getComments());
        return new BaseResult<>(ResponseCode.SUCCESS);
    }

    // get one peer review
    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/getPeerReview")
    @ResponseBody
    public BaseResult<PeerReviewPojo> getPeerReview(HttpServletRequest request, @RequestBody PeerReviewRequest data){
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        Fresh fresh = freshService.getFreshByUser(user);
        if (fresh == null) {
            return new BaseResult<>(ResponseCode.FRESH_NOT_EXIST);
        }
        Fresh targetFresh = freshService.getFreshById(data.getTargetId());
        if (targetFresh == null) {
            return new BaseResult<>(ResponseCode.FRESH_NOT_EXIST);
        }
        if (fresh.getGroup() != targetFresh.getGroup() && !roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, false)) {
            return new BaseResult<>(ResponseCode.PEER_REVIEW_ERROR);
        }
        PeerReview peerReview = freshService.getPeerReview(fresh, targetFresh);
        if (peerReview == null) {
            return new BaseResult<>(ResponseCode.PEER_REVIEW_NOT_EXIST);
        }
        return new BaseResult<>(ResponseCode.SUCCESS, new PeerReviewPojo(peerReview));
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://laojk.club/", "http://asoul.chaoshi.me/", "http://10.89.51.52:3000/"}, allowCredentials = "true")
    @PostMapping(value = "/api/fresh/getAllPeerReviews")
    @ResponseBody
    public BaseResult<Set<PeerReviewPojo>> getAllPeerReviews(HttpServletRequest request){
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        if (!roleService.verifyPermission(user, WebPages.HUMAN_RESOURCE, false)) {
            return new BaseResult<>(ResponseCode.PERMISSION_DENY);
        }
        List<PeerReview> peerReviews = freshService.getAllPeerReviews();
        return new BaseResult<>(ResponseCode.SUCCESS, peerReviews.stream().map(PeerReviewPojo::new).collect(Collectors.toSet()));
    }
}
