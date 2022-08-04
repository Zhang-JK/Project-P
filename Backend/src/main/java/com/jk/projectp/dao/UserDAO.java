package com.jk.projectp.dao;

import com.jk.projectp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
