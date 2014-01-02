package com.enthusys.threadplay;

public class Queue {
    private int myValue;

    public Queue() {
        System.out.println("***** Queue()");
        myValue = -1;
    }

    public synchronized void put(int x) {

        while (myValue != -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myValue = x;
        notify();
    }

    public synchronized int get() {

        while (myValue == -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int rv = myValue;
        myValue = -1;
        notify();
        return rv;
    }
}
