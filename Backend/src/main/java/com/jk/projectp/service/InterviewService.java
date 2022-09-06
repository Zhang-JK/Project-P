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

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
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

    @Autowired
    private MailService mailService;

    public List<Interview> getInterviews() {
        return interviewDAO.findAll();
    }

    public ResponseCode updateInterview(Integer userId, Integer freshId, Integer interviewId) {
        Interview interview = interviewDAO.findById(interviewId).isPresent() ? interviewDAO.findById(interviewId).get() : null;
        Fresh fresh = freshDAO.findById(freshId).isPresent() ? freshDAO.findById(freshId).get() : null;
        if (interview == null)
            return ResponseCode.INTERVIEW_NOT_EXIST;
        int maxCapacity = interview.getDate().getDayOfMonth() == 18 ? 6 : 9;
        if (fresh == null)
            return ResponseCode.USER_NOT_EXIST;
        if (!Objects.equals(fresh.getUser().getId(), userId))
            return ResponseCode.NOT_LOGIN;
        if (interviewFreshDAO.countByInterviewId(interviewId) >= maxCapacity || interview.getFull())
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
        if (interview.getDate().getDayOfMonth() == 18 && Integer.parseInt(interview.getStartTime().substring(0, 2)) >= 14)
            interviewFresh.setRoom((timeIndex - 1) / 3 == 0 ? InterviewRoom.ROOM_LIB_A : InterviewRoom.ROOM_LIB_C);
        else if (interview.getDate().getDayOfMonth() == 18 && Integer.parseInt(interview.getStartTime().substring(0, 2)) <= 12)
            interviewFresh.setRoom((timeIndex - 1) / 3 == 0 ? InterviewRoom.ROOM_LIB_A : InterviewRoom.ROOM_LIB_B);
        else
            interviewFresh.setRoom((timeIndex - 1) / 3 == 0 ? InterviewRoom.ROOM_A : (timeIndex - 1) / 3 == 1 ? InterviewRoom.ROOM_B : InterviewRoom.ROOM_C);
        interviewFreshDAO.saveAndFlush(interviewFresh);

        if (interviewFreshDAO.findByFreshId(freshId) == null || !Objects.equals(interviewFreshDAO.findByFreshId(freshId).getInterview().getId(), interviewId))
            return ResponseCode.INTERVIEW_ERROR;

        if (interviewFreshDAO.countByInterviewId(interviewId) >= maxCapacity) {
            interview.setFull(true);
            interviewDAO.saveAndFlush(interview);
        }
        if (interviewPrev != null) {
            if (interviewFreshDAO.countByInterviewId(interviewPrev.getId()) < maxCapacity) {
                interviewPrev.setFull(false);
                interviewDAO.saveAndFlush(interviewPrev);
            }
        }
        fresh.setStage(FreshStage.STARTED);
        freshDAO.saveAndFlush(fresh);
        try {
            mailService.sendMailToUser(fresh.getUser(), "HKUST RoboMaster Team Enterprize Manage System: Interview Registered Successfully",
                    "Dear " + fresh.getName() + ",\n\n" +
                            "Thank you for enrolling into HKUST RoboMaster Team Enterprize :)\n\n" +
                            "You have successfully registered for the interview on " + interview.getDate().toString() + " " + interview.getStartTime() + ".\n" +
                            "The interview room is " + interviewFresh.getRoom().getInterviewRoom() + ".\n" +
                            "Please arrive at the interview location 5 minutes prior to the start of the interview.\n\n" +
                            "Please refer to the link below for the interview process and interview questions. You can also find this document on our management website.\n" +
                            "https://drive.google.com/file/d/1dR38HxJTGOpH0SPhm1boy2ZI8X5tqO_I/\n\n" +
                            "Best Regards,\n" +
                            "HKUST RoboMaster Team Enterprize \n" +
                            "G04, UG HALL VIII, HKUST, Clear Water Bay\n" +
                            "Sai Kung, Hong Kong\n" +
                            "http://laojk.club/");
        } catch (MessagingException | IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return ResponseCode.SUCCESS;
    }

    public InterviewFresh getInfoByFreshId(Integer freshId) {
        return interviewFreshDAO.findByFreshId(freshId);
    }
}
