package com.underarmour.commons;

public enum PaymentRadioButton {
    CREDIT_CARD("AurusCreditCardPaymentInstrument"),
    PAYPAL("PayPalPaymentInstrument"),
    KLARNA("KlarnaPaymentInstrument");

    private final String valueFor;

    PaymentRadioButton(String valueFor) {
        this.valueFor = valueFor;
    }

    public String getValueFor() {
        return valueFor;
    }
}