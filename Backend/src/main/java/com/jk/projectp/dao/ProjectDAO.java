package com.jk.projectp.dao;

import com.jk.projectp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectDAO extends JpaRepository<Project, Integer> {
    @Override
    Optional<Project> findById(Integer integer);
}
