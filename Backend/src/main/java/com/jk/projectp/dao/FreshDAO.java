package com.jk.projectp.dao;

import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.InternGroup;
import com.jk.projectp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FreshDAO extends JpaRepository<Fresh, Integer> {
    Set<Fresh> findByGroup(InternGroup group);
    Fresh findByItsc(String itsc);

    Fresh findByUser(User user);
}
