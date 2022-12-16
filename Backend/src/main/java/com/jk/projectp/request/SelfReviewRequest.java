package com.jk.projectp.request;

import java.util.Set;

public class SelfReviewRequest {
    private String contribution;
    private Set<Integer> directionIds; // direction i

    public SelfReviewRequest(String contribution, Set<Integer> positionIds) {
        this.contribution = contribution;
        this.directionIds = positionIds;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public Set<Integer> getDirectionIds() {
        return directionIds;
    }

    public void setDirectionIds(Set<Integer> directionIds) {
        this.directionIds = directionIds;
    }
}
