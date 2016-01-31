package com.snit.mt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Opposition {

    public class Counter {
        private int count = 10;
        private final Object monitor = new Object();

        public void increment() {
            synchronized (monitor) {
                count++;
                if (count == 1) {
                    monitor.notifyAll();
                }
            }
        }
        public void decrement() throws InterruptedException {
            synchronized (monitor) {
                while (count == 0) {
                    monitor.wait();
                }
                count--;
            }
        }
        public int get() {
            return count;
        }
    }

    public class Wrestler implements Runnable {
        private Counter counter;
        private boolean increment;
        private Random rand;

        public Wrestler(Counter counter, boolean increment) {
            this.counter = counter;
            this.increment = increment;
            rand = new Random();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (increment) {
                        counter.increment();
                    } else {
                        counter.decrement();
                    }

                    int x = counter.get();
                    if (x < 0) {
                        threads.forEach(Thread::interrupt);
                        throw new IllegalStateException("We have below zero!");
                    }

                    System.out.println("Wrestler" + Thread.currentThread().getName() + " " + x);
                    Thread.sleep(rand.nextInt(100));
                }
            } catch (InterruptedException ignored) {
            }
        }

    }

    private List<Thread> threads;

    public static void main(String[] args) {
        new Opposition().start();
    }

    private void start() {
        Counter counter = new Counter();
        threads = new ArrayList<>();
        for (int i = 1 ; i < 100; i++) {
            threads.add(new Thread(new Wrestler(counter, i % 2 == 0)));
        }
        threads.forEach(Thread::start);
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException ignored) {}
        System.out.println("Finished");
    }

}
