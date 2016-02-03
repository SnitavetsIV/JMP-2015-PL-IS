package com.snit.ts;

public class Deadlock {


  private static Object obj1 = new Object();
  private static Object obj2 = new Object();
  private static Object obj3 = new Object();
  private static Object obj4 = new Object();


  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (obj1) {
          try {
            System.out.println("Thread1: obj1 locked");
            Thread.sleep(1000);
            System.out.println("Thread1: trying to lock obj2");
            synchronized (obj2) {
              System.out.println("Thread1: obj2 locked");
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
        synchronized (obj2) {
          try {
            System.out.println("Thread2: obj2 locked");
            Thread.sleep(1000);
            System.out.println("Thread2: trying to lock obj3");
            synchronized (obj3) {
              System.out.println("Thread2: obj3 locked");
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
        synchronized (obj3) {
          try {
            System.out.println("Thread3: obj3 locked");
            Thread.sleep(1000);
            System.out.println("Thread3: trying to lock obj4");
            synchronized (obj4) {
              System.out.println("Thread3: obj4 locked");
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
        synchronized (obj4) {
          try {
            System.out.println("Thread4: obj4 locked");
            Thread.sleep(1000);
            System.out.println("Thread4: trying to lock obj1");
            synchronized (obj1) {
              System.out.println("Thread4: obj1 locked");
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
