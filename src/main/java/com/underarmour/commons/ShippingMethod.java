package com.underarmour.commons;

public enum ShippingMethod {
    STANDARD("Standard", "shipping-method-standard", 4.99),
    EXPRESS_3DAYS("Express 3-Day", "shipping-method-express-3-day", 9.99),
    BUSINESS_1_2DAYS("1-2 Business Days", "shipping-method-1-business-day", 19.99),
    SHOP_RUNNER("ShopRunner", "shipping-method-shoprunner", 0.00);

    private final String name;
    private final String forValue;
    private final Double price;

    public String getName() {
        return name;
    }

    public String getForValue() {
        return forValue;
    }

    public Double getPrice() {
        return price;
    }

    ShippingMethod(String name, String forValue, Double price) {
        this.name = name;
        this.forValue = forValue;
        this.price = price;
    }
}