package com.jk.projectp.dao;

import com.jk.projectp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);

//    <S extends User>S save(S entity);
}
