package com.jk.projectp.dao;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.SelfReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelfReviewDAO extends JpaRepository<SelfReview, Integer> {
    SelfReview findByFromFresh(Fresh fromFresh);
}