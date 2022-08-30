package com.jk.projectp.utils.dataenum;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    NOT_TELL("NOT_TELL");
    private String genderString;

    Gender(String genderString){
        this.genderString = genderString;
    }
}
