package com.jk.projectp.result;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.Position;
import com.jk.projectp.utils.dataenum.Gender;
import com.jk.projectp.utils.dataenum.Grade;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class FreshListResult {
    String name;
    String chineseName;
    String nickName;
    Gender gender;
    String itsc;
    Grade grade;
    String major;
    String info;
    Set<String> positions;
    Integer userId;

    Instant registerTime;

    public Set<String> getPositions() {
        return positions;
    }

    public void setPositions(Set<String> positions) {
        this.positions = positions;
    }

    public FreshListResult(Fresh fresh) {
        this.name = fresh.getName();
        this.chineseName = fresh.getChineseName();
        this.nickName = fresh.getNickName();
        this.gender = fresh.getGender();
        this.itsc = fresh.getItsc();
        this.grade = fresh.getGrade();
        this.major = fresh.getMajor();
        this.info = fresh.getInfo();
        this.positions = fresh.getPositions().stream().map(Position::getName).collect(Collectors.toSet());
        this.userId = fresh.getUser().getId();
        this.registerTime = fresh.getRegisterTime();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Instant registerTime) {
        this.registerTime = registerTime;
    }
}
