package com.jk.projectp.result.pojo;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.Position;
import com.jk.projectp.utils.dataenum.FreshStage;
import com.jk.projectp.utils.dataenum.Gender;
import com.jk.projectp.utils.dataenum.Grade;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FreshPojo {
    private Integer id;
    private String name;
    private String chineseName;
    private String nickName;
    private Gender gender;
    private String itsc;
    private Grade grade;
    private String major;
    private String info;
    private Instant registerTime;
    private List<String> positions = new ArrayList<>();
    private FreshStage stage;
    private Integer userId;

    public FreshPojo(Fresh f) {
        this.id = f.getId();
        this.name = f.getName();
        this.chineseName = f.getChineseName();
        this.nickName = f.getNickName();
        this.gender = f.getGender();
        this.itsc = f.getItsc();
        this.grade = f.getGrade();
        this.major = f.getMajor();
        this.info = f.getInfo();
        this.registerTime = f.getRegisterTime();
        this.positions = f.getPositions().stream().map(Position::getName).collect(Collectors.toList());
        this.stage = f.getStage();
        this.userId = f.getUser().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getItsc() {
        return itsc;
    }

    public void setItsc(String itsc) {
        this.itsc = itsc;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Instant getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Instant registerTime) {
        this.registerTime = registerTime;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public FreshStage getStage() {
        return stage;
    }

    public void setStage(FreshStage stage) {
        this.stage = stage;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
