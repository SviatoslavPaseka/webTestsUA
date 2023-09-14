package com.underarmour.commons;

public enum PaymentDataForCreditCard {
    CARD_NUMBER("cNumber"),
    EXPIRATION_DATE("exDate"),
    SECURITY_CODE("secCode"),
    NAME_ON_CARD("cName");

    private final String domID;

    public String getDomID() {
        return domID;
    }

    PaymentDataForCreditCard(String domID) {
        this.domID = domID;
    }
}