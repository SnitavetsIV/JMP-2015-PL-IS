package com.snit.cl.service;

import com.snit.cl.db.DBHelper;
import com.snit.cl.entity.Game;
import com.snit.cl.util.IntegerUtil;

import java.util.List;

/**
 * @author Ilya Snitavets
 */
public class GameService {

  private static final GameService INSTANCE = new GameService();

  private final DBHelper DB_HELPER;
  private final PlayerService PLAYER_SERVICE;

  private GameService() {
    DB_HELPER = DBHelper.getInstance();
    PLAYER_SERVICE = PlayerService.getInstance();
  }

  public static GameService getInstance() {
    return INSTANCE;
  }

  public List<Game> getAllGames() {
    return DB_HELPER.findAllEntity(Game.class);
  }

  public Game createGame(String[] args) {
    if (args == null || args.length < 6) {
      return null;
    }
    int[] fields = new int[args.length];
    for (int i = 0; i < args.length; i++) {
      if (!IntegerUtil.isInteger(args[i])) {
        return null;
      }
      fields[i] = Integer.parseInt(args[i]);
    }
    Game game = new Game();
    game.setBlueAttack(PLAYER_SERVICE.findPlayer(fields[0]));
    game.setBlueDefence(PLAYER_SERVICE.findPlayer(fields[1]));
    game.setRedAttack(PLAYER_SERVICE.findPlayer(fields[2]));
    game.setRedDefence(PLAYER_SERVICE.findPlayer(fields[3]));
    game.setScoreBlue(fields[4]);
    game.setScoreRed(fields[5]);
    return DB_HELPER.saveOrUpdateEntity(game) ? game : null;
  }

  public Game updateGame(int id, String[] args) {
    if (id < 1 || args == null || args.length < 6) {
      return null;
    }
    Game game = DB_HELPER.findEntity(Game.class, id);
    if (game == null) {
      return null;
    }
    int[] fields = new int[args.length];
    for (int i = 0; i < args.length; i++) {
      if (!IntegerUtil.isInteger(args[i])) {
        return null;
      }
      fields[i] = Integer.parseInt(args[i]);
    }
    game.setBlueAttack(PLAYER_SERVICE.findPlayer(fields[0], /*withDeleted*/ true));
    game.setBlueDefence(PLAYER_SERVICE.findPlayer(fields[1], /*withDeleted*/ true));
    game.setRedAttack(PLAYER_SERVICE.findPlayer(fields[2], /*withDeleted*/ true));
    game.setRedDefence(PLAYER_SERVICE.findPlayer(fields[3], /*withDeleted*/ true));
    game.setScoreBlue(fields[4]);
    game.setScoreRed(fields[5]);
    return DB_HELPER.saveOrUpdateEntity(game) ? game : null;
  }

  public boolean deleteGame(int id) {
    if (id < 1) {
      return false;
    }
    Game game = DB_HELPER.findEntity(Game.class, id);
    if (game == null) {
      return false;
    }
    game.setDeleted(true);
    return DB_HELPER.saveOrUpdateEntity(game);
  }

}
