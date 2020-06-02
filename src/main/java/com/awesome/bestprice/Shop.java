package com.awesome.bestprice;

import com.awesome.Util;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public class Shop {

    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    public double getPrice(String product){
        return calculatePrice(product);
    }

    public CompletableFuture<Double> getPriceAsync(String product){
        return CompletableFuture.supplyAsync(()-> getPrice(product));
    }

    public CompletableFuture<String> getPriceStrAsync(String product, Executor executor){
        return CompletableFuture.supplyAsync(()-> {
            Discount disc = Discount.values()[new Random().nextInt(Discount.values().length)];
            double price = getPrice(product);
            return String.format("%s:%.2f:%s", product, price, disc);
        }, executor);
    }

    private double calculatePrice(String product) {
        Util.delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
