package com.jk.projectp.dao;

import com.jk.projectp.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoDAO extends JpaRepository<Demo, Integer> {

}