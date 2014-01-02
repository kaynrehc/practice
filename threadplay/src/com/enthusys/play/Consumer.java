package com.enthusys.play;

/**
 * Created with IntelliJ IDEA.
 * User: mchernyak
 * Date: 12/15/13
 * Time: 7:48 PM
 */
public class Consumer implements Runnable {

    private Queue q;

    public Consumer(Queue q) {
        System.out.println("***** Consumer()");
        this.q = q;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("***** got:" + q.get());
        }
        System.out.println("***** Consumer stops.");
    }

}
