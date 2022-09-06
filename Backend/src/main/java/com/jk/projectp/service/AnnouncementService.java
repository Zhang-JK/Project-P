package com.jk.projectp.service;

import com.jk.projectp.dao.AnnouncementDAO;
import com.jk.projectp.model.Announcement;
import com.jk.projectp.model.Project;
import com.jk.projectp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AnnouncementService {
    @Autowired
    AnnouncementDAO announcementDAO;

    public boolean createAnnouncement(String title, String content, User from_uid, Instant time, Project project) {
        try {
            Announcement announcement = new Announcement(title, content, from_uid, time, project);
            announcementDAO.save(announcement);
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
