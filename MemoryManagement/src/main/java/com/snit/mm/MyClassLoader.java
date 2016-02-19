package com.snit.mm;

import java.io.*;
import java.net.*;

/**
 * Java.MP.C4.M1.MemoryManagement Default Task03
 *
 * @author Ilya Snitavets
 * @see java.lang.ClassLoader
 */
public class MyClassLoader extends ClassLoader {

  public MyClassLoader(ClassLoader parent) {
    super(parent);
  }

  /**
   *
   * @param name class name. If 'reflection.MyObject' will use classes\com\snit\mm\OOMPermgenSpace.class
   * @return loaded class
   * @throws ClassNotFoundException
   */
  public Class loadClass(String name) throws ClassNotFoundException {
    if (!"reflection.MyObject".equals(name)) {
      return super.loadClass(name);
    }
    try {
      File f = new File("classes\\com\\snit\\mm\\OOMPermgenSpace.class");
      String url = "file:" + f.getAbsolutePath();
      URL myUrl = new URL(url);
      URLConnection connection = myUrl.openConnection();
      InputStream input = connection.getInputStream();
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      int data = input.read();

      while (data != -1) {
        buffer.write(data);
        data = input.read();
      }

      input.close();

      byte[] classData = buffer.toByteArray();

      return defineClass("com.snit.mm.OOMPermgenSpace",
          classData, 0, classData.length);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

}