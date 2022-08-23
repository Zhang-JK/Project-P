package com.jk.projectp.dao;

import com.jk.projectp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
