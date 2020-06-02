package com.awesome.bestprice;

import com.awesome.Util;

public class DiscountService {

    public String applyDiscount(Quote quote){
        return quote.getShopName() +  "price is: " + apply(quote.getPrice(), quote.getDiscount());
    }

    private double apply(double price, Discount discount){
        Util.delay();
        return price * (100 - discount.getPercentage())/100;
    }
}
