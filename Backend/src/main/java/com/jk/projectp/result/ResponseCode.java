package com.jk.projectp.result;

public enum ResponseCode {

    SUCCESS(200, "Success"),
    PERMISSION_DENY(300, "Permission deny"),
    LIMIT_EXCEED(400, "Limit exceed max"),
    LOGIN_USER_NOT_EXIST(1001, "User not exist"),
    LOGIN_WRONG_PASSWORD(1002, "Wrong password"),

    NOT_LOGIN(1003, "Not login"),
    USER_NOT_EXIST(1004, "User not exist"),
    CREATING_FB_ERROR(1010, "Error in creating feedback!"),
    FB_NOT_EXIST(1011, "Feedback not exist!"),
    CREATING_COMMENT_ERROR(1012, "Error in creating comment!"),
    COMMENT_NOT_EXIST(1013, "Comment not exist!"),
    INSUFFICIENT_INFO(1014, "Lack non-nullable information"),
    ITSC_FORMAT_WRONG(1015, "Itsc email format is wrong!"),
    ITSC_ALREADY_EXIST(1016, "This itsc has already registered!"),
    FRESH_POSITION_NOT_SELECTED(1017, "Fresh positions not selected!"),
    CREATE_ANNOUNCEMENT_ERROR(1018, "Error in creating announcement"),

    INTERVIEW_NOT_EXIST(1101, "Interview ID do not exist"),
    INTERVIEW_FULL(1102, "Selected interview timeslot is full"),
    INTERVIEW_ERROR(1103, "Error in creating interview"),
    ;

    private final int code;
    private final String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
