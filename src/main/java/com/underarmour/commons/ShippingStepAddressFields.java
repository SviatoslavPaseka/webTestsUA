package com.underarmour.commons;

public enum ShippingStepAddressFields {
    FIRST_NAME("address-firstName"),
    LAST_NAME("address-lastName"),
    ADDRESS1("address-address1"),
    ADDRESS2("address-address2"),
    CITY("address-city"),
    STATE("stateCode"),
    ZIP_CODE("address-postalCode");

    private final String id;

    ShippingStepAddressFields(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}