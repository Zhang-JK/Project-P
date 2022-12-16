package com.jk.projectp.request;

public class AddInternGroupRequest {
    private Integer groupNum;
    private String groupName;

    public AddInternGroupRequest(Integer groupNum, String groupName) {
        this.groupNum = groupNum;
        this.groupName = groupName;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
