package com.jk.projectp.request;

public class AnnouncementRequest {
    private Integer id;

    private String title;

    private String content;

    private Integer from_uid;

    private Integer project_id;

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public AnnouncementRequest(Integer id, String title, String content, Integer from_uid, Integer project_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.from_uid = from_uid;
        this.project_id = project_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(Integer from_uid) {
        this.from_uid = from_uid;
    }

    public AnnouncementRequest(Integer id, String title, String content, Integer from_uid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.from_uid = from_uid;
    }
}
