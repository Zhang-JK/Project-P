package com.jk.projectp.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "fresh")
public class Fresh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "chinese_name", length = 20)
    private String chineseName;

    @Column(name = "nick_name", length = 50)
    private String nickName;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "itsc", nullable = false, length = 50)
    private String itsc;

    @Column(name = "grade", nullable = false, length = 10)
    private String grade;

    @Column(name = "major", length = 50)
    private String major;

    @Column(name = "info", length = 500)
    private String info;

    @Column(name = "register_time")
    private Instant registerTime;

    @OneToMany(mappedBy = "fresh")
    private Set<FreshPosition> freshPositions = new LinkedHashSet<>();

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getItsc() {
        return itsc;
    }

    public void setItsc(String itsc) {
        this.itsc = itsc;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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

    public Set<FreshPosition> getFreshPositions() {
        return freshPositions;
    }

    public void setFreshPositions(Set<FreshPosition> freshPositions) {
        this.freshPositions = freshPositions;
    }

}