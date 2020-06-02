package com.awesome;

public class Synchronous {
    private final ServerOne serverOne;
    private final ServerTwo serverTwo;

    public Synchronous(ServerOne serverOne, ServerTwo serverTwo) {
        this.serverOne = serverOne;
        this.serverTwo = serverTwo;
    }

    public int foo(){
        long start = System.currentTimeMillis();
        int result = serverTwo.execute() + serverOne.execute();
        long end = System.currentTimeMillis();

        System.out.printf("Foo completed in: %d seconds", (end -start)/1000);

    return result;
    }
}
