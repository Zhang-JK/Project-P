package com.jk.projectp.utils.dataenum;

public enum WebPages {
    PROJECT_MANAGE("projectManage"),
    HUMAN_RESOURCE("humanResource"),
    ANNOUNCEMENT("announcement"),
    FINANCE("finance"),
    FEEDBACK("feedback");

    private final String webPage;

    public String getWebPage() {
        return webPage;
    }

    WebPages(String webPage) {
        this.webPage = webPage;
    }
}
