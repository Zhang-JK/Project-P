package com.jk.projectp.service;

import com.jk.projectp.dao.CommentMessageFeedbackDAO;
import com.jk.projectp.dao.MessageFeedbackDAO;
import com.jk.projectp.model.CommentMessageFeedback;
import com.jk.projectp.model.Feedback;
import com.jk.projectp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    MessageFeedbackDAO fbDAO;

    @Autowired
    CommentMessageFeedbackDAO commentDAO;

    public boolean createFB(User user, String msg, Instant time) {
        Feedback fb = new Feedback(msg, user, time);
        fbDAO.save(fb);
        return true;
    }

    public boolean createComment(User user, String msg, Integer parentId, Feedback feedback, Instant time) {

        Optional<CommentMessageFeedback> result = commentDAO.findById(parentId);
        CommentMessageFeedback parent = result.orElse(null);
        CommentMessageFeedback comment = new CommentMessageFeedback(msg, user, parent, feedback, time);
        commentDAO.save(comment);
        return true;
    }

    public Feedback getFeedbackById(Long id) {

        Optional<Feedback> result = fbDAO.findById(id);
        return result.orElse(null);
    }

}
