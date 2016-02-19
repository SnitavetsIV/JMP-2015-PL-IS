package com.snit.mt;

import java.util.*;

/**
 * ClassModel&Concurrence Default Task 5. Threads coordination
 *
 * @author Ilya Snitavets
 */
public class Opposition {

  private List<Thread> threads;

  public static void main(String[] args) {
    new Opposition().start();
  }

  private void start() {
    Counter counter = new Counter();
    threads = new ArrayList<>();
    for (int i = 1; i < 100; i++) {
      threads.add(new Thread(new Wrestler(counter, i % 2 == 0)));
    }
    for (Thread thread : threads) {
      thread.start();
    }
    try {
      for (Thread t : threads) {
        t.join();
      }
    } catch (InterruptedException ignored) {
    }
    System.out.println("Finished");
  }

  public class Counter {
    private final Object monitor = new Object();
    private int count = 10;

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

  /**
   *
   * This entity try to change counter
   */
  public class Wrestler implements Runnable {
    private final Counter counter;
    private final boolean increment;
    private final Random rand;

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
            for (Thread thread : threads) {
              thread.interrupt();
            }
            throw new IllegalStateException("We have below zero!");
          }

          System.out.println("Wrestler" + Thread.currentThread().getName() + " " + x);
          Thread.sleep(rand.nextInt(100));
        }
      } catch (InterruptedException ignored) {
      }
    }
  }

}
