package com.jk.projectp.service;

import cn.hutool.core.lang.Pair;
import com.jk.projectp.dao.FreshDAO;
import com.jk.projectp.dao.FreshPositionDAO;
import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.FreshPosition;
import com.jk.projectp.utils.SetOperation;
import com.jk.projectp.utils.dataenum.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FreshService {
    @Autowired
    private FreshDAO freshDAO;

    @Autowired
    private FreshPositionDAO freshPositionDAO;

    public boolean createFresh(Fresh fresh) {
        if (freshDAO.findByItsc(fresh.getItsc()) != null)
            return false;
        freshDAO.save(fresh);
        return true;
    }

    public void setPositions(Fresh fresh, Set<Position> positions){
        Set<FreshPosition> newPositions = new HashSet<>();
        for (Position position:positions){
            newPositions.add(new FreshPosition(position, fresh));
        }
        fresh.setFreshPositions(newPositions);
        freshDAO.save(fresh);
    }
}
