package com.jk.projectp.utils.dataenum;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    NOT_TO_TELL("NO");
    private String genderString;

    Gender(String genderString){
        this.genderString = genderString;
    }
}
