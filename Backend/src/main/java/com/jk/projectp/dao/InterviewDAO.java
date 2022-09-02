package com.jk.projectp.dao;

import com.jk.projectp.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewDAO extends JpaRepository<Interview, Integer> {
}
