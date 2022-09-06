package com.jk.projectp.dao;

import com.jk.projectp.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementDAO extends JpaRepository<Announcement, Integer> {
}
