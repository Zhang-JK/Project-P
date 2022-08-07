package com.jk.projectp.dao;

import com.jk.projectp.model.CommentMessageFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentMessageFeedbackDAO extends JpaRepository<CommentMessageFeedback, Integer> {
    @Override
    Optional<CommentMessageFeedback> findById(Integer integer);
}
