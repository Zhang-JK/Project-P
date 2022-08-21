package com.jk.projectp.service;

import com.jk.projectp.dao.CommentMessageFeedbackDAO;
import com.jk.projectp.dao.MessageFeedbackDAO;
import com.jk.projectp.model.FeedbackComment;
import com.jk.projectp.model.Feedback;
import com.jk.projectp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    MessageFeedbackDAO fbDAO;

    @Autowired
    CommentMessageFeedbackDAO commentDAO;

    public boolean createFB(User user, String msg, Instant time, String title) {
        Feedback fb = new Feedback(msg, user, time, title);
        fbDAO.save(fb);
        return true;
    }

    public boolean createComment(User user, String msg, Integer parentId, Feedback feedback, Instant time) {

        Optional<FeedbackComment> result = commentDAO.findById(parentId);
        FeedbackComment parent = result.orElse(null);
        FeedbackComment comment = new FeedbackComment(msg, user, parent, feedback, time);
        commentDAO.save(comment);
        return true;
    }

    public Feedback getFeedbackById(Long id) {

        Optional<Feedback> result = fbDAO.findById(id);
        return result.orElse(null);
    }

    public FeedbackComment getCommentById(Integer id) {
        Optional<FeedbackComment> result = commentDAO.findById(id);
        return result.orElse(null);
    }

    public List<Feedback> getAllFeedback() {
        return fbDAO.findAll();
    }

    public List<FeedbackComment> getCommentOfFeedback(Long fbId) {
        return commentDAO.findByFeedback_Id(fbId);
    }

    public void saveFeedback(Feedback fb) {
        fbDAO.save(fb);
    }

    public void saveComment(FeedbackComment comment) {
        commentDAO.save(comment);
    }

    public void deleteFeedback(Feedback fb) {
        commentDAO.deleteByFeedback(fb);
        fbDAO.delete(fb);
    }

    public void deleteComment(FeedbackComment comment) {
        List<FeedbackComment> children = commentDAO.findByParent(comment);
        for (FeedbackComment comment1 : children) {
            deleteComment(comment1);
        }
        commentDAO.delete(comment);
    }


}
