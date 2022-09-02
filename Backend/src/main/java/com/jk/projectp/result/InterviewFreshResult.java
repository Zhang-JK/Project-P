package com.jk.projectp.result;

import com.jk.projectp.model.Interview;
import com.jk.projectp.model.InterviewFresh;
import com.jk.projectp.result.pojo.FreshPojo;
import com.jk.projectp.result.pojo.InterviewPojo;
import com.jk.projectp.utils.dataenum.InterviewRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterviewFreshResult {
    private InterviewPojo interview;
    private Map<String, List<FreshPojo>> fresh = new HashMap<>();

    public InterviewFreshResult(Interview interview) {
        this.interview = new InterviewPojo(interview);
        for (InterviewFresh i : interview.getInterviewFreshes()) {
            if (fresh.containsKey(i.getRoom().getInterviewRoom())) {
                fresh.get(i.getRoom().getInterviewRoom()).add(new FreshPojo(i.getFresh()));
            } else {
                fresh.put(i.getRoom().getInterviewRoom(), new ArrayList<FreshPojo>() {{
                    add(new FreshPojo(i.getFresh()));
                }});
            }
        }
    }

    public InterviewPojo getInterview() {
        return interview;
    }

    public void setInterview(InterviewPojo interview) {
        this.interview = interview;
    }

    public Map<String, List<FreshPojo>> getFresh() {
        return fresh;
    }

    public void setFresh(Map<String, List<FreshPojo>> fresh) {
        this.fresh = fresh;
    }
}
