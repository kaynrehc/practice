package com.enthusys.threadplay;

/**
 * Created with IntelliJ IDEA.
 * User: mchernyak
 * Date: 12/15/13
 * Time: 7:40 PM
 */
public class Producer implements Runnable {
    private Queue q;

    public Producer(Queue q) {
        System.out.println("***** Producer()");
        this.q = q;
    }

    @Override
    public void run() {
        for (int i=1; i<=100; i++) {
            q.put(i);
        }
        System.out.println("***** Producer stops.");

    }
}