package com.jk.projectp.dao;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.FreshPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FreshPositionDAO extends JpaRepository<FreshPosition, Integer> {
    Set<FreshPosition> findByFresh(Fresh fresh);
}
