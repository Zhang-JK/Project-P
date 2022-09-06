package com.jk.projectp.dao;

import com.jk.projectp.model.AnnouncementComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementCommentDAO extends JpaRepository<AnnouncementComment, Integer> {
}
