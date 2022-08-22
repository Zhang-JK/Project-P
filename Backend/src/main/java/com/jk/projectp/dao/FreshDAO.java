package com.jk.projectp.dao;

import com.jk.projectp.model.Fresh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreshDAO extends JpaRepository<Fresh, Long> {
    Fresh findByItsc(String itsc);
}
