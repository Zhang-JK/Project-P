package com.jk.projectp.service;

import com.jk.projectp.dao.FreshDAO;
import com.jk.projectp.model.Fresh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreshService {
    @Autowired
    private FreshDAO freshDAO;

    public boolean createFresh(Fresh fresh) {
        if (freshDAO.findByItsc(fresh.getItsc()) != null)
            return false;
        freshDAO.save(fresh);
        return true;
    }
}
