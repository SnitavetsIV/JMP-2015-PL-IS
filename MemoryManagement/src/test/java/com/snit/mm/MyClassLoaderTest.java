package com.snit.mm;

import org.junit.*;

import java.io.*;

/**
 * @author Ilya Snitavets
 */
public class MyClassLoaderTest {

  @Test
  public void loadTest(){
    ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
    MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
    Class aClass = null;
    try {
      aClass = classLoader.loadClass("com.snit.mm.OOMPermgenSpace");
    } catch (ClassNotFoundException e) {
      Assert.fail(e.getMessage());
    }
    Assert.assertEquals("Wrong class", true, aClass.getCanonicalName().equals(OOMPermgenSpace.class.getCanonicalName()));
  }

}
