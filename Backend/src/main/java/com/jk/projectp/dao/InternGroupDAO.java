package com.jk.projectp.dao;

import com.jk.projectp.model.InternGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternGroupDAO extends JpaRepository<InternGroup, Integer> {
    InternGroup findByGroupNum(Integer groupNum);

}