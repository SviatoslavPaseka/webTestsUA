package com.underarmour.commons;

public enum HeaderNavItems {
    NEW("new-arrivals"),
    MEN("men"),
    WOMEN("women"),
    KIDS("kids"),
    SHOES("footwear"),
    BACK_TO_SCHOOL("back-to-school"),
    OUTLET("outlet");

    private final String DOMId;

    public String getDOMId() {
        return DOMId;
    }

    HeaderNavItems(String DOMId) {
        this.DOMId = DOMId;
    }
}