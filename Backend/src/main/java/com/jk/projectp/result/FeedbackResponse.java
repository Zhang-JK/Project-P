package com.jk.projectp.result;

import com.jk.projectp.model.Feedback;
import com.jk.projectp.result.pojo.FeedbackPojo;

import java.util.ArrayList;
import java.util.List;

public class FeedbackResponse {

    List<FeedbackPojo> fbPojoList;

    public List<FeedbackPojo> getFbPojoList() {
        return fbPojoList;
    }

    public FeedbackResponse(List<Feedback> fbList) {
        fbPojoList = new ArrayList<>();
        for (Feedback fb: fbList){
            fbPojoList.add(new FeedbackPojo(fb));
        }
    }
}
