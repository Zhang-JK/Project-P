package com.jk.projectp.service;

import com.jk.projectp.dao.FreshDAO;
import com.jk.projectp.dao.InterviewDAO;
import com.jk.projectp.dao.InterviewFreshDAO;
import com.jk.projectp.model.Fresh;
import com.jk.projectp.model.Interview;
import com.jk.projectp.model.InterviewFresh;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.utils.dataenum.FreshStage;
import com.jk.projectp.utils.dataenum.InterviewRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InterviewService {

    @Autowired
    private InterviewDAO interviewDAO;

    @Autowired
    private InterviewFreshDAO interviewFreshDAO;

    @Autowired
    private FreshDAO freshDAO;

    public List<Interview> getInterviews() {
        return interviewDAO.findAll();
    }

    public ResponseCode updateInterview(Integer userId, Integer freshId, Integer interviewId) {
        Interview interview = interviewDAO.findById(interviewId).isPresent() ? interviewDAO.findById(interviewId).get() : null;
        Fresh fresh = freshDAO.findById(freshId).isPresent() ? freshDAO.findById(freshId).get() : null;
        if (interview == null)
            return ResponseCode.INTERVIEW_NOT_EXIST;
        if (fresh == null)
            return ResponseCode.USER_NOT_EXIST;
        if (!Objects.equals(fresh.getUser().getId(), userId))
            return ResponseCode.NOT_LOGIN;
        if (interviewFreshDAO.countByInterviewId(interviewId) >= 9 || interview.getFull())
            return ResponseCode.INTERVIEW_FULL;

        Interview interviewPrev = null;
        InterviewFresh interviewFresh = interviewFreshDAO.findByFreshId(freshId);
        if (interviewFresh == null) {
            interviewFresh = new InterviewFresh();
            interviewFresh.setFresh(fresh);
        } else {
            interviewPrev = interviewFresh.getInterview();
            if (Objects.equals(interviewPrev.getId(), interviewId))
                return ResponseCode.SUCCESS;
        }
        interviewFresh.setInterview(interview);
        int timeIndex = 1;
        for (InterviewFresh i : interviewFreshDAO.findAllByInterviewIdOrderByTimeIndexAsc(interviewId)) {
            if (i.getTimeIndex() > timeIndex)
                break;
            timeIndex++;
        }
        interviewFresh.setTimeIndex(timeIndex);
        interviewFresh.setRoom((timeIndex - 1) / 3 == 0 ? InterviewRoom.ROOM_A : (timeIndex - 1) / 3 == 1 ? InterviewRoom.ROOM_B : InterviewRoom.ROOM_C);
        interviewFreshDAO.saveAndFlush(interviewFresh);

        if (interviewFreshDAO.findByFreshId(freshId) == null || !Objects.equals(interviewFreshDAO.findByFreshId(freshId).getInterview().getId(), interviewId))
            return ResponseCode.INTERVIEW_ERROR;

        if (interviewFreshDAO.countByInterviewId(interviewId) >= 9) {
            interview.setFull(true);
            interviewDAO.saveAndFlush(interview);
        }
        if (interviewPrev != null) {
            if (interviewFreshDAO.countByInterviewId(interviewPrev.getId()) < 9) {
                interviewPrev.setFull(false);
                interviewDAO.saveAndFlush(interviewPrev);
            }
        }
        fresh.setStage(FreshStage.STARTED);
        freshDAO.saveAndFlush(fresh);


        return ResponseCode.SUCCESS;
    }

    public InterviewFresh getInfoByFreshId(Integer freshId) {
        return interviewFreshDAO.findByFreshId(freshId);
    }
}
