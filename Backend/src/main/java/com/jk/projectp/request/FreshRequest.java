package com.jk.projectp.request;

import com.jk.projectp.utils.dataenum.Gender;
import com.jk.projectp.utils.dataenum.Grade;

import java.util.Set;

public class FreshRequest {
    String name;
    String chineseName;
    String nickName;
    Gender gender;
    String itsc;
    Grade grade;
    String major;
    String info;
    String password;

    Set<String> positions;

    public Set<String> getPositions() {
        return positions;
    }

    public void setPositions(Set<String> positions) {
        this.positions = positions;
    }

    public FreshRequest(String name, String chineseName, String nickName, Gender gender, String itsc, Grade grade, String major, String info, String password, Set<String> positions) {
        this.name = name;
        this.chineseName = chineseName;
        this.nickName = nickName;
        this.gender = gender;
        this.itsc = itsc;
        this.grade = grade;
        this.major = major;
        this.info = info;
        this.password = password;
        this.positions = positions;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
