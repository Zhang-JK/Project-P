package com.jk.projectp.service;

import com.jk.projectp.dao.FreshDAO;
import com.jk.projectp.dao.DemoDAO;
import com.jk.projectp.dao.DemoFreshDAO;
import com.jk.projectp.model.Demo;
import com.jk.projectp.model.DemoFresh;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.utils.dataenum.FreshStage;
import com.jk.projectp.utils.dataenum.InterviewRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DemoService {
    @Autowired
    private DemoDAO demoDAO;

    @Autowired
    private DemoFreshDAO demoFreshDAO;

    @Autowired
    private FreshDAO freshDAO;

    public List<Demo> getDemos() { return demoDAO.findAll(); }


}
