package com.snit.cl.service;

import com.snit.cl.db.DBHelper;
import com.snit.cl.entity.Game;

import java.util.List;

/**
 * @author Ilya Snitavets
 */
public class GameService {

  private static final GameService INSTANCE = new GameService();

  private final DBHelper DB_HELPER;

  private GameService() {
    DB_HELPER = DBHelper.getInstance();
  }

  public static GameService getInstance() {
    return INSTANCE;
  }

  public List<Game> getAllGames() {
    return DB_HELPER.getAllEntity(Game.class);
  }

}
