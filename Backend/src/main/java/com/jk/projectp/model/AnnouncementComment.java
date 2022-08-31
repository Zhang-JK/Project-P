package com.jk.projectp.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "announcement_comment")
public class AnnouncementComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "announcement_id", nullable = false)
    private Integer announcementId;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "from_uid", nullable = false)
    private Integer fromUid;

    @Column(name = "time", nullable = false)
    private Instant time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

}
