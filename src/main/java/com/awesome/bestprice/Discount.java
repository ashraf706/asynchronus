package com.awesome.bestprice;

public enum Discount {
    NONE(0), SILVER(5), GOLD(10), PLATINUM(15);

    public int getPercentage() {
        return percentage;
    }

    private final int percentage;

    Discount(int percentage) {
        this.percentage = percentage;
    }
}
