package com.awesome.bestprice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class BestPrice {
    private final List<Shop> shops;
    private final Executor executor;
    private final DiscountService discountService;
    private final ExchangeService exchangeService;

    public BestPrice(DiscountService discountService, ExchangeService exchangeService) {
        this.discountService = discountService;
        this.exchangeService = exchangeService;
        this.shops = new ArrayList<>();
        shops.addAll(Arrays.asList(
                new Shop("AA"),
                new Shop("BB"),
                new Shop("CC"),
                new Shop("DD"))
        );

        executor = Executors.newFixedThreadPool(5, (Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public List<String> findPrice(String product) {
        List<CompletableFuture<String>> futurePrice = shops.stream()
                .map(shop -> shop.getPriceStrAsync(product, executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture
                        .supplyAsync(() -> discountService.applyDiscount(quote))))
                .collect(Collectors.toList());

        return futurePrice.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public void showBestPrices(){
        long start = System.nanoTime();
        System.out.println(findPrice("my product"));
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("completed after " + invocationTime + " milliseconds");
    }

    public void show() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        CompletableFuture<Double> futurePrice = shop.getPriceAsync("my favourite product");
        CompletableFuture<Double> exchangedPrice = futurePrice
                .thenCombine(CompletableFuture.supplyAsync(() -> exchangeService.getRate()), (x, y) -> x * y);

        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " milliseconds");

        doSomethingElse(" update status");

        try {
            Double price = exchangedPrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + retrievalTime + " milliseconds");
    }

    private void doSomethingElse(String task) {
        System.out.println("executing " + task + " ================>");
    }
}
