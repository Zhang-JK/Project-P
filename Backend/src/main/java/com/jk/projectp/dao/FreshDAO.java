package com.jk.projectp.dao;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreshDAO extends JpaRepository<Fresh, Integer> {
    Fresh findByItsc(String itsc);

    Fresh findByUser(User user);
}
