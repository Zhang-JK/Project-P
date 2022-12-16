package com.jk.projectp.service;

import com.jk.projectp.dao.*;
import com.jk.projectp.model.*;
import com.jk.projectp.utils.dataenum.FreshStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FreshService {
    @Autowired
    private FreshDAO freshDAO;

    @Autowired
    private PositionDAO positionDAO;

    @Autowired
    private InternGroupDAO internGroupDAO;
    @Autowired
    private LogDAO logDAO;

    @Autowired
    private SelfReviewDAO selfReviewDAO;

    @Autowired
    private DirectionDAO directionDAO;

    @Autowired
    private PeerReviewDAO peerReviewDAO;


    public Fresh getFreshByUser(User u) {
        return freshDAO.findByUser(u);
    }

    public Fresh getFreshById(Integer id) {
        return freshDAO.findById(id).orElse(null);
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
    // add new intern group
    public boolean addInternGroup(Integer groupNum, String groupName){
        if (internGroupDAO.findByGroupNum(groupNum) != null)
            return false;
        internGroupDAO.save(new InternGroup(groupNum, groupName));
        return true;
    }

    // get all intern groups
    public Set<InternGroup> getAllInternGroups(){
        return new HashSet<>(internGroupDAO.findAll());
    }

    // modify intern group
    public boolean modifyInternGroup(Integer groupNum, String groupName){
        InternGroup group = internGroupDAO.findByGroupNum(groupNum);
        if (group == null)
            return false;
        group.setGroupName(groupName);
        internGroupDAO.save(group);
        return true;
    }

    // set intern group for fresh
    public boolean setInternGroup(Integer freshId, Integer groupNum){
        Fresh fresh = freshDAO.findById(freshId).isPresent() ? freshDAO.findById(freshId).get() : null;
        if (fresh == null)
            return false;
        InternGroup group = internGroupDAO.findByGroupNum(groupNum);
        if (group == null)
            return false;
        fresh.setGroup(group);
        freshDAO.save(fresh);
        return true;
    }

    public void updateFresh(Fresh fresh) {
        freshDAO.save(fresh);
    }

    public Optional<Fresh> findFreshByUser(User user) {
        return Optional.of(freshDAO.findByUser(user));
    }
    public Set<Fresh> findFreshByGroup(InternGroup group) {
        return freshDAO.findByGroup(group);
    }

    public void selfReview(Fresh fresh, String contribution, Set<Integer> directionIds) {
        SelfReview selfReview = selfReviewDAO.findByFromFresh(fresh);
        if (selfReview == null) {
            selfReview = new SelfReview(contribution, fresh);
        }

        Set<Direction> directions = new HashSet<>();
        for (Integer id : directionIds) {
            Optional<Direction> position = directionDAO.findById(id);
            position.ifPresent(directions::add);
        }
        fresh.setDirections(directions);
        selfReviewDAO.save(selfReview);
        freshDAO.save(fresh);
    }

    public void peerReview(Fresh fresh, Fresh targetFresh, boolean whetherKnow,Integer attendanceScore, Integer contributionScore, Integer communicationScore, String comments) {
        PeerReview peerReview = peerReviewDAO.findByFromFreshAndTargetFresh(fresh, targetFresh);
        if (peerReview == null) {
            peerReview = new PeerReview(fresh, targetFresh, whetherKnow, attendanceScore, contributionScore, communicationScore, comments);
        }else {
            peerReview.setWhetherKnow(whetherKnow);
            peerReview.setAttendanceScore(attendanceScore);
            peerReview.setContributionScore(contributionScore);
            peerReview.setCommunicationScore(communicationScore);
            peerReview.setComments(comments);
        }
        peerReviewDAO.save(peerReview);
    }

    public PeerReview getPeerReview(Fresh fresh, Fresh targetFresh) {
        return peerReviewDAO.findByFromFreshAndTargetFresh(fresh, targetFresh);
    }

    public List<PeerReview> getAllPeerReviews() {
        return peerReviewDAO.findAll();
    }
}
