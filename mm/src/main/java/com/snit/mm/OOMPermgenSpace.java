package com.snit.mm;

import java.util.*;

/**
 * Java.MP.C4.M1.MemoryManagement Default Task03
 *
 * @author Ilya Snitavets
 */
public class OOMPermgenSpace {

  public static void main(String[] args) {
    try {
      ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
      ArrayList<Class> a = new ArrayList<>();
      while (true) {
        MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
        a.add(classLoader.loadClass("reflection.MyObject"));
      }
    } catch (Error | Exception e) {
      e.printStackTrace();
    }
  }

}
