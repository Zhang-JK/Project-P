package com.jk.projectp.dao;

import com.jk.projectp.model.Feedback;
import com.jk.projectp.model.FeedbackComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentMessageFeedbackDAO extends JpaRepository<FeedbackComment, Integer> {
    @Override
    Optional<FeedbackComment> findById(Integer integer);
    List<FeedbackComment> findByFeedback_Id(Long id);

    @Transactional
    long deleteByFeedback(Feedback feedback);

    @Transactional
    List<FeedbackComment> findByParent(FeedbackComment parent);
}
