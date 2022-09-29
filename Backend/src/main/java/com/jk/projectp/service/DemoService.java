package com.jk.projectp.service;

import com.jk.projectp.dao.FreshDAO;
import com.jk.projectp.dao.DemoDAO;
import com.jk.projectp.dao.DemoFreshDAO;
import com.jk.projectp.model.Demo;
import com.jk.projectp.model.Fresh;
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

    public ResponseCode updateDemo(Integer userId, Integer freshId, Integer demoId) {
        Demo demo = demoDAO.findById(demoId).isPresent() ? demoDAO.findById(demoId).get() : null;
        Fresh fresh = freshDAO.findById(freshId).isPresent() ? freshDAO.findById(freshId).get() : null;
        if (demo == null)
            return ResponseCode.DEMO_NOT_EXIST;
        int maxCapacity = 5;
        if (fresh == null)
            return ResponseCode.USER_NOT_EXIST;
        if (!Objects.equals(fresh.getUser().getId(), userId))
            return ResponseCode.NOT_LOGIN;
        if (demoFreshDAO.countByDemoId(demoId) >= maxCapacity || demo.getFull())
            return ResponseCode.DEMO_FULL;

        Demo demoPrev = null;
        DemoFresh demoFresh = demoFreshDAO.findByFreshId(freshId);
        if (demoFresh == null) {
            demoFresh = new DemoFresh();
            demoFresh.setFresh(fresh);
        } else {
            demoPrev = demoFresh.getDemo();
        }

        demoFresh.setDemo(demo);
        demoFresh.setDemoId(demoId);
        demoFresh.setFreshId(freshId);

        demoFreshDAO.save(demoFresh);
        if (demoPrev != null) {
            demoPrev.setFull(demoFreshDAO.countByDemoId(demoPrev.getId()) >= maxCapacity);
            demoDAO.save(demoPrev);
        }
        demo.setFull(demoFreshDAO.countByDemoId(demoId) >= maxCapacity);
        demoDAO.save(demo);
        return ResponseCode.SUCCESS;
    }

    public DemoFresh getInfoByFreshId(Integer freshId) {
        return demoFreshDAO.findByFreshId(freshId);
    }
}
