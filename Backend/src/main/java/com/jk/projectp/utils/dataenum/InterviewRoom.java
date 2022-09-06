package com.jk.projectp.utils.dataenum;

public enum InterviewRoom {
    ROOM_A("2132A"),
    ROOM_B("2132B"),
    ROOM_C("2132C"),
    ROOM_LIB_A("Library LG1-361"),
    ROOM_LIB_B("Library LG1-362"),
    ROOM_LIB_C("Library LG1-363");

    private final String interviewRoom;

    public String getInterviewRoom() {
        return interviewRoom;
    }

    InterviewRoom(String interviewRoom) {
        this.interviewRoom = interviewRoom;
    }
}
