package com.underarmour.commons;

public enum HeaderNavItemLinks {
    NEW("nav-link-new-arrivals"),
    MEN("nav-link-men"),
    WOMEN("nav-link-women"),
    KIDS("nav-link-kids"),
    SHOES("nav-link-footwear"),
    BACK_TO_SCHOOL("nav-link-back-to-school"),
    OUTLET("nav-link-outlet");

    private final String DOMData_testid;

    public String getDOMData_testid() {
        return DOMData_testid;
    }

    HeaderNavItemLinks(String DOMData_testid) {
        this.DOMData_testid = DOMData_testid;
    }
}