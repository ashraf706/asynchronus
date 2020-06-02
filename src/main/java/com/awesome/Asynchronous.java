package com.awesome;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Asynchronous {

    private final ServerOne serverOne;
    private final ServerTwo serverTwo;
    private final Executor executor;

    public Asynchronous(ServerOne serverOne, ServerTwo serverTwo) {
        this.serverOne = serverOne;
        this.serverTwo = serverTwo;

        executor = Executors.newFixedThreadPool(5, (Runnable r) -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public int bar(){
        long start = System.currentTimeMillis();
        int result = 0;

        try {
            Future<Integer> twoFeature = serverTwo.executeAsync(executor);
            Future<Integer> oneFeature = serverOne.executeAsync(executor);
            result = twoFeature.get() + oneFeature.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.printf("Foo completed in: %d seconds", (end -start)/1000);

        return result;
    }
}
