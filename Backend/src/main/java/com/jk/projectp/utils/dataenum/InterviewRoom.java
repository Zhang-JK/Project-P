package com.jk.projectp.utils.dataenum;

public enum InterviewRoom {
    ROOM_A("2132A"),
    ROOM_B("2132B"),
    ROOM_C("2132C");

    private final String interviewRoom;

    public String getInterviewRoom() {
        return interviewRoom;
    }

    InterviewRoom(String interviewRoom) {
        this.interviewRoom = interviewRoom;
    }
}
