package com.jk.projectp.service;

import com.jk.projectp.dao.InterviewDAO;
import com.jk.projectp.model.Interview;
import com.jk.projectp.result.InterviewFreshResult;
import com.jk.projectp.result.pojo.InterviewPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private InterviewDAO interviewDAO;

    public List<Interview> getInterviews() {
        return interviewDAO.findAll();
    }
}
