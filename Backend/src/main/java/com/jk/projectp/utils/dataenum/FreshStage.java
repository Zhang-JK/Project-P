package com.jk.projectp.utils.dataenum;

public enum FreshStage {
    NONE("Not Started"),
    STARTED("Interview Ready"),
    INTERVIEW("Interview PASS"),
    TUTORIAL("Tutorial PASS"),
    INTERNAL("Internal PASS"),
    OFFER("Official Member"),
    FAIL("Disqualified");

    private final String freshStage;

    public String getFreshStage() {
        return freshStage;
    }

    FreshStage(String freshStage) {
        this.freshStage = freshStage;
    }
}
