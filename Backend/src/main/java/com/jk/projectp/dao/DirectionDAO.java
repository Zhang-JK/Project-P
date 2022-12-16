package com.jk.projectp.dao;

import com.jk.projectp.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionDAO extends JpaRepository<Direction, Integer> {
}