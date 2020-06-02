package com.awesome;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousDependency {
    private final ExecutorService executor;

    public AsynchronousDependency() {
        executor = Executors.newFixedThreadPool(5, (Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public int result(){

        //CompletableFuture<Integer> a = new CompletableFuture<>();
        //CompletableFuture<Integer> b = new CompletableFuture<>();

        /*CompletableFuture<Integer> c = rateAsync()
                .thenApply(i -> i/1)
                .thenCombine(calculateAsync(), (y, z) -> y * z);*/

        //executor.submit(() -> a.complete(rate()));
        //executor.submit(() -> b.complete(calculate()));

        CompletableFuture<Integer> d = rateAsync().thenComposeAsync(this::calculateComposeAsync);

        int result = 0;
        try {
            result = d.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        executor.shutdown();

        return result;
    }

    private CompletableFuture<Integer> calculateAsync(){
        System.out.printf("calculate returns after %d seconds %n", Util.delay(2000));
        return  CompletableFuture.supplyAsync(() -> 30);
    }

    private CompletableFuture<Integer> calculateComposeAsync(int i){
        System.out.printf("calculate returns after %d seconds %n", Util.delay(2000));
        return  CompletableFuture.supplyAsync(() ->  i /10);
    }

    private CompletableFuture<Integer> rateAsync(){
        System.out.printf("Rate returns after %d seconds %n", Util.delay(3000));
        return  CompletableFuture.supplyAsync(() -> 50);
    }

    private int rate(){
        System.out.printf("Rate returns after %d seconds %n", Util.delay(3000));
        return 5;
    }


    private int calculate(){
        System.out.printf("calculate returns after %d seconds %n", Util.delay(2000));
        return  3;
    }

}
