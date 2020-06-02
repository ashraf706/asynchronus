package com.awesome.bestprice;

public class Quote {
    private final String shopName;
    private final double price;
    private final Discount discount;

    public Quote(String shopName, double price, Discount discount) {
        this.shopName = shopName;
        this.price = price;
        this.discount = discount;
    }

    public static Quote parse(String quote){
        String[] split = quote.split(":");
        String shopName = split[0];
        Double price = Double.valueOf(split[1]);
        Discount discount = Discount.valueOf(split[2]);

        return new Quote(shopName,price,discount);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }
}
