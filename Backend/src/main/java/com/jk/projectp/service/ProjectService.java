package com.jk.projectp.service;

import com.jk.projectp.dao.ProjectDAO;
import com.jk.projectp.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectDAO projectDAO;

    public Project getProjectById(Integer project_id) {
        return project_id != null ? projectDAO.findById(project_id).orElse(null) : null;
    }
}
