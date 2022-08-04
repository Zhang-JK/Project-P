package com.jk.projectp.dao;

import com.jk.projectp.model.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRoleDAO extends JpaRepository<UserProject, Integer> {
    boolean existsById_ProjectIdAndId_UserIdAndRole(Integer projectId, Integer userId, Integer role);

}
