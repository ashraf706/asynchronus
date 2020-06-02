package com.awesome;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SynchronousTest {

    @Test
    public void synchronous() {
        ServerTwo serverTwo = new ServerTwo();
        assertEquals(200, serverTwo.execute());
        System.out.println("");
        ServerOne serverOne = new ServerOne();
        assertEquals(100, serverOne.execute());

    }

    @Test
    public void callSynchronous() {
        Synchronous synchronous = new Synchronous(new ServerOne(), new ServerTwo());
        synchronous.foo();
    }

    @Test
    public void callAsynchronous() {
        Asynchronous asynchronous = new Asynchronous(new ServerOne(), new ServerTwo());
        asynchronous.bar();

    }

    @Test
    public void asynchronousDependency() {
        AsynchronousDependency asynchronousDependency = new AsynchronousDependency();
        asynchronousDependency.result();
    }
}
