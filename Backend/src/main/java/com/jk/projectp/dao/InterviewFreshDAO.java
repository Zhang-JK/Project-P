package com.jk.projectp.dao;

import com.jk.projectp.model.InterviewFresh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewFreshDAO extends JpaRepository<InterviewFresh, Integer> {
    int countByInterviewId(int interviewId);

    List<InterviewFresh> findAllByInterviewIdOrderByTimeIndexAsc(int interviewId);

    InterviewFresh findByFreshId(Integer fresh_id);
}
