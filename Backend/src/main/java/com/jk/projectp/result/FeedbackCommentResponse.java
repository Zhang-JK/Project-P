package com.jk.projectp.result;

import com.jk.projectp.model.FeedbackComment;
import com.jk.projectp.result.pojo.FeedbackCommentPojo;

import java.util.ArrayList;
import java.util.List;

public class FeedbackCommentResponse {

    private final List<FeedbackCommentPojo> fbCommentList;

    public List<FeedbackCommentPojo> getFbCommentList() {
        return fbCommentList;
    }

    public FeedbackCommentResponse(List<FeedbackComment> commentList) {
        fbCommentList = new ArrayList<>();
        for (FeedbackComment comment:commentList){
            fbCommentList.add(new FeedbackCommentPojo(comment));
        }
    }
}
