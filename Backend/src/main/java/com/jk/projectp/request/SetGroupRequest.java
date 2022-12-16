package com.jk.projectp.request;

public class SetGroupRequest {
    private Integer freshId;
    private Integer groupNum;

    public SetGroupRequest(Integer freshId, Integer groupNum) {
        this.freshId = freshId;
        this.groupNum = groupNum;
    }

    //setters and getters

    public Integer getFreshId() {
        return freshId;
    }
    public Integer getGroupNum() {
        return groupNum;
    }

    public void setFreshId(Integer freshId) {
        this.freshId = freshId;
    }
    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

}
