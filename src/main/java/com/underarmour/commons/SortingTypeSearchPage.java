package com.underarmour.commons;

public enum SortingTypeSearchPage {
    NEW_TRENDING("product-list-sorting-option-now-trending"),
    BEST_SELLERS("product-list-sorting-option-top-sellers"),
    PRICE_LOW_HIGH("product-list-sorting-option-price-low-high"),
    PRICE_HIGH_LOW("product-list-sorting-option-price-high-low"),
    TOP_RATED("product-list-sorting-option-top-rated"),
    NEWEST("product-list-sorting-option-newest");

    private final String DOMId;

    public String getDOMId() {
        return DOMId;
    }

    SortingTypeSearchPage(String DOMId) {
        this.DOMId = DOMId;
    }
}