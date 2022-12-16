package com.jk.projectp.model;

import com.jk.projectp.utils.dataenum.FreshStage;
import com.jk.projectp.utils.dataenum.Gender;
import com.jk.projectp.utils.dataenum.Grade;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "itsc", nullable = false, length = 50)
    private String itsc;

    @Column(name = "grade", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "major", length = 50)
    private String major;

    @Column(name = "info", length = 500)
    private String info;

    @Column(name = "register_time")
    private Instant registerTime;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "fresh_position",
            joinColumns = @JoinColumn(name = "fresh_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Set<Position> positions = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "self_review_direction",
            joinColumns = @JoinColumn(name = "fresh_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private Set<Direction> directions = new LinkedHashSet<>();

    public Set<Direction> getDirections() {
        return directions;
    }
    public void setDirections(Set<Direction> directions) {
        this.directions = directions;
    }

    @Column(name = "stage", length = 20)
    @Enumerated(EnumType.STRING)
    private FreshStage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private InternGroup group;

    @Size(max = 255)
    @Column(name = "photo_path")
    private String photoPath;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public InternGroup getGroup() {
        return group;
    }

    public void setGroup(InternGroup group) {
        this.group = group;
    }

    public FreshStage getStage() {
        return stage;
    }

    public void setStage(FreshStage stage) {
        this.stage = stage;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    public String getItsc() {
        return itsc;
    }

    public void setItsc(String itsc) {
        this.itsc = itsc;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
}
