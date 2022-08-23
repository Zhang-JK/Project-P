package com.jk.projectp.utils.dataenum;

public enum Position {
    SOFTWARE("SOFTWARE"),
    HARDWARE("HARDWARE"),
    MECHANICAL("MECHANICAL"),
    LOGISTICS("LOGISTICS"),
    WEBSITE("WEBSITE");
    private String position;

    Position(String position) {
        this.position = position;
    }
}
