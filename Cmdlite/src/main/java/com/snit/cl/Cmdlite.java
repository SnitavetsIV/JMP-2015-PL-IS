package com.snit.cl;

import com.snit.cl.db.DBHelper;

/**
 * @author Ilya Snitavets
 */
public class Cmdlite {

  public static void main(String[] args) {
    System.out.println(DBHelper.getInstance().getAllPlayers());
  }

}
