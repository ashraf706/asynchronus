package com.awesome;

public class Util {

    public static int delay(long milliSec){
        long start = System.currentTimeMillis();

        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        return (int) ((end -start)/1000);
    }

    public static void delay(){
        try {
            Thread.sleep(1000L);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
