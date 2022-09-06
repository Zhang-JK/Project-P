package com.jk.projectp.service;

import com.jk.projectp.dao.FreshDAO;
import com.jk.projectp.dao.LogDAO;
import com.jk.projectp.dao.PositionDAO;
import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.Log;
import com.jk.projectp.model.Position;
import com.jk.projectp.model.User;
import com.jk.projectp.utils.dataenum.FreshStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FreshService {
    @Autowired
    private FreshDAO freshDAO;

    @Autowired
    private PositionDAO positionDAO;

    @Autowired
    LogDAO logDAO;

    public Fresh getFreshByUser(User u) {
        return freshDAO.findByUser(u);
    }

    public List<Fresh> getAll() {
        return freshDAO.findAll();
    }

    public boolean createFresh(Fresh fresh) {
        if (freshDAO.findByItsc(fresh.getItsc()) != null)
            return false;
        freshDAO.save(fresh);
        return true;
    }

    public void setPositions(Fresh fresh, Set<String> positions){
        Set<Position> posSet = new HashSet<>();
        for (String pos : positions) {
            Position p = positionDAO.findByName(pos);
            if (p != null)
                posSet.add(p);
        }
        fresh.setPositions(posSet);
    }

    public boolean updateStage(User user, Integer freshId, FreshStage stage, String msg) {
        Fresh f = freshDAO.findById(freshId).isPresent() ? freshDAO.findById(freshId).get() : null;
        if (f == null)
            return false;
        Log log = new Log();
        log.setCatalog("fresh");
        log.setOpId(user.getId());
        log.setFreshId(freshId);
        log.setOperation("change stage from " + f.getStage() + " to " + stage);
        log.setRemark(msg);
        log.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
        f.setStage(stage);
        freshDAO.save(f);
        logDAO.save(log);
        return true;
    }
}
