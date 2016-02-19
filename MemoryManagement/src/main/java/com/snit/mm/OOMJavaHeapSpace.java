package com.snit.mm;

/**
 * Java.MP.C4.M1.MemoryManagement Default Task03
 *
 * @author Ilya Snitavets
 */
public class OOMJavaHeapSpace {

  /**
   *
   *  try make heap error
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    while (true) {
      sb.append("Don't Try this at Home");
    }
  }

}
