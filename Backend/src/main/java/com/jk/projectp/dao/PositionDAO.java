package com.jk.projectp.dao;

import com.jk.projectp.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionDAO extends JpaRepository<Position, Integer> {
    Position findByName(String name);
}

