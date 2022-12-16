package com.jk.projectp.dao;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.PeerReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeerReviewDAO extends JpaRepository<PeerReview, Integer> {
    PeerReview findByFromFreshAndTargetFresh(Fresh fromFresh, Fresh targetFresh);
}