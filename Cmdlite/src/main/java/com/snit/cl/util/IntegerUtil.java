package com.snit.cl.util;

/**
 * @author Ilya Snitavets
 */
public final class IntegerUtil {

  private IntegerUtil() {
  }

  public static boolean isInteger(String s) {
    if (s == null || s.isEmpty()) return false;
    for (int i = 0; i < s.length(); i++) {
      if (i == 0 && s.charAt(i) == '-') {
        if (s.length() == 1) return false;
        else continue;
      }
      if (Character.digit(s.charAt(i), 10) < 0) return false;
    }
    return true;
  }
}
