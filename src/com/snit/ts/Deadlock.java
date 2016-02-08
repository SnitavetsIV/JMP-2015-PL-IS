package com.snit.ts;

public class Deadlock {


  private static final Object MONITOR1 = new Object();
  private static final Object MONITOR2 = new Object();
  private static final Object MONITOR3 = new Object();
  private static final Object MONITOR4 = new Object();


  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (MONITOR1) {
          try {
            System.out.println("Thread1: MONITOR1 locked");
            Thread.sleep(1000);
            System.out.println("Thread1: trying to lock MONITOR2");
            synchronized (MONITOR2) {
              System.out.println("Thread1: MONITOR2 locked");
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (MONITOR2) {
          try {
            System.out.println("Thread2: MONITOR2 locked");
            Thread.sleep(1000);
            System.out.println("Thread2: trying to lock MONITOR3");
            synchronized (MONITOR3) {
              System.out.println("Thread2: MONITOR3 locked");
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    Thread t3 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (MONITOR3) {
          try {
            System.out.println("Thread3: MONITOR3 locked");
            Thread.sleep(1000);
            System.out.println("Thread3: trying to lock MONITOR4");
            synchronized (MONITOR4) {
              System.out.println("Thread3: MONITOR4 locked");
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    Thread t4 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (MONITOR4) {
          try {
            System.out.println("Thread4: MONITOR4 locked");
            Thread.sleep(1000);
            System.out.println("Thread4: trying to lock MONITOR1");
            synchronized (MONITOR1) {
              System.out.println("Thread4: MONITOR1 locked");
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t1.join();
    t2.join();
    t3.join();
    t4.join();
  }

}
