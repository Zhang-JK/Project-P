package com.jk.projectp.dao;

import com.jk.projectp.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageFeedbackDAO extends JpaRepository<Feedback, Long> {
    @Override
    Optional<Feedback> findById(Long aLong);

//    <S extends MessageFeedback>S save(S entity);
}
