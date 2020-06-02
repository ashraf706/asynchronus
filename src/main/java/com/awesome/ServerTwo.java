package com.awesome;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public class ServerTwo {

    public int execute()  {
        long start = System.currentTimeMillis();
        System.out.println("Sever Two start processing...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.printf("Server two respond after %d seconds %n", (end -start)/1000);

        return 200;
    }

    public Future<Integer> executeAsync(Executor executor){
        return CompletableFuture.supplyAsync(()-> execute(),executor);
    }
}
