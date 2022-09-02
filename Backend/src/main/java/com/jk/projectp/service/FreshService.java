package com.jk.projectp.service;

import com.jk.projectp.dao.FreshDAO;
import com.jk.projectp.dao.PositionDAO;
import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.Position;
import com.jk.projectp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FreshService {
    @Autowired
    private FreshDAO freshDAO;

    @Autowired
    private PositionDAO positionDAO;

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
}
