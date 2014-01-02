package com.enthusys.play;

public class Main {

    public static void main(String[] args) {
        Queue q = new Queue();

        Consumer consumer = new Consumer(q);
        Producer producer = new Producer(q);

        Thread tConsumer = new Thread(consumer);
        tConsumer.start();

        Thread tProducer = new Thread(producer);
        tProducer.start();

        System.out.println("***** main() stops");
    }
}
